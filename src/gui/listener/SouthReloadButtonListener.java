package gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gui.GUI;
import gui.Service;
import service.thread.AutoCloseThraed;

public class SouthReloadButtonListener extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		AutoCloseThraed.timeToClose = false;
		
		Service.loadCategories();
		GUI.southReLoad.setVisible(false);
		GUI.pathField.setText("将文件夹拖入窗口中");
	}
}
