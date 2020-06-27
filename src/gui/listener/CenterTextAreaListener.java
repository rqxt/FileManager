package gui.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;

import gui.Service;

/**
 * 中间输出面板监听器，尝试一次继承两个接口 KeyListener、MouseListener
 */
public class CenterTextAreaListener extends MouseAdapter {
	private static String path;
	private static int start;
	@Override
	public void mousePressed(MouseEvent e) {
		start = Service.showBoard.getSelectionStart();
		int line;
		try {
			line = Service.showBoard.getLineOfOffset(start);
			int startIndex = Service.showBoard.getLineStartOffset(line);
			int endIndex = Service.showBoard.getLineEndOffset(line);
			Service.showBoard.setSelectionStart(startIndex);
			Service.showBoard.setSelectionEnd(endIndex);
			String pathTemp = Service.showBoard.getText(startIndex, endIndex-startIndex);
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
//		String selectedText = Service.showBoard.getSelectedText();
//		if (selectedText != null) {
//			System.out.println(selectedText);
//			System.out.println(selectedText.matches("\\.*[A-z]:/\\.*"));
//		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Service.showBoard.setSelectionStart(start);
		Service.showBoard.setSelectionEnd(start);
	}

}
