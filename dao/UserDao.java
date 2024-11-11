package com.example.t2.dao;
import com.example.t2.model.User;
import com.example.t2.utils.Tool;

import java.sql.*;
import java.util.Date;

public class UserDao {

    //查询所有用户
    public static ResultSet All_user(){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select * from user ";
            preparedStatement = connection.prepareStatement(s);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//根据用户名、密码、角色查询
public static User login(String name,String password,int index){     //登录
    Connection connection = Tool.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    User user = null;
    //查询注册用户名是否存在
    try {
        String s = "select * from user where name = ? and password = ? and role = ?";
        preparedStatement = connection.prepareStatement(s);
        //5.为SQL语句中的参数赋值
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,password);
        preparedStatement.setInt(3,index);
        //6.执行SQL
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setBirthday(resultSet.getDate("birthday"));
            user.setRole(resultSet.getInt("role"));
            user.setSex(resultSet.getString("sex"));
            user.setPhone(resultSet.getString("phone"));
        }
        return user;
    }catch (Exception e){
        e.printStackTrace();
        return null;
    }finally {
        Tool.Close(resultSet,preparedStatement,connection);
    }
}

//根据用户名和密码查询
public static User inquire(String name,String password){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;ResultSet resultSet = null;
        User user = null;
        try {
            String s = "select * from user where name = ? and password = ? ";
            preparedStatement = connection.prepareStatement(s);
            //5.为SQL语句中的参数赋值
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);

            //6.执行SQL
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setRole(resultSet.getInt("role"));
                user.setSex(resultSet.getString("sex"));
                user.setPhone(resultSet.getString("phone"));
            }
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            Tool.Close(resultSet,preparedStatement,connection);
        }
    }

    //根据用户名和密码查询
    public static ResultSet inquire2(String name){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;ResultSet resultSet = null;
        User user = null;
        try {
            String s = "select * from user where name = ?  ";
            preparedStatement = connection.prepareStatement(s);
            //5.为SQL语句中的参数赋值
            preparedStatement.setString(1,name);

            //6.执行SQL
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //根据用户名查询
    public static ResultSet selName(String name){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select * from user where name = ? ";
            preparedStatement = connection.prepareStatement(s);
            //5.为SQL语句中的参数赋值
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

//注册
public static int addUser(String name,String password,String phone,int role,String sex){
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    User user = null;
    user = UserDao.inquire(name,password);
    if (user != null)
        return 2;
    else {
        connection = Tool.getConnection();//连接数据库
        try {
            String s = "insert into user(name,password,phone,role ,sex) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, phone);
            preparedStatement.setInt(4, role);
            preparedStatement.setString(5, sex);
            return preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            Tool.Close(preparedStatement,connection);
        }
    }

}
//批量插入
public static int addUser(String name, String password, String birthday, String phone, int role, String sex){
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    User user = null;
    user = UserDao.inquire(name,password);
    if (user != null)
        return 2;
    else {
        connection = Tool.getConnection();//连接数据库
        try {
            String s = "insert into user(name,password,birthday,phone,role,sex) values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, birthday);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, role);
            preparedStatement.setString(6, sex);
            return preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

}

//删除
public static int deleteUser(String name,String password){
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    User user = null;
    user = UserDao.inquire(name,password);
    if (user == null)
        return 2;
    else {
    try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system", "root", "123456");//防止出现潜在的空指针异常
        String h = "delete from user where name=? and password=?";
        preparedStatement = connection.prepareStatement(h);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2, password);
        return preparedStatement.executeUpdate();
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }finally {
        Tool.Close(preparedStatement,connection);
    }
}
}

//根据编号修改信息
    public static int update(int id,String name,String password,String birthday,String sex,String phone) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system", "root", "123456");//防止出现潜在的空指针异常
            String s = "update user set name = ?,password = ?,birthday = ?,sex = ?,phone = ? where id = ? ";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,birthday);
            preparedStatement.setString(4,sex);
            preparedStatement.setString(5,phone);
            preparedStatement.setInt(6, id);
            int b = preparedStatement.executeUpdate();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }finally {
            Tool.Close(preparedStatement,connection);
        }

    }
    //根据用户名和密码修改信息
    public static int update(String name, String password, String birthday, String sex, String phone){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system", "root", "123456");//防止出现潜在的空指针异常
            String s = "update user set birthday = ?,sex = ?,phone = ? where name = ? and password = ? ";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,birthday);
            preparedStatement.setString(2,sex);
            preparedStatement.setString(3,phone);
            preparedStatement.setString(4,name);
            preparedStatement.setString(5,password);
            int b = preparedStatement.executeUpdate();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }finally {
            Tool.Close(preparedStatement,connection);
        }


    }

    //通过用户名查询角色
    public static int getRole(String name) {
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select * from user where name = ? ";
            preparedStatement = connection.prepareStatement(s);
            //5.为SQL语句中的参数赋值
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getInt("role");
            }
            else{
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            Tool.Close(resultSet,preparedStatement,connection);
        }
    }

}
