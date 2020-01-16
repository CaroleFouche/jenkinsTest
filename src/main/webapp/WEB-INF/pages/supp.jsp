<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="../assets/css/bootstrap.css" />

<script type="text/javascript" src="../assets/js/jquery-3.4.1.js"></script>

<script type="text/javascript" src="../assets/js/bootstrap.js"></script>
<title>Ma page suppr</title>
</head>
<body>
<%@ include file="../../template/header.html" %>
	<h1>Je suis dans la page suppr</h1>


	<form method="GET" action="submitDelete">
		<!-- modelAttribute ou commandName -->
		ID: <input type="number" name="pId" />
		<br />
		<input type="submit" value="Supprimer">
	</form>

	<h1 style="color:red">${message}</h1>

</body>
</html>