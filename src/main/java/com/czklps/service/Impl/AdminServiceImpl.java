package com.czklps.service.Impl;

import com.czklps.Pojo.Admin;
import com.czklps.Pojo.User;
import com.czklps.dao.AdminDao;
import com.czklps.dao.Impl.AdminDaoImpl;
import com.czklps.exception.AdminExistException;
import com.czklps.exception.AdminNotFoundException;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserExistException;
import com.czklps.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDao adminDao = new AdminDaoImpl();

    @Override
    public int add(Admin admin) {
        return adminDao.add(admin);
    }

    @Override
    public int updata(Admin admin) {
        return adminDao.updata(admin);
    }

    @Override
    public int delete(Admin admin) {
        return adminDao.delete(admin);
    }

    @Override
    public List<Admin> query() {
        return adminDao.query();
    }

    @Override
    public int queryAdmins(List<Admin> admins, String username, String phone, Integer pageNo, Integer pageSize) {
        return adminDao.queryAdmins(admins, username, phone, pageNo, pageSize);
    }

    @Override
    public boolean adminNameExist(String name) throws AdminExistException {
        return adminDao.queryUsernameExist(name);
    }

    @Override
    public Admin login(String name, String pwd) throws AdminNotFoundException, PasswordCorrectException {
        return adminDao.queryAdminByNameAndPwd(name,pwd);
    }

}
