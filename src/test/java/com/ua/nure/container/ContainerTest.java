package com.ua.nure.container;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

public class ContainerTest {

    private List<Integer> actualList;

    private static final int DEFAULT_SIZE = 3;

    /**
     * Default entity sets
     */
    private static final Integer[] DEFAULT_ENTITIES =  {1,2,3};
    private static final Integer[] ENTITIES_WITH_NULL =  {1,2,3, null};

    /**
     * Entities for {@code add} or {@code addAll}
     * and arrays with elements after {@code add} and {@code addAll}
     */
    private static final Integer ENTITY_FOR_ADD = 1;
    private static final Integer[] ENTITIES_AFTER_ADD =   {1,2,3,1};
    private static final List<Integer> ENTITIES_FOR_ADD_ALL = List.of(1,2);
    private static final Integer[] ENTITIES_AFTER_ADD_ALL =   {1,2,3,1,2};
    private static final int INDEX_FOR_ADD = 1;
    private static final Integer[] ENTITIES_AFTER_ADD_WITH_INDEX =   {1,1,2,3};
    private static final Integer[] ENTITIES_AFTER_ADD_ALL_WITH_INDEX =   {1,1,2,2,3};
    /**
     * Entities for {@code remove},{@code removeAll} or {@code retainAll}
     * and arrays with elements after {@code add} and {@code addAll}
     */
    private static final Integer ENTITY_FOR_REMOVE = 1;
    private static final Integer[] ENTITIES_AFTER_REMOVE =   {2,3};
    private static final List<Integer> ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL = List.of(1,2);
    private static final Integer[] ENTITIES_AFTER_REMOVE_ALL =  {3};
    private static final Integer NOT_CONTAINED_ENTITY = 4;
    private static final List<Integer> NOT_CONTAINED_ENTITIES =  List.of(4,5);
    private static final int INDEX_FOR_REMOVE = 0;
    /**
     * Constants and entities for {@code sort}
     */
    private static final Comparator<? super Integer> REVERSE_SEQUENCE = Comparator.comparing(Integer::intValue).reversed();
    private static final Integer[] REVERSE_ENTITIES =  {3,2,1};
    /**
     * Constants for {@code iterator} with condition
     */
    private static final Predicate<Integer> CONDITION = v -> v > 1;
    private static final Integer[] ENTITIES_AFTER_ITERATIONS =  {2,3};
    private static final int NUMBER_OF_ITERATIONS_WITH_CONDITION = 2;
    /**
     * Constants for {@code toString}
     */
    private static final String OUT_OF_EMPTY_LIST = "[]";
    private static final String OUT_OF_FULL_LIST = "[1, 2, 3]";
    private static final String OUT_OF_FULL_LIST_WITH_NULL = "[1, 2, 3, null]";
    /**
     * Constants for {@code get}
     */
    private static final int INDEX_FOR_GET = 1;
    /**
     * Constants for {@code set}
     */
    private static final Integer ENTITY_FOR_SET = 4;
    private static final int INDEX_FOR_SET = 1;
    private static final Integer[] ENTITIES_AFTER_SET = {1,4,3};
    private static final Integer[] ENTITIES_AFTER_SET_NULL = {1,null,3};

    /**
     * Constants for {@code lastIndexOf}
     */
    private static final Integer[] ENTITIES_FOR_LAST_INDEX =  {1,2,1};
    private static final int INDEX_FOR_LAST_INDEX_OF = 2;
    private static final Integer SEARCHED_ELEMENT = 1;

    @Before
    public void createList() {
        actualList = new CustomContainer<>();
    }

    @Test
    public void whenUseToString_onEmptyContainer_ShouldOutOnlyBrackets() {
        actualList = new CustomContainer<>();

        Assert.assertEquals(OUT_OF_EMPTY_LIST,actualList.toString());
    }

    @Test
    public void whenUseToString_onNotEmptyContainer_ShouldOutAllElements() {
        fillContainer();

        Assert.assertEquals(OUT_OF_FULL_LIST,actualList.toString());
    }

    @Test
    public void whenUseToString_onContainerWithNullElement_ShouldOutNull() {
        fillContainer();
        actualList.add(null);

        Assert.assertEquals(OUT_OF_FULL_LIST_WITH_NULL, actualList.toString());
    }

