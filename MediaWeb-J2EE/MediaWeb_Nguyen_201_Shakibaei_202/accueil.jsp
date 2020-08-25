<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="mediatek2020.items.Utilisateur" %>
<!DOCTYPE html>
<%
	Utilisateur u = null;
	if (session.getAttribute("utilisateur")!=null) {
		u = (Utilisateur) session.getAttribute("utilisateur");
	} else {
		getServletContext().getRequestDispatcher("/").forward(request, response);
	}

%>
<html lang='fr'>
<head>
<meta charset='utf-8'>
<title>Accueil</title>
<link rel="stylesheet" href="style.css" />
</head>

	<body>
	<div class="accueil">
	<h1>Bienvenue, <%=u.name()%></h1>
	<h2>Veuillez effectuer une action</h2>
	<% if (u.isBibliothecaire()) {
	%>
	<a class="btn btn-success" href="./ajouter">Ajouter un document</a>
	<%} else {%>
	<a class="btn btn-success" href="./emprunter">Emprunter un document</a>
	<br>
	<br>
	<a class="btn btn-success" href="./rendre">Rendre un document</a>
	<%} %>
	<br>
	<br>
	<a class="btn btn-danger" href="./deconnexion">Se déconnecter</a>
	</div>
	</body>
</html>