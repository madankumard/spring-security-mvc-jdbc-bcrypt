<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	Heello Woorld!!!

	<p>
		User:
		<security:authentication property="principal.username" />
		<br /> Roles:
		<security:authentication property="principal.authorities" />
	</p>

	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath }/leaders">Leadership
				Meeting </a> (Only for Manager peeps)
		</p>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath }/systems">IT Systems
				Meeting </a> (Only for Admin peeps)
		</p>
	</security:authorize>

	<form:form action="${pageContext.request.contextPath }/logout">
		<input type="submit" value="Logout">
	</form:form>

</body>
</html>