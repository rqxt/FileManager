package gui.listener;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;

import com.sun.org.apache.bcel.internal.classfile.Field;

import gui.GUI;
import gui.Service;
import gui.component.ComponentUtils;
import service.thread.AutoCloseThraed;
import service.thread.MsgPrintThread;
import service.thread.TopMsgChangeThread;

public class CenterTagListener extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		AutoCloseThraed.timeToClose = false;

		// 得到点击的标签名
		JButton button = (JButton) (e.getSource());
		String tagName = button.getText();
		// 更新全局变量
		Service.tagName = tagName;

		// 得到输入文件夹的绝对路径
		String absolutePath = GUI.pathField.getText().trim();
		File f = new File(absolutePath);
		
		if (!f.exists() || f.isFile()) {
			if (absolutePath.equals("") || absolutePath.equals("将文件夹拖入窗口中"))
				new TopMsgChangeThread("输入为空！", absolutePath).start();
			else if (!f.exists())
				new TopMsgChangeThread("文件夹不存在！", absolutePath).start();
			else if (f.isFile())
				new TopMsgChangeThread("输入的是文件名！", absolutePath).start();
			AutoCloseThraed.timeToClose = false;
			GUI.centerPanel.setVisible(false);
			return;
		}

		Service.loadShowBoard();

		Service.msgPrintThread = new MsgPrintThread(absolutePath);
		Service.msgPrintThread.start();
	}
}
