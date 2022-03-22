package com.czklps.service.Impl;

import com.czklps.Pojo.Product;
import com.czklps.dao.Impl.ProductDaoImpl;
import com.czklps.dao.ProductDao;
import com.czklps.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    /**
     * 查询所有用户并返回所有用户数据
     * @return
     */
    @Override
    public List<Product> queryProducts() {
        return productDao.queryProducts();
    }

    @Override
    public int queryTotalCount() {
        return productDao.queryTotalCount();
    }

    /**
     * 删除成功返回 true 不成功则 false
     * @param id
     * @return
     */
    @Override
    public boolean deleteProductById(Integer id) {
        return productDao.deleteProductById(id) != 0;
    }

    @Override
    public List<Product> queryProducts(Integer categoryId, String keyword, double lowprice, double highprice, Integer pageNo, Integer pageSize) {
        return productDao.queryProducts(categoryId, keyword, lowprice, highprice, pageNo, pageSize);
    }

    /**
     * 删除成功返回 true 不成功则 false
     * @param p
     * @return
     */
    @Override
    public boolean addProduct(Product p) {
        return productDao.insertProduct(p) != 0;
    }

    /**
     * 删除修改返回 true 不成功则 false
     * @param p
     * @return
     */
    @Override
    public boolean updateProductById(Product p) {
        return productDao.updateProductById(p) != 0;
    }

    /**
     * 根据 id 查询商品 查询成功则返回这个商品 不成功则为 null
     * @param id
     * @return
     */
    @Override
    public Product queryProductById(Integer id) {
        return productDao.queryProductById(id);
    }

    /**
     * 根据 id 修改图片 修改成功则返回这个商品 不成功则为false
     * @param id
     * @return
     */
    @Override
    public boolean updateProductImgPath(Product p) {
        return productDao.updateProductImgPath(p) != 0;
    }

}
