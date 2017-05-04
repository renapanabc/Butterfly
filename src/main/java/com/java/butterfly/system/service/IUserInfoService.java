package com.java.butterfly.system.service;

import java.util.Map;

import com.java.butterfly.common.dto.ResultMsg;
import com.java.butterfly.system.dto.UserInfoDTO;
import com.java.butterfly.system.entity.SysUserInfo;

public interface IUserInfoService {
    /**
     * TODO(userinfo查询)    
     * @author xl    
     * @Title: queryListByPage    
     * @param userinfo
     * @return 
     * @Return: Map 返回值
     */
    public Map queryListByPage(UserInfoDTO userinfoDTO);
    
    /**
     * TODO(get user by Id)
     * @author xl    
     * @Title: getUserById    
     * @param user
     * @return 
     * @Return: ResultMsg 返回值
     */
    public ResultMsg getUserById(String id);
    
    /**
     * TODO(delete users)    
     * @author xl    
     * @Title: deleteUsers    
     * @param usersId
     * @return 
     * @Return: ResultMsg 返回值
     */
    public ResultMsg deleteUsers(String usersId);
    
    /**
     * TODO(add user)    
     * @author xl    
     * @Title: addUser    
     * @param user
     * @return 
     * @Return: ResultMsg 返回值
     */
    public ResultMsg addUser(SysUserInfo user) throws Exception;
    
    /**
     * TODO(update)    
     * @author xl    
     * @Title: updateUser    
     * @param user
     * @return 
     * @Return: ResultMsg 返回值
     */
    public ResultMsg updateUser(SysUserInfo user) throws Exception;
    
    /**
     *根据用户名查询用户 
     * @author xulu    
     * @date: 2017年4月29日 下午2:32:24
     * @Title: queryByUserName    
     * @param userName
     * @return SysUserInfo 返回值
     */
    public SysUserInfo queryByUserName(String userName);
}
