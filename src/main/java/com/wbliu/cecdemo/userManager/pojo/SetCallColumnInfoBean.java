package com.wbliu.cecdemo.userManager.pojo;

import java.util.List;

/**
 * @author wbliu
 * @create 2017-07-11 11:19
 **/


public class SetCallColumnInfoBean {
    int id;
    int show_sensitiveinfo; //1,敏感 (不展示); 0 非敏感（展示）
    List<String> displaylevelList;
    String dataset;


    public List<String> getDisplaylevelList() {
        return displaylevelList;
    }

    public void setDisplaylevelList(List<String> displaylevelList) {
        this.displaylevelList = displaylevelList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShow_sensitiveinfo() {
        return show_sensitiveinfo;
    }

    public void setShow_sensitiveinfo(int show_sensitiveinfo) {
        this.show_sensitiveinfo = show_sensitiveinfo;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }
}
