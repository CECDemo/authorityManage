package com.wbliu.cecdemo.userManager.pojo;

/**
 * @author wbliu
 * @create 2017-07-07 17:18
 **/


public class DataSetBean {
    String  dataSetCode;
    String  dataSetName;
   String displayLevel;

    boolean isDisplay;


    public DataSetBean(String dataSetCode,String dataSetName){
     this.dataSetCode = dataSetCode;
     this.dataSetName =dataSetName;
    }

    public DataSetBean(String dataSetCode,String dataSetName,String displayLevel){
     this.dataSetCode = dataSetCode;
     this.dataSetName =dataSetName;
     this.displayLevel = displayLevel;
    }
    public DataSetBean(String dataSetCode,String dataSetName,boolean isDisplay){
     this.dataSetCode = dataSetCode;
     this.dataSetName =dataSetName;
     this.isDisplay =isDisplay;
    }


    public String getDisplayLevel() {
        return displayLevel;
    }

    public void setDisplayLevel(String displayLevel) {
        this.displayLevel = displayLevel;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    public String getDataSetCode() {
        return dataSetCode;
    }

    public void setDataSetCode(String dataSetCode) {
        this.dataSetCode = dataSetCode;
    }

    public String getDataSetName() {
        return dataSetName;
    }

    public void setDataSetName(String dataSetName) {
        this.dataSetName = dataSetName;
    }
}
