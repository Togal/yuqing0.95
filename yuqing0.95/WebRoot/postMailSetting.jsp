<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>postMail</title>
<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css"
	type="text/css"></link>
<link rel="stylesheet" type="text/css" href="source/css/common.css">
<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	function check() {
		var addr = $("#emailAddress").val();
		var addrReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if (!addrReg.test(addr)) {
			alert("请输入正确的邮箱格式！！！");
			return false;
		}
		return true;
	}
	$(function() {
		$('#butDel').click(function() {
			var array = new Array();
			for(var i=0;i<$(".select").length;i++) {
				var c = $('.select')[i];
				if($(c).is(':checked')) {
					array.push(($(c).val()).replace(/\//g,''));
				}
			}
			if(array.length == 0) {
				alert("请选择需要删除的邮件");
				return false;
			}else {
				$("#butDel").attr("href",'mailBoxDel?str='+array.toString());
				return true;
			}
		});
	});
</script>
<style type="text/css">
	table{font-family: Microsoft YaHei;text-align: center;}
	table th{font-size:15px;text-align: center;}
	
</style>
</head>

<body>
	<div class="title">发送邮件</div>
	<hr>
	<div style="border:0px solid green;margin:0px auto;width: 60%;">
		<div>
			<form action="mailBoxAdd" onsubmit="return check()" method="get">
    			<div class="row" style="margin-top: 10px;">
    				<div class="col-xs-3">
    					<input type="text" class="form-control" autofocus="autofocus" required="required" placeholder="请输入姓名" id="emailName" name="emailName"/>
    				</div>
    				<div class="col-xs-3">
    					<input type="text" class="form-control" required="required" placeholder="请输入邮箱地址" id="emailAddress" name="emailAddress"/>
    				</div>
    				<div class="col-xs-3">
    					<input type="submit" id="butAdd" class="btn btn-default"  value="添加">
    				</div>
    				<div class="col-xs-3">
    					<a href="mailBoxDel" class="btn btn-danger" style="float: right;"   id="butDel">删除</a>
    				</div>
    			</div>
    		</form>
		</div>
		<table class="table table-bordered" style="min-width: 550px;text-align: center;margin: 0px auto;">
			<tr id=startCol align="center">
				<th width="15%">选择</th>
				<th width="30%">名称</th>
				<th width="40%">邮箱地址</th>
			</tr>
			<c:forEach items="${mailBoxList }" var="mailBox">
				<tr align="center">
					<td><input type="checkbox" class="select" name="cb1" id="cb1" value="${mailBox.emailId }"/>
					</td>
					<td>${mailBox.emailName}</td>
					<td>${mailBox.emailAddress}</td>
				</tr>
			</c:forEach>
		
		</table>
	</div>
</body>
</html>
