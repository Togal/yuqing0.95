<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPEhtmlPUBLIC"-//W3C//DTDXHTML1.1//EN"   "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我所关心</title>
	<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
	<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" href="source/css/common.css" type="text/css"></link>
	<style type="text/css">
		.del_hint{
			background-color:pink;
			color:white;
			font-family:"微软雅黑";
			font-size:10px;
			display:none;
			position: absolute;
			width:100px;
			text-align:center;
			height: 25px;
			line-height:25px;
			z-index: 2;
			-webkit-border-radius: 4px;
			border-radius: 4px;
			-moz-border-radius : 4px;
		}
		.hint{
			margin-top:40px;
			margin-bottom:20px;
			font-family:"微软雅黑";
			font-size:15px;
		}
	</style>
 
  </head>
  
  <body>
    <div class="title">关键词设置</div><hr>
    <div class="container">
    	<div style="margin-top: 20px;">
    		<form action="interest/interestAddAction" onsubmit="return check()" method="post">
    			<div class="row">
    				<div class="col-xs-3">
    					<input type="text" class="form-control" autofocus="autofocus" required="required" placeholder="输入关键字" name="keyWord" id="keyWord">
    				</div>
    				<div class="col-xs-3">
    					<input type="submit" id="butAdd" onclick="return check(this)" class="btn btn-default"  value="添加">
    				</div>
    			</div>
    		</form>
    	</div>
    	<c:choose>
    		<c:when test="${fn:length(interests) != 0}">
    				<div class="hint">您已添加的关键词</div>
    		</c:when>
    		<c:otherwise>
    			<div>您还没有设置感兴趣的关键词哦</div>
    		</c:otherwise>
    	</c:choose>
		<c:set var="flag" value="0"></c:set>
		<c:forEach items="${interests}" var="i">
			<c:if test="${flag % 5 == 0}"><div></c:if>
			<button type="button" id="${i.id}">${i.content}</button>
			<c:if test="${flag % 5 == 4}"></div></c:if>
			<c:set var="flag" value="${flag+1}"></c:set>
		</c:forEach>
    </div>
	<div class="del_hint">双击删除关键字</div>
	
	<script type="text/javascript">
		var clazz = ["btn btn-default","btn btn-primary","btn btn-success","btn btn-info","btn btn-warning","btn btn-danger"];
		function check(obj) {
			if($('#keyWord').val().trim() != ""){
				return true;
			}
			setHint(obj,"输入不能为空");
			return false;
		}
		function getClazz() {
			var i = Math.floor(Math.random()*6);
			return clazz[i];
		}
		function setHint(obj,str) {
			$(".del_hint").stop();
			$(".del_hint").fadeIn();
			var cx=obj.offsetLeft;
			var cy=obj.offsetTop;
			var offset = $(obj).offset();
			offset.top-=30;
			offset.left = offset.left+($(obj).width()-$(".del_hint").width())/2;
			$(".del_hint").offset(offset);
			$(".del_hint").css('color',$(obj).css('color'));
			$(".del_hint").html(str);
			$(".del_hint").css('background-color',$(obj).css('background-color'));
		}
		$(function() {
			$('button').mouseenter(function() {
				setHint(this,"双击删除关键字");
			});
			$('button').mouseleave(function() {
				$(".del_hint").stop();
				$(".del_hint").fadeOut();
			});
			$('#butAdd').mouseleave(function(){
				$(".del_hint").stop();
				$(".del_hint").fadeOut();
			});
			$('button').dblclick(function() {
				var id = $(this).attr('id');
				var url = "interest/interestDelAction?interestId="+id;
				$.ajax({
				   type: "get",
				   url: url,
				   dataType:"text",
				   success: function(msg){
					  if(msg == "deleted") {
						  $(".del_hint").fadeOut();
						  $('#'+id).remove();
						  if($('button').size() == 0) {
							$(".hint").html("您已经没有感兴趣的关键词了哦");
						  }
					  }
				   }
				});
			});
			$('button').each(function(){
				$(this).removeClass();
				$(this).addClass(getClazz());
				$(this).css("margin-right","40px");
				$(this).css("margin-bottom","20px");
			 });
		});
	</script>
  </body>
</html>
