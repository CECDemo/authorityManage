package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.pojo.Usermenu;
import com.wbliu.cecdemo.userManager.pojo.UsermenuExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMenuDao {
    int countByExample(UsermenuExample example);

    int deleteByExample(UsermenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Usermenu record);

    int insertSelective(Usermenu record);

    List<Usermenu> selectByExample(UsermenuExample example);

    Usermenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Usermenu record, @Param("example") UsermenuExample example);

    int updateByExample(@Param("record") Usermenu record, @Param("example") UsermenuExample example);

    int updateByPrimaryKeySelective(Usermenu record);

    int updateByPrimaryKey(Usermenu record);


    List<Usermenu> selectByRoleName(String rolesname);

    boolean deleteRoleNameFromUserMenuByRoleName(String roleName);

    int updateRoleNameById(Integer id, String newMenuroleStr);

    boolean deleteRoleNameFromUserMenuByRoleNameAndPlatformMark(String roleName, String platformMark);

    List<Usermenu>  selectByRoleNameAndPlatfomrMark(String roleName, String platformMark);

    int  updateRoleNameByIdAndPlatformMark(Integer id, String newMenuroleStr, String platformMark);

    List<Usermenu>  selectByPlatformMark(String platformMark);

    int   updateByMenuDescription(Usermenu usermenu1);

    String  selectWorkFlowModuleRolesStr();

    int updateMenuRoleByMenuName(String menuRoleStr, String menuName);

    Usermenu  selectByMenuName(String menuName);
}