package com.wbliu.cecdemo.userManager.service;

import com.wbliu.cecdemo.userManager.dao.*;
import com.wbliu.cecdemo.userManager.dto.RoleDTO;
import com.wbliu.cecdemo.userManager.pojo.*;
import com.wbliu.cecdemo.userManager.property.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wbliu
 * @create 2017-03-03 8:59
 **/

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UsersDao userDao;

    @Autowired
    private RolesDao roleDao;

    @Autowired
    private AuthoritiesDao authDao;

    @Autowired
    private UserMenuDao userMenuDao;

    @Autowired
    private DataSetDao dataSetDao;

    @Autowired
    private CallColumnInfoDao callColumnInfoDao;

    @Autowired
    private SetCallColumnInfoDao setCallColumnInfoDao;


    @Override
    public String addRole(RoleDTO roleDto) {

        if (roleDto.getDescription() == null || roleDto.getDescription().equals("")) {
            return "角色名称，不能为空";
        }

        if (roleDto.getRoleComment() == null || roleDto.getRoleComment().equals("")) {
            roleDto.setRoleComment("角色描述（100字以内）");
        }

        if (isRoleHadExite(roleDto.getDescription(), roleDto.getPlatformMark())) {
            return "角色已存在，请勿重复添加";
        }

        try {
            if (addRoleOnly(roleDto)) {

                if (isHadNoUserMenu(roleDto.getUsermentStr())|| isOnlyContainDownload(roleDto.getUsermentStr())) {
                    return "新增角色成功";
                }

                addNewRoleToSensitiveSetTable(roleDto);

                addNewRoleToUserMenuTable(roleDto);

                return "新增角色成功！";
            }
        } catch (Exception e) {
            System.out.println("[新增角色失败  " + e.getMessage() + "]");
        }

        return "新增角色失败！";
    }

    private boolean isHadNoUserMenu(String usermentStr) {
    return  usermentStr.length() <= 0;
    }

    private boolean isRoleHadExite(String description, String platformMark) {
      return roleDao.selectByRoleDescriptionAndPlatformMark(description, platformMark) != null ;
    }

    private boolean isOnlyContainDownload(String userMenuStr) {
        List<String> userMenuList = Arrays.asList(userMenuStr.split(","));

        if(userMenuList.size() ==1 && userMenuList.contains("下载")){
            return true;
        }

        return false;
    }

    private void addNewRoleToUserMenuTable(RoleDTO roleDto) {
        List<String> menuNameList = Arrays.asList(roleDto.getUsermentStr().split(","));
        for (String menuName : menuNameList) {
            if ("下载".equalsIgnoreCase(menuName)) continue;
            addRoleNameToUserMenu(roleDto.getRolesname(), menuName);
        }

    }

    private boolean addRoleNameToUserMenu(String rolesname, String menuName) {
        Usermenu userMenu = userMenuDao.selectByMenuName(menuName);
        if (userMenu != null) {

            String menuRoleStr = "";
            String oldMenuRoleStr = userMenu.getMenurole();

            if (oldMenuRoleStr != null && oldMenuRoleStr.length() >= 1) {

                if(isNotContainCommaInTheEnd(oldMenuRoleStr)){
                    oldMenuRoleStr = oldMenuRoleStr+",";
                }

                if (isOldMenuRoleStrContainRoleName(oldMenuRoleStr, rolesname)) {
                    System.out.println("[ OldMenuRole Contains RoleName ]");
                } else {
                    menuRoleStr = String.format(oldMenuRoleStr +"%s,", rolesname);
                }

            } else {
                menuRoleStr = rolesname+",";
            }

           return userMenuDao.updateMenuRoleByMenuName(menuRoleStr, menuName) ==1;
        }


        return  false;
    }

    private boolean isOldMenuRoleStrContainRoleName(String oldMenuRoleStr, String rolesname) {
        return Arrays.asList(oldMenuRoleStr.split(",")).contains(rolesname);
    }

    private boolean addNewRoleToSensitiveSetTable(RoleDTO roleDto) {
        if (roleDto.getDataSetCodeAndFlagList().size() >= 1) {

            List<Boolean>  resultFlagList = new ArrayList<>();


            for (String dateSetCodeAndFlag : roleDto.getDataSetCodeAndFlagList()) {
               resultFlagList.add(addNewRoleToSensitiveSetTableByPlatform(roleDto.getRolesname(), dateSetCodeAndFlag.split("-"), roleDto.getPlatformMark()));
            }



            return resultFlagList.contains(false) ? false : true;
        } else {
            System.out.println("[没有需要维护的敏感信息列]");
          return  true;
        }
    }

    private boolean addRoleOnly(RoleDTO roleDto) {
        Roles role = new Roles();
        String roleName = Global.PREFIXROLENAME + roleDao.selectNextRoleId();

        role.setRolesname(roleName);
        role.setDescription(roleDto.getDescription());
        role.setDownloadlevel(getDownloadFlagValue(roleDto.getUsermentStr()));
        role.setRolelevel(Integer.parseInt(Global.DEFAULTROLELEVEL));
        role.setPlatformMark(roleDto.getPlatformMark());
        role.setRoleComment(roleDto.getRoleComment());

        roleDto.setRolesname(roleName);
        return roleDao.insert(role) == 1 ;

    }

    private int getDownloadFlagValue(String usermentStr) {
        return Arrays.asList(usermentStr.split(",")).contains("下载") ? 1 : 0;
    }

    private boolean addNewRoleToSensitiveSetTableByPlatform(String rolesname, String[] sensitiveSetInfoArray, String platformMark) {

        if ("cardservice".equalsIgnoreCase(platformMark)) {

            return   addNewRoleNameToCadPlatformSensitiveTable(rolesname, sensitiveSetInfoArray);

        } else if ("synthetical".equalsIgnoreCase(platformMark)) {

            return   addNewRoleNameToSynPlatformSensitiveTable(rolesname, sensitiveSetInfoArray);
        } else {
            System.out.println("[未知的敏感信息列数据集平台 :" + platformMark + "]");
        }




        return false;
    }

    private boolean addNewRoleNameToSynPlatformSensitiveTable(String rolesname, String[] sensitiveSetInfoArray) {

        if ("可见".equalsIgnoreCase(sensitiveSetInfoArray[1])) {

            List<SetCallColumnInfoBean> setCallColumnInfoBeanList = setCallColumnInfoDao.selectSetCallColumnInfoByDateSet(sensitiveSetInfoArray[0]);

            if (setCallColumnInfoBeanList.size() <= 0) {
                System.out.println("[没有需要配置的敏感字段 dataSetcode = " + sensitiveSetInfoArray[0] + "]");
                return true;
            }


            for (SetCallColumnInfoBean setCallColumnInfoBean : setCallColumnInfoBeanList) {

                if (isDisplayRoleContainRoleNameForSetCall(setCallColumnInfoBean.getId(),rolesname,setCallColumnInfoBean.getDataset())) {
                    continue;
                } else {
                   addNewRoleNameToSetCallTable(setCallColumnInfoBean.getId(),rolesname,sensitiveSetInfoArray[0]);
                }

            }

            return true;
        }

        return false;
    }

    private boolean addNewRoleNameToSetCallTable(int id, String rolesname, String dataSetCode) {
     return  setCallColumnInfoDao.insertRoleNameToSetCall(id,rolesname,dataSetCode);
    }

    private boolean isDisplayRoleContainRoleNameForSetCall(int id, String dataSetCode, String rolesname) {
    return  setCallColumnInfoDao.selectCountByDateSetAndRoleName(id,dataSetCode,rolesname) ==1;
    }

    private boolean addNewRoleNameToCadPlatformSensitiveTable(String rolesname, String[] sensitiveSetInfoArray) {

        if ("可见".equalsIgnoreCase(sensitiveSetInfoArray[1])) {

            List<CallColumnInfoBean> callColumnInfoBeanList = callColumnInfoDao.selectCallColumnInfoByDateSet(sensitiveSetInfoArray[0]);

            if (callColumnInfoBeanList.size() <= 0) {
                System.out.println("[没有需要配置的敏感字段 dataSetcode = " + sensitiveSetInfoArray[0] + "]");
                return true;
            }


            for (CallColumnInfoBean callColumnInfoBean : callColumnInfoBeanList) {

                if (isDisplayRoleContainRoleName(callColumnInfoBean.getDisplaylevel(),rolesname)) {
                    continue;
                } else {
                    callColumnInfoBean.setDisplaylevel(getNewDisplayLevelStr(callColumnInfoBean.getDisplaylevel(),rolesname));
                }
                updateCallColumnInfoByPlatform(callColumnInfoBean);
            }

            return true;
        }
        return false;
    }

    private String getNewDisplayLevelStr(String displaylevel, String rolesname) {

        if(isNotContainCommaInTheEnd(displaylevel)){
            displaylevel = displaylevel+",";
        }

    return displaylevel.length() <= 1 ? rolesname+"," : String.format(displaylevel + "%s,", rolesname);
    }

    private boolean isNotContainCommaInTheEnd(String displaylevel) {
     if (displaylevel.length() <=0) return  false;
     return  (displaylevel.length()-1) != displaylevel.lastIndexOf(",");
    }

    private boolean updateCallColumnInfoByPlatform(CallColumnInfoBean callColumnInfoBean) {
        return callColumnInfoDao.updateCallColumnInfo(callColumnInfoBean) == 1;
    }
    @Override
    public List<Roles> getRolesByPlatformMark(String platformMark) {
        return roleDao.selectByPlatformMark(platformMark);
    }

    @Override
    public String deleteRoleByRoleName(String roleName, String platformMark) {

        try {

            if("cardservice".equals(platformMark)){
                removeRoleFromSensitiveSetTable(roleName);
            }else if("synthetical".equals(platformMark)){
                removeRoleNameFromSynSetTable(roleName,platformMark);
            }else{
                System.out.println("[未知平台，暂不删除]");
                return "未知平台，暂不删除";
            }



            deleteRoleNameFromUserMenuByRoleNameAndPlatformMark(roleName,platformMark);

            deleteRoleNameFromAuthByRoleName(roleName);

            roleDao.deleteRoleByRoleNameAndPlatformMark(roleName, platformMark);

            return  "删除角色成功";
        }catch (Exception e){
            System.out.println("[删除角色出现异常 roleName = "+roleName+" "+e.getMessage()+"]");
        }

        return "删除角色失败";
    }

    private boolean removeRoleNameFromSynSetTable(String roleName, String platformMark) {
       List<DataSetBean> dataSetBeanList = dataSetDao.selectByPlatform(platformMark);

       if(dataSetBeanList.size() <=0){
           return  true;
       }

       List<Boolean> resultList = new ArrayList<>();

       for(DataSetBean dataSetBean :dataSetBeanList){
           resultList.add(setCallColumnInfoDao.deleteRoleNameSetCall(roleName,dataSetBean.getDataSetCode()));
       }

        return  resultList.contains(false)?false :true;
    }

    private boolean deleteRoleNameFromAuthByRoleName(String roleName) {
        List<Authorities> authoritiesList = authDao.selectLikeRoleName(roleName);

        if(authoritiesList.size() <=0){
            return true;
        }

        List<Boolean> resultFlagList = new ArrayList<>();
        for (Authorities authorities : authoritiesList) {
            String newRoleNameStr = getNewRoleNameStr(authorities.getAuthority(), roleName);

            if ("".equals(newRoleNameStr))
                resultFlagList.add(authDao.deleteByUserName(authorities.getUsername()) == 1);

            resultFlagList.add(authDao.updateAuthoritiesByKey(authorities.getId(), newRoleNameStr));
        }

        return resultFlagList.contains(false) ? false : true;
    }

    private boolean deleteRoleNameFromUserMenuByRoleNameAndPlatformMark(String roleName, String platformMark) {
     return userMenuDao.deleteRoleNameFromUserMenuByRoleNameAndPlatformMark(roleName, platformMark);
    }

    private void removeRoleFromSensitiveSetTable(String roleName) {

        List<CallColumnInfoBean> callColumnInfoBeanList = callColumnInfoDao.selectCallColumnInfoByRoleName(roleName);

        for (CallColumnInfoBean callColumnInfoBean : callColumnInfoBeanList) {

            List<String> roleNameList = Arrays.asList(callColumnInfoBean.getDisplaylevel().split(","));

            String displayLevelStr = "";

            for (String roleNameTemp : roleNameList) {
                if (roleName.equalsIgnoreCase(roleNameTemp)) {
                    continue;
                }
                displayLevelStr = String.format(displayLevelStr + ",%s", roleNameTemp);
            }

            displayLevelStr = displayLevelStr.length() >= 1 ? displayLevelStr.substring(1)+"," : "";

            callColumnInfoBean.setDisplaylevel(displayLevelStr);
            callColumnInfoDao.updateCallColumnInfo(callColumnInfoBean);
        }


    }

    @Override
    public String modifyRole(RoleDTO roleDto) {
        String menuStr = roleDto.getUsermentStr();

        Roles roles = roleDao.selectById(roleDto.getId());

        int downLoadFlag = 0;
        if (isContainDownload(roleDto.getUsermentStr())) {
            downLoadFlag = 1;
        }
        roleDto.setDownloadlevel(downLoadFlag);


        if (roles != null) {

            roleDto.setRolesname(roles.getRolesname());

            if (isRoleChanged(roleDto, roles)) {

                if (isPlatformChanged(roleDto.getPlatformMark(), roles.getPlatformMark()) || isDescriptionChanged(roleDto.getDescription(),roles.getDescription())) {
                    if(isRoleHadExite(roleDto.getDescription(),roleDto.getPlatformMark()))
                    return "角色已存在，请改为其他角色";
                }


                if(isUpdateRoleSuccess(roleDto)){

                    updateSensitiveFieldTable(roleDto,roles);

                    removeRoleNameFromUserMenuTable(roles.getRolesname(),roles.getPlatformMark());
                    if (menuStr.length() <= 0 ||isOnlyContainDownload(menuStr)) {
                        return "修改角色成功";
                    }else{
                        addRoleNameToUserMenuTable(roleDto);
                        return "修改角色成功";
                    }

                }

               return "修改角色失败";
            }

            updateSensitiveFieldTable(roleDto);

            removeRoleNameFromUserMenuTable(roleDto.getRolesname(),roleDto.getPlatformMark());

            if (menuStr.length() <= 0 ||isOnlyContainDownload(menuStr)) {
                return "修改角色成功";
            }else{
                addRoleNameToUserMenuTable(roleDto);
                return "修改角色成功";
            }
        }


        return "修改角色失败";
    }

    private boolean isDescriptionChanged(String description, String oldDescription) {
        return  description.equals(oldDescription) ? false : true;
    }

    private void updateSensitiveFieldTable(RoleDTO roleDto, Roles roles) {

        if(isPlatformChanged(roleDto.getPlatformMark(),roles.getPlatformMark())){

            deleteOldRoleNameFromSensitiveTable(roles.getRolesname(),roles.getPlatformMark());

            addNewRoleToSensitiveSetTable(roleDto);

        }else{
            updateSensitiveFieldTable(roleDto);
        }

    }

    private void deleteOldRoleNameFromSensitiveTable(String rolesname, String platformMark) {

        List<DataSetBean> dataSetBeanList = dataSetDao.selectByPlatform(platformMark);

        if("cardservice".equalsIgnoreCase(platformMark)){
            for(DataSetBean dataSetBean : dataSetBeanList){
                 removeRoleFromSensitiveSetTableByDataSetCode(rolesname,dataSetBean.getDataSetCode());
            }


        }else if ("synthetical".equalsIgnoreCase(platformMark)){

            for(DataSetBean dataSetBean : dataSetBeanList){
                 setCallColumnInfoDao.deleteRoleNameSetCall(rolesname,dataSetBean.getDataSetCode());
            }

        }else{
            System.out.println("[未知的平台 platformMark = "+platformMark+"]");
        }
    }

    private boolean removeRoleFromSensitiveSetTableByDataSetCode(String rolesname, String dataSetCode) {
            List<CallColumnInfoBean> callColumnInfoBeanList =  callColumnInfoDao.selectByDateSetAndRoleName(rolesname,dataSetCode);

            if(callColumnInfoBeanList.size() <=0){
                return  true;
            }else{

                List<Boolean> resultFlagList = new ArrayList<>();
                for (CallColumnInfoBean callColumnInfoBean :callColumnInfoBeanList){

                    String newDisplayStr = removeRoleNameFromDisplayRoles(callColumnInfoBean.getDisplaylevel(),rolesname);
                    callColumnInfoBean.setDisplaylevel(newDisplayStr);
                    resultFlagList.add(callColumnInfoDao.updateCallColumnInfo(callColumnInfoBean) ==1);
                }


                return  resultFlagList.contains(false) ? false : true;
            }
    }

    private boolean isUpdateRoleSuccess(RoleDTO roleDto) {

     return   roleDao.updateRoleById(roleDto) ==1;
    }

    private void removeRoleNameFromUserMenuTable(String rolesname, String platformMark) {
          List<Usermenu> usermenuList = userMenuDao.selectByRoleNameAndPlatfomrMark(rolesname,platformMark);

          String newRoleStr;
          for(Usermenu usermenu: usermenuList){
              newRoleStr = getNewRoleNameStr(usermenu.getMenurole(),rolesname);
              userMenuDao.updateRoleNameByIdAndPlatformMark(usermenu.getId(),newRoleStr,platformMark);
          }
    }

    private String getNewRoleNameStr(String menuRoleStr, String rolesName) {
        String newRoleNameStr = "";
        for (String roleNameStr : menuRoleStr.split(",")) {
            if (rolesName.equals(roleNameStr)) continue;
            newRoleNameStr = String.format(newRoleNameStr + ",%s", roleNameStr);
        }
        return newRoleNameStr.length() >= 1 ? newRoleNameStr.substring(1)+"," : "";
    }

    private boolean isPlatformChanged(String platformMark, String platformMark1) {
        if(platformMark.equals(platformMark1)){
            return false;
        }
      return  true;
    }

    private void addRoleNameToUserMenuTable(RoleDTO roleDto) {
        List<String> menuNameList = Arrays.asList(roleDto.getUsermentStr().trim().split(","));
        for (String menuName : menuNameList) {
            if ("下载".equalsIgnoreCase(menuName)) continue;
            addRoleNameToUserMenu(roleDto.getRolesname(), menuName);
        }

    }

    private void updateSensitiveFieldTable(RoleDTO roleDto) {


        if("cardservice".equalsIgnoreCase(roleDto.getPlatformMark())){


            if ((roleDto.getDataSetCodeAndFlagList().size()) >= 1) {
                for (String dateSetCodeAndFlag : roleDto.getDataSetCodeAndFlagList()) {
                    updateCallColumnInfoByDataSetAndRoleName(roleDto.getRolesname(), dateSetCodeAndFlag.split("-"));
                }
            } else {
                System.out.println("[没有需要维护的敏感信息列]");
            }

        }else if ("synthetical".equalsIgnoreCase(roleDto.getPlatformMark())){


            if ((roleDto.getDataSetCodeAndFlagList().size()) >= 1) {

                for (String dateSetCodeAndFlag : roleDto.getDataSetCodeAndFlagList()) {
                    updateSetCallColumnInfoByDataSetAndRoleName(roleDto.getRolesname(), dateSetCodeAndFlag.split("-"));
                }
            } else {
                System.out.println("[没有需要维护的敏感信息列]");
            }



        }else{
            System.out.println("[未知的平台 platformMark = "+roleDto.getPlatformMark()+"]");
        }
    }

    private void updateSetCallColumnInfoByDataSetAndRoleName(String rolesname, String[] dataSetCodeAndFlag) {
        List<SetCallColumnInfoBean> setCallColumnInfoBeanList = setCallColumnInfoDao.selectSetCallColumnInfoByDateSet(dataSetCodeAndFlag[0]);

        if (setCallColumnInfoBeanList.size() >= 1) {

            for (SetCallColumnInfoBean setCallColumnInfoBean : setCallColumnInfoBeanList) {

                if ("可见".equals(dataSetCodeAndFlag[1])) {

                    if (isDisplayRoleContainRoleNameForSetCall(setCallColumnInfoBean.getId(), dataSetCodeAndFlag[0],rolesname)) {
                        System.out.println("DisplayRole Contains  RoleName");
                    } else {
                       setCallColumnInfoDao.insertRoleNameToSetCall(setCallColumnInfoBean.getId(),rolesname,dataSetCodeAndFlag[0]);
                    }

                } else {

                    if (isDisplayRoleContainRoleNameForSetCall(setCallColumnInfoBean.getId(),dataSetCodeAndFlag[0],rolesname)) {
                            setCallColumnInfoDao.deleteRoleNameSetCall(rolesname,dataSetCodeAndFlag[0]);
                    } else {
                        System.out.println("DisplayRole not Contains  RoleName");
                    }


                }
            }
        }

    }

    private void  updateCallColumnInfoByDataSetAndRoleName(String rolesname, String[] dateSetCodeAndFlag) {
        List<CallColumnInfoBean> callColumnInfoBeanList = callColumnInfoDao.selectCallColumnInfoByDateSet(dateSetCodeAndFlag[0]);

        if (callColumnInfoBeanList.size() >= 1) {

            for (CallColumnInfoBean callColumnInfoBean : callColumnInfoBeanList) {

                String displayRoleStr = "";
                if ("可见".equals(dateSetCodeAndFlag[1])) {

                    if (isDisplayRoleContainRoleName(callColumnInfoBean.getDisplaylevel(), rolesname)) {
                        System.out.println("DisplayRole Contains  RoleName");
                    } else {
                        displayRoleStr = getDisplayRoleStr(callColumnInfoBean.getDisplaylevel(),rolesname);

                        callColumnInfoBean.setDisplaylevel(displayRoleStr);
                        callColumnInfoDao.updateCallColumnInfo(callColumnInfoBean);
                    }

                } else {

                    if (isDisplayRoleContainRoleName(callColumnInfoBean.getDisplaylevel(), rolesname)) {
                        displayRoleStr = removeRoleNameFromDisplayRoles(callColumnInfoBean.getDisplaylevel(), rolesname);

                        callColumnInfoBean.setDisplaylevel(displayRoleStr);
                        callColumnInfoDao.updateCallColumnInfo(callColumnInfoBean);
                    } else {
                        System.out.println("DisplayRole not Contains  RoleName");
                    }

                }
            }
        }
    }

    private String getDisplayRoleStr(String displaylevel, String rolesname) {

        if(isNotContainCommaInTheEnd(displaylevel)){
            displaylevel = displaylevel+",";
        }

        if (displaylevel.length() >= 1) {
            return String.format(displaylevel + "%s,", rolesname);
        } else {
            return rolesname+",";
        }

    }

    private String removeRoleNameFromDisplayRoles(String displaylevel, String rolesname) {
        String displayRoleStr = "";
        List<String> displayRoleList = Arrays.asList(displaylevel.split(","));

        for (String displayRole : displayRoleList) {
             if (rolesname.equals(displayRole)) continue;
            displayRoleStr = String.format(displayRoleStr+",%s", displayRole);
        }

        return displayRoleStr.length() >= 1 ? displayRoleStr.substring(1)+"," : "";
    }

    private boolean isDisplayRoleContainRoleName(String displaylevel, String rolesname) {
        return Arrays.asList(displaylevel.split(",")).contains(rolesname);
    }


    private boolean isContainDownload(String usermentStr) {
        return Arrays.asList(usermentStr.split(",")).contains("下载");
    }


    private boolean isRoleChanged(RoleDTO roleDto, Roles roles) {
        return !roles.getPlatformMark().equals(roleDto.getPlatformMark()) || !roles.getDescription().equals(roleDto.getDescription()) || !roles.getRoleComment().equals(roleDto.getRoleComment()) || roles.getDownloadlevel() != roleDto.getDownloadlevel();
    }

    @Override
    public List<RoleDTO> getRolesByUserName(String userName) {

        List<RoleDTO> roleDTOList = new ArrayList<>();
        List<Authorities> authoritiesList = authDao.selectByUserName(userName);

        RoleDTO roleDTO;
        for (Authorities authorities1 : authoritiesList) {
            Set<String> roleNameSet = new HashSet<String>();

            for (String roleName : authorities1.getAuthority().split(",")) {
                roleNameSet.add(roleName);
            }


            List<Roles> rolesList = roleDao.selectByRoleNameSet(roleNameSet);

            for (Roles roles1 : rolesList) {

                roleDTO = new RoleDTO();
                roleDTO.setId(roles1.getId());
                roleDTO.setRolesname(roles1.getRolesname());
                roleDTO.setDescription(roles1.getDescription());
                roleDTO.setDownloadlevel(roles1.getDownloadlevel());
                roleDTO.setRolelevel(roles1.getRolelevel());
                roleDTO.setRoleComment(roles1.getRoleComment());
                roleDTO.setPlatformMark(roles1.getPlatformMark());

                roleDTOList.add(roleDTO);
            }

        }


        return roleDTOList;
    }

    @Override
    public List<DataSetBean> getSensitiveSetField(String platformMark, String roleName) {
        List<DataSetBean> dataSetBeanList = dataSetDao.selectByPlatform(platformMark);

        if ("cardservice".equals(platformMark)) {
            getDataSetBeanListByCardservice(roleName, dataSetBeanList);
        } else if ("synthetical".equals(platformMark)) {
            getDataSetBeanListBySynthetical(roleName, dataSetBeanList);
        } else {
            System.out.println("[未知的数据平台 ：" + platformMark + "]");
        }




        return dataSetBeanList;
    }

    private void getDataSetBeanListBySynthetical(String roleName, List<DataSetBean> dataSetBeanList) {
        List<DataSetBean> removeDataSetList = new ArrayList<>();
        for (DataSetBean dataSetBean : dataSetBeanList) {
            if (setCallColumnInfoDao.selectCountByDateSet(dataSetBean.getDataSetCode()) <= 0) {
                removeDataSetList.add(dataSetBean);
            }
        }
        dataSetBeanList.removeAll(removeDataSetList);

        for(DataSetBean dataSetBean : dataSetBeanList){
            if(isDataSetDisplayBySynthetical(dataSetBean.getDataSetCode(),roleName)){
                dataSetBean.setDisplay(true);
            }
        }
    }

    private boolean isDataSetDisplayBySynthetical(String dataSetCode, String roleName) {
        int count = setCallColumnInfoDao.selectCountByDateSetAndRoleName(dataSetCode,roleName);
        return count >=1? true :false;
    }

    private void getDataSetBeanListByCardservice(String roleName, List<DataSetBean> dataSetBeanList) {
        List<DataSetBean> removeDataSetList = new ArrayList<>();
        for (DataSetBean dataSetBean : dataSetBeanList) {
            if (callColumnInfoDao.selectCountByDateSet(dataSetBean.getDataSetCode()) <= 0) {
                removeDataSetList.add(dataSetBean);
            }
        }
        dataSetBeanList.removeAll(removeDataSetList);

        for(DataSetBean dataSetBean : dataSetBeanList){
            if(isDataSetDisplay(dataSetBean.getDataSetCode(),roleName)){
                dataSetBean.setDisplay(true);
            }
        }
    }

    private Set<String> getUserRolesSetWithPlatformAndUserName(String platformMark, String userName) {
            Set<String> userRolesSetWithPlatformAndUserName = new HashSet<>();

              List<Authorities> authoritiesList = authDao.selectByUserName(userName);
              Set<String> roleNameSetWithPlatform = roleDao.selectRoleNameByPlatformMark(platformMark);


              if (authoritiesList.size() ==1){
                    for (String roleName : authoritiesList.get(0).getAuthority().split(",")){
                        if(roleNameSetWithPlatform.contains(roleName)){
                            userRolesSetWithPlatformAndUserName.add(roleName);
                        }
                    }
              }else{
                  System.out.println(" authoritiesList.size() = "+authoritiesList.size());
              }

           return  userRolesSetWithPlatformAndUserName;
    }

    private boolean isDataSetDisplay(String dataSetCode, String roleName) {
        int count = callColumnInfoDao.selectCountByDateSetAndRoleName(dataSetCode,roleName);

        return count >=1? true :false;
    }

    @Override
    public List<String> getRoleNamesByUserName(String userName) {
        List<Authorities> authoritiesList = authDao.selectByUserName(userName);
        return Arrays.asList(authoritiesList.get(0).getAuthority().split(","));
    }
}
