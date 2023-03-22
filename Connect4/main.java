import java.io.*;

public class Main{

    public static void main(String[] args)
    {
        Board b = new Board();
        Game g = new Game(b);
        b.PrettyPrint();
        System.out.println("HelloWorld");
    }

}
