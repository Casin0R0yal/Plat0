import java.io.DataInputStream;
import java.util.Scanner;
import java.io.*;

public class Main{

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please enter a test number");
            return;
        }

        int testNumber = Integer.parseInt(args[0]);
        switch (testNumber) {
            case 1:
                TestPlayer();
                break;
            case 2:
                TestBoard();
                break;
            case 3:
                TestGame();
                break;
            case 4:
                TestWholeProcess();
                break;
            default:
                System.out.println("Please enter a valid test number");
                break;
        }
    }

    public static void TestPlayer()
    {
        Player player1 = new Player("red", 1);
        Player player2 = new Player("yellow", 2);
    }

    // test board class
    public static void TestBoard()
    {
        Board board = new Board();
        board.PrettyPrint();
    }

    // test game class

    public static void TestGame()
    {
        Board board = new Board();
        Game game = new Game(board);

        game.CheckDiagonal(0, 0);
        game.CheckHorizontal(0, 0);
        game.CheckVertical(0, 0);
        game.CheckBoard(new int[]{0, 0});
    }


    // test whole process
    public static void TestWholeProcess()
    {
        Board board = new Board();
        Game game = new Game(board);
        game.Start();
    }

}


