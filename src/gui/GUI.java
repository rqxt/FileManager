package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import gui.component.WestShowStatePanel;
import gui.thread.AutoCloseThraed;
import gui.component.BottomButtonPanel;
import gui.component.CenterMainPanel;
import gui.component.NorthFilePathPanel;

public class GUI extends JFrame {
	// 将GUI的控件访问权限放开

	public GUI() {
		// 设置布局
		setLayout(new BorderLayout());

		// 顶部文件路径状态
		add(new NorthFilePathPanel(), BorderLayout.NORTH);
		// 左边显示操作状态
		add(new WestShowStatePanel(), BorderLayout.WEST);
		// 中间主界面
		add(new CenterMainPanel(), BorderLayout.CENTER);
		// 下边重新再次复制按钮
		add(new BottomButtonPanel(), BorderLayout.SOUTH);

		// 标题
		setTitle("代码存储管理");
		// 尺寸大小
		setSize(800, 600);
		// 水平竖直居中
		setLocationRelativeTo(null);
		// 关闭窗口的默认方式
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 显示窗口
		setVisible(true);
		CenterMainPanel.loadCategories();
	}

	public static void main(String[] args) throws Exception {
		new GUI();
		new AutoCloseThraed().start();
	}
}
