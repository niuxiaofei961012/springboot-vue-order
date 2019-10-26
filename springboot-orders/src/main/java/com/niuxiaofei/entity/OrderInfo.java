package com.niuxiaofei.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单详情类
 */
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = -626485735732953573L;
    private Integer id;
    private Integer order_id;
    private Integer product_id;
    private double price;
    private Integer num;
    private double total_price;
    private String name;
    private Date create_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                ", price=" + price +
                ", num=" + num +
                ", total_price=" + total_price +
                ", name='" + name + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
