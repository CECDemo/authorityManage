package com.wbliu.cecdemo.userManager.common.tools;

/**
 * @author wbliu
 * @create 2017-03-28 10:20
 **/


public class HandleString {

    private static HandleString handleString = null;
    private  HandleString(){}

    public static HandleString getInstance(){
        if (handleString ==null){
            handleString = new HandleString();

        }
        return handleString;
    }


    public String deleteLastComma(String inputStr){
        int lastCommaIndex = inputStr.lastIndexOf(",");

        if (lastCommaIndex >=0){
            inputStr = inputStr.substring(0,lastCommaIndex).trim();
        }
        return inputStr;
    }


}
