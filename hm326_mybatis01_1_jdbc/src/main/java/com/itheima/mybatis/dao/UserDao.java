package com.itheima.mybatis.dao;

import com.itheima.mybatis.pojo.User;

import java.util.List;

public interface UserDao {

    List<User> queryUserList() throws Exception;
}
