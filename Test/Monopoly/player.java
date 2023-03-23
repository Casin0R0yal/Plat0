package Monopoly;

class player {
    private String name;
    private int money;
    private int position;
    private box[] properties;
    private int nbProperties;
    private int jailTime;
    private int nbDoubles;
    private int nbTrainStations;
    private int nbUtilities;
    private Boolean jailCard;
    private int patrimony;

    public player(String Name) {
        this.name = Name;
        this.money = 500;
        this.position = 0;
        this.properties = new box[40];
        this.nbProperties = 0;
        this.jailTime = 0;
        this.nbDoubles = 0;
        this.nbTrainStations = 0;
        this.nbUtilities = 0;
        this.jailCard = false;
        this.patrimony = 0;
    }
    public String getName() { return this.name; }
    public int getMoney() { return this.money; }
    public int getPosition() { return this.position; }
    public box getProperties(int i) { return this.properties[i]; }
    public int getNbProperties() { return this.nbProperties; }
    public int getNbTrainStations() {return this.nbTrainStations;}
    public int getNbUtilities() {return this.nbUtilities;}
    public Boolean getJailCard() { return this.jailCard; }
    public land getLand(int i) { return (land)this.properties[i]; }
    public void addMoney(int amount) { this.money += amount; }
    public int setPosition(int position) { return this.position = position; }
    public void addProperty(land p) {nbProperties++;properties[nbProperties-1] = p;}
    public void addProperty(stations p) {nbProperties++;properties[nbProperties-1] = p;}
    public void setJailCard(Boolean b) { this.jailCard = b; }
    public void addPatrimony(int amount) {this.patrimony += amount;}
    public int retrieveMoney(int amount) { if(money<amount){bankruptcy(amount);}money-=amount; return amount; }
    public void removeProperty(int price) {nbProperties--;patrimony-=price/2;return;}
    public void ReduceJailTime() { this.jailTime--; }
    public void GoToJail() {this.position=10; this.jailTime=3; nbDoubles=0;}
    public void forward(int roll) {this.position += roll;if(this.position>39){this.position-=40;this.money+=200;}}
    
    public void play(board b) {
        int choice = 1;
        int[] corresp = {1,2,3};
        System.out.println(name + "\n(1) to roll the dices.");
        if(majGroup(b)) {choice++;System.out.println("("+choice+") to buy houses or Hotels.");}
        if(hasHouse()) {choice++;corresp[choice-1]=3;System.out.println("("+choice+") to sell houses or Hotels.");}
        try {
            int rep = Integer.parseInt(System.console().readLine())-1;
            if(rep == 0) {return;}
            else if(rep<choice && corresp[rep] == 2) {build();}
            else if(rep<choice && corresp[rep] == 3) {sellHoustel();}
            else {System.out.println("Invalid choice");}

        }
        catch (NumberFormatException e) {System.out.println("Invalid choice");}
        play(b);
    }
    
