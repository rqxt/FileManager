package gui.thread;

import gui.GUI;
import gui.component.CenterMainPanel;
import gui.component.NorthFilePathPanel;

/**
 * 改变顶部输入框的文字，显示消息，等待500毫秒之后复原
 */
public class TopMsgChangeThread extends Thread{
	String absolutePath;
	String msg;

	public TopMsgChangeThread(String _msg, String _absolutePath) {
		absolutePath = _absolutePath;
		msg = _msg;
	}

	public void run() {
		try {
			NorthFilePathPanel.pathField.setText(msg);
			Thread.sleep(500);
			NorthFilePathPanel.pathField.setText(absolutePath);
			CenterMainPanel.centerPanel.setVisible(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
