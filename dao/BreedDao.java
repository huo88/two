package com.example.t2.dao;
import com.example.t2.model.Breed;
import com.example.t2.utils.Tool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BreedDao {

    //查询
    public static Breed inquire(String name){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Breed breed = null;
        try {
            String s = "select * from breed where name = ? ";
            preparedStatement = connection.prepareStatement(s);
            //5.为SQL语句中的参数赋值

            preparedStatement.setString(1,name);
            //6.执行SQL
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
               breed= new Breed();
                breed.setId(resultSet.getInt("id"));
                breed.setName(resultSet.getString("name"));
            }
            return breed;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            Tool.Close(resultSet,preparedStatement,connection);
        }

    }

    public static ResultSet All_breed(){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select * from breed ";
            preparedStatement = connection.prepareStatement(s);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//根据类别查询
    public static ResultSet selName(String name){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select * from breed where name = ? ";
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

    //添加
    public static int addBreed(String breed_name){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Breed breed = null;
        breed = BreedDao.inquire(breed_name);
        if (breed != null)
            return 2;
        else {
            connection = Tool.getConnection();//连接数据库
            try {
                String s = "insert into breed(breed_name) values(?)";
                preparedStatement = connection.prepareStatement(s);
                preparedStatement.setString(1,breed_name);
                return preparedStatement.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
                return -1;
            }
        }
    }

    //修改
    public static int update(int id,String name){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system", "root", "123456");//防止出现潜在的空指针异常
            String s = "update user set name = ? where id = ? ";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2, id);
            int b = preparedStatement.executeUpdate();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    //删除
    public static int deleteBreed(String name){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Breed breed = null;
        breed = BreedDao.inquire(name);
        if (breed == null)
            return 2;
        else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system", "root", "123456");//防止出现潜在的空指针异常
                String h = "delete from breed where name=? ";
                preparedStatement = connection.prepareStatement(h);
                preparedStatement.setString(1,name);
                return preparedStatement.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
                return -1;
            }finally {
                Tool.Close(preparedStatement,connection);
            }
        }
    }

}
