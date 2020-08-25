package persistance;

import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.ReservationException;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

public abstract class DocBibliotheque implements Document {
	private int id;
	private String titre;
	private String auteur;
	private String description;
	private boolean emprunte;
	private Integer idUser;
	
	public DocBibliotheque(int id, String titre, String auteur, String description, boolean emprunte, Integer idUser) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.description = description;
		this.emprunte = emprunte;
		this.idUser = idUser;
	}

	@Override
	public Object[] data() {
		return new Object[] {id, getType(), titre, auteur, description, emprunte, idUser};
	}

	public abstract String getType();

	@Override
	public void emprunter(Utilisateur user) throws EmpruntException {
		Integer iduser = (Integer) user.data()[0];
		ConnexionBD.empruntDoc(id, iduser);
	}

	@Override
	public void reserver(Utilisateur user) throws ReservationException {
		throw new ReservationException();
	}
	
	@Override
	public void rendre(Utilisateur user) throws RetourException {
		Integer iduser = (Integer) user.data()[0];
		ConnexionBD.rendreDoc(id, iduser);
	}

}
