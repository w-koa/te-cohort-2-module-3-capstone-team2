<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/jsp/common/header.jsp" />
<head>
<meta charset="ISO-8859-1">
<title>National Park Geek - Park Details</title>
</head>
<section>
	<div>
		<c:forEach var="forecast" items="${forecasts}">
			<div>
				<c:if test="${forecast.forecast.equals('partly cloudy')}">
					<c:set var="forecast" target="${forecast.forecast}" value="partlyCloudy"/>
				</c:if>
				<c:url var="forecastPic" value="/img/weather/${forecast.forecast}.png"/>
				<img src="${forecastPic}" alt="weatherPic" />
				<div>
					<c:out value="${forecast.highInF}"/>
				</div>
				<div>
					<c:out value="${forecast.lowInF}"/>
				</div>




			</div>
		</c:forEach>


	</div>





</section>




<c:import url="/WEB-INF/jsp/common/footer.jsp" />