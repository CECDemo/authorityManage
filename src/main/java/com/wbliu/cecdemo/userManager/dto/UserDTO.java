package com.wbliu.cecdemo.userManager.dto;

import com.wbliu.cecdemo.userManager.pojo.Roles;
import com.wbliu.cecdemo.userManager.pojo.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wbliu
 * @create 2017-03-01 18:34
 **/
public class UserDTO{
 private Integer id;

 private String uuid;

 private String username;

 private String password;

 private Byte enabled;

private String newPassword1;
private String newPassword2;

 private  String userName;
 private String userPassword;
 private  List<RoleDTO> rolesDtoList ;

 private List<Roles> roleList = new ArrayList<Roles>();

 List<String> selectedRoleNameList;

 public List<String> getSelectedRoleNameList() {
  return selectedRoleNameList;
 }

 public void setSelectedRoleNameList(List<String> selectedRoleNameList) {
  this.selectedRoleNameList = selectedRoleNameList;
 }

 public List<Roles> getRoleList() {
  return roleList;
 }

 public void setRoleList(List<Roles> roleList) {
  this.roleList = roleList;
 }


 public void showMesg() {
  String str =null;
  str = "用户名 ："+this.getUserName()+" 角色名 ：";

  for (Roles roles : this.getRoleList()){
   str +=roles.getRolesname()+"_"+roles.getDescription();
  }

  System.out.println(str);
 }

 public List<RoleDTO> getRolesDtoList() {
  return rolesDtoList;
 }

 public void setRolesDtoList(List<RoleDTO> rolesDtoList) {
  this.rolesDtoList = rolesDtoList;
 }

 public String getUserName() {
  return userName;
 }

 public void setUserName(String userName) {
  this.userName = userName;
 }

 public String getUserPassword() {
  return userPassword;
 }

 public void setUserPassword(String userPassword) {
  this.userPassword = userPassword;
 }

 public Integer getId() {
  return id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public String getUuid() {
  return uuid;
 }

 public void setUuid(String uuid) {
  this.uuid = uuid;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public Byte getEnabled() {
  return enabled;
 }

 public void setEnabled(Byte enabled) {
  this.enabled = enabled;
 }

 public String getNewPassword1() {
  return newPassword1;
 }

 public void setNewPassword1(String newPassword1) {
  this.newPassword1 = newPassword1;
 }

 public String getNewPassword2() {
  return newPassword2;
 }

 public void setNewPassword2(String newPassword2) {
  this.newPassword2 = newPassword2;
 }
}

