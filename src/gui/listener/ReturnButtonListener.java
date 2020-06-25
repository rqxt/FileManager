package gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gui.Service;
import service.thread.AutoCloseThraed;

public class ReturnButtonListener extends MouseAdapter{
	@Override
	public void mousePressed(MouseEvent e) {
		AutoCloseThraed.timeToClose = false;
		// 仅当现在位置在tag界面的时候，有用
		if (Service.curPosition.equals("tag")) {
			Service.loadCategories();
		}
	}
}
