package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Set;

import javax.swing.JPanel;

import personnages.Personnage;
import data.InstancePartie;
import data.XY;

@SuppressWarnings("serial")
class Picture extends JPanel {
	public InstancePartie jeu;
	private int width, height;

	private BufferedImage imageCase;

	private int nbX;
	private int nbY;

	private int viewX = 0;
	private int viewY = 0;

	private int MouseCaseX = -1;
	private int MouseCaseY = -1;

	private AnimaTacticsUI ui;

	BufferedImage imagePlateau;

	public Picture(InstancePartie jeu, AnimaTacticsUI ui) {
		this.jeu = jeu;
		this.nbX = jeu.plateau.getTailleX();
		this.nbY = jeu.plateau.getTailleY();
		this.ui = ui;

		imageCase = SpriteStore.getSprite(SpriteStore.TILE_GRASS);
		imagePlateau = new BufferedImage(imageCase.getWidth() * nbX,
				imageCase.getHeight() * nbY, imageCase.getType());
		Graphics2D g = imagePlateau.createGraphics();

		for (int x = 0; x < nbX; x++) {
			for (int y = 0; y < nbY; y++) {
				if (jeu.plateau.get(x, y).getSprite() == SpriteStore.TILE_FOREST) {
					imageCase = SpriteStore.getSprite(SpriteStore.TILE_GRASS);
				} else {
					imageCase = SpriteStore.getSprite(jeu.plateau.get(x, y)
							.getSprite());
				}
				g.drawImage(imageCase, null, x * imageCase.getWidth(), y
						* imageCase.getHeight());
			}
		}

		PictureMouseListener gs = new PictureMouseListener(this, ui);
		addMouseListener(gs);
		addMouseMotionListener(gs);

	}

	public void moveView(int dx, int dy) {
		viewX += dx;
		if (viewX < 0)
			viewX = 0;
		if (viewX > nbX * imageCase.getWidth() - getWidth())
			viewX = nbX * imageCase.getWidth() - getWidth();
		viewY += dy;
		if (viewY < 0)
			viewY = 0;
		if (viewY > nbY * imageCase.getHeight() - getHeight())
			viewY = nbY * imageCase.getHeight() - getHeight();
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension size = super.getPreferredSize();
		size.width = width;
		size.height = height;
		return size;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		BufferedImage image = new BufferedImage(imagePlateau.getWidth(),
				imagePlateau.getHeight(), imagePlateau.getType());
		Graphics2D gTemp = image.createGraphics();
		gTemp.drawImage(imagePlateau, 0, 0, null);

		drawDecors(gTemp);

		drawPersonnages(gTemp);

		drawFiltre(gTemp);

		drawCurseur(gTemp);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), viewX, viewY, viewX
				+ getWidth(), viewY + getHeight(), null);
	}

	public void drawDecors(Graphics g) {
		imageCase = SpriteStore.getSprite(SpriteStore.TILE_FOREST);
		for (int x = 0; x < nbX; x++) {
			for (int y = 0; y < nbY; y++) {
				if (jeu.plateau.get(x, y).getSprite() == SpriteStore.TILE_FOREST) {
					g.drawImage(imageCase, x * imageCase.getWidth(), y
							* imageCase.getHeight(), null);
				}
			}
		}
	}

	public void drawPersonnages(Graphics g) {
		for (Personnage p : jeu.plateau.personnages) {
			XY pos = p.getPosition();
			// System.out.println(p.nom + "(" + pos.getX() + ", " + pos.getY()
			// + ")");
			BufferedImage imagePerso = SpriteStore.getSprite(p.getSprite());
			g.drawImage(imagePerso, (pos.getX()) * imagePerso.getWidth(),
					(pos.getY()) * imagePerso.getHeight(), null);
		}
	}

	public void drawFiltre(Graphics g) {
		Set<XY> zoneDepl = ui.jeu.ihm.getZoneDeplacement();
		Set<XY> path = ui.jeu.ihm.getPathTo(new XY(MouseCaseX, MouseCaseY));

		if (zoneDepl != null) {
			Color couleurFiltre = new Color(0, 0, 255, 64);
			g.setColor(couleurFiltre);

			for (XY c : zoneDepl) {
				g.fillRect(c.getX() * SpriteStore.MAP_TILE_SIZE, c.getY()
						* SpriteStore.MAP_TILE_SIZE, SpriteStore.MAP_TILE_SIZE,
						SpriteStore.MAP_TILE_SIZE);
			}
		}

		if (path != null) {
			Color couleurFiltre = new Color(0, 0, 255, 128);
			g.setColor(couleurFiltre);

			for (XY c : path) {
				g.fillRect(c.getX() * SpriteStore.MAP_TILE_SIZE, c.getY()
						* SpriteStore.MAP_TILE_SIZE, SpriteStore.MAP_TILE_SIZE,
						SpriteStore.MAP_TILE_SIZE);
			}
		}

		// Color couleurFiltre = new Color(255, 0, 0, 64);
		// g.setColor(couleurFiltre);
		// g.fillRect(MouseCaseX * SpriteStore.MAP_TILE_SIZE, MouseCaseY
		// * SpriteStore.MAP_TILE_SIZE, SpriteStore.MAP_TILE_SIZE,
		// SpriteStore.MAP_TILE_SIZE);
	}

	public void drawCurseur(Graphics g) {
		Color couleurCurseur = new Color(255, 0, 0);
		g.setColor(couleurCurseur);
		g.drawRect(MouseCaseX * SpriteStore.MAP_TILE_SIZE, MouseCaseY
				* SpriteStore.MAP_TILE_SIZE, SpriteStore.MAP_TILE_SIZE,
				SpriteStore.MAP_TILE_SIZE);
	}

	public int getMouseCaseX() {
		return MouseCaseX;
	}

	public void setMouseCaseX(int mouseCaseX) {
		MouseCaseX = mouseCaseX;
	}

	public int getMouseCaseY() {
		return MouseCaseY;
	}

	public void setMouseCaseY(int mouseCaseY) {
		MouseCaseY = mouseCaseY;
	}

	public int getViewX() {
		return viewX;
	}

	public int getViewY() {
		return viewY;
	}
}
