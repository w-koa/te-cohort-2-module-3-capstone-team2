<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>


<head>
<c:url value="/css/site.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">
<meta charset="ISO-8859-1">
<title>National Park Geek</title>
</head>
<body>
	<header>
		<c:url var="home" value="/" />
		<c:url var="logo" value="/img/logo.png" />
		<a href="${home}"><img src="${logo}" alt="logo" id="logo"/></a>
		<h2>Explore the National Parks!</h2>
	</header>
	<nav>

		<ul>
			<c:url var="survey" value="/survey" />
			<li><a href="${home}">Home</a></li>
			<li><a href="${survey}">Survey</a></li>
		</ul> 
	</nav>