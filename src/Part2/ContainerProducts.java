package Part2;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public class ContainerProducts implements List {
    public static void main(String[] args) {
        ContainerProducts c = new ContainerProducts();
        c.add(null);
        c.add(9);
        System.out.println(Arrays.toString(c.arrayOfProducts));
        System.out.println(c.contains(4));
        System.out.println(c.get(1));
    }


    private Object[] arrayOfProducts = new Object[0];
    private int size = 0;

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
        return Arrays.stream(arrayOfProducts).anyMatch((p) -> isElementContains(o, p));
    }

    private boolean isElementContains(Object o, Object p) {
        return nonNull(o) ? o.equals(p) : o == p;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        arrayOfProducts = Arrays.copyOf(arrayOfProducts, arrayOfProducts.length + 1);
        arrayOfProducts[size] = o;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
     /*   if (contains(o)){
            Arrays.stream(arrayOfProducts).forEachOrdered((p) -> );
        }*/
        return false;
    } /////////////////TO DO


    @Override
    public boolean containsAll(Collection c) {
        return c.stream().allMatch((p) -> contains(p));

    }

    @Override
    public boolean addAll(Collection c) {
        c.stream().forEachOrdered((p) -> add(p));
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {

        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
        arrayOfProducts = new Object[0];
        size = 0;
    }

    @Override
    public Object get(int index) {
        return Arrays.stream(arrayOfProducts).skip(index).findFirst().get();
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
}
