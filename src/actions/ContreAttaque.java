package actions;

import data.Des;
import data.EvenementJeu;
import data.GrilleLigneDeVue;
import data.XY;
import personnages.Personnage;

public class ContreAttaque extends Reaction {
	protected boolean actif = false;

	protected int bonusEsquive = 0;
	protected int nbDesLancesEsquive = 1;

	public Personnage cible;
	protected int bonusAttaque = 0;
	protected int nbDesLancesAttaque = 1;
	protected int bonusDegats = 0;
	protected int malusArmure = 0;
	protected int porteeAttaque = 0;

	public ContreAttaque(Personnage owner, int coutPA, String nom,
			String description, int bonusEsquive, int nbDesLancesEsquive,
			int bonusAttaque, int nbDesLancesAttaque, int bonusDegats,
			int malusArmure, int porteeAttaque) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.bonusEsquive = bonusEsquive;
		this.nbDesLancesEsquive = nbDesLancesEsquive;
		this.bonusAttaque = bonusAttaque;
		this.nbDesLancesAttaque = nbDesLancesAttaque;
		this.bonusDegats = bonusDegats;
		this.malusArmure = malusArmure;
		this.porteeAttaque = porteeAttaque;
		this.typeAction = ActionGenerique.TYPE_CONTREATTAQUE;
	}

	@Override
	public boolean isLegal(EvenementJeu ej) {
		boolean verifClasseMere = super.isLegal(ej); // Fait les vérifications
														// de la classe mère
		boolean typeCibleAttaque = ej.actionOrigine.typeAction == ActionGenerique.TYPE_ATTAQUE
				&& ((Attaque) ej.actionOrigine).cible == owner; // Vérifie si
																// l'action est
																// une attaque
																// avec pour
																// cible le
																// personnage
		boolean typeCibleCharge = ej.actionOrigine.typeAction == ActionGenerique.TYPE_CHARGE
				&& ((Charge) ej.actionOrigine).cible == owner; // Vérifie si
																// l'action est
																// une charge
																// avec pour
																// cible le
																// personnage

		XY caseCible = ej.actionOrigine.owner.getPosition();

		// Calcul de la portée de l'attaque : Si une portée est définie dans
		// l'attaque, on prend celle là. Sinon on prend la portée du personnage.
		int porteeAtt = 1;
		if (this.porteeAttaque > 0)
			porteeAtt = this.porteeAttaque;
		else
			porteeAtt = owner.getPortee();

		GrilleLigneDeVue possibilités = owner.partie.plateau
				.calculeGrilleLigneDeVue(owner.getPosition(), porteeAtt, true,
						false);

		boolean attaquantAtteignable = possibilités.contains(caseCible); // vérifie
																			// si
																			// la
																			// cible
																			// est
																			// à
																			// portee

		return (verifClasseMere && (typeCibleAttaque || typeCibleCharge) && attaquantAtteignable);
	}

	@Override
	public boolean avantJetAttaque(EvenementJeu ej) {
		return isLegal(ej);
	}

	@Override
	public boolean apresJetAttaque(EvenementJeu ej) {
		if (actif) {
			ej.esquive = true;
			ej.valeurDefenseBonus = bonusEsquive;
			ej.valeurDefenseDes = Des.lanceDes(Des.D10, nbDesLancesEsquive, Des.MAX);
			System.out.println(owner.nom + " tente de contre attaquer "
					+ ej.actionOrigine.nom);
		}
		return false;
	}
	
	@Override
	public boolean attaqueRatee(EvenementJeu ej) {
		if (actif) {
			this.actif = false;
			attaque();
		}
		return false;
	}

	@Override
	public void getParameters(EvenementJeu ej) {
		cible = ej.actionOrigine.owner;
		System.out.println("Test : " + cible.nom);
	}

	@Override
	public void execute(EvenementJeu ej) {
		this.actif = true;
	}

	public void attaque() {
		EvenementJeu ej = new EvenementJeu(this);

		owner.partie.avantJetAttaque(ej);

		int valeurAttaquePersonnage = owner.getAttaque() + this.bonusAttaque;
		int valeurAttaqueDes = Des.lanceDes(Des.D10, nbDesLancesAttaque,
				Des.MAX);
		int valeurAttaque = valeurAttaquePersonnage + valeurAttaqueDes - 2;
		System.out.println(owner.nom + " utilise " + this.nom + " contre "
				+ cible.nom + " : " + valeurAttaque + " ("
				+ valeurAttaquePersonnage + "+" + valeurAttaqueDes + "-2)");

		owner.partie.apresJetAttaque(ej);

		int valeurDefensePersonnage = cible.getDefense();
		int valeurDefense;
		if (ej.esquive) {
			valeurDefensePersonnage = valeurDefensePersonnage
					+ ej.valeurDefenseBonus;
			int valeurDefenseDes = ej.valeurDefenseDes;
			valeurDefense = valeurDefensePersonnage + valeurDefenseDes;
			System.out.println("Defense de " + cible.nom + " : "
					+ valeurDefense + " (" + valeurDefensePersonnage + "+"
					+ valeurDefenseDes + ")");
		} else {
			valeurDefense = valeurDefensePersonnage;
			System.out.println("Defense de " + cible.nom + " : "
					+ valeurDefensePersonnage);
		}

		boolean toucheCritique = (valeurAttaqueDes >= 10);
		if (toucheCritique) {
			owner.partie.touchecritique(new EvenementJeu(this));
			System.out.println("Touche critique");
		}

		if (valeurAttaqueDes >= 10 || valeurAttaque >= valeurDefense) {

			owner.partie.attaqueReussie(new EvenementJeu(this));

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

		} else {
			owner.partie.attaqueRatee(new EvenementJeu(this));
			System.out.println("Echec de l'attaque");
		}
	}

}
