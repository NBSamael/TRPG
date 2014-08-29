package personnages;

import test.SpriteStore;
import actions.Attaque;
import actions.AttaqueDeBase;
import actions.ChargeDeBase;
import actions.ContreAttaqueDeBase;
import actions.Course;
import actions.EsquiveDeBase;
import actions.Marche;
import actions.RechercheDeBase;
import data.Joueur;

public class DereckShezard extends Personnage {

	public DereckShezard(Joueur owner) {
		super(owner);

		this.nom = "Dereck Shezard";

		this.attaque = 6;
		this.degats = 5;
		this.defense = 10;
		this.armure = 0;

		this.nbPVMax = 18;
		this.nbPVActuel = this.nbPVMax;

		this.resistance = 10;

		this.vitesseMarche = 8;
		this.vitesseCourse = 12;

		this.nbPAMax = 5;
		this.nbPARegen = 3;
		this.nbPAActuels = nbPARegen + 1;

		this.categorie = Personnage.CATEGORIE_COMBATTANT;
		this.faction = Personnage.FACTION_NEUTRE;

		this.niveau = 65;

		this.sprite = SpriteStore.SPRITE_DERECK;

		this.actions.add(new Marche(this));
		this.actions.add(new Course(this));
		this.actions.add(new RechercheDeBase(this));
		this.actions.add(new AttaqueDeBase(this));
		this.actions.add(new ChargeDeBase(this));
		this.actions.add(new Attaque(this, 3, "Balle Néo",
				"Attaque à Distance (16 cases)", 0, 1, 0, 0, 16));
		this.actions.add(new Attaque(this, 5, "Néo Génèse",
				"Attaque +4 / Dégâts +4", 4, 1, 4, 0, 0));

		this.reactions.add(new EsquiveDeBase(this));
		this.reactions.add(new ContreAttaqueDeBase(this));
	}

}
