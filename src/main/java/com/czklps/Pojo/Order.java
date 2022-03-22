package com.czklps.Pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private List<OrderItem> orderitems = new ArrayList<>();
    private String orderid;
    private Integer userid;
    private Integer totalcount;
    private BigDecimal totalprice;

    private Date createtime;

    public Order() {
    }

    public Order(Integer userid) {
        this.orderid = System.currentTimeMillis() + userid + "";
        this.userid = userid;
    }

    public Order(List<OrderItem> orderitem, Integer userid) {
        this.orderitems = orderitem;
        this.orderid = System.currentTimeMillis() + userid + "";
        this.userid = userid;
    }

    public void addOrderItem(OrderItem orderItem){
        if(orderItem == null) return;
        for (OrderItem item : orderitems) {
            if(item.getName().trim().equals(orderItem.getName().trim())){
                item.setCount(item.getCount() + orderItem.getCount());
                return;
            }
        }
        orderitems.add(orderItem);
    }

    public Integer getTotalcount() {
        int totalcount = 0;
        for (OrderItem orderitem : orderitems) {
            totalcount += orderitem.getCount();
        }
        return totalcount;
    }

    public BigDecimal getTotalprice() {
        BigDecimal totalprice = new BigDecimal("0");
        for (OrderItem item : orderitems) {
            totalprice = totalprice.add(item.getTotalprice());
        }
        return totalprice;
    }

    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public List<OrderItem> getOrderitem() {
        return orderitems;
    }

    public String getOrderid() {
        return orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setOrderitem(List<OrderItem> orderitem) {
        this.orderitems = orderitem;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderitems=" + orderitems +
                ", orderid='" + orderid + '\'' +
                ", userid=" + userid +
                ", totalcount=" + getTotalcount() +
                ", totalprice=" + getTotalprice() +
                ", createtime=" + createtime +
                '}';
    }
}
