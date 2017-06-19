package com.lujiahao;

/**
 * Created by ljh on 2016/12/26.
 */
public class MyOrderArray extends MyArray {

    /**
     * 按顺序插入数据
     */
    public void insert(long value) {
        int i;
        for (i = 0; i < elements; i++) {
            if (arr[i] > value) {
                break;
            }
        }

        for (int j = elements; j > i; j--) {
            arr[j] = arr[j - 1];
        }
        arr[i] = value;
        elements++;
    }

    /**
     * 二分查找
     */
    public int binarySearch(long value){
        int middle = 0;
        int start = 0;
        int end = elements;
        while (true){
            middle = (start + end) / 2;
            if (arr[middle] == value){
                return middle;
            } else if (start > end){
                return -1;
            } else {
                if (arr[middle] > value){
                    end = middle -1;
                } else {
                    start = middle + 1;
                }
            }
        }
    }
}
