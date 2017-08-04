package com.wbliu.cecdemo.userManager.controller;

import com.wbliu.cecdemo.userManager.dto.PlatformDTO;
import com.wbliu.cecdemo.userManager.dto.RoleDTO;
import com.wbliu.cecdemo.userManager.dto.UserDTO;
import com.wbliu.cecdemo.userManager.dto.UsermenuDTO;
import com.wbliu.cecdemo.userManager.pojo.*;
import com.wbliu.cecdemo.userManager.service.PlatformService;
import com.wbliu.cecdemo.userManager.service.RoleService;
import com.wbliu.cecdemo.userManager.service.UserService;
import com.wbliu.cecdemo.userManager.service.UsermenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wbliu.cecdemo.userManager.property.Global.ACCESS_TOKEN;

/**
 * @author wbliu
 * @create 2017-02-28 13:20
 **/

@RestController
@RequestMapping(value = "/users")
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private UsermenuService userMenuService;


    /*判断用户是否存在*/

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(@RequestBody UserDTO userDto){
        System.out.println("-----------login-----------");

        String message = userService.login(userDto);



        Map<String,String> resMap = new HashMap<>();
        resMap.put("message",message);
        return resMap;
    }


    @RequestMapping(value = "/users/logout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getLogoutResult(HttpServletRequest request) {

        System.out.println("-------------logout-------------");
        Map<String,String > messageMap = new HashMap<>();

        boolean getLogoutStatus = userService.logout(request.getHeader(ACCESS_TOKEN));
        if (getLogoutStatus) {
            messageMap.put("message","退出成功");
        } else {
            messageMap.put("message","退出失败");
        }
        return messageMap;
    }


    /*获得所有用户，及每个用户对应的角色*/
    @RequestMapping(value = "/getAllUserInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> getAllUserInfo(){
        System.out.println("[ -------getAllUserInfo--- ]");
        return userService.getAllUserInfo();
    }

    /*根据用户名判断用户是否存在*/
    @RequestMapping(value = "/checkUserByUserName",method = RequestMethod.GET)
    @ResponseBody
    public Users checkUserByUserName(String userName){
        System.out.println("[ -------checkUserByUserName--- ]");
        System.out.println("userName = [" + userName + "]");

        return userService.getUserInfoByUserName(userName);
    }


    /*根据用户uuid查询用户的信息及其对应的角色信息*/
    @RequestMapping(value = "/getUserInfoByuuid",method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getUserInfoByuuid(String uuid){
        System.out.println("-------getUserInfoByuuid-------");
        UserDTO userDto = null;
        userDto = userService.getUserInfoByuuid(uuid);
        return  userDto;
    }

     /*根据uuid更新用户信息*/
     @RequestMapping(value = "/updateUserByuuid",method = RequestMethod.PUT)
     @ResponseBody
     public String updateUserByuuid(String uuid,UserDTO userDto){
        System.out.println("-------updateUserByuuid-------");
        boolean flag = userService.updateUserByuuid(uuid,userDto);
        return flag ? uuid:"error";
     }

     /*新增角色*/
   @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ResponseBody
     public Map<String,String> addRole(@RequestBody RoleDTO roleDto){

       String message = roleService.addRole(roleDto);
       Map<String,String> mesgMap = new HashMap<>();
        mesgMap.put("message",message);
       return  mesgMap;
   }

   @RequestMapping(value = "/modifyRole",method = RequestMethod.POST)
    @ResponseBody
     public Map<String,String> modifyRole(@RequestBody RoleDTO roleDto){
       String message = roleService.modifyRole(roleDto);

       Map<String,String> mesgMap = new HashMap<>();
        mesgMap.put("message",message);
       return  mesgMap;
   }

@RequestMapping(value = "/getUserInfoBySystemFlag")
@ResponseBody
public List<UserDTO> getUserInfoBySystemFlag(String systemFlag){
    System.out.println("[----getUserInfoBySystemFlag-----]");
    System.out.println("systemFlag = [" + systemFlag + "]");

    return  userService.getUserInfoBySystemFlag(systemFlag);
}

@RequestMapping(value = "/getUserInfoList")
@ResponseBody
public List<UserDTO> getUserAndRoleInfo(){
 return  userService.getAllUserInfo();
}





@RequestMapping(value = "/getRoleInfoByPlatformMark")
@ResponseBody
public List<RoleDTO> getRoleInfoByPlatformMark(String platformMark){
    System.out.println("[----getRoleInfoByPlatformMark-----]");
    System.out.println("platformMark = [" + platformMark + "]");

    return  userService.getRoleInfoByPlatformMark(platformMark);
}




    @RequestMapping(value = "/getAllRoles")
    public List<RoleDTO> getAllRoles(){
        List<RoleDTO> rolesDtoList = new ArrayList<RoleDTO>();

        List<Platforminfo> platformList = platformService.getAllPlatformInfo();

        List<Roles> rolesList;
        RoleDTO roleDTO;

        for (Platforminfo platforminfo : platformList){
            rolesList = roleService.getRolesByPlatformMark(platforminfo.getShortName());

            roleDTO = new RoleDTO();
            roleDTO.setPlatformMark(platforminfo.getDescribe());
            roleDTO.setRolesList(rolesList);
            roleDTO.setPlatforminfo(platforminfo);

            rolesDtoList.add(roleDTO);
        }

        return rolesDtoList;
    }

    @RequestMapping(value = "/getAllRoleDescriptionByUserName")
    public List<String> getAllRoleDescriptionByUserName(String userName){
        return roleService.getRoleNamesByUserName(userName);
    }

    
    @RequestMapping(value = "/getUserMenuByPlatformMark")
    public List<UsermenuDTO> getUserMenuByPlatformMark(@RequestParam(value = "platformMark")String platformMark){
        List<UsermenuDTO> usermenuDTOList = new ArrayList<UsermenuDTO>();

        List<Usermenu> usermenuList =userMenuService.getUsermenuListByPlatformMark(platformMark);

         UsermenuDTO usermenuDTO = null;

        for(Usermenu usermenu : usermenuList){
            usermenuDTO = new UsermenuDTO();

            usermenuDTO.setId(usermenu.getId());
            usermenuDTO.setMenudescription(usermenu.getMenudescription());
            usermenuDTO.setMenuurl(usermenu.getMenuurl());
            usermenuDTO.setMenuname(usermenu.getMenuname());
            usermenuDTO.setMenurole(usermenu.getMenurole());
            usermenuDTO.setMenuorder(usermenu.getMenuorder());
            usermenuDTO.setIsAdmin(usermenu.getIsAdmin());
            usermenuDTO.setPlatformMark(usermenu.getPlatformMark());

            usermenuDTOList.add(usermenuDTO);
        }

        return usermenuDTOList;
    }


    @RequestMapping(value = "/getAllPlatformMarkInfo")
    public List<Platforminfo> getAllPlatformMarkInfo(){
        return platformService.getAllPlatformInfo();
    }


    @RequestMapping(value = "/addNewUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addUser(@RequestBody UserDTO userDto){
        String message = userService.addUser(userDto);

        Map<String,String> resMap = new HashMap<>();
        resMap.put("message",message);
        return resMap;
    }

    /*修改用户信息，及其对应的角色*/
    @RequestMapping(value = "/modifyUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> modifyUser(@RequestBody UserDTO userDto){
        String message =userService.modifyUser(userDto);

        Map<String,String> resMap = new HashMap<>();
        resMap.put("message",message);
        return resMap;
    }

    @RequestMapping(value = "/modifyPassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> modifyPassword(@RequestBody UserDTO userDto){
        String message =userService.modifyPassword(userDto);

        Map<String,String> resMap = new HashMap<>();
        resMap.put("message",message);
        return resMap;
    }

    @RequestMapping(value = "/deleteUserByUserName",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> deleteUserByUserName(@RequestParam(value = "userName") String  userName){

       String message = "删除失败";

       if(userService.deleteUserByUserName(userName)){
            message="删除成功";
        }

        Map<String,String> resMap = new HashMap<>();
        resMap.put("message",message);
        return resMap;
    }


    @RequestMapping(value = "/deleteRoleByRoleName",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> deleteRoleByRoleName(@RequestParam(value ="roleName") String  roleName,@RequestParam(value = "platformMark") String platformMark){
       String message = roleService.deleteRoleByRoleName(roleName,platformMark);
        Map<String,String> resMap = new HashMap<>();
        resMap.put("message",message);
        return resMap;
    }


    @RequestMapping(value = "/getSensitiveSetField",method = RequestMethod.GET)
    @ResponseBody
    public List<DataSetBean> getSensitiveSetField(String platformMark,String roleName){
       return roleService.getSensitiveSetField(platformMark,roleName);
    }




}
