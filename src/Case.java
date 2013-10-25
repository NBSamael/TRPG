
public class Case {
	private int x;
	private int y;
	private Personnage occupant;
	private int type;
	
	
	public Case(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
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
