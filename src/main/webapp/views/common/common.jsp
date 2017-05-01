<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<!-- CSS START  -->
<link rel="stylesheet" type="text/css" href="plugins/bootstrap/v3.3.7/css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="plugins/bootstrap/v3.3.7/css/bootstrap-theme.min.css" >
<link rel="stylesheet" type="text/css" href="plugins/jquery-confirm/jquery-confirm.css" />
<link rel="stylesheet" type="text/css" href="css/common/common.css" />

<!-- 	CSS END -->

</head>

<body>
</body>

<!-- JS START -->
<script type="text/javascript" src="plugins/jquery/jquery-1.12.2.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap/v3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="plugins/jquery-confirm/jquery-confirm.js"></script>
<script type="text/javascript" src="js/common/common.js"></script>

</head>
<!-- JS END -->

</html>