package gui;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextArea;

import domain.Category;
import domain.Tag;
import gui.component.ComponentUtils;
import gui.listener.CenterTagListener;
import service.CategoryService;
import service.FileService;
import service.TagService;
import service.impl.CategoryServiceImpl;
import service.impl.FileServiceImpl;
import service.impl.TagServiceImpl;
import service.thread.MsgPrintThread;
/**
 * 服务层的静态资源和静态方法
 */
public class Service {
	// 预先加载资源
	public static CategoryService categoryService = new CategoryServiceImpl();
	public static TagService tagService = new TagServiceImpl();
	public static FileService fileService = new FileServiceImpl();
	// 全局共享变量
	public static String categroyName;
	public static String tagName;
	public static JTextArea showBoard;
	public static String curPosition;
	public static MsgPrintThread msgPrintThread;
	// 路径
	public static String copyFilePath;
	public static String backFilePath;
	
	/**
	 * 加载分类
	 */
	public static void loadCategories() {
		GUI.returnLeftButton.setText(" ");
		GUI.msgLeftLabel.setText("分类");
		
		GUI.centerPanel.removeAll();
		GUI.centerPanel.repaint();
		List<Category> categories = Service.categoryService.findAll();
		GUI.centerPanel.setLayout(new GridLayout((categories.size() + 1) / 2, 2));
		for (int i = 0; i < categories.size(); i++) {
			String name = categories.get(i).getName();
			JButton cButton = ComponentUtils.getButton(name);
			cButton.addMouseListener(ComponentUtils.centerButtonListener);
			GUI.centerPanel.add(cButton);
		}
		GUI.centerPanel.revalidate();
		Service.curPosition = "category";
	}
	
	/**
	 * 加载标签
	 */
	public static void loadTags() {
		// 获得该分类下的所有标签
		List<Tag> tags = Service.tagService.findByCategoryName(Service.categroyName);
		CenterTagListener listener = new CenterTagListener();
		// 重构中间部件Panel
		GUI.centerPanel.removeAll();
		GUI.centerPanel.repaint();
		GUI.centerPanel.revalidate();
		GUI.centerPanel.setLayout(new GridLayout((tags.size()+1)/2,2));
		GUI.msgLeftLabel.setText("标签");
		GUI.returnLeftButton.setText("<<");
		GUI.msgLeftPanel.repaint();
		for (int i = 0; i < tags.size(); i++) {
			String name = tags.get(i).getName();
			JButton cButton = ComponentUtils.getButton(name);
			cButton.addMouseListener(listener);
			GUI.centerPanel.add(cButton);
		}
		
		Service.curPosition = "tag";
	}
	
	public static void loadShowBoard() {
		// 更新中间Panel
		GUI.centerPanel.removeAll(); // 删除原来的
		Service.showBoard = ComponentUtils.getTextArea(); // 创建新的

		// 重设布局
		GUI.centerPanel.setLayout(new CardLayout());
		GUI.centerPanel.add(Service.showBoard); // 添加进来
		GUI.centerPanel.repaint(); // 重绘图
		GUI.centerPanel.revalidate(); // 重构组件
		
		GUI.returnLeftButton.setText(" ");
		Service.curPosition = "showboard";
	}
}
