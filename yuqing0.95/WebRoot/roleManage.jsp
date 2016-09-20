<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>角色管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
  		<link rel="stylesheet" href="source/css/common.css" type="text/css"></link>
		<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
		<style type="text/css">
			table{
				text-align: center;
			}
			table tr th{
				text-align: center;
			}
			
		</style>
		<script type="text/javascript">
		function del(id) {
			var flag =  confirm("您真的要删除该种角色吗");
			if(flag) {
				var url =  "delRoleAction?roleId=" + id;
				window.location.href = url;
			}
		}
		
		function skip(roleId) {
		var url = "queryByRole?rid=" + roleId + '&time='+new Date().getTime();
		 window.frames["powerManage"].location.href=url;
	}
		</script>
  </head>
  
  <body >
    <div class="title">角色管理</div><hr>
    	<div class="container">
    	<div style="margin-top: 20px;">
    		<form action="roleAddAction" onsubmit="" method="post">
    			<div class="row">
    				<div class="col-xs-3">
    					<input type="text" class="form-control" autofocus="autofocus" required="required" placeholder="输入角色名" name="roleName" id="keyWord">
    				</div>
    				<div class="col-xs-3">
    					<input type="submit" id="butAdd" onclick="return check(this)" class="btn btn-default"  value="添加角色">
    				</div>
    			</div>
    		</form>
    	</div>
    	
		<div id="hint" align="center"></div>
		<table class="table table-bordered" style="margin-top: 20px;">
			<tr>
				<th class="col-sm-2 control-label">角色编号</th>
				<th class="col-sm-2 control-label">角色名称</th>
				<th class="col-sm-2 control-label">操   作</th>
			</tr>
			<c:forEach var="role"  items="${roleList }">
    		<tr>
    			<td>${role.roleId}</td>
    			<td>${role.name }</td>
    			<td>
    					<input type="button" id="butSel" onclick=" skip(${role.roleId})" class="btn btn-default"  value="查看权限">
    					<input type="button" id="butUpdate" onclick=" check()" class="btn btn-default"  value="修改">
    					<input type="button" id="butDel" onclick="del(${role.roleId})"  class="btn btn-default"  value="删除"/>
    			</td>
    		</tr>
    		</c:forEach>
    		</table>
    		</div>
    		<div  class="container"> 
    		   <iframe id="powerManage" name="powerManage" src=""  width=100% height=100% MARGINHEIGHT=0 MARGINWIDTH=0 
    		   frameborder=0  style="overflow-x: hidden; overflow-y: auto;" ></iframe>
		     </div>
  </body>
</html>
