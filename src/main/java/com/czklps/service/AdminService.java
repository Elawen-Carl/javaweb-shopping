package com.czklps.service;

import com.czklps.Pojo.Admin;
import com.czklps.Pojo.User;
import com.czklps.exception.*;

import java.util.List;

public interface AdminService {
    public int add(Admin admin);
    public int updata(Admin admin);
    public int delete(Admin admin);
    public List<Admin> query();
    public int queryAdmins(List<Admin> admins, String username, String phone, Integer pageNo, Integer pageSize);
    public boolean adminNameExist(String name) throws AdminExistException;
    public Admin login(String name, String pwd) throws AdminNotFoundException, PasswordCorrectException;
}
