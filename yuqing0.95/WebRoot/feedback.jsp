<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户反馈</title>
    
  <link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
  <link rel="stylesheet" href="source/css/common.css" type="text/css"></link>
  <style type="text/css">
  	.container{
  			width: 70%;
  		}
	label{
		margin-top: 20px;
	}
	button{
		margin-top: 10px;
	}
  </style>
  </head>
   <body>
  	<div class="title">用户反馈</div><hr>
    <div class="container">
      	<form name="form" method="post">
	    	<label>联系方式</label>
        <input type="text" class="form-control" name="connection" id="connection" placeholder="联系方式" required autofocus>
        <label>您的意见</label>
        <textarea rows="10" cols="140" name="content" class="form-control" placeholder="谢谢您的意见^_^"></textarea>
        <button class="btn btn-lg btn-primary btn-block" type="submit">提交意见</button>
      	</form>
    </div>
  </body>
</html>
