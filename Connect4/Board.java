import java.io.*;


public class Board{
    public final static int rows = 7;
    public final static int cols = 6;
    public static int[][] table = new int[7][6];

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

    public int[] PutCell(Player p, int y)
    {
        if(y < 0 || y > 5)
            {
                System.out.println("Invalid column");
                return null;
            }
        int abs = 6;
        while(abs >=0 && table[abs][y] == 1)
            {
                abs--;
            }
        if(abs < 0)
            {
                System.out.println("Column is full");
                return null;
            }
        table[abs][y] = p.token;
        return new int[]{abs, y}; // return the coordinates of the cell
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


}
