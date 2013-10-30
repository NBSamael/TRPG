package Data;

import java.util.ArrayList;
import java.util.List;

import Actions.Action;


public class Personnage {
	public String nom;
	public XY position;
	public int vitesseDeplacement;
	public List<Action> actions;
	private int pointsAction;
	
	public Personnage(String nom, XY position, int vitesseDeplacement,
			int pointsAction) {
		super();
		this.nom = nom;
		this.position = position;
		this.vitesseDeplacement = vitesseDeplacement;
		this.pointsAction = pointsAction;
		this.actions = new ArrayList<Action>();
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
	
	public ArrayList<Action> getActionsPossibles() {
		ArrayList<Action> resultat = new ArrayList<Action>();
		for (Action a : actions) {
			if (a.getCoutPA() <= pointsAction)
				resultat.add(a);
		}
		return resultat;
	}
	
}
