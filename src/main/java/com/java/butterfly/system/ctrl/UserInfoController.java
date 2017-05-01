package com.java.butterfly.system.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.butterfly.common.util.DataCollecter;
import com.java.butterfly.system.dto.UserInfoDTO;
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
    
    /**
     * TODO(获取用户信息列表)    
     * @author xl    
     * @Title: getUsersList    
     * @param request
     * @throws Exception 
     * @Return: Object 返回值
     */
    @ResponseBody
    @RequestMapping(value = "/getUsersList")
    public Object getUsersList(HttpServletRequest request) throws Exception {
        UserInfoDTO userinfo = (UserInfoDTO) DataCollecter.collectionData(request, UserInfoDTO.class);
        return userInfoService.queryListByPage(userinfo);
    }
    
    @ResponseBody
    @RequestMapping(value = "/getUserById")
    public Object getUserById(HttpServletRequest request) throws Exception {
        UserInfoDTO userinfo = (UserInfoDTO) DataCollecter.collectionData(request, UserInfoDTO.class);
        return userInfoService.getUserById(userinfo);
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteUsers")
    public Object deleteUsers(HttpServletRequest request) throws Exception {
        String usersId = request.getParameter("usersId");
        return userInfoService.deleteUsers(usersId);
    }
    
    @ResponseBody
    @RequestMapping(value = "/addUser")
    public Object addUser(HttpServletRequest request) throws Exception {
        UserInfoDTO userinfo = (UserInfoDTO) DataCollecter.collectionData(request, UserInfoDTO.class);
        return userInfoService.addUser(userinfo);
    }
    
    @ResponseBody
    @RequestMapping(value = "/updateUser")
    public Object updateUser(HttpServletRequest request) throws Exception {
        UserInfoDTO userinfo = (UserInfoDTO) DataCollecter.collectionData(request, UserInfoDTO.class);
        return userInfoService.updateUser(userinfo);
    }
}
