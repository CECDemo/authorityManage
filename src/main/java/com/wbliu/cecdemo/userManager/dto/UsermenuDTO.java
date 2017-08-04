package com.wbliu.cecdemo.userManager.dto;

import com.wbliu.cecdemo.userManager.pojo.Platforminfo;

public class UsermenuDTO {
    private Integer id;

    private String menudescription;

    private String menuurl;

    private String menurole;

    private String menuname;

    private Integer menuorder;

    private byte isAdmin;

    private String platformMark;

private Platforminfo platforminfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenudescription() {
        return menudescription;
    }

    public void setMenudescription(String menudescription) {
        this.menudescription = menudescription == null ? null : menudescription.trim();
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
    }

    public String getMenurole() {
        return menurole;
    }

    public void setMenurole(String menurole) {
        this.menurole = menurole == null ? null : menurole.trim();
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public Integer getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(Integer menuorder) {
        this.menuorder = menuorder;
    }

    public byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPlatformMark() {
        return platformMark;
    }

    public void setPlatformMark(String platformMark) {
        this.platformMark = platformMark == null ? null : platformMark.trim();
    }

    public Platforminfo getPlatforminfo() {
        return platforminfo;
    }

    public void setPlatforminfo(Platforminfo platforminfo) {
        this.platforminfo = platforminfo;
    }
}