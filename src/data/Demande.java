package data;

import java.util.Set;

import actions.Action;

public class Demande {
	Action action;
	Type t;
	Filtre f;
	GrilleDeplacements deplPossibles;
	public XY SelectedCase;

	public enum Type {
		CASE, PERSO, JOUEUR
	}

	public enum Filtre {
		DEPL, ATT
	}

	public Demande(Action action, Type t, Filtre f, GrilleDeplacements possibilites) {
		this.action = action;
		this.t = t;
		this.f = f;
		this.deplPossibles = possibilites;
	}

}
