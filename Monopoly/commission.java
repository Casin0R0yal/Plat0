package Monopoly;

import java.util.ArrayList;
import java.util.Random;


interface traduction {
    void perform(board b, ArrayList<player> p, int i);
}

public class commission {
    traduction action;
    String text;

    public commission() {
        String[] commission = {
            "Placez-vous sur la case départ",
            "Erreur de la banque en votre faveur. Recevez M200",
            "Payez la note du médecin M100",
            "La vente de votre stock vous rapporte M100",
            "Vous êtes libéré de prison. Cette carte peut être conservée jusqu’à ce qu’elle soit utilisée ou vendue.",
            "Aller en prison. Rendez-vous directement à la prison. Ne franchissez pas par la case départ, ne touchez pas M200",
            "Retournez à Belleville",
            "Recevez votre revenu annuel M200",
            "C’est votre anniversaire. Chaque joueur doit vous donner M100",
            "Les contributions vous remboursent la somme de M200",
            "Recevez votre intérêt sur l’emprunt à 7% M250",
            "Payez votre Police d’Assurance M150",
            "Payez une amende de M100 (1) ou bien tirez une carte « CHANCE » (2)",
            "Rendez-vous à la gare la plus proche. Si vous passez par la case départ, recevez M200",
            "Vous avez gagné le deuxième Prix de Beauté. Recevez M100",
            "Vous héritez M100"
        };
        traduction[] trad = {
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).setPosition(0); b.getbox(0).action.perform(b, p, i);} },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).addMoney(200); } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).removeMoney(100,p);}},
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).addMoney(100); } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).setJailCard(true); } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).GoToJail(); } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).setPosition(1); b.getbox(1).action.perform(b, p, i);} },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).addMoney(200); } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) {
                int lon = p.size();
                for(int j = 0; j < lon; j++) { if (j != i && p.get(i).getMoney()<100) {p.get(i).removeMoney(100,p);
                p.get(i).addMoney(100*(lon-1));}}}},
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).addMoney(200); } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).addMoney(250); } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).removeMoney(150,p);}},
            new traduction() { public void perform(board b, ArrayList<player> p, int i) {
                if(p.get(i).getMoney()>=100 && System.console().readLine().equals("1")) {
                    p.get(i).removeMoney(100,p);}
                else { luck x = new luck(); x.onLuck(b,p,i); } } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { int pos = p.get(i).getPosition();
                if(pos < 5) { p.get(i).setPosition(5);b.getbox(5).action.perform(b, p, i);}
                else if(pos < 15) { p.get(i).setPosition(15);b.getbox(15).action.perform(b, p, i);}
                else if(pos < 25) { p.get(i).setPosition(25);b.getbox(15).action.perform(b, p, i);}
                else if(pos < 35) { p.get(i).setPosition(35);b.getbox(15).action.perform(b, p, i);}
                else { p.get(i).setPosition(5);p.get(i).addMoney(200);b.getbox(5).action.perform(b, p, i);} } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).addMoney(100); } },
            new traduction() { public void perform(board b, ArrayList<player> p, int i) { p.get(i).addMoney(100); } }
            };
		Random rand = new Random();
        int x=rand.nextInt(16);
        this.text = commission[x];
        this.action = trad[x];
    }
}
