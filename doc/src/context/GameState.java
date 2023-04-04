package context;

/**
 * 
 * Constantes de jeu
 * 
 * 
 * @author MOVELIGHTS
 *
 */
public class GameState {

	// Restez dans l'état de retour du bâtiment
	
	public  final static int HOSPITAL_EVENT = 1;

	public  final static int HUOSE_EVENT = 2;
	
	public  final static int LOTTERY_EVENT = 3;
	
	public  final static int NEWS_EVENT = 4;
	
	public  final static int ORIGIN_EVENT = 5;
	
	public  final static int PARK_EVENT = 6;
	
	public  final static int POINT_EVENT = 7;
	
	public  final static int PRISON_EVENT = 8;
	
	public  final static int SHOP_EVENT = 9;
	
	// En passant par le bâtiment et en revenant à l'État
	
	public final static int ORIGIN_PASS_EVENT = 1;
	
	// Utilisez la carte pour revenir au statut
	
	public  final static int CARD_ADDLEVEL = 1;
	
	public  final static int CARD_AVERAGERPOOR = 2;
	
	public  final static int CARD_CHANGE = 3;

	public  final static int CARD_CONTROLDICE = 4;
	
	public  final static int CARD_CROSSING = 5;
	
	public  final static int CARD_HAVE = 6;
	
	public  final static int CARD_REDUCELEVEL = 7;
	
	public  final static int CARD_ROB = 8;
	
	public  final static int CARD_TALLAGE = 9;
	
	public  final static int CARD_TORTOISE = 10;
	
	public  final static int CARD_TRAP = 11;
	
	public  final static int CARD_STOP = 12;
	
	// État de retour de l'effet de la carte
	
	public final static int CARD_BUFF_STOP = 1;
	
	public final static int CARD_BUFF_TORTOISE = 2;
}
