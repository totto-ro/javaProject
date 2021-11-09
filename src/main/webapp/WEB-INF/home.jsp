<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<main>
			<nav>
				<div>
					<h1>
						Welcome back, 
						<span><c:out value="${user.first_name}"></c:out></span>
						<span><c:out value="${user.last_name}"></c:out> </span>
					</h1>
					<h1> Current Listings</h1>
				</div>
				<a href="/logout">Logout</a>
			</nav>
			<h1>All Songs Labs</h1>
			<table>
			    <thead>
			        <tr>
			            <th>Song</th>
			            <th>Created by:</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach var="element" items="${songList}">
			        <tr>
			            <td>
			            <a href="/songs/${ element.id }">${ element.getTitle() }</a>
			            <p>Genre: <c:out value="${element.getGenre()}"/></p>
			            </td>
			            <td><c:out value="${element.getUser().getFirst_name()}"/></td>
			        </tr>
			        </c:forEach>
			    </tbody>
			</table>
				<div class="div">
					<a class="links" href="/songs/new"> New song</a>
				</div>
		</main>
	</body>
</html>