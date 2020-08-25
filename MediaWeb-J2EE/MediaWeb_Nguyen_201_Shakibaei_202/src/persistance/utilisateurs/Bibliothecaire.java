package persistance.utilisateurs;

import persistance.UserBibliotheque;

public class Bibliothecaire extends UserBibliotheque{

	public Bibliothecaire(int id, String nom) {
		super(id, nom);
	}

	@Override
	public boolean isBibliothecaire() {
		return true;
	}

}
