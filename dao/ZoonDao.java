package com.example.t2.dao;
import com.example.t2.model.Zoon;
import com.example.t2.utils.Tool;
import java.sql.*;

public class ZoonDao {
    //宠物全部查询
    public static ResultSet All_inquire() {
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select zoon.*,breed.* from zoon,breed where zoon.type_id = breed.breed_id";
            preparedStatement = connection.prepareStatement(s);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//根据编号查询
    public static ResultSet id_inquire(int zoon_id){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;ResultSet resultSet = null;
        try {
            String s = "select zoon.*,breed.* from zoon,breed where id = ? and zoon.type_id = breed.breed_id ";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setInt(1,zoon_id);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception e){
          e.printStackTrace();
          return null;
        }
    }
    //根据性别查询
    public static ResultSet sex_inquire(String sex){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Zoon zoon = null;
        try {
            String s = "select zoon.*,breed.* from zoon,breed where sex = ? and zoon.type_id = breed.breed_id ";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,sex);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //根据品种id查询
    public static ResultSet id_inquire2(int type_id){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select zoon.*,breed.* from zoon,breed where zoon.type_id=breed.breed_id and breed.breed_id=?";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setInt(1,type_id);
            resultSet = preparedStatement.executeQuery();

            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //根据宠物名查询
    public static Zoon name_inquire(String name){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Zoon zoon = null;
        try {
            String s = "select * from zoon where name = ? ";
            preparedStatement = connection.prepareStatement(s);
            //5.为SQL语句中的参数赋值
            preparedStatement.setString(1,name);

            //6.执行SQL
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
               zoon = new Zoon();
                zoon.setId(resultSet.getInt("id"));
                zoon.setName(resultSet.getString("name"));
                zoon.setBirthday(resultSet.getDate("birthday"));
                zoon.setSex(resultSet.getString("sex"));
            }
            return zoon;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            Tool.Close(resultSet,preparedStatement,connection);
        }
    }

    //根据宠物名查询返回列表
    public static ResultSet name1_inquire(String name){
        Connection connection = Tool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String s = "select zoon.*,breed.* from zoon,breed where name = ? and zoon.type_id=breed.breed_id ";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();

            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //添加
    public static int addZoon(String name,int type_id,Date birthday,int unit,String sex){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system", "root", "123456");//防止出现潜在的空指针异常
            String s = "insert into zoon(name,type_id,birthday,unit,sex) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2, type_id);
            preparedStatement.setDate(3, birthday);
            preparedStatement.setInt(4, unit);
            preparedStatement.setString(5,sex);
            return preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    //根据宠物名删除信息
    public static int deleteZoon(String name){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Zoon zoon = null;
        zoon = ZoonDao.name_inquire(name);
        if (zoon == null)
            return 2;
        else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system", "root", "123456");//防止出现潜在的空指针异常
                String h = "delete from zoon where name=?";
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

    //修改
    public static int update(int id,String name,int type_id,String sex,String birthday,int unit){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system", "root", "123456");//防止出现潜在的空指针异常
            String s = "update zoon set name = ?,type_id = ?,sex = ?,birthday = ?,unit = ? where id = ? ";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,type_id);
            preparedStatement.setString(3,sex);
            preparedStatement.setString(4,birthday);
            preparedStatement.setInt(5,unit);
            preparedStatement.setInt(6, id);
            int b = preparedStatement.executeUpdate();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
