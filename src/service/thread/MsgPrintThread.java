package service.thread;

import gui.GUI;
// 静态导入，可以的
import static gui.Service.*;
import static util.PrintUtils.*;

/**
 * 将消息打印到中心看板上
 */
public class MsgPrintThread extends Thread {

	String absolutePath;

	public MsgPrintThread(String _absolutePath) {
		absolutePath = _absolutePath;
	}

	public void run() {
		showBoard.setFocusable(false);

		// 拷贝文件，使用一个新的线程进行文件的拷贝
		GUI.msgLeftLabel.setText("工作");

		print("--------------开始拷贝文件夹----------------", showBoard);
		fileService.copyFile(absolutePath, categroyName, tagName);
		print("----------------拷贝完成！！！----------------", showBoard);
		try {
			// 休息一下
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 备份文件
		print("------------正在备份文件夹，请稍后------------", showBoard);
		fileService.backupFile(absolutePath, categroyName, tagName);
		print("---------------备份完成！！！---------------", showBoard);
		print("点击下面两个路径，即可在Windows下访问文件夹！", showBoard);
		print("保存路径：\n" + copyFilePath.substring(1), showBoard);
		print("备份路径：\n" + backFilePath.substring(1), showBoard);
		GUI.southReLoad.setVisible(true);
		GUI.msgLeftLabel.setText("完成");
		GUI.centerScrollPane.revalidate();
		GUI.centerScrollPane.repaint();

		print("********************************************************", showBoard);
		print("无操作，" + AutoCloseThraed.timeout * 2 + "秒后自动结束程序", showBoard);
		print("********************************************************", showBoard);
		curPosition = "complete";

		showBoard.setFocusable(true);

	}
}