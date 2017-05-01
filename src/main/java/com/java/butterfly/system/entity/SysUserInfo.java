package com.java.butterfly.system.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SysUserInfo {
    
    private Long userId;
    
    private String userName;
    
    private String userRealName;
    
    private String userPwd;
    
    private String userPhone;
    
    private String userDesc;
    
    private BigDecimal roleId;
    
    private Integer status;
    
    private Date createDate;
    
    private Date modifyDate;
    
    private String roleName;
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
    
    public String getUserRealName() {
        return userRealName;
    }
    
    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }
    
    public String getUserPwd() {
        return userPwd;
    }
    
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }
    
    public String getUserPhone() {
        return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }
    
    public String getUserDesc() {
        return userDesc;
    }
    
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc == null ? null : userDesc.trim();
    }
    
    public BigDecimal getRoleId() {
        return roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Date getModifyDate() {
        return modifyDate;
    }
    
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}