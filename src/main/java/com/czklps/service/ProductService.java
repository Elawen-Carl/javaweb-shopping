package com.czklps.service;

import com.czklps.Pojo.Product;

import java.util.List;

public interface ProductService {
    public List<Product> queryProducts();
    public int queryTotalCount();
    public boolean deleteProductById(Integer id);
    public List<Product> queryProducts(Integer categoryId, String keyword, double lowprice, double highprice, Integer pageNo, Integer pageSize);
    public boolean addProduct(Product p);
    public boolean updateProductById(Product p);
    public Product queryProductById(Integer id);
    public boolean updateProductImgPath(Product p);

}
