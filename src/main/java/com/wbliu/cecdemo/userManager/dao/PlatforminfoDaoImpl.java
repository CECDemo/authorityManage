package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.common.mysql.MySQLConnectionBean;
import com.wbliu.cecdemo.userManager.pojo.Platforminfo;
import com.wbliu.cecdemo.userManager.pojo.PlatforminfoExample;
import com.wbliu.cecdemo.userManager.pojo.Roles;
import com.wbliu.cecdemo.userManager.property.Global;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-28 12:27
 **/

@Repository(value = "platformDao")
public class PlatforminfoDaoImpl  implements  PlatforminfoDao{


    @Override
    public List<Platforminfo> getAllPlatformInfo() {
        List<Platforminfo> platforminfoList = new ArrayList<>();
        String queryStr = "select p.id ,p.short_name,p.describe,p.platform_url,p.level from analysisplatform.platforminfo p ORDER BY p.level";
        Connection connection = MySQLConnectionBean.getInstance().getDefaultConnection(Global.DatabaseName);

        handleResultSet(platforminfoList, queryStr, connection);

        return platforminfoList;
    }

    private void handleResultSet(List<Platforminfo> platforminfoList, String queryStr, Connection connection) {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryStr);

                Platforminfo  platforminfo = null;
                while (resultSet.next()) {
                    platforminfo = new Platforminfo();
                    platforminfo.setVaules(resultSet);

                    platforminfoList.add(platforminfo);
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
