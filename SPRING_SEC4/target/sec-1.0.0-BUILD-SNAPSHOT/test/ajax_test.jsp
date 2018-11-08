<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
$(document).ready(function() {	
//http://api.jquery.com/jquery.ajax/
//http://hsj0511.tistory.com/205	
	
	$("#gson_ajax").click(function(){
		$.ajax({
			url:"/gson_ajax",
			type:"post",  //get
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			data:"bseq=1&userid=lee" ,
			//content-type:"application/json; charset=utf-8" ,
			//data: JSON.stringify({"bseq":"2", "userid":"lee"}),
			dataType: "text",  //optional
			success:function(resString){
				console.log(resString); 	//"[{"seq":"1", "title":"제목1"},{"seq":"2", "title":"제목2"}]"
				var jsonObject = JSON.parse(resString); //String --> jsonObject
				console.log(jsonObject);	//>[{"seq":"1", "title":"제목1"},{"seq":"2", "title":"제목2"}]
				$.map(jsonObject, mapCallback);
				function mapCallback( v,  i) { 
					console.log(v.bseq + "," + v.title);
				}
				
				$("#ajaxReplyDiv").empty();
				replyListHTML(jsonObject);
			}
		});
	});
	
	$("#responsebody_ajax").click(function(){
		$.ajax({
			url:"/responsebody_ajax",
			type:"post",  //get
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			data:"bseq=1&userid=lee" ,
			//content-type:"application/json; charset=utf-8" ,
			//data: JSON.stringify({"bseq":"2", "userid":"lee"}),
			dataType: "json",  //optional
			success:function(resObject){
				console.log(resObject);
				$.map(resObject, mapCallback);
				function mapCallback( v,  i) { 
					console.log(v.bseq + "," + v.title);
				}
				
				$("#ajaxReplyDiv").empty();
				replyListHTML(resObject);
			}
		});
	});
	
	$("#requestbody_responsebody_ajax").click(function(){
		$.ajax({
			url:"/requestbody_responsebody_ajax",
			type:"post",  //only POST
		  	//contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			//data:"bseq=1&userid=lee" ,
			contentType: 'application/json; charset=utf-8',
		    data: JSON.stringify({"bseq":"2", "userid":"lee"}),
		    dataType: "json",  //optional
		    success:function(resObject){
				console.log(resObject);
				$.map(resObject, mapCallback);
				function mapCallback( v,  i) { 
					console.log(v.bseq + "," + v.title);
				}
				
				$("#ajaxReplyDiv").empty();
				replyListHTML(resObject);
			}
		});
	});
	
});

//------------------------------------
function replyListHTML(jsonObject) {
	var htmlStr = "";
	htmlStr += "<ul>";
	$.map(jsonObject, mapCallback);
	function mapCallback( v,  i) { 
		htmlStr += "<li>";
		htmlStr += v.bseq + " , ";
		htmlStr += v.title;
		//htmlStr += decodeURIComponent(v.title);
		htmlStr += "</li>"; 
	}
	htmlStr += "</ul>";
	$("#ajaxReplyDiv").html(htmlStr);
}
//------------------------------------
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" id="gson_ajax"  value="gson_ajax">
<input type="button" id="responsebody_ajax" value="responsebody_ajax">
<input type="button" id="requestbody_responsebody_ajax" value="requestbody_responsebody_ajax">
<br>
<!--  -------- 댓글 보여질 영역 ---------- -->
<div id="ajaxReplyDiv"></div>
</body>
</html>