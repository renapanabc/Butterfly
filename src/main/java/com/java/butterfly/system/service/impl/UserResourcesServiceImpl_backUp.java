//package com.java.butterfly.system.service.impl;
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.java.butterfly.common.util.EmptyUtils;
//import com.java.butterfly.system.dao.SysUserResourcesMapper;
//import com.java.butterfly.system.entity.SysUserInfo;
//import com.java.butterfly.system.entity.SysUserResources;
//
//@Service
////public class UserResourcesServiceImpl_backUp implements IUserResourcesService {
//public class UserResourcesServiceImpl_backUp {
//    
//    private static final Logger logger = LoggerFactory.getLogger(UserResourcesServiceImpl_backUp.class);
//    
//    @Resource
//    private SysUserResourcesMapper sysUserResourcesMapper;
//    
//    //    资源名称占位符
//    private static final String resourceNamePlaceholder = " ${#resourceName}";
//    
//    private static final String aContentPlaceholder = "${#a-content}";
//    
//    private static final String liClassPlaceholder = "${#li-class}";
//    
//    private static final String aActionlaceholder = "${#action}";
//    
//    private static final String aUrlPlaceholder = "${#url}";
//    
//    //      组装资源
//    private static Map<String, String> resourceStrMap = null;
//    
//    //    初始化资源组装信息
//    static {
//        if (EmptyUtils.isEmpty(resourceStrMap)) {
//            resourceStrMap = new HashMap<String, String>();
//            resourceStrMap
//                .put("resourcesContent", "<li class='${#li-class}'><a tabindex='0' ${#a-content} onclick='mainMenusClick(${#action},${#url});'> ${#resourceName}</a>");
//            resourceStrMap.put("span", "<span class='caret'></span>");
//            resourceStrMap.put("a-content", " data-toggle='dropdown' data-submenu=''");
//            resourceStrMap.put("ul", "<ul class='dropdown-menu'>");
//            resourceStrMap.put("li-dropdown", "dropdown");
//            resourceStrMap.put("submenu-dropdown", "dropdown-submenu");
//            resourceStrMap.put("ul-end", "</ul>");
//            resourceStrMap.put("li-end", "</li>");
//            resourceStrMap.put("divider", "<li class='divider'></li>");
//        }
//    }
//    
//    /**
//     * 角色权限
//     * key 角色id
//     * val 权限str
//     */
//    private static Map<Object, String> roleResource = new HashMap<Object, String>();
//    
//    private String getRoleResource(Object roleId) {
//        logger.info("*******************获取角色权限********************roleId:" + roleId.toString());
//        return roleResource.get(roleId);
//    }
//    
//    private void setRoleResource(Object roleId, String resources) {
//        logger.info("*******************角色权限写入内存********************roleId:" + roleId.toString());
//        roleResource.put(roleId, resources);
//    }
//    
//    public String loadUserResources(SysUserInfo userInfo) {
//        // TODO Auto-generated method stub
//        if (null == userInfo) {
//            logger.error("***********************获取用户权限异常，用户信息为空*****************");
//            return null;
//        }
//        BigDecimal roleId = userInfo.getRoleId();
//        if (!roleResource.containsKey(roleId)) {
//            //admin用户
//            if ("admin".equals(userInfo.getUserName())) {
//                this.structureResources(this.sysUserResourcesMapper.loadUserResourcesForAdmin(), roleId);
//            }
//            this.structureResources(this.sysUserResourcesMapper.loadUserResources(roleId), roleId);
//        }
//        return this.getRoleResource(roleId);
//    }
//    
//    /**
//     * TODO(组装用户菜单)    
//     * @author xl    
//     * @Title: structureResources    
//     * @param list 权限list
//     * @param roleId  角色ID
//     * @Return: void 返回值
//     */
//    public void structureResources(List<SysUserResources> list, BigDecimal roleId) {
//        if (EmptyUtils.isEmpty(list)) {
//            this.setRoleResource(roleId, null);
//        }
//        StringBuffer finalReourcesStr = new StringBuffer();
//        for (int i = 0; i < list.size(); i++) {
//            SysUserResources currentRes = list.get(i);
//            int lastLevel = i == 0 ? 0 : Integer.parseInt(list.get(i - 1).getLevel());
//            int currentLevel = Integer.parseInt(currentRes.getLevel());
//            int nextLevel = i == list.size() - 1 ? -1 : Integer.parseInt(list.get(i + 1).getLevel());
//            String currentResContent = resourceStrMap.get("resourcesContent")
//                .replace(aActionlaceholder, "\"" + currentRes.getResourceType() + "\"")
//                .replace(aUrlPlaceholder, "\"" + currentRes.getRsourceAction() + "\"");
//            //            计算当前标签内容
//            if (currentLevel == 1) {
//                currentResContent = currentResContent
//                    .replace(resourceNamePlaceholder, currentRes.getResourceName() + resourceStrMap.get("span"))
//                    .replace(aContentPlaceholder, resourceStrMap.get("a-content"))
//                    .replace(liClassPlaceholder, resourceStrMap.get("li-dropdown"));
//            } else {
//                currentResContent = currentResContent.replace(resourceNamePlaceholder, currentRes.getResourceName());
//                if (nextLevel > currentLevel) {//存在子菜单
//                    currentResContent =
//                        currentResContent.replace(liClassPlaceholder, resourceStrMap.get("submenu-dropdown"));
//                }
//            }
//            //开始组装菜单
//            if (currentLevel == 1) {
//                if (currentLevel == nextLevel || nextLevel == -1) {//同级一级菜单,或者没有了
//                    finalReourcesStr = finalReourcesStr.append(currentResContent).append(resourceStrMap.get("li-end"));
//                }
//                finalReourcesStr = finalReourcesStr.append(currentResContent).append(resourceStrMap.get("ul"));
//            } else {
//                if (currentLevel == 2 && lastLevel != 1) {
//                    finalReourcesStr = finalReourcesStr.append(resourceStrMap.get("divider"));
//                }
//                if (nextLevel != -1) {//不是最后一个资源
//                    if (currentLevel > nextLevel) {//下个是上层资源
//                        //判断有几层
//                        int count = currentLevel - nextLevel;
//                        String templiend = resourceStrMap.get("li-end");
//                        for (int j = 0; j < count; j++) {
//                            templiend = templiend + resourceStrMap.get("ul-end") + resourceStrMap.get("li-end");
//                        }
//                        finalReourcesStr = finalReourcesStr.append(currentResContent).append(templiend);
//                    }
//                    if (currentLevel == nextLevel) {//下个是同级资源
//                        finalReourcesStr =
//                            finalReourcesStr.append(currentResContent).append(resourceStrMap.get("li-end"));
//                    }
//                    if (currentLevel < nextLevel) {//下个是子资源
//                        finalReourcesStr = finalReourcesStr
//                            .append(currentResContent
//                                .replace(liClassPlaceholder, resourceStrMap.get("submenu-dropdown")))
//                            .append(resourceStrMap.get("ul"));
//                    }
//                } else {//最后一个资源
//                    String templiend = resourceStrMap.get("li-end");
//                    for (int j = 0; j < currentLevel - 1; j++) {
//                        templiend = templiend + resourceStrMap.get("ul-end") + resourceStrMap.get("li-end");
//                    }
//                    finalReourcesStr = finalReourcesStr.append(currentResContent).append(templiend);
//                } //判断是否最后一个资源end 
//                finalReourcesStr = new StringBuffer(
//                    finalReourcesStr.toString().replace(liClassPlaceholder, "").replace(aContentPlaceholder, ""));
//            }
//        }
//        //放入内存
//        this.setRoleResource(roleId, finalReourcesStr.toString());
//    }
//}
