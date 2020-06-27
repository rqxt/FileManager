package util;


import javax.swing.JTextArea;

/**
 * 打印工具类
 */
public class PrintUtils {
	/**
	 * 将系统的输出函数简化
	 * @param str
	 */
	public static void print(String str) {
		System.out.println(str);
	}
	/**
	 * 根据传入的对象调用相应的处理方法
	 * 将被打印的字符串放在前面，打印的目标放在后边
	 * @param str 
	 * @param target
	 */
	public static void print(String str, Object target) {
		if (target instanceof JTextArea) {
			print(str, (JTextArea) target);
		}
	}

	/**
	 * JTextArea专属的打印方式，自动换行，自动定位到最下方。
	 * 
	 * @param jTextArea
	 * @param str
	 */
	private static void print(String str, JTextArea jTextArea) {
		// 特性，自动换行，自动定位到最下方
		jTextArea.append(str);
		jTextArea.append("\n");
		jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
	}
}
