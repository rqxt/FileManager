package gui.listener;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import gui.GUI;
import gui.Service;
import gui.component.ComponentUtils;
import service.thread.MsgPrintThread;

public class CenterTagListener extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		// 得到点击的标签名
		JButton button = (JButton) (e.getSource());
		String tagName = button.getText();
		// 更新全局变量
		Service.tagName = tagName;
		
		// 得到输入文件夹的绝对路径
		String absolutePath = GUI.pathField.getText();

		// 更新中间Panel
		GUI.centerPanel.removeAll();						// 删除原来的
		Service.showBoard = ComponentUtils.getTextArea();	// 创建新的
		
		// 重设布局
		GUI.centerPanel.setLayout(new CardLayout());
		GUI.centerPanel.add(Service.showBoard);				// 添加进来
		GUI.centerPanel.repaint();							// 重绘图
		GUI.centerPanel.revalidate();						// 重构组件

		new MsgPrintThread(absolutePath).start();;
	}
}
