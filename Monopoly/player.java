package Monopoly;

import java.util.ArrayList;

class player {
    private String name;
    private int money;
    private int position;
    private ArrayList<stations> properties;
    private int jailTime;
    private int nbDoubles;
    private int nbTrainStations;
    private int nbUtilities;
    private Boolean jailCard;
    private int patrimony;
    private int play;

    public player(String Name) {
        this.name = Name;
        this.money = 1500;
        this.position = 0;
        this.properties = new ArrayList<stations>();
        this.jailTime = 0;
        this.nbDoubles = 0;
        this.nbTrainStations = 0;
        this.nbUtilities = 0;
        this.jailCard = false;
        this.patrimony = 0;
        this.play = 1;
    }
    public String getName() { return this.name; }
    public int getMoney() { return this.money; }
    public int getPosition() { return this.position; }
    public int getNbDouble() { return this.nbDoubles; }
    public stations getProperties(int i) { return properties.get(i); }
    public int getNbProperties() { return properties.size(); }
    public int getNbTrainStations() {return this.nbTrainStations;}
    public int getNbUtilities() {return this.nbUtilities;}
    public Boolean getJailCard() { return this.jailCard; }
    public land getLand(int i) { return (land)properties.get(i); }
    public void addMoney(int amount) { this.money += amount; }
    public void addDouble() { this.nbDoubles++; }
    public int setPosition(int position) { return this.position = position; }
    // public void addProperty(land p) {properties.add(p);p.owner=this;}
    public void addProperty(stations p) {properties.add(p);p.owner=this;if(p.type==type.TRAINSTATIONS){nbTrainStations++;}else if(p.type==type.COMPANIES){nbUtilities++;}}
    public void setJailCard(Boolean b) { this.jailCard = b; }
    public void resetDouble() { this.nbDoubles = 0; }
    public void addPatrimony(int amount) {this.patrimony += amount;}
    public int removeMoney(int amount) { if(money<amount){bankruptcy(amount);}money-=amount; return amount; }
    public void ReduceJailTime() { this.jailTime--; }
    public void GoToJail() {this.position=10; this.jailTime=3; nbDoubles=0;}
    public void forward(int roll) {this.position += roll;if(this.position>39){this.position-=40;this.money+=200;}}
    // public void removeProperty(land l) {properties.remove(l);patrimony-=l.price/2;}
    public void removeProperty(stations l) {properties.remove(l);patrimony-=l.price/2;}
    public void removeProperty(int i) {patrimony-=properties.get(i).price/2;properties.remove(i);}
    public int getPlay() {return this.play;}
    public void addPlay() {this.play++;}
    public void removePlay() {this.play--;}
    
    public void play(board b, player[] players) {
        int choice = 1;
        int[] corresp = {1,2,3,4};
        System.out.println(name + "\n(1) to roll the dices.");
        if(hasJailCard(players)||hasProperty(players)) {choice++;System.out.println("("+choice+") if you want to do some exchanges.");}
        if(majGroup(b)) {choice++;System.out.println("("+choice+") to buy houses or Hotels.");}
        if(hasHouse()) {choice++;corresp[choice-1]=3;System.out.println("("+choice+") to sell houses or Hotels.");}
        try {
            int rep = Integer.parseInt(System.console().readLine())-1;
            if(rep<0) {System.out.println("Invalid choice");}
            else if(rep == 0) {return;}
            else if(rep<choice && corresp[rep] == 2) {startExchange(players);}
            else if(rep<choice && corresp[rep] == 3) {build();}
            else if(rep<choice && corresp[rep] == 4) {sellHoustel();}
            else {System.out.println("Invalid choice");}

        }
        catch (NumberFormatException e) {System.out.println("Invalid choice");}
        play(b,players);
    }
    
