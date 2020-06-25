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
		Service.showBoard.setFocusable(false);
		// 检测路径是否合格

		// 拷贝文件，使用一个新的线程进行文件的拷贝
		GUI.msgLeftLabel.setText("工作");
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
		Service.showBoard.append("\n点击下面两个路径，即可在Windows下访问文件夹！");
		Service.showBoard.append("\n保存路径：\n" + Service.copyFilePath.substring(1));
		Service.showBoard.append("\n备份路径：\n" + Service.backFilePath.substring(1));
		Service.showBoard.setCaretPosition(Service.showBoard.getDocument().getLength());
		
		GUI.southReLoad.setVisible(true);
		GUI.msgLeftLabel.setText("完成");
		GUI.centerScrollPane.revalidate();
		GUI.centerScrollPane.repaint();
		Service.showBoard.append("\n");
		Service.showBoard.setCaretPosition(Service.showBoard.getText().length());

		Service.showBoard.append("\n********************************************************");
		Service.showBoard.append("\n无操作，" + AutoCloseThraed.timeout*2 + "秒后自动结束程序");
		Service.showBoard.append("\n********************************************************");
		Service.showBoard.setCaretPosition(Service.showBoard.getDocument().getLength());
		Service.curPosition = "complete";
		
		Service.showBoard.setFocusable(true);

	}
}