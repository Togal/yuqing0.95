
var xmlHttp = null;
var type = "text";
var onData;

function Ajax(url,method){
	onData = method;
	createXMLHttp();
	xmlHttp.open("get",url,true);
	xmlHttp.onreadystatechange = callBack;
	xmlHttp.send();
	Ajax.prototype.setType = function(t){
		type = t;
	}
}

function createXMLHttp(){
	if(xmlHttp == null){
		if(window.XMLHttpRequest){
			xmlHttp = new XMLHttpRequest();
		}else if(window.ActiveXObject){
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
}

function callBack(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(type =="text"){
			onData(xmlHttp.responseText);
		}else if(type=="xml"){
			onData(xmlHttp.responseXML);
		}
	}
}

