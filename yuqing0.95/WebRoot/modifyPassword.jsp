<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改密码</title>
  	<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
  	<link rel="stylesheet" href="source/css/common.css" type="text/css"></link>
  	<style type="text/css">
  		.container{
  			width: 50%;
  		}
  		span{
  			color:red;
  		}
  		label{
  			margin-top: 20px;
  		}
  		button{
  			margin-top: 10px;
  		}
  		.hint{
  			color:red;
  			text-align: center;
  		}
	</style>
  </head>
  
  <body>
  	<div class="title">修改密码</div><hr>
    <div class="container">
    	<div class="hint"><c:if test="${error != null}">${error}</c:if></div>
    	<form class="form-signin"  method="post" action="user/modifyPassword" >
    	<label>原密码</label><span id="hint1"></span>
        <input type="password" class="form-control" name="oldPwd" id="oldPwd" placeholder="原密码" required autofocus>
        <label>新密码</label><span id="hint2"></span>
        <input type="password" class="form-control" name="newPwd" id="newPwd" placeholder="密码" required>
        <label>确认密码</label><span id="hint3"></span>
        <input type="password" class="form-control" name="confirmPwd" id="confirmPwd" placeholder="确认密码" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">修改</button>
      </form>
    </div>
    <script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
    	$(function() {
    		$("#oldPwd").blur(function(){
    			var url = "validateUser";
    			var data = "name=${user.name}&password="+$("#oldPwd").val();
    			$.ajax({
 				   type: "POST",
 				   url: url,
 				   dataType:"text",
 				   data:data,
 				   success: function(msg){
 					  if(msg == "error") {
 						  $("#hint1").css('color','red');
 						  $("#hint1").html("&nbsp;&nbsp;*原密码错误");
 					  }else if(msg == "ok") {
 						 $("#hint1").css('color','green');
 						 $("#hint1").html("&nbsp;&nbsp;原密码正确");
 					  }
 				   }
 				});
    		});
			$("#newPwd").blur(function(){
    			
    		});
			$("#confirmPwd").blur(function(){
				if($("#newPwd").val() != $("#confirmPwd").val()) {
					$("#hint3").html("&nbsp;&nbsp;*输入的密码不一致");
				}
			});
    	});
    	
    </script>
  </body>
</html>
