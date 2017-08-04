package com.wbliu.cecdemo.userManager.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Roles {
    private Integer id;

    private String rolesname;

    private String description;

    private String roleComment;

    private Integer rolelevel;

    private Integer downloadlevel;

    private String platformMark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolesname() {
        return rolesname;
    }

    public void setRolesname(String rolesname) {
        this.rolesname = rolesname == null ? null : rolesname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getRolelevel() {
        return rolelevel;
    }

    public void setRolelevel(Integer rolelevel) {
        this.rolelevel = rolelevel;
    }

    public Integer getDownloadlevel() {
        return downloadlevel;
    }

    public void setDownloadlevel(Integer downloadlevel) {
        this.downloadlevel = downloadlevel;
    }

    public String getPlatformMark() {
        return platformMark;
    }

    public void setPlatformMark(String platformMark) {
        this.platformMark = platformMark == null ? null : platformMark.trim();
    }

    public String getRoleComment() {
        return roleComment;
    }

    public void setRoleComment(String roleComment) {
        this.roleComment = roleComment;
    }

    public void setVaules(ResultSet resultSet) {
       if (resultSet !=null){
           try {
                this.id = resultSet.getInt("id");
                this.rolesname = resultSet.getString("rolesname");
                this.description = resultSet.getString("description");
                this.rolelevel = resultSet.getInt("rolelevel");
                this.downloadlevel  = resultSet.getInt("downloadlevel");
                this.platformMark  = resultSet.getString("platform_Mark");
                this.roleComment  =resultSet.getString("roleComment");
           } catch (SQLException e) {
               e.printStackTrace();
           }

       }


    }
}