import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Scanner;
;
import java.util.Collections;

public class Board {
    private Stack<Card> stack;
    private Player[] players;
    private int turn;
    private Stack<Card> pick;

    private String[] colors = {"red", "green", "blue", "yellow"};

    private int nbcardstopick = 0;
    private String currentcolor = "";
    public Board(int nbplayers) {
        this.stack = new Stack<Card>();
        this.players = new Player[nbplayers];
        this.pick = new Stack<Card>();
        for (String color: colors){
            this.pick.push(new Card(color, 0, false, false, false, false));
            for (int i=1; i<10; i++){
                pick.push(new Card(color, i, false, false, false, false));
                pick.push(new Card(color, i, false, false, false, false));
            }
            for (int i=0; i<2; i++){
                pick.push(new Card(color, -1, false, false, true, false));
                pick.push(new Card(color, -1, false, false, true, false));
            }
            for (int i=0; i<2; i++){
                pick.push(new Card(color, -1, false, false, false, true));
                pick.push(new Card(color, -1, false, false, false, true));
            }

        }
        for (int i=0; i<4; i++){
            pick.push(new Card("", -1, true, false, false, false));
            pick.push(new Card("", -1, false, true, false, false));
        }
        java.util.Collections.shuffle(pick);
        for (int i=0; i<nbplayers; i++){
            this.players[i] = new Player();
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


    public void play() {
        // TODO Auto-generated method stub
        stack.peek().displaycard();
        System.out.println();
        boolean canfollow = false;
        List<Card> playablecards =  new ArrayList<Card>();
        System.out.println("\u001B[46m"+"Your hand");
        for (Card cardtmp : players[turn].getHand())
        {
            cardtmp.displaycard();
        }
        if (nbcardstopick==0)
        {
            for (Card cardtmp : players[turn].getHand())
            {

                if (cardtmp.isplus2 || cardtmp.is_4_joker){
                    int index = players[turn].getHand().indexOf(cardtmp);

                    canfollow = true;
                    playablecards.add(cardtmp);
                }
            }


            if (!canfollow)
            {
                for (int i=0; i<nbcardstopick; i++)
                {
                    players[turn].addCard(pick.pop());
                }
                nbcardstopick=0;
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
                    if (c.is_joker || top.getColor() == c.getColor() || top.getValue() == c.getValue()) {
                        stack.push(c);
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
                        }
                        if (c.iskip)
                        {
                            turn = (turn + 1) % players.length;
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

        }
    }

    public void main_play() {
        Boolean end = false;
        while (!end) {
            play();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ClearConsole.main(null);
            if (this.players[turn].getHand().size() == 0) {
                end = true;
            }
            turn = (turn + 1) % players.length;
        }
        System.out.println("Player " + turn + " won");
    }

}