    private void sellHoustel() {
        int nb = 0;
        int[] index = new int[getNbProperties()];
        System.out.println("You can sell houses or hotels on the following properties:");
        for (int i=0;i<getNbProperties();i++) {
            if(getProperties(i) instanceof land) {
                land l = (land)getProperties(i);
                if(l.getNbHouses()>0) {
                    System.out.print("("+(nb+1)+") "+l.name + " 1 ");
                    if(l.getNbHouses()<5) {System.out.print("house");}
                    else {System.out.print("hotel");}
                    System.out.println(" (M"+l.rent[7]/2+").");
                    index[nb]=i;
                    nb++;
                }
            }
        }
        System.out.print("Your choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice>nb) {System.out.println("Invalid choice.");sellHoustel();return;}
            land l = (land)getProperties(index[choice-1]);
            int sum = l.rent[7]/2;
            addMoney(sum);
            patrimony -= sum/2;
            l.retrieveHouse();
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            sellHoustel();
            return;
        }
    }

    private void build() {
        int nb = 0;
        int[] index = new int[getNbProperties()];
        System.out.println("You can build on the following properties:");
        for (int i=0;i<getNbProperties();i++) {
            if(getProperties(i) instanceof land) {
                land l = (land)getProperties(i);
                if(l.getCanBuild()) {
                    System.out.print("("+(nb+1)+") "+l.name + " ("+l.getNbHouses()+" house + 1 ");
                    if(l.getNbHouses()<4) {System.out.print("house");}
                    else {System.out.print("hotel");}
                    System.out.println(" for M"+l.rent[7]+").");
                    index[nb]=i;
                    nb++;
                }
            }
        }
        System.out.print("Your choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice>nb) {System.out.println("Invalid choice.");build();return;}
            land l = (land)getProperties(index[choice-1]);
            int sum = l.rent[7];
            removeMoney(sum);
            patrimony += sum/2;
            l.addHouse();
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            build();
            return;
        }
    }

    public Boolean majGroup(board b) {
        Boolean res = false;
        for (stations box : properties) {
            if(box instanceof land) {
                land l = (land)box;
                l.setCanBuild(false);
                if(l.getNbHouses()<5) { // si la propriete peut encore construire
                    if (((land)( b.getbox(l.getGroup()[0]) )).owner==this){ // si la premiere autre propriete du groupe appartient au joueur
                        if(l.getGroup().length > 1) { // si le groupe a 3 propriétés
                            if (((land)( b.getbox(l.getGroup()[1]) )).owner==this){ // si la deuxieme autre propriete du groupe appartient au joueur
                                if(((land)( b.getbox(l.getGroup()[0]) )).nbHouses >= l.nbHouses && ((land)( b.getbox(l.getGroup()[1]) )).nbHouses >= l.nbHouses) { // si les 3 propriétés ont le meme nombre de maisons
                                    l.setCanBuild(true);
                                    res = true;
                                }
                            }
                        }
                        else { // si le groupe a 2 propriétés
                            if(((land)( b.getbox(l.getGroup()[0]) )).nbHouses >= l.nbHouses) { // si les 2 propriétés ont le meme nombre de maisons
                                l.setCanBuild(true);
                                res = true;
                            }
                        }
                    }
                }
                else {l.setCanBuild(false);}
            }
        }
        return res;
    }

    private void inJail(player[] p) {
        System.out.println("(1) to pay M50.\n(2) to try to make a double.");
        if(this.jailCard) {System.out.println("(3) to use your jail card.");}
        try {
            int rep = Integer.parseInt(System.console().readLine())-1;
            if(rep == 1) {money-=50;jailTime = 0;move(p);return;}
            else if(rep == 2) {
                int roll = dice.roll();
                if (roll < 0) {
                    roll = -roll;
                    jailTime = 0;
                    forward(roll);
                    return;
                }
                else {this.jailTime--;return;}
            }
            else if (rep==3 && jailCard) {jailCard = false; jailTime = 0;System.out.println(name + " no longer have jail card"); move(p);return;}
            else {System.out.println("Invalid choice");}

        }
        catch (NumberFormatException e) {System.out.println("Invalid choice");}
        inJail(p);
        return;
    }

    public void move(player[] p) {
        if (jailTime > 0) { inJail(p); return; }
        forward(dice.roll(this));
    }
    
