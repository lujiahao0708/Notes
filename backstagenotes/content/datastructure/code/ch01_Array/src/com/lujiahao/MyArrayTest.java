package com.lujiahao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ljh on 2016/12/26.
 */
public class MyArrayTest {
    private MyArray arr = null;

    @Before
    public void setUp() throws Exception {
        arr = new MyArray();
        arr.insert(10);
        arr.insert(30);
        arr.insert(20);
        arr.insert(58);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insert() throws Exception {
        arr.insert(1);
        arr.display();
    }

    @Test
    public void display() throws Exception {
        arr.display();
    }

    @Test
    public void search() throws Exception {
        System.out.println(arr.search(10));
    }

    @Test
    public void del() throws Exception {
        arr.display();
        arr.del(1);
        arr.display();
    }

    @Test
    public void update() throws Exception {
        arr.display();
        arr.update(1, 100);
        arr.display();
    }

    @Test
    public void testSequentialSearch() {
        int[] arr = new int[]{1, 5, 6, 2, 45, 88, 66};
        int i = sequentialSearch(arr, 1);
        System.out.println("角标:" + i);
    }

    private int sequentialSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (value == arr[i]) {
                return i;
            }
        }
        System.out.println("未找到该值");
        return -1;
    }

    @Test
    public void testBinarySearch() {
        int[] arr = new int[]{1, 5, 6, 45, 88, 66};
        int i = binarySearch(arr, 88);
        System.out.println("角标:" + i);
    }

    private int binarySearch(int[] arr, int value) {
        int start = 0;
        int end = arr.length - 1;
        int middle = 0;
        while (start <= end) {
            middle = (start + end) / 2;
            if (value == arr[middle]) {
                System.out.println("查找到该值的索引");
                return middle;
            } else if (value > arr[middle]) {
                start = middle + 1;
            } else if (value < arr[middle]){
                end = middle -1;
            }
        }
        System.out.println("未查询到该值");
        return -1;
    }


}