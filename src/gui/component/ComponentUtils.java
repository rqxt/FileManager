package gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Scrollbar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import gui.Service;
import gui.listener.CenterCategoryListener;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

/**
 * 对控件的样式进行控制
 */
public class ComponentUtils {
	private static Font font = new Font("MicroSoft Yahei", Font.BOLD, 40);
	public static CenterCategoryListener centerButtonListener = new CenterCategoryListener();

	public static JScrollPane getScrollPane(JComponent component) {
		JScrollPane jScrollPane = new JScrollPane(component);
		// 设置滑动速度
		int speed = 40;
		jScrollPane.getVerticalScrollBar().setUnitIncrement(speed);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return jScrollPane;
	}

	public static JLabel getLabel(String text) {
		JLabel jLabel = new JLabel(text);
		jLabel.setFont(font);
		return jLabel;
	}

	public static JButton getButton(String text) {
		JButton jButton = new MyButton(text);
		jButton.setFont(font);
		return jButton;
	}

	public static JPanel getPanel(LayoutManager layout) {
		JPanel jPanel = new JPanel(layout);
		jPanel.setFont(font);
		return jPanel;
	}

	public static JTextField getTextField() {
		JTextField jTextField = new JTextField();
		jTextField.setFont(font);
		return jTextField;
	}

	public static JTextArea getTextArea() {
		// TODO Auto-generated method stub
		JTextArea jTextArea = new JTextArea();
		jTextArea.setFont(new Font("MicroSoft Yahei", Font.PLAIN, 24));
		jTextArea.setEditable(false); // 不可编辑
		jTextArea.setLineWrap(true); // 自动换行
		jTextArea.setTabSize(2); // tab键所占用的空格2
		jTextArea.setAutoscrolls(true); //自动滑动
		return jTextArea;
	}
}
