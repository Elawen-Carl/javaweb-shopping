package com.czklps.dao;

import com.czklps.Pojo.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> queryProducts();
    public int queryTotalCount();
    public List<Product> queryProducts(Integer categoryId, String keyword, double lowprice, double highprice, Integer pageNo, Integer pageSize);
    public int deleteProductById(Integer id);
    public int insertProduct(Product p);
    public int updateProductById(Product p);
    public Product queryProductById(Integer id);
    public int updateProductImgPath(Product p);
}
