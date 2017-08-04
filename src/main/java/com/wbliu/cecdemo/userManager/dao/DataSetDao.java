package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.pojo.DataSetBean;
import com.wbliu.cecdemo.userManager.pojo.Users;

import java.util.List;


public interface DataSetDao {

    List<DataSetBean>  selectByPlatform(String platformMark);
}