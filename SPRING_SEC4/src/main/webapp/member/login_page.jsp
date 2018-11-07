<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<body>
<h1 id="banner">Member Login</h1>
<form name="f" action="/filterLogin" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table>
    <tr>
        <td>Username:</td>
        <td><input type='text' name='mid' value=""/>  j_spring_security_check j_username</td>
    </tr>
    <tr>
        <td>Password:</td>
        <td><input type='password' name='mpw' value=""> j_spring_security_check  j_password</td>
    </tr>
    <tr>
        <td colspan="2">
        	<input type = "checkbox" name="_spring_security_remember_me" id = "remember_me" />Remember Me
        	<br>_spring_security_remember_me
        </td>
    </tr>
    
    <tr>
        <td colspan='2' align="center">
        	<input type="submit" value="Sign in" >
        </td>
    </tr>
</table>
</form> 

<c:if test="${not empty error}">
	<div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
	<div class="msg">${msg}</div>
</c:if>


</body>
</html>