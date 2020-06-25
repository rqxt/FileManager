package gui.component;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gui.func.DragFunc;
import gui.handler.MyTransferHandler;
import gui.listener.ReturnButtonListener;

public class GuiUtils {
	private static Font font = new Font("MicroSoft Yahei", Font.BOLD, 24);
	private static MyTransferHandler transferHandler = new MyTransferHandler();

	public static <T> T getJComponent(Class<T> type) {
		return getJComponent(type, null, null, null);
	}

	public static <T> T getJComponent(Class<T> type, String text) {
		return getJComponent(type, text, null, null);
	}

	public static <T> T getJComponent(Class<T> type, LayoutManager layout) {
		return getJComponent(type, null, null, layout);
	}

	public static <T> T getJComponent(Class<T> type, EventListener listener) {
		return getJComponent(type, null, listener, null);
	}

	public static <T> T getJComponent(Class<T> type, String text, EventListener listener) {

		return getJComponent(type, text, listener, null);
	}

	// 通过反射创建组件
	public static <T> T getJComponent(Class<T> type, String text, EventListener listener, LayoutManager layout) {
		JComponent jComponent = null;
		try {
			// 使用泛型建立该类的实例
			jComponent = (JComponent) type.newInstance();
			// 执行方法
			Method[] methods = type.getMethods();
			for (Method method : methods) {
				switch (method.getName()) {
				case "setText":
					method.invoke(jComponent, text);
					break;
				case "setEditable":
					method.invoke(jComponent, false);
					break;
				case "setLayout":
					method.invoke(jComponent, layout);
					break;
//				case "setFocusable":
//					method.invoke(jComponent, false);
//					break;
//				case "setLineWrap":
//					method.invoke(jComponent, true);
//					break;
				}

			}
			jComponent.setFont(font);

			// 设置拖拽handler
//			jComponent.setTransferHandler(transferHandler); // 这个会识别到多个文件
			DragFunc.drag(jComponent);
			// 设置监听器
			if (listener instanceof MouseListener) {
				jComponent.addMouseListener((MouseListener) listener);
			}

			else if (listener instanceof KeyListener)
				jComponent.addKeyListener((KeyListener) listener);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) jComponent;
	}

	public static JScrollPane getScrollPane(JComponent component) {
		JScrollPane jScrollPane = new JScrollPane(component);
		// 设置滑动速度
		int speed = 40;
		jScrollPane.getVerticalScrollBar().setUnitIncrement(speed);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return jScrollPane;
	}

}
