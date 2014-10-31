package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import personnages.Personnage;
import test.SpriteStore;

public class Terrain extends HashMap<XY, Case> implements java.io.Serializable {
	public transient Set<Personnage> personnages;
	// private Map<Coordonnees, Case> cases;
	private int tailleX;
	private int tailleY;

	public Terrain(int tailleX, int tailleY) {
		super();
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		personnages = new HashSet<Personnage>();
	}

	public void load(String filename) {
		ObjectInputStream ois = null;

		try {
			final FileInputStream fichier = new FileInputStream(filename);
			ois = new ObjectInputStream(fichier);
			Terrain temp = (Terrain) ois.readObject();
			this.tailleX = temp.tailleX;
			this.tailleY = temp.tailleY;
			this.putAll(temp);
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void save(String filename) {
		ObjectOutputStream oos = null;

		try {
			final FileOutputStream fichier = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(this);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public void genereTerrainAleatoire() {
		for (int i = 0; i < tailleY; i++) {
			for (int j = 0; j < tailleX; j++) {
				int type = Case.TYPE_GRASS;
				String sprite = SpriteStore.TILE_GRASS;
				switch (new Random().nextInt(12)) {
				case 0:
					type = Case.TYPE_MOUNTAIN;
					sprite = SpriteStore.TILE_MOUNTAIN;
					break;
				case 1:
					type = Case.TYPE_WATER;
					sprite = SpriteStore.TILE_WATER;
					break;
				case 2:
					type = Case.TYPE_FOREST;
					sprite = SpriteStore.TILE_FOREST;
					break;
				default:
					type = Case.TYPE_GRASS;
					sprite = SpriteStore.TILE_GRASS;
				}
				put(new XY(j, i), new Case(new XY(j, i), type, sprite));
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

	public GrilleLigneDeVue calculeGrilleLigneDeVue(XY origine, int limite,
			boolean priseEnCompteBlocageLdV,
			boolean priseEnCompteTerrainInfranchissable) {
		GrilleLigneDeVue td = new GrilleLigneDeVue(this, origine);
		td.calculeCiblesPossibles(limite, priseEnCompteBlocageLdV,
				priseEnCompteTerrainInfranchissable);
		return td;
	}

	public GrilleLigneDeVue calculeGrilleLigneDeVue(XY origine,
			boolean priseEnCompteBlocageLdV,
			boolean priseEnCompteTerrainInfranchissable) {
		return calculeGrilleLigneDeVue(origine, Math.max(tailleX, tailleY),
				priseEnCompteBlocageLdV, priseEnCompteTerrainInfranchissable);
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
		for (int j = 0; j < tailleX; j++) {
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
		for (int j = 0; j < tailleX; j++) {
			String index = new Integer(j).toString();
			aff = aff.concat(new Character(index.charAt(index.length() - 1))
					.toString() + " ");
		}
		aff = aff.concat("\n   ");
		for (int j = 0; j < tailleX; j++) {
			aff = aff.concat("XX");
		}
		aff = aff.concat("X\n");

		for (int i = 0; i < tailleY; i++) {
			if (i < 10)
				aff = aff.concat(" " + i);
			else {
				aff = aff.concat(new Integer(i).toString());
			}
			aff = aff.concat(" X");
			for (int j = 0; j < tailleX; j++) {
				Case c = get(new XY(j, i));
				if (c.getOccupant() != null)
					aff = aff.concat(c.getOccupant().nom.substring(0, 1));
				else if (c.getType() == Case.TYPE_GRASS)
					aff = aff.concat(" ");
				else if (c.getType() == Case.TYPE_MOUNTAIN)
					aff = aff.concat("*");
				else if (c.getType() == Case.TYPE_WATER)
					aff = aff.concat("~");
				else if (c.getType() == Case.TYPE_FOREST)
					aff = aff.concat("^");
				// aff = aff.concat(String.valueOf((char) 219));
				if (j + 1 < tailleX)
					aff = aff.concat("|");
			}
			if (i + 1 < tailleY)
				aff = aff.concat("X\n   X");
			else
				aff = aff.concat("X\n   ");
			if (i + 1 < tailleY) {
				for (int j = 0; j < tailleX; j++) {
					if (j + 1 < tailleX)
						aff = aff.concat("--");
					else
						aff = aff.concat("-");
				}
			}
			if (i + 1 < tailleY)
				aff = aff.concat("X\n");
		}

		for (int j = 0; j < tailleX; j++) {
			aff = aff.concat("XX");
		}
		aff = aff.concat("X");
		return aff;
	}
}
