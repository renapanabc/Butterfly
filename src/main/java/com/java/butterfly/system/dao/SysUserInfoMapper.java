package com.java.butterfly.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.butterfly.system.dto.UserInfoDTO;
import com.java.butterfly.system.entity.SysUserInfo;

public interface SysUserInfoMapper {
    int insert(SysUserInfo record);
    
    int insertSelective(SysUserInfo record);
    
    int deleteByPrimaryKey(int userId);
    
    int deletebyIds(int userIds);
    
    int update(SysUserInfo record);
    
    SysUserInfo queryByUserName(@Param(value = "userName") String userName);
    
    List<SysUserInfo> selectByExample(SysUserInfo record);
    
    List<SysUserInfo> queryListByPage(UserInfoDTO userinfo);
    
    int queryListByPageCount(UserInfoDTO userinfo);
}