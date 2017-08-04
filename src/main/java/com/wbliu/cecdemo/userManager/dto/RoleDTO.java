package com.wbliu.cecdemo.userManager.dto;

import com.wbliu.cecdemo.userManager.pojo.Platforminfo;
import com.wbliu.cecdemo.userManager.pojo.Roles;
import com.wbliu.cecdemo.userManager.pojo.Usermenu;

import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-01 19:01
 **/


public class RoleDTO {
    private Integer id;

    private String rolesname;

    private String description;
    private String roleComment;

    private Integer rolelevel;

    private Integer downloadlevel;

 private String platformMark;
 private  Platforminfo platforminfo;
 private List<Roles> rolesList;

 private List<Usermenu> userMenuList;
 private String usermentStr;
 private String originalUsermentStr;
 private String originalPlatformMark;

  List<String> dataSetCodeAndFlagList;


    public List<String> getDataSetCodeAndFlagList() {
        return dataSetCodeAndFlagList;
    }

    public void setDataSetCodeAndFlagList(List<String> dataSetCodeAndFlagList) {
        this.dataSetCodeAndFlagList = dataSetCodeAndFlagList;
    }

    public String getPlatformMark() {
        return platformMark;
    }

    public void setPlatformMark(String platformMark) {
        this.platformMark = platformMark;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    public Platforminfo getPlatforminfo() {
        return platforminfo;
    }

    public void setPlatforminfo(Platforminfo platforminfo) {
        this.platforminfo = platforminfo;
    }

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
        this.rolesname = rolesname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Usermenu> getUserMenuList() {
        return userMenuList;
    }

    public void setUserMenuList(List<Usermenu> userMenuList) {
        this.userMenuList = userMenuList;
    }

    public String getRoleComment() {
        return roleComment;
    }

    public void setRoleComment(String roleComment) {
        this.roleComment = roleComment;
    }

    public String getUsermentStr() {
        return usermentStr;
    }

    public void setUsermentStr(String usermentStr) {
        this.usermentStr = usermentStr;
    }

    public String getOriginalUsermentStr() {
        return originalUsermentStr;
    }

    public void setOriginalUsermentStr(String originalUsermentStr) {
        this.originalUsermentStr = originalUsermentStr;
    }

    public String getOriginalPlatformMark() {
        return originalPlatformMark;
    }

    public void setOriginalPlatformMark(String originalPlatformMark) {
        this.originalPlatformMark = originalPlatformMark;
    }
}
