package Data;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Terrain extends HashMap<XY, Case> {
	public Set<Personnage> personnages;
	//private Map<Coordonnees, Case> cases;
	private int tailleX;
	private int tailleY;

	public Terrain(int tailleX, int tailleY) {
		super();
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		personnages = new HashSet<Personnage>();
	}

	public void chargeTerrain() {
		for (int i = 1; i <= tailleY; i++) {
			for (int j = 1; j <= tailleX; j++) {
				// Code pour faire un mur
				if (j == 3 && i > 2 && i < 6)
					put(new XY(j, i), new Case(new XY(j, i), 1));
				else
				// fin code pour un mur
					put(new XY(j, i), new Case(new XY(j, i), 0));
			}
		}
	}

	public int getTailleX() {
		return tailleX;
	}

	public int getTailleY() {
		return tailleY;
	}
	
	public Case get(int x, int y) {
		return get(new XY(x, y));
	}

	public boolean isCaseAvailable(int x, int y) {
		return get(x, y).isFranchissable();
	}

	public boolean isCaseOccuped(int x, int y) {
		return get(x, y).isOccuped();
	}
	
	public boolean isCaseAvailable(XY position) {
		return get(position).isFranchissable();
	}

	public boolean isCaseOccuped(XY position) {
		return get(position).isOccuped();
	}
	
	public GrilleDeplacements calculeGrilleDeplacements(XY origine) {
		return calculeGrilleDeplacements(origine, Integer.MAX_VALUE);
	}
	
	public GrilleLigneDeVue calculeGrilleLigneDeVue(XY origine, int limite) {
		GrilleLigneDeVue td = new GrilleLigneDeVue(this, origine);
		td.calculeCiblesPossibles(limite);
		return td;
	}
	
	public GrilleLigneDeVue calculeGrilleLigneDeVue(XY origine) {
		return calculeGrilleLigneDeVue(origine, Math.max(tailleX, tailleY));
	}
	
	public GrilleDeplacements calculeGrilleDeplacements(XY origine, int limite) {
		GrilleDeplacements td = new GrilleDeplacements(this, origine);
		td.calculeDeplacementsPossibles(limite);
		return td;
	}
	
	public void deplacePersonnage (Personnage p, XY anciennePosition, XY nouvellePosition) {
		get(anciennePosition).setOccupant(null);
		get(nouvellePosition).setOccupant(p);
	}

	@Override
	public String toString() {
		String aff = new String();
		for (int j = 1; j <= tailleX+2; j++) {
			aff = aff.concat("X");
		}
		aff = aff.concat("\n");		
		for (int i = 1; i <= tailleY; i++) {
			aff = aff.concat("X");
			for (int j = 1; j <= tailleX; j++) {
				Case c = get(new XY(j, i));
				if (c.getOccupant() != null)
					aff = aff.concat(c.getOccupant().nom.substring(0,1));
				else if (c.getType()==0) 
					aff = aff.concat(" ");
				else if (c.getType()==1)
					aff = aff.concat("*");
					//aff = aff.concat(String.valueOf((char) 219));
			}
		aff = aff.concat("X\n");
		}
		for (int j = 1; j <= tailleX+2; j++) {
			aff = aff.concat("X");
		}
		return aff;
	}
}
