package com.java.butterfly.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.butterfly.common.dto.CommonConstant;
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
    
    public Map queryListByPage(UserInfoDTO userinfoDTO) {
        if (null != userinfoDTO.getKeyWords()) {
            userinfoDTO.setKeyWords("%" + userinfoDTO.getKeyWords() + "%");
        }
        return TabelData.tabelDataResult(sysuserinfoMapper
            .queryListByPageCount(userinfoDTO.getKeyWords()), sysuserinfoMapper.queryListByPage(userinfoDTO));
    }
    
    public ResultMsg getUserById(String userId) {
        if (EmptyUtils.isEmpty(userId)) {
            return new ResultMsg(false, "id为空，无法查询");
        }
        SysUserInfo userinfo = new SysUserInfo();
        userinfo.setUserId(Long.valueOf(userId));
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
    
    public ResultMsg addUser(SysUserInfo user) throws Exception {
        String pwd = EmptyUtils.isEmpty(user.getUserPwd()) ? CommonConstant.DEFAULT_PASSWORD : user.getUserPwd();
        user.setUserPwd(Md5Encryption.MD5Encription(pwd));
        SysUserInfo sysuser = new SysUserInfo();
        BeanUtils.copyProperties(user, sysuser);
        int total = sysuserinfoMapper.insert(sysuser);
        if (total == 1) {
            return new ResultMsg(true, "添加成功");
        }
        return new ResultMsg(false, "添加失败");
    }
    
    public ResultMsg updateUser(SysUserInfo user) throws Exception {
        
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
