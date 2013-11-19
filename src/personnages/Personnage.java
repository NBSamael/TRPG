package personnages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import actions.Action;
import actions.EffetPersistant;
import actions.Reaction;
import capacites.AttaqueADistance;
import capacites.Capacite;
import data.EvenementJeu;
import data.InstancePartie;
import data.Joueur;
import data.ListenerEvenementJeu;
import data.XY;


public abstract class Personnage implements ListenerEvenementJeu {
	public static int CATEGORIE_COMBATTANT = 0;
	public static int CATEGORIE_MYSTIQUE = 1;
	public static int CATEGORIE_RODEUR = 2;
	
	public static int FACTION_LUMIERE = 0;
	public static int FACTION_OBSCURITE = 1;
	public static int FACTION_NEUTRE = 2;
	
	public InstancePartie partie;
	public Joueur owner;
	
	public String nom;
	public XY position;
	protected boolean dissimule;
	
	protected int attaque;
	protected int degats;
	protected int defense;
	protected int armure;
	
	public int nbPVMax;
	public int nbPVActuel;
	
	protected int resistance;
	
	public int vitesseMarche;
	public int vitesseCourse;
	
	public int nbPAMax;
	public int nbPARegen;
	public int nbPAActuels;
	
	protected int categorie;
	//protected Organisation organisation; -- A Implementer
	protected int faction;
	
	protected int niveau;
	
	public List<Capacite> capacites;
	public List<Action> actions;
	public List<Reaction> reactions;
	public List<EffetPersistant> effetsActifs;

	protected int initBase;
	protected int initiativeTour;
	protected boolean aDejaBougeDansLeTour;
	
	protected int tailleZoneControle;
	
	public Personnage(Joueur owner) {
		super();
		this.owner = owner;
		this.actions = new ArrayList<Action>();
		this.reactions = new ArrayList<Reaction>();
		this.capacites = new ArrayList<Capacite>();
		this.effetsActifs = new ArrayList<EffetPersistant>();
		this.position = null;
		this.partie = null;
		this.dissimule = false;
		this.aDejaBougeDansLeTour = false;
		this.tailleZoneControle = 8;
	}
	
	public XY getPosition() {
		return position;
	}
	public void setPositionX(XY position) {
		this.position = position;
	}
	
	public int getPointsAction() {
		return nbPAActuels;
	}
	public void setPointsAction(int pointsAction) {
		this.nbPAActuels = pointsAction;
	}
	
	public int getInitBase() {
		return this.initBase;
	}
	
	public int calculInitiative() {
		return initiativeTour = initBase + 10;
	}
	
	public int getInitiative() {
		return initiativeTour;
	}
	
	public int getAttaque() {
		return attaque;
	}
	
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public int getDegats() {
		return degats;
	}
	
	public void setDegats(int degats) {
		this.degats = degats;
	}

	public int getDefense() {
		return defense;
	}
	
	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public int getArmure() {
		return armure;
	}
	
	public int getNbPAActuels() {
		return nbPAActuels;
	}

	public void setNbPAActuels(int nbPAActuels) {
		this.nbPAActuels = nbPAActuels;
	}

	public boolean isDissimule() {
		return dissimule;
	}

	public void setDissimule(boolean dissimule) {
		this.dissimule = dissimule;
	}

	public int getTailleZoneControle() {
		return tailleZoneControle;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public void setTailleZoneControle(int tailleZoneControle) {
		this.tailleZoneControle = tailleZoneControle;
	}

	public int getPortee() {
		for(Capacite c : capacites) {
			if (c.getType() == "AD") {
				return ((AttaqueADistance)c).portee;
			}
		}
		return 1;
	}
	
	public ArrayList<Action> getActionsPossibles() {
		ArrayList<Action> resultat = new ArrayList<Action>();
		for (Action a : actions) {
			if (a.isLegal())
				resultat.add(a);
		}
		return resultat;
	}
	
	public void reinitialisation() {
		System.out.println("test");
		setADejaBougeDansLeTour(false);
	}
	
	public void regenererPointsAction() {
		nbPAActuels = Math.min(nbPAActuels + nbPARegen, nbPAMax);
	}
	
	public void payerCoutsEntretien() {
		HashSet<EffetPersistant> tmp = new HashSet<EffetPersistant>(this.effetsActifs);
		for (EffetPersistant pe : tmp) {
			if (pe.getCoutEntretien() <= this.getPointsAction()) {
				if (!this.partie.ihm.repondreOuiNon("Payer le co�t d'entretien de l'effet " + pe.getNom() + " ?")) {
					pe.stop();
					this.effetsActifs.remove(pe);					
				}
			}
		}
	}
	
	public boolean getADejaBougeDansLeTour() {
		return aDejaBougeDansLeTour;
	}
	
	public void setADejaBougeDansLeTour(boolean aDejaBougeDansLeTour) {
		this.aDejaBougeDansLeTour = aDejaBougeDansLeTour;
	}

	@Override
	public void avantJetAttaque(EvenementJeu ej) {
		for(Reaction r : this.reactions)
			r.avantJetAttaque(ej);
	}

	@Override
	public void apresJetAttaque(EvenementJeu ej) {
		for(Reaction r : this.reactions)
			r.apresJetAttaque(ej);	
	}
}