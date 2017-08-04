package com.wbliu.cecdemo.userManager.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Authorities {
    private Integer id;

    private String username;

    private String authority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }



    public void setVaules(ResultSet resultSet) {
       if (resultSet !=null){
           try {
                this.id = resultSet.getInt("id");
                this.username = resultSet.getString("username");
                this.authority = resultSet.getString("authority");

           } catch (SQLException e) {
               e.printStackTrace();
           }


       }

    }
}