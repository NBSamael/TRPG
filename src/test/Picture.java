package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import data.InstancePartie;

@SuppressWarnings("serial")
class Picture extends JPanel {
	InstancePartie jeu;
	private int width, height;

	private BufferedImage imageCase;

	private int nbX;
	private int nbY;

	private int viewX = 0;
	private int viewY = 0;

	private int MouseCaseX = -1;
	private int MouseCaseY = -1;

	BufferedImage imagePlateau;

	public Picture(InstancePartie jeu) {
		this.jeu = jeu;
		this.nbX = jeu.plateau.getTailleX();
		this.nbY = jeu.plateau.getTailleY();

		imageCase = SpriteStore.getSprite(SpriteStore.TILE_GRASS);
		imagePlateau = new BufferedImage(imageCase.getWidth() * nbX,
				imageCase.getHeight() * nbY, imageCase.getType());
		Graphics2D g = imagePlateau.createGraphics();

		for (int x = 0; x < nbX; x++) {
			for (int y = 0; y < nbY; y++) {
				if (jeu.plateau.get(x + 1, y + 1).getSprite() == SpriteStore.TILE_FOREST) {
					imageCase = SpriteStore.getSprite(SpriteStore.TILE_GRASS);
				} else {
					imageCase = SpriteStore.getSprite(jeu.plateau.get(x + 1,
							y + 1).getSprite());
				}
				g.drawImage(imageCase, null, x * imageCase.getWidth(), y
						* imageCase.getHeight());
			}
		}

		GestionSouris gs = new GestionSouris(this);
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

		drawFiltre(gTemp);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), viewX, viewY, viewX
				+ getWidth(), viewY + getHeight(), null);
	}

	public void drawDecors(Graphics g) {
		imageCase = SpriteStore.getSprite(SpriteStore.TILE_FOREST);
		for (int x = 0; x < nbX; x++) {
			for (int y = 0; y < nbY; y++) {
				if (jeu.plateau.get(x + 1, y + 1).getSprite() == SpriteStore.TILE_FOREST) {
					g.drawImage(imageCase, x * imageCase.getWidth(), y
							* imageCase.getHeight(), null);
				}
			}
		}
	}

	public void drawFiltre(Graphics g) {
		Color couleurFiltre = new Color(255, 0, 0, 64);
		g.setColor(couleurFiltre);
		g.fillRect(MouseCaseX * SpriteStore.MAP_TILE_SIZE, MouseCaseY
				* SpriteStore.MAP_TILE_SIZE, SpriteStore.MAP_TILE_SIZE,
				SpriteStore.MAP_TILE_SIZE);
		// g.drawRect(MouseCaseX * SpriteStore.MAP_TILE_SIZE, MouseCaseY
		// * SpriteStore.MAP_TILE_SIZE, SpriteStore.MAP_TILE_SIZE,
		// SpriteStore.MAP_TILE_SIZE);
	}

	public int getMouseCaseX() {
		return MouseCaseX;
	}

	public void setMouseCaseX(int mouseCaseX) {
		MouseCaseX = mouseCaseX;
		repaint();
	}

	public int getMouseCaseY() {
		return MouseCaseY;
	}

	public void setMouseCaseY(int mouseCaseY) {
		MouseCaseY = mouseCaseY;
		repaint();
	}

	public int getViewX() {
		return viewX;
	}

	public int getViewY() {
		return viewY;
	}
}
