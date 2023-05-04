import cardpackage.*;
import java.util.Random;
import java.util.Scanner;


// import javax.imageio.ImageIO;
// import javax.swing.*;
// import java.awt.image.BufferedImage;
// import java.awt.*;
// import java.io.*;

public class Main {
    public static void main(String[] args)
    {
        Card[] Cards = InitCard();

        Cards = Shuffle(Cards);

        Column[] Columns = initColumn(Cards);
        Deck deck = new Deck(Cards);
        //PlaySwing(Columns, deck);
        while(true)
        {
            Play(Columns, deck);
            //clear the console
            System.out.print("\033[H\033[2J");
        }
    }

    // public static void PlaySwing(Column[] Columns, Deck deck) throws IOException
    // {

    //     JFrame frame = buildFrame();

    //     final BufferedImage image = ImageIO.read(new FileInputStream("//home//samy//oop//Solitaire//src//img//1.png"));

    //     JPanel pane = new JPanel() 
    //     {
    //         @Override
    //         protected void paintComponent(Graphics g) {
    //             super.paintComponent(g);
    //             g.drawImage(image, 100, 100, null);
    //         }
    //     };


    //     frame.add(pane);
          
    //     JButton b=new JButton("move");
    //     b.setBounds(100,800,100, 40);
    //     frame.add(b);

    //     JButton b2=new JButton("move");
    //     b2.setBounds(250,800,100, 40);
    //     frame.add(b2);

    //     JButton b3=new JButton("move");
    //     b3.setBounds(400,800,100, 40);
    //     frame.add(b3);

    //     JButton b4=new JButton("move");
    //     b4.setBounds(550,800,100, 40);
    //     frame.add(b4);

    //     frame.setSize(800,900);
    //     frame.setLayout(null);
    //     frame.setVisible(true);
    // }

    // private static JFrame buildFrame() 
    // {
    //     JFrame frame = new JFrame();
    //     frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //     frame.setSize(200, 200);
    //     frame.setVisible(true);
    //     return frame;
    // }


    public static void Play(Column[] Columns, Deck deck)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Here are the Top cards in the Columns:");
        for (int i = 0; i < Columns.length; i++) 
        {
            Card temp = Columns[i].Cards.peek();    
            temp.Print();
        }
        System.out.println();
        if(!deck.DiscardStack.isEmpty())
        {
            System.out.println("The top card in the discard pile is:");
            deck.DiscardStack.peek().Print();
            System.out.println();
        }
        else
            System.out.println("The discard pile is empty");
        
        deck.PrintStacks();

        System.out.println("What are you going to do?");
        System.out.println("1. Draw a card");
        System.out.println("2. Move a card");
        int choice = s.nextInt();
        while (choice!= 1 && choice != 2) 
        {
            System.out.println("Please enter a valid choice");
            choice = s.nextInt();
        }
        if(choice == 1)
        {
            Card temp = deck.Draw();
            System.out.println("You drew a " + temp.Info + " of " + temp.Form + "s");
        }
        else
        {
            System.out.println("Do you want to move a card from the Deck or from one of the columns?");
            System.out.println("1. Deck");
            System.out.println("2. Columns");
            int choice2 = s.nextInt();
            while (choice2!= 1 && choice2 != 2) 
            {
                System.out.println("Please enter a valid choice");
                choice2 = s.nextInt();
            }
            if(choice2 == 2)
            {
                System.out.println("Which column do you want to move from?");
                int from = s.nextInt();
                while (from < 1 || from > 7) 
                {
                    System.out.println("Please enter a valid choice");
                    from = s.nextInt();
                }
                System.out.println("Do you want to move it to the 4 Form Deck ? (y/n)");
                String choice3 = s.next();
                while (!choice3.equals("y") && !choice3.equals("n")) 
                {
                    System.out.println("Please enter a valid choice");
                    choice3 = s.next();
                }
                if(choice3.equals("y"))
                {
                    if(deck.AddtoStack(Columns[from-1].Cards.peek()))
                    {
                        Columns[from-1].Cards.pop();
                        System.out.println("You moved a card");
                    }
                    else
                    {
                        System.out.println("You can't move that card");
                        System.out.println("Enter c to continue");
                        String c = s.next();
                    }
                }
                else   
                {
                    System.out.println("Which column do you want to move to?");
                    int to = s.nextInt();
                    while (to < 1 || to > 7) 
                    {
                        System.out.println("Please enter a valid choice");
                        to = s.nextInt();
                    }

                    if(Columns[to-1].AddCard(Columns[from-1].Cards.peek()))
                    {
                        Columns[from-1].Cards.pop();
                        System.out.println("You moved a card");
                    }
                    else
                    {
                        System.out.println("You can't move that card");
                        System.out.println("Enter c to continue");
                        String c = s.next();
                    }
                }
            }
            else
            {
                System.out.println("Which column do you want to move to?");
                int to = s.nextInt();
                while (to < 1 || to > 7) 
                {
                    System.out.println("Please enter a valid choice");
                    to = s.nextInt();
                }
                if(Columns[to-1].AddCard(deck.DiscardStack.peek()))
                {   
                    deck.DiscardStack.pop();
                    System.out.println("You moved a card");
                }
                else
                {
                    System.out.println("You can't move that card");
                    System.out.println("Enter c to continue");
                    String c = s.next();
                }
            }
        }
        
    }
    
    public static Column[] initColumn(Card[] Cards)
    {
        Column[] Columns = new Column[7];
        int counter = 0;
        for (int i = 0; i < 7; i++) 
        {
            Cards[counter].Reveal();
            Columns[i] = new Column(i+1, false, Cards[counter++]);
            for (int j = 0; j < i; j++) 
            {
                Columns[i].Cards.add(Cards[counter++]);
            }
        }
        return Columns;
    }
    
    
    public static Card[] InitCard()
    {
        Card[] Cards = new Card[52];
        for (int i = 0; i < 52; i++) 
        {
            Cards[i] = new Card(((i+1) > 26), (i % 13)+1, null, (i / 13));
            
            switch (Cards[i].Value) 
            {
                case 1:
                    Cards[i].Info = "Ace";
                    break;
                case 11:
                    Cards[i].Info = "Jack";
                    break;
                case 12:
                    Cards[i].Info = "Queen";
                    break;
                case 13:
                    Cards[i].Info = "King";
                    break;
                default:
                    Cards[i].Info = Integer.toString(Cards[i].Value);
                    break;
            }
        }
        return Cards;
    }
    
    
    public static Card[] Shuffle(Card[] Cards)
    {
        Random rand = new Random();
        for (int i = 0; i < 52; i++) 
        {
            int randomIndexToSwap = rand.nextInt(52);
            Card temp = Cards[randomIndexToSwap];
            Cards[randomIndexToSwap] = Cards[i];
            Cards[i] = temp;
        }
        return Cards;
    }
}
