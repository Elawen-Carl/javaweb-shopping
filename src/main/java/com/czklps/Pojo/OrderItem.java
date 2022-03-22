package com.czklps.Pojo;

import java.math.BigDecimal;

public class OrderItem {
    private Integer id;
    private String url;
    private String name;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalprice;
    private String orderid;

    public OrderItem() {
    }

    public OrderItem(Integer id, String url, String name, BigDecimal price, Integer count, BigDecimal totalprice, String orderid) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.price = price;
        this.count = count;
        this.totalprice = totalprice;
        this.orderid = orderid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalprice() {
        return new BigDecimal(count).multiply(price);
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", totalprice=" + getTotalprice() +
                ", orderid='" + orderid + '\'' +
                '}';
    }
}
