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
    public boolean CheckHorizontal(int x, int y)
    {
        //check the horizontal line
        int count = 0;
        for(int i = 0; i < board.rows; i++)
        {
            if(board.table[i][y] == board.table[x][y])
                count++;
            else
                count = 0;
            if(count == 4)
            {
                if(board.table[x][y] == 1)
                    player1.HasWon = true;
                    
                else
                    player2.HasWon = true;
                return true;
            }
        }
        return false;

    }
    public boolean CheckVertical(int x, int y)
    {
        //check the vertical line
        int count = 0;
        for(int i = 0; i < board.cols; i++)
        {
            if(board.table[x][i] == board.table[x][y])
                count++;
            else
                count = 0;
            if(count == 4)
            {
                if(board.table[x][y] == 1)
                    player1.HasWon = true;
                else
                    player2.HasWon = true;
                return true;
            }
        }
        return false;
    }
    public boolean CheckDiagonal(int x, int y)
    {
        //check the diagonal line
        int count = 0;
        int i = x;
        int j = y;
        while(i < board.rows && j < board.cols)
        {
            if(board.table[i][j] == board.table[x][y])
                count++;
            else
                count = 0;
            if(count == 4)
            {
                if(board.table[x][y] == 1)
                    player1.HasWon = true;
                else
                    player2.HasWon = true;
                
                System.out.println("1");
                return true;
            }
            i++;
            j++;
        }
        i = x;
        j = y;
        while(i >= 0 && j >= 0)
        {
            if(board.table[i][j] == board.table[x][y])
                count++;
            else
                count = 0;
            if(count == 4)
            {
                if(board.table[x][y] == 1)
                    player1.HasWon = true;
                else
                    player2.HasWon = true;
                
                System.out.println("2");
                return true;
            }
            i--;
            j--;
        }
        i = x;
        j = y;
        while(i >= 0 && j < board.cols)
        {
            if(board.table[i][j] == board.table[x][y])
                count++;
            else
                count = 0;
            if(count == 4)
            {
                if(board.table[x][y] == 1)
                    player1.HasWon = true;
                else
                    player2.HasWon = true;
                
                System.out.println("3");
                return true;
            }
            i--;
            j++;
        }
        i = x;
        j = y;
        while(i < board.rows && j >= 0)
        {
            if(board.table[i][j] == board.table[x][y])
                count++;
            else
                count = 0;
            if(count == 4)
            {
                if(board.table[x][y] == 1)
                    player1.HasWon = true;
                else
                    player2.HasWon = true;
                return true;

                System.out.println("4");
            }
            i++;
            j--;
        }
        return false;
    }
    public void CheckBoard(int[] x)
    {
        //check if there is a winner
        if(CheckHorizontal(x[0],x[1]) || CheckVertical(x[0],x[1]) || CheckDiagonal(x[0],x[1]))
        {
            if(player1.HasWon)
                System.out.println("Player 1 has won !");
            if(player2.HasWon)
                System.out.println("Player 2 has won !");
        }
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
        int y1 = sc.nextInt();
        int[] p1 = board.PutCell(player1,y1);
        while(p1 == null)
        {
            y1 = sc.nextInt();
            p1 = board.PutCell(player1,y1);
        }
        CheckBoard(p1);
        board.PrettyPrint();

        if(EndGame())
        {
            return;
        }
        System.out.println("Player 2, it's your turn.");
        int y2 = sc.nextInt();

        int[] p2 = board.PutCell(player2,y2);
        while(p2 == null)
        {
            y2 = sc.nextInt();
            p2 = board.PutCell(player2,y2);
        }
        CheckBoard(p2);

        board.PrettyPrint();

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
