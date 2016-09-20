<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="power" uri="/powerTagLib" %>

<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="source/css/lunzhuan.css" type="text/css"></link>
<script type="text/javascript">
	$(document).ready(function() {
		$("li.mainlevel").click(function() {
			if ($(this).find('ul').is(':hidden')) {
				$(this).find('ul').slideDown('fast');
			} else {
				$(this).find('ul').slideUp('fast');
			}
		});
		$(".menu").hover(function() {
			$(this).css('background-color', '#3F89D0');
		}, function() {
			$(this).css('background-color', '#428BCA');
		});
		$("li.mainlevel ul li").click(function(event) {
			//阻止事件冒泡
			event.stopPropagation()

		});
		var interests = $(".tt");
		for ( var i = 0; i < interests.length; i++) {
			var t = parseInt(Math.random() * 5 + 1);
			$(interests[i]).attr('class', 'tagc' + t);
		}

		//一级菜单的动画效果
		$('.mainlevel').hover(function() {
			$(this).animate({
				width : 147,
				marginLeft : -8,
				opacity : 0.8
			}, 100).animate({
				width : 146,
				marginLeft : -4,
				opacity : 0.9
			}, 100).animate({
				width : 147,
				marginLeft : -8,
				opacity : 1
			}, 100)
		}, function() {
			$(this).stop(true).animate({
				width : 145,
				marginLeft : 0,
				opacity : 0.8
			}, 100);
		});
		
		//二级菜单的动画效果
		$('.mainlevel ul li').hover(function() {
			$(this).animate({
				width : 147,
				marginLeft : 10,
				opacity : 0.8
			}, 100).animate({
				width : 146,
				marginLeft : 5,
				opacity : 0.9
			}, 100).animate({
				width : 147,
				marginLeft : 10,
				opacity : 1
			}, 100)
		}, function() {
			$(this).stop(true).animate({
				width : 145,
				marginLeft : 0,
				opacity : 0.8
			}, 100);
		});
	});
</script>
<style>
/*menu*/
#nav {
	display: block;
	width: 145px;
}

#nav .mainlevel {
	border-top: 1px solid #fff;
	width: 100% /*IE6 only*/
}

.menu {
	background-color: #428BCA;
	color: white;
	text-decoration: none;
	font-family: 微软雅黑;
	font-size: 15px;
	text-align: center;
	line-height: 32px;
	display: block;
	width: 100%;
}

.menu:hover {
	color: black;
	text-decoration: none
}

#nav .mainlevel ul {
	display: none;
	position: static;
}

#nav .mainlevel li {
	border-top: 1px solid #fff;
	width: 100%; /*IE6 only*/
}

#nav .mainlevel ul li a {
	color: black;
	text-decoration: none;
	font-family: 微软雅黑;
	font-size: 13px;
	text-align: center;
	line-height: 32px;
	display: block;
	width: 100%
}

#nav .mainlevel ul li a:hover {
	color: blue;
	font-size: 15px;
	text-decoration: none;
}
</style>
<div
	style="margin-top: 0px;border:3px solid #1AADCE;width: 150px;clear:both;
		-webkit-border-radius: 4px;
		border-radius: 4px;
		-moz-border-radius : 4px;">
	<ul id="nav">
		<c:forEach items="${modules}" var="m1">
			<li class="mainlevel"><a class=menu href="javascript:void(0)">${m1.name}</a>
			<ul>
				<c:forEach items="${m1.modules}" var="m2">
					<li><a href="${m2.moduleUrl}" target=main>${m2.name}</a>
				</c:forEach>
			</ul>
		</c:forEach>
	</ul>
</div>

<div style="clear: both;">
	<div id="tagscloud" style="margin-top: 0px;">
		<c:forEach items="${interests}" var="interest">
			<a class="tt"
				href="opinionSearch?classify=0&searchKey=${interest.content}"
				target="main">${interest.content}</a>
		</c:forEach>
	</div>
</div>

<script type="text/javascript" src="source/js/lunzhuan.js"></script>
