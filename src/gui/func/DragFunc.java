package gui.func;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import gui.GUI;
import gui.Service;

public class DragFunc {
	public static void drag(JComponent jComponent) {
		new DropTarget(jComponent, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter() {
			@Override
			public void drop(DropTargetDropEvent dtde) {
				try {

					if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
						dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
						List<File> list = (List<File>) (dtde.getTransferable()
								.getTransferData(DataFlavor.javaFileListFlavor));

						String temp = "";
						for (File file : list) {
//							temp += file.getAbsolutePath() + ";\n";
//							JOptionPane.showMessageDialog(null, temp);
							GUI.pathField.setText(file.getAbsolutePath());
							dtde.dropComplete(true);
						}
					}
					else {
						dtde.rejectDrop();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
