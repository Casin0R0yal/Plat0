package ui.config;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.PlayerModel;
import ui.JFrameGame;
import ui.WaitFrame;
import util.FrameUtil;
import control.Control;
import control.GameRunning;

/**
 * 
 * ��ȡ�û�����
 * 
 * */
public class FrameConfig extends JFrame {

	private JButton jbnStart = new JButton("Start");
	//private JButton jbnradom = new JButton("���");
	private JButton jbnCancel = new JButton("Réinitialiser les paramètres");

	private JButton jbnPlayer01 = new JButton("Valider");
	private JLabel jbnPlayerNameLabel01 = new JLabel("Nom:");
	private JTextField jbnPlayerNameField01 = new JTextField(12);
	private JButton jbnPlayerName01 = new JButton("Valider");

	private JButton jbnPlayer02 = new JButton("Valider");
	private JLabel jbnPlayerNameLabel02 = new JLabel("Nom:");
	private JTextField jbnPlayerNameField02 = new JTextField(12);
	private JButton jbnPlayerName02 = new JButton("Valider");

	/**
	 * ѡ�
	 * */
	private JTabbedPane tabs;

	/**
	 * ��ѡͼƬ
	 * */
	private ImageIcon[] img = Photo.PLAYER_CHOOSE;
	/**
	 * ����1
	 **/
	private JLabel jlPlayer01Choose = null;
	private final JLabel jlPlayer01Selected = new JLabel(
			Photo.PLAYER_01_SELECTED);
	private JButton leftButton01;
	private JButton rightButton01;

	/**
	 * ����2
	 **/
	private JLabel jlPlayer02Choose = null;
	private final JLabel jlPlayer02Selected = new JLabel(
			Photo.PLAYER_02_SELECTED);
	private JButton leftButton02;
	private JButton rightButton02;
	/**
	 * 1P 2P��ѡ����
	 */
	private int[] chooses = { 0, 0 };
	/**
	 * 1P 2P��ѡ����
	 */
	private int[] selected = { -1, -2 };
	/**
	 * 1P 2P��������
	 */
	private String[] selectedName = { "", "" };

	/**
	 * 
	 * �����
	 * 
	 * */
	private JFrameGame jFrameGame;

	public FrameConfig(WaitFrame wFrame,JFrameGame jFrameGame) {
		wFrame.setVisible(false);
		this.jFrameGame = jFrameGame;
		setTitle("Settings");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 
		this.setLayout(new BorderLayout());
		// 
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		// 
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.setResizable(false);
		this.setSize(380, 370);
		// 
		FrameUtil.setFrameCenter(this);
		setVisible(true);
	}

	/**
	 * 
	 */
	private JTabbedPane createMainPanel() {
		this.tabs = new JTabbedPane();
		this.tabs.setOpaque(false);
		this.tabs.add("Player", this.createPlayerSelectPanel());
		this.tabs.setToolTipTextAt(0, "Compléter le réglage des caractères");
		this.tabs.add("Stage", this.createMapSelectPanel());
		this.tabs.setToolTipTextAt(1, "Vous pouvez définir la scène du jeu.");
		this.tabs.add("Settings", this.createGameSelectPanel());
		this.tabs.setToolTipTextAt(2, "Vous pouvez définir les conditions de victoire du jeu, etc...");
		return tabs;
	}

	/**
	 * 
	 * ��Ϸʤ����������
	 * 
	 */
	private Component createGameSelectPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.setBackground(new Color(235,236,237));

		// --------------------------------
		final JPanel dayPanel = new JPanel();
		dayPanel.setBorder(BorderFactory.createTitledBorder(""));
		JLabel day = new JLabel("Nombre de jours de match");
		final String[] days = { "Illimité.", "20", "40", "80", "120", "240", "480" };
		final Choice daysChoice = new Choice();

