package Monopoly;

import java.util.ArrayList;
import java.util.Collections;


public class trade {
    int JAILCARD = 41;

    private player p; //player to trade with
    private player me;
    private ArrayList<Integer> give;
    private ArrayList<Integer> take;

    public trade(ArrayList<player> lp, player i) {
        this.me = i;
        this.give = new ArrayList<Integer>();
        this.take = new ArrayList<Integer>();
        startExchange(lp, i);
        System.out.println("Select something to take :");
        choose(this.p,this.take);
        System.out.println("Select something to give :");
        choose(i,this.give);
        execute(i,lp);
    }

    private Boolean hasProposedMoney(ArrayList<Integer> l){
        for (int i=0;i<l.size();i++) {if (l.get(i) < 0) {return true;}}return false;
    }
    private void money(player player, ArrayList<Integer> l) {
        System.out.print("Amount: ");
        try {
            int amount = Integer.parseInt(System.console().readLine());
            if(amount>0) {l.add(-amount);return;}
        } catch (NumberFormatException e) {}
        System.out.println("Invalid amount.");
        money(player, l);
    }

    private void startExchange(ArrayList<player> p, player me) {
        System.out.println("Select someone to exchange with:");
        int nb = 0;
        int[] index = new int[p.size()];
        for (int i=0;i<p.size();i++) {
            if(p.get(i)!=me) {
                System.out.println("("+(nb+1)+") "+p.get(i).getName()+".");
                index[nb]=i;nb++;
            }
        } System.out.print("Your choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice<=nb && choice>0) {this.p=p.get(index[choice-1]);return;}
        } catch (NumberFormatException e) {}
        System.out.println("Invalid choice.");
        startExchange(p, me);
    }
    private void choose(player player, ArrayList<Integer> l) {
        int nb = 0;
        int[] index = new int[player.getNbProperties()+2-l.size()];
        Boolean noOption = true;
        if (player.getJailCard() && ((this.p!=player&&!this.p.getJailCard())||(p==player&&!me.getJailCard())) && !l.contains(JAILCARD)) {
            index[nb]=(int) JAILCARD;
            nb++;
            System.out.println("("+nb+") Jail card.");
            noOption = false;
        }
        for(int i=0;i<player.getNbProperties();i++) {
            if (!l.contains(i)) {
                index[nb]=i;
                nb++;
                System.out.println("("+nb+") "+player.getProperties(i).name+".");
                noOption = false;
            }
        }
        if(!hasProposedMoney(l)) {
            index[nb]=-1;
            nb++;
            System.out.println("("+nb+") Money.");
            noOption = false;
        }
        
        if(noOption){return;}
        System.out.print("Your choice ('q' to quit): ");
        String c = System.console().readLine();
        if(c.equals("q")) {return;}
        try {
            int choice = Integer.parseInt(c);
            if(choice<=nb && choice>0) {
                if(index[choice-1]==-1) {money(player,l);}
                else{l.add(index[choice-1]);}
            }
        } catch (NumberFormatException e) {System.out.println("Invalid choice.");}
        choose(player,l);
    }

    private void execute(player x,ArrayList<player> player) {
        System.out.println(this.p.getName()+", do you accept giving:");
        for (int i=0;i<this.take.size();i++) {
            if (this.take.get(i)==JAILCARD) {
                System.out.println(" - Jail card.");}
            else if (this.take.get(i)<0) {
                System.out.println(" - M"+(-this.take.get(i))+".");}
            else {
                System.out.println(" - "+this.p.getProperties(this.take.get(i)).name+".");}
        }
        System.out.println("To take:");
        for (int i=0;i<this.give.size();i++) {
            if (this.give.get(i)==JAILCARD) {
                System.out.println(" - Jail card.");
            }
            else if (this.give.get(i)<0) {
                System.out.println(" - M"+(-this.give.get(i))+".");
            }
            else {
                System.out.println(" - "+x.getProperties(this.give.get(i)).name+".");
            }
        }
        System.out.print("y/n: ");
        if(System.console().readLine().equals("n"))
        {
            System.out.println("Trade cancelled.");
            return;
        }
        Collections.sort(this.give,Collections.reverseOrder());
        Collections.sort(this.take,Collections.reverseOrder());
        for (int i=0;i<this.take.size();i++) {
            if (this.take.get(i)<0) {
                this.p.removeMoney(-this.take.get(i),player);
                x.addMoney(-this.take.get(i));
            }
            else if (this.take.get(i)==JAILCARD) {
                this.p.setJailCard(false);
                x.setJailCard(true);
            }
            else {
                stations station = this.p.removeProperty(this.take.get(i));
                x.addProperty(station);
            }
        }
        for (int i=0;i<this.give.size();i++) {
            if (this.give.get(i)<0) {
                x.removeMoney(-this.give.get(i),player);
                this.p.addMoney(-this.give.get(i));
            }
            else if (this.give.get(i)==JAILCARD) {
                x.setJailCard(false);
                this.p.setJailCard(true);
            }
            else {
                stations station = x.removeProperty(this.give.get(i));
                this.p.addProperty(station);
            }
        }
        System.out.println("Trade executed.");
    }
}
