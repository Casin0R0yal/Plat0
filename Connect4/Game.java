package Connect4;

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
    }

    public static boolean EndGame()
    {
        if(board.IsFull)
            return true;
        else if(player1.HasWon || player2.HasWon)
            return true;
        else
            return false;
    }

    public makeMove()
    {
    }

    public Start()
    {
    }

}
