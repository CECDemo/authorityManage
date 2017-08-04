package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.common.mysql.MySQLConnectionBean;
import com.wbliu.cecdemo.userManager.dto.UserDTO;
import com.wbliu.cecdemo.userManager.pojo.Roles;
import com.wbliu.cecdemo.userManager.pojo.Usermenu;
import com.wbliu.cecdemo.userManager.pojo.Users;
import com.wbliu.cecdemo.userManager.property.Global;
import org.springframework.stereotype.Repository;

import javax.security.auth.login.AccountException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-27 16:19
 **/

@Repository(value="userDao")
public class UsersDaoImpl implements UsersDao {

    @Override
    public Users selectUserByUserNameAndPassword(String userName, String password) {
        Users users = null;


        String queryStr = "select id ,uuid,username,password,enabled from loginsecurity.users where username ='"+userName+"' AND password ='"+password+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        if(connection !=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryStr);

                while (resultSet.next()){
                    users = new Users();
                    users.setVaules(resultSet);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return users;
    }




    @Override
    public List<Users> selectByUserName(String userName) {
        List<Users> usersList = new ArrayList<>();

        String queryStr = "select id ,uuid,username,password,enabled from loginsecurity.users where username ='"+userName+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        if(connection !=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryStr);

                Users users  = null;
                while (resultSet.next()){
                    users = new Users();
                    users.setVaules(resultSet);

                    usersList.add(users);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return usersList;
    }

    @Override
    public int insert(Users users) {

        int count = 0;
        String insertStr = "insert into  loginsecurity.users (uuid,username,password,enabled) VALUES (?,?,?,?)";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        try {
            PreparedStatement statement = connection.prepareStatement(insertStr);
             statement.setString(1,users.getUuid());
             statement.setString(2,users.getUsername());
             statement.setString(3,users.getPassword());
             statement.setByte(4,users.getEnabled());

             count = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int updateUserPassword(String username, String password, String newPassword1) {
        int count = 0;
        String insertStr = "update loginsecurity.users user  SET user.password = ? where user.username = ? and  user.password = ?";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        try {
            PreparedStatement statement = connection.prepareStatement(insertStr);
            statement.setString(1,newPassword1);
            statement.setString(2,username);
            statement.setString(3,password);
            count = statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return count;
    }

    @Override
    public int deleteByUserName(String userName) {
        int count = 0;
        String deleteStr = "DELETE FROM loginsecurity.users WHERE  username = '"+userName+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        try {
            Statement statement = connection.createStatement();
            count = statement.executeUpdate(deleteStr);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Users selectByUUID(String uuid) {
        String queryStr = "select id ,uuid,username,password,enabled from loginsecurity.users where uuid ='"+uuid+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        Users users  = null;

        if(connection !=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryStr);

                while (resultSet.next()){
                    users = new Users();
                    users.setVaules(resultSet);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return users;
    }

    @Override
    public int updateUserByUUID(Users users1) {
        int count = 0;
        String updateStr = "update loginsecurity.users user  SET user.password = ? , user.username = ?  where   user.uuid = ?";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        try {
            PreparedStatement statement = connection.prepareStatement(updateStr);
            statement.setString(1,users1.getPassword());
            statement.setString(2,users1.getUsername());
            statement.setString(3,users1.getUuid());
            count = statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("["+e.getMessage()+"]");
        }

        return count;
    }

    @Override
    public List<Users> selectUserWithNoRole() {
        String queryStr = "SELECT id ,uuid,username,password,enabled from loginsecurity.users\n" +
                "WHERE  username NOT IN ( SELECT DISTINCT (username) FROM loginsecurity.authorities) ;\n";
        return getUserList(queryStr);
    }

    @Override
    public List<Users> selectUsers() {
        String queryStr = "SELECT id ,uuid,username,password,enabled from loginsecurity.users";
        return getUserList(queryStr);
    }

    private List<Users> getUserList(String queryStr) {
        List<Users> usersList = new ArrayList<>();
        Users users;
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStr);

            while (resultSet.next()){
                users = new Users();
                users.setVaules(resultSet);
                usersList.add(users);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }


}
