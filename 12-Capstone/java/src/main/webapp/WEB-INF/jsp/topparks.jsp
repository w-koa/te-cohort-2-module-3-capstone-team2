<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<html>
<head>
<meta charset="ISO-8859-1">
<title>National Park Geek Home</title>
</head>

<div class="topFiveHead">
	<h1>The Top 5 National Parks</h1>
	<h2>Decided by you!</h2>
</div>

<div id="line">
	<c:forEach var="park" items="${topFiveParks}">
		<div class="parkInfoBlock">

			<c:set var="parkImage" value="${park.parkCode}" />
			<c:set var="parkImageLc" value="${fn:toLowerCase(parkImage) }" />
			<c:url var="image" value="/img/parks/${parkImageLc}.jpg" />
			<c:url var="parkDetails"
				value="/park/details?parkCode=${park.parkCode}" />
			<div class="img-hover-zoom">
				<a href="${parkDetails}"><img src="${image}" style=""
					alt="park photo" /></a>
			</div>
			<div>
				<h2>
					<c:out value="${park.parkName}" />
				</h2>
				<p>
					<c:out value="${park.parkVotes}" />
					Vote(s)!
				</p>
				<h3>
					<c:out value="${park.state}" />
				</h3>
				<div>
					<p>
						<c:out value="${park.description}" />
					</p>
				</div>
			</div>


		</div>

	</c:forEach>


</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />