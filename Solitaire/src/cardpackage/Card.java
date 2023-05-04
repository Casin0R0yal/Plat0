package cardpackage;

public class Card
{
    public boolean Color;
    //False for Black and True for Red
    public boolean State;
    //False for Hidden and True for Shown
    public int Value;
    // 1-13 for Ace-King
    public String Info;
    // Ace-2-3-...-Queen-King
    public Form Form;

    
    public Card(boolean color, int value, String info, int form) {
        this.Color = color;
        this.Value = value;
        this.Info = info;
        this.Form = Form.values()[form];
        this.State = false;
    }
    
    
    public boolean Reveal()
    {
        if(this.State)
            throw new IllegalStateException("Already Revealed !");
        this.State = true;
        return true;
    }

    public void Print()
    {
        System.out.print(this.Info + " of " + this.Form.getSymbol() + "     ");
    }
    public int Img()
    {
        return this.Value + (this.Form.ordinal() * 13);
    }
}
