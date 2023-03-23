package control;

import java.applet.AudioClip;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

import model.BackgroundModel;
import model.BuildingsModel;
import model.DiceModel;
import model.EffectModel;
import model.EventsModel;
import model.LandModel;
import model.PlayerModel;
import model.Port;
import model.TextTipModel;
import model.buildings.Building;
import model.buildings.Hospital;
import model.buildings.News;
import model.buildings.Origin;
import model.buildings.Park;
import model.buildings.Point;
import model.buildings.Prison;
import model.buildings.Shop_;
import model.card.Card;
import model.card.TortoiseCard;
import music.Music;
import ui.JPanelGame;
import util.FileUtil;
import util.MyThread;
import context.GameState;

/**
 * 
 * Maître de jeu
 * 
 * 
 * @author Administrator
 * 
 */
public class Control {
	/**
	 * 
	 * Valeur du tick du jeu
	 * 
	 */
	public static long tick;
	/**
	 * 
	 * Fréquence de rafraîchissement de l'écran par seconde
	 * 
	 */
	public static int rate = 30;
	/**
	 * 
	 * Panneau principal du jeu
	 * 
	 */
	private JPanelGame panel;
	/**
	 * 
	 * Objet de jeu
	 * 
	 */
	private GameRunning run = null;

	private List<Port> models = new ArrayList<Port>();
	private List<PlayerModel> players = null;
	private BuildingsModel building = null;
	private BackgroundModel background = null;
	private LandModel land = null;
	private TextTipModel textTip = null;
	private DiceModel dice = null;
	private EventsModel events = null;
	private EffectModel effect = null;

	private Music music = null;
	
	/**
	 * 
	 * Minuterie de jeu
	 * 
	 */
	private Timer gameTimer = null;

	public Control() {
		// Créer un état de jeu
		this.run = new GameRunning(this, players);
		// Initialiser l'objet de jeu
		this.initClass();
		// Ajouter un modèle de joueur à l'état du jeu
		this.run.setPlayers(players);
	}

	public void setPanel(JPanelGame panel) {
		this.panel = panel;
	}

	/**
	 * 
	 * Initialiser l'objet de jeu
	 * 
	 */
	private void initClass() {
		// Créer un nouveau modèle d'événement
		this.events = new EventsModel();
		this.models.add(events);
		// Créer un nouveau modèle d'effet de scène
		this.effect = new EffectModel();
		this.models.add(effect);
		// Créer un nouveau modèle d'arrière-plan
		this.background = new BackgroundModel();
		this.models.add(background);
		// Créer un nouveau modèle de terrain
		this.land = new LandModel();
		this.models.add(land);
		// Créer un nouveau modèle d'affichage de texte
		this.textTip = new TextTipModel();
		this.models.add(textTip);
		// Créer un nouveau modèle de bâtiment
		this.building = new BuildingsModel(land);
		this.models.add(building);
		// Créer un nouveau réseau de joueurs
		this.players = new ArrayList<PlayerModel>();
		this.players.add(new PlayerModel(1, this));
		this.players.add(new PlayerModel(2, this));
		this.models.add(players.get(0));
		this.models.add(players.get(1));
		// Créer un nouveau modèle de dés
		this.dice = new DiceModel(run);
		this.models.add(dice);
		
		// Créer un joueur
		this.music = new Music();
	}

