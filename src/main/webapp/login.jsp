.<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<jsp:include page="/views/common/common.jsp" />
	
<base href="<%=basePath%>">

<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- CSS START -->
<link rel="stylesheet" href="css/login/login.css">
<!-- CSS END -->

<style>  

</style>
</head>

<body background="img/login/xx.png" >

<form action="">
	<div class="input-group">
	  <span class="input-group-addon" id="basic-addon1"></span>
	  <input id="form-username" value="admin" type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1"/>
	</div>
	<br>
	<div class="input-group">
	  <span class="input-group-addon" id="basic-addon1"></span>
	  <input id="form-password" value="123456" type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon1" />
	</div>
	<br>
	<button type="button" style="width:280px;" class="btn btn-default" onclick="login();">登 录</button>
</form>
</body>


<!-- JS START -->
<script type="text/javascript" src="js/system/login.js"></script>
<!-- JS END -->

</html>
