package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.common.mysql.MySQLConnectionBean;
import com.wbliu.cecdemo.userManager.pojo.CallColumnInfoBean;
import com.wbliu.cecdemo.userManager.property.Global;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-27 16:19
 **/

@Repository(value="callColumnInfoDao")
public class CallColumnInfoDaoImpl implements CallColumnInfoDao {

    @Override
    public int selectCountByDateSet(String dataSetCode) {

        int count = 0;
        String queryStr = "SELECT COUNT(*) from analysisplatform.callcolumninfo WHERE showsensitiveinfo =1 AND dataset = '"+dataSetCode+"'";
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
    public List<CallColumnInfoBean> selectCallColumnInfoByDateSet(String dataSetCode) {
        String queryStr = "SELECT id,displaylevel,showsensitiveinfo,dataset from analysisplatform.callcolumninfo WHERE showsensitiveinfo = 1 and dataset = '"+dataSetCode+"'";
        return getCallColumnInfoBeanListByQueryStr(queryStr);
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
        return getCallColumnInfoBeanListByQueryStr(queryStr);
    }

    @Override
    public int selectCountByDateSetAndRoleName(String dataSetCode, String roleName) {
        String queryStr = "SELECT COUNT(*) from analysisplatform.callcolumninfo WHERE showsensitiveinfo =1 AND dataset = '"+dataSetCode+"' AND  displaylevel LIKE  '%"+roleName+",%'";
        return  getCount(queryStr);
    }

    @Override
    public List<CallColumnInfoBean> selectByDateSetAndRoleName(String roleName, String dataSetCode) {
        String queryStr = "SELECT id,displaylevel,showsensitiveinfo,dataset from analysisplatform.callcolumninfo WHERE showsensitiveinfo =1 and displaylevel LIKE '%"+roleName+",%' and dataset ='"+dataSetCode+"'";
        return getCallColumnInfoBeanListByQueryStr(queryStr);
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


    private List<CallColumnInfoBean>  getCallColumnInfoBeanListByQueryStr(String queryStr) {
        List<CallColumnInfoBean> callColumnInfoBeanList = new ArrayList<>();
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStr);

            while (resultSet.next()){
                CallColumnInfoBean callColumnInfoBean = new CallColumnInfoBean();

                callColumnInfoBean.setId(resultSet.getInt(1));
                callColumnInfoBean.setDisplaylevel(resultSet.getString(2));
                callColumnInfoBean.setShowsensitiveinfo(resultSet.getInt(3));
                callColumnInfoBean.setDataset(resultSet.getString(4));

                callColumnInfoBeanList.add(callColumnInfoBean);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("["+e.getMessage()+"]");
        }
        return  callColumnInfoBeanList;
    }


}
