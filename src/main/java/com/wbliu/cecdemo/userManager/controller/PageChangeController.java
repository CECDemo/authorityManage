package com.wbliu.cecdemo.userManager.controller;

import com.wbliu.cecdemo.userManager.service.RoleService;
import com.wbliu.cecdemo.userManager.service.UsermenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wbliu
 * @create 2017-02-27 16:19
 **/

@Controller
public class PageChangeController {



    @Autowired
    private UsermenuService userMenuService;




    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView  goTologinPage(){
      ModelAndView modelAndView = new ModelAndView("login");
      return  modelAndView;
    }

    @RequestMapping(value = "/userManager",method = RequestMethod.GET)
    public ModelAndView  goToUserManagerPage(){
      ModelAndView modelAndView = new ModelAndView("userManager");

      return  modelAndView;
    }

    @RequestMapping(value = "/roleManager",method = RequestMethod.GET)
    public ModelAndView  goToRoleManagerPage(){
      ModelAndView modelAndView = new ModelAndView("roleManager");

      return  modelAndView;
    }



    /*add by wbliu 20170421    数据库维护程序*/

@RequestMapping(value = "/dbtools/addAdminRoleToWorkflowModule",method = RequestMethod.GET)
    public void  addAdminRoleToWorkflowModule(){


    String message = userMenuService.addAdminRoleToWorkflowModule();

      System.out.printf("addAdminRoleToWorkflowModule'result is " + message);
    }








}
