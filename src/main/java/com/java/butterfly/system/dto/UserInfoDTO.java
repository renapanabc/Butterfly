package com.java.butterfly.system.dto;

import com.java.butterfly.common.dto.PageInfo;

public class UserInfoDTO extends PageInfo {
    
    public Long UserId;
    
    public String userName;
    
    public String userRealName;
    
    public String roleName;
    
    public String userPwd;
    
    public String userDesc;
    
    public Long getUserId() {
        return UserId;
    }
    
    public void setUserId(Long userId) {
        UserId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserRealName() {
        return userRealName;
    }
    
    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getUserPwd() {
        return userPwd;
    }
    
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    
    public String getUserDesc() {
        return userDesc;
    }
    
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }
    
}
