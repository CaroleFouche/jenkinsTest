<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Ajouer la librairie core de jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Lier librairie Bootstrap a ma page -->
<link rel="stylesheet" href="assets/css/bootstrap.css" />


<script type="text/javascript" src="assets/js/jquery-3.4.1.js"></script>

<script type="text/javascript" src="assets/js/bootstrap.js"></script>

<title>Index</title>
</head>
<body>
<%@ include file="../../template/header.html" %>


<!-- Rediriger la requete vers accueil -->

<% response.sendRedirect("ecole/liste"); %>
</body>
</html>