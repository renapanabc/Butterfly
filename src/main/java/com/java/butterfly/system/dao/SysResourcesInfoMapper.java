package com.java.butterfly.system.dao;

import java.math.BigDecimal;
import java.util.List;

import com.java.butterfly.system.entity.SysResourcesInfo;

public interface SysResourcesInfoMapper {
    int deleteByPrimaryKey(BigDecimal resourceId);
    
    int insert(SysResourcesInfo record);
    
    int insertSelective(SysResourcesInfo record);
    
    List<SysResourcesInfo> selectByExample(SysResourcesInfo example);
    
    SysResourcesInfo selectByPrimaryKey(BigDecimal resourceId);
    
    int updateByPrimaryKeySelective(SysResourcesInfo record);
    
    int updateByPrimaryKey(SysResourcesInfo record);
}