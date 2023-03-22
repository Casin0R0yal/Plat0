import java.io.*;
package Connect4;

public class Player(){

    public String color;
    public int token;
    public boolean HasWon;

    public Player(String color,int token)
    {
        this.color = color;
        this.token = token;
        Haswon = false;

    }

}
