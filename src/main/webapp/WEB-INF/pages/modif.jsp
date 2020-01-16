<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <!-- Ajouer la librairie core de jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../assets/css/bootstrap.css" />

<script type="text/javascript" src="../assets/js/jquery-3.4.1.js"></script>

<script type="text/javascript" src="../assets/js/bootstrap.js"></script>
<title>Ma page modif</title>
</head>

<body>

<%@ include file="../../template/header.html" %>
<h1>Formulaire de modification</h1>

<form:form method="POST" action="submitUpdate" modelAttribute="eUp">
		<!-- modelAttribute ou commandName -->
		ID: <form:input path="id" />
		<br />
		Nom: <form:input path="nom" />
		<!-- path fait reference à l'attribut de la classe Client -->
		<br />
		Prenom: <form:input path="prenom" />
		<br />
		Date de naissance: <form:input type="date" path="dN" />
		<br />
		<input type="submit" value="Modifier">
	</form:form>
<h1 style="color:red">${message}</h1>
</body>
</html>