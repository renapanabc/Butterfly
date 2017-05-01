package com.java.butterfly.system.service;

import java.util.List;

import com.java.butterfly.system.entity.SysUserInfo;
import com.java.butterfly.system.entity.SysUserResources;

public interface IUserResourcesService {
    /**
     * TODO(加载用户权限)    
     * @author xl    
     * @Title: loadUserResources    
     * @param userinfo
     * @return 
     * @Return: String 返回值
     */
    List<SysUserResources> loadUserResources(SysUserInfo userinfo);
    
}
