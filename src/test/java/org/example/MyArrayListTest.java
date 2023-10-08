package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyArrayListTest {

    @Test
    void sort_emptyList_successTest(){

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        arrayList.sort(Integer::compare);
        Assertions.assertNull(arrayList.get(0));

    }

    @Test
    void sort_oneElement_successTest(){

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        Integer data = 2;

        arrayList.add(data);
        arrayList.sort(Integer::compare);
        Assertions.assertEquals(data,arrayList.get(0));

    }

    @Test
    void sort_manyElements_successTest(){

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        arrayList.add(156);
        arrayList.add(15);
        arrayList.add(28);
        arrayList.add(-44);
        arrayList.add(85);
        arrayList.add(41);
        arrayList.add(14);
        arrayList.add(26);
        arrayList.add(26);

        Assertions.assertEquals(156,arrayList.get(0));
        Assertions.assertEquals(26,arrayList.get(8));


        arrayList.sort(Integer::compare);

        Assertions.assertEquals(-44,arrayList.get(0));
        Assertions.assertEquals(156,arrayList.get(8));

    }

    @Test
    void add_successTest(){

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        Integer data = 5;

        Assertions.assertEquals(0,arrayList.current);
        Assertions.assertEquals(MyArrayList.THRESHOLD,arrayList.capacity);

        arrayList.add(data);

        Assertions.assertEquals(1,arrayList.current);
        Assertions.assertEquals(MyArrayList.THRESHOLD,arrayList.capacity);

        Assertions.assertEquals(data, arrayList.get(0));

    }

    @Test
    void add_ListAllocate_successTest(){

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        for (int i = 0; i < MyArrayList.THRESHOLD - 1; i++) {
            arrayList.add(i);
        }
        arrayList.add(MyArrayList.THRESHOLD);

        Assertions.assertEquals(MyArrayList.THRESHOLD, arrayList.current);
        Assertions.assertEquals(MyArrayList.THRESHOLD * 2, arrayList.capacity);

    }

    @Test
    void get_successTest(){
        MyArrayList<Integer> arrayList = new MyArrayList<>();

        arrayList.add(10);

        Assertions.assertEquals(10,arrayList.get(0));

    }

    @Test
    void get_invalidIndex_failureTest(){
        MyArrayList<Integer> arrayList = new MyArrayList<>();

        Assertions.assertThrows(NegativeIndexException.class,()->arrayList.get(-1));

    }

    @Test
    void get_emptyList_successTest(){

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        Assertions.assertNull(arrayList.get(0));

    }

    @Test
    void delete_successTest(){
        MyArrayList<Integer> arrayList = new MyArrayList<>();

        arrayList.add(10);

        Assertions.assertEquals(10,arrayList.get(0));

        arrayList.delete(0);

        Assertions.assertNull(arrayList.get(0));

    }

    @Test
    void delete_invalidIndex_failureTest(){
        MyArrayList<Integer> arrayList = new MyArrayList<>();

        Assertions.assertThrows(NegativeIndexException.class,()->arrayList.delete(-1));

    }

    @Test
    void delete_emptyList_successTest(){

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class,()->arrayList.delete(0));

    }

    @Test
    void iterator_Test(){

        MyArrayList<Integer> arrayList = new MyArrayList<>();

        arrayList.add(156);
        arrayList.add(15);
        arrayList.add(28);
        arrayList.add(-44);
        arrayList.add(85);
        arrayList.add(41);
        arrayList.add(14);
        arrayList.add(26);
        arrayList.add(26);

        int i = 0;

        for (Integer integer : arrayList) {
            Assertions.assertEquals(integer,arrayList.get(i));
            i++;
        }
    }

}
