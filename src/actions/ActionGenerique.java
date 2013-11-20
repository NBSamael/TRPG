package actions;

import personnages.Personnage;

public class ActionGenerique {

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
	public static int TYPE_ESQUIVE = 6;
	public static int TYPE_CONTREATTAQUE = 7;
	
	public static int CATEGORIE_KI = 0;
	public static int CATEGORIE_MAGIE = 1;
	public static int CATEGORIE_SUBTERFUGE = 2;
	
	public ActionGenerique(Personnage owner, int coutPA) {
		this.owner = owner;
		this.coutPA = coutPA;
	}

	public int getCoutPA() { return coutPA; }
	
	public boolean verifieCoutAction() {
		return (getCoutPA() <= owner.getNbPAActuels());		
	}

	public String getNom() { return nom; }

	public void payeCout() {
		owner.setNbPAActuels(owner.getNbPAActuels() - coutPA);
	}

	public Personnage getOwner() {
		return owner;
	}

}