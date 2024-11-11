package com.example.t2.dao;

import com.example.t2.utils.Tool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecordsDao {

    //购买
    public static int update(String user_name, int zoon_id, String zoon_name, Date time, int spend) {
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String s = "insert into records (user_name,zoon_id,zoon_name,time ,spend) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1, user_name);
            preparedStatement.setInt(2, zoon_id);
            preparedStatement.setString(3,zoon_name);
            preparedStatement.setDate(4, time);
            preparedStatement.setInt(5, spend);
            int b = preparedStatement.executeUpdate();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // 返回一个错误代码或者其他适当的值
        } finally {
            // 在这里关闭数据库连接和 PreparedStatement
            Tool.Close(preparedStatement, connection);
        }
    }
    //查询所有购买记录
    public static ResultSet select(){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select * from records ";
            preparedStatement = connection.prepareStatement(s);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //根据用户名查询购买记录
    public static ResultSet select(String name){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select * from records where user_name =? ";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
