package part2;

import org.junit.Before;
import org.junit.Test;
import part1.Beer;

import java.util.*;

import static org.junit.Assert.*;

public class ContainerProductsTest {
    List<Beer> list;
    Beer beer1;
    Beer beer2;

    @Before
    public void init(){
        list = new ContainerProducts<>();
        beer1 = new Beer();
        beer2 = new Beer();
    }

    @Test
    public void sizeShouldReturnZeroWhenCallThisMethod(){
        int actually = 0;
        int expected = list.size();
        assertEquals(actually, expected);
    }

    //Добавить проверсу на ClassCastException
    //Добавить проверку на NullPointerException
    @Test
    public void containsShouldReturnTrueIfThisListContainsElement(){
        Beer actually = beer1;
        list.add(beer1);
        assertTrue(list.contains(actually));
    }

    @Test
    public void toArrayShouldReturnAnArrayOfThisList(){
        Beer[] b = {beer1, beer2};
        list.add(beer1);
        list.add(beer2);
        assertArrayEquals(b, list.toArray());
    }

  /*  @Test
    public void toArrayWithParamsShouldReturnAnArrayOfThisList(){
        [] b = {1, 2, 3} ;
        list.add(beer1);
        list.add(beer2);
        assertArrayEquals(b, list.toArray(b));
    } */

    //Добавить проверсу на ClassCastException
    //Добавить проверку на NullPointerException
    @Test
    public void addShouldIncrementSizeWhenInsertElement(){
        int i = list.size();
        list.add(beer1);
        assertEquals(i+1,list.size());
    }

    @Test
    public void addShouldAddAppendElementIntoEndOfTheList(){
        list.add(beer1);
        assertEquals(list.toArray()[list.size()-1], beer1);
    }


    //Добавить проверсу на ClassCastException
    //Добавить проверку на NullPointerException
    @Test
    public void removeByObjectShouldDecrementSizeWhenDeleteElement(){
        int i = list.size();
        list.add(beer1);
        list.remove(beer1);
        assertEquals(list.size(), i);
    }

    @Test
    public void removeByObjectShouldDeleteElementFromList(){
        list.add(beer1);
        list.remove(beer1);
        assertTrue(list.size() < 1);
    }

    @Test
    public void removeMustReturnFalseIfElementIsNotExist(){
        boolean flag = list.remove(beer1);
        assertFalse(flag);
    }

    //Добавить тест на ContainsAll

    @Test
    public void addAllShouldIncreaseSizeOfCustomCollectionByTheNumberOfItemsInTheCollectionInParam(){
        ArrayList test = new ArrayList();
        test.add(beer1);
        test.add(beer2);
        list.add(beer1);
        int addedCollectionSize = test.size();
        int expectedCollectionSize = list.size() + addedCollectionSize;
        list.addAll(test);
        assertEquals(list.size(), expectedCollectionSize);
    }

    //Добавить проверсу на ClassCastException
    //Добавить проверку на NullPointerException
    @Test
    public void addAllShouldInputAllElementsFromCollectionInParamInTheEndOfList(){
        list.add(beer1);
        ArrayList test = new ArrayList();
        test.add(beer1);
        test.add(beer2);
        list.addAll(test);
        int count = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.toArray()[i].equals(test.toArray()[i-1])){
                count++;
            }
        }
        assertEquals(count, 2);
    }



















}
