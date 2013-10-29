
public class Case {
	private XY coordonnees;
	private Personnage occupant;
	private int type;
	
	
	public Case(XY coordonnees, int type) {
		super();
		this.coordonnees = coordonnees;
		this.type = type;
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
	public XY getCoordonnees() {
		return coordonnees;
	}

	public boolean isAvailable() {
		if (type == 0)
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
