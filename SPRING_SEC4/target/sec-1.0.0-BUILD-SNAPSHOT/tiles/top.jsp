<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
/sample/tiles/top.jsp<br>
<a href="/member/login_page.jsp">JSP직접접근 : 로그인폼(/member/login_page.jsp)</a><br>
<hr>
<a href="/memberctl/loginPageByMav">컨트롤러MAV :로그인폼(/memberctl/loginPageByMav)</a><br>
<a href="/memberctl/tiles">컨트롤러TILES :로그인폼(/memberctl/tiles)</a><br>
<a href="/memberctl/loginPageByTiles">컨트롤러TILES :로그인폼(/memberctl/loginPageByTiles)</a><br>
<hr>

<a href="/tilestop_member_login_page">TILES직접접근 : 로그인폼(tilestop_member_login_page)</a><br>
<a href="/tilesbody_member_login_page">TILES직접접근 : 로그인폼(tilesbody_member_login_page)</a><br>
<a href="/tilespopup_member_login_page" id="opener" >TILES직접접근 : 회원목록팝업(/tilespopup_login_page)</a><br>

<hr>
<button type="button" id="responsebody_ajax">컨트롤러REST :ajax</button><br>
<div id="ajaxResDiv"></div>


<script>
$(document).ready(function(){
	var l = (screen.availWidth-400) / 2;
	var t = (screen.availHeight-400) / 2;	
 
	$("#opener").on('click',function(event){
		window.open(this.href,"window","width=400,height=400,left=" + l + ",top=" + t + ", scrollbars = yes, location = no, toolbar = no, menubar = no, status = no");
		 return false;
	});
	    
	    
	//ajax 리스트
	$("#responsebody_ajax").click(function(){
		$.ajax({
			url:"/memberctl/responsebody_ajax",
			type:"get",  //get
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			data:"seq=1" ,
			//content-type:"application/json; charset=utf-8" ,
			//data: JSON.stringify({"bseq":"2", "userid":"lee"}),
			dataType: "json",  //optional
			success:function(resObject){
				$("#ajaxResDiv").empty();
				var htmlStr = "";
				$.map(resObject, mapCallback);
				function mapCallback( v,  i) { 
					htmlStr += v.mid + " , " + v.mpw + "<br>";
				}
				$("#ajaxResDiv").html(htmlStr);
			}
		});
	});
	

});
</script>




</body>
</html>