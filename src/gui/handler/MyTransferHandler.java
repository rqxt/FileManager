package gui.handler;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import gui.GUI;

public class MyTransferHandler extends TransferHandler {
	public boolean importData(JComponent comp, Transferable t) {
		try {
			Object o = t.getTransferData(DataFlavor.javaFileListFlavor);

			String filepath = o.toString();
			if (filepath.startsWith("[")) {
				filepath = filepath.substring(1);
			}
			if (filepath.endsWith("]")) {
				filepath = filepath.substring(0, filepath.length() - 1);
			}
			GUI.pathField.setText(filepath);
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	@Override
	public boolean canImport(JComponent comp, DataFlavor[] flavors) {
		for (int i = 0; i < flavors.length; i++) {
			if (DataFlavor.javaFileListFlavor.equals(flavors[i])) {
				return true;
			}
		}
		return false;
	}
}
