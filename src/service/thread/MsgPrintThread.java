package service.thread;

import gui.GUI;
import gui.Service;

/**
 * 将消息打印到中心看板上
 */
public class MsgPrintThread extends Thread {

	String absolutePath;

	public MsgPrintThread(String _absolutePath) {
		absolutePath = _absolutePath;
	}

	public void run() {
		// 检测路径是否合格

		// 拷贝文件，使用一个新的线程进行文件的拷贝
		GUI.msgLeftLabel.setText("进行中");
		Service.showBoard.append("--------------开始拷贝文件夹----------------\n");
		Service.fileService.copyFile(absolutePath, Service.categroyName, Service.tagName);
		Service.showBoard.append("----------------拷贝完成！！！----------------");
		try {
			// 休息一下
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 备份文件
		Service.showBoard.append("\n------------正在备份文件夹，请稍后------------\n");
		Service.fileService.backupFile(absolutePath, Service.categroyName, Service.tagName);
		Service.showBoard.append("---------------备份完成！！！---------------");
		Service.showBoard.append("\n保存路径："+Service.copyFilePath.substring(1));
		Service.showBoard.append("\n备份路径："+Service.backFilePath.substring(1));
		Service.showBoard.setCaretPosition(Service.showBoard.getDocument().getLength());
		GUI.southReLoad.setVisible(true);
		GUI.msgLeftLabel.setText("完成");
		GUI.centerScrollPane.revalidate();
		GUI.centerScrollPane.repaint();
		GUI.centerPanel.revalidate();
		GUI.centerPanel.repaint();
		Service.showBoard.append("\n");
		Service.showBoard.setCaretPosition(Service.showBoard.getText().length());

		try {
			Thread.sleep(1000);
			Service.showBoard.append("\n********************************************************");
			Service.showBoard.append("\n无操作，10秒后自动结束程序");
			Service.showBoard.setCaretPosition(Service.showBoard.getDocument().getLength());
			for (int i = 0; i <= 10; i++) {
				if (Thread.interrupted()) {
					// 如果打断当前线程，就不删除自动删除了
					return;
				}
				Thread.sleep(1000);
				Service.showBoard.append("\n倒计时    "+(10-i));
				Service.showBoard.setCaretPosition(Service.showBoard.getDocument().getLength());
			}
			System.exit(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}