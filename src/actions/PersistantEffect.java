package actions;

import personnages.Personnage;

public abstract class PersistantEffect extends Effect {
	
	int coutEntretien;

	public PersistantEffect(Personnage owner, int coutPA) {
		super(owner, coutPA);
		this.typeAction = Action.TYPE_EFFET_PERSISTANT;
	}

	public int getCoutEntretien() {
		return coutEntretien;
	}
	
	public abstract void start();
	
	public abstract void stop();
}
