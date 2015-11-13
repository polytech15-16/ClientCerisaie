<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>

<link rel="stylesheet" type="text/css"
	href="${url}/resources/css/semantic.css">
<link rel="stylesheet" type="text/css"
	href="${url}/resources/css/style.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="${url}/resources/js/semantic.js"></script>
<script src="${url}/resources/js/script.js"></script>
<script src="${url}/resources/js/handlebars-v4.0.4.js"></script>
<script src="${url}/resources/js/moment.js"></script>
<script src="${url}/resources/js/helper.js"></script>


</head>
<body>
	<div class="ui fixed inverted menu">
		<div class="ui container">
			<a href="${url}" class="header item"> <img class="logo"
				src="${url}/resources/images/logo.png"> Camping paradise
			</a> <a href="${url}" class="item">Accueil</a>
			<div class="ui simple dropdown item">
				Utilisateur <i class="dropdown icon"></i>
				<div class="menu">
					<a class="item" href="${url}/user"><i class="gamepad icon"></i>Afficher
						les utilisateurs</a>
					<div class="divider"></div>
					<a class="item" href="${url}/user/add"><i class="user icon"></i>Ajouter
						un utilisateur</a> <a class="item" href="${url}/user/1"><i
						class="user icon"></i>Afficher l'utilisateur 1</a>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${notification != null}">
		<div class="ui notification message">
			<i class="close icon"></i>
			<div class="header">Notification !</div>
			<p>${notification}.</p>
		</div>
	</c:if>

	<script type="text/javascript">
		$('.message .close').on('click', function() {
			$(this).closest('.message').transition('fade');
		});
	</script>