    private void sellHoustel() {
        int nb = 0;
        int[] index = new int[properties.length];
        System.out.println("You can sell houses or hotels on the following properties:");
        for (int i=0;i<properties.length;i++) {
            if(properties[i] instanceof land) {
                land l = (land)properties[i];
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
            land l = (land)properties[index[choice-1]];
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
        int[] index = new int[properties.length];
        System.out.println("You can build on the following properties:");
        for (int i=0;i<properties.length;i++) {
            if(properties[i] instanceof land) {
                land l = (land)properties[i];
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
            land l = (land)properties[index[choice-1]];
            int sum = l.rent[7];
            retrieveMoney(sum);
            patrimony += sum/2;
            l.addHouse();
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            build();
            return;
        }
    }

    private Boolean majGroup(board b) {
        Boolean res = false;
        for (box box : properties) {
            if(box instanceof land) {
                land l = (land)box;
                if(l.getNbHouses()<5) { // si la propriete peut encore construire
                    if (((land)( b.getbox(l.getGroup()[0]) )).owner==this){ // si la premiere autre propriete du groupe appartient au joueur
                        if(l.getGroup().length > 1) { // si le groupe a 3 propriétés
                            if (((land)( b.getbox(l.getGroup()[1]) )).owner==this){ // si la deuxieme autre propriete du groupe appartient au joueur
                                l.setCanBuild(true);
                                res = true;
                            }
                        }
                        else { // si le groupe a 2 propriétés
                            l.setCanBuild(true);
                            res = true;
                        }
                    }
                }
                else {l.setCanBuild(false);}
            }
        }
        return res;
    }

    private int inJail(player[] p) {
        int choice = 2;
        System.out.println("(1) to pay M50.\n(2) to try to make a double.");
        if(this.jailCard) {choice++;System.out.println("("+choice+") to use your jail card.");}
        try {
            int rep = Integer.parseInt(System.console().readLine())-1;
            if(rep == 1) {money-=50;jailTime = 0;return move(p);}
            else if(rep == 2) {
                int roll = dice.roll();
                if (roll < 0) {
                    roll = -roll;
                    jailTime = 0;
                    forward(roll);
                    return 0;
                }
                else {this.jailTime--;return 0;}
            }
            else if (rep==choice && rep == 3) {jailCard = false; jailTime = 0;System.out.println(name + " no longer have jail card"); return move(p);}
            else {System.out.println("Invalid choice");}

        }
        catch (NumberFormatException e) {System.out.println("Invalid choice");}
        inJail(p);
        return 0;
    }

    public int move(player[] p) {
        if (jailTime > 0) { return inJail(p); }
        int roll = dice.roll();
        if (roll < 0) {
            nbDoubles++;
            System.out.println(name + " rolled doubles for the "+nbDoubles+" time.");
            if (nbDoubles >= 3) { GoToJail(); return 0; }
            else {forward(-roll);System.out.println("You roll again.");return 1;}
        }
        nbDoubles = 0;
        forward(roll);
        return 0;
    }
    
    public void printInfo() {
        System.out.println("\n"+name+"\nCurrent balance: "+"\u001B[9m"+"M"+"\u001B[0m"+money+"\nCurrent properties: ");
        for (box box : properties) {
            System.out.println(" - "+box.name+(box.isMortgaged()? " (mortgaged)" : ""));
        }
    }

    private Boolean hasHouse() {
        for (box box : properties) {
            if(box instanceof land) {
                land p = (land) box;
                if(p.getNbHouses()>0) {return true;}
            }
        }
        return false;
    }

    public int payOwner(int amount, String n) {
        int res = retrieveMoney(amount);
        System.out.println(this.name+" paid "+"\u001B[9m"+"M"+"\u001B[0m"+amount+" to "+n+".");
        return res;
    }
    
    private void mortgageProperties() {
        System.out.println("Select a property to mortgage:");
        int nb = 0;
        int[] index = new int[properties.length];
        for (int i=0;i<properties.length;i++) {
            if(properties[i]!=null && !properties[i].isMortgaged()) {
                System.out.println("("+(nb+1)+") "+properties[i].name+" (M"+properties[i].mortgage(false)+").");index[nb]=i;nb++;
            }
        }
        System.out.print("Your choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice>nb) {System.out.println("Invalid choice.");mortgageProperties();return;}
            int sum = properties[index[choice-1]].mortgage(true);
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
        int[] index = new int[properties.length];
        for (int i=0;i<properties.length;i++) {
            if(properties[i] instanceof land) {
                land p = (land) properties[i];
                if(p.getNbHouses()>0) {System.out.println("("+(nb+1)+") "+p.name+" (M"+p.rent[7]/2+").");index[nb]=i;nb++;}
            }
        }
        System.out.print("Your choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            if(choice>nb) {System.out.println("Invalid choice.");sellHouse();return;}
            land p = (land) properties[index[choice-1]];
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
}