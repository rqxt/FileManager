package gui.component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import domain.Category;
import domain.Tag;
import gui.Service;
import gui.thread.AutoCloseThraed;
import gui.thread.MsgPrintThread;
import gui.thread.TopMsgChangeThread;
/**
 * 中央主界面
 */
public class CenterMainPanel extends JPanel{
	public static JPanel centerPanel;
	public static JScrollPane centerScrollPane;
	public static CenterCategoryListener centerCategoryListener = new CenterCategoryListener();
	public static CenterTextAreaListener centerTextAreaListener = new CenterTextAreaListener();
	public static CenterTagListener centerTagListener = new CenterTagListener();
	public static JTextArea showBoard;
	
	public CenterMainPanel() {
		// 中间活动区域
		centerPanel = SwingUtils.newInstance(JPanel.class);
		centerScrollPane = SwingUtils.newInstance(JScrollPane.class, centerPanel);
		setLayout(new BorderLayout());
		add(centerScrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * 加载分类
	 */
	public static void loadCategories() {
		WestShowStatePanel.returnLeftButton.setText(" ");
		WestShowStatePanel.msgLeftLabel.setText("分类");
		reloadPanel();
		List<Category> categories = Service.categoryService.findAll();
		centerPanel.setLayout(new GridLayout((categories.size() + 1) / 2, 2));
		for (int i = 0; i < categories.size(); i++) {
			String name = categories.get(i).getName();
			JButton cButton = SwingUtils.newInstance(MyButton.class, name);
			cButton.addMouseListener(centerCategoryListener);
			centerPanel.add(cButton);
		}
		centerPanel.revalidate();
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
		reloadPanel();
		centerPanel.setLayout(new GridLayout((tags.size() + 1) / 2, 2));
		WestShowStatePanel.msgLeftLabel.setText("标签");
		WestShowStatePanel.returnLeftButton.setText("<<");
//		GUI.msgLeftPanel.repaint();
		for (int i = 0; i < tags.size(); i++) {
			String name = tags.get(i).getName();
			JButton cButton = SwingUtils.newInstance(MyButton.class, name, listener);
//			cButton.addMouseListener(listener);
			centerPanel.add(cButton);
		}

		Service.curPosition = "tag";
	}

	public static void loadShowBoard() {
		// 更新中间Panel
		reloadPanel();
		showBoard = SwingUtils.newInstance(JTextArea.class);
		showBoard.addMouseListener(new CenterTextAreaListener());
		showBoard.setFont(new Font("MicroSoft Yahei", 0, 25));
		showBoard.setBackground(new Color(240,218,210));
		// 重设布局
		centerPanel.setLayout(new CardLayout());
		centerPanel.add(showBoard); // 添加进来
		centerPanel.repaint(); // 重绘图
		centerPanel.revalidate(); // 重构组件

		WestShowStatePanel.returnLeftButton.setText(" ");
		Service.curPosition = "working";
	}
	public static void reloadPanel() {
		centerPanel.removeAll();
		centerPanel.repaint();
		centerPanel.revalidate();
	}
	static class CenterCategoryListener extends MouseAdapter{
		public CenterTagListener centerTagListener = new CenterTagListener();
		@Override
		public void mousePressed(MouseEvent e) {
			AutoCloseThraed.timeToClose = false;

			// 获得点击的分类名
			JButton button = (JButton)(e.getSource());
			String categoryName = button.getText();
			// 更新全局变量
			Service.categroyName = categoryName;
			
			CenterMainPanel.loadTags();
		}

	}

	static class CenterTagListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			AutoCloseThraed.timeToClose = false;

			// 得到点击的标签名
			JButton button = (JButton) (e.getSource());
			String tagName = button.getText();
			// 更新全局变量
			Service.tagName = tagName;

			// 得到输入文件夹的绝对路径
			String absolutePath = NorthFilePathPanel.pathField.getText().trim();
			File f = new File(absolutePath);
			
			if (!f.exists() || f.isFile()) {
				if (absolutePath.equals("") || absolutePath.equals("将文件夹拖入窗口中"))
					new TopMsgChangeThread("输入为空！", absolutePath).start();
				else if (!f.exists())
					new TopMsgChangeThread("文件夹不存在！", absolutePath).start();
				else if (f.isFile())
					new TopMsgChangeThread("输入的是文件名！", absolutePath).start();
				AutoCloseThraed.timeToClose = false;
				CenterMainPanel.centerPanel.setVisible(false);
				return;
			}

			CenterMainPanel.loadShowBoard();

			Service.msgPrintThread = new MsgPrintThread(absolutePath);
			Service.msgPrintThread.start();
		}
	}

	static class CenterTextAreaListener extends MouseAdapter {
		private static String path;
		private static int start;
		@Override
		public void mousePressed(MouseEvent e) {
			start = showBoard.getSelectionStart();
			int line;
			try {
				line = showBoard.getLineOfOffset(start);
				int startIndex = showBoard.getLineStartOffset(line);
				int endIndex = showBoard.getLineEndOffset(line);
				showBoard.setSelectionStart(startIndex);
				showBoard.setSelectionEnd(endIndex);
				String pathTemp = showBoard.getText(startIndex, endIndex-startIndex);
				path = pathTemp.trim().replace('/','\\');
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {

			boolean matches = path.matches("^[A-z]:\\\\([^|><?*\":\\/]*\\\\)*([^|><?*\":\\/]*)?$");
			if (matches) {
				try {
					Runtime.getRuntime().exec("cmd /c start " + path);
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
//			String selectedText = showBoard.getSelectedText();
//			if (selectedText != null) {
//				System.out.println(selectedText);
//				System.out.println(selectedText.matches("\\.*[A-z]:/\\.*"));
//			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			showBoard.setSelectionStart(start);
			showBoard.setSelectionEnd(start);
		}

	}
}
