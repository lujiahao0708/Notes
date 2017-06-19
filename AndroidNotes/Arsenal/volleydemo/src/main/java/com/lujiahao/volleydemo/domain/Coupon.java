package com.lujiahao.volleydemo.domain;

import com.lujiahao.l_library.engine.response.IResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 停车券的bean
 * @author lujiahao
 * created at 2016/3/21 15:41
 */
public class Coupon implements Serializable,IResponse {
    private String status;
    private List<Data> data;

    private class Data{
        private String phone;//用户电话号码
        private int id;//停车券编号
        private int price;//停车券金额
        private long dueDate;//截止日期
        private int limitDay;//停车券有效天数
        private String name;//名称
        private int couponType;//停车券类型
        //private List<?> parks;//试用停车场pid

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public long getDueDate() {
            return dueDate;
        }

        public void setDueDate(long dueDate) {
            this.dueDate = dueDate;
        }

        public int getLimitDay() {
            return limitDay;
        }

        public void setLimitDay(int limitDay) {
            this.limitDay = limitDay;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCouponType() {
            return couponType;
        }

        public void setCouponType(int couponType) {
            this.couponType = couponType;
        }

//    public List<?> getParks() {
//        return parks;
//    }
//
//    public void setParks(List<?> parks) {
//        this.parks = parks;
//    }

        @Override
        public String toString() {
            return "Coupon{" +
                    "phone='" + phone + '\'' +
                    ", id=" + id +
                    ", price=" + price +
                    ", dueDate=" + dueDate +
                    ", limitDay=" + limitDay +
                    ", name='" + name + '\'' +
                    ", couponType=" + couponType +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
