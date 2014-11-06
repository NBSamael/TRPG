package actions;

import personnages.Personnage;
import data.Des;
import data.EvenementJeu;

public class ActionGenerique {

	protected String nom;
	protected String description;
	protected Personnage owner;
	protected int coutPA;

	protected int typeAction;
	protected int categorieAction;

	public static int TYPE_EFFET_INSTANTANE = 0;
	public static int TYPE_EFFET_PERSISTANT = 1;
	public static int TYPE_ATTAQUE = 2;
	public static int TYPE_MOUVEMENT = 3;
	public static int TYPE_CHARGE = 4;
	public static int TYPE_RECHERCHE = 5;
	public static int TYPE_ESQUIVE = 6;
	public static int TYPE_CONTREATTAQUE = 7;

	public static int CATEGORIE_KI = 0;
	public static int CATEGORIE_MAGIE = 1;
	public static int CATEGORIE_SUBTERFUGE = 2;

	public ActionGenerique(Personnage owner, int coutPA) {
		this.owner = owner;
		this.coutPA = coutPA;
	}

	public int getCoutPA() {
		return coutPA;
	}

	public boolean verifieCoutAction() {
		return (getCoutPA() <= owner.getNbPAActuels());
	}

	public String getNom() {
		return nom;
	}

	public void payeCout() {
		owner.setNbPAActuels(owner.getNbPAActuels() - coutPA);
	}

	public Personnage getOwner() {
		return owner;
	}

	public String getDescription() {
		return description;
	}

	public int jetAttaque(int nbDesLancesAttaque) {
		return Des.lanceDes(Des.D10, nbDesLancesAttaque, Des.MAX);
	}

	public void attaque(Personnage cible, int bonusAttaque,
			int nbDesLancesAttaque, int bonusDegats) {
		reveleAttaquant();

		owner.partie.avantJetAttaque(new EvenementJeu(this));

		int valeurAttaquePersonnage = owner.getAttaque() + bonusAttaque;
		int valeurAttaqueDes = jetAttaque(nbDesLancesAttaque);
		int valeurAttaque = valeurAttaquePersonnage + valeurAttaqueDes;
		System.out.println(owner.nom + " utilise " + this.nom + " contre "
				+ cible.nom + " : " + valeurAttaque + " ("
				+ valeurAttaquePersonnage + "+" + valeurAttaqueDes + ")");

		EvenementJeu ej = new EvenementJeu(this);
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

		boolean toucheCritique = isToucheCritique(valeurAttaqueDes);
		if (toucheCritique) {
			owner.partie.touchecritique(new EvenementJeu(this));
			System.out.println("Touche critique");
		}

		if (toucheCritique || valeurAttaque >= valeurDefense) {

			owner.partie.attaqueReussie(new EvenementJeu(this));

			int niveauSucces = Math.max(0, valeurAttaque - valeurDefense);
			int baseDegats = owner.getDegats() + bonusDegats;

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

			cible.recoitBlessure(valeurBlessure);
			System.out.println("Valeur des PV de " + cible.nom + " : "
					+ cible.nbPVActuel + " (enlevé : " + valeurBlessure + ")");

		} else {
			System.out.println("Echec de l'attaque");
			owner.partie.attaqueRatee(new EvenementJeu(this));
		}
	}

	protected void reveleAttaquant() {
		owner.setDissimule(false);
	}

	protected boolean isToucheCritique(int valeurAttaqueDes) {
		return (valeurAttaqueDes >= 10);
	}

	@Override
	public String toString() {
		return this.nom;
	}

}