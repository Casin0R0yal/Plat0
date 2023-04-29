import cardpackage.*;

import java.util.Stack;

public class Deck
{
    Stack<Card> CurrenStack = new Stack<Card>();    
    Stack<Card> DiscardStack = new Stack<Card>();

    Stack<Card> ClubStack = new Stack<Card>();
    Stack<Card> SpadeStack = new Stack<Card>();
    Stack<Card> DiamondStack = new Stack<Card>();
    Stack<Card> HeartStack = new Stack<Card>();

    public Deck( Card[] restCards)
    {
        for (int i = 29; i < 52; i++) 
        {
            CurrenStack.push(restCards[i]);
        }
    }
    
    
    public Card Draw()
    {
        if(this.CurrenStack.isEmpty())
            this.Restart();
        this.DiscardStack.push(this.CurrenStack.pop());
        
        return this.DiscardStack.peek();
    }
    
    
    public void Restart()
    {
        while(this.DiscardStack.peek() != null)
            this.CurrenStack.push(this.DiscardStack.pop());
    }
    
    
    public boolean AddtoStack(Card toAdd)
    {
        switch (toAdd.Form) 
        {
            case CLUB:
                if(this.ClubStack.isEmpty() && toAdd.Value == 1)
                {
                    this.ClubStack.push(toAdd);
                    return true;
                }
                if(this.ClubStack.peek().Value == toAdd.Value - 1)
                    this.ClubStack.push(toAdd);
                return true;
            case DIAMOND:
                if(this.DiamondStack.isEmpty() && toAdd.Value == 1)
                {
                    this.DiamondStack.push(toAdd);
                    return true;
                }    
                if(this.DiamondStack.peek().Value == toAdd.Value - 1)
                    this.DiamondStack.push(toAdd);
                return true;
            case HEART:
                if(this.HeartStack.isEmpty() && toAdd.Value == 1)
                {
                    this.HeartStack.push(toAdd);
                    return true;
                }
                if(this.HeartStack.peek().Value == toAdd.Value - 1)
                    this.HeartStack.push(toAdd);
                return true;
            case SPADE:
                if(this.SpadeStack.isEmpty() && toAdd.Value == 1)
                {
                    this.SpadeStack.push(toAdd);
                    return true;
                }
                if(this.SpadeStack.peek().Value == toAdd.Value - 1)
                    this.SpadeStack.push(toAdd);
                return true;
            default:
                break;
        }
        return false;
    }
    public boolean PrintStacks()
    {
        if(this.ClubStack.isEmpty())
            System.out.println("Clubs: Empty");
        else
        {
            System.out.print("Clubs:" );
            ClubStack.peek().Print();
            System.out.println();
        }
        
        if(this.DiamondStack.isEmpty())
            System.out.println("Diamonds: Empty");
        else
        {
            System.out.print("Diamonds:" );
            DiamondStack.peek().Print();
            System.out.println();
        }

        if(this.HeartStack.isEmpty())
            System.out.println("Hearts: Empty");
        else
        {
            System.out.print("Hearts:" );
            HeartStack.peek().Print();
            System.out.println();
        }

        if(this.SpadeStack.isEmpty())
            System.out.println("Spades: Empty");
        else
        {
            System.out.print("Spades:" );
            SpadeStack.peek().Print();
            System.out.println();
        }

        return true;
    }
}
