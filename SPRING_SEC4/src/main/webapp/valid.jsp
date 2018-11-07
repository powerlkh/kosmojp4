<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
$(document).ready(function() {	

	//$("#gson_ajax").click(function(){
		$.ajax({
			//url:"https://api.instagram.com/v1/tags/nofilter/media/recent",
			url:"https://api.instagram.com/oauth/authorize/?client_id=55a75492e0d1462b8590efd4e285cd40&redirect_uri=http://localhost/valid&response_type=token",
			type:"get",  
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			//data:"access_token=8786670577.55a7549.14dd79cdb9b448c0aa5ea06b4e8ca22a" ,
			//content-type:"application/json; charset=utf-8" ,
			//data: JSON.stringify({"bseq":"2", "userid":"lee"}),
			//dataType: "text",  //optional
			success:function(res){
				console.log(res); 	//"[{"seq":"1", "title":"제목1"},{"seq":"2", "title":"제목2"}]"
				$("#instadiv").html(res);
				/*
				var jsonObject = JSON.parse(resString); //String --> jsonObject
				console.log(jsonObject);	//>[{"seq":"1", "title":"제목1"},{"seq":"2", "title":"제목2"}]
				$.map(jsonObject, mapCallback);
				function mapCallback( v,  i) { 
					console.log(v.bseq + "," + v.title);
				} 
				$("#ajaxReplyDiv").empty();
				replyListHTML(jsonObject);
				*/
			}
		});
	});
</script>
</head>	
<body>
valid.jsp<br>
${param.respone_type}

<div id="instadiv"></div>
</body>
</html>