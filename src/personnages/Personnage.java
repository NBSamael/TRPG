package personnages;

import java.util.ArrayList;
import java.util.List;

import actions.Action;
import capacites.AttaqueADistance;
import capacites.Capacite;
import data.XY;


public class Personnage {
	public static int CATEGORIE_COMBATTANT = 0;
	public static int CATEGORIE_MYSTIQUE = 1;
	public static int CATEGORIE_RODEUR = 2;
	
	public static int FACTION_LUMIERE = 0;
	public static int FACTION_OBSCURITE = 1;
	public static int FACTION_NEUTRE = 2;
	
	
	public String nom;
	public XY position;
	
	public int attaque;
	private int degats;
	private int defense;
	private int armure;
	
	public int nbPVMax;
	public int nbPVActuel;
	
	private int resistance;
	
	public int vitesseMarche;
	public int vitesseCourse;
	
	public int nbPAMax;
	public int nbPARegen;
	public int nbPAActuels;
	
	private int categorie;
	//private Organisation organisation; -- A Implementer
	private int faction;
	
	private int niveau;
	
	public List<Capacite> capacites;
	public List<Action> actions;

	private int initBase;
	private int initiativeTour;
	
	public Personnage(String nom, XY position, int vitesseDeplacement,
			int nbPAActuels, int initBase, int maxPdV, int baseAttaque) {
		super();
		this.nom = nom;
		this.position = position;
		this.vitesseMarche = vitesseDeplacement;
		this.nbPAActuels = nbPAActuels;
		this.actions = new ArrayList<Action>();
		this.capacites = new ArrayList<Capacite>();
		this.initBase = initBase;
		this.nbPVMax = maxPdV;
		this.nbPVActuel = maxPdV;
		this.attaque = baseAttaque;
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
			if (a.getCoutPA() <= nbPAActuels)
				resultat.add(a);
		}
		return resultat;
	}
	
}
