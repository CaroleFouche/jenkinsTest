<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Ajouer la librairie core de jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../assets/css/bootstrap.css" />


<script type="text/javascript" src="../assets/js/jquery-3.4.1.js"></script>

<script type="text/javascript" src="../assets/js/bootstrap.js"></script>


<title>Ma page recherche</title>
</head>
<body>
<%@ include file="../../template/header.html" %>
	<h1>Rechercher un étudiant</h1>



	<form method="GET" action="submitSearch">

		<!-- modelAttribute ou commandName -->
		<div class="col-md-6">
			ID: <input type="number" class="form-control" id="exampleInputEmail3"
				placeholder="Id" name="pId" required> <br />
			<br /> <input class="btn btn-info" type="submit" value="Rechercher" style="position:center"><br />
			<br />
		</div>

	</form>

	<c:if test="${not empty eGet}">
		<br />
		<h3>L'étudiant trouvé est :</h3>
		<table class="table table-hover">
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date de naissance</th>
			</tr>
			<tr>
				<td>${eGet.id}</td>
				<td>${eGet.nom}</td>
				<td>${eGet.prenom}</td>
				<td>${eGet.dN}</td>
			</tr>
		</table>
	</c:if>

	<h1 style="color: red">${message}</h1>

</body>
</html>