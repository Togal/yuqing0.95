<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPEhtmlPUBLIC"-//W3C//DTDXHTML1.1//EN"   "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>舆情列表</title>
  	<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
  	<link rel="stylesheet" href="source/css/common.css" type="text/css"></link>
  </head>
  
  <body>
  <div class="title"><c:out value="${head}"></c:out></div><hr/>
	<div class="container">
		<div style="border:0px solid green;">
		<c:set var="i" value="1"></c:set>
		<c:forEach items="${opinions}" var="opinion">
			<ul style="border:1px solid #F6F6F6;
				background-color:#F6F6F6;
				-webkit-border-radius: 4px;
				border-radius: 4px;
				-moz-border-radius : 4px;">
				<li class="item_title">
					<span class="badge badge-info">${i}</span>
					<a href="opinionDetail?opinionId=${opinion.opinionId}">${opinion.title}</a>
					<span class="item_hint">${opinion.website.name} &nbsp; ${opinion.catchTime }</span>
				</li>
				<li class="item_content">
					<c:if test="${fn:length(opinion.content)>100}">
						${fn:substring(opinion.content, 0,100)}....
					</c:if>
					<c:if test="${fn:length(opinion.content)<100}">
						${opinion.content}
					</c:if>
					<a href="opinionDetail?opinionId=${opinion.opinionId}">详细</a>
				</li>
			</ul>
			<c:set var="i" value="${i+1}"></c:set>
		</c:forEach>
		</div>
		<div style="text-align: center;"><ul class="pagination">
			${paging}
		</ul></div>
	</div>
  </body>
</html>
