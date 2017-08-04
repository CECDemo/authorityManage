package com.wbliu.cecdemo.userManager.service;

import com.wbliu.cecdemo.userManager.dao.AuthoritiesDao;
import com.wbliu.cecdemo.userManager.dao.RolesDao;
import com.wbliu.cecdemo.userManager.dao.UserMenuDao;
import com.wbliu.cecdemo.userManager.dao.UsersDao;
import com.wbliu.cecdemo.userManager.dto.RoleDTO;
import com.wbliu.cecdemo.userManager.pojo.*;
import com.wbliu.cecdemo.userManager.dto.UserDTO;
import com.wbliu.cecdemo.userManager.property.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author wbliu
 * @create 2017-02-28 13:19
 **/

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDao userDao;

    @Autowired
    private RolesDao roleDao;

    @Autowired
    private AuthoritiesDao authDao;

    @Autowired
    private UserMenuDao userMenuDao;


    @Autowired
    DefaultTokenServices tokenServices;

    public boolean checkUser(String userName, String pass) {
        UsersExample example = new UsersExample();
        example.createCriteria()
                .andPasswordEqualTo(pass)
                .andUsernameEqualTo(userName);
        int count = 1;//userDao.countByExample(example);
        if (count == 1) {
            System.out.println("[ 查询成功！]");
            return true;
        } else {
            System.out.printf("获得的user为空！");
        }
        return false;
    }


    public List<UserDTO> getAllUserInfo() {

        List<UserDTO> userDTOList = new ArrayList<>();

        List<Users> usersList = userDao.selectUsers();

        UserDTO userDTO;
        for (Users users : usersList) {
            userDTO = new UserDTO();

            userDTO.setId(users.getId());
            userDTO.setUuid(users.getUuid());
            userDTO.setUsername(users.getUsername());
            userDTO.setPassword(users.getPassword());
            userDTO.setEnabled(users.getEnabled());

            userDTO.setRoleList(getRoleListByUserName(users.getUsername()));
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }


    private List<Roles> getRoleListByUserName(String username) {
        List<Roles> rolesList = new ArrayList<>();

        List<Authorities> authoritiesList = authDao.selectByUserName(username);

        if (authoritiesList != null && authoritiesList.size() == 1) {
            Set<String> roleNameSet = new HashSet<>();
            String roleName = authoritiesList.get(0).getAuthority();

            if (roleName.contains(",")) {
                String[] roleNames = roleName.split(",");

                for (String temp : roleNames) {
                    roleNameSet.add(temp.trim());
                }

            } else {
                roleNameSet.add(roleName);
            }


            rolesList = roleDao.selectByRoleNameSet(roleNameSet);

        } else {
            System.out.println("username = " + username + "  authoritiesList.size() =" + authoritiesList.size());

            Roles roles = new Roles();
            roles.setRolesname("null");
            roles.setDescription("暂时没有分配角色");

            rolesList.add(roles);
        }

        return rolesList;
    }


    public UserDTO getUserInfoByuuid(String uuid) {
        UserDTO userDto = new UserDTO();

        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andUuidEqualTo(uuid);

        List<Users> usersList = null;//userDao.selectByExample(usersExample);
        if (usersList.size() == 1) {
            Users users = usersList.get(0);
            String userName = users.getUsername();


            List<Roles> rolesList;
            rolesList = getRoleListByUserName(userName);


            userDto.setRoleList(rolesList);

            return userDto;

        }


        return null;
    }

    public Users getUserInfoByUserName(String userName) {
        List<Users> usersList;
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andUsernameEqualTo(userName);

        usersList = null;//userDao.selectByExample(usersExample);
        if (usersList.size() == 0) {
            return null;
        }

        return usersList.get(0);
    }

    @Override
    public boolean deleteUserByUserName(String userName) {
        try {
            if (isDeleteUserByUserName(userName)) {
                isDeleteAuthByUserName(userName);
                return true;
            }

        } catch (Exception e) {
            System.out.println("[ 删除用户，出现异常 e.getMessage = " + e.getMessage() + "]");
        }

        return false;
    }

    private boolean isDeleteUserByUserName(String userName) {
        return userDao.deleteByUserName(userName) >= 1 ;
    }

    @Override
    public List<RoleDTO> getRoleInfoByPlatformMark(String platformMark) {
        List<RoleDTO> roleDTOList = new ArrayList<>();

        List<Roles> rolesList = roleDao.selectByPlatformMark(platformMark);


        RoleDTO roleDTO;
        List<Usermenu> usermenuList;
        Usermenu usermenu = null;
        for (Roles roles : rolesList) {

            roleDTO = new RoleDTO();

            roleDTO.setId(roles.getId());
            roleDTO.setRolesname(roles.getRolesname());
            roleDTO.setDescription(roles.getDescription());
            roleDTO.setRolelevel(roles.getRolelevel());
            roleDTO.setDownloadlevel(roles.getDownloadlevel());
            roleDTO.setPlatformMark(roles.getPlatformMark());
            roleDTO.setRoleComment(roles.getRoleComment());

            usermenuList = userMenuDao.selectByRoleName(roles.getRolesname());

            if (roles.getDownloadlevel() == 1) {//enable download
                usermenu = new Usermenu();
                usermenu.setMenudescription("下载");
                usermenuList.add(usermenu);
            }

            if (usermenuList.size() <= 0) { //handle role's userMenu is null
                usermenu = new Usermenu();
                usermenu.setMenudescription("暂时未分配功能");
                usermenuList.add(usermenu);
            }


            roleDTO.setUserMenuList(usermenuList);

            roleDTOList.add(roleDTO);
        }

        return roleDTOList;
    }

    @Override
    public boolean updateUserByuuid(String uuid, UserDTO userDto) {
       /*更新用户信息*/

        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andUuidEqualTo(uuid);

        return false;
    }

    @Override
    public String addUser(UserDTO userDto) {

        if (userDto.getUserName() == null || userDto.getUserName().length() <= 0) {
            return "用户名不能为空";
        }

        if (isUserHadExite(userDto.getUserName())) {
            return "用户已存在，请勿重复添加";
        }


        try {

            if (isAddUserSuccess(userDto.getUserName(), userDto.getNewPassword1())) {

                addNewRoleNameToAuth(userDto.getUserName(), userDto.getSelectedRoleNameList());

                return "新增用户成功";
            }
        } catch (Exception e) {
            System.out.println("[ 新增用户出现异常 " + e.getMessage() + " ]");
        }

        return "新增用户失败";
    }

    private boolean isUserHadExite(String userName) {
    return  userDao.selectByUserName(userName).size() >= 1;
    }

    private boolean addNewRoleNameToAuth(String username, List<String> selectedRoleNameList) {
        if (selectedRoleNameList.size() <= 0) {
            System.out.println("[未选择任何角色，无需添加 Auth]");
            return true;
        }

        Authorities authorities = new Authorities();
        authorities.setUsername(username);
        authorities.setAuthority(getRoleNameStr(selectedRoleNameList));
        return authDao.insert(authorities) == 1 ;
    }

    private boolean isAddUserSuccess(String userName, String password) {
        String uuid = UUID.randomUUID().toString();
        Users users = new Users();

        users.setUuid(uuid);
        users.setUsername(userName);
        users.setPassword(password);
        users.setEnabled(Byte.parseByte(Global.UserIsEnabled));


        return userDao.insert(users) == 1;
    }

    @Override
    public String modifyUser(UserDTO userDto) {
        try {
            Users users = userDao.selectByUUID(userDto.getUuid());

            if (users == null) {
                return "未找到要更新的用户信息";
            }


            if (updateUserInfo(users, userDto)) {

                updateAuthInfo(userDto,users.getUsername());

                return "更新用户信息成功";
            }

        } catch (Exception e) {
            System.out.println(" [ 更新用户出现异常 ： e.message = " + e.getMessage() + " ]");
        }


        return "更新用户信息失败";
    }

    private boolean updateAuthInfo(UserDTO userDto,String oldUserName) {
        List<Authorities> authoritiesList = authDao.selectByUserName(oldUserName);


        if (authoritiesList.size() <= 0) {
            return addNewRoleNameToAuth(userDto.getUserName(), userDto.getSelectedRoleNameList());
        }


        if (isDeleteAuthByUserName(oldUserName)) {
            return addNewRoleNameToAuth(userDto.getUserName(), userDto.getSelectedRoleNameList());
        } else {
            System.out.println("[删除原有Auth信息失败]");
        }

        return false;
    }

    private String getRoleNameStr(List<String> selectedRoleNameList) {

        if (selectedRoleNameList.size() <= 0) return "";

        String roleNameStr = "";
        for (String tempRoleName : selectedRoleNameList) {
            roleNameStr = String.format(roleNameStr + ",%s", tempRoleName);
        }

        return roleNameStr.substring(1)+",";
    }

    private boolean isDeleteAuthByUserName(String userName) {
        return authDao.deleteByUserName(userName) >= 1 ;
    }

    private boolean updateUserInfo(Users users, UserDTO userDto) {
        if (isNotUserChanged(users, userDto)) {
            return true;
        }


        Users users1 = new Users();
        users1.setUuid(users.getUuid());

        if (isUserNameChanged(users.getUsername(), userDto.getUserName())) {
            users1.setUsername(userDto.getUserName());
        } else {
            users1.setUsername(users.getUsername());
        }

        if (isPasswordChanged(users.getPassword(), userDto.getNewPassword1())) {
            users1.setPassword(userDto.getNewPassword1());
        } else {
            users1.setPassword(users.getPassword());
        }

        return userDao.updateUserByUUID(users1) == 1;
    }

    private boolean isNotUserChanged(Users users, UserDTO userDto) {
        if (isPasswordChanged(users.getPassword(), userDto.getNewPassword1()) || isUserNameChanged(users.getUsername(), userDto.getUserName()))
            return false;
        return true;
    }

    private boolean isUserNameChanged(String username, String userName) {
        return !username.equals(userName);
    }

    private boolean isPasswordChanged(String oldPassword, String newPassword) {
        if ("noChange".equals(newPassword)) return false;
        return !oldPassword.equalsIgnoreCase(newPassword);
    }

    @Override
    public String login(UserDTO userDto) {

        String superAdminName = Global.SUPERADMINNAME;
        if (!userDto.getUsername().equals(superAdminName)) {
            return "请使用超级管理员账户登录";
        }

        Users users = userDao.selectUserByUserNameAndPassword(userDto.getUsername(), userDto.getPassword());
        if (users == null) {
            return "用户名/密码错误";
        }


        return "登录成功";
    }


    @Override
    public boolean logout(String token) {
        return tokenServices.revokeToken(token);
    }


    @Override
    public List<UserDTO> getUserInfoBySystemFlag(String platformMark) {

        List<UserDTO> userDtoList;
        /*获得平台下的所有角色*/
        List<Roles> rolesList = roleDao.selectByPlatformMark(platformMark);

        /*根据角色名获得用户名列表*/
        Set<String> userNameSet = getUserNameSetByRoleList(rolesList);


        /*根据用户名列表获得用户信息和用户在该平台下的角色信息*/
        userDtoList = getUserDtoByUserNameSetAndSystemFlag(userNameSet, platformMark);

         /*获得没有分配角色的用户*/
        List<UserDTO> noRoleUserList = getNoRoleUserList(platformMark);
        userDtoList.addAll(noRoleUserList);

        return userDtoList;
    }

    private List<UserDTO> getNoRoleUserList(String platformMark) {
        List<UserDTO> noRoleUserList = new ArrayList<>();
        List<Users> usersList;

        usersList = userDao.selectUserWithNoRole();

        UserDTO userDto;
        Roles roles;
        List<Roles> rolesList;

        for (Users users : usersList) {
            userDto = new UserDTO();

            userDto.setUsername(users.getUsername());
            userDto.setId(users.getId());
            userDto.setUuid(users.getUuid());
            userDto.setPassword(users.getPassword());
            userDto.setEnabled(users.getEnabled());

            roles = new Roles();
            roles.setPlatformMark(platformMark);
            roles.setDescription("未分配角色");
            rolesList = new ArrayList<>();
            rolesList.add(roles);

            userDto.setRoleList(rolesList);


            noRoleUserList.add(userDto);
        }


        return noRoleUserList;
    }

    @Override
    public String modifyPassword(UserDTO userDto) {

        if (userDto.getPassword() == null || userDto.getNewPassword1() == null || userDto.getNewPassword2() == null) {
            return "输入项不能有空值";
        }

        if (!userDto.getNewPassword1().equals(userDto.getNewPassword2())) {
            return "两次输入新密码不一致";
        }

        Users users = userDao.selectUserByUserNameAndPassword(userDto.getUsername(), userDto.getPassword());

        if (users == null) {
            return "原始密码不正确";
        }

        int count = userDao.updateUserPassword(userDto.getUsername(), userDto.getPassword(), userDto.getNewPassword1());

        if (count >= 1) {
            return "修改成功，请重新登录";
        }

        return "未知异常，请联系工程师处理";
    }

    /*根据角色名获得用户名列表*/
    private Set<String> getUserNameSetByRoleList(List<Roles> rolesList) {
        Set<String> userNameSet = new HashSet<>();

        List<Authorities> authoritiesList;
        for (Roles roles : rolesList) {
            String roleName = roles.getRolesname();

            authoritiesList = authDao.selectLikeRoleName(roleName);


            if (authoritiesList != null && authoritiesList.size() > 0) {
                for (Authorities authorities : authoritiesList) {
                    userNameSet.add(authorities.getUsername());
                }
            } else {
                continue;
            }

        }

        return userNameSet;
    }


    /*根据用户名列表获得用户信息和用户在该平台下的角色信息*/
    private List<UserDTO> getUserDtoByUserNameSetAndSystemFlag(Set<String> userNameSet, String platformMark) {
        List<UserDTO> userDtoList = new ArrayList<>();

        List<Users> usersList;
        List<Roles> rolesList;
        Users users;
        UserDTO userDto;
        for (String userName : userNameSet) {
        /*根据用户名获得用户信息*/
            usersList = userDao.selectByUserName(userName);

            if (usersList != null && usersList.size() == 1) {
                users = usersList.get(0);
                userDto = new UserDTO();

                userDto.setUsername(users.getUsername());
                userDto.setId(users.getId());
                userDto.setUuid(users.getUuid());
                userDto.setPassword(users.getPassword());
                userDto.setEnabled(users.getEnabled());

       /*根据用户名和平台标志，获得用户在该平台下的角色*/
                rolesList = getRoleListByUserNameAndSystemFlag(userName, platformMark);
                userDto.setRoleList(rolesList);

                userDtoList.add(userDto);
            } else {
                System.out.println("用户名不唯一,或没有该用户名， userName = " + userName);
            }

        }//end for
        return userDtoList;
    }

    /*根据用户名和平台标志，获得用户在该平台下的角色*/
    private List<Roles> getRoleListByUserNameAndSystemFlag(String userName, String platformMark) {
        List<Roles> rolesListTemp;
        List<Roles> rolesList = new ArrayList<>();

         /*根据用户名获得角色*/
        rolesListTemp = getRoleListByUserName(userName);
         /*根据平台标志获得该平台下的角色*/
        for (Roles roles : rolesListTemp) {
            if (platformMark.equals(roles.getPlatformMark())) {
                rolesList.add(roles);
            }

        }

        return rolesList;
    }

}
