package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import domain.Category;
import domain.Tag;
import gui.component.ComponentUtils;
import gui.component.SwingUtils;
import gui.component.MyButton;
import gui.func.DragFunc;
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
import service.thread.AutoCloseThraed;

public class GUI extends JFrame {
	// 将GUI的控件访问权限放开
	public static JPanel centerPanel;
	public static JScrollPane centerScrollPane;
	public static JPanel msgLeftPanel;
	public static JLabel msgLeftLabel;
	public static MyButton returnLeftButton;
	public static JPanel pathTopPanel;
	public static JTextField pathField;
	public static JButton southReLoad;

	public GUI() {
		// 文件夹的绝对路径
		pathTopPanel = SwingUtils.newInstance(JPanel.class, new BorderLayout());
		pathTopPanel.setBackground(new Color(222, 125, 44, 53));
		// 提示框
		pathTopPanel.add(SwingUtils.newInstance(JLabel.class, "文件夹绝对路径："), BorderLayout.WEST);
		// 输入框
		pathField = SwingUtils.newInstance(JTextField.class, "C:\\Users\\llf\\Desktop\\代码中转\\测试包"); // 将文件夹拖入窗口中
		pathTopPanel.add(pathField, BorderLayout.CENTER);
		pathField.setDragEnabled(false);

		// 左边状态栏目
		msgLeftPanel = SwingUtils.newInstance(JPanel.class, new BorderLayout());
		msgLeftLabel = SwingUtils.newInstance(JLabel.class, "");
		msgLeftPanel.add(msgLeftLabel, BorderLayout.CENTER);
		returnLeftButton = SwingUtils.newInstance(MyButton.class, " ", new ReturnButtonListener());
		msgLeftPanel.add(returnLeftButton, BorderLayout.SOUTH);

		// 中间活动区域
		centerPanel = SwingUtils.newInstance(JPanel.class);
		centerScrollPane = SwingUtils.newInstance(JScrollPane.class, centerPanel);

		// 下边重新再次复制按钮
		southReLoad = SwingUtils.newInstance(JButton.class, "添加新的文件夹", new SouthReloadButtonListener());
		southReLoad.setVisible(false);
		southReLoad.setBorder(null);

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
		new AutoCloseThraed().start();
	}

	public static void main(String[] args) throws Exception {
		new GUI();
		Service.loadCategories();
	}
}
