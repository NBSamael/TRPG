package data;

import personnages.Personnage;

public class Case implements java.io.Serializable {
	private XY coordonnees;
	private Personnage occupant;
	private int type;
	private String sprite;

	public static int TYPE_GRASS = 0;
	public static int TYPE_FOREST = 1;
	public static int TYPE_MOUNTAIN = 2;
	public static int TYPE_WATER = 3;

	public Case(XY coordonnees, int type, String sprite) {
		super();
		this.coordonnees = coordonnees;
		this.type = type;
		this.sprite = sprite;
		this.occupant = null;
	}

	public Personnage getOccupant() {
		return occupant;
	}

	public void setOccupant(Personnage occupant) {
		this.occupant = occupant;
	}

	public int getType() {
		return type;
	}

	public String getSprite() {
		return sprite;
	}

	public XY getCoordonnees() {
		return coordonnees;
	}

	public boolean isFranchissable() {
		if (type == TYPE_GRASS || type == TYPE_FOREST)
			return true;
		else
			return false;
	}

	public boolean isBloqueLdV() {
		if (type == TYPE_MOUNTAIN || type == TYPE_FOREST)
			return true;
		else
			return false;
	}

	public boolean isOccuped() {
		if (occupant == null)
			return false;
		return true;
	}
}
