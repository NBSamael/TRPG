package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import personnages.Personnage;
import test.AnimaTacticsUI;
import actions.Action;
import actions.Reaction;
import data.Demande.Filtre;
import data.Demande.Type;

public class Interface {
	Scanner sc;
	AnimaTacticsUI ui;
	ArrayList<Demande> listeDemandes;

	public void addSelect(ArrayList<Demande> listeDemandes) {
		this.listeDemandes.addAll(listeDemandes);
	}

	public Set<XY> getZoneDeplacement() {
		if (!listeDemandes.isEmpty()) {
			if (listeDemandes.get(0).f == Filtre.DEPL) {
				return listeDemandes.get(0).deplPossibles.keySet();
			}
		}
		return null;
	}

	public Set<XY> getPathTo(XY dest) {
		if (!listeDemandes.isEmpty()) {
			if (listeDemandes.get(0).f == Filtre.DEPL) {
				GrilleDeplacements dp = listeDemandes.get(0).deplPossibles;
				if (dp.containsKey(dest)) {
					return new HashSet<XY>(dp.getTrajet(dest));
				}
			}
		}
		return null;
	}

	public void caseClicked(XY coordonn�es) {
		if (!listeDemandes.isEmpty()) {
			if (listeDemandes.get(0).t == Type.CASE) {
				Demande d = listeDemandes.get(0);
				if (d.deplPossibles.containsKey(coordonn�es)) {
					d.SelectedCase = coordonn�es;
					listeDemandes.remove(0);
					d.action.setParameter(d);
				}
			}
		}
	}

	public Interface(AnimaTacticsUI ui) {
		sc = new Scanner(System.in);
		listeDemandes = new ArrayList<Demande>();
		this.ui = ui;
	}

	public int selectionnerAction(Personnage p) {
		System.out.println("Personnage : " + p.nom);
		System.out.println("Liste des actions :");
		ArrayList<Action> tmp = p.getActionsPossibles();
		for (int i = 0; i < tmp.size(); i++) {
			System.out.println(i + " > " + tmp.get(i).getNom());
		}
		System.out
				.print("\nSaisissez le num�ro de l'action voulue (ou n'importe quoi d'autre pour passer) : ");
		String str = sc.nextLine();
		int n = -1;
		try {
			n = new Integer(str).intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}
		return n;
	}

	public int selectionnerReaction(ArrayList<Reaction> possibilites, Joueur j) {
		System.out.println("Joueur : " + j.nom);
		System.out.println("Liste des reactions possibles :");

		for (int i = 0; i < possibilites.size(); i++) {
			System.out.println(i + " > " + possibilites.get(i).getNom());
		}
		System.out
				.print("\nSaisissez le num�ro de la reaction voulue (ou n'importe quoi d'autre pour passer) : ");
		String str = sc.nextLine();
		int n = -1;
		try {
			n = new Integer(str).intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}
		return n;
	}

	public XY selectionnerCase(String prompt) {
		System.out.print(prompt + " (format (X,Y)) : ");
		String str = sc.nextLine();
		str = str.substring(1, str.length() - 1);
		try {
			String[] coord = str.split(",");
			int x = new Integer(coord[0]).intValue();
			int y = new Integer(coord[1]).intValue();
			return new XY(x, y);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public boolean repondreOuiNon(String prompt) {
		System.out.print(prompt + " ([O]ui ou [N]on) : ");
		String str = sc.nextLine();
		if (str == "O")
			return true;
		else
			return false;
	}

	public Personnage selectionnerPersonnage(ArrayList<Personnage> listePersos) {
		System.out.println("Liste des personnages s�lectionnables :");

		for (int i = 0; i < listePersos.size(); i++) {
			System.out.println(i + " > " + listePersos.get(i).nom);
		}
		System.out
				.print("\nSaisissez le num�ro du personnage voulu (ou n'importe quoi d'autre pour passer) : ");
		String str = sc.nextLine();
		int n = -1;
		try {
			n = new Integer(str).intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		if (n >= listePersos.size() || n < 0)
			return null;

		return listePersos.get(n);
	}

	public void refreshPlateau() {
		if (ui != null) {
			ui.repaintAll();
		}
	}
}
