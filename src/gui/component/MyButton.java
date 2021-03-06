package gui.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JToggleButton;
/**
 * 渐变颜色的自定义按钮
 * https://blog.csdn.net/yuanzihui/article/details/43935509
 */
public class MyButton extends JButton {
	private float alpha = 1f; // 底色的透明度，默认为不透明
	private int isMouseEntered = 1;// 鼠标是否进入按钮

	public MyButton() {
		this(" ");
	}

	public MyButton(String buttonText) {
		super(buttonText);
		initStyle();
		// 添加鼠标监听
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 当鼠标进入时,鼠标进入状态改为TRUE，并重绘按钮
				isMouseEntered = 0;
				repaint();
				super.mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				isMouseEntered = 1;
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				isMouseEntered = 2;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isMouseEntered = 0;
				repaint();
			}

		});

	}

	/**
	 * 初始化按钮样式
	 */
	private void initStyle() {
		// 初始化透明按钮
		setOpaque(false);
		setBorder(null);
//      setBorderPainted(false);
		setFocusable(false);

		setBackground(null);
//      setFocusPainted(false);
		setContentAreaFilled(false);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// 绘制渐变底色
		switch (isMouseEntered) {
		case 0:
			AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g2.setComposite(composite);
			drawButtonBackground(g2, this, Color.decode("#E0F1FC"), Color.decode("#DEEBFE"), Color.decode("#D6E5F5"),
					Color.decode("#FFFFFF"));
			break;
		case 1:

			break;
		case 2:
			AlphaComposite composite1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g2.setComposite(composite1);
//          drawButtonBackground(g2, this, Color.decode("#D6E5F5"), Color.decode("#FFFFFF"), 
//                  Color.decode("#FFFFFF"), Color.decode("#D6E5F5"));
			drawButtonPressBackground(g2, this, Color.decode("#D6E5F5"), Color.decode("#EFF5FE"),
					Color.decode("#EFF5FE"), Color.decode("#D6E5F5"));
		default:
			break;
		}
		super.paintComponent(g);

	}

	private static void drawButtonBackground(Graphics2D g2, MyButton myButton, Color c1, Color c2, Color c3, Color c4) {

		// 使平滑
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// 造一个圆角区域
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, myButton.getWidth() - 1, myButton.getHeight() - 1, 10, 10);
		Shape clip = g2.getClip();
		g2.clip(r2d);
		g2.setClip(clip);

		g2.setColor(Color.decode("#afafaf"));
		g2.drawRoundRect(0, 0, myButton.getWidth() - 2, myButton.getHeight() - 1, 10, 10);

		// 渐变背景
		g2.setPaint(new GradientPaint(2, 2, c1, 1, myButton.getHeight() / 3, c2));
		g2.fillRoundRect(2, 2, myButton.getWidth() - 5, myButton.getHeight() / 3, 10, 10);
		// 渐变二段
		g2.setPaint(new GradientPaint(1, myButton.getHeight() / 3, c3, 1, myButton.getHeight(), c4));
		g2.fillRoundRect(2, myButton.getHeight() / 3, myButton.getWidth() - 5, myButton.getHeight() / 3 * 2 - 1, 10, 10);

//      g2.dispose();

	}

	private static void drawButtonPressBackground(Graphics2D g2, MyButton myButton, Color c1, Color c2, Color c3,
			Color c4) {

		// 使平滑
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// 造一个圆角区域
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, myButton.getWidth() - 1, myButton.getHeight() - 1, 10, 10);
		Shape clip = g2.getClip();
		g2.clip(r2d);
		g2.setClip(clip);

		// 外边框
		g2.drawRoundRect(0, 0, myButton.getWidth() - 2, myButton.getHeight() - 1, 10, 10);

		// 渐变背景
		g2.setPaint(new GradientPaint(1, myButton.getHeight() / 7, c1, 1, myButton.getHeight() / 3, c2));
		g2.fillRect(2, 2, myButton.getWidth() - 5, myButton.getHeight() / 3);
		// 渐变二段
		g2.setPaint(new GradientPaint(1, myButton.getHeight() / 3, c3, 1, myButton.getHeight(), c4));
		g2.fillRect(2, myButton.getHeight() / 3, myButton.getWidth() - 5, myButton.getHeight() / 3 * 2 - 1);

		// 内边框
		g2.setColor(Color.decode("#afafaf"));
		g2.drawRoundRect(1, 1, myButton.getWidth() - 3, myButton.getHeight() - 2, 9, 9);
		g2.drawRoundRect(2, 2, myButton.getWidth() - 4, myButton.getHeight() - 3, 8, 8);
		g2.setColor(Color.decode("#cfcfcf"));
		g2.drawRoundRect(2, 3, myButton.getWidth() - 4, myButton.getHeight() - 4, 8, 7);

	}
}
