package gui.component;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 下边重新再次复制按钮
 */
public class BottomButtonPanel extends JPanel {
	public static JButton southReLoad;

	public BottomButtonPanel() {
		setLayout(new BorderLayout());

		southReLoad = SwingUtils.newInstance(JButton.class, "添加新的文件夹", new SouthReloadButtonListener());
		southReLoad.setVisible(false);
		southReLoad.setBorder(null);

		add(southReLoad, BorderLayout.CENTER);
	}

	static class SouthReloadButtonListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			// 重新导入分类
			CenterMainPanel.loadCategories();
			// 隐藏最底下的 (添加新的文件夹) 按钮
			BottomButtonPanel.southReLoad.setVisible(false);
			// 重置顶部信息
			NorthFilePathPanel.pathField.setText("将文件夹拖入窗口中");
		}
	}
}
