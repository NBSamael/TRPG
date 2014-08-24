package test;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import javax.swing.JScrollPane;

public class GestionSouris extends MouseAdapter {
	private JScrollPane comp;
	private Picture picture;
	boolean enfonce;
	int vValue;
	int hValue;
	int oldX;
	int oldY;
	int caseX;
	int caseY;

	public GestionSouris(JScrollPane comp, Picture picture) {
		super();
		this.comp = comp;
		this.picture = picture;
		this.enfonce = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("X : " + caseX + " / Y : " + caseY);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			enfonce = true;
			oldX = e.getX();
			oldY = e.getY();
			vValue = comp.getVerticalScrollBar().getValue();
			hValue = comp.getHorizontalScrollBar().getValue();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (caseX != (e.getX() / 50) || caseY != (e.getY() / 50)) {
			caseX = e.getX() / 50;
			caseY = e.getY() / 50;
			reDessine(picture);
			comp.setViewportView(picture);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (enfonce) {
			comp.getVerticalScrollBar().setValue(new Double(vValue + ((oldY - e.getY()))*0.6).intValue());
			comp.getHorizontalScrollBar()
					.setValue(new Double(hValue + ((oldX - e.getX()))*0.5).intValue());
	
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		enfonce = false;
	}

	public void reDessine(Picture picture) {
		BufferedImage imageCase = SpriteStore.get().getSprite("sprites/herbe50.jpg");
		BufferedImage targetImage = SpriteStore.get().getSprite("sprites/herbe50.jpg_rg_lg");

		int nbX = 100;
		int nbY = 60;

		BufferedImage imagePlateau = new BufferedImage(imageCase.getWidth()
				* nbX, imageCase.getHeight() * nbY, imageCase.getType());
		Graphics2D graphiqueImagePlateau = imagePlateau.createGraphics();

		for (int x = 0; x < nbX; x++) {
			for (int y = 0; y < nbY; y++) {
				if (x == caseX && y == caseY) {

					graphiqueImagePlateau.drawImage(targetImage, null, x
							* imageCase.getWidth(), y * imageCase.getHeight());

				} else {
					
					graphiqueImagePlateau.drawImage(imageCase, null, x
							* imageCase.getWidth(), y * imageCase.getHeight());
				}
			}
		}

		picture.setImage(imagePlateau);
	}

}
