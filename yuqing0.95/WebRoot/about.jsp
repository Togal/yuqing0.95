<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>about</title>

  <link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
  <link rel="stylesheet" href="source/css/common.css" type="text/css"></link>
  </head>
  
  <body>
  	<div class="title">关于舆情</div><hr>
    <div class="container">
    <h3>舆情是“舆论情况”的简称</h3>
    <h4 style="line-height: 30px;">&nbsp;&nbsp;&nbsp;&nbsp;是指在一定的社会空间内，围绕中介性社会事件的发生、发展和变化，作为主体的民众对作为客体的社会管理者及其政治取向产生和持有的社会政治态度。它是较多群众关于社会中各种现象、问题所表达的信念、态度、意见和情绪等等表现的总和。</h4>
    <h3>本系统提供的分析样本图</h3>
    <center>
    	<img alt="" src="source/img/1.png"><br><br><br><br>
    	<img alt="" src="source/img/2.png"><br><br><br><br>
    	<img alt="" src="source/img/3.png" width="800px"><br><br><br><br>
    </center>
    </div>
  </body>
</html>
