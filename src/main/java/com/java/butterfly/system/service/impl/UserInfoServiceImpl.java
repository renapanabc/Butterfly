package com.java.butterfly.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.butterfly.common.dto.ResultMsg;
import com.java.butterfly.common.util.BeanUtils;
import com.java.butterfly.common.util.EmptyUtils;
import com.java.butterfly.common.util.TabelData;
import com.java.butterfly.common.util.encrypt.Md5Encryption;
import com.java.butterfly.system.dao.SysUserInfoMapper;
import com.java.butterfly.system.dto.UserInfoDTO;
import com.java.butterfly.system.entity.SysUserInfo;
import com.java.butterfly.system.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    
    @Resource
    public SysUserInfoMapper sysuserinfoMapper;
    
    public Map queryListByPage(UserInfoDTO userinfo) {
        if (null != userinfo.getUserName()) {
            userinfo.setUserName("%" + userinfo.getUserName() + "%");
        }
        return TabelData.tabelDataResult(sysuserinfoMapper.queryListByPageCount(userinfo), sysuserinfoMapper
            .queryListByPage(userinfo));
    }
    
    public ResultMsg getUserById(UserInfoDTO user) {
        if (EmptyUtils.isEmpty(user.getUserId())) {
            return new ResultMsg(false, "id为空，无法查询");
        }
        SysUserInfo userinfo = new SysUserInfo();
        userinfo.setUserId(user.getUserId());
        List<SysUserInfo> usersList = sysuserinfoMapper.selectByExample(userinfo);
        if (EmptyUtils.isEmpty(usersList)) {
            return new ResultMsg(false, "未返回结果集");
        }
        if (usersList.size() > 1) {
            return new ResultMsg(false, "返回多个结果集");
        }
        return new ResultMsg(true, usersList.get(0));
    }
    
    public ResultMsg deleteUsers(String usersId) {
        
        return new ResultMsg();
    }
    
    public ResultMsg addUser(UserInfoDTO user) throws Exception {
        String pwd = EmptyUtils.isEmpty(user.getUserPwd()) ? "123456" : user.getUserPwd();
        user.setUserPwd(Md5Encryption.MD5Encription(pwd));
        SysUserInfo sysuser = new SysUserInfo();
        BeanUtils.copyProperties(user, sysuser);
        int total = sysuserinfoMapper.insert(sysuser);
        if (total == 1) {
            return new ResultMsg(true, "添加成功");
        }
        return new ResultMsg(false, "添加失败");
    }
    
    public ResultMsg updateUser(UserInfoDTO user) throws Exception {
        
        SysUserInfo sysuser = new SysUserInfo();
        BeanUtils.copyProperties(user, sysuser);
        int total = sysuserinfoMapper.update(sysuser);
        ;
        if (total == 1) {
            return new ResultMsg(true, "修改成功");
        }
        return new ResultMsg(false, "修改失败");
    }
    
    public SysUserInfo queryByUserName(String userName) {
        if (EmptyUtils.isEmpty(userName)) {
            return null;
        }
        return this.sysuserinfoMapper.queryByUserName(userName);
    }
}
