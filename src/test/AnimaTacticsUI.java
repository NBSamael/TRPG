package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class AnimaTacticsUI {

	public JFrame mainWindow;

	private JTextPane textAreaMessages;

	private JPanel tableauPrincipal;

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
		initialize();
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
	private void initialize() {
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

		JPanel plateau = new JPanel();
		plateau.setBackground(Color.BLACK);
		plateau.setMinimumSize(new Dimension(800, 500));
		plateau.setPreferredSize(new Dimension(800, 500));
		plateau.setMaximumSize(new Dimension(800, 500));
		tableauPrincipal.add(plateau);

		JPanel infosPersos = new JPanel();
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

}
