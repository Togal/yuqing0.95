<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="source/css/jquery.vector-map.css" media="screen"
	rel="stylesheet" type="text/css" />
<script src="source/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="source/js/jquery.vector-map.js" type="text/javascript"></script>
<script src="source/js/china-zh.js" type="text/javascript"></script>
<script type="text/javascript" src="source/js/highcharts.js"></script>
<script type="text/javascript">
	var level1 = 5;
	var level2 = 10;
	var level3 = 50;
	$(function() {
		var data = ${data};
		var dataStatus = [{cha : 'HKG',	name : '香港',des : '21',url : ' '
			}, {cha : 'HAI',name : '海南',des : '2',	url : ' '
			}, {cha : 'YUN',name : '云南',des : '113',url : ' '
			}, {cha : 'BEJ',name : '北京',des : '13',url : ' '
			}, {cha : 'TAJ',name : '天津',des : '4',	url : ' '
			}, {cha : 'XIN',name : '新疆',des : '144',url : ' '
			}, {cha : 'TIB',name : '西藏',des : '3',	url : ' '
			}, {cha : 'QIH',name : '青海',des : '22',url : ' '
			}, {cha : 'GAN',name : '甘肃',des : '11',url : ' '
			}, {cha : 'NMG',name : '内蒙古',	des : '21',	url : ' '
			}, {cha : 'NXA',name : '宁夏',des : '2',	url : ' '
			}, {cha : 'SHX',name : '山西',des : '3',	url : ' '
			}, {cha : 'LIA',name : '辽宁',des : '14',url : ' '
			}, {cha : 'JIL',name : '吉林',des : '4',	url : ' '
			}, {cha : 'HLJ',name : '黑龙江',	des : '3',url : ' '
			}, {cha : 'HEB',name : '河北',des : '21',url : ' '
			}, {cha : 'SHD',name : '山东',des : '1',	url : ' '
			}, {cha : 'HEN',name : '河南',des : '12',url : ' '
			}, {cha : 'SHA',name : '陕西',des : '33',url : ' '
			}, {cha : 'SCH',name : '四川',des : '44',url : ' '
			}, {cha : 'CHQ',name : '重庆',des : '23',url : ' '
			}, {cha : 'HUB',name : '湖北',des : '112',url : ' '
			}, {cha : 'ANH',name : '安徽',des : '24',url : ' '
			}, {cha : 'JSU',name : '江苏',des : '33',url : ' '
			}, {cha : 'SHH',name : '上海',des : '42',url : ' '
			}, {cha : 'ZHJ',name : '浙江',des : '2',	url : ' '
			}, {cha : 'FUJ',name : '福建',des : '12',url : ' '
			}, {cha : 'TAI',name : '台湾',des : '24',url : ' '
			}, {cha : 'JXI',name : '江西',des : '33',url : ' '
			}, {cha : 'HUN',name : '湖南',des : '11',url : ' '
			}, {cha : 'GUI',name : '贵州',des : '41',url : ' '
			}, {cha : 'GXI',name : '广西',des : '111',url : ' '
			}, {cha : 'GUD',name : '广东',des : '14',url : ' '
			}, {cha : 'MAC',name : '澳门',des : '0',url : ' '} ];
		for ( var i = 0; i < dataStatus.length; i++) {
			dataStatus[i].des = data[i];
		}
		$('#container').vectorMap({
			map : 'china_zh',
			color : "#B8E3AA", //地图颜色
			onLabelShow : function(event, label, code) {//动态显示内容
				$.each(dataStatus, function(i, items) {
					if (code == items.cha) {
						label.html(items.name + items.des);
					}
				});
			}
		})
		$.each(dataStatus, function(i, items) {
			var it = parseInt(items.des);
			if (it == 0) {
				var josnStr = "{" + items.cha + ":'#B8E3AA'}";
				$('#container').vectorMap('set', 'colors',
						eval('(' + josnStr + ')'));
			} else if (0 < it && it <= level1) {//动态设定颜色，此处用了自定义简单的判断
				var josnStr = "{" + items.cha + ":'#3C64DD'}";
				$('#container').vectorMap('set', 'colors',
						eval('(' + josnStr + ')'));
			} else if (it <= level2) {//动态设定颜色，此处用了自定义简单的判断
				var josnStr = "{" + items.cha + ":'#FFFF01'}";
				$('#container').vectorMap('set', 'colors',
						eval('(' + josnStr + ')'));
			}
			else if (it <= level3) {//动态设定颜色，此处用了自定义简单的判断
				var josnStr = "{" + items.cha + ":'#FE9702'}";
				$('#container').vectorMap('set', 'colors',
						eval('(' + josnStr + ')'));
			}
			else if (it > level3) {//动态设定颜色，此处用了自定义简单的判断
				var josnStr = "{" + items.cha + ":'#D92D29'}";
				$('#container').vectorMap('set', 'colors',
						eval('(' + josnStr + ')'));
			}
		});
		$('.jvectormap-zoomin').click(); //放大
		
		for(var i=0;i<dataStatus.length-1;i++) {
			for(var j=i+1;j<dataStatus.length;j++) {
				if(dataStatus[i].des<dataStatus[j].des) {
					var temp = dataStatus[i];
					dataStatus[i] = dataStatus[j];
					dataStatus[j] = temp;
				}
			}
		}
		var str="";
		var replyData = new Array();
		for(var i=0;i<10;i++) {
			str+="<tr>";
			str+= "<td>"+(i+1)+"</td>";
			str+= "<td>"+dataStatus[i].name+"</td>";
			str+= "<td>"+dataStatus[i].des+"</td>";
			str+="</tr>";
			if(dataStatus[i].des!=0) {
				var tempArray = new Array();
				tempArray.push(dataStatus[i].name);
				tempArray.push(dataStatus[i].des);
				replyData.push(tempArray);
			}
		}
		$(".tab_head").after(str);
		//回复比例图
		var replyCount = new Highcharts.Chart({
			chart : {
				renderTo : 'replyCount',
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '回复数量top10比例图',
				style : {
					fontSize : '16px',
					color : '#000000',
					fontFamily : '微软雅黑'
				}
			},
			subtitle:{
				text:'注意：回复量为0的省份未列出',
				align:'right',
				style:{
					color:'red',
					fontSize : '12px',
					fontFamily : 'Microsoft YaHei'
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
				name : '回复数量统计',
				type : 'pie',
				data : replyData
			} ],
			credits:{
  	        	text:null
  	        }
		});
	});
