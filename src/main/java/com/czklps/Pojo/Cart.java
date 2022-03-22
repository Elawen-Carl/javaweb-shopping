package com.czklps.Pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartitems = new ArrayList<>();

    public Cart() {
    }

    public Cart(List<CartItem> cartitems) {
        this.cartitems = cartitems;
    }

    public void addCartItem(CartItem cartItem){
        if(cartItem == null) return;
        for (CartItem item : cartitems) {
            if(item.getId() == cartItem.getId()){
                item.setCount(item.getCount() + cartItem.getCount());
                return;
            }
        }
        cartitems.add(cartItem);
    }

    public Integer getTotalcount() {
        int totalcount = 0;
        for (CartItem cartitem : cartitems) {
            totalcount += cartitem.getCount();
        }
        return totalcount;
    }

    public BigDecimal getTotalprice() {
        BigDecimal totalprice = new BigDecimal("0");
        for (CartItem cartitem : cartitems) {
            totalprice = totalprice.add(cartitem.getTotalprice());
        }
        return totalprice;
    }


    public List<CartItem> getCartitems() {
        return cartitems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartitems=" + cartitems +
                ", totalPrice=" + getTotalprice() +
                ", totalcount=" + getTotalcount() +
                '}';
    }
}
