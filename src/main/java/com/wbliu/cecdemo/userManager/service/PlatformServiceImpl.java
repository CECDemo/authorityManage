package com.wbliu.cecdemo.userManager.service;

import com.wbliu.cecdemo.userManager.dao.PlatforminfoDao;
import com.wbliu.cecdemo.userManager.pojo.Platforminfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-21 16:48
 **/

@Service(value = "platformService")
public class PlatformServiceImpl  implements  PlatformService{

    @Autowired
    private PlatforminfoDao  platformDao;



    @Override
    public List<Platforminfo> getAllPlatformInfo() {
        return platformDao.getAllPlatformInfo();
    }
}
