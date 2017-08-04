package com.wbliu.cecdemo.userManager.dao;

import com.mysql.jdbc.MySQLConnection;
import com.wbliu.cecdemo.userManager.common.mysql.MySQLConnectionBean;
import com.wbliu.cecdemo.userManager.common.tools.HandleString;
import com.wbliu.cecdemo.userManager.dto.RoleDTO;
import com.wbliu.cecdemo.userManager.pojo.Roles;
import com.wbliu.cecdemo.userManager.pojo.RolesExample;
import com.wbliu.cecdemo.userManager.property.Global;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wbliu
 * @create 2017-03-27 16:36
 **/

@Repository(value = "roleDao")
public class RolesDaoImpl implements RolesDao {

/*
    @Override
    public int countByExample(RolesExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(RolesExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Roles record) {
        return 0;
    }

    @Override
    public int insertSelective(Roles record) {
        return 0;
    }

    @Override
    public List<Roles> selectByExample(RolesExample example) {
        return null;
    }

    @Override
    public Roles selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(@Param("record") Roles record, @Param("example") RolesExample example) {
        return 0;
    }

    @Override
    public int updateByExample(@Param("record") Roles record, @Param("example") RolesExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Roles record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Roles record) {
        return 0;
    }
*/

    @Override
    public List<Roles> selectByPlatformMark(String platformMark) {
     List<Roles> rolesList = new ArrayList<>();
     String queryStr = "select id,rolesname,description,rolelevel,downloadlevel,platform_mark,roleComment  from roles where rolesname not in('ROLE_ADMIN_SUPER_ZH','ROLE_ADMIN_SUPER_CARD')  and  platform_mark = '"+platformMark+"'";
     Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);

        handleResultSet(rolesList, queryStr, connection);

        return rolesList;
    }



    @Override
    public List<Roles> selectByRoleNameSet(Set<String> roleNameSet) {
        List<Roles> rolesList = new ArrayList<>();
        String roleNameStr = "";

        for (String roleName : roleNameSet){
            roleNameStr += "'"+roleName+"',";
        }

        roleNameStr = HandleString.getInstance().deleteLastComma(roleNameStr);

        String queryStr = "select id,rolesname,description,rolelevel,downloadlevel,platform_mark ,roleComment from roles where  rolesname IN ("+roleNameStr+")";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);

        handleResultSet(rolesList, queryStr, connection);

        return rolesList;
    }

    @Override
    public int deleteRoleByRoleName(String roleName) {
        String deleteStr = "DELETE FROM loginsecurity.roles WHERE  rolesname = '"+roleName+"'";
        return deleteRole(deleteStr);
}

    @Override
    public int deleteRoleByRoleNameAndPlatformMark(String roleName, String platformMark) {
        String deleteStr = "DELETE FROM loginsecurity.roles WHERE  rolesname = '"+roleName+"' AND platform_mark ='"+platformMark+"'";
        return deleteRole(deleteStr);
    }

    private int deleteRole(String deleteStr) {
        int count = 0;
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
    public String selectNextRoleId() {

        String queryStr = "select MAX(id)+1 FROM loginsecurity.roles";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);

        String nextRoleId ="";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStr);
        while(resultSet.next()){
            nextRoleId = resultSet.getString(1);
        }

        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nextRoleId;
    }

    @Override
    public Roles selectByRoleDescriptionAndPlatformMark(String description, String platformMark) {
        List<Roles> rolesList = new ArrayList<>();
        String queryStr = "select id,rolesname,description,rolelevel,downloadlevel,platform_mark,roleComment  from roles where description = '"+description+"' and platform_mark = '"+platformMark+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);

        handleResultSet(rolesList, queryStr, connection);

        if(rolesList == null || rolesList.size()!=1){
            return  null;
        }

        return rolesList.get(0);
    }

    @Override
    public int insert(Roles role) {

        int count = 0;
        String insertStr = "INSERT INTO loginsecurity.roles (rolesname,description,rolelevel,downloadlevel,platform_mark,roleComment) VALUES (?,?,?,?,?,?)";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        try {
            PreparedStatement statement = connection.prepareStatement(insertStr);
            statement.setString(1,role.getRolesname());
            statement.setString(2,role.getDescription());
            statement.setInt(3,role.getRolelevel());
            statement.setInt(4,role.getDownloadlevel());
            statement.setString(5,role.getPlatformMark());
            statement.setString(6,role.getRoleComment());

            count = statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Roles selectById(Integer id) {
        List<Roles> rolesList = new ArrayList<>();
        String queryStr = "select id,rolesname,description,rolelevel,downloadlevel,platform_mark,roleComment  from roles where id = "+id;
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);

        handleResultSet(rolesList, queryStr, connection);

        if(rolesList == null || rolesList.size()!=1){
            return  null;
        }

        return rolesList.get(0);
    }

    @Override
    public int updateRoleById(RoleDTO roleDto) {

        int count = 0;
        String updateStr = "UPDATE loginsecurity.roles SET description = ?, roleComment = ?, downloadlevel = ? ,platform_mark = ?  WHERE  id = ?";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        try {
            PreparedStatement statement = connection.prepareStatement(updateStr);
            statement.setString(1,roleDto.getDescription());
            statement.setString(2,roleDto.getRoleComment());
            statement.setInt(3,roleDto.getDownloadlevel());
            statement.setString(4,roleDto.getPlatformMark());
            statement.setInt(5,roleDto.getId());

            count = statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<String> selectAdminRoleNameFromRolesDB() {
        String queryStr = "select rolesname from loginsecurity.roles where description like '%管理员%' and  description  not like '%超级%'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);

        List<String> roleNameList = new ArrayList<>();
        if(connection !=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryStr);

                while (resultSet.next()){
                    roleNameList.add(resultSet.getString(1));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

         return roleNameList;
    }

    @Override
    public List<Roles> getRoleListByUserName(String username) {
        return null;
    }

    @Override
    public Set<String> selectRoleNameByPlatformMark(String platformMark) {
        Set<String> roleNameSet = new HashSet<>();
        String queryStr = "select rolesname  from loginsecurity.roles where platform_mark = '"+platformMark+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStr);

            while (resultSet.next()){
                roleNameSet.add(resultSet.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("["+e.getMessage()+"]");
        }


        return roleNameSet;
    }


    private void handleResultSet(List<Roles> rolesList, String queryStr, Connection connection) {
        if(connection !=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryStr);

                Roles roles  = null;
                while (resultSet.next()){
                    roles = new Roles();
                    roles.setVaules(resultSet);

                    rolesList.add(roles);
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }



        }
    }

}
