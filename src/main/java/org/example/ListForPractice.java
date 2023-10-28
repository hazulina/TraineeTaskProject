package org.example;

import java.util.Comparator;

/**
 * Collection that based on ordered sequence.
 * The user can access elements by their integer index (position in the list), search for elements in the list,
 * update and delete elements.
 * Type parameters:
 *
 * @param <T> the type of elements in this list.
 */

public interface ListForPractice<T> {

    /**
     * Add the element to list
     *
     * @param element that should be added
     */
    void addElement(T element);

    /**
     * Add the element exactly to index position in the list.
     *
     * @param element element to add.
     * @param index   position in the list for an element.
     */
    void addElementToIndex(T element, int index);

    /**
     * Get the element from the list by the index.
     *
     * @param index position in the list for an element.
     * @return element from the index position.
     */
    T getElementByIndex(int index);

    /**
     * Get the element's index by it's value.
     *
     * @param element which index is needed
     * @return int value of element index
     */
    int getIndex(T element);

    /**
     * Update element value at index position in the list.
     *
     * @param element new value to add.
     * @param index   position in the list that will be updated.
     */
    void updateElementByIndex(T element, int index);

    /**
     * Remove element from the list.
     *
     * @param element element that will be removed.
     */

    void deleteElement(T element);

    /**
     * Remove all elements in the list.
     */
    void truncate();

    /**
     * Sort elements in asc order in the list.
     *
     * @param comparator rules for elements comparison.
     */

    void sort(Comparator<? super T> comparator);

    /**
     * Get current size of list.
     *
     * @return int value of list size.
     */
    int getSize();
}
