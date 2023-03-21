import java.io.*;
package Connect4;


public class Board{
    int rows = 7;
    int cols = 6;
    int[][] table = new int[7][6];

    public Board()
    {
    }

    public static boolean IsEmpty()
    {
        return false;
    }

    public static boolean IsFull()
    {
        return false;
    }

    public static void Clear()
    {
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
