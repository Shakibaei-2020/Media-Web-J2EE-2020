package persistance;

import mediatek2020.items.Utilisateur;

public abstract class UserBibliotheque implements Utilisateur {

	private String nom;
	private int id;
	

	public UserBibliotheque(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	@Override
	public Object[] data() {
		return new Object[]{id};
	}

	@Override
	public String name() {
		return nom;
	}
	
	@Override
	public abstract boolean isBibliothecaire();

}
