package Monopoly;

import java.util.ArrayList;

public class monopoly {
    public static void main(String[] args) {
        monopoly m = new monopoly();
        m.Play();
    }
    public void Play() {
        board b = new board();
        int turn = 0;
        ArrayList<player> player = new ArrayList<player>();
        player.add(new player("player 1"));
        player.add(new player("player 2"));

        while(player.size()>1) {
            player p = player.get(turn);
            while (p.getPlay()>0) {
                board.printBoard(player);
                p.play(b,player);
                if(player.size()==1){return;}
                p.move(player);
                if(player.size()==1){return;}
                board.printBoard(player);
                b.getbox(p.getPosition()).action.perform(b,player,turn);
                if(player.size()==1){return;}
                p.removePlay();
            }
            System.console().readLine();
            turn = turn==player.size()-1?0:turn+1;
            p.addPlay();
        }
    }
    public void PlayBankrupt() {
        board b = new board();
        ArrayList<player> p = new ArrayList<player>();
        p.add(new player("Player 1"));
        p.add(new player("Player 2"));

        p.get(0).setPosition(1);
        b.getbox(1).action.perform(b,p,0);

        for (int i : new int[]{3,5,6,8,9,11,12}) {
            p.get(1).setPosition(i);
            b.getbox(i).action.perform(b,p,1);
        }
        p.get(1).setPosition(1);
        p.get(1).removeMoney(p.get(1).getMoney(),p);
        b.getbox(1).action.perform(b,p,1);
    }

    public void PlayPay() {
        board b = new board();
        ArrayList<player> p = new ArrayList<player>();
        p.add(new player("Player 1"));
        p.add(new player("Player 2"));
        p.add(new player("Player 3"));
        for (int i : new int[]{1,3,6,8,9,12,28,5,15,25,35}) {
            board.printBoard(p);
            p.get(0).setPosition(i);
            b.getbox(i).action.perform(b,p,0);
            p.get(0).addMoney(500);
        }

        board.printBoard(p);
        p.get(0).play(b,p);
        p.get(0).move(p);
        board.printBoard(p);
        b.getbox(p.get(0).getPosition()).action.perform(b,p,0);
        p.get(0).removePlay();

        for (int i : new int[]{3,9,12,15}) {
            p.get(1).setPosition(i);
            b.getbox(i).action.perform(b,p,1);
        }
    }
}