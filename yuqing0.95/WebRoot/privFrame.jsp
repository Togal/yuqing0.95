<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'privFrame.jsp' starting page</title>
<script type="text/javascript" src="source/js/ajax.js"></script>
<script type="text/javascript">
	function change(aid,op,val) {
		var v =  op+aid ; 
		var value = document.getElementById(v).value;
		if(value == 1){
	           val = 0;
	           document.getElementById(v).value = 0;
	        }else if( value == 0){
	           val = 1;
	           document.getElementById(v).value = 1;
	        }
		 var url = "updatePower?powerId=" +aid + "&op=" + op + "&value=" + val + "&time="+new Date().getTime();
	        var ajax = new Ajax(url,onData);
	        ajax.setType("text");
		 
		 function onData(data){
		    document.getElementById('accMsg').innerHTML = '<font color=red size=2>'+data + '</font>';
		    timeout();
		 }
		 
		 function codefans() {
		 		var msg = document.getElementById('accMsg');
		 		alert(msg.style);
		 		msg.style.display="none";
		 }
		 function timeout() {
		 		setTimeout("codefans()",3000);
		 }	 
	}
	</script>
	</head>

<body >
  	<div style="width:200px; height:20px;margin-top:10px; margin-left:150px; position:fixed; _position:absolute;_bottom:auto; " id="accMsg"></div>
	<table align=center width=100% cellspacing=3 cellpadding=1
		bordercolor="#4b73b0"
		style="border-style:solid; border-width:0px; padding-left: 0px; padding-right: 0px; padding-top: 5px; padding-bottom: 5px">
		<tr>
			<th align=center width=28%>模块</th>
			<th width=18%>查询权限</th>
			<th width=18%>修改权限</th>
			<th width=18%>删除权限</th>
			<th width=18%>添加权限</th>
		</tr>
		<c:forEach var="power" items="${powers}">
			<tr>
				<td align=center bgcolor=#EEEEEE>${power.module.name}</td>
				<td align=center bgcolor=#EEEEEE>
					<input type=checkbox name="cksFind" <c:if test="${power.find==1 }">checked</c:if>  onclick="change(${power.powerId},'find',this.value)"> 
					<input type=hidden id="find${power.powerId }" value="${power.find}">
				</td>
				<td align=center bgcolor=#EEEEEE>
					<input type=checkbox name="cksUpdate" <c:if test="${power.update==1 }">checked</c:if> onclick="change(${power.powerId},'update',this.value)">
					<input type=hidden id="update${power.powerId}" value="${power.update}">
				</td>
				<td align=center bgcolor=#EEEEEE>
					<input type=checkbox name="cksDel" <c:if test="${power.del==1 }">checked</c:if> onclick="change(${power.powerId },'del',this.value)">
					<input type=hidden id="del${power.powerId }" value="${power.del}">
				</td>
				<td align=center bgcolor=#EEEEEE>
					<input type=checkbox name="cksAdd" <c:if test="${power.add==1 }">checked</c:if> onclick="change(${power.powerId },'add',this.value)">
					<input type=hidden id="add${power.powerId}" value="${power.add}">
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan=1 align=center><font size=2 color=blue>注意：打钩为拥有此权限。</font>
			</td>
		</tr>
	</table>
</body>
</html>