    @Test
    public void whenCreateContainer_WithParametrizedByArrayConstructor_shouldContainArraysElements() {
        actualList = new CustomContainer<>(DEFAULT_ENTITIES);
        CustomContainer<Integer> expectedList = new CustomContainer<>();

        expectedList.addAll(Arrays.asList(DEFAULT_ENTITIES));

        Assert.assertEquals(actualList,expectedList);
    }

    @Test
    public void whenCreateContainer_WithParametrizedByArrayConstructor_shouldHaveArraysSize() {
        actualList = new CustomContainer<>(DEFAULT_ENTITIES);

        Assert.assertEquals(DEFAULT_ENTITIES.length, actualList.size());
    }

    @Test
    public void whenCreateContainer_WithParametrizedByArrayConstructor_shouldBeNotEqualToEmptyOne() {
        actualList = new CustomContainer<>(DEFAULT_ENTITIES);
        CustomContainer<Integer> emptyList = new CustomContainer<>();

        Assert.assertNotEquals(emptyList,actualList);
    }

    @Test
    public void whenCreateContainer_WithParametrizedByCollectionConstructor_shouldContainCollectionsElements() {
        actualList = new CustomContainer<>(Arrays.asList(DEFAULT_ENTITIES));
        CustomContainer<Integer> expectedList = new CustomContainer<>();

        expectedList.addAll(Arrays.asList(DEFAULT_ENTITIES));

        Assert.assertEquals(expectedList, actualList);
    }
    @Test
    public void whenCreateContainer_WithParametrizedByCollectionConstructor_shouldHaveCollectionsSize() {
        actualList = new CustomContainer<>(Arrays.asList(DEFAULT_ENTITIES));

        Assert.assertEquals(DEFAULT_ENTITIES.length, actualList.size());
    }

    @Test
    public void should_returnTrue_WhenContainerContainsElement() {
        fillContainer();

        Assert.assertTrue(actualList.contains(DEFAULT_ENTITIES[0]));
    }

    @Test
    public void should_returnFalse_WhenContainerDoesntContainElement() {
        fillContainer();

        Assert.assertFalse(actualList.contains(null));
    }

    @Test
    public void should_returnArray() {
        fillContainer();
        Object[] array = actualList.toArray();

        Assert.assertArrayEquals(DEFAULT_ENTITIES,array);
    }

    @Test
    public void should_returnTheSameArray_FilledByContainersElements() {
        fillContainer();
        Object[] filledArray = new Object[3];
        Object[] actualArray = actualList.toArray(filledArray);

        Assert.assertArrayEquals(filledArray,actualArray);
        Assert.assertArrayEquals(DEFAULT_ENTITIES,actualArray);
    }

    @Test
    public void should_returnNotNewArray_FilledByContainersElements() {
        fillContainer();
        Object[] filledArray = new Object[2];
        Object[] actualArray = actualList.toArray(filledArray);

        Assert.assertFalse(Arrays.equals(actualArray, filledArray));
        Assert.assertArrayEquals(DEFAULT_ENTITIES,actualArray);
    }

    @Test
    public void whenAddElement_ShouldIncreaseSizeByOne() {
        fillContainer();
        actualList.add(ENTITY_FOR_ADD);

        Assert.assertEquals(DEFAULT_SIZE + 1, actualList.size());
    }
    @Test
    public void whenAddElement_ShouldContainThisElement() {
        fillContainer();
        List<Integer> expectedList =new CustomContainer<>(ENTITIES_AFTER_ADD);

        actualList.add(ENTITY_FOR_ADD);

        Assert.assertEquals(expectedList, actualList);
    }
    @Test
    public void whenAddNull_ShouldIncreaseSizeByOne() {
        fillContainer();
        actualList.add(null);

        Assert.assertEquals(DEFAULT_SIZE + 1, actualList.size());
    }

