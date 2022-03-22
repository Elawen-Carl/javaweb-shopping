package com.czklps.dao;


import com.czklps.Pojo.Product;
import com.czklps.dao.Impl.ProductDaoImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDaoTest {
    private ProductDao productDao = new ProductDaoImpl();
    @Test
    public void queryProducts() {
        System.out.println(productDao.queryProducts());
    }

    @Test
    void deleteProductById(){
        System.out.println(productDao.deleteProductById(9));
    }

    @Test
    void insertProduct(){
        Product p = new Product(null,"华为 HUAWEI Mate 40 Pro","huaweimeta40.jpg",new BigDecimal(6599.00) ,"4G 全网通 麒麟9000旗舰芯片 8GB+256GB亮黑色华为手机【搭载HarmonyOS 2】",new Date());
        System.out.println(productDao.insertProduct(p));
    }

    @Test
    void updateProductById(){
        Product p = new Product(null,"技术顶尖","static/images/eeb99a5d4cc3ad1a[1].jpg",new BigDecimal(600),"如何成为像波波哥一样的大牛",new Date());
        System.out.println(productDao.updateProductById(p));
    }

    @Test
    void queryProductById(){
        System.out.println(productDao.queryProductById(1));
    }

    @Test
    void updateProductImgPathByid(){
        Product p = new Product();
        p.setUrl("static/images/product/banner.jpg");
        p.setId(24);
        productDao.updateProductImgPath(p);
    }
}