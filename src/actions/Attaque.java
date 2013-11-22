package actions;

import personnages.Personnage;
import data.Des;
import data.EvenementJeu;
import data.GrilleLigneDeVue;
import data.XY;

public class Attaque extends Action {
	public Personnage cible;
	protected int bonusAttaque = 0;
	protected int nbDesLancesAttaque = 1;
	protected int bonusDegats = 0;
	protected int malusArmure = 0;
	protected int porteeAttaque = 0;
	
	public Attaque(Personnage owner, int coutPA, String nom,
			String description, int bonusAttaque, int nbDesLances, int bonusDegats, int malusArmure, int porteeAttaque) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.bonusAttaque = bonusAttaque;
		this.nbDesLancesAttaque = nbDesLances;
		this.bonusDegats = bonusDegats;
		this.malusArmure = malusArmure;
		this.porteeAttaque = porteeAttaque;
		this.typeAction = ActionGenerique.TYPE_ATTAQUE;
	}

	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon d'attaque du personnage puis demande � l'utilisateur
		 * de saisir une cible dans ce rayon
		 */
		XY caseCible = null;

		// Calcul de la port�e de l'attaque : Si une port�e est d�finie dans
		// l'attaque, on prend celle l�. Sinon on prend la port�e du personnage.
		int porteeAtt = 1;
		if (this.porteeAttaque > 0)
			porteeAtt = this.porteeAttaque;
		else
			porteeAtt = owner.getPortee();

		GrilleLigneDeVue possibilit�s = owner.partie.plateau
				.calculeGrilleLigneDeVue(owner.getPosition(), porteeAtt, true, false);
		do {
			caseCible = owner.partie.ihm
					.selectionnerCase("S�lectionner une cible");
		} while (owner.partie.plateau.get(caseCible) == null
				|| !possibilit�s.contains(caseCible)
				|| owner.partie.plateau.get(caseCible).getOccupant() == null
				|| owner.partie.plateau.get(caseCible).getOccupant().isDissimule());
		cible = owner.partie.plateau.get(caseCible).getOccupant();
	}

	@Override
	public void execute() {

		attaque(cible, bonusAttaque, nbDesLancesAttaque, bonusDegats);
		
	}
}
