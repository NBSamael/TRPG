package data;

import java.util.ArrayList;

import actions.Action;
import actions.Reaction;

public class EvenementJeu {
	public Action actionOrigine;
	public ArrayList<Reaction> reactionsPossibles;
	
	public boolean esquive = false;
	public int valeurDefenseDes = 0;
	public int valeurDefenseBonus = 0;
	
	public EvenementJeu(Action actionOrigine) {
		super();
		this.actionOrigine = actionOrigine;
		this.reactionsPossibles = new ArrayList<Reaction>();
	}
}
