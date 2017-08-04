package com.wbliu.cecdemo.userManager.dao;


import com.wbliu.cecdemo.userManager.pojo.CallColumnInfoBean;

import java.util.List;

public interface CallColumnInfoDao {

    int   selectCountByDateSet(String dataSetCode);

    List<CallColumnInfoBean>  selectCallColumnInfoByDateSet(String s);

    int updateCallColumnInfo(CallColumnInfoBean callColumnInfoBean);

    List<CallColumnInfoBean>  selectCallColumnInfoByRoleName(String roleName);

    int  selectCountByDateSetAndRoleName(String dataSetCode, String roleName);

    List<CallColumnInfoBean>  selectByDateSetAndRoleName(String rolesname, String dataSetCode);

}