    public void printInfo() {
        System.out.println("\n"+name+"\nCurrent balance: "+"\u001B[9m"+"M"+"\u001B[0m"+money+"\nCurrent properties: ");
        for (stations box : properties) {
            System.out.println(" - "+box.name+(box.isMortgaged()? " (mortgaged)" : ""));
        }
    }

    private Boolean hasHouse() {
        for (stations box : properties) {
            if(box instanceof land) {
                land p = (land) box;
                if(p.getNbHouses()>0) {return true;}
            }
        }
        return false;
    }

    public int payOwner(int amount, String n) {
        int res = removeMoney(amount);
        System.out.println(this.name+" paid "+"\u001B[9m"+"M"+"\u001B[0m"+amount+" to "+n+".");
        return res;
    }
    
    private void mortgageProperties() {
        System.out.println("Select a property to mortgage:");
        int nb = 0;
        int[] index = new int[getNbProperties()];
        for (int i=0;i<getNbProperties();i++) {
            if(getProperties(i)!=null && !getProperties(i).isMortgaged()) {
                System.out.println("("+(nb+1)+") "+getProperties(i).name+" (M"+getProperties(i).mortgage(false)+").");index[nb]=i;nb++;
            }
        }
        System.out.print("Your choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice>nb) {System.out.println("Invalid choice.");mortgageProperties();return;}
            int sum = getProperties(index[choice-1]).mortgage(true);
            money += sum;
            patrimony -= sum;
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            mortgageProperties();
            return;
        }
    }
    private void sellHouse() {
        System.out.println("Select a property to sell house:");
        int nb = 0;
        int[] index = new int[getNbProperties()];
        for (int i=0;i<getNbProperties();i++) {
            if(getProperties(i) instanceof land) {
                land p = (land) getProperties(i);
                if(p.getNbHouses()>0) {System.out.println("("+(nb+1)+") "+p.name+" (M"+p.rent[7]/2+").");index[nb]=i;nb++;}
            }
        }
        System.out.print("Your choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice>nb) {System.out.println("Invalid choice.");sellHouse();return;}
            land p = (land) getProperties(index[choice-1]);
            p.retrieveHouse();
            money += p.rent[7]/2;
            patrimony -= p.rent[7]/2;
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            sellHouse();
            return;
        }
    }
    
    public int overcomeBankruptcy(int amount) {
        if(hasHouse()) {
            System.out.println("(1) You can sell houses.\n(2) You can mortgage properties.");
            if(System.console().readLine().equals("1")) {
                sellHouse();
                if(money<amount){return overcomeBankruptcy(amount);}else{return 1;}
            }
        }
        mortgageProperties();
        if(money<amount){
            return overcomeBankruptcy(amount);
        }
        return 1;
    }
    public int bankruptcy(int amount) {
        if(amount>patrimony) {return 0;}
        System.out.println(name+" you are bankrupt. You have to pay "+amount+".");
        return overcomeBankruptcy(amount);
    }

    public Boolean hasJailCard(player[] players) {
        for (player player : players) {
            if(player.jailCard){return true;}
        }
        return false;
    }
    public Boolean hasProperty(player[] players) {
        for (player player : players) {
            if(player.getNbProperties()>0){return true;}
        }
        return false;
    }

