package com.czklps.dao;

import com.czklps.Pojo.User;
import com.czklps.dao.Impl.UserDaoImpl;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserExistException;
import com.czklps.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private UserDao userDao = new UserDaoImpl();
    @Test
    void queryByUsernameAndPassword() {
        try {
            System.out.println(userDao.queryUserByUsernameAndPassword("admin", "1231546"));
        } catch (UserNotFoundException e) {
            System.out.println("username or password error!");
        } catch (PasswordCorrectException e) {
            System.out.println("password incorrect");
        }
    }

    @Test
    void queryUsers1(){
        List<User> u = new ArrayList<User>();
        System.out.println(userDao.queryUsers(u,"java","13546548650",1,15));
        System.out.println(u);
    }
    @Test
    public void queryUsernameExist(){
        try {
            System.out.println(userDao.queryUsernameExist("admin"));
        } catch (UserExistException e) {
            System.out.println("用户名存在");
        }

    }
    @Test
    public void insertUserToDatabase(){
        User u = new User(null, "gf", "asdf", "13333333333", new Date(), "asdf@qq.com");
        System.out.println(userDao.insertUserToDatabase(u));
    }

    @Test
    void queryUsers(){
        System.out.println(userDao.queryUsers());
    }

    @Test
    void deleteUserById(){
        System.out.println(userDao.deleteUserById(108));
    }

    @Test
    void updateUser(){
        User u = new User(2, "yuzhouwudibo", "yuzhouwudibo", "13546548658", new Date(), "bobogewudi@qq.com");
        System.out.println(userDao.updateUserById(u));
    }
}