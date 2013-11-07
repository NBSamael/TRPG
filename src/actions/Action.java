package actions;

import personnages.Personnage;
import data.InstancePartie;

public abstract class Action {
	protected String nom;
	protected String description;
	protected Personnage owner;
	protected InstancePartie partie;
	
	public Action(Personnage owner, InstancePartie partie) {
		super();
		this.owner = owner;
		this.partie = partie;
	}

	public int getCoutPA()  { return 2; }
	
	public String getNom() { return nom; }
	
	public abstract void getParameters();
	
	public abstract void execute();
	
}
