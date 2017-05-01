package com.java.butterfly.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.java.butterfly.common.dto.ResultMsg;
import com.java.butterfly.common.util.EmptyUtils;
import com.java.butterfly.common.util.encrypt.Md5Encryption;
import com.java.butterfly.system.ctrl.UserLoginController;
import com.java.butterfly.system.dao.SysUserInfoMapper;
import com.java.butterfly.system.entity.SysUserInfo;
import com.java.butterfly.system.service.IUserLoginService;

@Service
public class UserLoginServiceImpl implements IUserLoginService {
    //    Log4j
    private static Logger logger = Logger.getLogger(UserLoginController.class);
    
    @Resource
    private SysUserInfoMapper sysuserinfoMapper;
    
    @Override
    public ResultMsg loginCheck(String userName, String userPwd) {
        logger.info("**用户登录**" + userName);
        // TODO Auto-generated method stub
        if (EmptyUtils.isEmpty(userName) || EmptyUtils.isEmpty(userPwd)) {
            return new ResultMsg(false, "用户名密码不能为空!");
        }
        SysUserInfo record = new SysUserInfo();
        record.setUserName(userName);
        List<SysUserInfo> userslist = this.sysuserinfoMapper.selectByExample(record);
        if (EmptyUtils.isEmpty(userslist)) {
            logger.info("****找不到此用户****" + userName);
            return new ResultMsg(false, "找不到此用户!");
        }
        if (userslist.size() > 1) {
            logger.error(userName + "登录异常，存在多个用户!");
            return new ResultMsg(false, "账号登录异常!");
        }
        SysUserInfo user = userslist.get(0);
        String encriptPwd = Md5Encryption.MD5Encription(userPwd);
        if (EmptyUtils.isEmpty(encriptPwd)) {
            throw new RuntimeException("获取密码失败!");
        }
        if (!encriptPwd.equals(user.getUserPwd())) {
            return new ResultMsg(false, "错误的密码");
        } else {
            logger.info("****登录成功****" + userName);
            return new ResultMsg(true, userslist.get(0));
        }
    }
}
