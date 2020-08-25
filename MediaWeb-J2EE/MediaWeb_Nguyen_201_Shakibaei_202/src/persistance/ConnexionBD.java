package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediatek2020.items.EmpruntException;
import mediatek2020.items.RetourException;

public class ConnexionBD {
	private static Connection connect;
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ETUDIANT", "ETUDIANT");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public static ResultSet executeAllDocs() throws SQLException {
		PreparedStatement allDocSt = connect.prepareStatement("SELECT * FROM DOCUMENT"); 
		return allDocSt.executeQuery();
	}
	public static ResultSet executeGetUser(String login, String password) throws SQLException {
		PreparedStatement getUsrSt = connect.prepareStatement("SELECT * FROM UTILISATEUR WHERE login=? AND password=?"); 
		ResultSet r;
		getUsrSt.setString(1, login);
		getUsrSt.setString(2, password);
		r = getUsrSt.executeQuery();
		return r;
	}
	public static ResultSet executeGetDoc(int numDocument) throws SQLException {
		PreparedStatement getDocSt = connect.prepareStatement("SELECT * FROM DOCUMENT WHERE iddoc = ?"); 
		ResultSet r;
		getDocSt.setInt(1, numDocument);
		r = getDocSt.executeQuery();
		return r;
	}
	public static void empruntDoc(int iddoc, int iduser) throws EmpruntException{
		int nb = 0;
		try {
			PreparedStatement updDocSt = connect.prepareStatement("UPDATE DOCUMENT SET emprunte = 1, iduser = ? WHERE iddoc = ? AND emprunte = 0 AND (iduser IS NULL OR iduser = ?)"); 
			updDocSt.setInt(1, iduser);
			updDocSt.setInt(2, iddoc);
			updDocSt.setInt(3, iduser);
			nb = updDocSt.executeUpdate();
		}
		catch (SQLException e) {}
		if (nb==0) {
			throw new EmpruntException();
		}
	}
	public static void ajoutDoc(int type, String titre, String auteur, String description) throws SQLException {
		PreparedStatement addDocSt = connect.prepareStatement("INSERT INTO DOCUMENT(iddoc, typedoc, title, author, description) VALUES(doc_seq.nextval, ?, ?, ?, ?)"); 
		addDocSt.setInt(1, type);
		addDocSt.setString(2, titre);
		addDocSt.setString(3, auteur);
		addDocSt.setString(4, description);
		addDocSt.executeQuery();
	}
	public static void rendreDoc(int iddoc, int iduser) throws RetourException {
		int nb = 0;
		try {
			PreparedStatement updDocSt = connect.prepareStatement("UPDATE DOCUMENT SET emprunte = 0, iduser = NULL WHERE iddoc = ? AND iduser = ?"); 
			updDocSt.setInt(1, iddoc);
			updDocSt.setInt(2, iduser);
			nb = updDocSt.executeUpdate();
		}
		catch (SQLException e) {}
		if (nb==0) {
			throw new RetourException();
		}
	}
}
