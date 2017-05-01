<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<jsp:include page="../common/common.jsp" />
	<jsp:include page="../common/topMenus.jsp" />
	<jsp:include page="../common/common_table_plugins.jsp" />
	
<base href="<%=basePath%>">

<title>用户信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">

</style>
</head>

<body>
    <div class="container">
        <h3>用户信息</h3>
        <table id="table"
               data-toggle="table"
               data-height="460"
               data-toolbar="#toolbar"
               data-search="true"
               data-show-refresh="true"
               data-show-toggle="true"
               data-show-columns="true"
               data-side-pagination="server"
               data-pagination="true" 
               data-page-list="[5, 10, 20, 50, 100, 200]"
               data-url="userInfo/getUsersList">
            <thead>
	            <tr>
	                <th data-field="state" data-checkbox="true"></th>
	                <th data-field="userId" width="100">ID</th>
	                <th data-field="userName" >登录名</th>
	                <th data-field="userRealName" >姓名</th>
	                <th data-field="userPhone">电话</th>	
	                <th data-field="userDesc">描述</th>
	                <th data-field="createDate" data-formatter="tableDateFormatter">录入日期</th>
	            </tr>
            </thead>
        </table>
    </div>
</body>

<!-- JS START -->
<script type="text/javascript" src="js/system/userInfo.js"></script>

<script type="text/javascript">
</script>
<!-- JS END -->

</html>
