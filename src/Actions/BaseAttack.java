package Actions;

import Data.GrilleLigneDeVue;
import Data.InstancePartie;
import Data.Personnage;
import Data.XY;

public class BaseAttack extends Attack {
	private Personnage cible;

	public BaseAttack(Personnage owner, InstancePartie partie) {
		super(owner, partie);
		this.nom = "Attaque de base";
		this.description = "Le personnage attaque une cible situ�e dans un rayon maximum �gal � sa port�e d'attaque";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon d'attaque du personnage puis demande �
		 * l'utilisateur de saisir une cible dans ce rayon
		 */
		XY caseCible = null;
		GrilleLigneDeVue possibilit�s = partie.plateau.calculeGrilleLigneDeVue(
				owner.getPosition(), owner.porteeAttaque);
		do {
			caseCible = partie.ihm
					.selectionnerCase("S�lectionner une cible");
		} while (partie.plateau.get(caseCible) == null
				|| !possibilit�s.contains(caseCible) || partie.plateau.get(caseCible).getOccupant() == null);
		cible = partie.plateau.get(caseCible).getOccupant();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		cible.currentPdV = cible.currentPdV - owner.baseAttaque;
		System.out.println(owner.nom + " attaque " + cible.nom + " et lui fait " + owner.baseAttaque + " points de dommage");
		System.out.println("Il reste " + cible.currentPdV + " points de vie � " + cible.nom);
	}

}
