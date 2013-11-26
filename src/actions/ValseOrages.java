package actions;

import personnages.Personnage;

public class ValseOrages extends AttaqueDeBase {
	private int nbAttaque = 4;

	public ValseOrages(Personnage owner) {
		super(owner);
		this.nom = "Valse d'Orages";
		this.description = "Port�e de l'Attaque : 6 cases. Alessa ex�cute quatres attaques sur une seule unit� vis�e";
		this.coutPA = 4;
	}

	@Override
	public void execute() {
		for (int i = 1; i <= nbAttaque; i++) {
			System.out.println("Attaque num�ro " + i);
			attaque(cible, bonusAttaque, nbDesLancesAttaque, bonusDegats);
			if (!cible.isVivant())
				return;
		}
	}
}
