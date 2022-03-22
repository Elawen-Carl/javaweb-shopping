package com.czklps.service.Impl;

import com.czklps.Pojo.User;
import com.czklps.dao.Impl.UserDaoImpl;
import com.czklps.dao.UserDao;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserExistException;
import com.czklps.exception.UserNotFoundException;
import com.czklps.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * @param username
     * @param password
     * @return 不为 null 则表示用户名密码正确 null 则取反
     * @throws UserNotFoundException
     * @throws PasswordCorrectException
     */
    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordCorrectException {
        return userDao.queryUserByUsernameAndPassword(username, password);
    }

    @Override
    public int queryUsers(List<User> users, String username, String phone, Integer pageNo, Integer pageSize) {
        return userDao.queryUsers(users, username, phone, pageNo, pageSize);
    }

    @Override
    public int queryTotalCount() {
        return userDao.queryTotalCount();
    }

    /**
     * 若存在则返回 true 不存在则 false 并且抛出 userNotFoundException
     * @param username
     * @return
     */
    @Override
    public boolean usernameExist(String username) throws UserExistException {
        return userDao.queryUsernameExist(username);
    }

    /**
     *  成功插入则返回 true 否则 返回 false
     * @param u
     * @return
     */
    @Override
    public boolean saveUser(User u) {
        return userDao.insertUserToDatabase(u) != 0;
    }

    /**
     * 成功查询
     * @return
     */
    @Override
    public List<User> queryTotalUser() {
        return userDao.queryUsers();
    }

    /**
     * 删除成功 返回true 不成功则 false
     * @param id
     * @return
     */
    @Override
    public boolean deleteUserById(Integer id) {
        return userDao.deleteUserById(id) != 0;
    }

    /**
     * 修改成功返回 true 不成功则 false
     * @param u
     * @return
     */
    @Override
    public boolean updateUserById(User u) {
        return userDao.updateUserById(u) != 0;
    }

}
