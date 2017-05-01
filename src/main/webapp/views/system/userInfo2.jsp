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
<link rel="stylesheet" type="text/css" href="http://issues.wenzhixin.net.cn/bootstrap-table/assets/bootstrap/css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="http://issues.wenzhixin.net.cn/bootstrap-table/assets/bootstrap-table/src/bootstrap-table.css" >
<link rel="stylesheet" type="text/css" href="http://issues.wenzhixin.net.cn/bootstrap-table/assets/examples.css" >
<link rel="stylesheet" type="text/css" href="" >
<link rel="stylesheet" type="text/css" href="" >
<!-- 	CSS END -->
    <style>
        .w70 {width: 70px !important;}
    </style>
<!-- JS START -->
<script type="text/javascript" src="http://issues.wenzhixin.net.cn/bootstrap-table/assets/jquery.min.js"></script>
<script type="text/javascript" src="http://issues.wenzhixin.net.cn/bootstrap-table/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://issues.wenzhixin.net.cn/bootstrap-table/assets/bootstrap-table/src/bootstrap-table.js"></script>
<script type="text/javascript" src="http://issues.wenzhixin.net.cn/bootstrap-table/ga.js"></script>
<script type="text/javascript" src=""></script>

	
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
        <h1>Custom Toolbar</h1>
        <p>Use <code>toolbar</code> option to define the custom toolbars.</p>
        <div id="toolbar">
            <div class="form-inline" role="form">
                <div class="form-group">
                    <span>Offset: </span>
                    <input name="offset" class="form-control w70" type="number" value="0">
                </div>
                <div class="form-group">
                    <span>Limit: </span>
                    <input name="limit" class="form-control w70" type="number" value="5">
                </div>
                <div class="form-group">
                    <input name="search" class="form-control" type="text" placeholder="Search">
                </div>
                <button id="ok" type="submit" class="btn btn-default">OK</button>
            </div>
        </div>
        <table id="table"
               data-toggle="table"
               data-height="460"
               data-toolbar="#toolbar"
               data-show-refresh="true"
               data-show-toggle="true"
               data-show-columns="true"
               data-query-params="queryParams"
               data-response-handler="responseHandler"
               data-url="/examples/bootstrap_table/data">
            <thead>
            <tr>
                <th data-field="id">ID</th>
                <th data-field="name">Item Name</th>
                <th data-field="price">Item Price</th>
            </tr>
            </thead>
        </table>
    </div>

<script>
    var $table = $('#table'),
        $ok = $('#ok');

    $(function () {
        $ok.click(function () {
            $table.bootstrapTable('refresh');
        });
    });

    function queryParams() {
        var params = {};
        $('#toolbar').find('input[name]').each(function () {
            params[$(this).attr('name')] = $(this).val();
        });
        return params;
    }

    function responseHandler(res) {
        return res.rows;
    }
</script>
</body>
</html>