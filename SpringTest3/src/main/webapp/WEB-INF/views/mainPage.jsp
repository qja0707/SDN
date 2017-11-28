<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>

</head>
<body>
	<!-- 이미지 -->
	<img src="resources/switch.png" style="position: absolute; left: 300px; top: 200px" width="70" height="50" title="s1">
	<img src="resources/switch.png" style="position: absolute; left: 500px; top: 100px" width="70" height="50" title="s2">
	<img src="resources/switch.png" style="position: absolute; left: 500px; top: 300px" width="70" height="50" title="s3">
	<img src="resources/switch.png" style="position: absolute; left: 700px; top: 200px" width="70" height="50" title="s4">
	
	<img src="resources/hostPC.png" style="position: absolute; left: 100px; top: 200px" width="100" height="70" title="h1">
	<img src="resources/hostPC.png" style="position: absolute; left: 900px; top: 70px" width="100" height="70" title="h2">
	<img src="resources/hostPC.png" style="position: absolute; left: 900px; top: 200px" width="100" height="70" title="h3">
	<img src="resources/hostPC.png" style="position: absolute; left: 900px; top: 350px" width="100" height="70" title="h4">
	
		<img src="resources/lineH.png" style="position: absolute; left: 200px; top: 220px" width="90" height="3" title="h1-eth0 : s1-eth0">
		<img src="resources/lineUP.png" style="position: absolute; left: 390px; top: 130px" width="90" height="90" title="s1-eth1 : s2-eth0">
		<img src="resources/lineDOWN.png" style="position: absolute; left: 390px; top: 200px" width="90" height="160" title="s1-eth2 : s3-eth0">
		<img src="resources/lineDOWN.png" style="position: absolute; left: 590px; top: 90px" width="90" height="160" title="s2-eth1 : s4-eth0">
		<img src="resources/lineUP.png" style="position: absolute; left: 590px; top: 230px" width="90" height="90" title="s3-eth1 : s4-eth1">
	
	
	<a href="google.co.kr">
		<img src="resources/lineUP.png" style="position: absolute; left: 800px; top: 110px" width="90" height="90" title="s4-eth2 : h2-eth0">
	</a>
	<a href="google.co.kr">
		<img src="resources/lineH.png" style="position: absolute; left: 800px; top: 220px" width="90" height="3" title="s4-eth3 : h3-eth0">
	</a>
	<a href="google.co.kr">
		<img src="resources/lineDOWN.png" style="position: absolute; left: 800px; top: 230px" width="90" height="160" title="s4-eth4 : h4-eth0">
	</a>
	
	<form name="hosts">
		<input type="checkbox" name="host" value="2" style="position: absolute; left: 940px; top: 140px"/>
		<input type="checkbox" name="host" value="3" style="position: absolute; left: 940px; top: 270px"/>
		<input type="checkbox" name="host" value="4" style="position: absolute; left: 940px; top: 420px"/>
		<input type="button" value="멀티캐스팅" onclick="send();">
	</form>
	
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
	function send(){
		var objs = document.getElementsByName("host");
		var objs_length=objs.length;
		var jsonArr = new Array();
		for(i=0;i<objs_length;i++){
			if(objs[i].checked==true){
				jsonArr.push(objs[i].value);
			}
		}
		var jsonData = JSON.stringify(jsonArr);
		alert(jsonData);
		
		$.ajax({
			type : 'GET',
			url :'/test/mainPage/restPut',
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

