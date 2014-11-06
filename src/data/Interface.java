package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import personnages.Personnage;
import test.AnimaTacticsUI;
import test.FenetreSelectionReaction;
import actions.Action;
import actions.Reaction;
import data.Demande.Filtre;
import data.Demande.Type;

public class Interface {
	Scanner sc;
	AnimaTacticsUI ui;
	ArrayList<Demande> listeDemandes;

	public Interface(AnimaTacticsUI ui) {
		sc = new Scanner(System.in);
		listeDemandes = new ArrayList<Demande>();
		this.ui = ui;
	}

	public void afficheTexte(Object message) {
		afficheTexte(message, true);
	}

	public void afficheTexte(Object message, boolean retourALaLigne) {
		ui.append(message.toString(), retourALaLigne);
	}

	public void addSelect(Demande demande) {
		this.listeDemandes.add(demande);
	}

	public void addSelect(ArrayList<Demande> listeDemandes) {
		this.listeDemandes.addAll(listeDemandes);
	}

	public Set<XY> getZoneDeplacement() {
		if (!listeDemandes.isEmpty()) {
			if (listeDemandes.get(0).f == Filtre.DEPL) {
				if (listeDemandes.get(0).deplPossibles != null) {
					return listeDemandes.get(0).deplPossibles.keySet();
				} else {
					return new HashSet<XY>(listeDemandes.get(0).ciblesPossibles);
				}
			}
		}
		return null;
	}

	public Set<XY> getPathTo(XY dest) {
		if (!listeDemandes.isEmpty()) {
			if (listeDemandes.get(0).deplPossibles != null) {
				GrilleDeplacements dp = listeDemandes.get(0).deplPossibles;
				if (dp.containsKey(dest)) {
					return new HashSet<XY>(dp.getTrajet(dest));
				}
			} else {
				GrilleLigneDeVue dp = listeDemandes.get(0).ciblesPossibles;
				if (dp.contains(dest)) {
					return new HashSet<XY>(GrilleLigneDeVue.calculerLdV(
							ui.jeu.plateau, listeDemandes.get(0).action
									.getOwner().getPosition(), dest));
				}
			}
		}
		return null;
	}

	public Set<XY> getZoneAttaque() {
		if (!listeDemandes.isEmpty()) {
			if (listeDemandes.get(0).f == Filtre.ATT) {
				if (listeDemandes.get(0).deplPossibles != null) {
					return listeDemandes.get(0).deplPossibles.keySet();
				} else {
					return new HashSet<XY>(listeDemandes.get(0).ciblesPossibles);
				}
			}
		}
		return null;
	}

	public void caseClicked(XY coordonnées) {
		if (!listeDemandes.isEmpty()) {
			if (listeDemandes.get(0).t == Type.CASE
					&& listeDemandes.get(0).f == Filtre.DEPL) {
				Demande d = listeDemandes.get(0);
				if (d.deplPossibles != null
						&& d.deplPossibles.containsKey(coordonnées)) {
					System.out.println("ok");
					d.SelectedCase = coordonnées;
					listeDemandes.remove(0);
					d.action.setParameter(d);
				}
				if (d.deplPossibles == null
						&& d.ciblesPossibles.contains(coordonnées)) {
					d.SelectedCase = coordonnées;
					listeDemandes.remove(0);
					d.action.setParameter(d);
				}
			} else if (listeDemandes.get(0).t == Type.PERSO
					&& listeDemandes.get(0).f == Filtre.ATT) {
				Demande d = listeDemandes.get(0);
				if (d.ciblesPossibles.contains(coordonnées)
						&& ui.jeu.plateau.isCaseOccuped(coordonnées)) {
					Personnage cible = ui.jeu.plateau.get(coordonnées)
							.getOccupant();
					if (cible.owner.equipe != d.action.getOwner().owner.equipe) {
						d.SelectedPerso = cible;
						listeDemandes.remove(0);
						d.action.setParameter(d);
					}
				}
			}
		}
	}

	public int selectionnerAction(Personnage p) {
		System.out.println("Personnage : " + p.nom);
		System.out.println("Liste des actions :");
		ArrayList<Action> tmp = p.getActionsPossibles();
		for (int i = 0; i < tmp.size(); i++) {
			System.out.println(i + " > " + tmp.get(i).getNom());
		}
		System.out
				.print("\nSaisissez le numéro de l'action voulue (ou n'importe quoi d'autre pour passer) : ");
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

	class AttenteReponse extends Thread {
		FenetreSelectionReaction fsr;

		public AttenteReponse(FenetreSelectionReaction fsr) {
			this.fsr = fsr;
		}

		@Override
		public void run() {
			while (!fsr.isChoixFait())
				;
		}
	}

	public Reaction selectionnerReaction(ArrayList<Reaction> possibilites,
			Joueur j) {
		FenetreSelectionReaction fsr = new FenetreSelectionReaction(ui,
				possibilites, j);
		fsr.setVisible(true);
		while (!fsr.isChoixFait()) {
		}
		System.out.println("Le choix est fait");
		return fsr.getReaction();
	}

	public int selectionnerReaction2(ArrayList<Reaction> possibilites, Joueur j) {
		System.out.println("Joueur : " + j.nom);
		System.out.println("Liste des reactions possibles :");
		FenetreSelectionReaction fsr = new FenetreSelectionReaction(ui,
				possibilites, j);
		fsr.setVisible(true);
		for (int i = 0; i < possibilites.size(); i++) {
			System.out.println(i + " > " + possibilites.get(i).getNom());
		}
		System.out
				.print("\nSaisissez le numéro de la reaction voulue (ou n'importe quoi d'autre pour passer) : ");
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
		System.out.println("Liste des personnages sélectionnables :");

		for (int i = 0; i < listePersos.size(); i++) {
			System.out.println(i + " > " + listePersos.get(i).nom);
		}
		System.out
				.print("\nSaisissez le numéro du personnage voulu (ou n'importe quoi d'autre pour passer) : ");
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
