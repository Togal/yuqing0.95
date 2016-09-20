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
    
    <title>紧要级别统计</title>
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
  	<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
  	<script type="text/javascript" src="source/js/highcharts.js"></script>
  	<script type="text/javascript">
  		var chart;
  		$(function() {
  			chart = new Highcharts.Chart({
  				chart:{
  					renderTo:'mychart',
  					type:'column',
  					style:{
  						margin: '0 auto'
  					}
  				},
  				credits: {//配置右下角版权链接
  	                enabled : true,
  	                href : "levelCount",
  	                text : "OP舆情级别统计"
  	            },
  	            title: {//配置标题
  	                text: '舆情级别统计',
	  	            style : {
	  					fontSize : '16px',
	  					color : '#000000',
	  					fontFamily : '微软雅黑'
	  				}
  	            },
  	            xAxis: {//配置x轴
  	                categories: ${dataName},
  	              	labels:{
  	              		style : {
	  	              		fontSize : '12px',
		  					color : '#000000',
		  					fontFamily : '微软雅黑'
	  					}
  	              	}
  	            },
  	            // 配置y轴
  	            yAxis: {
  	                title: {
  	                    text: ''
  	                },
  	                min:0,
  	              	allowDecimals:false,
  	                labels: {  
  	                    formatter: function() {
  	                        return this.value +'条'
  	                    }
  	                }
  	            },
  	            tooltip: {//配置数据点提示框
  	            	formatter:function(){
  	            		return '<b>'+this.series.name+'</b><br/>'+
  	            			this.x +' '+this.y+'条';
  	            	},
  	                crosshairs: false
  	            },
  	          //配置数据列
  	            series: [{
  	                name: '舆情级别',
  	                type:'column',
  	                data: ${data}
  	            }]
  			});
  			
  			//表格数据
  			var data = ${data};
  			var dataName = ${dataName};
  			var totalData = 1;
  			for ( var i = 0; i < data.length; i++) {
  				totalData += data[i];
  			}
  			totalData= totalData-1;
  			for(var i=dataName.length-1;i>=0;i--) {
  				var temp = data[i]/totalData*100+"";
  				var str="<tr>";
  				str+= "<td>"+(i+1)+"</td>";
  				str+= "<td>"+dataName[i]+"</td>";
  				str+= "<td>"+data[i]+"</td>";
  				str+= "<td>"+temp.substring(0,temp.indexOf(".")+4)+"%</td>";
  				str+="</tr>";
  				$(".tab_head").after(str);
  			}
  		});
  	</script>
  </head>
  
  <body>
    <div class="container" style="padding-top: 10px;border: 0px solid green;width:1000px;margin-left: auto;margin-right: auto;">
		<div style="border:0px solid green;width:300px;float: left;padding-top: 30px;">
			<table class="table table-bordered">
				<tr class="tab_head"><th>序号</th><th>级别</th><th>数量</th><th>比例</th></tr>
			</table>
		</div>
		<div id=mychart style="width:600px;height: 550px;border:0px solid green;float: right;"></div>
	</div>
  </body>
</html>
