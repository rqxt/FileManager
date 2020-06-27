package gui.component;

import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.EventListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;

import gui.thread.AutoCloseThraed;

/**
 * 创建统一样式控件的Swping工具类
 */
public class SwingUtils {
	/**
	 * 公共监听器，鼠标一旦点击说明用户活跃
	 */
	private static CommonListenner commonListenner = new CommonListenner();
	/**
	 * 默认字体，所有使用本工具类创建出来的控件，都是此字体。
	 */
	private static Font font = new Font("MicroSoft Yahei", Font.BOLD, 40);

	/**
	 * 拖拽handler，这个会识别到多个文件。目前不适用本程序
	 */
	@Deprecated
	private static CommonTransferHandler transferHandler = new CommonTransferHandler();
//	jComponent.setTransferHandler(transferHandler);

	/**
	 * 无参，构造对象
	 */
	public static <T> T newInstance(Class<T> type) {
		try {
			T newInstance = type.newInstance();
			return newInstance(newInstance, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 指定控件的文本内容，构造对象
	 */
	public static <T> T newInstance(Class<T> type, String text) {
		try {
			Constructor<T> constructor = type.getConstructor(String.class);
			T newInstance = constructor.newInstance(text);
			return newInstance(newInstance, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 指定控件的布局，构造对象
	 */
	public static <T> T newInstance(Class<T> type, LayoutManager layout) {
		try {
			Constructor<T> constructor = type.getConstructor(LayoutManager.class);
			T newInstance = constructor.newInstance(layout);
			return newInstance(newInstance, null, layout);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 指定对象的监听器，构造对象
	 */
	public static <T> T newInstance(Class<T> type, EventListener listener) {
		try {
			T newInstance = type.newInstance();
			return newInstance(newInstance, listener, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 指定对象的文本和监听器，构造对象
	 * 
	 */
	public static <T> T newInstance(Class<T> type, String text, EventListener listener) {
		try {
			Constructor<T> constructor = type.getConstructor(String.class);
			T newInstance = constructor.newInstance(text);
			return newInstance(newInstance, listener, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T newInstance(Class<T> type, Component component) {
		try {
			Constructor<T> constructor = type.getConstructor(Component.class);
			T newInstance = constructor.newInstance(component);
			return newInstance(newInstance, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 给定文本、监听器、布局、对象参数，构造对象。
	 */
	public static <T> T newInstance(T newInstance, EventListener listener, LayoutManager layout) {
		JComponent jComponent = (JComponent) newInstance;
		try {
			// 执行公共方法
			Method[] methods = newInstance.getClass().getMethods();
			for (Method method : methods) {
				switch (method.getName()) {
				case "setEditable":
					method.invoke(jComponent, false);
					break;
				case "setFont":
					method.invoke(jComponent, font);
					break;
				case "addMouseListener":
					method.invoke(jComponent, commonListenner);
					break;
				}
			}
			// 设置拖拽处理器
			DragFunc.drag(jComponent);

			// 设置监听器
			if (listener instanceof MouseListener) {
				jComponent.addMouseListener((MouseListener) listener);
			} else if (listener instanceof KeyListener)
				jComponent.addKeyListener((KeyListener) listener);

			// 执行特定控件的特定方法
			if (jComponent instanceof JScrollPane) {
				((JScrollPane) jComponent).getVerticalScrollBar().setUnitIncrement(50);
				((JScrollPane) jComponent).setHorizontalScrollBarPolicy(31);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回泛型对象
		return (T) jComponent;
	}

	static class CommonListenner extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			AutoCloseThraed.timeToClose = false;
		}

	}
	
	/**
	 * 拖动函数，添加多个文件中的最后一个文件，使用方便
	 */
	static class DragFunc {
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
//								temp += file.getAbsolutePath() + ";\n";
//								JOptionPane.showMessageDialog(null, temp);
								NorthFilePathPanel.pathField.setText(file.getAbsolutePath());
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
	
	/**
	 * 拖动函数，可以添加多个文件，但是不方便使用
	 */
	@Deprecated
	static class CommonTransferHandler extends TransferHandler {
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
				NorthFilePathPanel.pathField.setText(filepath);
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
}
