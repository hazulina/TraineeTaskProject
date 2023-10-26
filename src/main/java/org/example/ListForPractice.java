package org.example;

public interface ListForPractice<T> {

    void addElement(T element);

    void addElementToIndex(T element, int index);

    T getElementByIndex(int index);

    void updateElementByIndex(T element, int index);

    void deleteElement(T element);

    void truncate(ListForPractice<T> listForPractice);

    void sort(ListForPractice<T> listForPractice);
}
