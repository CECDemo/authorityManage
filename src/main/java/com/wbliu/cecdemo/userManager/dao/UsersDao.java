package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.dto.UserDTO;
import com.wbliu.cecdemo.userManager.pojo.Usermenu;
import com.wbliu.cecdemo.userManager.pojo.Users;
import com.wbliu.cecdemo.userManager.pojo.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface UsersDao {
   Users selectUserByUserNameAndPassword(String userName,String password);



    List<Users> selectByUserName(String userName);

    int insert(Users users);

    int updateUserPassword(String username, String password, String newPassword1);

    int deleteByUserName(String userName);


    Users  selectByUUID(String uuid);

 int  updateUserByUUID(Users users1);


    List<Users>  selectUserWithNoRole();

    List<Users>  selectUsers();
}