    @Test
    public void whenAddNull_ShouldContainThisElement() {
        fillContainer();
        List<Integer> expectedList =new CustomContainer<>(ENTITIES_WITH_NULL);

        actualList.add(null);

        Assert.assertEquals(expectedList,actualList);
    }
    @Test
    public void whenAddElement_WithIndexOne_ShouldContainThisElement_InSecondPosition() {
        fillContainer();
        List<Integer> expectedList =new CustomContainer<>(ENTITIES_AFTER_ADD_WITH_INDEX);

        actualList.add(1, ENTITY_FOR_ADD);

        Assert.assertEquals(expectedList, actualList);
    }
    @Test
    public void whenAddAllElements_ShouldContainTheseElements() {
        fillContainer();
        List<Integer> expectedList =new CustomContainer<>(ENTITIES_AFTER_ADD_ALL);

        actualList.addAll(ENTITIES_FOR_ADD_ALL);

        Assert.assertEquals(expectedList, actualList);
    }
    @Test
    public void whenAddAllElements_ShouldIncreaseSizeByNumberOfElements() {
        fillContainer();

        actualList.addAll(ENTITIES_FOR_ADD_ALL);

        Assert.assertEquals(DEFAULT_SIZE + ENTITIES_FOR_ADD_ALL.size(), actualList.size());
    }


    @Test
    public void whenAddAllElements_WithIndexOne_ShouldContainTheseElements_StartingFromSecondPosition() {
        fillContainer();
        List<Integer> expectedList = new CustomContainer<>(ENTITIES_AFTER_ADD_ALL_WITH_INDEX);
        actualList.addAll(1, ENTITIES_FOR_ADD_ALL);

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void whenRemoveElement_ContainerDoesntContainIt_AndReplaceNextElementsBackByOne() {
        fillContainer();
        List<Integer> expectedList = new CustomContainer<>(ENTITIES_AFTER_REMOVE);

        actualList.remove(ENTITY_FOR_REMOVE);

        Assert.assertEquals(expectedList,actualList);
    }

    @Test
    public void whenRemoveContainedElement_ShouldReturnTrue() {
        fillContainer();
        boolean actual = actualList.remove(ENTITY_FOR_REMOVE);

        Assert.assertTrue(actual);
    }

    @Test
    public void whenRemoveElement_ShouldDecreaseSizeByOne() {
        fillContainer();

        actualList.remove(ENTITY_FOR_REMOVE);

        Assert.assertEquals(DEFAULT_SIZE - 1,actualList.size());
    }
    @Test
    public void whenRemoveElement_WithIndex_ShouldNotContainIt_AndReplaceNextElementsBackByOne() {
        fillContainer();
        List<Integer> expectedList = new CustomContainer<>(ENTITIES_AFTER_REMOVE);

        actualList.remove(INDEX_FOR_REMOVE);

        Assert.assertEquals(expectedList,actualList);
    }
    @Test
    public void whenRemoveElement_WithIndex_ShouldReturnRemovedElement() {
        fillContainer();
        Integer actual = actualList.remove(INDEX_FOR_REMOVE);

        Assert.assertEquals(ENTITY_FOR_REMOVE,actual);
    }

    @Test
    public void whenRemove_NotContainingElement_ShouldReturnFalse() {
        fillContainer();
        boolean actual = actualList.remove(NOT_CONTAINED_ENTITY);

        Assert.assertFalse(actual);
    }
    @Test
    public void whenRemove_NotContainingElement_SizeShouldNotChange() {
        fillContainer();
        actualList.remove(NOT_CONTAINED_ENTITY);

        Assert.assertEquals(DEFAULT_SIZE, actualList.size());
    }
    @Test
    public void whenRemove_NotContainingElement_ContainerShouldNotChange() {
        fillContainer();
        List<Integer> expectedList = actualList;

        actualList.remove(NOT_CONTAINED_ENTITY);

        Assert.assertEquals(expectedList,actualList);
    }

    @Test
    public void whenRemoveAllWithCollection_ShouldDecreaseSizeByCollectionSize() {
        fillContainer();
        actualList.removeAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL);

        Assert.assertEquals(DEFAULT_SIZE - ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL.size(), actualList.size());
    }
    @Test
    public void whenRemoveAllWithCollection_ShouldNotContainAllCollectionElements() {
        fillContainer();
        actualList.removeAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL);

