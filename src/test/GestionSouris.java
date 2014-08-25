package test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionSouris extends MouseAdapter {
	private Picture picture;
	boolean enfonce;

	private int clickX;
	private int clickY;

	public GestionSouris(Picture picture) {
		super();
		this.picture = picture;
		this.enfonce = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked (" + (e.getX() / 50) + ", "
				+ (e.getY() / 50) + ")");
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
//		System.out.println("Moved (" + ((picture.getViewX() +e.getX()) / 50) + ", "
//				+ ((picture.getViewY() + e.getY()) / 50) + ")");
		picture.setMouseCaseX((picture.getViewX() +e.getX()) / 50);
		picture.setMouseCaseY((picture.getViewY() + e.getY()) / 50);
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
}
