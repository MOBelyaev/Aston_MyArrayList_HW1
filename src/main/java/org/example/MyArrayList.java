package org.example;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements MyArray<E>  {

    public static class MyArrayListIterator<IE> implements Iterator<IE>{

        private final IE[] data;
        private final int size;

        private int count = 0;

        public MyArrayListIterator(IE[] data, int size) {
            this.data = data;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public IE next() {

            if(count < 0){
                throw new NoSuchElementException();
            }
            if(count > size - 1){
                throw new NoSuchElementException();
            }

            IE temp = data[count];
            count++;
            return temp;
        }
    }

    public static final int THRESHOLD = 16;

    protected int current = 0;
    protected int capacity = THRESHOLD;
    protected E[] array;

    public MyArrayList() {
        this.array = (E[]) new Object[capacity];
    }

    public boolean add(E e) {
        try {
            if(current >= capacity - 1){
                allocate();
            }
            array[current] = e;
            current++;
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void allocate(){

        int newSize = capacity + THRESHOLD;

        E[] temp = array;
        array = (E[]) new Object[newSize];
        System.arraycopy(temp, 0, array, 0, temp.length);
        capacity = newSize;
    }

    public void delete(int i) {

        if(i >= current){
            throw new IllegalArgumentException("Элемента по индексу " + i + " не существует");
        }
        if(i < 0){
            throw new NegativeIndexException("Отрицательный индекс");
        }

        System.arraycopy(this.array,i + 1,this.array, i, current - i);
        current--;


    }

    public E get(int i) {
        if(i >= current){
            return null;
        }
        if(i < 0){
            throw new NegativeIndexException();
        }
        return array[i];
    }

    public int size() {
        return current;
    }


    public Iterator<E> iterator() {

        return new MyArrayListIterator<>(array,current);
    }

    public void sort(Comparator<E> comparator){
        quickSort(comparator,0, current -1);
    }
    private void quickSort(Comparator<E> comparator, int l, int r){

        if(l >= r) return;

        int pi = partition(comparator, l, r);

        quickSort(comparator, l, pi-1);
        quickSort(comparator, pi + 1, r);

    }

    private int partition(Comparator<E> comparator, int l, int r){

        E pivot = array[r];
        int wall = l - 1;

        for (int i = l; i < r; i++) {
            int compare = comparator.compare(array[i], pivot);
            if(compare < 0){
                wall++;
                swap(wall, i);
            }

        }
        swap(wall +1,r);
        return wall +1;
    }

    private void swap(int ptr, int i){
        E temp = array[ptr];
        array[ptr] = array[i];
        array[i] = temp;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < current; i++) {
            sb.append(array[i]);
            if(i != current-1){
                sb.append(",");
            }

        }
        return "[" +
                 sb + "]";
    }

}
