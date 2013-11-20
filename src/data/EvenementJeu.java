package data;

import java.util.ArrayList;

import actions.ActionGenerique;
import actions.Reaction;

public class EvenementJeu {
	public ActionGenerique actionOrigine;
	public ArrayList<Reaction> reactionsPossibles;
	
	public boolean esquive = false;
	public int valeurDefenseDes = 0;
	public int valeurDefenseBonus = 0;
	
	public EvenementJeu(ActionGenerique actionOrigine) {
		super();
		this.actionOrigine = actionOrigine;
		this.reactionsPossibles = new ArrayList<Reaction>();
	}
}
