package personnages;

import java.util.ArrayList;

import actions.Action;
import actions.BaseAttack;
import actions.Walk;
import capacites.Capacite;

public class DereckShezard extends Personnage {
	
	public DereckShezard() {
		super();
		
		this.nom = "Dereck Shezard";

		this.attaque = 6;
		this.degats = 5;
		this.defense = 10;
		this.armure = 0;
		
		this.nbPVMax = 18;
		this.nbPVActuel = this.nbPVMax;
		
		this.resistance = 10;
		
		this.vitesseMarche = 8;
		this.vitesseMarche = 12;
		
		this.nbPAMax = 5;
		this.nbPAActuels = 3;
		this.nbPARegen = 3;
		
		this.categorie = Personnage.CATEGORIE_COMBATTANT;
		this.faction = Personnage.FACTION_NEUTRE;
		
		this.niveau = 65;
		
		this.actions.add(new Walk(this));
		this.actions.add(new BaseAttack(this));
		this.actions.add(new BaseAttack(this, 3, "Balle N�o", "Attaque � Distance (16 cases)", 0, 0, 16));
		this.actions.add(new BaseAttack(this, 5, "N�o G�n�se", "Attaque +4 / D�g�ts +4", 4, 4, 0));	
	}

}
