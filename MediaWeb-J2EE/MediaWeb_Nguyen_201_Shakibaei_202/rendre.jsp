<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="mediatek2020.Mediatheque" %>
<%@ page import="mediatek2020.items.Utilisateur" %>
<%@ page import="mediatek2020.items.Document" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%
	Utilisateur u = null;
	if (session.getAttribute("utilisateur")!=null) {
		u = (Utilisateur) session.getAttribute("utilisateur");
	} else {
		getServletContext().getRequestDispatcher("/").forward(request, response);
	}
	if (u.isBibliothecaire()) {
		getServletContext().getRequestDispatcher("/").forward(request, response);
	}
	
	String iddoc = request.getParameter("iddoc");
	Boolean valide = false;
	if (iddoc!=null) {
		valide = (Boolean) request.getAttribute("valide");
	}
%>
<html lang='fr'>
<head>
<meta charset='utf-8'>
<title>Rendre</title>

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
			<h2 style="text-align:center; margin-bottom: 5%;">Que souhaitez-vous rendre ?</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Type</th>
						<th scope="col">Titre</th>
						<th scope="col">Auteur</th>
						<th scope="col">Description</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<% if (iddoc!=null) { 
						if (!valide) { %>
							<p style="color:red;">Impossible de rendre ce document !</p>
					<%	} else { %>
							<p style="color:green;">Document rendu !</p>
					<%}
					}%>
					<% for (Document d:Mediatheque.getInstance().tousLesDocuments()) {
						Object[] data = d.data();
						if ((Integer)data[6]==u.data()[0]) {
					%>
					<tr>
						<td><%=data[1]%></td>
						<td><%=data[2]%></td>
						<td><%=data[3]%></td>
						<td><%=data[4]%></td>
						<td><a class="btn btn-success" href="./rendre?iddoc=<%=data[0]%>">Rendre</a></td>
					</tr>
					<%	}
					}%>
				</tbody>
			</table>
			<a class="btn btn-info" href="./accueil" style="margin-left:45%; margin-top: 5%; width:150px">Retour</a>
		</div>
	</body>
</html>