package uno.src;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Collections;
public class Board {
    private Stack<Card> stack;
    private PlayerUno[] players;
    private int turn;
    private Stack<Card> pick;

    private String[] colors = {"red", "green", "blue", "yellow"};

    private int nbcardstopick = 0;
    private String currentcolor = "";
    public Board(int nbplayers) {
        this.stack = new Stack<Card>();
        this.players = new PlayerUno[nbplayers];
        this.pick = new Stack<Card>();
        for (String color: colors){
            for (int i=1; i<10; i++){
                pick.push(new Card(color, i, false, false, false, false, "./src/images/"+color+i+".png"));
                pick.push(new Card(color, i, false, false, false, false, "./src/images/"+color+i+".png"));
            }
            for (int i=0; i<2; i++){
                pick.push(new Card(color, -1, false, false, true, false, "./src/images/"+color+"+2.png"));
                pick.push(new Card(color, -1, false, false, true, false, "./src/images/"+color+"+2.png"));
            }
            for (int i=0; i<2; i++){
                pick.push(new Card(color, -1, false, false, false, true, "./src/images/"+color+"skip.png"));
                pick.push(new Card(color, -1, false, false, false, true, "./src/images/"+color+"skip.png"));
            }

        }
        for (int i=0; i<4; i++){
            pick.push(new Card("", -1, true, false, false, false, "./src/images/joker.png"));
            pick.push(new Card("", -1, false, true, false, false, "./src/images/joker+4.png"));
        }
        java.util.Collections.shuffle(pick);
        for (int i=0; i<nbplayers; i++){
            this.players[i] = new PlayerUno();
            for (int j=0; j<7; j++){
                this.players[i].addCard(pick.pop());
            }
        }
        this.turn = 0;
        Card ctmp = pick.pop();
        while (ctmp.is_joker || ctmp.is_4_joker){
            pick.push(ctmp);
            java.util.Collections.shuffle(pick);
            ctmp = pick.pop();
        }
        this.stack.push(ctmp);
        currentcolor = stack.peek().getColor();
    }

    public PlayerUno[] getPlayers() {
        return players;
    }

    public void play() {
        // TODO Auto-generated method stub
        stack.peek().displaycard();
        if (stack.peek().is_4_joker || stack.peek().is_joker)
            System.out.println("\nCurrent color: " + currentcolor);

        if (nbcardstopick!=0)
        {
            System.out.println("You have to pick " + nbcardstopick + " cards");
            for (int i=0; i<nbcardstopick; i++)
            {
                players[turn].addCard(pick.pop());
            }
            nbcardstopick=0;
            return;
        }


        System.out.println();
        boolean canfollow = false;
        List<Card> playablecards =  new ArrayList<Card>();
        System.out.println("Your hand" + "player " + turn );
        for (int i=0; i<players[turn].getHand().size() ;i++)
        {
            players[turn].getHand().get(i).displaycard();
            System.out.print(":"+ i + " ");
        }
        if (nbcardstopick==0)
        {
            for (Card cardtmp : players[turn].getHand())
            {

                if (cardtmp.isplus2 || cardtmp.is_4_joker || cardtmp.getColor() == currentcolor || cardtmp.getValue() == stack.peek().getValue() ){
                    int index = players[turn].getHand().indexOf(cardtmp);

                    canfollow = true;
                }
            }


            if (!canfollow)
            {
                System.out.println("You can't follow, you have to pick a card");
                nbcardstopick = 1;
                for (int i=0; i<nbcardstopick; i++)
                {
                    players[turn].addCard(pick.pop());
                }
                nbcardstopick=0;
                turn = (turn+1)%players.length;
                return;
            }
        }

        if (canfollow) {

            if (nbcardstopick == 0)
                playablecards = players[turn].getHand();



            System.out.println(Card.ANSI_BLACK_BACKGROUND);
            System.out.println(Card.TEXT_WHITE+"Choose a card to play");
            Scanner sc = new Scanner(System.in);
            int card = sc.nextInt();
            Card c = playablecards.get(card);


            if (c.is_joker) {
                System.out.println("Choose a color");
                String color = sc.next();
                currentcolor = color;
            } else {

                Card top = stack.peek();
                Boolean played = false;

                while (!played) {
                    if (c.is_joker || c.is_4_joker || currentcolor == c.getColor() || top.getValue() == c.getValue()) {

                        stack.push(c);
                        currentcolor = c.getColor();
                        played = true;
                        if (c.is_joker) {
                            System.out.println("Choose a color");
                            String color = sc.next();
                            currentcolor = color;
                        }
                        if (c.isplus2) {
                            nbcardstopick += 2;
                        }
                        if (c.is_4_joker) {
                            nbcardstopick += 4;
                            System.out.println("Choose a color");
                            String color = sc.next();
                            currentcolor = color;
                        }



                    } else {
                        System.out.println("You can't play this card");
                        System.out.println("Choose a card to play");
                        sc = new Scanner(System.in);
                        List<Card> tmp= this.players[turn].getHand();
                        c= tmp.get(sc.nextInt());

                    }
                }

            }

            players[turn].removeCard(c);
            if (c.iskip)
            {
                turn = (turn + 1) % players.length;
            }
            for(int i=0; i<50; i++)
            {
                System.out.println();
            }
            // wait for 5 seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void main_play() {
        Boolean end = false;
        while (!end) {
            play();
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if (this.players[turn].getHand().size() == 0) {
                end = true;
            }
            turn = (turn + 1) % players.length;
        }
        System.out.println("Player " + turn + " won");
    }

}
