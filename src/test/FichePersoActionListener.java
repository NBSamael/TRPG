package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.Action;

public class FichePersoActionListener implements ActionListener {
	Action action;
	AnimaTacticsUI ui;

	public FichePersoActionListener(Action action, AnimaTacticsUI ui) {
		this.action = action;
		this.ui = ui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		action.getParameters();
		ui.repaintAll();
	}
}
