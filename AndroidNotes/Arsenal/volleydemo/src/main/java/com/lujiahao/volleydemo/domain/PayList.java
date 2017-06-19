package com.lujiahao.volleydemo.domain;

import com.lujiahao.l_library.engine.response.IResponse;

import java.io.Serializable;

/**
 * 预定的pay_list
 * @author lujiahao
 * created at 2016/3/25 10:38
 */
public class PayList implements Serializable,IResponse {
    private int price;
    private int maxReserveSpace;// 最大车位数
    private int orderTakeStatus;
    private int pid;
    private String mainAdminPhone;
    private int remainSpace;// 可预约数
    private int type;
    private String key;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxReserveSpace() {
        return maxReserveSpace;
    }

    public void setMaxReserveSpace(int maxReserveSpace) {
        this.maxReserveSpace = maxReserveSpace;
    }

    public int getOrderTakeStatus() {
        return orderTakeStatus;
    }

    public void setOrderTakeStatus(int orderTakeStatus) {
        this.orderTakeStatus = orderTakeStatus;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getMainAdminPhone() {
        return mainAdminPhone;
    }

    public void setMainAdminPhone(String mainAdminPhone) {
        this.mainAdminPhone = mainAdminPhone;
    }

    public int getRemainSpace() {
        return remainSpace;
    }

    public void setRemainSpace(int remainSpace) {
        this.remainSpace = remainSpace;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "PayList{" +
                "price=" + price +
                ", maxReserveSpace=" + maxReserveSpace +
                ", orderTakeStatus=" + orderTakeStatus +
                ", pid=" + pid +
                ", mainAdminPhone='" + mainAdminPhone + '\'' +
                ", remainSpace=" + remainSpace +
                ", type=" + type +
                ", key='" + key + '\'' +
                '}';
    }
}
