package test;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import personnages.Personnage;
import actions.Action;

public class FichePerso extends JPanel {
	private JLabel lblNom;
	private JLabel lblValeurAtt;
	private JLabel lblValeurDeg;
	private JLabel lblValeurDef;
	private JLabel lblValeurArm;
	private ArrayList<JButton> btnActionsList;

	private static int ESPACE_BOUTONS = 10;

	public FichePerso() {
		setLayout(null);

		lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNom.setBounds(10, 11, 180, 30);
		add(lblNom);

		JLabel lblAttaque = new JLabel("Attaque :");
		lblAttaque.setBounds(10, 52, 58, 14);
		add(lblAttaque);

		JLabel lblDegats = new JLabel("D\u00E9g\u00E2ts :");
		lblDegats.setBounds(10, 77, 58, 14);
		add(lblDegats);

		JLabel lblDefense = new JLabel("D\u00E9fense :");
		lblDefense.setBounds(10, 102, 58, 14);
		add(lblDefense);

		JLabel lblArmure = new JLabel("Armure :");
		lblArmure.setBounds(10, 127, 58, 14);
		add(lblArmure);

		lblValeurAtt = new JLabel("valeurAtt");
		lblValeurAtt.setBounds(78, 52, 46, 14);
		add(lblValeurAtt);

		lblValeurDeg = new JLabel("valeurDeg");
		lblValeurDeg.setBounds(78, 77, 46, 14);
		add(lblValeurDeg);

		lblValeurDef = new JLabel("valeurDef");
		lblValeurDef.setBounds(78, 102, 46, 14);
		add(lblValeurDef);

		lblValeurArm = new JLabel("valeurArm");
		lblValeurArm.setBounds(78, 127, 46, 14);
		add(lblValeurArm);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 175, 180, 2);
		add(separator);

		btnActionsList = new ArrayList<JButton>();
	}

	public void chargeInfosPersos(Personnage p) {
		lblNom.setText(p.nom);
		lblValeurAtt.setText(String.valueOf(p.getAttaque()));
		lblValeurDeg.setText(String.valueOf(p.getDegats()));
		lblValeurDef.setText(String.valueOf(p.getDefense()));
		lblValeurArm.setText(String.valueOf(p.getArmure()));

		for (JButton b : btnActionsList) {
			remove(b);
		}
		btnActionsList.clear();

		ArrayList<Action> tmp = p.getActionsPossibles();
		for (int i = 0; i < tmp.size(); i++) {
			Action a = tmp.get(i);
			JButton btnTemp = new JButton(a.getNom() + " (" + a.getCoutPA()
					+ ")");
			btnTemp.setToolTipText(a.getDescription());
			btnTemp.setBounds(36, 188 + (i * (30 + FichePerso.ESPACE_BOUTONS)),
					128, 30);
			btnTemp.addActionListener(new FichePersoActionListener(a));
			add(btnTemp);
			btnActionsList.add(btnTemp);
		}
	}
}
