<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录</title>
    <link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" href="source/css/login.css" type="text/css"></link>
</head>
  <body>
   <div class="container">
   		<h1 style="font-family: Microsoft YaHei;text-align: center;">OP舆情分析系统</h1>
      <form class="form-signin"  method="post" action="login" >
        <label>用户名</label>
        	 <label class="hint"><c:if test="${userNameError!=null}"><c:out value="*用户名不存在"></c:out>
	   			</c:if></label>
	   			<label class="hint"><c:if test="${error!=null}"><c:out value="*用户名或者密码不能为空"></c:out>
	   			</c:if></label>
	   			<label class="hint"><c:if test="${notLogin!=null}"><c:out value="*对不起，您还没有登录"></c:out>
	   			</c:if></label>
        <input type="text" class="form-control" name="name" placeholder="用户名" value='<c:if test="${name!=null}">${name}</c:if>' required autofocus>
        <label>密码</label>  
        <label class="hint"><c:if test="${passwordError!=null}"><c:out value="*密码错误"></c:out>
	   			</c:if></label>
        <input type="password" class="form-control" name="password" placeholder="密码" required>
       <!--  <label class="checkbox">
           <input type="checkbox" value="remember-me">记住我 
        </label> -->
        <button class="btn btn-lg btn-primary btn-block" type="submit">进入OP</button>
        <a id="detail" class="btn btn-default" href="about.jsp">了解我们 &raquo;</a>
        <a id="connect" class="btn btn-default" href="feedback.jsp">联系我们 &raquo;</a>
      </form>
    </div>
</body>
</html>
