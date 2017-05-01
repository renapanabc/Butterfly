package com.java.butterfly.system.entity;

import java.util.Date;

public class SysResourcesInfo {
    private String resourceCode;
    
    private String resourceName;
    
    private String status;
    
    private Date createDate;
    
    private Date modifyDate;
    
    public String getResourceCode() {
        return resourceCode;
    }
    
    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }
    
    public String getResourceName() {
        return resourceName;
    }
    
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    public Date getCreateDate() {
        return createDate;
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
}