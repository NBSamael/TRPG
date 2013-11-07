package data;

public class XY {
	private int x;
	private int y;
	
	
	public XY(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public static int calculeDistance(XY c1, XY c2) {
		return ((c2.x - c1.x) * (c2.x - c1.x)) + ((c2.y - c1.y) * (c2.y - c1.y));
	}

	@Override
	public boolean equals(Object arg0) {
		XY c = (XY) arg0;
		if (this.x == c.x && this.y == c.y)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return 10000*x+y;
	}

	@Override
	public String toString() {
		String aff = new String();
		aff = aff.concat("(");
		aff = aff.concat(Integer.toString(x));
		aff = aff.concat(",");
		aff = aff.concat(Integer.toString(y));
		aff = aff.concat(")");
		return aff;
	}
	
	
}
