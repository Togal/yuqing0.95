<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPEhtmlPUBLIC"-//W3C//DTDXHTML1.1//EN"   "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>日志</title>
    <link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="source/css/common.css">
	<style type="text/css">
		table tr th,td{
			text-align: center;
			font-family: "微软雅黑";
			font-size: 16px;
		}
	</style>
  </head>
  
  <body>
    <div class="title">查询日志</div>
    <hr>
    <div class="container">
    	<div style="border:0px solid green;">
    		<table class="table table-bordered" style="width: 80%;margin: 0px auto;">
    			<tr>
    				<th>用户</th>
    				<th>操作</th>
    				<th>时间</th>
    			</tr>
    			<c:forEach items="${logs}" var="log" >
    			<tr>
	    			<td>${log.user.name }</td>
	    			<td>${log.operation }</td>
	    			<td><fmt:formatDate value="${log.time }" pattern="yyyy-MM-dd HH:mm"/></td>
	    		</tr>
    		</c:forEach>
    		</table>
    	</div>
		<div style="text-align: center;"><ul class="pagination">
			${paging}
		</ul></div>
    </div>
    
  </body>
</html>
