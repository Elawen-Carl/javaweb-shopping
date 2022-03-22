package com.czklps.dao;

import com.czklps.Pojo.Admin;
import com.czklps.Pojo.User;
import com.czklps.exception.AdminExistException;
import com.czklps.exception.AdminNotFoundException;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserExistException;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    public int add(Admin admin);
    public int updata(Admin admin);
    public int delete(Admin admin);
    public List<Admin> query();
    public int queryAdmins(List<Admin> admins, String username, String phone, Integer pageNo, Integer pageSize);
    public boolean queryUsernameExist(String name) throws AdminExistException;

    public Admin queryAdminByNameAndPwd(String name, String pwd) throws AdminNotFoundException, PasswordCorrectException;
}
