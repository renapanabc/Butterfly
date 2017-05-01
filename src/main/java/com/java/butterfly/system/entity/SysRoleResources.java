package com.java.butterfly.system.entity;

public class SysRoleResources {
    private String roleId;
    
    private String resourcesCode;
    
    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
    
    public String getResourcesCode() {
        return resourcesCode;
    }
    
    public void setResourcesCode(String resourcesCode) {
        this.resourcesCode = resourcesCode == null ? null : resourcesCode.trim();
    }
}