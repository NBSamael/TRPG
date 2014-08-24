package test;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;


@SuppressWarnings("serial")
class Picture extends JPanel implements Scrollable {
    private Image image;
    private int width, height;
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
        g.drawImage(image, 0, 0, this);
    }
    public void setImage(final Image img) {
        this.image = img;
        width = img.getWidth(this);
        height = img.getHeight(this);
        revalidate();
        repaint();
    }
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }
    public int getScrollableUnitIncrement(Rectangle visibleRect,
            int orientation, int direction) {
        return 100;
    }
    public int getScrollableBlockIncrement(Rectangle visibleRect,
            int orientation, int direction) {
        return 200;
    }
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
