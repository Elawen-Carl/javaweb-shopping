package com.czklps.dao;

import com.czklps.Pojo.Admin;
import com.czklps.dao.Impl.AdminDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminDaoTest {
    AdminDao adminDao = new AdminDaoImpl();
    @Test
    void insertAdmin() {
        Admin a = new Admin();
        a.setId(-1);
        a.setName("bobasdfsoge");
        a.setPhone("13546548654");
        a.setEmail("3038652743@qq.com");
        a.setInformation("bobogewudi");
        adminDao.add(a);
    }

    @Test
    void updata(){
        Admin a = new Admin();
        a.setId(1);
        a.setName("javatest");
        a.setPhone("13546548654");
        a.setEmail("3038652743@qq.com");
        a.setInformation("bobogewudi");
        adminDao.updata(a);
    }
    @Test
    void delete(){
        Admin a = new Admin();
        a.setId(2);
        adminDao.delete(a);
    }
    @Test
    void query(){
        System.out.println(adminDao.query());
    }

    @Test
    void queryAdmins(){
        List<Admin> admins = new ArrayList<Admin>();
        System.out.println(adminDao.queryAdmins(admins, "java", "135456548658", 1, 4));
        System.out.println(admins);
    }
}