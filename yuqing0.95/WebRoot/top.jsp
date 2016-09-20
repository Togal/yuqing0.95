<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 

<script type="text/javascript">
	$(function() {
		$('#exit').click(function (){
    		return confirm("您真的要退出吗");
    	});
		$('.nav ul li').hover(function() {
			$(this).css('background','#2A4F95');
		},function() {
			$(this).css('background','');
		});
	});
</script>
	<div class="top">
		<div class="logo"><img src="source/img/logo.png" height="40px"></img></div>
		<div class="nav">
			<ul>
				<li><a href="home" target="main">主页</a></li>
				<li><a href="search.jsp" target="main">搜索</a></li>
			</ul>
		</div>
		<div class="top_right">
			<ul>	
			<li>
				<div class="input-group" style="float: left;width: 200px;overflow:hidden; height: 40px;margin-right: 40px;">
      				<form method="post" action="opinionSearch?classify=0" target="main">
      				<input type="text" class="form-control input-sm" style="float: left;width: 120px;height: 40px;" placeholder="快速搜索" name="searchKey">
      				<span class="input-group-btn" style="float: left;height: 40px;">
        				<button class="btn btn-default btn-lg input-sm" type="submit" 
        					style="height: 40px;width:60px;">GO!</button>
   					</span>
   					</form>
 				</div>
 			</li>
			<li>用户：<a href="userDetail" target="main"><c:out value="${user.nick}"/></a></li>
    				<li>&nbsp;角色：<a href="javascript:void(0)" target="main"><c:out value="${user.role.name }"/></a></li>
    				<li>[<a href="modifyPassword.jsp" target="main">修改密码</a>
  							<a href="feedback.jsp" target="main">用户反馈</a> 
 							<a href="exit" target="parent.document" id="exit">注销</a> 
  							<a href="about.jsp" target="main">关于</a>]</li>
    		</ul>		
  		</div>
	</div>
	




