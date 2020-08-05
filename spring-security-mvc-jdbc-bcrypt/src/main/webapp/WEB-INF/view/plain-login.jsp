<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Custom Login Page</title>
</head>
<body>

	<form:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">

		<c:if test="${param.error != null}">
			<i>Invalid Username or Password</i>
		</c:if>

		<c:if test="${param.logout != null}">
			<i>You have been logged out</i>
		</c:if>

		<p>
			User name: <input type="text" name="username">
		</p>
		<p>
			Password: <input type="password" name="password">
		</p>

		<input type="submit" value="Login">
	</form:form>

</body>
</html>