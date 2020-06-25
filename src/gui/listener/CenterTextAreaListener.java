package gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;

import gui.Service;

/**
 * 中间输出面板监听器，尝试一次继承两个接口 KeyListener、MouseListener
 */
public class CenterTextAreaListener extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent e) {
		int start = Service.showBoard.getSelectionStart();
		int line;
		try {
			line = Service.showBoard.getLineOfOffset(start);
			int startIndex = Service.showBoard.getLineStartOffset(line);
			int endIndex = Service.showBoard.getLineEndOffset(line);
			String pathTemp = Service.showBoard.getText(startIndex, endIndex-startIndex);
			String path = pathTemp.trim().replace('/','\\');
			boolean matches = path.matches("^[A-z]:\\\\([^|><?*\":\\/]*\\\\)*([^|><?*\":\\/]*)?$");
			if (matches) {
				Runtime.getRuntime().exec("cmd /c start " + path);	
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

//		String selectedText = Service.showBoard.getSelectedText();
//		if (selectedText != null) {
//			System.out.println(selectedText);
//			System.out.println(selectedText.matches("\\.*[A-z]:/\\.*"));
//		}
	}

}
