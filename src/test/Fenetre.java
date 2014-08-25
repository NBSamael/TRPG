package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

		contentPane.add(picture);
	}

	// public static BufferedImage addImage(BufferedImage image1,
	// BufferedImage image2) {
	// Graphics2D g2d = image1.createGraphics();
	// g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	// RenderingHints.VALUE_ANTIALIAS_ON);
	// g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
	// RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	//
	// g2d.drawImage(image2, 0, 0, null);
	//
	// g2d.dispose();
	//
	// return image1;
	// }

}
