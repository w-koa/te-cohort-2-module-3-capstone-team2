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
		<h2>Enjoy the National Parks? Vote for your Favorite!</h2>
		<c:url var="processSurvey" value="/survey" />
		<form action="${processSurvey}" method="POST">
			<div>
				<label for="Email Address">Email Address: <input type="text"
					name="email" /></label>
			</div>
			<div>
				<label for="State of Residence">State of Residence: <input
					type="text" name="state" /></label>
			</div>
			<div>
				<label for="Level of Activity">Level of Activity: 
						<input type="radio" name="activityLevel" value="inactive"/>Inactive
						<input type="radio" name="activityLevel" value="sedentary"/>Sedentary
						<input type="radio" name="activityLevel" value="active"/>Active
						<input type="radio" name="activityLevel" value="extremelyActive"/>Extremely Active
				</label>
			</div>







		</form>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />