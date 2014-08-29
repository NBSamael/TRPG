package personnages;

import test.SpriteStore;
import actions.Attaque;
import actions.AttaqueDeBase;
import actions.ChargeDeBase;
import actions.ContreAttaqueDeBase;
import actions.Course;
import actions.EffetInfligeAttitude;
import actions.EsquiveDeBase;
import actions.Marche;
import actions.RechercheDeBase;
import attitudes.Berserk;
import attitudes.Bloque;
import attitudes.Paralyse;
import data.Joueur;

public class Bael extends Personnage {

	public Bael(Joueur owner) {
		super(owner);

		this.nom = "Bael";

		this.attaque = 4;
		this.degats = 2;
		this.defense = 8;
		this.armure = 0;

		this.nbPVMax = 10;
		this.nbPVActuel = this.nbPVMax;

		this.resistance = 12;

		this.vitesseMarche = 8;
		this.vitesseCourse = 12;

		this.nbPAMax = 4;
		this.nbPARegen = 3;
		this.nbPAActuels = nbPARegen + 1;

		this.categorie = Personnage.CATEGORIE_MYSTIQUE;
		this.faction = Personnage.FACTION_OBSCURITE;

		this.niveau = 45;

		this.sprite = SpriteStore.SPRITE_BAEL;

		this.actions.add(new Marche(this));
		this.actions.add(new Course(this));
		this.actions.add(new RechercheDeBase(this));
		this.actions.add(new AttaqueDeBase(this));
		this.actions.add(new ChargeDeBase(this));

		this.actions.add(new Attaque(this, 2, "Rayon Noir",
				"Portée de l'Attaque : 16 cases, Attaque +1 / Dégâts +2", 1, 1,
				2, 0, 16));
		this.actions
				.add(new EffetInfligeAttitude(
						this,
						2,
						"La Marque d'Erebus",
						"Lancez Bloqué Niv. 2 sur une unité ennemie dans la Zone de Contrôle de Bael. Effet 16.",
						Bloque.class, 2, 16, 0));
		this.actions
				.add(new EffetInfligeAttitude(
						this,
						2,
						"La Marque de Noah",
						"Lancez Berserk Niv. 2 sur une unité amie dans la Zone de Contrôle de Bael. Effet 16.",
						Berserk.class, 2, 16, 0));
		this.actions
				.add(new EffetInfligeAttitude(
						this,
						4,
						"La Marque de Jedah",
						"Lancez Paralysé Niv. 2 sur une unité ennemie dans la Zone de Contrôle de Bael. Effet 16.",
						Paralyse.class, 2, 16, 0));

		this.reactions.add(new EsquiveDeBase(this));
		this.reactions.add(new ContreAttaqueDeBase(this));
	}

}
