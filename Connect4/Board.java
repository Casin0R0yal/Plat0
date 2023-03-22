import java.io.*;
package Connect4;


public class Board{
    public const int rows = 7;
    public const int cols = 6;
    public int[][] table = new int[7][6];

    public Board()
    {
    }

    public static boolean IsEmpty()
    {
        for(int i = 0; i < cols; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                if(table[j][i] != 0)
                    return false;
            }
        }
        return true;

    }

    public static boolean IsFull()
    {
        for(int i = 0; i < cols; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                if(table[j][i] == 0)
                    return false;
            }
        }
        return true;
    }

    public static void Clear()
    {
        for(int i = 0; i < cols; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                table[j][i] = 0;
            }
        }
    }

    public static void PutCell(Player p, int x, int y)
    {
        table[x][y] = p.token;
    }

    public void PrettyPrint()
    {
        for(int i = 0; i < cols; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                System.out.print(" | ");
                System.out.print(table[j][i]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        Board b = new Board();
        b.PrettyPrint();
    }

}
