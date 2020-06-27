package gui.thread;

import gui.GUI;
import gui.Service;
import gui.component.WestShowStatePanel;

/**
 * 自动关闭程序线程，如果程序一段时间内没有人使用，就自动关闭
 */
public class AutoCloseThraed extends Thread {
	public static boolean timeToClose;
	public static int timeout = 60;

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				timeToClose = true;
				// 每60秒检测是否有人操作（将timeToClose变为false）
				int timeoutLocale = timeout;
				Thread.sleep(timeoutLocale*100);
				while (timeToClose && !Service.curPosition.equals("working")) {
					Thread.sleep(1000);
					WestShowStatePanel.returnLeftButton.setText((timeoutLocale--) + "");
					if (timeoutLocale == 0) System.exit(0);
				}
				if (Service.curPosition.equals("tag"))
					WestShowStatePanel.returnLeftButton.setText("<<");
				else 
					WestShowStatePanel.returnLeftButton.setText(" ");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
