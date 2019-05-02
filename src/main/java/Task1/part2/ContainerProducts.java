package Task1.part2;

import Task1.part1.Beer;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class ContainerProducts<E extends Beer> implements List<E> {
    private Beer[] arrayOfProducts;
    private int size;

    public ContainerProducts() {
        size = 0;
        arrayOfProducts = new Beer[size];
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(Object element) {
        if (isNull(element)) {
            for (int i = 0; i < size; i++)
                if (isNull(arrayOfProducts[i]))
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (element.equals(arrayOfProducts[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayOfProducts, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size)
            return (T[]) Arrays.copyOf(arrayOfProducts, size, array.getClass());
        System.arraycopy(arrayOfProducts, 0, array, 0, size);
        if (array.length > size)
            array[size] = null;
        return array;
    }

    @Override
    public boolean add(E element) {
        if (arrayOfProducts.length == size) {
            arrayOfProducts = Arrays.copyOf(arrayOfProducts, arrayOfProducts.length + 10);
        }
        arrayOfProducts[size++] = element;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        return nonNull(element) ? changeArrayIfElementIsNotNull(element) : changeArrayIfElementIsNull(element);
    }


    @Override
    public boolean containsAll(Collection collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E element : collection) {
            add(element);
        }
        return true;
    }


    @Override
    public boolean addAll(int index, Collection collection) {
        checkIndex(index);
        int tmpIndex = index;
        int tmpSize = size();
        for (int i = 0; i < collection.size(); i++) {
            add(tmpIndex++, (E) collection.toArray()[i]);
        }
        return size() == tmpSize + collection.size();
    }

    @Override
    public boolean removeAll(Collection collection) {
        Objects.requireNonNull(collection);
        boolean flag = false;
        for (int i = 0; i < collection.size(); i++) {
            if (contains(collection.toArray()[i])) {
                remove(collection.toArray()[i]);
                flag = true;
            }
        }
        return flag;
    }


    @Override
    public boolean retainAll(Collection collection) {
        Objects.requireNonNull(collection);
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (!collection.contains(arrayOfProducts[i])) {
                remove(arrayOfProducts[i]);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void clear() {
        arrayOfProducts = new Beer[0];
        size = 0;
    }


    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) arrayOfProducts[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Beer tmp = arrayOfProducts[index];
        arrayOfProducts[index] = element;
        return (E) tmp;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        add(element);
        removeLastElementToPosition(index);
    }


    @Override
    public E remove(int index) {
        checkIndex(index);
        Beer o = arrayOfProducts[index];
        size--;
        removeElementToLastPosition(index);
        arrayOfProducts = Arrays.copyOf(arrayOfProducts, size);
        return (E) o;
    }

    @Override
    public int lastIndexOf(Object element) {
        if (!nonNull(element)) {
            for (int i = size - 1; i >= 0; i--)
                if (!nonNull(arrayOfProducts[i]))
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (element.equals(arrayOfProducts[i]))
                    return i;
        }
        return -1;
    }


    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arrayOfProducts[currentIndex++];
            }
        };
    }

    @Override
    public String toString() {
        return "ContainerProducts{" +
                "arrayOfProducts=" + Arrays.toString(arrayOfProducts) +
                ", size=" + size +
                '}';
    }

    private boolean changeArrayIfElementIsNull(Object element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayOfProducts[i]) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean changeArrayIfElementIsNotNull(Object element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(arrayOfProducts[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    private void checkIndex(int index) {
        if ((index > size) || (index < 0)) {
            throw new IndexOutOfBoundsException("Index is " + index + ", but size is " + size);
        }
    }

    private void removeLastElementToPosition(int index) {
        checkIndex(index);
        for (int i = size - 1; i > index; i--) {
            Beer tmp = arrayOfProducts[i];
            arrayOfProducts[i] = arrayOfProducts[i - 1];
            arrayOfProducts[i - 1] = tmp;
        }
    }

    private void removeElementToLastPosition(int index) {
        checkIndex(index);
        for (int i = index; i < size; i++) {
            Beer tmp = arrayOfProducts[i];
            arrayOfProducts[i] = arrayOfProducts[i + 1];
            arrayOfProducts[i + 1] = tmp;
        }
    }

    class CustomIterator implements Iterator {
        Predicate predicate;
        List<Beer> containerProducts;
        int indexForIterator = 0;
        int checker = 0;

        public CustomIterator(Predicate predicate, List containerProducts) {
            this.predicate = predicate;
            this.containerProducts = containerProducts;
        }

        /**
         * Returns true if the iteration has more elements than math che predicate.
         * (In other words, returns true if next would return an
         * element rather than throwing an exception.)
         */
        @Override
        public boolean hasNext() {
            while (checker < containerProducts.size()
                    && !predicate.test((E) containerProducts.get(checker)))
                checker++;
            return checker < containerProducts.size();
        }

        /**
         * Returns the next element in the iteration;
         **/
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                indexForIterator = checker;
                checker++;
                return (E) containerProducts.get(indexForIterator);
            }
        }
    }
}
