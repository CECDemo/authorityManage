package com.wbliu.cecdemo.userManager.dao;

import com.wbliu.cecdemo.userManager.pojo.Authorities;
import com.wbliu.cecdemo.userManager.pojo.AuthoritiesExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface AuthoritiesDao {
    int countByExample(AuthoritiesExample example);

    int deleteByExample(AuthoritiesExample example);

    int deleteByPrimaryKey(Integer id);

    boolean updateAuthoritiesByKey(Integer id, String roleNameStr);

    int insert(Authorities record);

    int insertSelective(Authorities record);

    List<Authorities> selectByExample(AuthoritiesExample example);

    Authorities selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Authorities record, @Param("example") AuthoritiesExample example);

    int updateByExample(@Param("record") Authorities record, @Param("example") AuthoritiesExample example);

    int updateByPrimaryKeySelective(Authorities record);

    int updateByPrimaryKey(Authorities record);



    List<Authorities> selectLikeRoleName(String roleName);


    List<Authorities> selectByUserName(String userName);

    int deleteByUserName(String userName);

    boolean  updateAuthoritiesByRoleName(String roleName);

}