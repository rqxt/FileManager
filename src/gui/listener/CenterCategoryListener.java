package gui.listener;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;

import domain.Tag;
import gui.GUI;
import gui.Service;
import gui.component.ComponentUtils;

public class CenterCategoryListener extends MouseAdapter{
	private static List<Tag> tags;
	public CenterTagListener centerTagListener = new CenterTagListener();
	@Override
	public void mouseClicked(MouseEvent e) {
		// ���û���������·������ִ���κβ���
		if(GUI.pathField.getText().trim().equals("")) {
			return;
		}
		// ��õ���ķ�����
		JButton button = (JButton)(e.getSource());
		String categoryName = button.getText();
		// ��ø÷����µ����б�ǩ
		tags = Service.tagService.findByCategoryName(categoryName);
		// �ع��м䲿��Panel
		GUI.centerPanel.removeAll();
		GUI.centerPanel.repaint();
		GUI.centerPanel.revalidate();
		GUI.centerPanel.setLayout(new GridLayout((tags.size()+1)/2,2));
		GUI.msgLeftLabel.setText("��ǩ");
		for (int i = 0; i < tags.size(); i++) {
			String name = tags.get(i).getName();
			JButton cButton = ComponentUtils.getButton(name);
			cButton.addMouseListener(centerTagListener);
			GUI.centerPanel.add(cButton);
		}
		// ����ȫ�ֱ���
		Service.categroyName = categoryName;
	}

}
