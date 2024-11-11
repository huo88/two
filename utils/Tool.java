package com.example.t2.utils;
import java.sql.*;
public class Tool {//工具类

    public static Connection getConnection(){//链接数据库

        try {
            Connection connection = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            //协议：使用JDBC技术访问mysql
            String url = "jdbc:mysql://localhost:3306/system?useSSL=false&serverTimezone=UTC";
            String dbUsername = "root";
            String dbPassword = "123456";
            //通过驱动管理器创建于数据库的连接
            connection = DriverManager.getConnection(url,dbUsername,dbPassword);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //增、删、改
    public static void Close(Statement statement, Connection connection){
        try {
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //查
    public static void Close(ResultSet resultSet, Statement statement, Connection connection){
        try {
            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}




