<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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

<title>websiteCount</title>

<link rel="stylesheet" href="source/bootstrap/bootstrap.min.css" type="text/css"></link>
<style type="text/css">
	table tr th{
		font-family: Microsoft YaHei;
		font-size: 15px;
		text-align: center;
	}
	table tr td{
		font-family: Microsoft YaHei;
		font-size: 15px;
		text-align: center;
	}
</style>
</head>

<body>
	<div class="container" style="padding-top: 10px;border: 0px solid green;width:1000px;margin-left: auto;margin-right: auto;">
	<div style="border:0px solid green;width:300px;float: left;padding-top: 30px;">
	<table class="table table-bordered">
		<tr class="tab_head"><th>序号</th><th>站点</th><th>回复数量</th><th>比例</th></tr>
	</table>
	</div>
	<div id="websiteCount" style="width:600px;height: 550px;border:0px solid green;float: right;"></div>
	</div>
</body>

<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>

<script type="text/javascript" src="source/js/highcharts.js"></script>

<script type="text/javascript">
	var totalData = 1;
	var data = ${data};
	$(function() {
		var chart = new Highcharts.Chart({
			chart : {
				renderTo : 'websiteCount',
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '舆情来源站点比例图',
				style : {
					fontSize : '16px',
					color : '#000000',
					fontFamily : '微软雅黑'
				}
			},
			tooltip : {
				formatter : function() {
					return this.point.name + " 总计：" + this.y + "条";
				}
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						formatter: function(){
  	                        return '<b>' + this.point.name+" "+this.point.percentage.toFixed(2)+"%" + '</b>';  
  	                    }
					},
					showInLegend: true
				}
			},
			series : [ {
				name : '站点来源',
				type : 'pie',
				data : data
			} ],
			credits : {//配置右下角版权链接
				enabled : true,
				href : "websiteCount",
				text : "来源站点统计"
			},
		});
		data = ${data};
		for ( var i = 0; i < data.length; i++) {
			totalData += data[i][1];
		}
		totalData= totalData-1;
		for(var i=data.length-1;i>=0;i--) {
			var temp = data[i][1]/totalData*100+"";
			var str="<tr>";
			str+= "<td>"+(i+1)+"</td>";
			str+= "<td>"+data[i][0]+"</td>";
			str+= "<td>"+data[i][1]+"</td>";
			str+= "<td>"+temp.substring(0,temp.indexOf(".")+3)+"%</td>";
			str+="</tr>";
			$(".tab_head").after(str);
		}
	});
</script>
</html>
