package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import domain.Category;
import domain.Tag;
import gui.component.ComponentUtils;
import gui.listener.CenterTagListener;
import gui.listener.ReturnButtonListener;
import gui.listener.SouthReloadButtonListener;
import javafx.geometry.Orientation;
import service.CategoryService;
import service.FileService;
import service.TagService;
import service.impl.CategoryServiceImpl;
import service.impl.FileServiceImpl;
import service.impl.TagServiceImpl;

public class GUI extends JFrame {
	// 将GUI的控件访问权限放开
	public static JPanel centerPanel;
	public static JScrollPane centerScrollPane;
	public static JPanel msgLeftPanel;
	public static JLabel msgLeftLabel;
	public static JButton returnLeftButton;
	public static JPanel pathTopPanel;
	public static JTextField pathField;
	public static JButton southReLoad;
	

	public GUI() {
		// 上边文件夹的绝对路径
		pathTopPanel = ComponentUtils.getPanel(new BorderLayout());
		pathTopPanel.setBackground(new Color(222, 125, 44, 53));
		// 提示框
		pathTopPanel.add(ComponentUtils.getLabel("文件夹绝对路径："), BorderLayout.WEST);
		// 输入框
		pathField = ComponentUtils.getTextField();
		pathTopPanel.add(pathField, BorderLayout.CENTER);

		// 左边状态栏目
		msgLeftPanel = ComponentUtils.getPanel(new BorderLayout());
		returnLeftButton = ComponentUtils.getButton(" ");
		returnLeftButton.addMouseListener(new ReturnButtonListener());
		returnLeftButton.setFocusable(false);
		msgLeftPanel.add(returnLeftButton, BorderLayout.SOUTH);
		
		msgLeftLabel = ComponentUtils.getLabel("");
		msgLeftPanel.add(msgLeftLabel, BorderLayout.CENTER);
		
		// 中间活动区域
		centerPanel = new JPanel();
		centerScrollPane = ComponentUtils.getScrollPane(centerPanel);

		// 下边重新再次复制按钮
		southReLoad = new JButton("添加新的文件夹");
		southReLoad.setVisible(false);
		southReLoad.setFocusable(false);
		southReLoad.setBorder(null);
		southReLoad.setFont(ComponentUtils.font);
		southReLoad.addMouseListener(new SouthReloadButtonListener());
		
		// 设置布局
		setLayout(new BorderLayout());
		add(pathTopPanel, BorderLayout.NORTH);
		add(msgLeftPanel, BorderLayout.WEST);
		add(centerScrollPane, BorderLayout.CENTER);
		add(southReLoad, BorderLayout.SOUTH);

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
	}

	public static void main(String[] args) {
		new GUI();
		Service.loadCategories();
	}
}
