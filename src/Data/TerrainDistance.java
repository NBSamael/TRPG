package Data;
import java.util.ArrayList;
import java.util.HashMap;

public class TerrainDistance extends HashMap<XY, Noeud> {
	private XY origine;
	private Terrain t;
	private HashMap<XY, Noeud> aTraiter;

	public TerrainDistance(XY origine, Terrain t) {
		super();
		this.origine = origine;
		this.t = t;
		aTraiter = new HashMap<XY, Noeud>();
	}

	public void calculeTrajets(int limite) {
		XY courant;

		Noeud ori = new Noeud();
		ori.cout = 0;
		ori.coutEclidien = 0;
		ori.parent = null;

		aTraiter.put(origine, ori);

		/*
		 * on cherche le meilleur noeud de la liste ouverte, tant qu'elle n'est
		 * pas vide ou qu'on depasse le rayon de calcul
		 */
		while ((courant = getMeilleurNoeud(limite)) != null) {

			/* on le passe dans la liste fermee, il ne peut pas déjà y être */
			this.basculeTraite(courant);

			/* on recommence la recherche des noeuds adjacents */
			this.ajouterCasesAdjacentes(courant);
		}
	}
	
	public int getDistance(XY destination) {
		return get(destination).cout;
	}
	
	public ArrayList<XY> getTrajet(XY destination) {
		ArrayList<XY> trajet = new ArrayList<XY>();
		trajet.add(0, destination);
		Noeud n = get(destination);
		while (n.parent != null) {
			trajet.add(0, n.parent);
			n = get(n.parent);
		}
		trajet.remove(0);
		return trajet;
	}

	private XY getMeilleurNoeud(int limite) {
		if (aTraiter.isEmpty())
			return null;
		int minCout = Integer.MAX_VALUE;
		int minCoutEclidien = Integer.MAX_VALUE;
		XY minNoeud = null;
		aTraiter.keySet();
		for (XY coord : aTraiter.keySet()) {
			Noeud tmp = aTraiter.get(coord);
			if (tmp.cout <= limite && (tmp.cout * 10000 + tmp.coutEclidien) < (minCout * 10000 + minCoutEclidien)) {
				minCout = tmp.cout;
				minCoutEclidien = tmp.coutEclidien;
				minNoeud = coord;
			}
		}
		return minNoeud;
	}

	private void basculeTraite(XY coord) {
		Noeud n = aTraiter.get(coord);
		this.put(coord, n);
		aTraiter.remove(coord);
	}

	private void ajouterCasesAdjacentes(XY ori) {
		Noeud tmp = new Noeud();

		for (int i = ori.getY() - 1; i <= ori.getY() + 1; i++) {
			if (i <= 0 || i > t.getTailleY()) // En dehors du terrain
				continue;
			for (int j = ori.getX() - 1; j <= ori.getX() + 1; j++) {
				if (j <= 0 || j > t.getTailleX()) // En dehors du terrain
					continue;
				if (i == ori.getY() && j == ori.getX()) // Case d'origine
					continue;

				XY it = new XY(j, i);

				if (!t.get(it).isAvailable()) // Case non franchissable
					continue;

				if (!this.containsKey(it)) {
					/*
					 * calcul du cout du noeud en cours d'étude : cout du parent
					 * + distance jusqu'au parent
					 */
					tmp.cout = get(ori).cout + 1; // XY.calculeDistance(ori,
														// it);
					tmp.coutEclidien = get(ori).coutEclidien + XY.calculeDistance(ori, it);
					tmp.parent = ori;

					if (aTraiter.containsKey(it)) {
						/*
						 * le noeud est déjà présent dans la liste ouverte, il
						 * faut comparer les couts
						 */
						if (tmp.cout * 10000 + tmp.coutEclidien < aTraiter.get(it).cout * 10000 + aTraiter.get(it).coutEclidien) {
							/* si le nouveau chemin est meilleur, on met à jour */
							aTraiter.put(it, tmp);
						}
						/*
						 * le noeud courant a un moins bon chemin, on ne change
						 * rien
						 */
					} else {
						aTraiter.put(it, tmp);
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		String aff = new String();
		aff = aff.concat("X");
		for (int j = 1; j <= t.getTailleX(); j++) {
			aff = aff.concat("XXX");
		}
		aff = aff.concat("X\n");
		for (int i = 1; i <= t.getTailleY(); i++) {
			aff = aff.concat("X");
			for (int j = 1; j <= t.getTailleX(); j++) {
				Noeud n = get(new XY(j, i));
				if (n != null)
					if (n.cout < 10)
						aff = aff.concat("  " + Integer.toString(n.cout));
					else
						aff = aff.concat(" " + Integer.toString(n.cout));
				else
					aff = aff.concat("   ");
			}
			aff = aff.concat("X\n");
		}
		aff = aff.concat("X");
		for (int j = 1; j <= t.getTailleX(); j++) {
			aff = aff.concat("XXX");
		}
		aff = aff.concat("X");
		return aff;
	}
}