		for (String a : days) {
			daysChoice.add(a);
		}
		daysChoice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String str = days[daysChoice.getSelectedIndex()];
				if (str.equals("Illimité.")) {
					GameRunning.GAME_DAY = -1;
				} else {
					GameRunning.GAME_DAY = Integer.parseInt(str);
				}
			}
		});
		dayPanel.add(day);
		dayPanel.add(daysChoice);

		// --------------------------------
		JPanel moneyPanel = new JPanel();
		moneyPanel.setBorder(BorderFactory.createTitledBorder(""));
		JLabel money = new JLabel("Gagner de l'argent");
		final String[] money_ = { "Illimité.", "10000", "20000", "40000", "80000",
				"200000" };
		final Choice moneyChoice = new Choice();
		for (String a : money_) {
			moneyChoice.add(a);
		}
		moneyChoice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String str = money_[moneyChoice.getSelectedIndex()];
				if (str.equals("Illimité.")) {
					GameRunning.MONEY_MAX = -1;
				} else {
					GameRunning.MONEY_MAX = Integer.parseInt(str);
				}
			}
		});
		moneyPanel.add(money);
		moneyPanel.add(moneyChoice);
		// ---------------------------------
		// --------------------------------
		JPanel cashPanel = new JPanel();
		cashPanel.setBorder(BorderFactory.createTitledBorder(""));
		JLabel cash = new JLabel("L'argent initial du joueur");
		final String[] cash_ = { "1000", "2000", "5000", "7000", "10000",
				"20000" };
		final Choice cashChoice = new Choice();
		for (String a : cash_) {
			cashChoice.add(a);
		}
		cashChoice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String str = cash_[cashChoice.getSelectedIndex()];
					GameRunning.PLAYER_CASH = Integer.parseInt(str);
