package com.wbliu.cecdemo.userManager.common.mysql;

import com.wbliu.cecdemo.userManager.property.Global;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author wbliu
 * @create 2017-03-27 23:26
 **/


public class MySQLConnectionBean {

    private  static  MySQLConnectionBean mySQLConnectionBean = null;
    private  MySQLConnectionBean(){}

    public static  MySQLConnectionBean getInstance(){
        if(mySQLConnectionBean == null){
            mySQLConnectionBean = new MySQLConnectionBean();
        }
        return  mySQLConnectionBean;
    }

    public Connection getDefaultConnection(String inputDataBaseName){
        Connection connection = null;
        try {
            Class.forName(Global.DriverClassName);
            String connectString = Global.DatabaseHost + inputDataBaseName;
            connection = DriverManager.getConnection(connectString, Global.DatabaseUser, Global.DatabasePassword);
            //System.out.println("Connect Mysql Success");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Connect Mysql Error, " + e.getMessage());
        }
        return connection;
    }

}
