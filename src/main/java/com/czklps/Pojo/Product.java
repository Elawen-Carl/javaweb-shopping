package com.czklps.Pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;
    private String name;
    private String url;
    private BigDecimal price;
    private String descr;
    private Date createtime;
    private Integer categoryId;
    private Category category;

    public Product() {
    }

    public Product(Integer id, String name, String url, BigDecimal price, String descr, Date cretetime) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.price = price;
        this.descr = descr;
        this.createtime = cretetime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", descr='" + descr + '\'' +
                ", createtime=" + createtime +
                ", categoryId=" + categoryId +
                ", category=" + category +
                '}';
    }
}
