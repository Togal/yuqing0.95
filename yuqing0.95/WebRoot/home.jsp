<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>welcome</title>
	<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>	
  	</head>
  <body style="min-width: 1000px;">
	<div class="container" style="padding-top: 10px;height:280px;text-align: center;margin: 0px auto;overflow: hidden;">
		<div class="panel panel-primary" style="min-width: 205px;max-width:250px;height:256px; display: inline-block;">
			<div class="panel-heading" style="text-align: center;">
			   <a href="categroyTopic?level=1" style="color:inherit;">我所关心</a>
			</div>
			<ul class="list-group">
			  <c:forEach items="${interestTop5}" var="interest">
			  	<li class="list-group-item">
			  		<span class="badge" title="回复">${interest.replyCount}</span>
			  		<c:choose>
			  			<c:when test="${fn:length(interest.opinion.title) <= 8}">
<a href="opinionDetail?opinionId=${interest.opinion.opinionId}" title="${interest.opinion.title}" style="color: inherit;font-family: 微软雅黑;font-size:15px;">${interest.opinion.title}</a> 
			  			</c:when>
			  			<c:otherwise>
<a href="opinionDetail?opinionId=${interest.opinion.opinionId}" title="${interest.opinion.title}" style="color: inherit;font-family: 微软雅黑;font-size:15px;">${fn:substring(interest.opinion.title, 0, 8)}..</a> 			
			  			</c:otherwise>
			  		</c:choose>
			  	</li>
			  </c:forEach>
			</ul>
		</div>
		
		<div class="panel panel-primary" style="margin-left:20px; min-width: 205px;height:256px;max-width:250px;display:inline-block;">
			<div class="panel-heading" style="text-align: center;">
			   <a href="categroyTopic?level=2" style="color:inherit;">最新舆情</a>
			</div>
			<ul class="list-group">
			  <c:forEach items="${latestTop5}" var="latest">
			  	<li class="list-group-item">
			  		<span class="badge" title="回复">${latest.replyCount}</span>
			  		<c:choose>
			  			<c:when test="${fn:length(latest.opinion.title) <= 8}">
<a href="opinionDetail?opinionId=${latest.opinion.opinionId}" title="${latest.opinion.title}" style="color: inherit;font-family: 微软雅黑;font-size:15px;">${latest.opinion.title}</a> 
			  			</c:when>
			  			<c:otherwise>
<a href="opinionDetail?opinionId=${latest.opinion.opinionId}" title="${latest.opinion.title}" style="color: inherit;font-family: 微软雅黑;font-size:15px;">${fn:substring(latest.opinion.title, 0, 8)}..</a> 			
			  			</c:otherwise>
			  		</c:choose>
			  	</li>
			  </c:forEach>
			</ul>
		</div>
		
		<div class="panel panel-primary" style="margin-left:30px;min-width: 205px;height:256px;max-width:250px;display: inline-block;">
			<div class="panel-heading" style="text-align: center;">
			   <a href="categroyTopic?level=5" style="color:inherit;">最热舆情</a>
			</div>
			<ul class="list-group">
			  <c:forEach items="${hotTop5}" var="hot">
			  	<li class="list-group-item">
			  		<span class="badge" title="回复">${hot.replyCount}</span>
			  		<c:choose>
			  			<c:when test="${fn:length(hot.opinion.title) <= 8}">
<a href="opinionDetail?opinionId=${hot.opinion.opinionId}" title="${hot.opinion.title}" style="color: inherit;font-family: 微软雅黑;font-size:15px;">${hot.opinion.title}</a> 
			  			</c:when>
			  			<c:otherwise>
