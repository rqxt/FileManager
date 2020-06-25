package gui.listener;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;

import domain.Tag;
import gui.GUI;
import gui.Service;
import gui.component.ComponentUtils;
import service.thread.AutoCloseThraed;

public class CenterCategoryListener extends MouseAdapter{
	public CenterTagListener centerTagListener = new CenterTagListener();
	@Override
	public void mousePressed(MouseEvent e) {
		AutoCloseThraed.timeToClose = false;

		// 获得点击的分类名
		JButton button = (JButton)(e.getSource());
		String categoryName = button.getText();
		// 更新全局变量
		Service.categroyName = categoryName;
		
		Service.loadTags();
	}

}
