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
	
<base href="<%=basePath%>">

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</style>
</head>

<body>
 <div style="padding: 0px 100px 10px;">
	<div class="input-group">
		<span class="input-group-addon">姓名</span>
		<input type="text" class="form-control" placeholder="">
	</div>
	<br>
	<div class="input-group">
		<span class="input-group-addon">账户</span>
		<input type="text" class="form-control" placeholder="">
	</div>
	<br>
	<div class="input-group">
		<span class="input-group-addon">密码</span>
		<input type="text" class="form-control" placeholder="">
	</div>
	<br>
	<div class="input-group">
		<span class="input-group-addon">电话</span>
		<input type="text" class="form-control" placeholder="">
	</div>
	<br>
	<div class="input-group">
		<span class="input-group-addon">备注</span>
		<textarea class="form-control" rows="3"></textarea>
	</div>
	<br>
	<div class="btn-toolbar" role="toolbar">
		<div class="btn-group">
		    <button type="button" class="btn btn-default">确认</button>
		</div>
		<div class="btn-group">
		    <button type="button" class="btn btn-default" >取消</button>
		</div>
	</div>

</body>
 <%
	    request.setCharacterEncoding("utf-8");
	    String operaType = request.getParameter("operaType"); 
  %> 
<script type="text/javascript">
	//定义操作模式，add or edit
	var operaType = '<%=operaType%>';  
	$(function () {
		if("add"==operaType){
			alert('add');
		}else if("edit"==operaType){
			
		}
	});
	  
</script>

</html>
