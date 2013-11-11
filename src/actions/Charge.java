package actions;

import java.util.ArrayList;

import data.Des;
import data.GrilleLigneDeVue;
import data.XY;
import personnages.Personnage;

public class Charge extends Action {
	protected Personnage cible;
	protected int bonusAttaque = 0;
	protected int nbDesLances = 1;
	protected int bonusDegats = 0;
	protected int malusArmure = 0;
	protected int vitesseCharge = 0;
	protected ArrayList<XY> trajet;

	public Charge(Personnage owner, int coutPA, String nom,
			String description, int bonusAttaque, int nbDesLances,  int bonusDegats, int malusArmure, int vitesseCharge) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.bonusAttaque = bonusDegats;
		this.nbDesLances = nbDesLances;
		this.bonusDegats = bonusDegats;
		this.malusArmure = malusArmure;
		this.vitesseCharge = vitesseCharge;
		this.typeAction = Action.TYPE_CHARGE;
	}

	@Override
	public boolean isLegal() {
		return (verifieCoutAction() && !owner.getADejaBougeDansLeTour());
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
		int vitesseChg = 1;
		if (this.vitesseCharge <= 0)
			vitesseChg = owner.vitesseCourse;

		GrilleLigneDeVue possibilités = owner.partie.plateau
				.calculeGrilleLigneDeVue(owner.getPosition(), vitesseChg + 1, true, false);
		do {
			caseCible = owner.partie.ihm
					.selectionnerCase("Sélectionner une cible");
		} while (owner.partie.plateau.get(caseCible) == null
				|| !possibilités.contains(caseCible)
				|| owner.partie.plateau.get(caseCible).getOccupant() == null
				|| XY.calculeDistance( owner.getPosition(), caseCible) <= 2); // On vérifie que la cible n'est pas déjà au contact avec le personnage
		cible = owner.partie.plateau.get(caseCible).getOccupant();
		
		/*
		 * Calcul le rayon de deplacement du personnage puis demande à
		 * l'utilisateur de saisir une position de destination dans ce rayon
		 */
		XY destSelec = null;
		possibilités = owner.partie.plateau.calculeGrilleLigneDeVue(owner.getPosition(), vitesseChg, false, true);
		System.out.println(possibilités);
		do {
			destSelec = owner.partie.ihm
					.selectionnerCase("Sélectionner une destination");
		} while (owner.partie.plateau.get(destSelec) == null
				|| !possibilités.contains(destSelec)
				|| owner.partie.plateau.get(destSelec).getOccupant() != null
				|| XY.calculeDistance( caseCible, destSelec) > 2); // On vérifie que la destination est au contact de la cible
		trajet = GrilleLigneDeVue.calculerLdV(owner.partie.plateau, owner.getPosition(), destSelec);
	}

	@Override
	public void execute() {
		// Deplace le personnage case par case
		System.out.print(owner.nom + " court de " + owner.getPosition() + " à ");
		for (XY etape : trajet) {
			owner.partie.plateau.deplacePersonnage(owner, owner.getPosition(), etape);
			owner.setPositionX(etape);
		}
		System.out.println(owner.getPosition());
		
		attaque();
		
		owner.setADejaBougeDansLeTour(true);
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

}
