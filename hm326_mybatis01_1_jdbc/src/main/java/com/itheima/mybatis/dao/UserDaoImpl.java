package com.itheima.mybatis.dao;

import com.itheima.mybatis.pojo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8";
    private String username = "root";
    private String password = "root";

    @Override
    public List<User> queryUserList() throws Exception {
        List<User> userList = new ArrayList<>();

        Class.forName(driver);
        /**
         * 问题一：频繁获取释放数据库连接，影响数据库和系统性能
         * 解决：数据库连接池，C3P0 DBCP DRUID（阿里巴巴，号称世界最强没有之一）
         */
        Connection connection = DriverManager.getConnection(url, username, password);
        /**
         * 问题二：sql语句硬编码，后期难以维护
         * 解决：sql语句和java代码分离，比如写在配置文件。Mybatis框架就是这么干
         */
        String sql = "select * from user  ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        /**
         * 问题三：sql语句where条件参数和占位符一一对应，后期难以维护
         */
        //preparedStatement.setString(1,"");
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        /**
         * 问题四：结果集解析麻烦，查询列硬编码
         * 期望：如果单条记录直接返回对象，如果多条记录返回list集合
         */
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setSex(resultSet.getString("sex"));
            user.setBirthday(resultSet.getDate("birthday"));
            user.setAddress(resultSet.getString("address"));
            userList.add(user);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return userList;
    }
}
