<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPEhtmlPUBLIC"-//W3C//DTDXHTML1.1//EN"   "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
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
			var arr = new Array();//保存用户最初的角色id
			function change(obj,userId) {
				var p = "userId="+userId+"&roleId="+obj.value;
				if(confirm("确定改变该用户的权限？")) {
					$.ajax({
						type:"POST",
						url:"userPowerUpdateAction",
						data:p,
						cache:false,
						success:function(msg) {
							if(msg == "ok") {
								changeOk();
							}else {
								changeError();
							}
						},
						dataType:"text"
					});
					arr[userId] = obj.value;
				}else {
					obj.value = arr[userId];
				}
			}
			function clear(){
				$("#hint").html("");
			}
			function changeOk(){
				$("#hint").html("修改成功");
				setTimeout("clear()",1000);
			}
			function changeError(){
				$("#hint").html("error");
				setTimeout("clear()",1000);
			}
			
			function check(obj) {
			if($('#keyWord').val().trim() != ""){
				return true;
			}
			setHint(obj,"输入不能为空");
			return false;
		}
		
		function del(id) {
			var flag =  confirm("您真的要删除该用户吗？");
			if(flag) {
				var url =  "delUserAction?userId=" + id;
				window.location.href = url;
			}
		}
		</script>
	</head>
	<body>
		<div class="title">用户管理</div><hr>
		<div class="container">
		
		<div style="margin-top: 20px;">
    		<form action="userAddAction" onsubmit="" method="post">
    			<div class="row">
    				<div class="col-xs-3">
    					<input type="text" class="form-control" autofocus="autofocus" required="required" placeholder="输入账号" name="userName" id="keyWord">
    				</div>
    				<div class="col-xs-3">
    					<input type="password" class="form-control" autofocus="autofocus" required="required" placeholder="输入密码" name="userPassword" id="keyWord">
    				</div>
    				<div class="col-xs-3">
    					<input type="submit" id="butAdd" onclick="return check(this)" class="btn btn-default"  value="添加">
    				</div>
    			</div>
    		</form>
    	</div>
    	
		<div id="hint" align="center"></div>
		<table class="table table-bordered" style="margin-top: 20px;">
			<tr>
				<th class="col-sm-2 control-label">账号</th>
				<th class="col-sm-2 control-label">昵称</th>
				<th class="col-sm-2 control-label">用户角色</th>
				<th class="col-sm-2 control-label">操&nbsp;&nbsp;作</th>				
			</tr>
			<c:forEach items="${userList}" var="user">
    		<tr>
    			<td>${user.name}</td>
    			<td>${user.nick }</td>
    			<td><select class="form-control" onchange="change(this,${user.userId })">
    					<c:forEach items="${roleList }" var="role">
    						<c:if test="${role.roleId==user.role.roleId }">
    							<option selected="selected" value="${role.roleId }">${role.name }</option>
    						</c:if>
    						<c:if test="${role.roleId != user.role.roleId }">
    							<option value="${role.roleId }">${role.name }</option>
    						</c:if>
    					</c:forEach>
    			</select></td>
    			
    			<td>
    						<input type="button" id="butUpdate" onclick="check()" class="btn btn-default"  value="修改"/>
    						 <input type="button" id="butDel" onclick="del( ${user.userId})" class="btn btn-default"  value="删除"/>
    			</td>
    		</tr>
    		<script type="text/javascript">
    			var u = ${user.userId};
    			var r = ${user.role.roleId};
				arr[u] = r;
			</script>
    		</c:forEach>
		</table>
    		
    	<div style="text-align: center;"><ul class="pagination">
			${paging}
		</ul></div>
    	</div>
	</body>
</html>