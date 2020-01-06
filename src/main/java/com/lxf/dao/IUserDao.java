package com.lxf.dao;

import com.lxf.domain.User;

import java.util.List;

public interface IUserDao {
    public List<User> findAll();
    public void savaUser(User user);
    public void updataUser(User user);
    public void delUser(Integer id);
}
