<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Product</title>
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<main>
			<h1>Start a new Song!</h1>
			<div class="addInput">
				<c:out value="${errorMessage}"></c:out>
			</div>
			<div>
				<form method="POST" action="/${ song.getId() }/edit">
				<input type="hidden" name="_method" value="put">
				    <p>
						<label for="title"> Song Title: </label>
						<input type="text" id="title" name="title" value="${song.getTitle()}" />
				    </p>
				    <p>
						<label for="genre"> Genre: </label>
						<input type="text" id="genre" name="genre" value="${song.getGenre()}"/>
				    </p>
				    <p>
						<label for="lyrics"> Add your lyrics: </label>
						<textarea id="lyrics" name="lyrics" rows="10" cols="20" >${song.getLyrics()}</textarea>
				    </p> 
				    <input class="bSummit" type="submit" value="Submit"/>
				</form>    
			</div>
			<div class="div">
				<a href="/home">cancel</a>
				<form action="/${song.id}" method="POST">
				    <input type="hidden" name="_method" value="delete">
				    <input type="submit" value="delete">
				</form>
			</div>
		</main>
	
	</body>
</html>