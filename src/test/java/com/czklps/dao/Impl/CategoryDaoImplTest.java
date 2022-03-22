package com.czklps.dao.Impl;

import com.czklps.dao.CategoryDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDaoImplTest {
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Test
    void queryTotalCategory() {
        System.out.println(categoryDao.queryTotalCategory());
    }

    @Test
    void deleteCategory(){
//        categoryDao.deleteCategoryById(24,23,true);
    }
}
