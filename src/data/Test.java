package data;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;

import personnages.AlessaRaincross;
import personnages.Bael;
import personnages.DereckShezard;
import personnages.Personnage;
import test.Fenetre;

public class Test {

	public static void main(String[] args) {
		// testdes();
		test2();
	}

	private static void test2() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstancePartie jeu = createGame(100, 60);

					Fenetre frame = new Fenetre(jeu);
					frame.setVisible(true);

					// jeu.initialisePartie();
					// jeu.lancePartie();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private InstancePartie createGame(int width, int height) {

				Interface interf = new Interface(null);

				Terrain t = new Terrain(width, height);
				t.genereTerrainAleatoire();

				Joueur j1 = new Joueur("NBS");
				Joueur j2 = new Joueur("Mirth");

				Personnage p1 = new DereckShezard(j1);

				Random r = new Random();
				int rX, rY;

				do {
					rX = 1 + r.nextInt(width);
					rY = 1 + r.nextInt(height);
				} while (!t.get(new XY(rX, rY)).isFranchissable()
						&& !t.get(new XY(rX, rY)).isOccuped());

				p1.setPosition(new XY(rX, rY));
				t.get(new XY(rX, rY)).setOccupant(p1);
				t.personnages.add(p1);

				Personnage p2 = new AlessaRaincross(j2);

				do {
					rX = 1 + r.nextInt(width);
					rY = 1 + r.nextInt(height);
				} while (!t.get(new XY(rX, rY)).isFranchissable()
						&& !t.get(new XY(rX, rY)).isOccuped());

				p2.setPosition(new XY(rX, rY));
				t.get(new XY(rX, rY)).setOccupant(p2);
				t.personnages.add(p2);

				Personnage p3 = new Bael(j2);

				do {
					rX = 1 + r.nextInt(width);
					rY = 1 + r.nextInt(height);
				} while (!t.get(new XY(rX, rY)).isFranchissable()
						&& !t.get(new XY(rX, rY)).isOccuped());

				p3.setPosition(new XY(rX, rY));
				t.get(new XY(rX, rY)).setOccupant(p3);
				t.personnages.add(p3);

				j1.persosActifs.add(p1);
				j2.persosActifs.add(p2);
				j2.persosActifs.add(p3);

				Equipe e1 = new Equipe("Equipe 1");
				Equipe e2 = new Equipe("Equipe 2");
				e1.joueursActifs.add(j1);
				e2.joueursActifs.add(j2);

				ArrayList<Equipe> listeEquipes = new ArrayList<Equipe>();
				listeEquipes.add(e1);
				listeEquipes.add(e2);

				InstancePartie jeu = new InstancePartie(t, listeEquipes, interf);

				j1.equipe = e1;
				j2.equipe = e2;

				e1.partie = jeu;
				e2.partie = jeu;

				p1.partie = jeu;
				p2.partie = jeu;
				p3.partie = jeu;
				return jeu;
			}
		});
	}
}
