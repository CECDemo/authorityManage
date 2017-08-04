package com.wbliu.cecdemo.userManager.service;

import com.wbliu.cecdemo.userManager.pojo.Usermenu;

import java.util.List;

/**
 * @author wbliu
 * @create 2017-04-01 16:13
 **/

public interface UsermenuService {


    List<Usermenu>  getUsermenuListByPlatformMark(String platformMark);

    /*add by wbliu 20170421 add AdminRole To WorkflowModule */
    String addAdminRoleToWorkflowModule();
}
