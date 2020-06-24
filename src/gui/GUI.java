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
import gui.component.ComponentUtils;
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
	public static JLabel msgLeftLabel;
	public static JPanel pathTopPanel;
	public static JTextField pathField;
	
	public GUI() {
		// 上边文件夹的绝对路径
		pathTopPanel = ComponentUtils.getPanel(new BorderLayout());
		pathTopPanel.setBackground(Color.LIGHT_GRAY);
		// 提示框
		pathTopPanel.add(ComponentUtils.getLabel("文件夹绝对路径："), BorderLayout.WEST);
		// 输入框
		pathField = ComponentUtils.getTextField();
		pathTopPanel.add(pathField, BorderLayout.CENTER);
		
		// 左边状态栏目
		msgLeftLabel = ComponentUtils.getLabel("分类");
		
		// 中间活动区域
		centerPanel = new JPanel();
		centerScrollPane = ComponentUtils.getScrollPane(centerPanel);
		
		// 加载分类
		List<Category> categories = Service.categoryService.findAll();
		centerPanel.setLayout(new GridLayout((categories.size()+1)/2,2));
		for (int i = 0; i < categories.size(); i++) {
			String name = categories.get(i).getName();
			JButton cButton = ComponentUtils.getButton(name);
			cButton.addMouseListener(ComponentUtils.centerButtonListener);
			centerPanel.add(cButton);
		}
		
		// 设置布局
        setLayout(new BorderLayout());
        add(pathTopPanel, BorderLayout.NORTH);
        add(msgLeftLabel, BorderLayout.WEST);
        add(centerScrollPane, BorderLayout.CENTER);
		
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
	}
}
