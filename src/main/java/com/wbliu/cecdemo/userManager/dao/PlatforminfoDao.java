package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.pojo.Platforminfo;
import com.wbliu.cecdemo.userManager.pojo.PlatforminfoExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlatforminfoDao {

    List<Platforminfo> getAllPlatformInfo();

}