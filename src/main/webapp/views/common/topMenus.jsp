<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="plugins/submenu/bootstrap-submenu.min.css">
<link rel="stylesheet" href="css/common/topMenus.css">

<script src="plugins/submenu/bootstrap-submenu.min.js" ></script>

<script type="text/javascript">

</script>

<base href="<%=basePath%>">

 <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">


</head>
<body>
   <nav class="navbar navbar-default">
        <div class="navbar-header">
       		 <a class="navbar-brand" href="<%=basePath%>views/system/main.jsp">Butterfly </a>
        </div>
        <div class="collapse navbar-collapse">
	        <ul class="nav navbar-nav" id="menusContent">
	        	<shiro:hasPermission name="resource_manage_add">
	        	<li class="dropdown"><a tabindex="0" data-toggle="dropdown" data-submenu="">  系统管理<span class="caret"></span></a>
			        <ul class="dropdown-menu">
	 					<shiro:hasPermission name="resource_manage_add">
				        	<li class="divider"></li>
					        <li><a tabindex="0" href="userInfo/userInfoPage">用户管理</a></li>
	 					</shiro:hasPermission>	
	 					
	 					<shiro:hasPermission name="resource_manage_add">
				        	<li class="divider"></li>
				        	<li><a tabindex="0">角色管理</a></li>
	 					</shiro:hasPermission>	
	 					
	 					<shiro:hasPermission name="resource_manage_add">
				        	<li class="divider"></li>
				        	<li><a tabindex="0">资源管理</a></li>
	 					</shiro:hasPermission>	
	 					
	 					<shiro:hasPermission name="resource_manage_add">
				        	<li class="divider"></li>
				        	<li><a tabindex="0">权限管理</a></li>
	 					</shiro:hasPermission>	
			        </ul>
	        	</li>
	 			</shiro:hasPermission>	
	        	
<!-- 	        	<li class="dropdown"><a tabindex="0" data-toggle="dropdown" data-submenu="">  TEST2<span class="caret"></span></a> -->
<!-- 			        <ul class="dropdown-menu"> -->
<!-- 			        	<li class="dropdown-submenu"><a tabindex="0">AAAA</a> -->
<!-- 					        <ul class="dropdown-menu"> -->
<!-- 						        <li><a tabindex="0">aaa1</a></li> -->
<!-- 						        <li class="dropdown-submenu"><a tabindex="0">aaa2</a> -->
<!-- 							        <ul class="dropdown-menu"> -->
<!-- 								        <li><a tabindex="0">a21</a></li> -->
<!-- 								        <li><a tabindex="0">a22</a></li> -->
<!-- 								        <li><a tabindex="0">a23</a></li> -->
<!-- 							        </ul> -->
<!-- 					        	</li> -->
<!-- 					        	<li><a tabindex="0">aaa3</a></li> -->
<!-- 					        	<li class="disabled"><a tabindex="-1">aaa4</a></li> -->
<!-- 					        	<li class="dropdown-submenu"><a tabindex="0">aaa5</a> -->
<!-- 							        <ul class="dropdown-menu"> -->
<!-- 								        <li><a tabindex="0">a51</a></li> -->
<!-- 								        <li><a tabindex="0">a52</a></li> -->
<!-- 								        <li><a tabindex="0">a53</a></li> -->
<!-- 							        </ul> -->
<!-- 					        	</li> -->
<!-- 					        </ul> -->
<!-- 			        	</li> -->
<!-- 				        <li class="divider"></li> -->
<!-- 			        	<li class="dropdown-submenu"><a tabindex="0">BBBB</a> -->
<!-- 					        <ul class="dropdown-menu"> -->
<!-- 						        <li><a tabindex="0">b1</a></li> -->
<!-- 						        <li><a tabindex="0">b2</a></li> -->
<!-- 						        <li><a tabindex="0">b3</a></li> -->
<!-- 					        </ul> -->
<!-- 			       		</li> -->
<!-- 				        <li class="divider"></li> -->
<!-- 				        <li><a tabindex="0">CCCC</a></li> -->
<!-- 				        <li class="divider"></li> -->
<!-- 				        <li><a tabindex="0">DDDD</a></li> -->
<!-- 			        </ul> -->
<!-- 	        	</li> -->
	        
	        	
	        </ul>
	        
	        <ul class="nav navbar-nav navbar-right">
              <li><a href="<%=basePath%>userLogin/signOut">退出</a></li>
            </ul>
        </div>
    </nav>

</body></html>