	/**
	 * 
	 * Minuterie de jeu
	 * 
	 */
	private void createGameTimer() {
		this.gameTimer = new Timer();
		this.gameTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				tick++;
				// Mettre à jour chaque objet
				for (Port temp : models) {
					temp.updata(tick);
				}
				// Mise à jour de l'interface utilisateur
				panel.repaint();
			}
		}, 0, (1000 / rate));
	}

	/**
	 * 
	 * Démarrez le contrôleur
	 * 
	 */
	public void start() {
		// Créer une minuterie
		this.createGameTimer();
		// Actualiser les données initiales de l'objet
		for (Port temp : this.models) {
			temp.startGameInit();
		}
		// L'environnement de jeu commence.
		this.run.startGameInit();
		// Initialisation du panneau
		this.panel.startGamePanelInit();
		// Musique de fond du jeu
		this.startMusic();
		// Le jeu commence à produire un effet de carte.
		this.effect.showImg("start");
	}

	
	/**
	 * 
	 * Musique de fond du jeu
	 * 
	 */
	private void startMusic() {
		music.start();
	}

	public List<PlayerModel> getPlayers() {
		return players;
	}

	public BuildingsModel getBuilding() {
		return building;
	}

	public BackgroundModel getBackground() {
		return background;
	}

	public LandModel getLand() {
		return land;
	}

	public EffectModel getEffect() {
		return effect;
	}

	public TextTipModel getTextTip() {
		return textTip;
	}

	public GameRunning getRunning() {
		return run;
	}

	public DiceModel getDice() {
		return dice;
	}

	public EventsModel getEvents() {
		return events;
	}

	public JPanelGame getPanel() {
		return panel;
	}

	/**
	 * 
	 * 
	 * Appuyez sur les dés
	 * 
	 * 
	 */
	public void pressButton() {
		PlayerModel player = this.run.getNowPlayer();
		if (player.getInHospital() > 0 || player.getInPrison() > 0) {
			this.run.nextState();
			if (player.getInHospital() > 0) {
				this.textTip.showTextTip(player, player.getName() + "À l'hôpital.", 3);
			} else if (player.getInPrison() > 0) {
				this.textTip.showTextTip(player, player.getName() + "En prison.ß", 3);
			}
			this.run.nextState();
		} else {
			// Définissez le temps de rotation de l'objet des dés.
			this.dice.setStartTick(Control.tick);
			// Définissez le temps de rotation de fin de l'objet de dés.
			this.dice.setNextTick(this.dice.getStartTick()
					+ this.dice.getLastTime());
			// Passez le nombre de points d'objet en cours d'exécution dans l'objet de dés
			this.dice.setPoint(this.run.getPoint());
			// Convertir l'état en "état en mouvement"
			this.run.nextState();
			// Une fois les dés tournés, le joueur se déplace.
			this.run.getNowPlayer().setStartTick(this.dice.getNextTick() + 10);
			this.run.getNowPlayer().setNextTick(
					this.run.getNowPlayer().getStartTick()
							+ this.run.getNowPlayer().getLastTime()
							* (this.run.getPoint() + 1));
		}
	}

	/**
	 * 
	 * 
	 * Les joueurs se déplacent.
	 * 
	 * 
	 */
	public void movePlayer() {
		// Mouvement populaire de la population
		for (int i = 0; i < (60 / this.run.getNowPlayer().getLastTime()); i++) {
			// Joueurs mobiles
			if (GameRunning.MAP == 1){
				this.move01();
			} else if (GameRunning.MAP == 2){
				this.move02();
			} else if (GameRunning.MAP == 3) {
				this.move03();
			}
		}
	}

	/**
	 * 
	 * Les joueurs passent devant le bâtiment en chemin.
	 * 
	 */
	public void prassBuilding() {
		// Joueurs actuels
		PlayerModel player = this.run.getNowPlayer();
		// La maison à cet endroit
		Building building = this.building.getBuilding(player.getY() / 60,
				player.getX() / 60);
		if (building != null && player.getX() % 60 == 0
				&& player.getY() % 60 == 0) {
			// Incident après la maison
			int event = building.passEvent();
			// Entrez et traitez l'incident de la maison.
			disposePassEvent(building, event, player);
		}
	}

	/**
	 * 
	 * Après l'incident du logement
	 * 
	 */
	private void disposePassEvent(Building b, int event, PlayerModel player) {
		switch (event) {
		case GameState.ORIGIN_PASS_EVENT:
			// Passez l'origine à mi-chemin.
			passOrigin(b, player);
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * Passez l'origine à mi-chemin.
	 * 
	 */
	private void passOrigin(Building b, PlayerModel player) {
		this.textTip.showTextTip(player, player.getName() + "Passez l'origine, récompense"
				+ ((Origin) b).getPassReward() + "Pièces d'or.", 3);
		player.setCash(player.getCash() + ((Origin) b).getPassReward());
	}

	/**
	 * 
	 * 
	 * Comment les joueurs se déplacent
	 * 
	 * 
	 */
	private void move02() {
		int dice = this.run.getPoint() + 1;
		PlayerModel p = this.run.getNowPlayer();
		// Unité de pixels mobiles
		int movePixel = 1;
		if (p.getX() < 12 * 60 && p.getY() == 0) {
			p.setX(p.getX() + movePixel);
		} else if (p.getX() == 12 *60 && p.getY() < 2 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() == 12 * 60 && p.getY() == 2 * 60){
			if ((int)(Math.random() * 2 ) == 0){
				p.setX(p.getX() - movePixel);
			} else {
				p.setY(p.getY() + movePixel);
			}
		} else if (p.getX() == 12 * 60 && p.getY() > 2 * 60 && p.getY() < 4 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() > 8 * 60 && p.getX() <= 12 * 60 && p.getY() == 4 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 8 * 60 && p.getY() == 4 * 60){
			if ((int)(Math.random() * 2 ) == 0){
				p.setX(p.getX() - movePixel);
			} else {
				p.setY(p.getY() + movePixel);
			}
		} else if (p.getX() > 4 * 60 && p.getX() < 8 * 60 && p.getY() == 4 * 60) {
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 8 * 60 && p.getY() > 4 * 60 && p.getY() < 7 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() >  4 * 60 && p.getX() <= 8 * 60 && p.getY() == 7 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() > 4 * 60 && p.getX() < 12 * 60 && p.getY() == 2 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 4 * 60 && p.getY() >= 2 * 60 && p.getY() < 7 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() > 0 && p.getX() <= 4 * 60 && p.getY() == 7 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 0 && p.getY() > 0){
			p.setY(p.getY() - movePixel);
		}
	}
	
	/**
	 * 
	 * 
	 * Comment les joueurs se déplacent
	 * 
	 * 
	 */
	private void move01() {
		int dice = this.run.getPoint() + 1;
		PlayerModel p = this.run.getNowPlayer();
		// Unité de pixels mobiles
		int movePixel = 1;
		Boolean turn = dice % 2 != 0;
		if (p.getX() < 9 * 60 && p.getY() == 0) {
			// Au-dessus de
			if (p.getX() == 4 * 60 && turn) {
				// La situation de la fourche dans la fourche
				p.setY(p.getY() + movePixel);
			} else {
				p.setX(p.getX() + movePixel);
			}
		} else if (p.getX() == 9 * 60 && p.getY() >= 0 && p.getY() < 60) {
			// [0,9]
			// bas
			p.setY(p.getY() + movePixel);
		} else if (p.getX() >= 8 * 60 && p.getX() < 12 * 60
				&& p.getY() >= 1 * 60 && p.getY() <= 60 * 1.5) {
			// droite
			p.setX(p.getX() + movePixel);
		} else if (p.getX() == 12 * 60 && p.getY() >= 1 * 60
				&& p.getY() < 7 * 60) {
			// bas
			p.setY(p.getY() + movePixel);
		} else if (p.getX() > 0 && p.getY() == 7 * 60) {
			// gauche
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 0 && p.getY() > 0) {
			// haut
			p.setY(p.getY() - movePixel);
		} else if (p.getX() == 4 * 60 && p.getY() > 0 && p.getY() < 7 * 60) {
			// bas
			p.setY(p.getY() + movePixel);
		}
	}
	/**
	 * 
	 * 
	 * Comment les joueurs se déplacent
	 * 
	 * 
	 */
	private void move03() {
		PlayerModel p = this.run.getNowPlayer();
		// Unité de pixels mobiles
		int movePixel = 1;
		if (p.getX() < 12 * 60 && p.getY() == 0) {
			p.setX(p.getX() + movePixel);
		} else if (p.getX() == 12 *60 && p.getY() < 7 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() > 0 && p.getY() == 7 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 0 && p.getY() > 0){
			p.setY(p.getY() - movePixel);
		}
	}
	/**
	 * 
	 * Une fois que le joueur s'est déplacé, arrêtez-vous pour juger.
	 * 
	 */
	public void playerStopJudge() {
		// Joueurs actuels
		PlayerModel player = this.run.getNowPlayer();
		if (player.getInHospital() > 0) {
			this.textTip.showTextTip(player, player.getName() + "Actuellement à l'hôpital, il ne peut pas être déplacé.",
					2);
			// Modifier le statut du joueur
			this.run.nextState();
		} else if (player.getInPrison() > 0) {
			this.textTip.showTextTip(player, player.getName() + "Actuellement en prison, il ne peut pas être déplacé.",
					2);
			// Modifier le statut du joueur
			this.run.nextState();
		} else {
			// Effectuer des opérations de joueur (achat d'une maison, événements, etc.)
			this.playerStop();
		}
	}

	/**
	 * 
	 * Une fois que le joueur a fini de se déplacer, arrêtez l'opération.
	 * 
	 */
	public void playerStop() {
		// Joueurs actuels
		PlayerModel player = this.run.getNowPlayer();
		// La maison à cet endroit
		Building building = this.building.getBuilding(player.getY() / 60,
				player.getX() / 60);
		if (building != null) {// Obtenir une maison
			int event = building.getEvent();
			// Informations sur le logement de déclenchement
			disposeStopEvent(building, event, player);

		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private void disposeStopEvent(Building b, int event, PlayerModel player) {
		switch (event) {
		case GameState.HOSPITAL_EVENT:
			// 
			stopInHospital(b, player);
			break;
		case GameState.HUOSE_EVENT:
			// 
			stopInHouse(b, player);
			break;
		case GameState.LOTTERY_EVENT:
			// 
			stopInLottery(b, player);
			break;
		case GameState.NEWS_EVENT:
			// 
			stopInNews(b, player);
			break;
		case GameState.ORIGIN_EVENT:
			// 
			stopInOrigin(b, player);
			break;
		case GameState.PARK_EVENT:
			// 
			stopInPack(b, player);
			break;
		case GameState.POINT_EVENT:
			// 
			stopInPoint(b, player);
			break;
		case GameState.PRISON_EVENT:
			// 
			stopInPrison(b, player);
			break;
		case GameState.SHOP_EVENT:
			// 
			stopInShop(b, player);
			break;
		}

	}

	/**
	 * 
	 * 
	 * 
	 */
	private void stopInShop(Building b, PlayerModel player) {
		if (player.getNx() > 0){
		// 
		((Shop_) b).createCards();
		// 
		this.panel.getShop().addCards((Shop_) b);
		// 
		this.panel.getShop().moveToFront();
		} else {
			this.run.nextState();
		}
	}

	/**
	 * 
	 * 
	 * 
	 */
	private void stopInPrison(Building b, PlayerModel player) {
		int days = (int) (Math.random() * 3) + 2;
		player.setInPrison(days);
		int random = (int) (Math.random() * ((Prison) b).getEvents().length);
		String text = ((Prison) b).getEvents()[random];
		this.textTip.showTextTip(player, player.getName() + text + "Rester"
				+ (days - 1) + "Dieu.", 3);
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * 
	 * 
	 */
	private void stopInPoint(Building b, PlayerModel player) {
		player.setNx(((Point) b).getPoint() + player.getNx());
		this.textTip.showTextTip(player, player.getName() + " Réaliser "
				+ ((Point) b).getPoint() + "Enroulez-vous.", 3);
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * 
	 * 
	 */
	private void stopInPack(Building b, PlayerModel player) {
		int random = (int) (Math.random() * ((Park) b).getImgageEvents().length);

		switch (random) {
		case 0:
		case 1:
			// 
			player.setCash(player.getCash() - 1);
			break;
		case 2:
			// 
			player.setCash(player.getCash() - 200);
			break;
		case 3:
			// 
			player.setCash(player.getCash() + 200);
			break;
		}
		// 
		this.events.showImg(((Park) b).getImgageEvents()[random], 3, new Point(
				320, 160, 0));
		new Thread(new MyThread(run, 3)).start();
	}

	/**
	 * 
	 * 
	 * 
	 */
	private void stopInOrigin(Building b, PlayerModel player) {
		this.textTip.showTextTip(player, player.getName() + " Restez au point de départ, récompensez "
				+ ((Origin) b).getReward() + "Pièce d'or.", 3);
		player.setCash(player.getCash() + ((Origin) b).getReward());
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * 
	 * 
	 */
	private void stopInNews(Building b, PlayerModel player) {
		int random = (int) (Math.random() * ((News) b).getImgageEvents().length);
		switch (random) {
		case 0:
		case 1:
			// 
			player.setInHospital(player.getInHospital() + 4);
			// 
			if (LandModel.hospital != null) {
				player.setX(LandModel.hospital.x);
				player.setY(LandModel.hospital.y);
			}
			break;
		case 2:
		case 3:
			player.setCash(player.getCash() - 1000);
			break;
		case 4:
			player.setCash(player.getCash() - 1500);
			break;
		case 5:
			player.setCash(player.getCash() - 2000);
			break;
		case 6:
		case 7:
			player.setCash(player.getCash() - 300);
			break;
		case 8:
			player.setCash(player.getCash() - 400);
			break;
		case 9:
			// 
			if (player.getNx() < 40) {
				stopInNews(b, player);
				return;
			}
			player.setNx(player.getNx() - 40);
			break;
		case 10:
			player.setCash(player.getCash() - 500);
			break;
		case 11:
			player.setCash(player.getCash() + 1000);
			break;
		case 12:
		case 13:
			player.setCash(player.getCash() + 2000);
			break;
		case 14:
			player.setCash(player.getCash() + 3999);
			player.setNx(player.getNx() + 100);
			break;
		case 15:
			player.setNx(player.getNx() + 300);
			break;
		case 16:
			for (int i = 0; i  < player.getCards().size();i++){
//				System.out.println(player.getCards().get(i).getcName());
				// 
				if (player.getCards().get(i).getName().equals("CrossingCard")){
					player.getCards().remove(i);
					// 
					player.getOtherPlayer().setCash(player.getOtherPlayer().getCash() - 3000);
					this.textTip.showTextTip(player, player.getName() + "Mettez\"3000 Yuan\"À blâmer pour"+ player.getOtherPlayer().getName()+". Ce n'est vraiment pas aussi bon que le calcul de Dieu.", 6);
					this.events.showImg(((News) b).get3000(), 3, new Point(
							420, 160, 0));
					new Thread(new MyThread(run, 3)).start();
					return;
				}
			}
			player.setCash(player.getCash() - 3000);
			break;
		}
		// 
		this.events.showImg(((News) b).getImgageEvents()[random], 3, new Point(
				420, 160, 0));
		new Thread(new MyThread(run, 3)).start();
	}

	/**
	 * 
	 * 
	 * 
	 */
	private void stopInLottery(Building b, PlayerModel player) {
		// 
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private void stopInHouse(Building b, PlayerModel player) {
		if (b.isPurchasability()) {// 
			if (b.getOwner() == null) { // 
				// 
				this.buyHouse(b, player);
			} else {// 
				if (b.getOwner().equals(player)) {// 
					// 
					this.upHouseLevel(b, player);
				} else {// 
					// 
					this.giveTax(b, player);
				}
			}
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private void giveTax(Building b, PlayerModel player) {
		if (b.getOwner().getInHospital() > 0) {
			// 
			this.textTip.showTextTip(player, b.getOwner().getName()
					+ "Je suis à l'hôpital et je ne paie pas le péage.", 3);
		} else if (b.getOwner().getInPrison() > 0) {
			// �����ı���ʾ
			this.textTip.showTextTip(player, b.getOwner().getName()
					+ "Je suis en prison et je suis exempté des péages.", 3);
		} else {
			int revenue = b.getRevenue();
			// ����Ҽ��ٽ��
			player.setCash(player.getCash() - revenue);
			// ҵ���õ����
			b.getOwner().setCash(b.getOwner().getCash() + revenue);
			// �����ı���ʾ
			this.textTip.showTextTip(player, player.getName() + "����"
					+ b.getOwner().getName() + "�ĵ��̣���·��:" + revenue + "���.", 3);

		}
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * ִ���������ݲ���
	 * 
	 */
	private void upHouseLevel(Building b, PlayerModel player) {
		if (b.canUpLevel()) {
			// ��������
			int price = b.getUpLevelPrice();
			String name = b.getName();
			String upName = b.getUpName();
			int choose = JOptionPane.showConfirmDialog(null,
					"�װ���:" + player.getName() + "\r\n" + "�Ƿ��������أ�\r\n" + name
							+ "��" + upName + "\r\n" + "�۸�" + price + " ���.");
			if (choose == JOptionPane.OK_OPTION) {
				if (player.getCash() >= price) {
					b.setLevel(b.getLevel() + 1);
					// ������Ҫ�Ľ��
					player.setCash(player.getCash() - price);
					// �����ı���ʾ
					this.textTip.showTextTip(player, player.getName() + " �� "
							+ name + " ������ " + upName + ".������ " + price
							+ "���. ", 3);
				} else {
					// �����ı���ʾ
					this.textTip.showTextTip(player, player.getName()
							+ " ��Ҳ���,����ʧ��. ", 3);
				}
			}
		}
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * ִ���򷿲���
	 * 
	 * 
	 */
	private void buyHouse(Building b, PlayerModel player) {
		int price = b.getUpLevelPrice();
		int choose = JOptionPane.showConfirmDialog(
				null,
				"�װ���:" + player.getName() + "\r\n" + "�Ƿ��������أ�\r\n"
						+ b.getName() + "��" + b.getUpName() + "\r\n" + "�۸�"
						+ price + " ���.");

		if (choose == JOptionPane.OK_OPTION) {
			// ����
			if (player.getCash() >= price) {
				b.setOwner(player);
				b.setLevel(1);
				// ���÷��ݼ��뵱ǰ��ҵķ����б���
				player.getBuildings().add(b);
				// ������Ҫ�Ľ��
				player.setCash(player.getCash() - price);
				this.textTip.showTextTip(player, player.getName()
						+ " ������һ��յ�.������: " + price + "���. ", 3);
			} else {
				this.textTip.showTextTip(player, player.getName()
						+ " ��Ҳ���,����ʧ��. ", 3);
			}
		}
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * ͣ����ҽԺ
	 * 
	 */
	private void stopInHospital(Building b, PlayerModel player) {
		int days = (int) (Math.random() * 4) + 2;
		player.setInHospital(days);
		int random = (int) (Math.random() * ((Hospital) b).getEvents().length);
		String text = ((Hospital) b).getEvents()[random];
		this.textTip.showTextTip(player, player.getName() + text + "ͣ��"
				+ (days - 1) + "��.", 3);
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * ��ƬЧ������
	 * 
	 */
	public void cardsBuff() {
		List<Card>delete = new ArrayList<Card>();
		for (Card a : this.run.getNowPlayer().getEffectCards()) {
			int buff = a.cardBuff();
			cardBuff(a, buff,delete);
		}
		this.run.getNowPlayer().getEffectCards().removeAll(delete);
		this.run.nextState();
	}

	/**
	 * 
	 * ��ƬЧ������
	 * 
	 * 
	 */
	private void cardBuff(Card card, int buff,List<Card>delete) {
		switch (buff) {
		case GameState.CARD_BUFF_TORTOISE:
			// �ڹ꿨BUff
			buffTortoiseCard((TortoiseCard) card,delete);
			break;
		case GameState.CARD_BUFF_STOP:
			// ͣ����Buff
			buffStopCard(card,delete);
			break;
		}
	}

	/**
	 * 
	 * ͣ����Buff
	 * 
	 * 
	 */
	private void buffStopCard(Card card,List<Card>delete) {
		// �����ı���ʾ
		this.textTip.showTextTip(card.geteOwner(), card.geteOwner().getName()
				+ " ��\"ͣ����\" ���ã������ƶ�.. ", 2);
		// �Ƴ���Ƭ
		delete.add(card);
		this.run.nextState();
		new Thread(new MyThread(run, 1)).start();
	}
	

	/**
	 * 
	 * �ڹ꿨BUff
	 * 
	 */

	private void buffTortoiseCard(TortoiseCard card,List<Card>delete) {
		if (card.getLife() <= 0) {
			delete.add(card);
			return;
		} else {
			card.setLife(card.getLife() - 1);
		}
		this.textTip.showTextTip(card.geteOwner(), card.geteOwner().getName()
				+ " ��\"�ڹ꿨\" ���ã�ֻ���ƶ�һ��.. ", 2);
		this.run.setPoint(0);
	}

	/**
	 * 
	 * ʹ�ÿ�Ƭ
	 * 
	 */
	public void useCards() {
		PlayerModel p = this.run.getNowPlayer();
		while (true) {
			if (p.getCards().size() == 0) {
				// �޿�Ƭ�������׶�
				this.run.nextState();
				break;
			} else {
				Object[] options = new Object[p.getCards().size() + 1];
				int i;
				for (i = 0; i < p.getCards().size(); i++) {
					options[i] = p.getCards().get(i).getcName() + "\r\n";
				}
				options[i] = "����,��ʹ��";
				int response = JOptionPane.showOptionDialog(null,
						" " + p.getName() + "��ѡ����Ҫʹ�õĿ�Ƭ", "��Ƭʹ�ý׶�.",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);
				if (response != i && response != -1) {
					// ��ÿ�Ƭ
					int th = p.getCards().get(response).useCard();
					// ʹ�ÿ�Ƭ
					useCard(p.getCards().get(response), th);
				} else {
					// ��ʹ�ã������׶�.
					this.run.nextState();
					break;
				}
			}
		}
	}

	/**
	 * 
	 * ʹ�ÿ�Ƭ
	 * 
	 */
	private void useCard(Card card, int th) {
		switch (th) {
		case GameState.CARD_ADDLEVEL:
			// ʹ�üӸǿ�
			useAddLevelCard(card);
			break;
		case GameState.CARD_AVERAGERPOOR:
			// ʹ�þ�ƶ��
			useAveragerPoorCard(card);
			break;
		case GameState.CARD_CHANGE:
			// ʹ�û��ݿ�
			useChangeCard(card);
			break;
		case GameState.CARD_CONTROLDICE:
			// ʹ��ң�����ӿ�
			useControlDiceCard(card);
			break;
		case GameState.CARD_HAVE:
			// ʹ�ù��ؿ�
			useHaveCard(card);
			break;
		case GameState.CARD_REDUCELEVEL:
			// ʹ�ý�����
			useReduceLevelCard(card);
			break;
		case GameState.CARD_ROB:
			// ʹ�����Ῠ
			useRobCard(card);
			break;
		case GameState.CARD_STOP:
			// ʹ��ͣ����
			useStopCard(card);
			break;
		case GameState.CARD_TALLAGE:
			// ʹ�ò�˰��
			useTallageCard(card);
			break;
		case GameState.CARD_TORTOISE:
			// ʹ���ڹ꿨
			useTortoiseCard(card);
			break;
		case GameState.CARD_TRAP:
			// ʹ���ݺ���
			useTrapCard(card);
			break;
		case GameState.CARD_CROSSING:
			// ʹ�ü޻���
			useCrossingCard(card);
			break;
		}
	}

	/**
	 * 
	 * ʹ�ü޻���
	 * 
	 */
	private void useCrossingCard(Card card) {
		Object[] options1 = { "����ѡ��" };
		JOptionPane.showOptionDialog(null, " �޻����ڴ��¼�����ʱ���Զ�ʹ��.",
				"��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options1,
				options1[0]);
	}

	/**
	 * 
	 * ʹ���ݺ���
	 * 
	 */
	private void useTrapCard(Card card) {
		Object[] options = { "ȷ��ʹ��", "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null, "ȷ��ʹ��\"�ݺ���\"�� \""
				+ card.getOwner().getOtherPlayer().getName() + "\"����2��?",
				"��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);
		if (response == 0) {
			// ʹ��
			PlayerModel cPlayer = card.getOwner().getOtherPlayer();
			// ��������
			cPlayer.setInPrison(cPlayer.getInPrison() + 2);
			// ���λ���л���ҽԺλ��
			if (LandModel.prison != null) {
				cPlayer.setX(LandModel.prison.x);
				cPlayer.setY(LandModel.prison.y);
			}
			// �����ı���ʾ
			this.textTip
					.showTextTip(card.getOwner(), card.getOwner().getName()
							+ " ʹ���� \"�ݺ���\"���� \""
							+ card.getOwner().getOtherPlayer().getName()
							+ "\"����2��.", 2);
			// ����ȥ��Ƭ
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * ʹ���ڹ꿨
	 * 
	 * 
	 */
	private void useTortoiseCard(Card card) {
		Object[] options = { card.getOwner().getName(),
				card.getOwner().getOtherPlayer().getName(), "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null,
				" ��ѡ��Ŀ����ң�������\"�ڹ꿨\".", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == 0) {
			card.getOwner().getEffectCards().add(card);
			card.seteOwner(card.getOwner());
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ���Լ�ʹ����\"�ڹ꿨\". ", 2);
			card.getOwner().getCards().remove(card);
		} else if (response == 1) {
			card.getOwner().getOtherPlayer().getEffectCards().add(card);
			card.seteOwner(card.getOwner().getOtherPlayer());
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ��\"" + card.getOwner().getOtherPlayer().getName()
					+ "\"ʹ����\"�ڹ꿨\". ", 2);
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * ʹ�ò�˰��
	 * 
	 * 
	 */
	private void useTallageCard(Card card) {
		Object[] options = { "ȷ��ʹ��", "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null, "ȷ��ʹ��\"��˰��\"�� \""
				+ card.getOwner().getOtherPlayer().getName() + "\"���л�� 10%˰��?",
				"��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);
		if (response == 0) {
			// ʹ��
			int money = (int) (card.getOwner().getOtherPlayer().getCash() / 10);
			card.getOwner().setCash(card.getOwner().getCash() + money);
			card.getOwner()
					.getOtherPlayer()
					.setCash(card.getOwner().getOtherPlayer().getCash() - money);
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ʹ���� \"��˰��\"���� \""
					+ card.getOwner().getOtherPlayer().getName()
					+ "\"���л�� 10%˰��", 2);
			// ����ȥ��Ƭ
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * 
	 * ʹ��ͣ����
	 * 
	 */
	private void useStopCard(Card card) {
		Object[] options = { card.getOwner().getName(),
				card.getOwner().getOtherPlayer().getName(), "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null,
				" ��ѡ��Ŀ����ң�������\"ͣ����\".", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == 0) {
			card.getOwner().getEffectCards().add(card);
			card.seteOwner(card.getOwner());
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ���Լ�ʹ����\"ͣ����\". ", 2);
			card.getOwner().getCards().remove(card);
		} else if (response == 1) {
			card.getOwner().getOtherPlayer().getEffectCards().add(card);
			card.seteOwner(card.getOwner().getOtherPlayer());
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ��\"" + card.getOwner().getOtherPlayer().getName()
					+ "\"ʹ����\"ͣ����\". ", 2);
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * 
	 * ʹ�����Ῠ
	 * 
	 * 
	 */
	private void useRobCard(Card card) {
		if (card.getOwner().getCards().size() >= PlayerModel.MAX_CAN_HOLD_CARDS) {
			// �޷�ʹ��
			Object[] options = { "����ѡ��" };
			JOptionPane.showOptionDialog(null, " ���Ŀ�Ƭ�����Ѿ��ﵽ���ޣ��޷�ʹ��\"���Ῠ\"",
					"��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		} else if (card.getOwner().getOtherPlayer().getCards().size() == 0) {
			// �޷�ʹ��
			Object[] options = { "����ѡ��" };
			JOptionPane.showOptionDialog(null, " \""
					+ card.getOwner().getOtherPlayer().getName()
					+ "\"û�п�Ƭ���޷�ʹ��\"���Ῠ\"", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		} else {
			PlayerModel srcPlayer = card.getOwner().getOtherPlayer();
			// ���ѡȡһ��
//			System.out.println(srcPlayer.getCards().size() + "zhang");
			Card getCard = srcPlayer.getCards().get((int) (srcPlayer.getCards().size() * Math.random()));
			// ����ɥʧ��Ƭ
			srcPlayer.getCards().remove(getCard);
			// ��Ƭӵ���߻��
			card.getOwner().getCards().add(getCard);
			// ���Ļ�ÿ�Ƭӵ����
			getCard.setOwner(card.getOwner());
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ʹ���� \"���Ῠ\"�������� \"" + srcPlayer.getName() + "\"��һ��\""
					+ getCard.getcName() + ".\". ", 2);
			// ����ȥ��Ƭ
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * ʹ�ý�����
	 * 
	 */
	private void useReduceLevelCard(Card card) {
		Building building = this.building.getBuilding(
				card.getOwner().getY() / 60, card.getOwner().getX() / 60);
		if (building.getOwner() != null
				&& building.getOwner().equals(card.getOwner().getOtherPlayer())) {// �Ƕ��ֵķ���
			if (building.getLevel() > 0) { // ���Խ���
				// ����
				building.setLevel(building.getLevel() - 1);
				// �����ı���ʾ
				this.textTip.showTextTip(card.getOwner(), card.getOwner()
						.getName()
						+ " ʹ���� \"������\"����\""
						+ card.getOwner().getOtherPlayer().getName()
						+ "\"�ķ��ݵȼ�����һ��. ", 2);
				// ����ȥ��Ƭ
				card.getOwner().getCards().remove(card);
			} else {
				// �޷�ʹ��,���ɽ���
				Object[] options = { "����ѡ��" };
				JOptionPane.showOptionDialog(null, " ��ǰ���ݲ��ɽ���", "��Ƭʹ�ý׶�.",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);
			}
		} else {
			// �޷�ʹ��.
			Object[] options = { "����ѡ��" };
			JOptionPane.showOptionDialog(null, " ��ǰ���ݲ���ʹ�øÿ�Ƭ.", "��Ƭʹ�ý׶�.",
					JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options, options[0]);
		}
	}

	/**
	 * 
	 * ʹ�ù��ؿ�
	 * 
	 */
	private void useHaveCard(Card card) {
		// �õص㷿��
		Building building = this.building.getBuilding(
				card.getOwner().getY() / 60, card.getOwner().getX() / 60);
		if (building.getOwner() != null
				&& building.getOwner().equals(card.getOwner().getOtherPlayer())) {// �ǶԷ��ķ���
			Object[] options = { "ȷ��ʹ��", "����ѡ��" };
			int response = JOptionPane.showOptionDialog(null,
					"ȷ��ʹ��\"���ؿ�\"���˵��չ�����Ҫ���ѣ�" + building.getAllPrice() + " ���.",
					"��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			if (response == 0) {
				if (card.getOwner().getCash() >= building.getAllPrice()) {
					// ��ҽ���
					building.getOwner().setCash(
							building.getOwner().getCash()
									+ building.getAllPrice());
					card.getOwner().setCash(
							card.getOwner().getCash() - building.getAllPrice());
					building.setOwner(card.getOwner());
					// �����ı���ʾ
					this.textTip.showTextTip(card.getOwner(), card.getOwner()
							.getName() + " ʹ���� \"���ؿ�\"���չ�����˸�����. ", 2);
					// ����ȥ��Ƭ
					card.getOwner().getCards().remove(card);
				} else {
					Object[] options1 = { "����ѡ��" };
					JOptionPane.showOptionDialog(null, " ��Ҳ��㣬�޷�������!",
							"��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options1,
							options1[0]);
				}
			}
		} else {
			Object[] options1 = { "����ѡ��" };
			JOptionPane.showOptionDialog(null, "�˷����޷�ʹ�øÿ�Ƭ.", "��Ƭʹ�ý׶�.",
					JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options1, options1[0]);
		}
	}

	/**
	 * 
	 * 
	 * ʹ��ң�����ӿ�
	 * 
	 * 
	 */
	private void useControlDiceCard(Card card) {
		Object[] options = { "1��", "2��", "3��", "4��", "5��", "6��", "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null,
				"ȷ��ʹ��\"ң�����ӿ�\"ң�����ӵ���?", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == -1 || response == 6) {
			return;
		} else {
			// ʹ��
			this.run.setPoint(response);
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ʹ���� \"ң�����ӿ�\".", 2);
			// ����ȥ��Ƭ
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * ʹ�û��ݿ�
	 * 
	 */
	private void useChangeCard(Card card) {
		Building building = this.building.getBuilding(
				card.getOwner().getY() / 60, card.getOwner().getX() / 60);
		if (building.getOwner() != null
				&& building.getOwner().equals(card.getOwner().getOtherPlayer())) {// �Ƕ��ַ���
			Object[] options = { "ȷ��ʹ��", "����ѡ��" };
			int response = JOptionPane.showOptionDialog(null,
					"ȷ��ʹ��\"���ݿ�\"����ֽ���һ��ͬ���͵ķ��ݣ������", "��Ƭʹ�ý׶�.",
					JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				// ��Ѱ��ȼ�����
				int thisBuildingLevel = building.getLevel();
				Building changeBuilding = null;
				for (Building a : card.getOwner().getBuildings()) {
					if (a.getLevel() == thisBuildingLevel) {
						changeBuilding = a;
						break;
					}
				}
				// �ҵ�ͬ���ͷ���
				if (changeBuilding != null) {
					changeBuilding.setOwner(card.getOwner().getOtherPlayer());
					building.setOwner(card.getOwner());
					// �����ı���ʾ
					this.textTip.showTextTip(card.getOwner(), card.getOwner()
							.getName()
							+ " ʹ���� \"���ݿ�\"����ĳ��������"
							+ card.getOwner().getOtherPlayer().getName()
							+ "�õصķ��ݽ��н���.. ", 2);
					// ����ȥ��Ƭ
					card.getOwner().getCards().remove(card);
				} else {
					Object[] options1 = { "����ѡ��" };
					JOptionPane.showOptionDialog(null, " ��ǰ���ݲ���ʹ��\"���ݿ�\"",
							"��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options1,
							options1[0]);
				}
			}
		} else {
			Object[] options = { "����ѡ��" };
			JOptionPane.showOptionDialog(null, " ��ǰ���ݲ���ʹ��\"���ݿ�\"", "��Ƭʹ�ý׶�.",
					JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options, options[0]);
		}
	}

	/**
	 * 
	 * ʹ�þ�ƶ��
	 * 
	 */
	private void useAveragerPoorCard(Card card) {
		Object[] options = { "ȷ��ʹ��", "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null,
				"ȷ��ʹ��\"��ƶ��\"�����ƽ���ֽ�?", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == 0) {
			// ʹ��
			int money = (int) (card.getOwner().getCash() + card.getOwner()
					.getOtherPlayer().getCash()) / 2;
			card.getOwner().setCash(money);
			card.getOwner().getOtherPlayer().setCash(money);
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ʹ���� \"��ƶ��\"�������ƽ�����ֽ�,����˫���ֽ���Ϊ:" + money + " ���. ", 2);

			// ����ȥ��Ƭ
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * ʹ�üӸǿ�
	 * 
	 */

	private void useAddLevelCard(Card card) {
		Building building = this.building.getBuilding(
				card.getOwner().getY() / 60, card.getOwner().getX() / 60);
		if (building.getOwner() != null
				&& building.getOwner().equals(card.getOwner())) {// ���Լ��ķ���
			if (building.canUpLevel()) { // ������
				// ����
				building.setLevel(building.getLevel() + 1);
				// �����ı���ʾ
				this.textTip.showTextTip(card.getOwner(), card.getOwner()
						.getName() + " ʹ���� \"�Ӹǿ�\"�������ݵȼ�����һ��. ", 2);
				// ����ȥ��Ƭ
				card.getOwner().getCards().remove(card);
			} else {
				// �޷�ʹ��,��������
				Object[] options = { "����ѡ��" };
				JOptionPane.showOptionDialog(null, " ��ǰ���ݲ�������.", "��Ƭʹ�ý׶�.",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);
			}
		} else {
			// �޷�ʹ��.
			Object[] options = { "����ѡ��" };
			JOptionPane.showOptionDialog(null, " ��ǰ���ݲ���ʹ�øÿ�Ƭ.", "��Ƭʹ�ý׶�.",
					JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options, options[0]);
		}
	}

	/**
	 * 
	 * �˳��̵�
	 * 
	 */
	public void exitShop() {
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * �̵�����Ƭ����
	 * 
	 * 
	 */
	public void buyCard(Shop_ shop) {
		int chooseCard = this.panel.getShop().getChooseCard();
		if (chooseCard >= 0
				&& this.panel.getShop().getCard().get(chooseCard) != null) {
			// ����Ƭ �������ɹ�
			if (this.buyCard(shop, chooseCard)) {
				// �գ���ȥ��Ƭ
				this.panel.getShop().getCard().get(chooseCard).setEnabled(false);
				// ��ʼ����ѡ��Ƭ
				this.panel.getShop().setChooseCard(-1);
			}
		}
	}

	/**
	 * 
	 * ����Ƭ
	 * 
	 * 
	 */
	public boolean buyCard(Shop_ shop, int p) {
		if (this.panel.getShop().getCard().get(p) != null) {
			if (this.run.getNowPlayer().getCards().size() >= PlayerModel.MAX_CAN_HOLD_CARDS) {
				JOptionPane.showMessageDialog(null, "�����ɳ���:"
						+ PlayerModel.MAX_CAN_HOLD_CARDS + "�ſ�Ƭ,Ŀǰ�Ѿ������ٹ�����!");
				return false;
			}
			if (this.run.getNowPlayer().getNx() < shop.getCards().get(p)
					.getPrice()) {
				JOptionPane.showMessageDialog(null, "��ǰ��Ƭ��Ҫ:"
						+ shop.getCards().get(p).getPrice() + "���,���ĵ������.");
				return false;
			}
			// ���ÿ�Ƭӵ����
			shop.getCards().get(p).setOwner(this.run.getNowPlayer());
			// ����ҿ�Ƭ�������ӿ�Ƭ
			this.run.getNowPlayer().getCards().add(shop.getCards().get(p));
			// ��ȥ��Ӧ���
			this.run.getNowPlayer().setNx(
					this.run.getNowPlayer().getNx()
							- shop.getCards().get(p).getPrice());
		}
		return true;
	}

	/**
	 * 
	 * ��Ϸ����~
	 * 
	 * 
	 * @param winer
	 */
	public void gameOver () {
		this.run.setNowPlayerState(GameRunning.GAME_STOP);
		this.panel.getBackgroundUI().moveToFront();
		this.panel.getRunning().moveToFront();
		this.panel.getPlayerInfo().moveToFront();
		this.panel.getEffect().moveToFront();
		this.music.gameOver();
		this.effect.showImg("timeover2");
		
	}
}