</script>
<style type="text/css">
li {
	font-family: Microsoft YaHei;
	font-size: 13px;
	text-align: center;
	float: left;
	margin-left: 20px;
	list-style: none;
}
li span {
	border: 1px solid white;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	-moz-border-radius: 5px;
	float: left;
	width: 25px;
	text-align: center;
}
table tr th {
	font-family: Microsoft YaHei;
	font-size: 15px;
	text-align: center;
}
table tr td {
	font-family: Microsoft YaHei;
	font-size: 15px;
	text-align: center;
}
</style>
</head>
<body>
	<div style="border: 0px solid green;width:1000px;margin-left: auto;margin-right: auto;">
		<div id=mychart	style="width:600px; height: 600px;border:0px solid green;float: left;">
			<div style="margin-bottom:10px; font-size:16px;font-family:Microsoft YaHei; clear: both;text-align: center;">
				"<c:out value="${opinionTitle}" />"回复量分布地图
			</div>
			<div style="border:1px solid blue;width:655px;height:580px; margin:0px auto;-webkit-border-radius: 4px;border-radius: 4px;-moz-border-radius : 4px;">
				<div id="container"	style="width: 100%; height: 540px;margin: 0px auto;border: 0px;"></div>
				<div style="margin: 0px auto;">
					<ul>
						<li><span style="background-color: #B8E3AA">灰</span>无人问津</li>
						<li><span style="background-color: #3C64DD">蓝</span>三言两语</li>
						<li><span style="background-color: #FE9702">橙</span>聚少成多</li>
						<li><span style="background-color: #FFFF01">黄</span>比比皆是</li>
						<li><span style="background-color: #D92D29">红</span>人山人海</li>
					</ul>
				</div>
			</div>
		</div>
		<div style="border:0px solid green;width:300px;float: right;">
			<div style="margin-bottom:10px; font-size:16px;font-family:Microsoft YaHei; clear: both;text-align: center;">回复量top10</div>
			<table class="table table-bordered" style="width:100%;border:0px solid blue;">
				<tr class="tab_head">
					<th>序号</th>
					<th>地点</th>
					<th>数量</th>
				</tr>
			</table>
		</div>
		<div id="replyCount" style="width:310px;height:330px; border:0px solid blue;border-top:0px; float: right;"></div>
	</div>
</body>
</html>