    public Boolean exchangeAccepted(player p, String add, String loss) {
        System.out.println(p.name+", do you accept giving your "+loss+" for "+add+"? (y/n)");
        return(System.console().readLine().equals("y"));
    }
    public void startExchange(player[] p) {
        System.out.println("Select someone to exchange with:");
        int nb = 0;
        int[] index = new int[p.length];
        for (int i=0;i<p.length;i++) {
            if(p[i]!=this && (p[i].jailCard || p[i].getNbProperties()>0)) {
                System.out.println("("+(nb+1)+") "+p[i].name+".");index[nb]=i;nb++;
            }
        }
        System.out.print("Your choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice>nb) {System.out.println("Invalid choice.");startExchange(p);return;}
            exchange(p[index[choice-1]]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            startExchange(p);return;
        }
    }
    public int GiveMoney() {
        System.out.print("How much money do you want to give? ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice>money) {System.out.println("You don't have enough money.");return GiveMoney();}
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return GiveMoney();
        }
    }
    public void GiveForJailCard(player p) {
        int nb = 1;
        int[] index = new int[getNbProperties()+1];
        System.out.println("What is you offer for "+p.name+"'s jail card.");
        System.out.println("("+(nb)+") Some money.");
        index[0]=0;
        for (box box : this.properties) {
            if(box!=null) {
                System.out.println("("+(nb+1)+") Your "+box.name+".");
                index[nb]=nb;
                nb++;
            }
        }
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if (choice>nb) {System.out.println("Invalid choice.");GiveForJailCard(p);return;}
            if (index[choice-1]==0){
                int money = GiveMoney();
                if(!exchangeAccepted(p, "M"+money, "jail card")) {return;}
                p.addMoney(money);
                this.money -= money;
            } else {
                if(!exchangeAccepted(p, this.getProperties(index[choice-1]-1).name, "jail card")) {return;}
                p.removeProperty(index[choice-1]-1);
            }
            this.jailCard = true;
            p.jailCard = false;
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");GiveForJailCard(p);return;
        }
    }
    public void GiveForProperty(player p, int i) {
        int nb = 1;
        int[] index = new int[getNbProperties()+1];
        System.out.println("What is you offer for "+p.name+"'s "+p.getProperties(i).name);
        System.out.println("("+(nb)+") Some money.");
        index[0]=0;
        for (box box : this.properties) {
            if(box!=null) {
                System.out.println("("+(nb+1)+") Your "+box.name+".");
                index[nb]=nb;
                nb++;
            }
        }

        try {
            int choice = Integer.parseInt(System.console().readLine());
            if (choice>nb) {System.out.println("Invalid choice.");GiveForProperty(p,i);return;}
            if (index[choice-1]==0){
                int money = GiveMoney();
                if(!exchangeAccepted(p, "M"+money, p.getProperties(i).name)) {return;}
                p.addMoney(money);
                this.money -= money;
            } else {
                if(!exchangeAccepted(p, this.getProperties(index[choice-1]-1).name, p.getProperties(i).name)) {return;}
                if(this.getProperties(index[choice-1]-1) instanceof land) {
                    land l = (land) this.getProperties(index[choice-1]-1);
                    l.retrieveHouse();
                    l.removeProperty(this);
                    p.addProperty(l);
                }
                else if(this.getProperties(index[choice-1]-1) instanceof stations) {
                    stations l = (stations) this.getProperties(index[choice-1]-1);
                    l.removeProperty(this);
                    p.addProperty(l);
                }
                // la propriété est ajoutée à l'autre joueur
            }
            if(p.getProperties(i) instanceof land) {
                land l = (land) p.getProperties(i);
                l.retrieveHouse();
                this.addProperty(l);
                l.removeProperty(p);
            }
            else if(p.getProperties(i) instanceof stations) {
                stations l = (stations) p.getProperties(i);
                l.removeProperty(p);
                this.addProperty(l);
            }
            // la propriété est ajoutée à moi
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");GiveForProperty(p,i);return;
        }
    }

    public void exchange(player p) {
        int nb = 0;
        int[] index = new int[p.getNbProperties()+1];
        System.out.println("Select something to exchange :");
        if (p.jailCard && !this.jailCard) {
            System.out.println("("+(nb+1)+") "+p.name+"'s jail card.");
            index[nb]=nb;
            nb++;
        }
        for (box box : p.properties) {
            if(box!=null) {
                System.out.println("("+(nb+1)+") "+p.name+"'s "+box.name+".");
                index[nb]=nb;
                nb++;
            }
        }
        System.out.print("Your choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice>nb) {System.out.println("Invalid choice.");exchange(p);return;}
            if (choice==1 && p.jailCard) {GiveForJailCard(p);return;}
            GiveForProperty(p,index[choice-1]);return;
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            exchange(p);
            return;
        }
    }
}