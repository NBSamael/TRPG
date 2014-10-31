package test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import data.XY;

public class PictureMouseListener extends MouseAdapter {
	private Picture picture;
	boolean enfonce;

	private XY actualCoord;

	private int clickX;
	private int clickY;

	private AnimaTacticsUI ui;

	public PictureMouseListener(Picture picture, AnimaTacticsUI ui) {
		super();
		this.picture = picture;
		this.enfonce = false;
		this.ui = ui;
		this.actualCoord = new XY(-1, -1);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked (" + actualCoord.getX() + ", "
				+ actualCoord.getY() + ")");

		picture.jeu.ihm.caseClicked(actualCoord);

		ui.repaintAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			enfonce = true;
			clickX = e.getX();
			clickY = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		enfonce = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// System.out.println("Moved (" + ((picture.getViewX() +e.getX()) / 50)
		// + ", "
		// + ((picture.getViewY() + e.getY()) / 50) + ")");
		picture.setMouseCaseX((picture.getViewX() + e.getX())
				/ SpriteStore.MAP_TILE_SIZE);
		picture.setMouseCaseY((picture.getViewY() + e.getY())
				/ SpriteStore.MAP_TILE_SIZE);
		XY coord = new XY(picture.getMouseCaseX(), picture.getMouseCaseY());

		if (!actualCoord.equals(coord)) {
			caseChanged(actualCoord, coord);
			actualCoord = coord;
		}

		ui.repaintAll();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (enfonce) {
			int dx = clickX - e.getX();
			int dy = clickY - e.getY();
			picture.moveView(dx, dy);
			clickX = e.getX();
			clickY = e.getY();
		}
	}

	public void caseChanged(XY oldCoord, XY newCoord) {
		ui.updateInfosPersos();
	}
}
