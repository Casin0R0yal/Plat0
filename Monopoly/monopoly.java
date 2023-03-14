package Monopoly;

public class monopoly {
    public static void main(String[] args) {
        monopoly m = new monopoly();
        m.Play();
    }
    public void Play() {
        board b = new board();
        String[] names = {"Roro", "Arthur"};
        player[] p = new player[names.length];
        for (int i = 0; i < names.length; i++) {p[i] = new player(names[i]);}
        while(true) {
            for (int i = 0; i < p.length; i++) {
                int play = 1;
                while (play>0) {
                    board.printBoard(p);
                    p[i].play(b);
                    play += p[i].move(p);
                    board.printBoard(p);
                    b.getbox(p[i].getPosition()).action.perform(b,p,i);
                    play--;
                }
                System.console().readLine();
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
        p[1].retrieveMoney(p[1].getMoney());
        b.getbox(1).action.perform(b,p,1);
    }
    public void PlayBuild() {
        board b = new board();
        String[] names = {"Player 1", "Player 2", "Player 3"};
        player[] p = new player[names.length];
        for (int i = 0; i < names.length; i++) {p[i] = new player(names[i]);}
        for (int i : new int[]{1,3,6,8,9,11,13,14}) {
            p[0].setPosition(i);
            b.getbox(i).action.perform(b,p,0);
            p[0].addMoney(1000);
        }
        while(true) {
            int play = 1;
            while (play>0) {
                board.printBoard(p);
                p[0].play(b);
                play += p[0].move(p);
                board.printBoard(p);
                b.getbox(p[0].getPosition()).action.perform(b,p,0);
                play--;
            }
        }
    }
}