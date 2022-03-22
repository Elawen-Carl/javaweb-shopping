package com.czklps.service;

import com.czklps.Pojo.Product;
import com.czklps.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService productService = new ProductServiceImpl();
    @Test
    void queryProducts() {
        System.out.println(productService.queryProducts());
    }

    @Test
    void deleteProductById() {
        System.out.println(productService.deleteProductById(10));
    }

    @Test
    void updateProductById(){
        Product p = new Product(null,"波波哥帅爆了","static/images/eeb99a5d4cc3ad1a[1].jpg",new BigDecimal(600),"如何成为像广发一样的大牛",new Date());
        System.out.println(productService.updateProductById(p));
    }

    @Test
    void queryProductById(){
        System.out.println(productService.queryProductById(1));
    }
}