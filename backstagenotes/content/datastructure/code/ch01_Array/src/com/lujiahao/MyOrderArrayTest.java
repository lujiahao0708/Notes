package com.lujiahao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ljh on 2016/12/26.
 */
public class MyOrderArrayTest {
    private MyOrderArray arr = null;
    @Before
    public void setUp() throws Exception {
        arr = new MyOrderArray();
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
    public void binarySearch() throws Exception{
        arr.display();
        System.out.println(arr.binarySearch(20));
    }
}