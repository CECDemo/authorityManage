package com.wbliu.cecdemo.userManager.service;

import com.wbliu.cecdemo.userManager.dto.RoleDTO;
import com.wbliu.cecdemo.userManager.pojo.DataSetBean;
import com.wbliu.cecdemo.userManager.pojo.Roles;

import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-03 8:59
 **/

public interface RoleService {
    String addRole(RoleDTO roleDto);

    List<Roles> getRolesByPlatformMark(String platformMark);

    String deleteRoleByRoleName(String roleName, String platformMark);

    String  modifyRole(RoleDTO roleDto);

    List<RoleDTO>  getRolesByUserName(String userName);

    List<DataSetBean>  getSensitiveSetField(String platformMark, String userName);

    List<String>  getRoleNamesByUserName(String userName);
}
