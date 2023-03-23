package Monopoly;

public class monopoly {
    public static void main(String[] args) {
        monopoly m = new monopoly();
        m.PlayPay();
    }
    public void Play() {
        board b = new board();
        String[] names = {"Player 1", "Player 2", "Player 3"};
        player[] p = new player[names.length];
        for (int i = 0; i < names.length; i++) {p[i] = new player(names[i]);}
        while(true) {
            for (int i = 0; i < p.length; i++) {
                while (p[i].getPlay()>0) {
                    board.printBoard(p);
                    p[i].play(b,p);
                    p[i].move(p);
                    board.printBoard(p);
                    b.getbox(p[i].getPosition()).action.perform(b,p,i);
                    p[i].removePlay();
                }
                System.console().readLine();
                p[i].addPlay();
            }
        }
    }
    public void PlayBankrupt() {
        board b = new board();
        player[] p = new player[2];
        p[0] = new player("Player 1");
        p[1] = new player("Player 2");

        p[0].setPosition(1);
        b.getbox(1).action.perform(b,p,0);

        for (int i : new int[]{3,5,6,8,9,11,12}) {
            p[1].setPosition(i);
            b.getbox(i).action.perform(b,p,1);
        }
        p[1].setPosition(1);
        p[1].removeMoney(p[1].getMoney());
        b.getbox(1).action.perform(b,p,1);
    }

    public void PlayPay() {
        board b = new board();
        String[] names = {"Player 1", "Player 2", "Player 3"};
        player[] p = new player[names.length];
        for (int i = 0; i < names.length; i++) {p[i] = new player(names[i]);}
        for (int i : new int[]{1,3,6,8,9,12,28,5,15,25,35}) {
            board.printBoard(p);
            p[0].setPosition(i);
            b.getbox(i).action.perform(b,p,0);
            p[0].addMoney(500);
        }

        board.printBoard(p);
        p[0].play(b,p);
        p[0].move(p);
        board.printBoard(p);
        b.getbox(p[0].getPosition()).action.perform(b,p,0);
        p[0].removePlay();

        for (int i : new int[]{3,9,12,15}) {
            p[1].setPosition(i);
            b.getbox(i).action.perform(b,p,1);
        }
    }
}