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
 * https://blog.csdn.net/yuanzihui/article/details/43935509
 */
public class MyButton extends JButton {
	private float alpha = 1f; // ��ɫ��͸���ȣ�Ĭ��Ϊ��͸��
	private int isMouseEntered = 1;// ����Ƿ���밴ť

	public MyButton() {
		initStyle();

	}

	public MyButton(String buttonText) {
		super(buttonText);
		initStyle();
		// ���������
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// ��������ʱ,������״̬��ΪTRUE�����ػ水ť
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
	 * ��ʼ����ť��ʽ
	 */
	private void initStyle() {
		// ��ʼ��͸����ť
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

		// ���ƽ����ɫ
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

		// ʹƽ��
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// ��һ��Բ������
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, myButton.getWidth() - 1, myButton.getHeight() - 1, 10, 10);
		Shape clip = g2.getClip();
		g2.clip(r2d);
		g2.setClip(clip);

		g2.setColor(Color.decode("#afafaf"));
		g2.drawRoundRect(0, 0, myButton.getWidth() - 2, myButton.getHeight() - 1, 10, 10);

		// ���䱳��
		g2.setPaint(new GradientPaint(2, 2, c1, 1, myButton.getHeight() / 3, c2));
		g2.fillRoundRect(2, 2, myButton.getWidth() - 5, myButton.getHeight() / 3, 10, 10);
		// �������
		g2.setPaint(new GradientPaint(1, myButton.getHeight() / 3, c3, 1, myButton.getHeight(), c4));
		g2.fillRoundRect(2, myButton.getHeight() / 3, myButton.getWidth() - 5, myButton.getHeight() / 3 * 2 - 1, 10, 10);

//      g2.dispose();

	}

	private static void drawButtonPressBackground(Graphics2D g2, MyButton myButton, Color c1, Color c2, Color c3,
			Color c4) {

		// ʹƽ��
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// ��һ��Բ������
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, myButton.getWidth() - 1, myButton.getHeight() - 1, 10, 10);
		Shape clip = g2.getClip();
		g2.clip(r2d);
		g2.setClip(clip);

		// ��߿�
		g2.drawRoundRect(0, 0, myButton.getWidth() - 2, myButton.getHeight() - 1, 10, 10);

		// ���䱳��
		g2.setPaint(new GradientPaint(1, myButton.getHeight() / 7, c1, 1, myButton.getHeight() / 3, c2));
		g2.fillRect(2, 2, myButton.getWidth() - 5, myButton.getHeight() / 3);
		// �������
		g2.setPaint(new GradientPaint(1, myButton.getHeight() / 3, c3, 1, myButton.getHeight(), c4));
		g2.fillRect(2, myButton.getHeight() / 3, myButton.getWidth() - 5, myButton.getHeight() / 3 * 2 - 1);

		// �ڱ߿�
		g2.setColor(Color.decode("#afafaf"));
		g2.drawRoundRect(1, 1, myButton.getWidth() - 3, myButton.getHeight() - 2, 9, 9);
		g2.drawRoundRect(2, 2, myButton.getWidth() - 4, myButton.getHeight() - 3, 8, 8);
		g2.setColor(Color.decode("#cfcfcf"));
		g2.drawRoundRect(2, 3, myButton.getWidth() - 4, myButton.getHeight() - 4, 8, 7);

	}
}
