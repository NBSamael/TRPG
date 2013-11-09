package personnages;

import actions.BaseAttack;
import actions.Walk;

public class AlessaRaincross extends Personnage {

	public AlessaRaincross() {
		super();
		
		this.nom = "Alessa Raincross";

		this.attaque = 4;
		this.degats = 4;
		this.defense = 9;
		this.armure = 0;
		
		this.nbPVMax = 10;
		this.nbPVActuel = this.nbPVMax;
		
		this.resistance = 10;
		
		this.vitesseMarche = 8;
		this.vitesseMarche = 12;
		
		this.nbPAMax = 4;
		this.nbPAActuels = 3;
		this.nbPARegen = 3;
		
		this.categorie = Personnage.CATEGORIE_RODEUR;
		this.faction = Personnage.FACTION_OBSCURITE;
		
		this.niveau = 45;
		
		this.actions.add(new Walk(this));
		this.actions.add(new BaseAttack(this));	
	}
	
}
