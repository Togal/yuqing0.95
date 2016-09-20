<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>opinionTrend</title>
    
	<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>

	<script type="text/javascript" src="source/js/highstock.js"></script>

  	<script type="text/javascript" src="source/js/exporting.js"></script>
  </head>
  
  <body>
    <div id="container" style="min-height: 550px; min-width: 600px"></div>
  </body>
  
  <script type="text/javascript">
	$(function() {
		Highcharts.setOptions({
			global:{
				useUTC:false
			},
			lang:{
				rangeSelectorFrom:'从',
				rangeSelectorTo:'到',
				rangeSelectorZoom:'最近:',
				downloadJPEG:'下载jpeg图片',
				downloadPDF:'下载为pdf文档',
				downloadPNG:'下载png图片',
				downloadSVG :'下载svg',
				printChart:'打印图片',
				months:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
				shortMonths:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
				weekdays:['周日','周一','周二','周三','周四','周五','周六']
				
			}
		});
		var chart = new Highcharts.StockChart({
			chart : {
				renderTo : 'container',//指向的div的id属性
				type:'spline',
				events : {
					click : function(event) {
					//	alert(event);
					}
				}
			},
			credits:{
				enabled:true,
				href:window.location.href,
				text:'OP舆情',
				position:{
					align:'right'
				}
			},
			exporting : {
				enabled : true,
				buttons : {
					contextButton:{
						text:'导出'
					}
				},
			},
			legend: {
		    	enabled: true,
		    	align: 'center',
		    	layout: 'horizontal',
		    	shadow: true
		    },
			title : {
				text : "${opinionTitle}", //图表标题
				style : {
  					fontSize : '16px',
  					color : '#000000',
  					fontFamily : '微软雅黑'
  				}
			},
			xAxis : {
				tickPixelInterval : 200,//x轴上的间隔
				type : 'datetime', //定义x轴上日期的显示格式
				labels : {
					formatter : function() {
						var vDate = new Date(this.value);
						return vDate.getFullYear() + "-"
								+ (vDate.getMonth() + 1) + "-"
								+ vDate.getDate();
					},
					align : 'center'
				}
			},
			yAxis : {
				title : {
					text : '回复(条)' //y轴上的标题
				}
			},
			tooltip : {
				xDateFormat : '%Y-%m-%d %H:%M, %A'//鼠标移动到趋势线上时显示的日期格式
			},
			rangeSelector : {
				buttons : [ {//定义一组buttons,下标从0开始
					type : 'day',
					count : 1,
					text : '1天'
				}, 
				{
					type : 'week',
					count : 1,
					text : '1周'
				},
				{
					type : 'all',
					text : '所有'
				} ],
				selected : 1,
				inputDateFormat : '%Y-%m-%d %H',
				inputEditDateFormat : '%Y-%m-%d %H'
			},
			series:${data}
		});
	});
</script>
</html>
