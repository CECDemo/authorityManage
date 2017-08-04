package com.wbliu.cecdemo.userManager.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Platforminfo {
    private Integer id;

    private String shortName;

    private String describe;

    private String platformUrl;

    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getPlatformUrl() {
        return platformUrl;
    }

    public void setPlatformUrl(String platformUrl) {
        this.platformUrl = platformUrl == null ? null : platformUrl.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    public void setVaules(ResultSet resultSet) {

        if (resultSet !=null){
            try {
                this.id = resultSet.getInt("id");
                this.shortName = resultSet.getString("short_name");
                this.describe = resultSet.getString("describe");
                this.platformUrl = resultSet.getString("platform_url");
                this.level = resultSet.getInt("level");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}