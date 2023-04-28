package Monopoly;

import java.util.ArrayList;

public class land extends stations {
    Boolean canBuild;
    int[] group;
    int nbHouses;
    int nbHotels;

    public land(type Type, String Name, int Price, color Color, int[] Rent, int[] Group, action Action) {
        super(Type, Name, Price, Color, Rent, Action);
        this.canBuild = false;
        this.group = Group;
        this.nbHouses = 0;
        this.nbHotels = 0;
    }
    public int getNbHouses() { return nbHouses; }
    public int getNbHotels() { return nbHotels; }
    public color getColor() { return color; }
    public int[] getGroup() { return group; }
    public player getOwner() { return owner; }
    public Boolean getCanBuild() {return canBuild;}
    public void setCanBuild(Boolean b) { canBuild = b; }
    public void addHouse() { nbHouses++; }
    public void removeHouse() { nbHouses--; }
    public int morgage(Boolean forReal) {
        if(!forReal){
            return (price + nbHouses*rent[7] + nbHotels*rent[8])/2;}
        mortgaged = true;
        nbHouses=0; 
        nbHotels=0;
        return (price + nbHouses*rent[7] + nbHotels*rent[8])/2;
    }
    public void unmorgage() { mortgaged = false; }
    public Boolean isMorgaged() { return mortgaged; }
    public void removeProperty(player i) {
        i.removeProperty(this);
        owner = null;
        mortgaged = false;
    }

    public static int onProperty(land p, ArrayList<player> lp, int i) {
        if (p.owner == null) { return buyProperty(p,lp,i); }
        else if (p.isMorgaged()) {
            System.out.println("This property is mortgaged.");
            return 0;
        }
        else if (p.owner == lp.get(i)) {
            System.out.println("You own this property.");
            return 0;
        }
        else {System.out.println(lp.get(i).getName()+", you are on "+p.name+"."); return payOwner(p,i,lp); }
    }
    private static int buyProperty(land p, ArrayList<player> lp, int i) {
        if (lp.get(i).getMoney() >= p.price)
        {
            System.out.println("\n"+lp.get(i).getName()+", do you want to buy "+p.name+" for "+"\u001B[9m"+"M"+"\u001B[0m"+p.price+" ? (y/n)");
            if(System.console().readLine().equals("y")) {
                lp.get(i).removeMoney(p.price,lp);
                lp.get(i).addPatrimony(p.price/2);
                lp.get(i).addProperty(p);
                p.owner = lp.get(i);
                System.out.println("You bought "+p.name+".");
                return 1;
            }
        }
        int sale = p.auctionSale(lp);
        if (sale == 0) { System.out.println("Nobody bought "+p.name+"."); return 0;}
        return 1;
    }
    private static int payOwner(land p, int i,ArrayList<player> lp) {
        int recu;
        if(p.getNbHouses()==0){recu = lp.get(i).payOwner(p.rent[0]*(p.getCanBuild()?2:1), p.owner.getName(),lp);}
        else{recu = lp.get(i).payOwner(p.rent[p.getNbHouses()], p.owner.getName(),lp);}
        p.owner.addMoney(recu);
        return recu;
    }

    public int proposal(ArrayList<player> p, int i,int max) {
        System.out.print(p.get(i).getName() + " (M" + p.get(i).getMoney()+") your proposal (empty:abandon):");
        try {
            int prop = Integer.parseInt(System.console().readLine());
            if (prop <= max || prop > p.get(i).getMoney()) {
                System.out.println("Invalid proposal.");
                return proposal(p, i, max);
            }
            return prop;
        } catch (Exception e) {return -1;}
    }
    public int auctionSale(ArrayList<player> p){
        System.out.println("Auction for " + name + " (M" + price + ")");
        int max = 0;int lon=p.size();
        Boolean[] wantIt = new Boolean[lon]; for (int k=0;k<lon;k++) {wantIt[k] = true;}
        int winner = -1;
        int j=0;
        while(true) {
            if (wantIt[j]) {
                int prop = proposal(p, j, max);
                if (prop == -1) {wantIt[j] = false;}
                else {max = prop;}
            }
            Boolean alone=true;
            j++;
            if(j==lon){j=0;}
            for (int k = 0; k < lon; k++) {if(j!=k) {alone &= !wantIt[k];}}
            if (alone) {winner = j; break;}
        }
        if(max == 0) {max = 1;}
        p.get(winner).removeMoney(max,p);
        p.get(winner).addPatrimony(price/2);
        p.get(winner).addProperty(this);
        owner = p.get(winner);
        System.out.println(p.get(winner).getName() + " won the auction for " + name + " with a proposal of " + max + ".");
        return max;

    }
}
