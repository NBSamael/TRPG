package actions;

import personnages.Personnage;

public abstract class EffetPersistant extends Effet {
	
	int coutEntretien;

	public EffetPersistant(Personnage owner, int coutPA) {
		super(owner, coutPA);
		this.typeAction = ActionGenerique.TYPE_EFFET_PERSISTANT;
	}

	public int getCoutEntretien() {
		return coutEntretien;
	}
	
	public abstract void start();
	
	public abstract void stop();
}