        Assert.assertFalse(actualList.containsAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL));
    }
    @Test
    public void whenRemoveAllWithCollection_ShouldNotEqualContainerBeforeIt() {
        fillContainer();
        List<Integer> expectedList = new CustomContainer<>(actualList);

        actualList.removeAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL);

        Assert.assertNotEquals(expectedList,actualList);
    }
    @Test
    public void whenRemoveAllWithCollection_ShouldReplaceOtherElements() {
        fillContainer();
        List<Integer> expectedList = new CustomContainer<>(ENTITIES_AFTER_REMOVE);

        actualList.removeAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL);

        Assert.assertNotEquals(expectedList,actualList);
    }


    @Test
    public void whenRetainAllWithCollection_SizeShouldEqualRetainingSize() {
        fillContainer();
        actualList.retainAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL);

        Assert.assertEquals(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL.size(), actualList.size());
    }
    @Test
    public void whenRetainAllWithCollection_ShouldContainAllInputElements() {
        fillContainer();
        actualList.retainAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL);

        Assert.assertTrue(actualList.containsAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL));
    }
    @Test
    public void whenRetainAllWithCollection_ShouldNotEqualContainerBeforeIt() {
        fillContainer();
        List<Integer> expectedList = new CustomContainer<>(actualList);

        actualList.retainAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL);

        Assert.assertNotEquals(expectedList,actualList);
    }
    @Test
    public void whenRetainAllWithCollection_ShouldReplaceOtherElements() {
        fillContainer();
        List<Integer> expectedList = new CustomContainer<>(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL);

        actualList.retainAll(ENTITIES_FOR_REMOVE_ALL_AND_RETAIN_ALL);

        Assert.assertEquals(expectedList,actualList);
    }

    @Test
    public void should_SortContainerByDesc() {
        fillContainer();
        actualList.sort(REVERSE_SEQUENCE);

        Assert.assertEquals(new CustomContainer<>(REVERSE_ENTITIES), actualList);
    }
    @Test
    public void whenClear_ShouldBeEmpty() {
        fillContainer();
        actualList.clear();

        Assert.assertTrue(actualList.isEmpty());
    }

    @Test
    public void should_IterateEveryContainersElement() {
        fillContainer();
        actualList = new CustomContainer<>(DEFAULT_ENTITIES);

        int actualNumberOfIterations = 0;
        for (Integer value : actualList) {
            actualNumberOfIterations++;
        }

        Assert.assertEquals(DEFAULT_SIZE, actualNumberOfIterations);
    }

    @Test
    public void should_IterateTwoTimes_AndOnlyElementsThatHigherThanOne() {
        fillContainer();
        Iterator<Integer> iterator = ((CustomContainer<Integer>) actualList).iterator(CONDITION);
        CustomContainer<Integer> iteratedValues = new CustomContainer<>();
        int actualNumberOfIterations = 0;
        while (iterator.hasNext()) {
            iteratedValues.add(iterator.next());
            actualNumberOfIterations++;

        }
        Assert.assertEquals(new CustomContainer<>(ENTITIES_AFTER_ITERATIONS), iteratedValues);
        Assert.assertEquals(NUMBER_OF_ITERATIONS_WITH_CONDITION,actualNumberOfIterations);
    }
    @Test
    public void should_decreaseSizeByOne_RemoveTheElement_AndDisplaceOtherElements_AfterRemovingByIterator() {
        fillContainer();
        Iterator<Integer> iterator = actualList.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (counter == INDEX_FOR_REMOVE) {
                iterator.remove();
            }
            counter++;
        }
        Assert.assertEquals(DEFAULT_SIZE - 1, actualList.size());
        Assert.assertEquals(new CustomContainer<>(ENTITIES_AFTER_REMOVE), actualList);
    }

    @Test
    public void should_returnSecondElementOfContainer() {
        fillContainer();
        Integer actual = this.actualList.get(INDEX_FOR_GET);

        Assert.assertEquals(DEFAULT_ENTITIES[INDEX_FOR_GET], actual);
    }

    @Test
    public void should_changeElement_WithActualIndex() {
        fillContainer();
        actualList.set(INDEX_FOR_SET, ENTITY_FOR_SET);

        Assert.assertEquals(DEFAULT_SIZE, actualList.size());
        Assert.assertEquals(actualList.get(INDEX_FOR_SET), ENTITY_FOR_SET);
        Assert.assertEquals(new CustomContainer<>(ENTITIES_AFTER_SET), actualList);
    }

    @Test
    public void should_changeElement_WithActualIndex_ToNull() {
        fillContainer();
        actualList.set(INDEX_FOR_SET,null);

        Assert.assertEquals(DEFAULT_SIZE, actualList.size());
        Assert.assertNull(actualList.get(INDEX_FOR_SET));
        Assert.assertEquals(new CustomContainer<>(ENTITIES_AFTER_SET_NULL), actualList);
    }

    @Test
    public void shouldNot_ThrowAnyException() {
        fillContainer();
        actualList = new CustomContainer<>(1);

        actualList.addAll(List.of(
                DEFAULT_ENTITIES[0],
                DEFAULT_ENTITIES[1],
                DEFAULT_ENTITIES[2]
        ));
        Assert.assertEquals(DEFAULT_SIZE, actualList.size());
        Assert.assertEquals(new CustomContainer<>(DEFAULT_ENTITIES), actualList);
    }

    @Test
    public void should_ReturnLastIndexOfCertainElement() {
        fillContainer();
        actualList = new CustomContainer<>(ENTITIES_FOR_LAST_INDEX);
        int actual = actualList.lastIndexOf(SEARCHED_ELEMENT);

        Assert.assertEquals(INDEX_FOR_LAST_INDEX_OF, actual);
    }

    @Test
    public void should_ReturnStringWithNull() {
        fillContainer();
        actualList.add(null);

        Assert.assertEquals(OUT_OF_FULL_LIST_WITH_NULL, actualList.toString());
    }

    @Test
    public void should_ReturnNextIndexAndMinusOne_FromFirstElementOfListIterator() {
        fillContainer();
        ListIterator<Integer> listIterator = actualList.listIterator();

        Assert.assertEquals(0,listIterator.nextIndex());
        Assert.assertEquals(-1, listIterator.previousIndex());
    }

    @Test
    public void should_ReturnPreviousIndexAndMinusOne_FromLastElementOfListIterator() {
        fillContainer();
        ListIterator<Integer> listIterator = actualList.listIterator(DEFAULT_SIZE - 1);

        Assert.assertEquals(DEFAULT_SIZE - 1,listIterator.nextIndex());
        Assert.assertEquals(DEFAULT_SIZE - 2, listIterator.previousIndex());
    }

    @Test
    public void should_IterateAllElementsOfContainer() {
        fillContainer();
        ListIterator<Integer> listIterator = actualList.listIterator();
        CustomContainer<Integer> iteratedElements = new CustomContainer<>();
        while (listIterator.hasNext()) {
            iteratedElements.add(listIterator.next());
        }

        Assert.assertEquals(actualList, iteratedElements);
    }

    @Test
    public void should_IterateAllElementsOfContainer_InReverseOrder() {
        fillContainer();
        ListIterator<Integer> listIterator = actualList.listIterator(actualList.size());
        CustomContainer<Integer> iteratedElements = new CustomContainer<>();
        while (listIterator.hasPrevious()) {
            iteratedElements.add(listIterator.previous());
        }

        Assert.assertEquals(new CustomContainer<>(REVERSE_ENTITIES), iteratedElements);
    }

    @Test
    public void should_addNewElement_afterAddingFromListIterator() {
        fillContainer();
        ListIterator<Integer> listIterator = actualList.listIterator(INDEX_FOR_ADD);
        if (listIterator.hasNext()) {
            listIterator.add(ENTITY_FOR_ADD);
        }

        Assert.assertEquals(new CustomContainer<>(ENTITIES_AFTER_ADD_WITH_INDEX), actualList);
    }

    @Test
    public void should_removeElement_ByListIterator() {
        fillContainer();
        ListIterator<Integer> listIterator = actualList.listIterator(INDEX_FOR_REMOVE);
        if (listIterator.hasNext()) {
            listIterator.next();
            listIterator.remove();
        }

        Assert.assertEquals(new CustomContainer<>(ENTITIES_AFTER_REMOVE), actualList);
    }

    @Test
    public void should_setElement_ByListIterator() {
        fillContainer();
        ListIterator<Integer> listIterator = actualList.listIterator(INDEX_FOR_SET);
        if (listIterator.hasNext()) {
            listIterator.next();
            listIterator.set(ENTITY_FOR_SET);
        }

        Assert.assertEquals(new CustomContainer<>(ENTITIES_AFTER_SET), actualList);
    }

    private void fillContainer() {
        actualList.addAll(Arrays.asList(DEFAULT_ENTITIES));
    }
}
