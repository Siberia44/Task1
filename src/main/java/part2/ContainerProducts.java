package part2;

import part1.Beer;

import java.util.*;
import java.util.function.Predicate;

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
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayOfProducts, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(arrayOfProducts, size, a.getClass());
        }
        System.arraycopy(arrayOfProducts, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E o) {
        if (arrayOfProducts.length == size) {
            arrayOfProducts = Arrays.copyOf(arrayOfProducts, arrayOfProducts.length + 10);
        }
        arrayOfProducts[size++] = o;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return nonNull(o) ? changeArrayIfElementIsNotNull(o) : changeArrayIfElementIsNull(o);
    }



    @Override
    public boolean containsAll(Collection c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            add(element);
        }
        return true;
    }


    @Override
    public boolean addAll(int index, Collection c) {
        checkIndex(index);
        int tmpIndex = index;
        int tmpSize = size();
        for (int i = 0; i < c.size(); i++) {
            add(tmpIndex++, (E) c.toArray()[i]);
        }
        return size() == tmpSize + c.size();
    }

    @Override
    public boolean removeAll(Collection c) {
        Objects.requireNonNull(c);
        boolean flag = false;
        for (int i = 0; i < c.size(); i++) {
            if (contains(c.toArray()[i])) {
                remove(c.toArray()[i]);
                flag = true;
            }
        }
        return flag;
    }


    @Override
    public boolean retainAll(Collection c) {
        Objects.requireNonNull(c);
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(arrayOfProducts[i])) {
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
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
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
    public int indexOf(Object o) {
        if (!nonNull(o)) {
            for (int i = 0; i < size; i++)
                if (!nonNull(arrayOfProducts[i]))
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(arrayOfProducts[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (!nonNull(o)) {
            for (int i = size - 1; i >= 0; i--)
                if (!nonNull(arrayOfProducts[i]))
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(arrayOfProducts[i]))
                    return i;
        }
        return -1;
    }


    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
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
                if (!hasNext()){
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

    private boolean changeArrayIfElementIsNull(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == arrayOfProducts[i]) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean changeArrayIfElementIsNotNull(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(arrayOfProducts[i])) {
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
            if (checker < containerProducts.size()) {
                while (!predicate.test(containerProducts.get(checker)) && checker < containerProducts.size()) {
                    checker++;
                }
            }
            return indexForIterator < containerProducts.size();
        }


        /**
         * Returns the next element in the iteration;
         **/
        @Override
        public Object next() {
            indexForIterator = checker;
            checker++;
            return containerProducts.get(indexForIterator);
        }
    }
}
