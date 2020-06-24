package service.thread;

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
		// 拷贝文件，使用一个新的线程进行文件的拷贝
		Service.showBoard.append("-----------------开始拷贝-------------------\n");
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
		Service.showBoard.append("\n------------正在备份文件，请稍后------------\n");
		Service.fileService.backupFile(absolutePath, Service.categroyName, Service.tagName);
		Service.showBoard.append("---------------备份完成！！！---------------");
	}
}