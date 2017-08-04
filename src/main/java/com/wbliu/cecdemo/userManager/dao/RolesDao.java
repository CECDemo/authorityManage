package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.dto.RoleDTO;
import com.wbliu.cecdemo.userManager.pojo.Roles;
import com.wbliu.cecdemo.userManager.pojo.RolesExample;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface RolesDao {
   /* int countByExample(RolesExample example);

    int deleteByExample(RolesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    List<Roles> selectByExample(RolesExample example);

    Roles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Roles record, @Param("example") RolesExample example);

    int updateByExample(@Param("record") Roles record, @Param("example") RolesExample example);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
*/

    List<Roles> selectByPlatformMark(String platformMark);

    List<Roles> selectByRoleNameSet(Set<String> roleNameSet);

    int deleteRoleByRoleName(String roleName);

    int deleteRoleByRoleNameAndPlatformMark(String roleName, String platformMark);

    String  selectNextRoleId();

    Roles  selectByRoleDescriptionAndPlatformMark(String description, String platformMark);

    int   insert(Roles role);

    Roles   selectById(Integer id);

    int  updateRoleById(RoleDTO roleDto);


    List<String> selectAdminRoleNameFromRolesDB();

    List<Roles>  getRoleListByUserName(String username);

    Set<String>  selectRoleNameByPlatformMark(String platformMark);
}