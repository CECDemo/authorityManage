package com.wbliu.cecdemo.userManager.service;

import com.wbliu.cecdemo.userManager.common.tools.HandleString;
import com.wbliu.cecdemo.userManager.dao.RolesDao;
import com.wbliu.cecdemo.userManager.dao.UserMenuDao;
import com.wbliu.cecdemo.userManager.pojo.Usermenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wbliu
 * @create 2017-04-01 16:14
 **/

@Service(value = "userMenuService")
public class UsermenuServiceImpl implements UsermenuService {


    @Autowired
    private UserMenuDao userMenuDao;

    @Autowired
    private RolesDao roleDao;



    @Override
    public List<Usermenu> getUsermenuListByPlatformMark(String platformMark) {
      List<Usermenu> usermenuList = userMenuDao.selectByPlatformMark(platformMark);

      return usermenuList;
    }

    /*add by wbliu 20170421 add AdminRole To WorkflowModule */
    @Override
    public String addAdminRoleToWorkflowModule() {

           List<String> adminRoleNameList = roleDao.selectAdminRoleNameFromRolesDB();

           String  workFlowModuleRolesStr = userMenuDao.selectWorkFlowModuleRolesStr();

           adminRoleNameList.addAll(Arrays.asList(workFlowModuleRolesStr.split(",")));

           Set<String> workFlowModuleRolesSet = new HashSet<>(adminRoleNameList);

           String newWorkFlowModuleRolesStr = "";

           for(String roleName : workFlowModuleRolesSet){
               newWorkFlowModuleRolesStr+=roleName+",";
           }

          newWorkFlowModuleRolesStr =  HandleString.getInstance().deleteLastComma(newWorkFlowModuleRolesStr);


           /*int count = userMenuDao.updateWorkflowModuleRoleStr();

           if(count !=1){
               return "更新失败！";
           }*/


        return "更新成功1";
    }
}
