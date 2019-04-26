package part2;

import part1.Beer;

import java.util.*;
import java.util.function.Predicate;
import static java.util.Objects.nonNull;


public class ContainerProducts<E extends Beer> implements List<E> {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
    }

    private Beer[] arrayOfProducts = new Beer[0];
    private int size = 10;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    } ////////////////////////

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (isElementContains(o, arrayOfProducts[i])){
                return true;
            }
        }
        return false;
    }

    private boolean isElementContains(Object o, Object p) {
        return nonNull(o) ? o.equals(p) : o == p;
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayOfProducts, arrayOfProducts.length);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return a;
    }

    @Override
    public boolean add(E o) {
        arrayOfProducts = Arrays.copyOf(arrayOfProducts, arrayOfProducts.length + 1);
        arrayOfProducts[size] = o;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return nonNull(o) ? changeArrayIfElementIsNotNull(o) : changeArrayIfElementIsNull(o);
    }

    private boolean changeArrayIfElementIsNull(Object o) {
        for (int i = 0; i < arrayOfProducts.length; i++) {
            if (o == arrayOfProducts[i]) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean changeArrayIfElementIsNotNull(Object o) {
        for (int i = 0; i < arrayOfProducts.length; i++) {
            if (o.equals(arrayOfProducts[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        int count = 0;
        for (int i = 0; i < c.size(); i++) {
            if (contains(c.toArray()[i])){
                count++;
            }
        }
        return count == c.size();

    }

    @Override
    public boolean addAll(Collection c) {
      return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        int tmpSize = size();
        Collections.reverse((List)c);
        c.forEach((p) ->add(index, (E)p));
        return size() == tmpSize + c.size();
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean flag = false;
        for (int i = 0; i < c.size(); i++) {
            if (contains(c.toArray()[i])){
                remove(c.toArray()[i]);
                flag = true;
            }
        }
        return flag;
    }



    @Override
    public boolean retainAll(Collection c) {
        boolean flag = false;
        for (int i = 0; i < arrayOfProducts.length; i++) {
            if (!c.contains(arrayOfProducts[i])){
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
            return (E)arrayOfProducts[index];
    }

    @Override
    public E set(int index, E element) {
        arrayOfProducts[index] = element;
        return (E)arrayOfProducts[index];
    }

    @Override
    public void add(int index, E element) {
            add(element);
            removeLastElementToPosition(index);
    }

    private void removeLastElementToPosition(int index) {
        for (int i = arrayOfProducts.length - 1; i > index; i--) {
            Beer tmp = arrayOfProducts[i];
            arrayOfProducts[i] = arrayOfProducts[i - 1];
            arrayOfProducts[i - 1] = tmp;
        }
    }

    private void removeElementToLastPosition(int index){
        for (int i = index; i < size; i++) {
            Beer tmp = arrayOfProducts[i];
            arrayOfProducts[i] = arrayOfProducts[i + 1];
            arrayOfProducts[i + 1] = tmp;
        }
    }

    @Override
    public E remove(int index) {
        Beer o = arrayOfProducts[index];
        --size;
        removeElementToLastPosition(index);
        arrayOfProducts = Arrays.copyOf(arrayOfProducts, arrayOfProducts.length  - 1);
        return (E)o;
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
            for (int i = size-1; i >= 0; i--)
                if (!nonNull(arrayOfProducts[i]))
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(arrayOfProducts[i]))
                    return i;
        }
        return -1;
    }



    @Override
    public ListIterator listIterator() {
        throw new NullPointerException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new NullPointerException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new NullPointerException();
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

    class CustomIterator implements Iterator {
        Predicate predicate;
        int indexForIterator = 0;
        int checker = 0;

        public CustomIterator(Predicate predicate) {
            this.predicate = predicate;
        }

        /**
         Returns true if the iteration has more elements than math che predicate.
         (In other words, returns true if next would return an
         element rather than throwing an exception.)
         *
         */
        @Override
        public boolean hasNext() {
            if(checker < size) {
                while (!predicate.test(arrayOfProducts[checker])) {
                    checker++;
                }
            }
            return indexForIterator < size;
        }


        /**
         Returns the next element in the iteration;
         **/
        @Override
        public Object next() {
            indexForIterator = checker;
            checker++;
            return arrayOfProducts[indexForIterator++];
        }
    }
}
