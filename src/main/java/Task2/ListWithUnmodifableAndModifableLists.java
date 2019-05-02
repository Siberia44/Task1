package Task2;

import Task1.part1.Beer;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListWithUnmodifableAndModifableLists  <E extends Beer> implements List<E> {
    private List modifableList;
    private List unmodifableList;

    public ListWithUnmodifableAndModifableLists(List modifableList, List unmodifableList) {
        this.modifableList = modifableList;
        this.unmodifableList = unmodifableList;
    }

    @Override
    public int size() {
        return unmodifableList.size() + modifableList.size() > Integer.MAX_VALUE ? Integer.MAX_VALUE :
                                                        unmodifableList.size() + modifableList.size();
    }

    @Override
    public boolean isEmpty() {
        return unmodifableList.size() + modifableList.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return unmodifableList.contains(o) | modifableList.contains(o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return modifableList.add(e);
    }

    @Override
    public boolean remove(Object o) {
        if (unmodifableList.contains(o)){
            throw new IllegalStateException();
        }
        return modifableList.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return modifableList.containsAll(c) | unmodifableList.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        return modifableList.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends E> c) {
        if (index <= unmodifableList.size()){
            throw new IllegalArgumentException();
        }
        return modifableList.addAll(index - unmodifableList.size(), c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return modifableList.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return modifableList.retainAll(c);
    }

    @Override
    public void clear() {
        modifableList.clear();
    }

    @Override
    public E get(int index) {
        return index <= unmodifableList.size() ? (E)unmodifableList.get(index) : (E)modifableList.get(index);
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
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

    @NotNull
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @NotNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
