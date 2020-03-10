<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<html>
<head>
<meta charset="ISO-8859-1">
<title>National Park Geek Survey</title>
</head>
<section>
	<div>
		<c:url var="processSurvey" value="/survey"/>
		<form action="${processSurvey}" method="POST">
			<label for="Email Address">Email Address: <input type="text" name="email"/></label>
		
		
		
		
		
		
		
		</form>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />