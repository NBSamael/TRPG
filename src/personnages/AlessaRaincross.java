package personnages;

import capacites.AttaqueADistance;
import data.Joueur;
import actions.AttaqueDeBase;
import actions.ChargeDeBase;
import actions.ContreAttaqueDeBase;
import actions.Course;
import actions.EsquiveDeBase;
import actions.Furtivite;
import actions.RechercheDeBase;
import actions.SniperSombre;
import actions.ValseOrages;
import actions.Marche;

public class AlessaRaincross extends Personnage {

	public AlessaRaincross(Joueur owner) {
		super(owner);
		
		this.nom = "Alessa Raincross";

		this.attaque = 4;
		this.degats = 4;
		this.defense = 9;
		this.armure = 0;
		
		this.nbPVMax = 10;
		this.nbPVActuel = this.nbPVMax;
		
		this.resistance = 10;
		
		this.vitesseMarche = 8;
		this.vitesseCourse = 12;
		
		this.nbPAMax = 4;
		this.nbPARegen = 3;
		this.nbPAActuels = nbPARegen + 1;
		
		this.categorie = Personnage.CATEGORIE_RODEUR;
		this.faction = Personnage.FACTION_OBSCURITE;
		
		this.niveau = 45;
		
		this.capacites.add(new AttaqueADistance(this, 16));
		
		this.actions.add(new Marche(this));
		this.actions.add(new Course(this));
		this.actions.add(new RechercheDeBase(this));
		this.actions.add(new AttaqueDeBase(this));
		this.actions.add(new ChargeDeBase(this));
		this.actions.add(new Furtivite(this));
		this.actions.add(new SniperSombre(this));
		this.actions.add(new ValseOrages(this));
		
		this.reactions.add(new EsquiveDeBase(this));
		this.reactions.add(new ContreAttaqueDeBase(this));
	}
	
}
