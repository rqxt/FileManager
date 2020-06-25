package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DragTest extends JFrame {

	JPanel jp1;

	public DragTest() {

		jp1 = new JPanel();
		jp1.setBackground(Color.yellow);
		getContentPane().add(jp1, BorderLayout.CENTER);
		setSize(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 200);
		setTitle("tuozhuai");
//		drag();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DragTest().setVisible(true);
	}



}
