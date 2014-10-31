package test;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import personnages.AlessaRaincross;
import personnages.Bael;
import personnages.DereckShezard;
import personnages.Personnage;
import data.Equipe;
import data.InstancePartie;
import data.Interface;
import data.Joueur;
import data.Terrain;
import data.XY;

public class AnimaTacticsUI {

	public InstancePartie jeu;

	public JFrame mainWindow;

	private JTextPane textAreaMessages;

	private JPanel tableauPrincipal;
	private FichePerso infosPersos;
	private Picture plateau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AnimaTacticsUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnimaTacticsUI() {
		jeu = createGame(100, 60);
		initialize(jeu);
		mainWindow.setVisible(true);
		mainWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(InstancePartie jeu) {
		mainWindow = new JFrame();
		mainWindow.setResizable(false);
		mainWindow.setTitle("Anima Tactics");
		mainWindow.setSize(1200, 700);
		mainWindow.setMinimumSize(new Dimension(1200, 700));
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setLayout(
				new BoxLayout(mainWindow.getContentPane(), BoxLayout.Y_AXIS));

		tableauPrincipal = new JPanel();
		tableauPrincipal.setMinimumSize(new Dimension(1200, 500));
		tableauPrincipal.setPreferredSize(new Dimension(1200, 500));
		tableauPrincipal.setMaximumSize(new Dimension(1200, 500));
		tableauPrincipal.setLayout(new BoxLayout(tableauPrincipal,
				BoxLayout.X_AXIS));
		mainWindow.getContentPane().add(tableauPrincipal);

		JList list = new JList();
		list.setMinimumSize(new Dimension(200, 500));
		list.setPreferredSize(new Dimension(200, 500));
		list.setMaximumSize(new Dimension(200, 500));
		tableauPrincipal.add(list);

		plateau = new Picture(jeu, this);
		plateau.setMinimumSize(new Dimension(800, 500));
		plateau.setPreferredSize(new Dimension(800, 500));
		plateau.setMaximumSize(new Dimension(800, 500));
		tableauPrincipal.add(plateau);

		infosPersos = new FichePerso(this);
		infosPersos.setMinimumSize(new Dimension(200, 500));
		infosPersos.setPreferredSize(new Dimension(200, 500));
		infosPersos.setMaximumSize(new Dimension(200, 500));
		tableauPrincipal.add(infosPersos);

		textAreaMessages = new JTextPane();
		JScrollPane scroll = new JScrollPane(textAreaMessages);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setMinimumSize(new Dimension(1200, 200));
		scroll.setPreferredSize(new Dimension(1200, 200));
		scroll.setMaximumSize(new Dimension(1200, 200));
		mainWindow.getContentPane().add(scroll);
		// frmBloblines.getContentPane().add(textAreaMessages);
		textAreaMessages.setText("Welcome to Anima Tactics");
		textAreaMessages.setEditable(false);
		textAreaMessages.setFocusable(false);
	}

	private InstancePartie createGame(int width, int height) {

		Interface interf = new Interface();

		Terrain t = new Terrain(width, height);
		t.genereTerrainAleatoire();

		Joueur j1 = new Joueur("NBS");
		Joueur j2 = new Joueur("Mirth");

		Personnage p1 = new DereckShezard(j1);

		Random r = new Random();
		int rX, rY;

		do {
			rX = r.nextInt(width);
			rY = r.nextInt(height);
		} while (!t.get(new XY(rX, rY)).isFranchissable()
				&& !t.get(new XY(rX, rY)).isOccuped());

		p1.setPositionX(new XY(rX, rY));
		t.get(new XY(rX, rY)).setOccupant(p1);
		t.personnages.add(p1);

		Personnage p2 = new AlessaRaincross(j2);

		do {
			rX = r.nextInt(width);
			rY = r.nextInt(height);
		} while (!t.get(new XY(rX, rY)).isFranchissable()
				&& !t.get(new XY(rX, rY)).isOccuped());

		p2.setPositionX(new XY(rX, rY));
		t.get(new XY(rX, rY)).setOccupant(p2);
		t.personnages.add(p2);

		Personnage p3 = new Bael(j2);

		do {
			rX = r.nextInt(width);
			rY = r.nextInt(height);
		} while (!t.get(new XY(rX, rY)).isFranchissable()
				&& !t.get(new XY(rX, rY)).isOccuped());

		p3.setPositionX(new XY(rX, rY));
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

	public void updateInfosPersos() {
		if (jeu.plateau.get(plateau.getMouseCaseX(), plateau.getMouseCaseY())
				.isOccuped()) {
			Personnage p = jeu.plateau.get(plateau.getMouseCaseX(),
					plateau.getMouseCaseY()).getOccupant();
			infosPersos.chargeInfosPersos(p);
		}
	}

	public void repaintAll() {
		infosPersos.repaint();
		plateau.repaint();
	}

}
