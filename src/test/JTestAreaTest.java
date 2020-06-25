package test;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;


public class JTestAreaTest extends JFrame{
	public JTestAreaTest() throws Exception {
		JTextArea jTextArea = new JTextArea();
		jTextArea.setFont(new Font("楷体", 1, 30));
		jTextArea.setAutoscrolls(true);
		jTextArea.setLineWrap(true);
		jTextArea.setEditable(false);
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		
		for (int i = 0; i < 100; i++) {
			jTextArea.append((i+1) + ": " + "jTextAreajTextAreajTextArea\n");
		}
		
		int lineCount = jTextArea.getLineCount();
		System.out.println(lineCount);
		
		int lineEndOffset = jTextArea.getLineStartOffset(lineCount-2);
		System.out.println(lineEndOffset);
		String text = jTextArea.getText(0, lineEndOffset);
		jTextArea.setText(text);
		
		lineCount = jTextArea.getLineCount();
		System.out.println(lineCount);
		
		add(jScrollPane);
		// 标题
		setTitle("测试JTextArea");
		// 尺寸大小
		setSize(800, 600);
		// 水平竖直居中
		setLocationRelativeTo(null);
		// 关闭窗口的默认方式
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 显示窗口
		setVisible(true);
	}
	
	public static void main(String[] args) throws Exception{
		new JTestAreaTest();
	}
}
