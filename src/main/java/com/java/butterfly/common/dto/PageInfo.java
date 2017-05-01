package com.java.butterfly.common.dto;

/**
 * TODO(界面分页信息)    
 * @ClassName: PageInfo    
 * @author 许路
 * @date: 2017年1月6日 上午11:37:58    
 * @version  v 1.0
 */
public class PageInfo {
    public int pageNo = 1;// 当前页编号
    
    public int pageSize = 10;// 每页显示条数
    
    public int getPageNo() {
        return pageNo;
    }
    
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
}