<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
    <h1>401 Unauthorized Access</h1>
    <hr />

    <c:if test="${not empty error}">
        <div style="color:red">
            login denied...<br />
            Caused : ${session.SPRING_SECURITY_LAST_EXCEPTION.message}
            message : ${message}
            error message : ${errorMessage}
        </div>
    </c:if>

</body>
</html>