package data;

import java.util.ArrayList;

import personnages.Personnage;

public class GrilleLigneDeVue extends ArrayList<XY> {
	private XY origine;
	private Terrain t;

	public GrilleLigneDeVue(Terrain t, XY origine) {
		super();
		this.t = t;
		this.origine = origine;
	}

	public void calculeCiblesPossibles(int limite,
			boolean priseEnCompteBlocageLdV,
			boolean priseEnCompteTerrainInfranchissable) {
		for (int i = origine.getY() - limite; i <= origine.getY() + limite; i++) {
			if (i < 0 || i >= t.getTailleY()) // En dehors du terrain
				continue;
			for (int j = origine.getX() - limite; j <= origine.getX() + limite; j++) {
				if (j < 0 || j >= t.getTailleX()) // En dehors du terrain
					continue;
				if (i == origine.getY() && j == origine.getX()) // Case
																// d'origine
					continue;

				XY it = new XY(j, i);
				if (t.get(it).isFranchissable()
						&& hasLdV(t, origine, it, priseEnCompteBlocageLdV,
								priseEnCompteTerrainInfranchissable)) {
					this.add(it);
				}
			}
		}
	}

	public ArrayList<Personnage> getPersonnagesDansZone() {
		ArrayList<Personnage> valeurRetour = new ArrayList<Personnage>();
		for (XY coord : this) {
			Case c = this.t.get(coord);
			if (c.isOccuped()) {
				valeurRetour.add(c.getOccupant());
			}
		}
		return valeurRetour;
	}

	public ArrayList<Personnage> getAlliesDansZone(Personnage demandeur) {
		ArrayList<Personnage> valeurRetour = new ArrayList<Personnage>();
		for (XY coord : this) {
			Case c = this.t.get(coord);
			if (c.isOccuped()
					&& c.getOccupant().owner.equipe == demandeur.owner.equipe) {
				valeurRetour.add(c.getOccupant());
			}
		}
		return valeurRetour;
	}

	public ArrayList<Personnage> getEnnemisDansZone(Personnage demandeur) {
		ArrayList<Personnage> valeurRetour = new ArrayList<Personnage>();
		for (XY coord : this) {
			Case c = this.t.get(coord);
			if (c.isOccuped()
					&& c.getOccupant().owner.equipe != demandeur.owner.equipe) {
				valeurRetour.add(c.getOccupant());
			}
		}
		return valeurRetour;
	}

	public static boolean hasLdV(Terrain t, XY origine, XY destination,
			boolean priseEnCompteBlocageLdV,
			boolean priseEnCompteTerrainInfranchissable) {
		ArrayList<XY> resultat = calculerLdV(t, origine, destination);
		for (XY etape : resultat) {
			if (priseEnCompteBlocageLdV && t.get(etape).isBloqueLdV())
				return false;
			if (priseEnCompteTerrainInfranchissable
					&& !t.get(etape).isFranchissable())
				return false;
		}
		return true;
	}

	public static ArrayList<XY> calculerLdV(Terrain t, XY origine,
			XY destination) {
		int dx, dy, i, xinc, yinc, cumul, x, y;
		ArrayList<XY> resultat = new ArrayList<XY>();

		x = origine.getX();
		y = origine.getY();
		dx = destination.getX() - origine.getX();
		dy = destination.getY() - origine.getY();
		xinc = (dx > 0) ? 1 : -1;
		yinc = (dy > 0) ? 1 : -1;
		dx = Math.abs(dx);
		dy = Math.abs(dy);
		resultat.add(new XY(x, y));

		if (dx > dy) {
			cumul = dx / 2;
			for (i = 1; i <= dx; i++) {
				x += xinc;
				cumul += dy;
				if (cumul >= dx) {
					cumul -= dx;
					y += yinc;
				}
				resultat.add(new XY(x, y));
			}
		} else {
			cumul = dy / 2;
			for (i = 1; i <= dy; i++) {
				y += yinc;
				cumul += dx;
				if (cumul >= dy) {
					cumul -= dy;
					x += xinc;
				}
				resultat.add(new XY(x, y));
			}
		}

		return resultat;
	}

	@Override
	public String toString() {
		String aff = new String();
		aff = aff.concat("X");
		for (int j = 0; j < t.getTailleX(); j++) {
			aff = aff.concat("XXX");
		}
		aff = aff.concat("X\n");
		for (int i = 0; i < t.getTailleY(); i++) {
			aff = aff.concat("X");
			for (int j = 0; j < t.getTailleX(); j++) {
				if (this.contains(new XY(j, i))) {
					int cout = calculerLdV(t, origine, new XY(j, i)).size();
					if (cout < 10)
						aff = aff.concat("  " + Integer.toString(cout));
					else
						aff = aff.concat(" " + Integer.toString(cout));
				} else if (origine == new XY(j, i))
					aff = aff.concat("  0");
				else
					aff = aff.concat("   ");
			}
			aff = aff.concat("X\n");
		}
		aff = aff.concat("X");
		for (int j = 0; j < t.getTailleX(); j++) {
			aff = aff.concat("XXX");
		}
		aff = aff.concat("X");
		return aff;
	}
}