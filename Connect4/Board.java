import java.io.*;


public class Board{
    public final static int rows = 6;
    public final static int cols = 7;
    public static int[][] table = new int[rows][cols];

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
        if(y < 0 || y > cols)
        {
            System.out.println("Invalid column");
            return null;
        }
        int abs = 5;
        while(abs >=0 && table[abs][y] != 0)
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
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                System.out.print(" | ");
                System.out.print(table[i][j]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public static int CheckWinner() {
        // Check rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                int val = table[i][j];
                if (val != 0 && val == table[i][j+1] && val == table[i][j+2] && val == table[i][j+3]) {
                    return val;
                }
            }
        }

        // Check columns
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i <= rows - 4; i++) {
                int val = table[i][j];
                if (val != 0 && val == table[i+1][j] && val == table[i+2][j] && val == table[i+3][j]) {
                    return val;
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                int val = table[i][j];
                if (val != 0 && val == table[i+1][j+1] && val == table[i+2][j+2] && val == table[i+3][j+3]) {
                    return val;
                }
            }
        }

        // Check diagonals (bottom-left to top-right)
        for (int i = rows - 1; i >= 3; i--) {
            for (int j = 0; j <= cols - 4; j++) {
                int val = table[i][j];
                if (val != 0 && val == table[i-1][j+1] && val == table[i-2][j+2] && val == table[i-3][j+3]) {
                    return val;
                }
            }
        }

        // Check for a tie
        if (IsFull()) {
            return -1;
        }

        // No winner yet
        return 0;
    }

}
