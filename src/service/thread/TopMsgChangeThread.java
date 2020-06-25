package service.thread;

import gui.GUI;

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
			GUI.pathField.setText(msg);
			Thread.sleep(500);
			GUI.pathField.setText(absolutePath);
			GUI.centerPanel.setVisible(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
