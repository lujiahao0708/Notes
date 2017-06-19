package com.lujiahao;

/**
 * 使用自定义的类来封装数组
 * Created by ljh on 2016/12/26.
 */
public class MyArray {
    protected long[] arr;
    protected int elements;// 表示有效数据的长度

    public MyArray() {
        arr = new long[50];
    }

    public MyArray(int maxSize) {
        arr = new long[maxSize];
    }

    /**
     * 添加数据
     */
    public void insert(long value) {
        arr[elements] = value;
        elements++;
    }

    /**
     * 显示数据
     */
    public void display() {
        System.out.print("[");
        for (int i = 0; i < elements; i++) {
            if (i != elements - 1) {
                System.out.print(arr[i] + ",");
            } else {
                System.out.print(arr[i]);
            }
        }
        System.out.println("]");
    }

    /**
     * 线性查找
     * 根据value查找数据
     */
    public int search(long value) {
        int i;
        for (i = 0; i < elements; i++) {
            if (value == arr[i]) {
                break;
            }
        }

        if (elements == i) {
            return -1;
        } else {
            return i;
        }
    }



    /**
     * 根据索引来查找
     */
    public long get(int index) {
        if (index >= elements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return arr[index];
        }
    }

    /**
     * 根据索引删除数据
     */
    public void del(int index) {
        if (index >= elements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            for (int i = index; i < elements; i++) {
                arr[i] = arr[i + 1];
            }
            elements--;
        }
    }

    /**
     * 更新数据
     */
    public void update(int index, int newValue) {
        if (index >= elements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            arr[index] = newValue;
        }
    }
}
