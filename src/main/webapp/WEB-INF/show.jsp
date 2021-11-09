<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Show</title>
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<main>
	        <div class="info">
	                <div>
                    	<h1>
                    		<c:out value = "${song.getTitle()}" > </c:out>
                    	</h1>
	                   	<h2>
	                    	Started by
	                    	<span>
	                    	<c:out value = "${song.getUser().getFirst_name()}" > </c:out>
	                    	<c:out value = "${song.getUser().getLast_name()}" > </c:out>
	                    	</span>
	                    </h2>
	                    <p>
	                    	Genre: 
	                        <c:out value = "${song.getGenre()}" > </c:out>
	                    </p>
	                    <p>
	                    	Lyrics:
	                        <c:out value = "${song.getLyrics()}" > </c:out>
	                    </p>
	                </div>
	                <div class="options">
						<a href="/${ song.getId() }/edit">Contribute</a>
	                </div>
	        </div>
		</main>
	</body>
</html>