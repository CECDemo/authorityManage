package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.common.mysql.MySQLConnectionBean;
import com.wbliu.cecdemo.userManager.pojo.CallColumnInfoBean;
import com.wbliu.cecdemo.userManager.pojo.SetCallColumnInfoBean;
import com.wbliu.cecdemo.userManager.property.Global;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-27 16:19
 **/

@Repository(value="setCallColumnInfoDao")
public class SetCallColumnInfoDaoImpl implements SetCallColumnInfoDao {

    @Override
    public int selectCountByDateSet(String dataSetCode) {

        int count = 0;
        String queryStr = "SELECT COUNT(*) from analysisplatform.set_callcolumninfo WHERE show_sensitiveinfo =1 AND dataset = '"+dataSetCode+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStr);

            while (resultSet.next()){
              count = resultSet.getInt(1);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  count;
    }

    @Override
    public List<CallColumnInfoBean> selectCallColumnInfoByDateSet(String s) {
        return null;
    }


    @Override
    public int updateCallColumnInfo(CallColumnInfoBean callColumnInfoBean) {
        String updateStr = "UPDATE analysisplatform.callcolumninfo SET displaylevel =? WHERE showsensitiveinfo =1 and id =?";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);
      int coutn = 0;
        try {
            PreparedStatement  preparedStatement = connection.prepareStatement(updateStr);

            preparedStatement.setString(1,callColumnInfoBean.getDisplaylevel());
            preparedStatement.setInt(2,callColumnInfoBean.getId());

            coutn = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("["+e.getMessage()+"]");
        }

        return  coutn;
    }

    @Override
    public List<CallColumnInfoBean> selectCallColumnInfoByRoleName(String roleName) {
        String queryStr = "SELECT id,displaylevel,showsensitiveinfo,dataset from analysisplatform.callcolumninfo WHERE showsensitiveinfo =1 and displaylevel LIKE '%"+roleName+",%'";
        return null;//getCallColumnInfoBeanListByQueryStr(queryStr);
    }

    @Override
    public int selectCountByDateSetAndRoleName(int id, String dataSetCode, String roleName) {
        String queryStr = "SELECT COUNT(*) from analysisplatform.set_callcolumn_role WHERE column_id ="+id+" and role ='"+roleName+"' and dataset ='"+dataSetCode+"'";
        return  getCount(queryStr);
    }

    @Override
    public List<CallColumnInfoBean> selectByDateSetAndRoleName(String roleName, String dataSetCode) {
        String queryStr = "SELECT id,displaylevel,show_sensitiveinfo,dataset from analysisplatform.set_callcolumninfo WHERE showsensitiveinfo =1 and displaylevel LIKE '%"+roleName+",%' and dataset ='"+dataSetCode+"'";

        return null;//getCallColumnInfoBeanListByQueryStr(queryStr);
    }



    @Override
    public List<SetCallColumnInfoBean> selectSetCallColumnInfoByDateSet(String dataSetCode) {

        String queryStr = "SELECT id,show_sensitiveinfo,dataset from analysisplatform.set_callcolumninfo WHERE show_sensitiveinfo = 1 and dataset = '"+dataSetCode+"'";
        return getCallColumnInfoBeanListByQueryStr(queryStr);
    }

    @Override
    public int selectCountByDateSetAndRoleName(String dataSetCode, String roleName) {
        String queryStr = "SELECT COUNT(*) from analysisplatform.set_callcolumn_role WHERE role ='"+roleName+"' and dataset ='"+dataSetCode+"'";
        return  getCount(queryStr);
    }


    @Override
    public boolean insertRoleNameToSetCall(int id, String rolesname, String dataSetCode) {
        int count =0;
        String insertStr = "INSERT INTO analysisplatform.set_callcolumn_role (column_id,role,dataset)VALUES(?,?,?)";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertStr);
             preparedStatement.setInt(1,id);
             preparedStatement.setString(2,rolesname);
             preparedStatement.setString(3,dataSetCode);
             count =preparedStatement.executeUpdate();

             connection.close();
        } catch (SQLException e) {
            System.out.println("["+e.getMessage()+"]");
        }


        return count ==1;
    }

    @Override
    public boolean deleteRoleNameSetCall(String rolesname, String dataSetCode) {
        String deleteStr = "DELETE FROM analysisplatform.set_callcolumn_role WHERE role =? AND dataset =?";

       int count = 0;
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteStr);
            preparedStatement.setString(1,rolesname);
            preparedStatement.setString(2,dataSetCode);
            count =preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("["+e.getMessage()+"]");
        }


        return count ==1;
    }

    private int getCount(String queryStr) {
        int count = 0;
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStr);

            while (resultSet.next()){
                count = resultSet.getInt(1);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    private List<SetCallColumnInfoBean>  getCallColumnInfoBeanListByQueryStr(String queryStr) {
        List<SetCallColumnInfoBean> setCallColumnInfoBeanList = new ArrayList<>();
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStr);

            while (resultSet.next()){
                SetCallColumnInfoBean setCallColumnInfoBean = new SetCallColumnInfoBean();

                setCallColumnInfoBean.setId(resultSet.getInt(1));
                setCallColumnInfoBean.setShow_sensitiveinfo(resultSet.getInt(2));
                setCallColumnInfoBean.setDataset(resultSet.getString(3));

                setCallColumnInfoBeanList.add(setCallColumnInfoBean);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("["+e.getMessage()+"]");
        }
        return  setCallColumnInfoBeanList;
    }

}
