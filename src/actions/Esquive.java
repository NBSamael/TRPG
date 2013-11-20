package actions;

import data.Des;
import data.EvenementJeu;
import data.GrilleLigneDeVue;
import data.XY;
import personnages.Personnage;

public class Esquive extends Reaction {
	protected boolean actif = false;

	protected int bonusEsquive = 0;
	protected int nbDesLancesEsquive = 1;

	public Esquive(Personnage owner, int coutPA, String nom,
			String description, int bonusEsquive, int nbDesLancesEsquive) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.bonusEsquive = bonusEsquive;
		this.nbDesLancesEsquive = nbDesLancesEsquive;
		this.typeAction = ActionGenerique.TYPE_ESQUIVE;
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
		boolean typeCibleContreAttaque = ej.actionOrigine.typeAction == ActionGenerique.TYPE_CONTREATTAQUE
				&& ((ContreAttaque) ej.actionOrigine).cible == owner; // Vérifie si
																// l'action est
																// une contre attaque
																// avec pour
																// cible le
																// personnage

		return (verifClasseMere && (typeCibleAttaque || typeCibleCharge || typeCibleContreAttaque));
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
			ej.valeurDefenseDes = Des.lanceDes(Des.D10, nbDesLancesEsquive,
					Des.MAX);
			actif = false;
			System.out.println(owner.nom + " tente d'equiver "
					+ ej.actionOrigine.nom);
		}
		return false;
	}

	@Override
	public void execute(EvenementJeu ej) {
		this.actif = true;
	}

}
