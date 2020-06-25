package gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gui.GUI;
import gui.Service;

public class SouthReloadButtonListener extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		Service.loadCategories();
		GUI.southReLoad.setVisible(false);
		GUI.pathField.setText("");
		Service.msgPrintThread.interrupt();
	}
}
