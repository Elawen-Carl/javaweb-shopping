package com.czklps.service.Impl;

import com.czklps.Pojo.Category;
import com.czklps.dao.CategoryDao;
import com.czklps.dao.Impl.CategoryDaoImpl;
import com.czklps.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> queryCategories() {
        return categoryDao.queryTotalCategory();
    }

    /**
     * 添加类别
     * 添加成功则返回true 否则返回false
     * @return
     */
    @Override
    public boolean addCategory(Category c) {
        return categoryDao.addCategory(c) != 0;
    }

    /**
     * 修改类别
     * 修改成功则返回true 否则返回false
     * @return
     */
    @Override
    public boolean updateCategoryById(Category c) {
        return categoryDao.updateCategoryById(c) != 0;
    }

    /**
     * 删除类别
     * 删除成功则返回true 否则返回false
     * @param pid 父id
     * @return
     */
    @Override
    public boolean deleteCategory(Integer id, Integer pid, boolean isLeaf) throws SQLException {
        return categoryDao.deleteCategoryById(id, pid, isLeaf)!=0;
    }

    @Override
    public Category queryCategoryNameById(Integer id) {
        return categoryDao.queryCategoryNameById(id);
    }

    @Override
    public int queryTotalCount() {
        return categoryDao.queryTotalCount();
    }

}
