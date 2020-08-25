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
	if (!u.isBibliothecaire()) {
		getServletContext().getRequestDispatcher("/").forward(request, response);
	}
	
	Boolean valide = (Boolean) request.getAttribute("valide");
%>
<html lang='fr'>
<head>
<meta charset='utf-8'>
<title>Ajouter</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">
	
	<style>
	body {
	background-image: url("http://www.fondsecran.eu/a/get_photo/730013/1920/1080");
	color: black;
	}
	</style>
	
</head>

<body>
	<div class="content-fluid">
		<div class="col-sm-4 col-sm-offset-4">
			<h2 style="text-align:center; margin-bottom: 5%;">Que souhaitez-vous ajouter ?</h2>
			<form method="post" action="ajouter" class="form-group">
				<label>Type : </label>
				<br> 
				<select class="form-control" name="type">
					<option value='0'>Livre</option>
					<option value='1'>Cd</option>
					<option value='2'>Jeu</option>
				</select>
				<br>
				<label>Titre : </label>
				<br>
				<input class="form-control" type="text" name="titre">
				<br>
				<label>Auteur : </label>
				<br>
				<input class="form-control" type="text" name="auteur">
				<br>
				<label>Description : </label>
				<br>
				<textarea class="form-control" rows="5" name="description"></textarea>
				<br>
				<input type="submit" value="Ajouter" class="btn btn-info" style="margin-left:20%; width:140px; margin-top: 5%">
				<%
					if (valide!=null) {
						if (!valide) {
				%>
							<p style="color:red;">Ajout invalide !</p>
				<%
						} else {
				%>
							<p style="color:green;">Ajout réussi !</p>
				<%
						}
					}
				%>
			</form>
			<a class="btn btn-info" href="./accueil" style="margin-left:50%; margin-top: -14%; width:150px">Retour</a>
		</div>
	</div>
</body>
</html>