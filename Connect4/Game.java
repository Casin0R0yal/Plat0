package Connect4;
import java.io.DataInputStream;

public class Game{
    
    Board board;
    Player player1;
    Player player2;
    
    public Game(int[][] board){
        Board = board;
        player1 = new Player("red");
        player2 = new Player("yellow");
    }

    public static void CheckBoard()
    {
        for(int i = 0; i < cols; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                // check neighbours table[j][i]
            }
            System.out.println();
        }
        // Declare if player has won
    }

    public static boolean EndGame()
    {
        if(player1.HasWon || player2.HasWon)
            return true;
        else
            return false;
    }

    public static boolean TieGame()
    {
        return board.IsFull();
    }

    public static void makeRound()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Player 1, it's your turn.");
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();

        player1.putCell(x1,y1);

        System.out.println("Player 2, it's your turn.");
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();

        player2.putCell(x2, y2);

    }

    public static void Start()
    {
        if(EndGame())
        {
            System.out.println("It is the End.");
            return;
        }
        if(TieGame())
        {
            System.out.println("It is a Tie. Start a new game !");
            return;

        }
        makeRound();
        Start();
    }

}
