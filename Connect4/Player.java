import java.io.*;
import java.awt.Color;
public class Player {

    public String color;
    public int token;
    public boolean IsPlaying;
    public boolean HasWon;

    public Player(String color,int token)
    {
        this.color = color;
        this.token = token;
        IsPlaying = false;
        HasWon = false;
    }

    public Color getColor()
    {
        if(color == "red")
            return Color.RED;
        else
            return Color.YELLOW;
    }

    public String getName()
    {
        return color;
    }

    public int getId()
    {
        Color c = this.getColor();
        if(c == Color.RED)
            return 1;
        else
            return 2;
    }

}
