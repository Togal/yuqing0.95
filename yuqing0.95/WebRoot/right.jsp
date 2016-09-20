<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>最近操作</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
	body,div,ul,li {
		list-style-type: none;
		margin: 0px auto;
		padding: 0px;
	}
	.title{
		margin-top:30px;
		font-family: 微软雅黑;
		font-size:15px;
	}
	.item ul{
	}
	.item ul li{
		list-style-type:none;
		margin-top:20px;
		margin-left:15px;
		font-family:微软雅黑;
		font-size:12px;
	}
	.item ul li a{
		color:black;
		text-decoration: none;
	}
	.item ul li a:hover{
		text-decoration: underline;
	}
</style>
</head>

<body>
	<div class="title">最近操作</div>
	<div>
		<div class="item">
			<ul id="opera">
			</ul>
		</div>
	</div>
</body>
</html>
