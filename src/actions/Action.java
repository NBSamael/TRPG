package actions;

import personnages.Personnage;

public abstract class Action {
	protected String nom;
	protected String description;
	protected Personnage owner;
	protected int coutPA;
	protected int typeAction;
	protected int categorieAction;
	
	public static int TYPE_EFFET_INSTANTANE = 0;
	public static int TYPE_EFFET_PERSISTANT = 1;
	public static int TYPE_ATTAQUE = 2;
	public static int TYPE_MOUVEMENT = 3;
	public static int TYPE_CHARGE = 4;
	public static int TYPE_RECHERCHE = 5;
	
	public static int CATEGORIE_KI = 0;
	public static int CATEGORIE_MAGIE = 1;
	public static int CATEGORIE_SUBTERFUGE = 2;
	
	public Action(Personnage owner, int coutPA) {
		super();
		this.owner = owner;
		this.coutPA = coutPA;
	}

	public int getCoutPA()  { return coutPA; }
	
	public String getNom() { return nom; }
	
	public boolean isLegal() {
		return verifieCoutAction();
	}
	
	public boolean verifieCoutAction() {
		return (coutPA <= owner.getNbPAActuels());		
	}
	
	public void payeCout() {
		owner.setNbPAActuels(owner.getNbPAActuels() - coutPA);
	}
	
	public abstract void getParameters();
	
	public abstract void execute();
	
}
