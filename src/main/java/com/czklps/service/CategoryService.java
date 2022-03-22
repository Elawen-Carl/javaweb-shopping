package com.czklps.service;

import com.czklps.Pojo.Category;
import com.czklps.Pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService{
    public List<Category> queryCategories();
    public boolean addCategory(Category c);
    public boolean updateCategoryById(Category c);
    public boolean deleteCategory(Integer id, Integer pid, boolean isLeaf) throws SQLException;
    public Category queryCategoryNameById(Integer id);
    public int queryTotalCount();

}
