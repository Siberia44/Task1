package part2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContainerProductsTest {
    private ContainerProducts containerProducts;
    private final String firstPosition = "First position";
    private final String secondPosition = "Second position";
    private final String thirdPosition = "Third position";
    private final int firstIndexPosition = 1;

    @Before
    public void init(){
        containerProducts = new ContainerProducts();
        containerProducts.add(firstPosition);
        containerProducts.add(secondPosition);
        containerProducts.add(thirdPosition);
    }


    @Test
    public void isAddInputElementIntoCollection(){
        String expectedValue = secondPosition;
        assertEquals(expectedValue, containerProducts.toArray()[firstIndexPosition]);
    }

    @Test
    public void isGetGiveElementFromIndex(){
        String expectedValue = secondPosition;
        Object actual = containerProducts.get(firstIndexPosition);
        assertEquals(expectedValue, actual);
    }

    @Test
    public void isRemoveToIndexDeleteElement(){
        ArrayList test = new ArrayList();
        test.add(firstPosition);
        test.add(thirdPosition);
        containerProducts.remove(firstIndexPosition);
        assertEquals(Arrays.toString(containerProducts.toArray()), test.toString());

    }

    @Test
    public void isRemoveToObjectDeleteElement(){
        ArrayList test = new ArrayList();
        test.add(firstPosition);
        test.add(thirdPosition);
        containerProducts.remove(secondPosition);
        assertEquals(Arrays.toString(containerProducts.toArray()), test.toString());
    }

    @Test
    public void isAddByIndexInputElementIntoCollection(){
        containerProducts.add(firstIndexPosition, thirdPosition);
        assertEquals(containerProducts.toArray()[firstIndexPosition], thirdPosition);
    }

    @Test
    public void isIteratorWork(){
        Iterator iterator = containerProducts.iterator();
        iterator.hasNext();
        iterator.next();
        Object o = iterator.next();
        assertEquals(o, containerProducts.toArray()[firstIndexPosition]);
    }

}
