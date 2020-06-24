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
		// 如果没有输入绝对路径，则不执行任何操作
		if(GUI.pathField.getText().trim().equals("")) {
			return;
		}
		// 获得点击的分类名
		JButton button = (JButton)(e.getSource());
		String categoryName = button.getText();
		// 获得该分类下的所有标签
		tags = Service.tagService.findByCategoryName(categoryName);
		// 重构中间部件Panel
		GUI.centerPanel.removeAll();
		GUI.centerPanel.repaint();
		GUI.centerPanel.revalidate();
		GUI.centerPanel.setLayout(new GridLayout((tags.size()+1)/2,2));
		GUI.msgLeftLabel.setText("标签");
		for (int i = 0; i < tags.size(); i++) {
			String name = tags.get(i).getName();
			JButton cButton = ComponentUtils.getButton(name);
			cButton.addMouseListener(centerTagListener);
			GUI.centerPanel.add(cButton);
		}
		// 更新全局变量
		Service.categroyName = categoryName;
	}

}
