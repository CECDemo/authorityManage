package com.wbliu.cecdemo.userManager.pojo;

/**
 * @author wbliu
 * @create 2017-07-11 11:19
 **/


public class CallColumnInfoBean {
    int id;
    int showsensitiveinfo; //1,敏感 (不展示); 0 非敏感（展示）
    String displaylevel;
    String dataset;



    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getDisplaylevel() {
        return displaylevel;
    }

    public void setDisplaylevel(String displaylevel) {
        this.displaylevel = displaylevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShowsensitiveinfo() {
        return showsensitiveinfo;
    }

    public void setShowsensitiveinfo(int showsensitiveinfo) {
        this.showsensitiveinfo = showsensitiveinfo;
    }

}
