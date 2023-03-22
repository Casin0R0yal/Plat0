import java.io.DataInputStream;
import java.util.Scanner;
import java.io.*;

public class Game{

    public Board board;
    public Player player1;
    public Player player2;

    public Game(Board board){
        this.board = board;
        player1 = new Player("red",1);
        player2 = new Player("yellow",2);
    }

    public void CheckBoard()
    {
        for(int i = 0; i < board.cols; i++)
        {
            for(int j = 0; j < board.rows; j++)
            {
                // check neighbours table[j][i]
            }
            System.out.println();
        }
        // Declare if player has won
    }

    public boolean EndGame()
    {
        if(this.player1.HasWon || this.player2.HasWon)
            return true;
        else
            return false;
    }

    public boolean TieGame()
    {
        return board.IsFull();
    }

    public void makeRound()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Player 1, it's your turn.");
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();

        board.PutCell(player1,x1,y1);

        System.out.println("Player 2, it's your turn.");
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();

        board.PutCell(player2,x2, y2);

    }

    public void Start()
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
