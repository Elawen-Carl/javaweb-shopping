package com.czklps.service;

import com.czklps.Pojo.User;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserExistException;
import com.czklps.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public User login(String username, String password) throws UserNotFoundException, PasswordCorrectException;
    public int queryUsers(List<User> users, String username, String phone, Integer pageNo, Integer pageSize);
    public int queryTotalCount();
    public boolean usernameExist(String username) throws UserExistException;
    public boolean saveUser(User u);
    public List<User> queryTotalUser();
    public boolean deleteUserById(Integer id);
    public boolean updateUserById(User u);
}
