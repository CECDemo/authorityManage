package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.common.mysql.MySQLConnectionBean;
import com.wbliu.cecdemo.userManager.pojo.Authorities;
import com.wbliu.cecdemo.userManager.pojo.AuthoritiesExample;
import com.wbliu.cecdemo.userManager.pojo.Roles;
import com.wbliu.cecdemo.userManager.property.Global;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-27 16:37
 **/

@Repository(value = "authDao")
public class AuthoritiesDaoImpl implements AuthoritiesDao {
    @Override
    public int countByExample(AuthoritiesExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AuthoritiesExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }
    @Override
    public int insertSelective(Authorities record) {
        return 0;
    }

    @Override
    public List<Authorities> selectByExample(AuthoritiesExample example) {
        return null;
    }

    @Override
    public Authorities selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(@Param("record") Authorities record, @Param("example") AuthoritiesExample example) {
        return 0;
    }

    @Override
    public int updateByExample(@Param("record") Authorities record, @Param("example") AuthoritiesExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Authorities record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Authorities record) {
        return 0;
    }





    @Override
    public List<Authorities> selectLikeRoleName(String roleName) {
        List<Authorities> authoritiesList = new ArrayList<>();

        String queryStr = "select id ,username,authority from loginsecurity.authorities where authority LIKE '%"+roleName+",%'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);

        handleResultSet(authoritiesList, queryStr, connection);

        return authoritiesList;
    }

    @Override
    public List<Authorities> selectByUserName(String userName) {
        List<Authorities> authoritiesList = new ArrayList<>();

        String queryStr = "select id ,username,authority from loginsecurity.authorities where username = '"+userName+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);

        handleResultSet(authoritiesList, queryStr, connection);

        return authoritiesList;
    }

    @Override
    public int deleteByUserName(String userName) {
        String deleteStr = "DELETE FROM loginsecurity.authorities WHERE  username = '"+userName+"'";
        return executeUpdateSql(deleteStr);
    }

    private int executeUpdateSql(String deleteStr) {
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        int count = 0;
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
    public boolean updateAuthoritiesByRoleName(String roleName) {
        List<Authorities> authoritiesList = this.selectLikeRoleName(roleName);
        String roleNameStr = "";

        for(Authorities authorities : authoritiesList){

            if(authorities.getAuthority().contains(roleName)){
                roleNameStr = removeRoleNameFromAuthorityStr(authorities.getAuthority(),roleName);
            }else{
                continue;
            }


            if(roleNameStr!=null && roleNameStr.length()==0){
                deleteByUserName(authorities.getUsername());
                return  true;
            }



            if (!updateAuthoritiesByKey(authorities.getId(),roleNameStr)){
                return  false;
            }
        }





        return true;
    }

    @Override
    public  boolean updateAuthoritiesByKey(Integer id, String roleNameStr) {
        int count =0;
        String updateStr = "UPDATE loginsecurity.authorities SET authority ='"+roleNameStr+"' WHERE  id='"+id+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        try {
            PreparedStatement statement = connection.prepareStatement(updateStr);
            count = statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("[ "+e.getMessage()+"]");
        }

        return  count ==1;
    }

    private String removeRoleNameFromAuthorityStr(String authority, String roleName) {

        String roleNameStr ="";

       for(String tempStr : authority.split(",")){
          if(tempStr.equals(roleName)){
                continue;
          }else{
              roleNameStr += tempStr+",";
          }

       }

        int lastIndex = roleNameStr.trim().trim().lastIndexOf(",");

       if(lastIndex !=-1){
          roleNameStr = roleNameStr.substring(0,lastIndex);
       }

        return  roleNameStr;
    }

    @Override
    public int insert(Authorities authorities) {

        int count = 0;
        String insertStr = "insert into loginsecurity.authorities(username,authority) VALUES(?,?)";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.UserDatabaseName);
        try {
            PreparedStatement statement = connection.prepareStatement(insertStr);
            statement.setString(1,authorities.getUsername());
            statement.setString(2,authorities.getAuthority());

            count = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }



    private void handleResultSet(List<Authorities> authoritiesList, String queryStr, Connection connection) {
        if(connection !=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryStr);

                Authorities authorities  = null;
                while (resultSet.next()){
                    authorities = new Authorities();
                    authorities.setVaules(resultSet);
                    authoritiesList.add(authorities);
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
