package org.example;

import java.util.Comparator;

public interface MyArray <E> extends Iterable<E>{
    boolean add(E e);
    void delete(int i);
    E get(int i);
    int size();
    void sort(Comparator<E> comparator);
}
