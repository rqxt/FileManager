package gui.listener;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import gui.GUI;
import gui.Service;
import gui.component.ComponentUtils;

public class CenterTagListener extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		// �õ�����ı�ǩ��
		JButton button = (JButton) (e.getSource());
		String tagName = button.getText();
		// ����ȫ�ֱ���
		Service.tagName = tagName;
		
		// �õ������ļ��еľ���·��
		String absolutePath = GUI.pathField.getText();

		// �����м�Panel
		GUI.centerPanel.removeAll();						// ɾ��ԭ����
		Service.showBoard = ComponentUtils.getTextArea();	// �����µ�
		GUI.centerPanel.add(Service.showBoard);				// ��ӽ���
		GUI.centerPanel.repaint();							// �ػ�ͼ
		GUI.centerPanel.revalidate();						// �ع����
		
		// �����ļ�
		Service.fileService.copyFile(absolutePath, Service.categroyName, Service.tagName);
		Service.showBoard.append("--------------������ɣ�����--------------");
		
		// �����ļ�
		Service.showBoard.append("\n------------���ڱ����ļ������Ժ�------------");
		Service.fileService.backupFile(absolutePath, Service.categroyName, Service.tagName);
		Service.showBoard.append("\n--------------������ɣ�����--------------");
	}
}