//					System.out.println(GameRunning.PLAYER_CASH);
				}
		});
		cashPanel.add(cash);
		cashPanel.add(cashChoice);

		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(BorderFactory.createTitledBorder(""));
		JLabel info = new JLabel();
		info.setText("<html>Cela peut changer les conditions de gain du jeu. <strong>(la faillite est un échec)</strong></html>");
		infoPanel.add(info);

		panel.add(dayPanel);
		panel.add(moneyPanel);
		panel.add(cashPanel);
		panel.add(infoPanel);
		return panel;
	}

	/**
	 * 
	 * ��ͼѡ�����
	 * 
	 */
	private JPanel createMapSelectPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout());
		jp.setBackground(new Color(235,236,237));
		JPanel lPane = new JPanel(new BorderLayout());
		String[] maps = { "\"Carte de l'AMOUR\"", "\"Carte de la maison hantée\"", "\"Carte de bonne chance\"","Monopoly" };
		final ImageIcon[] maps1 = {
				new ImageIcon("images/other/1.png"),
				new ImageIcon("images/other/2.png"),
				new ImageIcon("images/other/3.png"),
				new ImageIcon("images/other/4.png") };
		final JList jlst = new JList(maps);
		jlst.setSelectedIndex(0);
		final JLabel mapV = new JLabel(maps1[0]);
		final JButton ok = new JButton("Select");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameRunning.MAP = jlst.getSelectedIndex() + 1;
				ok.setText("Already selected");
			}
		});
		jlst.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mapV.setIcon(maps1[jlst.getSelectedIndex()]);
				ok.setText("Select");
			}
		});
		lPane.add(jlst);
		lPane.add(ok, BorderLayout.SOUTH);
		JPanel rPane = new JPanel();
		rPane.add(mapV);
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				false, lPane, rPane);
		jp.add(jSplitPane);
		return jp;
	}

	/**
	 * 
	 * */
	private JPanel createPlayerSelectPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(235,236,237));
		// 
		addPlayer01Config(12, 0, jp);
		// 
		addPlayer02Config(212, 0, jp);
		// 
		addCancelButton(jp);
		return jp;
	}

	private void addCancelButton(JPanel panel) {
		jbnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					reLoad();
			}

			/**
			 * ���¼��� ����ѡ��ѡ�
			 */
			private void reLoad() {
				leftButton01.setEnabled(true);
				rightButton01.setEnabled(true);
				jbnPlayer01.setEnabled(true);
				jlPlayer01Selected.setVisible(false);
				jlPlayer01Choose.setIcon(img[0]);
				jbnPlayerNameField01.setText("");
				jbnPlayerNameField01.setEditable(true);
				jbnPlayerName01.setEnabled(true);
				selected[0] = 2;
				chooses[0] = 0;

				leftButton02.setEnabled(true);
				rightButton02.setEnabled(true);
				jbnPlayer02.setEnabled(true);
				jlPlayer02Selected.setVisible(false);
				jlPlayer02Choose.setIcon(img[0]);
				jbnPlayerNameField02.setText("");
				jbnPlayerNameField02.setEditable(true);
				jbnPlayerName02.setEnabled(true);
				selected[1] = 3;
				chooses[1] = 0;
				repaint();
			}
		});
		jbnCancel.setBounds(256 + 7, 235, 80, 30);
		panel.add(jbnCancel);
	}

	/**
	 * 
	 */
	private void addPlayer01Config(int x, int y, JPanel jp) {
		// 
		jlPlayer01Choose = new JLabel(img[chooses[0]]);
		jlPlayer01Choose.setBounds(x + 8, y, 128, 128);
		// 
		jlPlayer01Selected.setBounds(x + 8, y, 128, 128);
		jlPlayer01Selected.setVisible(false);
		// 
		leftButton01 = this.createButton(x, 92 + y, Photo.BUTTON_LEFT, 'a');
		// 
		leftButton01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 
				if (chooses[0] <= 0) {
					chooses[0] = img.length;
				}
				jlPlayer01Choose.setIcon(img[--chooses[0]]);
			}
		});

		jp.add(leftButton01);
		// �����Ұ�ť
		rightButton01 = this.createButton(128 + x, 92 + y, Photo.BUTTON_RIGHT,
				'd');
		// ���Ӽ����¼�
		rightButton01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ����ѭ��
				if (chooses[0] >= img.length - 1) {
					chooses[0] = -1;
				}
				jlPlayer01Choose.setIcon(img[++chooses[0]]);
			}
		});
		jp.add(rightButton01);
		// ����ȷ����
		jbnPlayer01.setBounds(12 + x, 128 + y, 120, 30);
		// �����¼�����
		jbnPlayer01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((chooses[0] != selected[1])) {
					// ���ò��ܵ��
					leftButton01.setEnabled(false);
					rightButton01.setEnabled(false);
					jbnPlayer01.setEnabled(false);
					// ����ѡ��ͼƬ
					jlPlayer01Selected.setVisible(true);
					selected[0] = chooses[0];
				}
			}
		});
		jp.add(jbnPlayer01);
		jp.add(jlPlayer01Selected);
		jp.add(jlPlayer01Choose);
		// �������ֿ�
		jbnPlayerNameLabel01.setBounds(x + 12, y + 128 + 36, 50, 30);
		jbnPlayerNameField01.setBounds(x + 12 + 30, y + 128 + 36, 120 - 30, 30);
		jbnPlayerName01.setBounds(x + 12, y + 128 + 36 + 36, 120, 30);
		// ��ť���Ӽ���
		jbnPlayerName01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jbnPlayerNameField01.getText().equals("")) {
					selectedName[0] = jbnPlayerNameField01.getText();
					jbnPlayerNameField01.setEditable(false);
					jbnPlayerName01.setEnabled(false);

				}

			}
		});
		jp.add(jbnPlayerNameLabel01);
		jp.add(jbnPlayerNameField01);
		jp.add(jbnPlayerName01);
	}

	/**
	 * ����2P���
	 */
	private void addPlayer02Config(int x, int y, JPanel jp) {
		// ���� ����ͼ��label
		jlPlayer02Choose = new JLabel(img[chooses[1]]);
		jlPlayer02Choose.setBounds(x + 8, y, 128, 128);
		// ��������ͼ����ѡ��label
		jlPlayer02Selected.setBounds(x + 8, y, 128, 128);
		jlPlayer02Selected.setVisible(false);
		// ������ť
		leftButton02 = this.createButton(x, 92 + y, Photo.BUTTON_LEFT, 'a');
		// ���Ӽ����¼�
		leftButton02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����Ϊѭ��
				if (chooses[1] <= 0) {
					chooses[1] = img.length;
				}
				jlPlayer02Choose.setIcon(img[--chooses[1]]);
			}
		});

		jp.add(leftButton02);
		// �����Ұ�ť
		rightButton02 = this.createButton(128 + x, 92 + y, Photo.BUTTON_RIGHT,
				'd');
		// ���Ӽ����¼�
		rightButton02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ����ѭ��
				if (chooses[1] >= img.length - 1) {
					chooses[1] = -1;
				}
				jlPlayer02Choose.setIcon(img[++chooses[1]]);
			}
		});

		jp.add(rightButton02);
		// ����ȷ����
		jbnPlayer02.setBounds(12 + x, 128 + y, 120, 30);
		// �����¼�����
		jbnPlayer02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selected[0] != chooses[1]) {
					// ���ò��ܵ��
					leftButton02.setEnabled(false);
					rightButton02.setEnabled(false);
					jbnPlayer02.setEnabled(false);
					// ����ѡ��ͼƬ
					jlPlayer02Selected.setVisible(true);
					selected[1] = chooses[1];
				}
			}
		});
		jp.add(jbnPlayer02);
		jp.add(jlPlayer02Selected);
		jp.add(jlPlayer02Choose);
		// �������ֿ�
		jbnPlayerNameLabel02.setBounds(x + 12, y + 128 + 36, 50, 30);
		jbnPlayerNameField02.setBounds(x + 12 + 30, y + 128 + 36, 120 - 30, 30);
		jbnPlayerName02.setBounds(x + 12, y + 128 + 36 + 36, 120, 30);
		// ��ť���Ӽ���
		jbnPlayerName02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jbnPlayerNameField02.getText().equals("")) {
					selectedName[1] = jbnPlayerNameField02.getText();
					jbnPlayerNameField02.setEditable(false);
					jbnPlayerName02.setEnabled(false);

				}

			}
		});
		jp.add(jbnPlayerNameLabel02);
		jp.add(jbnPlayerNameField02);
		jp.add(jbnPlayerName02);
	}

	/**
	 * 
	 * ͼ�갴ť
	 * 
	 * */
	public JButton createButton(int x, int y, ImageIcon[] img, char keyLinstenr) {
		JButton add = new JButton("", img[0]);
		add.setPressedIcon(img[3]);
		add.setRolloverIcon(img[2]);
		add.setMnemonic(keyLinstenr);
		add.setBounds(x, y, img[0].getIconWidth(), img[0].getIconHeight());
		return add;
	}

	/**
	 * 
	 */
	private JPanel createButtonPanel() {
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		// 
		jbnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected[0] < 0 || selected[1] < 0) {
					JOptionPane.showMessageDialog(null, "choose your player !");
				} else if (selectedName[0].equals("")
						|| selectedName[1].equals("")) {
					JOptionPane.showMessageDialog(null, "input your name !");
				} else {
					startGame();
					// int choose = JOptionPane.showConfirmDialog(null, "start ?");
					// if (choose == JOptionPane.OK_OPTION) {
					// 
					// 	startGame();
					// }
				}
			}

			/**
			 * ��ʼ��Ϸ
			 * */
			private void startGame() {
				setVisible(false);
				jFrameGame.setVisible(true);
				Control control = jFrameGame.getPanelGame().getControl();
				// 
				dealPlayers(control);
				// ����������
				control.start();
			}

			/**
			 * ���������������
			 */
			private void dealPlayers(Control control) {
				List<PlayerModel> tempPlayer = control.getPlayers();
				// ��������
				tempPlayer.get(0).setName(selectedName[0]);
				tempPlayer.get(1).setName(selectedName[1]);
				// ����ʹ�ý�ɫ���
				tempPlayer.get(0).setPart(selected[0]);
				tempPlayer.get(1).setPart(selected[1]);
				// ���� ��ɫ������ɫ
				tempPlayer.get(0).setOtherPlayer(tempPlayer.get(1));
				tempPlayer.get(1).setOtherPlayer(tempPlayer.get(0));
			}

		});

		jp.add(jbnStart);
		//jp.add(jbnradom);
		return jp;
	}
}
