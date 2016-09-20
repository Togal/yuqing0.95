<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>模块管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css"
	type="text/css"></link>
<link rel="stylesheet" href="source/css/common.css" type="text/css"></link>
<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="source/js/ajax.js"></script>
<style type="text/css">
table {
	text-align: center;
	
	 valign:center;
}

table tr th {
	text-align: center;
	
	valign:center;
	
}
</style>

<script type="text/javascript">
			function judge() {
				var value = document.getElementById("judge1").value;
				if(value =="check1") {
					document.getElementById("mp").style.display = "none";
					document.getElementById("judge1").value= "checked";
					document.getElementById("flagCheck").value = "trueCheck";
				}else {
					document.getElementById("mp").style.display = "inherit";
					document.getElementById("judge1").value = "check1";
					document.getElementById("flagCheck").value = "falseCheck";
				}
			}
			
			function del(moduleId) {
			alert(moduleId);
			var flag =  confirm("您真的要删除该模块吗？");
			if(flag) {
				var url =  "moduleDelAction?moduleId=" + moduleId;
				window.location.href = url;
			}
		
		}
			function check(obj) {
			if($('#keyWord').val().trim() != ""){
				return true;
			}
			setHint(obj,"输入不能为空");
			return false;
		}
		function change(roleId,moduleId) {   //修改角色和模块
			 
			 var flagcheck = document.getElementById("mdMroleId"+roleId+"moduleId"+moduleId);
			 var ajax = null;
			 if(flagcheck.checked) {
			 		var url="addModuleByRole?roleId="+roleId+"&moduleId="+moduleId+"&time="+ new Date().getTime();
	  			     ajax = new Ajax(url,onData);
			 }else {
			 		var url="cancelModuleByRole?roleId="+roleId+"&moduleId="+moduleId+"&time="+ new Date().getTime();
	  			   ajax = new Ajax(url,onData);
			 }
			  ajax.setType("text");
		 }
		 function onData(data){
		    document.getElementById('accMsg').innerHTML = '<font color=red size=2>'+data + '</font>';
		    timeout();
		 }
		 function codefans() {
		 		var msg = document.getElementById('accMsg');
		 		msg.style.display="none";
		 }
		 function timeout() {
		 		setTimeout("codefans()",5000);
		 }	 
		</script>
</head>
<body>

	<div class="title">模块管理</div>
	<div style="width:200px; height:20px;margin-top:10px; margin-left:300px; position:fixed; _position:absolute;_bottom:auto; " id="accMsg"></div>
	<hr>
	<div class="container">
	
		<div style="margin-top: 20px;">
				<form action="moduleAddAction"  method="post" >
				<div class="row">
					<div class="col-xs-3">
						<input type="text" class="form-control" autofocus="autofocus"
							required="required" placeholder="输入模块名" name="moduleName"
							id="keyWord">
					</div>
					<div class="col-xs-3">
						<input type="text" class="form-control" required="required"
							placeholder="输入模块Url 如manage.jsp" name="moduleUrl" id="keyWord" />
					</div>
					<div style="width: 120px;float: left;height: 45px;">
						<input type="checkbox" id="judge1" value="check1" onclick="judge()"><span>设置为一级菜单</span>
						<input type="hidden" id="flagCheck"  name ="flagCheck" value="trueCheck"/>
					</div>
					<div class="col-xs-2">
						<select id=mp name=mp
							style="width:120px;border:none;display: ingerit;margin-left: 5px;"
							class="form-control">
							<c:forEach var="moduleParent" items="${moduleParent}">
								<option style="border:none;" class="form-control"
									value='${moduleParent.moduleId }'>${moduleParent.name
									}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-xs-1">
						<input type="submit" id="butAdd" class="btn btn-default" onclick="return check(this)"  value="添加模块"  />
					</div>
				</div>
			</form>
		</div>

		<div id="hint" align="center"></div>
		<table class="table table-bordered" style="margin-top: 20px;valign:middle">
			<tr>
				<th class="col-sm-2 control-label">模块名称</th>
				<c:forEach var="role" items="${roleList }">
					<th class="col-sm-2 control-label">${role.name}</th>
				</c:forEach>
				<th class="col-sm-2 control-label">操 作</th>
			</tr>
			<c:forEach var="module" items="${moduleList }">
				<tr >
					<td >${module.name}</td>
					<c:forEach var="role" items="${roleList }">
						<td>
							<input type=checkbox id="mdMroleId${role.roleId}moduleId${module.moduleId}" name="mdM"  
							 <c:forEach var="power" items="${powerList }">
								 <c:if test="${power.module.moduleId == module.moduleId &&   power.role.roleId == role.roleId }">checked</c:if>
								 </c:forEach>
								onclick="change(${role.roleId},${module.moduleId})" />
						</td>
					</c:forEach>

					<!-- <input type=checkbox id="mdM" name="mdM"  
								checked= "<c:forEach var="power" items="${powerList }">
								 <c:if test="${power.module.moduleId == module.moduleId &&   power.role.roleId == role.roleId }">checked</c:if>
								 </c:forEach>"
								onclick="change(${role.roleId},${module.moduleId})" />	
						-->

					<!--  <input type=checkbox name="cksFind" <c:if test="${power.find==1 }">checked</c:if>  onclick="change(${power.powerId},'find',this.value)"> 
					<input type=hidden id="find${power.powerId }" value="${power.find}">-->

					<td>
							<input type="button" id="butUpdate" onclick=" check()" class="btn btn-default" value="修改">
						    <input type="button" id="butDel" onclick="del(${module.moduleId})" class="btn btn-default"value="删除" />
						 </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>
