package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.common.mysql.MySQLConnectionBean;
import com.wbliu.cecdemo.userManager.pojo.DataSetBean;
import com.wbliu.cecdemo.userManager.pojo.Users;
import com.wbliu.cecdemo.userManager.property.Global;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-27 16:19
 **/

@Repository(value="dataSetDao")
public class DataSetDaoImpl implements DataSetDao {

    @Override
    public List<DataSetBean> selectByPlatform(String platformMark) {
        List<DataSetBean> dataSetBeanList = new ArrayList<>();

        String queryStr = "SELECT dataset_code,dataset_name from  analysisplatform.platform_dataset WHERE platform='"+platformMark+"'";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryStr);

                while (resultSet.next()){
                 dataSetBeanList.add(new DataSetBean(resultSet.getString(1),resultSet.getString(2))) ;
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return dataSetBeanList;
    }
}
