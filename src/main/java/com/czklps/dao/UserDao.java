package com.czklps.dao;

import com.czklps.Pojo.User;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserExistException;
import com.czklps.exception.UserNotFoundException;

import java.sql.ResultSet;
import java.util.List;

public interface UserDao {
    public User queryUserByUsernameAndPassword(String username, String password) throws PasswordCorrectException, UserNotFoundException;
    public boolean queryUsernameExist(String username) throws UserExistException;
    public int queryUsers(List<User> users, String username, String phone, Integer pageNo, Integer pageSize);
    public int insertUserToDatabase(User user);
    public List<User> queryUsers();
    public int deleteUserById(Integer id);
    public int updateUserById(User u);
    public int queryTotalCount();
}
