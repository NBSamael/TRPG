package Data;

import java.util.ArrayList;
import java.util.List;

import Actions.Action;


public class Personnage {
	public String nom;
	public XY position;
	
	public int maxPdV;
	public int currentPdV;
	
	public int vitesseDeplacement;
	public int porteeAttaque;
	public int baseAttaque;
	
	public List<Action> actions;
	private int pointsAction;
	
	private int initBase;
	private int initiativeTour;
	
	public Personnage(String nom, XY position, int vitesseDeplacement,
			int pointsAction, int initBase, int maxPdV, int porteeAttaque, int baseAttaque) {
		super();
		this.nom = nom;
		this.position = position;
		this.vitesseDeplacement = vitesseDeplacement;
		this.pointsAction = pointsAction;
		this.actions = new ArrayList<Action>();
		this.initBase = initBase;
		this.maxPdV = maxPdV;
		this.currentPdV = maxPdV;
		this.porteeAttaque = porteeAttaque;
		this.baseAttaque = baseAttaque;
	}
	
	
	public XY getPosition() {
		return position;
	}
	public void setPositionX(XY position) {
		this.position = position;
	}
	
	public int getPointsAction() {
		return pointsAction;
	}
	public void setPointsAction(int pointsAction) {
		this.pointsAction = pointsAction;
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
	
	public ArrayList<Action> getActionsPossibles() {
		ArrayList<Action> resultat = new ArrayList<Action>();
		for (Action a : actions) {
			if (a.getCoutPA() <= pointsAction)
				resultat.add(a);
		}
		return resultat;
	}
	
}