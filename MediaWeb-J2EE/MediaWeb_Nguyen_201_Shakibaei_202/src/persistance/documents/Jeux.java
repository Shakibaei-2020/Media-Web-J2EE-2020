package persistance.documents;

import persistance.DocBibliotheque;

public class Jeux extends DocBibliotheque {

	public Jeux(int id, String titre, String auteur, String description, boolean emprunte, Integer idUser) {
		super(id, titre, auteur, description, emprunte, idUser);
	}

	@Override
	public String getType() {
		return "Jeu";
	}

}
