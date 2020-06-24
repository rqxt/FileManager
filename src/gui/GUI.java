package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import domain.Category;
import gui.component.ComponentUtils;
import javafx.geometry.Orientation;
import service.CategoryService;
import service.FileService;
import service.TagService;
import service.impl.CategoryServiceImpl;
import service.impl.FileServiceImpl;
import service.impl.TagServiceImpl;

public class GUI extends JFrame {
	// ��GUI�Ŀؼ�����Ȩ�޷ſ�
	public static JPanel centerPanel;
	public static JScrollPane centerScrollPane;
	public static JLabel msgLeftLabel;
	public static JPanel pathTopPanel;
	public static JTextField pathField;
	
	public GUI() {
		// �ϱ��ļ��еľ���·��
		pathTopPanel = ComponentUtils.getPanel(new BorderLayout());
		pathTopPanel.setBackground(Color.LIGHT_GRAY);
		// ��ʾ��
		pathTopPanel.add(ComponentUtils.getLabel("�ļ��о���·����"), BorderLayout.WEST);
		// �����
		pathField = ComponentUtils.getTextField();
		pathTopPanel.add(pathField, BorderLayout.CENTER);
		
		// ���״̬��Ŀ
		msgLeftLabel = ComponentUtils.getLabel("����");
		
		// �м�����
		centerPanel = new JPanel();
		centerScrollPane = ComponentUtils.getScrollPane(centerPanel);
		
		// ���ط���
		List<Category> categories = Service.categoryService.findAll();
		centerPanel.setLayout(new GridLayout((categories.size()+1)/2,2));
		for (int i = 0; i < categories.size(); i++) {
			String name = categories.get(i).getName();
			JButton cButton = ComponentUtils.getButton(name);
			cButton.addMouseListener(ComponentUtils.centerButtonListener);
			centerPanel.add(cButton);
		}
		
		// ���ò���
        setLayout(new BorderLayout());
        add(pathTopPanel, BorderLayout.NORTH);
        add(msgLeftLabel, BorderLayout.WEST);
        add(centerScrollPane, BorderLayout.CENTER);
		
		// ����
		setTitle("����洢����");
		// �ߴ��С
		setSize(800, 600);
		// ˮƽ��ֱ����
		setLocationRelativeTo(null);
		// �رմ��ڵ�Ĭ�Ϸ�ʽ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��ʾ����
		setVisible(true);
	}

	public static void main(String[] args) {
		new GUI();
	}
}
