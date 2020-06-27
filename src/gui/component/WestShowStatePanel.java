package gui.component;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Service;
import gui.thread.AutoCloseThraed;

/**
 * 左边显示操作状态
 */
public class WestShowStatePanel extends JPanel {
	public static JLabel msgLeftLabel;
	public static MyButton returnLeftButton;

	public WestShowStatePanel() {
		setLayout(new BorderLayout());

		// 当前程序状态显示
		msgLeftLabel = SwingUtils.newInstance(JLabel.class, "");
		add(msgLeftLabel, BorderLayout.CENTER);

		// 返回上一级
		returnLeftButton = SwingUtils.newInstance(MyButton.class, " ", new ReturnButtonListener());
		add(returnLeftButton, BorderLayout.SOUTH);
	}

	static class ReturnButtonListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			AutoCloseThraed.timeToClose = false;
			// 仅当现在位置在tag界面的时候，有用
			if (Service.curPosition.equals("tag")) {
				CenterMainPanel.loadCategories();
			}
		}
	}
}
