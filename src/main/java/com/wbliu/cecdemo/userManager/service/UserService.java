package com.wbliu.cecdemo.userManager.service;

import com.wbliu.cecdemo.userManager.dto.RoleDTO;
import com.wbliu.cecdemo.userManager.pojo.Usermenu;
import com.wbliu.cecdemo.userManager.pojo.Users;
import com.wbliu.cecdemo.userManager.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-02-27 16:20
 **/

public interface UserService {
      boolean  checkUser(String userName,String pass);

      List<UserDTO> getAllUserInfo();

      UserDTO getUserInfoByuuid(String uuid);

      Users getUserInfoByUserName(String userName);


    boolean updateUserByuuid(String uuid, UserDTO userDto);



    String addUser(UserDTO userDto);
    List<UserDTO> getUserInfoBySystemFlag(String platformMark);
    String modifyPassword(UserDTO userDto);
    boolean deleteUserByUserName(String userName);

    List<RoleDTO> getRoleInfoByPlatformMark(String platformMark);


    String   modifyUser(UserDTO userDto);

    String  login(UserDTO userDto);

    public boolean logout(String token);

}
