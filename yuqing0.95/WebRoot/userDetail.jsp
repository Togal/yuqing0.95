<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户详情</title>
    <link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
  	<link rel="stylesheet" href="source/css/common.css" type="text/css"></link>
	
  	<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
  	<script type="text/javascript" src="source/js/data.js"></script>
  	<script type="text/javascript" src="source/js/datajs.js"></script>
  	<script type="text/javascript">
		$(document).ready(function(){
			var p = "${provice}";
			var c = "${city}";
			var h = "${hometown}";
			init("province",p,"city",c,"hometown",h);
		});
	</script>
	<style type="text/css">
		table{
			text-align: center;
		}
	
	</style>
  </head>
  <body>    
  <div class="title">用户详情</div><hr>
    <div class="container">
    <form action="modifyDetailAction" method="post">
    	<table border="0" style="margin-top: 20px;border:1px solid #CCCCCC" align="center" width="70%">
    		<tr>
    			<td rowspan="3" width="160px"><img width="160px" height="175px" src="source/img/user.jpg"></td>
    			<td>用户</td>
    			<td><input class="form-control" id="disabledInput" name="username" disabled="disabled"
    				type="text" value="${user.name}"></td>
    		</tr>
    		<tr>
    			<td>昵称</td>
    			<td><input type="text" class="form-control" id="focusedInput" name=nick value="${user.nick}"></td>
    		</tr>
    		<tr>
    			<td>地区</td>
    			<td>
    				<select class="form-control" id="province" name =province></select>
					<select class="form-control" id="city" name = city></select>
					<select class="form-control" id="hometown" name = hometown></select>
				</td>
    		</tr>
    		<tr>
    			<td rowspan="2">[${user.role.name}]</td>
    			<td>电话</td>
    			<td><input class="form-control" type="text" name=phone value="${user.phone }"></td>
    		</tr>
    		<tr>
    			<td colspan="2"><input class="btn btn-default" type="submit"  value="修改"/></td>
    		</tr>
    	 </table>
    	 </form>
      </div>
  </body>
</html>
