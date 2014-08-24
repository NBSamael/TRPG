package test;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JScrollPane;

import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Fenetre extends JFrame {

	private Picture picture;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre frame = new Fenetre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Fenetre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 557);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		picture = new Picture();
		BufferedImage imageCase = SpriteStore.get().getSprite("sprites/herbe50.jpg");
		BufferedImage targetImage = SpriteStore.get().getSprite("sprites/herbe50.jpg_rg_lg");

		int nbX = 100;
		int nbY = 60;

		BufferedImage imagePlateau = new BufferedImage(imageCase.getWidth()
				* nbX, imageCase.getHeight() * nbY, imageCase.getType());
		Graphics2D graphiqueImagePlateau = imagePlateau.createGraphics();

		for (int x = 0; x < nbX; x++) {
			for (int y = 0; y < nbY; y++) {
				if (x == 3 && y == 3) {

					graphiqueImagePlateau.drawImage(targetImage, null, x
							* imageCase.getWidth(), y * imageCase.getHeight());

				} else {
					
					graphiqueImagePlateau.drawImage(imageCase, null, x
							* imageCase.getWidth(), y * imageCase.getHeight());
				}
			}
		}

		picture.setImage(imagePlateau);

		JScrollPane scrollPane = new JScrollPane(picture,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		GestionSouris gs = new GestionSouris(scrollPane, picture);
		picture.addMouseListener(gs);
		picture.addMouseMotionListener(gs);

		contentPane.add(scrollPane);
	}

	public static BufferedImage addImage(BufferedImage image1,
			BufferedImage image2) {
		Graphics2D g2d = image1.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

		g2d.drawImage(image2, 0, 0, null);

		g2d.dispose();

		return image1;
	}

}
