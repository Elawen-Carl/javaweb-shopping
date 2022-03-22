package com.czklps.Pojo;

import java.math.BigDecimal;

public class CartItem {
    private Integer id;
    private String url;
    private String name;
    private BigDecimal price;
    private Integer count;

    public CartItem() {
    }

    public CartItem(Integer id, String url, String name, BigDecimal price, Integer count) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.price = price;
        this.count = count;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", totalprice=" + getTotalprice() +
                '}';
    }
}
