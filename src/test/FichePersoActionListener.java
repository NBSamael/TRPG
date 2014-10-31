package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.Action;

public class FichePersoActionListener implements ActionListener {
	Action action;

	public FichePersoActionListener(Action action) {
		this.action = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		action.getParameters();
	}
}
