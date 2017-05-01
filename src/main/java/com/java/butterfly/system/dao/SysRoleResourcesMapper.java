package com.java.butterfly.system.dao;

import com.java.butterfly.system.entity.SysRoleResources;

public interface SysRoleResourcesMapper {
    int insert(SysRoleResources record);
    
    int insertSelective(SysRoleResources record);
    
    int deleteByRoleId(int roleId);
}