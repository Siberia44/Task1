package part2;

import org.junit.Before;
import org.junit.Test;
import part1.Beer;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class ContainerProductsTest {
    ContainerProducts<Beer> list;
    Iterator iterator;
    Iterator customIterator;
    Predicate predicate;
    Beer beer1;
    Beer beer2;
    Beer beer3;
    Beer beer4;

    @Before
    public void init() {
        list = new ContainerProducts<>();
        iterator = list.iterator();
        predicate = Objects::nonNull;
        customIterator = new ContainerProducts<Beer>().new CustomIterator(predicate, list);
        beer1 = new Beer();
        beer2 = new Beer();
        beer3 = new Beer();
        beer4 = new Beer();
        beer1.setName("beer1");
        beer2.setName("beer2");
        beer3.setName("beer3");
        beer4.setName("beer4");

    }

    @Test
    public void isEmptyShouldReturnFalseIfSizeMoreThanZero() {
        list.add(beer1);
        assertFalse(list.isEmpty());
    }


    @Test
    public void isEmptyShouldReturnTrueIfNoElementsInCollection() {
        assertTrue(list.isEmpty());
    }


    @Test
    public void sizeShouldReturnZeroWhenCallThisMethod() {
        int actually = list.size();
        int expected = 0;
        assertEquals(actually, expected);
    }

    @Test
    public void sizeShouldReturnTwoAfterAddingTwoElementsToTheCollection() {
        list.add(beer1);
        list.add(beer2);
        int actually = list.size();
        int expected = 2;
        assertEquals(actually, expected);
    }

    @Test
    public void containsShouldReturnTrueIfThisListContainsElement() {
        Beer testBeer = beer1;
        list.add(beer1);
        assertTrue(list.contains(testBeer));
    }

    @Test
    public void containsShouldReturnFalseIfThisElementNotOnTheCollection() {
        list.add(beer1);
        assertFalse(list.contains(beer2));
    }

    @Test
    public void containsShouldReturnTrueWhenCheckNull() {
        list.add(null);
        assertTrue(list.contains(null));
    }

    @Test
    public void toArrayShouldReturnAnArrayOfThisList() {
        Beer[] arrayOfBeers = {beer1, beer2};
        list.add(beer1);
        list.add(beer2);
        assertArrayEquals(arrayOfBeers, list.toArray());
    }

    @Test(expected = NullPointerException.class)
    public void toArrayWithParamsShouldThrowNullPointerException() {
        list.toArray(null);
    }


    @Test
    public void addShouldIncrementSizeWhenInsertElement() {
        int sizeBeforeAddElement = list.size();
        list.add(beer1);
        int expectedSizeAfterAddElement = sizeBeforeAddElement + 1;
        int actuallySizeAfterAddElement = list.size();
        assertEquals(expectedSizeAfterAddElement, actuallySizeAfterAddElement);
    }

    @Test
    public void addShouldAppendElementIntoEndOfTheList() {
        list.add(beer1);
        assertEquals(list.toArray()[list.size() - 1], beer1);
    }

    @Test
    public void addShouldInputNull() {
        list.add(null);
        assertNull(list.get(0));
    }

    @Test
    public void addShouldReturnTrueIfElementAdded() {
        assertTrue(list.add(beer1));
    }


    @Test
    public void removeByObjectShouldDecrementSizeWhenDeleteElement() {
        int sizeBeforeAddingElement = list.size();
        list.add(beer1);
        list.remove(beer1);
        assertEquals(list.size(), sizeBeforeAddingElement);
    }

    @Test
    public void removeByObjectShouldDeleteElementFromList() {
        list.add(beer1);
        list.remove(beer1);
        assertTrue(list.size() < 1);
    }

    @Test
    public void removeShouldReturnFalseIfElementIsNotExist() {
        assertFalse(list.remove(beer1));
    }

    @Test
    public void removeByObjectShouldDeleteNull() {
        list.add(beer1);
        list.add(null);
        list.add(beer3);
        list.remove(null);
        assertEquals(list.get(1), beer3);
    }


    @Test
    public void addAllShouldIncreaseSizeOfCustomCollectionByTheNumberOfItemsInTheCollectionInParam() {
        ArrayList test = new ArrayList();
        test.add(beer1);
        test.add(beer2);
        list.add(beer1);
        int addedCollectionSize = test.size();
        int expectedCollectionSize = list.size() + addedCollectionSize;
        list.addAll(test);
        assertEquals(list.size(), expectedCollectionSize);
    }

    @Test
    public void addAllShouldInputAllElementsFromCollectionInParamInTheEndOfList() {
        list.add(beer1);
        ArrayList test = new ArrayList();
        test.add(beer1);
        test.add(beer2);
        list.addAll(test);
        int count = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.toArray()[i].equals(test.toArray()[i - 1])) {
                count++;
            }
        }
        assertEquals(count, 2);
    }

    @Test
    public void removeAllShouldDeleteAllElementsThatAreContainedInTheCollection() {
        list.add(beer1);
        list.add(beer2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(beer1);
        list.removeAll(arrayList);
        assertEquals(list.size(), 1);
        assertEquals(list.get(0), beer2);

    }

    @Test
    public void removeAllShouldReturnTrueIfThisListChanged() {
        list.add(beer1);
        list.add(beer2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(beer1);
        assertTrue(list.removeAll(arrayList));
    }

    @Test
    public void removeAllShouldDecrementSize() {
        list.add(beer1);
        list.add(beer2);
        int sizeAfterAddedElement = list.size();
        ArrayList arrayList = new ArrayList();
        arrayList.add(beer1);
        int expectedSize = sizeAfterAddedElement - 1;
        list.removeAll(arrayList);
        assertEquals(expectedSize, list.size());
    }


    @Test
    public void retainAllShouldSaveAllElementsThatAreContainedInTheCollection() {
        list.add(beer1);
        list.add(beer2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(beer1);
        list.retainAll(arrayList);
        assertEquals(list.get(0), arrayList.get(0));
    }

    @Test
    public void retainAllShouldDecrementSize() {
        list.add(beer1);
        list.add(beer2);
        int sizeAfterAddElements = list.size();
        ArrayList arrayList = new ArrayList();
        arrayList.add(beer1);
        list.retainAll(arrayList);
        int sizeAfterRetainElements = list.size();
        assertEquals(sizeAfterRetainElements, sizeAfterAddElements - 1);
    }

    @Test
    public void retainAllShouldReturnTrueIfCollectionIsChanged() {
        list.add(beer1);
        list.add(beer2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(beer1);
        assertTrue(list.retainAll(arrayList));
    }

    @Test
    public void clearShouldMakeSizeZero() {
        list.add(beer1);
        list.add(beer2);
        list.clear();
        assertEquals(list.size(), 0);
    }


    @Test
    public void containAllShouldReturnTrueIfThisCollectionContainAllOfTheElementsInCollectionInParam() {
        list.add(beer1);
        list.add(beer2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(beer2);
        assertTrue(list.containsAll(arrayList));
    }

    @Test
    public void addAllWithParamsShouldInsertAllElementsInTheCollectionIntoList() {
        list.add(beer1);
        list.add(beer2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(beer3);
        arrayList.add(beer4);
        list.addAll(1, arrayList);
        assertEquals(list.get(1), beer3);
        assertEquals(list.get(2), beer4);
        assertEquals(list.get(3), beer2);
    }

    @Test
    public void addAllWithParamsShouldIncreaseSize() {
        list.add(beer1);
        list.add(beer2);
        int beforeAdd = list.size();
        ArrayList arrayList = new ArrayList();
        arrayList.add(beer3);
        arrayList.add(beer4);
        list.addAll(1, arrayList);
        int afterAdd = list.size();
        assertEquals(beforeAdd + 2, afterAdd);

    }

    @Test
    public void setShouldInputValueIntoIndexPlace() {
        list.add(beer1);
        list.add(beer2);
        list.add(beer3);
        list.set(1, beer4);
        assertEquals(list.get(1), beer4);
    }

    @Test
    public void setShouldReturnPreviousElement() {
        list.add(beer1);
        list.add(beer2);
        list.add(beer3);
        assertEquals(list.set(1, beer4), beer2);
    }

    @Test
    public void addWithParamsShouldIncreaseElementToThePosition() {
        list.add(beer1);
        list.add(beer2);
        list.add(beer3);
        list.add(1, beer4);
        assertEquals(list.get(1), beer4);
    }

    @Test
    public void addWithParamsShouldShiftElements() {
        list.add(beer1);
        list.add(beer2);
        list.add(beer3);
        list.add(1, beer4);
        assertEquals(list.get(2), beer2);
        assertEquals(list.get(3), beer3);
    }

    @Test
    public void addWithParamsShouldIncrementSize() {
        list.add(beer1);
        list.add(beer2);
        list.add(beer3);
        int sizeBeforeAdd = list.size();
        list.add(1, beer4);
        int sizeAfterAdd = list.size();
        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void addWithParamsShouldThrowExceptionIfIndexMorTHanSize() {
        list.add(beer1);
        list.add(beer2);
        list.add(beer3);
        list.add(9, beer4);
    }

    @Test
    public void indexOfShouldReturnIndexOfFirstElement() {
        list.add(beer1);
        list.add(beer2);
        list.add(beer3);
        assertEquals(list.indexOf(beer1), 0);
    }

    @Test
    public void indexOfShouldReturnIndexOfTheFirstMatchingItem() {
        list.add(beer1);
        list.add(beer1);
        assertEquals(list.indexOf(beer1), 0);
    }

    @Test
    public void lastIndexOfShouldReturnIndexOfTheLastMatchingItem() {
        list.add(beer1);
        list.add(beer1);
        assertEquals(list.lastIndexOf(beer1), 1);
    }

    @Test
    public void lastIndexShouldReturnIndexOfTheLastMatchingNull() {
        list.add(null);
        list.add(null);
        assertEquals(list.lastIndexOf(null), 1);
    }

    @Test
    public void iteratorHasNextShouldReturnFalseOnEmptyCollection(){
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextShouldThrowException(){
        iterator.next();
    }

    @Test
    public void iteratorHasNextShouldReturnTrueOnCollectionWithOneItemSeveralTimes(){
        list.add(beer1);
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
    }

    @Test
    public void iteratorShouldWorkWithCollectionWithOneElementCorrect(){
        list.add(beer1);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), beer1);
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorShouldWorkWithCollectionWithMoreThanOneElementCorrect(){
        list.add(beer1);
        list.add(beer2);
        list.add(beer3);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), beer1);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), beer2);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), beer3);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void customIteratorHasNextShouldReturnFalseWithEmptyCollection(){
        assertFalse(customIterator.hasNext());
    }

    @Test
    public void customIteratorHasNextShouldReturnFalseWithOneNullElementInCollection(){
        list.add(null);
        assertFalse(customIterator.hasNext());
    }

    @Test
    public void customIteratorHasNextShouldReturnTrueWithTwoElementInCollectionAndNextReturnLastElement(){
        list.add(null);
        list.add(beer1);
        assertTrue(customIterator.hasNext());
        assertEquals(customIterator.next(), beer1);
    }

    @Test
    public void customIteratorShouldWorkWithCollectionWithMoreThanOneElementCorrect(){
        list.add(beer1);
        list.add(null);
        list.add(beer3);
        list.add(null);
        assertTrue(customIterator.hasNext());
        assertEquals(customIterator.next(), beer1);
        assertTrue(customIterator.hasNext());
        assertEquals(customIterator.next(), beer3);
        assertFalse(customIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void customIteratorNextShouldThrowNoSuchElementException(){
        customIterator.next();
    }




}
