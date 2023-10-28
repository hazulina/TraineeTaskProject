package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Implementation of {@link ListForPractice} interface.
 *
 * @param <T> type of objects in the list.
 */
public class ArrayListForPractice<T> implements ListForPractice<T> {
    /**
     * Amount of elements that list contains
     */
    private int size;
    /**
     * Default initial capacity.
     */
    private final int CAPACITY = 10;
    /**
     * Inner array to store list data.
     */
    private Object[] array;

    /**
     * Constructs an empty list with the default initial capacity.
     */
    public ArrayListForPractice() {
        array = new Object[CAPACITY];
        size = 0;
    }

    /**
     * {@inheritDoc}
     *
     * @param element that should be added
     */
    @Override
    public void addElement(T element) {
        array = isArrayReadyToGrow(array);
        array[size] = element;
        size += 1;
    }

    /**
     * Return current array or array with increased capacity depending on the array's fullness.
     *
     * @param array the inner array of the list.
     * @return the same array, or it's bigger version.
     */
    private Object[] isArrayReadyToGrow(Object[] array) {
        if (size >= CAPACITY * 0.8) {
            array = increaseArrayCapacity(size);
        }
        return array;
    }

    /**
     * Increase the array size and copy it's content
     *
     * @param size current size of array
     * @return a new array with same values.
     * @throws OutOfMemoryError if the new length would exceed Integer.MAX_VALUE
     */
    private Object[] increaseArrayCapacity(int size) {
        int grownSize = size * 2 + 1;
        if (grownSize < Integer.MAX_VALUE - 8) {
            Object[] grownArray = new Object[grownSize];
            System.arraycopy(array, 0, grownArray, 0, size);
            return grownArray;
        } else {
            throw new OutOfMemoryError("Required array length is too large!");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param element element to add.
     * @param index   position in the list for an element.
     */
    @Override
    public void addElementToIndex(T element, int index) {
        Objects.checkIndex(index, size);
        array = isArrayReadyToGrow(array);
        moveElementsRight(index);
        array[index] = element;
        size += 1;
    }

    /**
     * Move all elements in the list right and prepare space for adding element.
     *
     * @param index which index need to free.
     */
    private void moveElementsRight(int index) {
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param index position in the list for an element.
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getElementByIndex(int index) {
        Objects.checkIndex(index, size);
        return (T) array[index];
    }

    /**
     * {@inheritDoc}
     *
     * @param element new value to add.
     * @param index   position in the list that will be updated.
     */
    @Override
    public void updateElementByIndex(T element, int index) {
        Objects.checkIndex(index, size);
        array[index] = element;
    }

    /**
     * {@inheritDoc}
     *
     * @param element element that will be removed.
     */
    @Override
    public void deleteElement(T element) {
        if (isPresent(element)) {
            int neededIndex = getIndexOfElement(element);
            array[neededIndex] = null;
            moveElementsLeft(neededIndex);
            size -= 1;
        }
    }

    /**
     * Check if element is in list.
     *
     * @param element element to check.
     * @return true if element is present or false.
     */
    private boolean isPresent(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Move all elements in the list left after deleting any of it.
     *
     * @param neededIndex position that is empty after deleting.
     */
    private void moveElementsLeft(int neededIndex) {
        for (int start = neededIndex + 1; start < size; start++) {
            array[start - 1] = array[start];
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param element which index is needed
     * @return int value of element index
     */
    @Override
    public int getIndex(T element) {
        return getIndexOfElement(element);
    }

    /**
     * Private implementation of getIndex() method.
     *
     * @param element which index is needed
     * @return int value of element index
     */
    private int getIndexOfElement(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Remove all elements in the list.
     */
    @Override
    public void truncate() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * {@inheritDoc}
     *
     * @param comparator rules for elements comparison.
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        array = trimToSize();
        quickSort(0, size - 1, comparator);
    }

    /**
     * Quick sort algorithm for method sort()
     *
     * @param left       pointer
     * @param right      pointer
     * @param comparator rules for elements comparison.
     */
    private void quickSort(int left, int right, Comparator<? super T> comparator) {
        if (left < right) {
            int pivotIndex = partition(left, right, comparator);
            quickSort(left, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, right, comparator);
        }
    }

    /**
     * Partition method — part of quick sort.
     *
     * @param left       pointer
     * @param right      pointer
     * @param comparator rules for elements comparison.
     * @return new pivot index
     */
    @SuppressWarnings("unchecked")
    private int partition(int left, int right, Comparator<? super T> comparator) {
        T pivot = (T) array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare((T) array[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, right);
        return i + 1;
    }

    /**
     * Swaps two element for quick sort algorithm
     *
     * @param firstElementIndex  index of first element.
     * @param secondElementIndex index of second element.
     */
    private void swap(int firstElementIndex, int secondElementIndex) {
        Object temp = array[firstElementIndex];
        array[firstElementIndex] = array[secondElementIndex];
        array[secondElementIndex] = temp;
    }

    /**
     * Trim array length to current array size.
     *
     * @return array with correct length.
     */
    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
    private T[] trimToSize() {
        T[] fitSizeArray = (T[]) new Object[size];
        System.arraycopy(array, 0, fitSizeArray, 0, size);
        return fitSizeArray;
    }

    /**
     * Get current size of list.
     *
     * @return int value of list size.
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * String representation of list for output.
     *
     * @return string with list values.
     */
    @Override
    public String toString() {
        return Arrays.toString(trimToSize());
    }

    /**
     * Override version of Object.equals() method.
     *
     * @param o object, which with will compare a current object.
     * @return boolean result of equality.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayListForPractice)) return false;
        ArrayListForPractice<?> that = (ArrayListForPractice<?>) o;
        array = trimToSize();
        that.array = trimToSize();
        return size == that.size && Arrays.equals(array, that.array);
    }

    /**
     * Override version of Object.hashCode() method.
     *
     * @return int value of the current object.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
