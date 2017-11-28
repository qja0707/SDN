<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<img src="resources/topology.png" style="position: absolute; left: 100px; top: 100px" width="1000" height="300">
	
	<!-- <a href="/switchStat">
		<img src="resources/hyperLink.png" style="position: absolute; left: 370px; top: 100px" width="100" height="90" title="s1">
		<img src="resources/hyperLink.png" style="position: absolute; left: 625px; top: 100px" width="100" height="90" title="s2">
		<img src="resources/hyperLink.png" style="position: absolute; left: 625px; top: 310px" width="100" height="90" title="s3">
	</a> -->
	
	<form name="hosts">
		<input type="checkbox" name="host" value="2" style="position: absolute; left: 1030px; top: 196px"/>
		<input type="checkbox" name="host" value="3" style="position: absolute; left: 1030px; top: 400px"/>
		<input type="radio"	name="switch" value="1" style="position: absolute; left: 400px; top: 196px" checked="checked"/>
		<input type="radio"	name="switch" value="2" style="position: absolute; left: 690px; top: 196px"/>
		<input type="radio"	name="switch" value="3" style="position: absolute; left: 690px; top: 400px"/>
		<input type="button" value="멀티캐스팅" onclick="multiCast();">
	</form>
	<input type="button" value="AllDelete" onclick="uniCast();">
	<input type="button" value="GetTable" onclick="getXml();">
	
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
	function getXml(){
		var jsonData = new Array();
		$.ajax({
			type : 'GET',
			url :'/test/getXmls',
			contentType : 'application/json;charset=UTF-8',
// 			data : {'jsonArr':jsonData},
			dataType:'json',
// 			error : function(response){alert("error");}
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		       },
			success : function(response){
				var contact = JSON.stringify(response);
				var output = JSON.parse(contact);
				alert(contact);
				console.log(typeof response);
				console.log(response);
			}
		});
	}
	function uniCast(){
		var jsonData = new Array();
		$.ajax({
			type : 'GET',
			url :'/test/uniCast',
			contentType : 'application/json;charset=UTF-8',
			data : {'jsonArr':jsonData},
			dataType:'json',
// 			error : function(response){alert("error");}
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		       },
			success : function(response){
				alert("All multicasting flows are deleted");
				console.log(typeof response);
				console.log(response);
			}
		});
	}
	function multiCast(){
		var switchObjs = document.getElementsByName("switch");
		var hostObjs = document.getElementsByName("host");
		
		var jsonArr = new Array();
		var swArr = new Array();
		var hostArr = new Array();

		for(i=0;i<switchObjs.length;i++){
			if(switchObjs[i].checked==true){
				swArr.push(switchObjs[i].value);
			}
		}
		for(i=0;i<hostObjs.length;i++){
			if(hostObjs[i].checked==true){
				hostArr.push(hostObjs[i].value);
			}
		}
		jsonArr.push(swArr);
		jsonArr.push(hostArr);
		var jsonData = JSON.stringify(jsonArr);
		alert("multicasting flow is created on switch:"+swArr+"      Host:"+hostArr);
		
		$.ajax({
			type : 'GET',
			url :'/test/multiCast',
			contentType : 'application/json;charset=UTF-8',
			data : {'jsonArr':jsonData},
			dataType:'json',
// 			error : function(response){alert("error");}
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		       },
			success : function(response){
				if(response==null){
					alert("success");
				}
				console.log(typeof response);
				console.log(response);
			}
		});
		
	}
	</script>
</body>
</html>
