package persistance.utilisateurs;

import persistance.UserBibliotheque;

public class Client extends UserBibliotheque{

	public Client(int id, String nom) {
		super(id, nom);
	}

	@Override
	public boolean isBibliothecaire() {
		return false;
	}

}
