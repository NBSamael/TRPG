package test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class Picture extends JPanel {
	private Image image;
	private int width, height;

	private BufferedImage imageCase;

	private int nbX = 30;
	private int nbY = 20;

	private int viewX = 0;
	private int viewY = 0;

	public Picture() {
		imageCase = SpriteStore.get().getSprite("sprites/herbe50.jpg");

		BufferedImage imagePlateau = new BufferedImage(imageCase.getWidth()
				* nbX, imageCase.getHeight() * nbY, imageCase.getType());
		Graphics2D g = imagePlateau.createGraphics();

		for (int x = 0; x < nbX; x++) {
			for (int y = 0; y < nbY; y++) {
				g.drawImage(imageCase, null, x * imageCase.getWidth(), y
						* imageCase.getHeight());

				g.drawString(x + "/" + y, x * imageCase.getWidth(), y
						* imageCase.getHeight() + imageCase.getHeight());
			}

		}

		setImage(imagePlateau);

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
		super.paintComponent(g);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), viewX, viewY, viewX
				+ getWidth(), viewY + getHeight(), null);
	}

	public void setImage(final Image img) {
		this.image = img;
		width = img.getWidth(this);
		height = img.getHeight(this);
		revalidate();
		repaint();
	}
}
