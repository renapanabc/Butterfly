package com.java.butterfly.system.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.butterfly.common.util.HttpRequestUtil;
import com.java.butterfly.system.dto.UserInfoDTO;
import com.java.butterfly.system.entity.SysUserInfo;
import com.java.butterfly.system.service.IUserInfoService;

@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController {
    private static Logger logger = Logger.getLogger(UserInfoController.class);
    
    @Resource
    private IUserInfoService userInfoService;
    
    @RequestMapping(value = "/userInfoPage")
    public ModelAndView userInfoPage(HttpServletRequest request) throws Exception {
        return new ModelAndView("views/system/userInfo");
    }
    
    @ResponseBody
    @RequestMapping(value = "/getUsersList")
    public Object getUsersList(HttpServletRequest request, UserInfoDTO userinfoDTO) throws Exception {
        HttpRequestUtil.requestParametersShowLog(request);
        return userInfoService.queryListByPage(userinfoDTO);
    }
    
    @ResponseBody
    @RequestMapping(value = "/getUserById")
    public Object getUserById(HttpServletRequest request) throws Exception {
        return userInfoService.getUserById(request.getParameter("userId"));
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteUsers")
    public Object deleteUsers(HttpServletRequest request) throws Exception {
        String usersId = request.getParameter("usersId");
        return userInfoService.deleteUsers(usersId);
    }
    
    @ResponseBody
    @RequestMapping(value = "/addUser")
    public Object addUser(HttpServletRequest request, SysUserInfo userinfo) throws Exception {
        return userInfoService.addUser(userinfo);
    }
    
    @ResponseBody
    @RequestMapping(value = "/updateUser")
    public Object updateUser(HttpServletRequest request, SysUserInfo userinfo) throws Exception {
        return userInfoService.updateUser(userinfo);
    }
}
