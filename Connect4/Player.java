import java.io.*;

public class Player {

    public String color;
    public int token;
    public boolean HasWon;

    public Player(String color,int token)
    {
        this.color = color;
        this.token = token;
        HasWon = false;
    }

}
