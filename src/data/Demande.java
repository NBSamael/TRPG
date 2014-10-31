package data;

import actions.Action;

public class Demande {
	Action action;
	Type t;
	GrilleDeplacements grille;
	public XY SelectedCase;

	public enum Type {
		CASE, PERSO, JOUEUR
	}

	public Demande(Action action, Type t, GrilleDeplacements grille) {
		this.action = action;
		this.t = t;
		this.grille = grille;
	}

}
