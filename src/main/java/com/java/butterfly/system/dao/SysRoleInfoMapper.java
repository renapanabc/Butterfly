package com.java.butterfly.system.dao;

import java.math.BigDecimal;
import java.util.List;

import com.java.butterfly.system.entity.SysRoleInfo;

public interface SysRoleInfoMapper {
    int deleteByPrimaryKey(BigDecimal roleId);
    
    int insert(SysRoleInfo record);
    
    int insertSelective(SysRoleInfo record);
    
    List<SysRoleInfo> selectByExample(SysRoleInfo example);
    
    SysRoleInfo selectByPrimaryKey(BigDecimal roleId);
    
    int updateByPrimaryKeySelective(SysRoleInfo record);
    
    int updateByPrimaryKey(SysRoleInfo record);
}