package com.wbliu.cecdemo.userManager.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    private Integer id;

    private String uuid;

    private String username;

    private String password;

    private Byte enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }



    public void setVaules(ResultSet resultSet) {
                if(resultSet !=null){
                    try {
                         this.id = resultSet.getInt("id");
                         this.uuid = resultSet.getString("uuid");
                         this.username = resultSet.getString("username");
                         this.password = resultSet.getString("password");
                         this.enabled = resultSet.getByte("enabled");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
    }
}