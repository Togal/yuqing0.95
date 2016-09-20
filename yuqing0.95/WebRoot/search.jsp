<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPEhtmlPUBLIC"-//W3C//DTDXHTML1.1//EN"   "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'seek.jsp' starting page</title>
	
	<link rel="stylesheet" type="text/css" href="source/css/search.css">
	<script type="text/javascript" src="source/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		function check() {
			var t = document.all.searchKey.value.trim();
			if(t == ""){
				alert("输入不能为空！！！");
				return false;
			}
			
			return true;
		}
		var navlevel1 = function(level1) {
			$(level1).click(function(){
				varthis = $(this);
				var index = $(level1).index(this);
				var url = "opinionSearch?classify="+index;
				$(".search-form").attr({'action':url});
				$("#searchNav li").attr({'class':""});
				varthis.attr({'class':"current"});
			});
		};
	
		$(document).ready(function(){
	  		navlevel1("#searchNav li");
		});
	</script>
  </head>
  <body>
    <div class="wrap">

    <div class="search-banner">
        <div class="banner-text">
            <span>舆情从opinion开始</span>
        </div>
        <div>
        	<img class="banner-logo" src="source/img/blue_logo.png">
        </div>
    </div>
    
    <div id="searchBox" class="search-wrap">
        <ul id="searchNav" class="search-nav">
          <li class="current">全部</li>
          <li>文章</li>
  	  	  <li>论坛</li>
  	  	  <li>微博</li>
  	  	  <li>博客</li>
  	  	  <li>社区</li>
  	  	  <li>贴吧</li>
          <li>知道 </li>
        </ul>
        
        <div id="searchLogo" class="baidu-logo">
    
        </div>
        
        <form id="searchForm" class="search-form" method="post" action="opinionSearch?classify=0" onsubmit="return check()" name="search-form">
            
            <div class="outer-wrap">
                <div class="inner-wrap">
                    <input id="searchKey" class="search-key keyInput" type="text" autocomplete="off" name="searchKey"></input>
                    <input id="searchBtn" class="search-btn" type="submit" value="搜索"></input>
                </div>
            </div>
            
            <ul id="searchRadio" class="radio-wrap">
                <li>
                    <input id="baidu_web" type="radio" checked="checked" name="searchMethod" value=1></input>
                    <label for="baidu_web">模糊查询</label>
                </li>
                <li>
                    <input id="google_web" type="radio" name="searchMethod" value=2></input>
                    <label for="google_web">精确查找</label>
                </li>
            </ul>
        </form>
    </div>
   </div>
  </body>
</html>
