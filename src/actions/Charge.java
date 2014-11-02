package actions;

import java.util.ArrayList;

import personnages.Personnage;
import data.GrilleLigneDeVue;
import data.XY;

public class Charge extends Action {
	protected Personnage cible;
	protected int bonusAttaque = 0;
	protected int nbDesLancesAttaque = 1;
	protected int bonusDegats = 0;
	protected int malusArmure = 0;
	protected int vitesseCharge = 0;
	protected ArrayList<XY> trajet;

	public Charge(Personnage owner, int coutPA, String nom, String description,
			int bonusAttaque, int nbDesLancesAttaque, int bonusDegats,
			int malusArmure, int vitesseCharge) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.bonusAttaque = bonusDegats;
		this.nbDesLancesAttaque = nbDesLancesAttaque;
		this.bonusDegats = bonusDegats;
		this.malusArmure = malusArmure;
		this.vitesseCharge = vitesseCharge;
		this.typeAction = ActionGenerique.TYPE_CHARGE;
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
				.calculeGrilleLigneDeVue(owner.getPosition(), vitesseChg + 1,
						true, false);
		do {
			caseCible = owner.partie.ihm
					.selectionnerCase("Sélectionner une cible");
			System.out.println(owner.partie.plateau.get(caseCible)
					.getOccupant());
			System.out.println(XY.calculeDistance(owner.getPosition(),
					caseCible));
		} while (owner.partie.plateau.get(caseCible) == null
				|| !possibilités.contains(caseCible)
				|| owner.partie.plateau.get(caseCible).getOccupant() == null
				|| owner.partie.plateau.get(caseCible).getOccupant()
						.isDissimule()
				|| XY.calculeDistance(owner.getPosition(), caseCible) <= 2); // On
																				// vérifie
																				// que
																				// la
																				// cible
																				// n'est
																				// pas
																				// déjà
																				// au
																				// contact
																				// avec
																				// le
																				// personnage
		cible = owner.partie.plateau.get(caseCible).getOccupant();

		/*
		 * Calcul le rayon de deplacement du personnage puis demande à
		 * l'utilisateur de saisir une position de destination dans ce rayon
		 */
		XY destSelec = null;
		possibilités = owner.partie.plateau.calculeGrilleLigneDeVue(
				owner.getPosition(), vitesseChg, false, true);
		System.out.println(possibilités);
		do {
			destSelec = owner.partie.ihm
					.selectionnerCase("Sélectionner une destination");
		} while (owner.partie.plateau.get(destSelec) == null
				|| !possibilités.contains(destSelec)
				|| owner.partie.plateau.get(destSelec).getOccupant() != null
				|| XY.calculeDistance(caseCible, destSelec) > 2); // On vérifie
																	// que la
																	// destination
																	// est au
																	// contact
																	// de la
																	// cible
		trajet = GrilleLigneDeVue.calculerLdV(owner.partie.plateau,
				owner.getPosition(), destSelec);
	}

	@Override
	public void execute() {
		// Deplace le personnage case par case
		System.out
				.print(owner.nom + " court de " + owner.getPosition() + " à ");
		for (XY etape : trajet) {
			owner.partie.deplacePersonnage(owner, owner.getPosition(), etape);
			owner.setPosition(etape);
		}
		System.out.println(owner.getPosition());

		attaque(cible, bonusAttaque, nbDesLancesAttaque, bonusDegats);

		owner.setADejaBougeDansLeTour(true);
	}
}
