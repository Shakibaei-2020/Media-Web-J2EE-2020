package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2020.Mediatheque;
import mediatek2020.PersistentMediatheque;
import mediatek2020.items.Document;
import mediatek2020.items.Utilisateur;
import persistance.documents.Cd;
import persistance.documents.Jeux;
import persistance.documents.Livre;
import persistance.utilisateurs.Bibliothecaire;
import persistance.utilisateurs.Client;


public class MediathequeData implements PersistentMediatheque {
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {
	}

	private Document createDocFromResultSet(ResultSet r) throws SQLException {
		Integer type = r.getInt("typeDoc");
		Integer iduser = r.getInt("iduser");
		if (iduser==0) {
			iduser = null;
		}
		boolean emprunte = r.getInt("emprunte")!=0;
		switch (type) {
		case 0:
			return new Livre(r.getInt("idDoc"), r.getString("title"), r.getString("author"), r.getString("description"), emprunte , iduser);
		case 1:
			return new Cd(r.getInt("idDoc"), r.getString("title"), r.getString("author"), r.getString("description"), emprunte, iduser);
		case 2:
			return new Jeux(r.getInt("idDoc"), r.getString("title"), r.getString("author"), r.getString("description"), emprunte, iduser);
		default:
			return null;
		}
	}

	@Override
	public List<Document> tousLesDocuments() {
		ResultSet r;
		List<Document> l = new ArrayList<>();
		try {
			r = ConnexionBD.executeAllDocs();
			while (r.next()) {
				Document d = createDocFromResultSet(r);
				if (d!=null) {
					l.add(d);
				}
			}
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return l;
	}

	@Override
	public Utilisateur getUser(String login, String password) {
		ResultSet r;
		Utilisateur u = null;
		try {
			r = ConnexionBD.executeGetUser(login, password);
			if (r.next()) {
				if (r.getInt("bibliothecaire")!=0) {
					u = new Bibliothecaire(r.getInt("idUser"), r.getString("name"));
				} else {
					u = new Client(r.getInt("idUser"), r.getString("name"));
				}
			}
			r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public Document getDocument(int numDocument) {
		ResultSet r;
		Document d = null;
		try {
			r = ConnexionBD.executeGetDoc(numDocument);
			if (r.next()) {
				d = createDocFromResultSet(r);
			}
			r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return d;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		try {
			ConnexionBD.ajoutDoc(type, (String) args[0], (String) args[1], (String) args[2]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
