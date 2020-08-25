package persistance.documents;

import persistance.DocBibliotheque;

public class Cd extends DocBibliotheque {

	public Cd(int id, String titre, String auteur, String description, boolean emprunte, Integer idUser) {
		super(id, titre, auteur, description, emprunte, idUser);
	}

	@Override
	public String getType() {
		return "CD";
	}

}
