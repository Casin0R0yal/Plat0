import cardpackage.Card;

import java.util.Stack;

public class Column
{
    int Size;
    boolean IsEmpty;
    Stack<Card> Cards = new Stack<Card>();

    
    public Column(int size, boolean emptiness, Card top)
    {
        this.Size = size;
        this.IsEmpty = emptiness;
        this.Cards.push(top);
    }

    
    public boolean AddColumn(Column toAdd)
    {
        if(this.Cards.isEmpty() && toAdd.Cards.peek().Value == 13)
        {
            this.Cards.push(toAdd.Cards.pop());
            return true;
        }
        if((toAdd.Cards.peek().Value -1 ) == this.Cards.peek().Value && toAdd.Cards.peek().Color != this.Cards.peek().Color)
        {
            this.Cards.push(toAdd.Cards.pop());
            return true;
        }
        return false;
    }
    public boolean AddCard(Card toAdd)
    {
        if(this.Cards.isEmpty() && toAdd.Value == 13)
        {
            this.Cards.push(toAdd);
            return true;
        }

        if((toAdd.Value - 1) < this.Cards.peek().Value && toAdd.Color != this.Cards.peek().Color)
        {
            this.Cards.push(toAdd);
            return true;
        }
        return false;
    }

}
