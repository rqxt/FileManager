package gui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 顶部文件路径状态
 */
public class NorthFilePathPanel extends JPanel{
	public static JTextField pathField;
	public NorthFilePathPanel() {
		setLayout(new BorderLayout());
		
		// 文件提示框：请将文件夹拖入窗体
		JLabel fileState = SwingUtils.newInstance(JLabel.class, "请将文件夹拖入窗体： ");
		fileState.setOpaque(true);
		fileState.setBackground(new Color(25,202,173));
		add(fileState, BorderLayout.WEST);
		
		// 输入框
		pathField = SwingUtils.newInstance(JTextField.class, "C:\\Users\\llf\\Desktop\\代码中转\\测试包"); 
		add(pathField, BorderLayout.CENTER);
		
		
	}
}