<a href="opinionDetail?opinionId=${hot.opinion.opinionId}" title="${hot.opinion.title}" style="color: inherit;font-family: 微软雅黑;font-size:15px;">${fn:substring(hot.opinion.title, 0, 8)}..</a> 			
			  			</c:otherwise>
			  		</c:choose>
			  	</li>
			  </c:forEach>
			</ul>
		</div>
		
		<div class="panel panel-primary" style="margin-left:30px;min-width: 205px;height:256px;max-width:250px;display:inline-block;">
			<div class="panel-heading" style="text-align: center;">
			   <a href="categroyTopic?level=3" style="color:inherit;">本地舆情</a>
			</div>
			<ul class="list-group">
			  <c:forEach items="${locationTop5}" var="location">
			  	<li class="list-group-item">
			  		<span class="badge" title="回复">${location.replyCount}</span>
			  		<c:choose>
			  			<c:when test="${fn:length(location.opinion.title) <= 8}">
<a href="opinionDetail?opinionId=${location.opinion.opinionId}" title="${location.opinion.title}" style="color: inherit;font-family: 微软雅黑;font-size:15px;">${location.opinion.title}</a> 
			  			</c:when>
			  			<c:otherwise>
<a href="opinionDetail?opinionId=${location.opinion.opinionId}" title="${location.opinion.title}" style="color: inherit;font-family: 微软雅黑;font-size:15px;">${fn:substring(location.opinion.title, 0, 8)}..</a> 			
			  			</c:otherwise>
			  		</c:choose>
			  	</li>
			  </c:forEach>
			</ul>
		</div>
	</div>
	<div style="border:0px solid green;text-align: center;margin: 0 auto;">
		<div id="hotPercent" style="width:45%;display: inline-block;"></div>
		<div id="hot2Percent" style="width: 49%;display: inline-block;"></div>
	</div>
  </body>
  <script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="source/js/highcharts.js"></script>  	
  	<script type="text/javascript">
	  	$(function () {
	  		var chart = new Highcharts.Chart({
	  	        chart: {
	  	        	renderTo:'hotPercent',plotBackgroundColor: null,
	                plotBorderWidth: null,
	                plotShadow: false
	  	        },
	  	      	title: {
	                text: '所关心内容回复所占比重',
	                style:{
	                	fontSize:'16px',
	                	color:'#000000',
	                	fontFamily:'微软雅黑'
	                }
	            },
	            
	  	        tooltip: {
		            formatter:function(){
	 	    	    	return this.point.name+" 总计："+this.y+"条	所占比重"+this.point.percentage.toFixed(2)+"%";
	 	    	    }
	  	        },
	  	        plotOptions: {
	  	            pie: {
	  	                allowPointSelect: true,
	  	                cursor: 'pointer',
	  	                dataLabels: {
	  	                    enabled: true,
	  	                    color: '#000000',
	  	                    connectorColor: '#000000',
	  	                    formatter: function(){
	  	                        return '<b>' + this.point.name+" "+this.point.percentage.toFixed(2)+"%" + '</b>';  
	  	                    }
	  	                },
	  	              	showInLegend: true
	  	            }
	  	        },
	  	        credits:{
	  	        	text:null
	  	        },
	  	        series: [{
	  	            type: 'pie',
	  	            data: ${interestReplyCountData}
	  	        }]
	  	    });
	  		var chart2 = new Highcharts.Chart({
	            chart: {
	                type: 'column',
	                renderTo:'hot2Percent'
	            },
	            title: {
	                text: '近5天热点回复量比较',
                	style:{
	                	fontSize:'16px',
	                	color:'#000000',
	                	fontFamily:'微软雅黑'
	                }
	            },
	            credits:{
	  	        	text:null
	  	        },
	            xAxis: {
	                categories:${hotReplyCountY}
	            },
	            yAxis: {
	            	allowDecimals:false,
	                min: 0,
	                title: {
	                    text: '回复/条'
	                }
	            },
	            tooltip: {
	                headerFormat: '<span>{point.key}</span>',
	                pointFormat: '<div><ul><li>{series.name} <b>{point.y:.0f}条回复</b> </li></ul>',
	                footerFormat: '</div>',
	                shared: true,
	                useHTML: true
	            },
	            legend:{
	            	itemMarginTop: 8
	            },
	            plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
	            series: ${hotReplyCount}
	        });
	  	});				
	</script>
</html>

