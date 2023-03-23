package ui.massage;

import java.awt.Image;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


import ui.JPanelGame;

public class Massage extends JPanel {

	protected Image bg = new ImageIcon("images/massage/massage.png").getImage();

	protected Point origin = new Point(); // ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��

	protected int x, y, w, h;

	protected String titileStr = "En-tête";
	protected JLabel titile  = null;

	protected JPanelGame panel = null;


	
	/**
	 * 
	 * ����һ����Ϣ�Ի���
	 * 
	 */
	protected Massage(String titile,JPanelGame panel) {
		this.titileStr = titile;
		// ��ʼ��λ��
		initBounds();
		setLayout(null);
		// ���ӱ���
		addTitle();
		// �����ı���
		// ���Ӽ���
		addListener();
		// ���ñ���͸��
		setOpaque(false);
		this.panel = panel;
	}

	public void setTitileStr(String titileStr) {
		this.titileStr = titileStr;
		this.titile.setText("<html><font color='white' >"+titileStr+"</font></html>");
	}

	/**
	 * 
	 * ����������
	 * 
	 */
	public void moveToBack() {
		this.panel.getLayeredPane().moveToBack(this);
	}

	/**
	 * 
	 * ����������
	 * 
	 */
	public void moveToFront() {
		this.panel.getLayeredPane().moveToFront(this);
	}
	
	private void initBounds() {
		this.x = (950 - bg.getWidth(null)) /2 ;
		this.y = (650 - bg.getHeight(null)) / 2;
		this.w = bg.getWidth(null);
		this.h =bg.getHeight(null);
		setBounds(x, y, w, h);
	}

	private void addTitle() {
		titile = new JLabel("<html><font color='white' >"+titileStr+"</font></html>");
		titile.setBounds(18, 4, 230, 20);
		add(titile);
	}


	private void addListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // ����
				origin.x = e.getX(); // ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
				origin.y = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) { // �϶�
				x += e.getX() - origin.x;
				y += e.getY() - origin.y;
				if (x < 0) {
					x = 0;
				}
				if (x + w > 950) {
					x = 950 - w;
				}
				if (y < 0) {
					y = 0;
				}
				if (y + h > 650) {
					y = 650 - h;
				}
				setBounds(x, y, w, h);
			}
		});
	}
	/**
	 * 
	 * ����ok��ť
	 * 
	 */
	public void ok() {
		System.out.println("ok");
	}
	
	/**
	 * 
	 * ����cancel��ť
	 * 
	 */
	public void cancel() {
		System.out.println("cancel");
	}
}
