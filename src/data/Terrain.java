package data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import personnages.Personnage;

public class Terrain extends HashMap<XY, Case> {
	public Set<Personnage> personnages;
	// private Map<Coordonnees, Case> cases;
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

	public GrilleLigneDeVue calculeGrilleLigneDeVue(XY origine, int limite, boolean priseEnCompteBlocageLdV, boolean priseEnCompteTerrainInfranchissable) {
		GrilleLigneDeVue td = new GrilleLigneDeVue(this, origine);
		td.calculeCiblesPossibles(limite, priseEnCompteBlocageLdV, priseEnCompteTerrainInfranchissable);
		return td;
	}

	public GrilleLigneDeVue calculeGrilleLigneDeVue(XY origine, boolean priseEnCompteBlocageLdV, boolean priseEnCompteTerrainInfranchissable) {
		return calculeGrilleLigneDeVue(origine, Math.max(tailleX, tailleY), priseEnCompteBlocageLdV, priseEnCompteTerrainInfranchissable);
	}

	public GrilleDeplacements calculeGrilleDeplacements(XY origine, int limite) {
		GrilleDeplacements td = new GrilleDeplacements(this, origine);
		td.calculeDeplacementsPossibles(limite);
		return td;
	}

	public void deplacePersonnage(Personnage p, XY anciennePosition,
			XY nouvellePosition) {
		get(anciennePosition).setOccupant(null);
		get(nouvellePosition).setOccupant(p);
	}

	@Override
	public String toString() {
		String aff = new String();
		aff = aff.concat("    ");
		for (int j = 1; j <= tailleX; j++) {
			if (j < 10)
				aff = aff.concat("  ");
			else {
				String index = new Integer(j).toString();
				aff = aff
						.concat(new Character(index.charAt(index.length() - 2))
								.toString() + " ");
			}
		}
		aff = aff.concat("\n    ");
		for (int j = 1; j <= tailleX; j++) {
			String index = new Integer(j).toString();
			aff = aff.concat(new Character(index.charAt(index.length() - 1))
					.toString() + " ");
		}
		aff = aff.concat("\n   ");
		for (int j = 1; j <= tailleX; j++) {
			aff = aff.concat("XX");
		}
		aff = aff.concat("X\n");

		for (int i = 1; i <= tailleY; i++) {
			if (i < 10)
				aff = aff.concat(" " + i);
			else {
				aff = aff.concat(new Integer(i).toString());
			}
			aff = aff.concat(" X");
			for (int j = 1; j <= tailleX; j++) {
				Case c = get(new XY(j, i));
				if (c.getOccupant() != null)
					aff = aff.concat(c.getOccupant().nom.substring(0, 1));
				else if (c.getType() == 0)
					aff = aff.concat(" ");
				else if (c.getType() == 1)
					aff = aff.concat("*");
				// aff = aff.concat(String.valueOf((char) 219));
				if (j + 1 <= tailleX)
					aff = aff.concat("|");
			}
			if (i + 1 <= tailleY)
				aff = aff.concat("X\n   X");
			else
				aff = aff.concat("X\n   ");
			if (i + 1 <= tailleY) {
				for (int j = 1; j <= tailleX; j++) {
					if (j + 1 <= tailleX)
						aff = aff.concat("--");
					else
						aff = aff.concat("-");
				}
			}
			if (i + 1 <= tailleY)
				aff = aff.concat("X\n");
		}

		for (int j = 1; j <= tailleX; j++) {
			aff = aff.concat("XX");
		}
		aff = aff.concat("X");
		return aff;
	}
}
