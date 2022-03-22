package com.czklps.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.czklps.Pojo.User;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserExistException;
import com.czklps.exception.UserNotFoundException;
import com.czklps.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Date;
import java.util.List;

class UserServiceTest {
    private UserService userService = new UserServiceImpl();
    @Test
    void login() {
        try {
            System.out.println(userService.login("admin", "admin"));
        } catch (UserNotFoundException e) {
            System.out.println("user not found ! please input correct!");
        } catch (PasswordCorrectException e) {
            System.out.println("password incorrect ! please input correct!");
        }
    }
    @Test
    void usernameExist(){
        try {
            System.out.println(userService.usernameExist("adm123in"));
        } catch (UserExistException e) {
            System.out.println("username already exist!");
        }
    }

    @Test
    void saveUser(){
        User u = new User(null, "asdfasdf", "hfgwudi", "12345678912", new Date(), "hgfwudi@qq.com");
        System.out.println(userService.saveUser(u));
    }

    @Test
    void queryTotalUser(){
        System.out.println(userService.queryTotalUser());
    }

    @Test
    void testJosn(){
        List<User> users = userService.queryTotalUser();
        JSONArray jsonArray = new JSONArray();
        for (User user : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",user.getId());
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("phone",user.getPhone());
            jsonObject.put("createtime",user.getCreateTime());
            jsonObject.put("email",user.getEmail());
            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray);
    }

    @Test
    void updateUserById(){
        User u = new User(2, "boboge", "boboge", "123456789", null, "bobogewudi@qq.com");
        System.out.println(userService.updateUserById(u));
    }
    @Test
    void test(){
        System.out.println(new File("src/main/webapp/static/images/product/text.txt").getAbsolutePath());
    }
}