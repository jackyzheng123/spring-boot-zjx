package com.zjx.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/3/20 11:39
 * @Version V1.0
 **/
public class Order implements Serializable {

    private Integer id;
    private String userName;
    private String goodsName;
    private BigDecimal goodsPrice;
    private Integer num;
    private Date createTime;

    public Order() {
    }

    public Order(String userName, String goodsName, BigDecimal goodsPrice, Integer num) {
        this.userName = userName;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", num=" + num +
                ", createTime=" + createTime +
                '}';
    }
}
