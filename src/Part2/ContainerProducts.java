package Part2;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

public class ContainerProducts implements List {

    public static void main(String[] args) {
        ContainerProducts c = new ContainerProducts();
        c.add("Hello");
        c.add("LOL");
        c.add("privet");
        System.out.println(Arrays.toString(c.arrayOfProducts));
        c.add(7, "PRIVET");
        ArrayList<String> a = new ArrayList<String>();
        a.add("f");
        a.add("k");
        a.add("m");
        c.addAll(2, a);
        c.add(2, null);
        System.out.println(Arrays.toString(c.arrayOfProducts));
        Iterator it = c.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }


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
    public Object[] toArray() {
        return arrayOfProducts;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return a;
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
        return nonNull(o) ? changeArrayIfElementIsNotNull(o) : changeArrayIfElementIsNull(o);
    }

    private boolean changeArrayIfElementIsNull(Object o) {
        for (int i = 0; i < arrayOfProducts.length; i++) {
            if (o == arrayOfProducts[i]) {
                remove(i);
            }
        }
        return true;
    }

    private boolean changeArrayIfElementIsNotNull(Object o) {
        for (int i = 0; i < arrayOfProducts.length; i++) {
            if (o.equals(arrayOfProducts[i])) {
                remove(i);
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        return c.stream().allMatch(this::contains);

    }

    @Override
    public boolean addAll(Collection c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        Collections.reverse((List)c);
        c.stream().forEach((p) ->add(index, p));
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        c.stream().filter(this::contains).forEach((p) -> remove(p));
        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        c.stream().filter((p) -> !contains(p)).forEach((p) -> remove(p));
        return false;
    }

    @Override
    public void clear() {
        arrayOfProducts = new Object[0];
        size = 0;
    }

    @Override
    public Object get(int index) {
            return arrayOfProducts[index];
    }

    @Override
    public Object set(int index, Object element) {
            arrayOfProducts[index] = element;
        return arrayOfProducts[index];
    }

    @Override
    public void add(int index, Object element) {
            add(element);
            removeLastElementToPosition(index);
    }

    private void removeLastElementToPosition(int index) {
        for (int i = arrayOfProducts.length - 1; i > index; i--) {
            Object tmp = arrayOfProducts[i];
            arrayOfProducts[i] = arrayOfProducts[i - 1];
            arrayOfProducts[i - 1] = tmp;
        }
    }

    private void removeElementToLastPosition(int index){
        for (int i = index; i < size; i++) {
            Object tmp = arrayOfProducts[i];
            arrayOfProducts[i] = arrayOfProducts[i + 1];
            arrayOfProducts[i + 1] = tmp;
        }
    }

    @Override
    public Object remove(int index) {
        Object o = arrayOfProducts[index];
        --size;
        removeElementToLastPosition(index);
        arrayOfProducts = Arrays.copyOf(arrayOfProducts, arrayOfProducts.length  - 1);
        return o;
    }


    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < arrayOfProducts.length; i++) {
            if (o.equals(arrayOfProducts[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int tmp = -1;
        for (int i = 0; i < arrayOfProducts.length; i++) {
            if (o.equals(arrayOfProducts[i])){
                tmp = i;
            }
        }
        return tmp;
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
        return  new Iterator() {
            Predicate predicate = Objects::nonNull;
            int indexForIterator = 0;
            int checker = 0;

            @Override
            public boolean hasNext() {
                if(checker < size) {
                    while (!predicate.test(arrayOfProducts[checker])) {
                        checker++;
                    }
                }
                return indexForIterator < size;
            }

            @Override
            public Object next() {
                    indexForIterator = checker;
                    checker++;
                    return arrayOfProducts[indexForIterator++];
            }
        };
    }

}
