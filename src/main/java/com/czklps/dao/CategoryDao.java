package com.czklps.dao;

import com.czklps.Pojo.Category;

import java.sql.SQLException;
import java.util.List;


public interface CategoryDao {
    public List<Category> queryTotalCategory();
    public int addCategory(Category c);
    public int updateCategoryById(Category c);
    public int deleteCategoryById(Integer id, Integer pid, boolean isLeaf) throws SQLException;
    public Category queryCategoryNameById(Integer id);
    public int queryTotalCount();
}
