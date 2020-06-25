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
import gui.handler.MyTransferHandler;
import gui.listener.CenterCategoryListener;
import gui.listener.CenterTagListener;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

/**
 * 对控件的样式进行控制
 */
public class ComponentUtils {
	public static Font font = new Font("MicroSoft Yahei", Font.BOLD, 40);


	public static JButton getButton(String text) {
		JButton jButton = new MyButton(text);
		jButton.setFont(font);
		return jButton;
	}
	
	// jTextArea.setTabSize(2); // tab键所占用的空格2
}
