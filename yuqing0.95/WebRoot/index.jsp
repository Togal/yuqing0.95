<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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

<title>舆情首页</title>
<link rel="shortcut icon" type="image/x-icon"
	href="source/img/favicon.ico">
<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css"
	type="text/css"></link>

<link type="text/css" rel="styleSheet" href="source/css/top.css">
<style type="text/css">
	ul li{
		list-style-type: none;
	}
</style>
<!-- import js -->
<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		resize();
	});
	$(window).resize(function(){  
		resize();
	}); 
	function resize(){
		$('#main').height($(window).height()-70);
	}
</script>
</head>

<body style="overflow: hidden">
	<div class="top-container">
		<div style="width:1200px;height:40px;margin: 0px auto"><jsp:include
				page="top.jsp"></jsp:include></div>
		<div class="topBorder"></div>
	</div>

	<!-- left main-->
	<div style="clear:both;width:100%;height: 100%;margin:0px auto;padding: 0px;">
		<!-- left -->
		<div style="height: 100%;float: left;width: 15%;min-width: 155px;">
			<div style="text-align: center;border:0px solid green;border-right: 1px solid #1AADCE;">
				<jsp:include page="left.jsp"></jsp:include>
			</div>
		</div>

		<!-- main -->
		<div style="height: 100%;float: left;width:85%;border:0px solid green;">
		 	 <iframe id="main" name="main" width="100%" src="home"
				style="border: 0px solid green;max-height: 700px"> </iframe>
		</div>
	</div>
</body>
</html>
