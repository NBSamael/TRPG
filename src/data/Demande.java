package data;

import personnages.Personnage;
import actions.Action;

public class Demande {
	Action action;
	Type t;
	Filtre f;
	GrilleDeplacements deplPossibles;
	GrilleLigneDeVue ciblesPossibles;
	public XY SelectedCase;
	public Personnage SelectedPerso;

	public enum Type {
		CASE, PERSO, JOUEUR
	}

	public enum Filtre {
		DEPL, ATT
	}

	public Demande(Action action, Type t, Filtre f,
			GrilleDeplacements possibilites, GrilleLigneDeVue ciblesPossibles) {
		this.action = action;
		this.t = t;
		this.f = f;
		this.deplPossibles = possibilites;
		this.ciblesPossibles = ciblesPossibles;
	}

}
