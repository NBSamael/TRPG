package Data;

import java.util.*;

public class GrilleLigneDeVue extends ArrayList<XY> {
	private XY origine;
	private Terrain t;
	
	public GrilleLigneDeVue(Terrain t, XY origine) {
		super();
		this.t = t;
		this.origine = origine;
	}
	
	public void calculeCiblesPossibles(int limite) {
		for (int i = origine.getY() - limite; i <= origine.getY() + limite; i++) {
			if (i <= 0 || i > t.getTailleY()) // En dehors du terrain
				continue;
			for (int j = origine.getX() - limite; j <= origine.getX() + limite; j++) {
				if (j <= 0 || j > t.getTailleX()) // En dehors du terrain
					continue;
				if (i == origine.getY() && j == origine.getX()) // Case d'origine
					continue;

				XY it = new XY(j, i);
				if (t.get(it).isFranchissable() && hasLdV(t, origine, it)) {
					this.add(it);
				}
			}
		}
	}

	public static boolean hasLdV(Terrain t, XY origine, XY destination) {
		ArrayList<XY> resultat = calculerLdV(t, origine, destination);
		for (XY etape : resultat) {
			if (t.get(etape).isBloqueLdV())
				return false;
		}
		return true;
	}

	public static ArrayList<XY> calculerLdV(Terrain t, XY origine, XY destination) {
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
		resultat.add(new XY(x,y));

		if (dx > dy) {
			cumul = dx / 2;
			for (i = 1; i <= dx; i++) {
				x += xinc;
				cumul += dy;
				if (cumul >= dx) {
					cumul -= dx;
					y += yinc;
				}
				resultat.add(new XY(x,y));
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
				resultat.add(new XY(x,y));
			}
		}

		return resultat;
	}
}