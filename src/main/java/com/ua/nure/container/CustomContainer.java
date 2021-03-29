package com.ua.nure.container;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Predicate;

public class CustomContainer<T> implements List<T> {

    private final Logger LOG = LogManager.getLogger(CustomContainer.class.getName());

    private static final int DEFAULT_CAPACITY = 15;

    private Object[] array;
    private int size = 0;

    public CustomContainer() {
        LOG.debug("Creating container with default capacity");
        array = new Object[DEFAULT_CAPACITY];
    }

    public CustomContainer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        LOG.debug("Creating container with capacity {}", capacity);
        array = new Object[capacity];
    }

    public CustomContainer(T[] otherArr) {
        array = Arrays.copyOf(otherArr, otherArr.length + DEFAULT_CAPACITY);
        size = otherArr.length;
        LOG.debug("Creating container by copying arr {}", otherArr);
    }

    public CustomContainer(Collection<T> collection) {
        Object[] otherArr = collection.toArray();
        array = Arrays.copyOf(otherArr, otherArr.length + DEFAULT_CAPACITY);
        size = otherArr.length;
        LOG.debug("Creating container by copying collection {}", collection);
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
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    public Iterator<T> iterator(Predicate<T> condition) {
        return new IteratorImpl(condition);
    }

    private class IteratorImpl implements Iterator<T> {

        private int current = 0;
        private int lastTook = -1;
        private final Predicate<T> condition;

        public IteratorImpl() {
            condition = v -> true;
        }

        public IteratorImpl(Predicate<T> condition) {
            this.condition = condition;
        }

        @Override
        public boolean hasNext() {
            if (current >= size) {
                return false;
            }
            if (condition.test((T) array[current])) {
                return true;
            }
            current++;
            return hasNext();
        }

        @Override
        public T next() {
            if (current >= size) {
                throw new NoSuchElementException();
            }
            lastTook = current;
            current++;
            return (T) array[lastTook];
        }

        @Override
        public void remove() {
            if (lastTook < 0) {
                throw new NoSuchElementException();
            }

            CustomContainer.this.remove(array[lastTook]);
            current = lastTook;
            lastTook = -1;
        }
    }

    @Override
    public T[] toArray() {
        return (T[]) Arrays.copyOf(array, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) return (T1[]) toArray();
        else {
            for (int i = 0; i < size; i++) {
                a[i] = (T1) array[i];
            }
            return a;
        }
    }

    @Override
    public boolean add(T t) {
        expandIfNotEnough(1);
        array[size] = t;
        size++;
        LOG.debug("Add the element to the end of the container -> {}", t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return indexOf(o) != -1 && remove(indexOf(o)) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        expandIfNotEnough(c.size());

        System.arraycopy(c.toArray(), 0, array, size, c.size());
        size = size + c.size();

        LOG.debug("Add collection {}", c);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        isInRange(index);
        expandIfNotEnough(c.size());

        System.arraycopy(array, index, array, index + c.size(), size - index + 1);
        System.arraycopy(c.toArray(), 0, array, index, c.size());
        size = size + c.size();

        LOG.debug("Add collection starting from index {} -> {}", index, c);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldSize = size;
        Arrays.stream(toArray()).filter(c::contains).forEach(this::remove);
        return size != oldSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldSize = size;
        Arrays.stream(toArray()).filter(v -> !c.contains(v)).forEach(this::remove);
        return size != oldSize;
    }

    @Override
    public void sort(Comparator<? super T> c) {
        T[] sortedArr = toArray();
        Arrays.sort(sortedArr, c);
        array = Arrays.copyOf(sortedArr, array.length);
    }

    @Override
    public void clear() {
        size = 0;
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public T get(int index) {
        isInRange(index);
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        isInRange(index);
        T res = get(index);
        array[index] = element;
        LOG.debug("Set value with index {}. {} -> {}", index, res, element);
        return res;
    }

    @Override
    public void add(int index, T element) {
        isInRange(index);
        expandIfNotEnough(1);
        System.arraycopy(array, index - 1, array, index, size - index + 1);
        array[index] = element;
        size++;
        LOG.debug("Add element to container with index {} -> {}", index, element);
    }

    @Override
    public T remove(int index) {
        isInRange(index);
        T res = (T) array[index];

        System.arraycopy(array, index + 1, array, index, size - index);
        array[--size] = null;
        LOG.debug("Remove from container element with index {} -> {}.", index, res);
        return res;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = 0; i < size; i++) {
            int index = size - i;
            if (Objects.equals(o, array[index])) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIteratorImpl();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIteratorImpl(index);
    }


    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private class ListIteratorImpl implements ListIterator<T> {

        private int current;
        private int lastTook = -1;

        public ListIteratorImpl() {
            current = 0;
        }

        public ListIteratorImpl(int index) {
            current = index;
        }

        @Override
        public boolean hasNext() {
            return size > current;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastTook = current;
            current++;
            return (T) array[lastTook];
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            current--;
            lastTook = current;

            return (T) array[lastTook];
        }

        @Override
        public int nextIndex() {
            return current;
        }

        @Override
        public int previousIndex() {
            return current - 1;
        }

        @Override
        public void remove() {
            if (lastTook < 0) {
                throw new NoSuchElementException();
            }

            CustomContainer.this.remove(array[lastTook]);
            current = lastTook;
            lastTook = -1;
        }

        @Override
        public void set(T t) {
            if (lastTook == -1) {
                throw new IllegalArgumentException();
            }
            CustomContainer.this.set(lastTook, t);
        }

        @Override
        public void add(T t) {
            CustomContainer.this.add(current, t);
            current++;
            lastTook = -1;
        }
    }

    @Override
    public int hashCode() {
        int res = 31;
        for (Object value : toArray()) {
            res = res * 11 + ((value == null) ? 0 : value.hashCode());
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return Arrays.equals(toArray(), ((CustomContainer<T>) obj).toArray());
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }

    private void isInRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }


    private void expandIfNotEnough(int minValue) {
        if (!isEnoughCapacity(minValue)) {
            long increasedSize = (long) array.length + array.length / 3;
            if (increasedSize < minValue) increasedSize+= minValue;
            if (increasedSize > Integer.MAX_VALUE) {
                LOG.warn("Container will be overheated");
                throw new IndexOutOfBoundsException();
            }
            array = Arrays.copyOf(array, (int) increasedSize);
            LOG.debug("Expand the container by 1.5");
        }
        LOG.debug("Current capacity is enough");
    }

    private boolean isEnoughCapacity(int value) {
        return size + value < array.length;
    }

}
