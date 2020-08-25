<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<%
	if (session.getAttribute("utilisateur")!=null) {
		getServletContext().getRequestDispatcher("/accueil").forward(request, response);
	}
	
	Boolean valide;
	if (request.getAttribute("valide") == null) {
		valide = true;
	} else {
		valide = (Boolean) request.getAttribute("valide");
	}
%>
<html lang='fr'>
<head>
<meta charset='utf-8'>
<link rel="stylesheet" href="style.css" />
<title>Connexion</title>
</head>

<body>
	<div class="content-fluid">
		<div class="col-sm-4 col-sm-offset-4">
			<h1 style="color:white; padding-top:30%">Bienvenue dans la bibliothèque</h1>
			<form method="post" action="authentication" class="form-group">
				<label style="color:white; padding-top:10%">Identifiant : </label> <br> 
				<input class="form-control" type="text" name="login">
				<br>
				<label style="color:white;">Mot de passe : </label>
				<br>
				<input class="form-control" type="password" name="password">
				<br>
				<input style="margin-left:40%; margin-top: 10%"type="submit" value="Se connecter" class="btn btn-info">
				<%
					if (!valide) {
				%>
				<p style="color:red;">Identifiant ou mot de passe incorrects</p>
				<%
					}
				%>
			</form>
		</div>
	</div>
</body>
</html>