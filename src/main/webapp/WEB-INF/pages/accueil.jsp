<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Ajouer la librairie core de jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Ajouer la librairie fmt de jstl qui sert a rendre l'application internationale (internationalisation) -->

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../assets/css/bootstrap.css" />

<script type="text/javascript" src="../assets/js/jquery-3.4.1.js"></script>

<script type="text/javascript" src="../assets/js/bootstrap.js"></script>


<title>Ma page d'accueil</title>
</head>
<body>
	<%@ include file="../../template/header.html"%>


	<div class="row">
		<div class="col-md-8">
			<table class="table table-bordered">
				<tr>
					<th>ID</th>
					<th>Nom</th>
					<th>Prenom</th>
					<th>Date de naissance</th>
					<th>Opérations</th>
				</tr>
				<c:forEach var="e" items="${etudiants}">
					<tr>
						<td>${e.id}</td>
						<td>${e.nom}</td>
						<td>${e.prenom}</td>
						<td><fmt:formatDate value="${e.dN}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
						<td><a
							href="<c:url value='/ecole/submitDelete?pId=${e.id}'/>">Supprimer</a>
							| <a href="<c:url value='/ecole/linkUpdate?pId=${e.id}'/>">Modifier</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-md-4">
			<img src="<c:url value='/assets/images/home.png'/>"
				class="img-thumbnail">
		</div>
	</div>
</body>
</html>