package actions;

import personnages.Personnage;
import data.Des;
import data.GrilleLigneDeVue;
import data.XY;

public class Attaque extends Action {
	protected Personnage cible;
	protected int bonusAttaque = 0;
	protected int nbDesLances = 1;
	protected int bonusDegats = 0;
	protected int malusArmure = 0;
	protected int porteeAttaque = 0;
	
	public Attaque(Personnage owner, int coutPA, String nom,
			String description, int bonusAttaque, int nbDesLances, int bonusDegats, int malusArmure, int porteeAttaque) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.bonusAttaque = bonusDegats;
		this.nbDesLances = nbDesLances;
		this.bonusDegats = bonusDegats;
		this.malusArmure = malusArmure;
		this.porteeAttaque = porteeAttaque;
		this.typeAction = Action.TYPE_ATTAQUE;
	}
	
	protected void attaque() {
		int valeurAttaquePersonnage = owner.getAttaque() + this.bonusAttaque;
		int valeurAttaqueDes = Des.lanceDes(Des.D10, nbDesLances, Des.MAX);
		int valeurAttaque = valeurAttaquePersonnage + valeurAttaqueDes;
		System.out.println(owner.nom + " utilise " + this.nom + " contre "
				+ cible.nom + " : " + valeurAttaque + " ("
				+ valeurAttaquePersonnage + "+" + valeurAttaqueDes + ")");

		int valeurDefense = cible.getDefense();
		System.out.println("Defense de " + cible.nom + " : " + valeurDefense);

		boolean toucheCritique = (valeurAttaqueDes >= 10);
		if (toucheCritique)
			System.out.println("Touche critique");

		if (valeurAttaqueDes >= 10 || valeurAttaque >= valeurDefense) {

			int niveauSucces = Math.max(0, valeurAttaque - valeurDefense);
			int baseDegats = owner.getDegats() + this.bonusDegats;

			int valeurDegats = niveauSucces + baseDegats;
			System.out.println("Valeur des degats : " + valeurDegats + " ("
					+ baseDegats + "+" + niveauSucces + ")");

			int valeurArmure = cible.getArmure();
			System.out.println("Armure de " + cible.nom + " : " + valeurArmure);

			int valeurBlessure;
			if (toucheCritique)
				valeurBlessure = Math.max(1, valeurDegats - valeurArmure);
			else
				valeurBlessure = Math.max(0, valeurDegats - valeurArmure);

			cible.nbPVActuel = cible.nbPVActuel - valeurBlessure;
			System.out.println("Valeur des PV de " + cible.nom + " : "
					+ cible.nbPVActuel + " (enlevé : " + valeurBlessure + ")");

		}
	}

	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon d'attaque du personnage puis demande à l'utilisateur
		 * de saisir une cible dans ce rayon
		 */
		XY caseCible = null;

		// Calcul de la portée de l'attaque : Si une portée est définie dans
		// l'attaque, on prend celle là. Sinon on prend la portée du personnage.
		int porteeAtt = 1;
		if (this.porteeAttaque > 0)
			porteeAtt = this.porteeAttaque;
		else
			porteeAtt = owner.getPortee();

		GrilleLigneDeVue possibilités = owner.partie.plateau
				.calculeGrilleLigneDeVue(owner.getPosition(), porteeAtt, true, false);
		do {
			caseCible = owner.partie.ihm
					.selectionnerCase("Sélectionner une cible");
		} while (owner.partie.plateau.get(caseCible) == null
				|| !possibilités.contains(caseCible)
				|| owner.partie.plateau.get(caseCible).getOccupant() == null);
		cible = owner.partie.plateau.get(caseCible).getOccupant();
	}

	@Override
	public void execute() {

		attaque();
		
	}
}
