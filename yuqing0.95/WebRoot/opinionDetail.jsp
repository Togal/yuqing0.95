<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPEhtmlPUBLIC"-//W3C//DTDXHTML1.1//EN"   "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>opinionDetail</title>
<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="source/css/common.css" type="text/css"></link>
<style type="text/css">
body div {
	margin: 0px;
	padding: 0px;
}
.hint {
	font-family: Microsoft YaHei;
	font-size: 12px;
	color:#B5AB00;
	float: right;
	margin-right: 20px;
}
.body {
	margin-top: 10px;
	font-family: Microsoft YaHei;
	font-size: 15px;
	line-height: 30px;
}
</style>
</head>

<body style="margin: 0 auto;min-width: 800px;width: 85%;max-width: 950px;">
	<div style="text-align: center;margin-bottom: 20px;">
		<div style="font-size: 18px;font-family: Microsoft YaHei;color:black;text-align: center;">${opinion.title }</div>
		<div class="hint">
			作者:${opinion.author}&nbsp;&nbsp;发布时间:${opinion.publishTime}
			<span >hehe</span>
		</div>	
	</div>
	<div class="body">&nbsp;&nbsp;&nbsp;&nbsp; ${opinion.content}</div>
	<div style="height: 50px;background-color:inherit;"></div>
	<div id="foot" class="btn-group btn-group-justified" style="position:fixed; bottom: 0px;min-width: 800px;width: 85%;max-width: 950px;">
		  <a class="btn btn-danger" href="opinionTrendCount?opinionId=${opinion.opinionId}">舆情趋势图</a>
		  <a class="btn btn-primary" href="opinionLocationCount?opinionId=${opinion.opinionId }&opinionTitle=${opinion.title}">舆情地区分布</a>
		  <a class="btn btn-success" href="${opinion.url}" target="_bank">来源网址</a>
		  <a class="btn btn-info" href="${opinion.website.homePage}" target="_bank">来源网站</a>
	</div>
</body>
</html>
