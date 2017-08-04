package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.common.mysql.MySQLConnectionBean;
import com.wbliu.cecdemo.userManager.common.tools.HandleString;
import com.wbliu.cecdemo.userManager.pojo.Usermenu;
import com.wbliu.cecdemo.userManager.pojo.UsermenuExample;
import com.wbliu.cecdemo.userManager.property.Global;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-30 14:27
 **/


@Repository(value = "userMenuDao")
public class UserMenuDaoImpl implements UserMenuDao {
    @Override
    public int countByExample(UsermenuExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(UsermenuExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Usermenu record) {
        return 0;
    }

    @Override
    public int insertSelective(Usermenu record) {
        return 0;
    }

    @Override
    public List<Usermenu> selectByExample(UsermenuExample example) {
        return null;
    }

    @Override
    public Usermenu selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(@Param("record") Usermenu record, @Param("example") UsermenuExample example) {
        return 0;
    }

    @Override
    public int updateByExample(@Param("record") Usermenu record, @Param("example") UsermenuExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Usermenu record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Usermenu record) {
        return 0;
    }

    @Override
    public List<Usermenu> selectByRoleName(String rolesname) {
        List<Usermenu> usermenuList = new ArrayList<>();
        String queryStr = "select id ,menudescription,menuurl,menurole,menuname,menuorder,is_admin,platform_mark from analysisplatform.usermenu where menurole like '%" + rolesname + ",%'";

        executeQueryAndHandleResult(usermenuList, queryStr);
        return usermenuList;
    }

    @Override
    public List<Usermenu> selectByRoleNameAndPlatfomrMark(String roleName, String platformMark) {
        List<Usermenu> usermenuList = new ArrayList<>();
        String queryStr = "select id ,menudescription,menuurl,menurole,menuname,menuorder,is_admin,platform_mark from analysisplatform.usermenu where menurole like '%" + roleName + ",%' AND  platform_mark like '%" + platformMark + "%'";
        executeQueryAndHandleResult(usermenuList, queryStr);
        return usermenuList;
    }


    @Override
    public boolean deleteRoleNameFromUserMenuByRoleName(String roleName) {

        List<Usermenu> usermenuList = this.selectByRoleName(roleName);

        if (usermenuList == null || usermenuList.size() <= 0) {
            return true;
        }

        String newMenuroleStr = "";
        List<String> menuroleList = null;
        int count = 0;
        for (Usermenu usermenu : usermenuList) {

            menuroleList = Arrays.asList(usermenu.getMenurole().split(","));

            menuroleList = new ArrayList<>(menuroleList);

            menuroleList.remove(roleName);

            for (String str : menuroleList) {
                newMenuroleStr += str + ",";
            }

            newMenuroleStr = HandleString.getInstance().deleteLastComma(newMenuroleStr);
            int count0 = this.updateRoleNameById(usermenu.getId(), newMenuroleStr);

            count += count0;
        }

        if (count == menuroleList.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRoleNameFromUserMenuByRoleNameAndPlatformMark(String roleName, String platformMark) {
        List<Usermenu> usermenuList = this.selectByRoleName(roleName);

        if (usermenuList == null || usermenuList.size() <= 0) {
            return true;
        }

        String newMenuroleStr = "";
        int count = 0;
        for (Usermenu usermenu : usermenuList) {
            newMenuroleStr = getNewMenuRoleStr(roleName,  usermenu.getMenurole());

            int count0 = this.updateRoleNameById(usermenu.getId(), newMenuroleStr);
            count += count0;
        }

        if (count == usermenuList.size()) {
            return true;
        }

        return false;
    }

    private String getNewMenuRoleStr(String roleName, String newMenuroleTempSwtr) {
        String newMenuroleStr ="";
        List<String> menuroleList = Arrays.asList(newMenuroleTempSwtr.split(","));

        for (String str : menuroleList) {
            if(roleName.equals(str))continue;
            newMenuroleStr += str + ",";
        }
        return newMenuroleStr;
    }


    @Override
    public int updateRoleNameById(Integer id, String newMenuroleStr) {
        int count = 0;
        String insertStr = "update analysisplatform.usermenu set menurole = ? where id = '" + id + "'";
        count = localExecuteUpdate(newMenuroleStr, count, insertStr);
        return count;
    }

    @Override
    public int updateRoleNameByIdAndPlatformMark(Integer id, String newMenuroleStr, String platformMark) {
        int count = 0;
        String updateStr = "update analysisplatform.usermenu set menurole = ? where id = '" + id + "' AND  platform_mark like'%" + platformMark + "%'";
        return localExecuteUpdate(newMenuroleStr, count, updateStr);
    }

    private int localExecuteUpdate(String newMenuroleStr, int count, String insertStr) {
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);
        try {
            PreparedStatement statement = connection.prepareStatement(insertStr);
            statement.setString(1, newMenuroleStr);
            count = statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("["+e.getMessage()+"]");

        }
        return count;
    }


    @Override
    public List<Usermenu> selectByPlatformMark(String platformMark) {
        List<Usermenu> usermenuList = new ArrayList<>();

        String queryStr = "select id ,menudescription,menuurl,menurole,menuname,menuorder,is_admin,platform_mark from analysisplatform.usermenu where is_admin !=0 and platform_mark like '%" + platformMark + "%'";

        executeQueryAndHandleResult(usermenuList, queryStr);
        return usermenuList;
    }

    @Override
    public int updateByMenuDescription(Usermenu usermenu1) {

        int count = 0;
        String updateStr = "update analysisplatform.usermenu set menurole = ?  where  menudescription=?";

        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);
        try {


            if (usermenu1.getPlatformMark() != null) {
                updateStr = "update analysisplatform.usermenu set menurole = ? ,platform_mark = ?  where  menudescription=?";
                PreparedStatement statement = connection.prepareStatement(updateStr);
                statement.setString(1, usermenu1.getMenurole());
                statement.setString(2, usermenu1.getPlatformMark());
                statement.setString(3, usermenu1.getMenudescription());
                count = statement.executeUpdate();

            } else {
                PreparedStatement statement = connection.prepareStatement(updateStr);
                statement.setString(1, usermenu1.getMenurole());
                statement.setString(2, usermenu1.getMenudescription());
                count = statement.executeUpdate();
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public String selectWorkFlowModuleRolesStr() {

        String workflowModuleRoleStr = "";

        String queryStr = "select menurole from analysisplatform.usermenu where menudescription = '功能模块管理'";

        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStr);

            while (resultSet.next()) {
                workflowModuleRoleStr = resultSet.getString(1);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return workflowModuleRoleStr;
    }

    @Override
    public int updateMenuRoleByMenuName(String menuRoleStr, String menuName) {

        int count=0;
        String updateStr = "update analysisplatform.usermenu set menurole = ? where menuname =?";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);

        try {
                PreparedStatement ps = connection.prepareStatement(updateStr);
                ps.setString(1, menuRoleStr);
                ps.setString(2, menuName);

                count = ps.executeUpdate();

                connection.close();
            } catch (SQLException e) {
                System.out.println("[ updateMenuRoleByMenuName () --->" + e.getMessage() + "]");
            }


            return count;
        }

    @Override
    public Usermenu selectByMenuName(String menuName) {
        Usermenu usermenu = null;

        List<Usermenu> usermenuList = new ArrayList<>();

        String queryStr = "select id,menudescription,menuurl,menurole,menuname,menuorder,is_admin,platform_mark from analysisplatform.usermenu where menuname = '" + menuName + "'";
        executeQueryAndHandleResult(usermenuList, queryStr);

        if(usermenuList.size() ==1){
            usermenu = usermenuList.get(0);
        }else if(usermenuList.size() >1){
            System.out.println("[ menuName 不唯一 ,menuName ="+ menuName+"]");
        }else{
            System.out.println("[ usemenu is not exite ]");
        }

        return usermenu;
    }

    private void executeQueryAndHandleResult(List<Usermenu> usermenuList, String queryStr) {
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStr);

            Usermenu usermenu;
            while (resultSet.next()) {
                usermenu = new Usermenu();
                usermenu.setId(resultSet.getInt("id"));
                usermenu.setMenudescription(resultSet.getString("menudescription"));
                usermenu.setMenuurl(resultSet.getString("menuurl"));
                usermenu.setMenurole(resultSet.getString("menurole"));
                usermenu.setMenuname(resultSet.getString("menuname"));
                usermenu.setMenuorder(resultSet.getInt("menuorder"));
                usermenu.setIsAdmin(resultSet.getByte("is_admin"));
                usermenu.setPlatformMark(resultSet.getString("platform_mark"));

                if (usermenu.getMenurole().contains("ROLE_ADMIN_SUPER_CARD") || usermenu.getMenurole().contains("ROLE_ADMIN_SUPER_ZH")) {
                    continue;
                }
                usermenuList.add(usermenu);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("[executeQueryAndHandleResult() : " + e.getMessage() + " ]");
        }
    }

}
