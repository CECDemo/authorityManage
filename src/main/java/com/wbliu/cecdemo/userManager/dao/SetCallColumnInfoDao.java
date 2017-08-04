package com.wbliu.cecdemo.userManager.dao;


import com.wbliu.cecdemo.userManager.pojo.CallColumnInfoBean;
import com.wbliu.cecdemo.userManager.pojo.SetCallColumnInfoBean;

import java.util.List;

public interface SetCallColumnInfoDao {

    int   selectCountByDateSet(String dataSetCode);

    List<CallColumnInfoBean>  selectCallColumnInfoByDateSet(String s);

    int updateCallColumnInfo(CallColumnInfoBean callColumnInfoBean);

    List<CallColumnInfoBean>  selectCallColumnInfoByRoleName(String roleName);


    List<CallColumnInfoBean>  selectByDateSetAndRoleName(String rolesname, String dataSetCode);


    int  selectCountByDateSetAndRoleName(int id, String dataSetCode, String roleName);
    List<SetCallColumnInfoBean>  selectSetCallColumnInfoByDateSet(String dataSetCode);

    int  selectCountByDateSetAndRoleName(String dataSetCode, String roleName);

    boolean  insertRoleNameToSetCall(int id, String rolesname, String dataSetCode);

    boolean deleteRoleNameSetCall(String rolesname, String dataSetCode);
}