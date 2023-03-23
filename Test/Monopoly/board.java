package Monopoly;

class board {
    public static box[] board;
    public board() {
        board = new box[40];
        board[0] = new box(type.START, "Start", new action() {public void perform(board b, player[] p, int i) {p[i].addMoney(200);}});
        board[1] =  new land(type.LAND, "Boulevard de Belleville",  60, color.BROWN, new int[]{2,4,10,30,90,160,250,50,50,33}, new int[]{3}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[1],p,i); } });
        board[3] =  new land(type.LAND, "Rue Lecourbe",             60, color.BROWN, new int[]{4,8,20,60,180,320,450,50,50,33}, new int[]{1}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[3],p,i); } });
        board[6] =  new land(type.LAND, "Rue de Vaugirard",         100,color.LIGHTBLUE, new int[]{6,12,30,90,270,400,550,50,50,55}, new int[]{8,9}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[6],p,i); } });
        board[8] =  new land(type.LAND, "Rue de Courcelles",        100,color.LIGHTBLUE, new int[]{6,12,30,90,270,400,550,50,50,55}, new int[]{6,9}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[8],p,i); } });
        board[9] =  new land(type.LAND, "Avenue de la République",  120,color.LIGHTBLUE, new int[]{8,16,40,100,300,450,600,50,50,66}, new int[]{6,8}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[9],p,i); } });
        board[11] = new land(type.LAND, "Boulevard de la Villette", 140,color.PINK, new int[]{10,20,50,150,450,625,750,100,100,77}, new int[]{13,14}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[11],p,i); } });
        board[13] = new land(type.LAND, "Avenue de Neuilly",        140,color.PINK, new int[]{10,20,50,150,450,625,750,100,100,77}, new int[]{11,14}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[13],p,i); } });
        board[14] = new land(type.LAND, "Rue de Paradis",           160,color.PINK, new int[]{12,24,60,180,500,700,900,100,100,88}, new int[]{11,13}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[14],p,i); } });
        board[16] = new land(type.LAND, "Avenue Mozart",            180,color.ORANGE, new int[]{14,28,70,200,550,750,950,100,100,99}, new int[]{18,19}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[16],p,i); } });
        board[18] = new land(type.LAND, "Boulevard Saint-Michel",   180,color.ORANGE, new int[]{14,28,70,200,550,750,950,100,100,99}, new int[]{16,19}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[18],p,i); } });
        board[19] = new land(type.LAND, "Place Pigalle",            200,color.ORANGE, new int[]{16,32,80,220,600,800,1000,100,100,110}, new int[]{16,18}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[19],p,i); } });
        board[21] = new land(type.LAND, "Avenue Matignon",          220,color.RED, new int[]{18,36,90,250,700,875,1050,150,150,121}, new int[]{23,24}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[21],p,i); } });
        board[23] = new land(type.LAND, "Boulevard Malsherbes",     220,color.RED, new int[]{18,36,90,250,700,875,1050,150,150,121}, new int[]{21,24}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[23],p,i); } });
        board[24] = new land(type.LAND, "Avenue Henri-Martin",      240,color.RED, new int[]{20,40,100,300,750,925,1100,150,150,132}, new int[]{21,23}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[24],p,i); } });
        board[26] = new land(type.LAND, "Faubourg Saint-Honoré",    260,color.YELLOW, new int[]{22,44,110,330,800,975,1150,150,150,143}, new int[]{27,29}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[26],p,i); } });
        board[27] = new land(type.LAND, "Place de la Bourse",       260,color.YELLOW, new int[]{22,44,110,330,800,975,1150,150,150,143}, new int[]{26,29}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[27],p,i); } });
        board[29] = new land(type.LAND, "Rue la Fayette",           280,color.YELLOW, new int[]{24,48,120,360,850,1025,1200,150,150,154}, new int[]{26,27}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[29],p,i); } });
        board[31] = new land(type.LAND, "Avenue de Breteuil",       300,color.GREEN, new int[]{26,52,130,390,900,1100,1275,200,200,165}, new int[]{32,34}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[31],p,i); } });
        board[32] = new land(type.LAND, "Avenue Foch",              300,color.GREEN, new int[]{26,52,130,390,900,1100,1275,200,200,165}, new int[]{31,34}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[32],p,i); } });
        board[34] = new land(type.LAND, "Boulevard des Capucines",  320,color.GREEN, new int[]{28,56,150,450,1000,1200,1400,200,200,176}, new int[]{31,32}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[34],p,i); } });
        board[37] = new land(type.LAND, "Avenue des Champs-Élysées",350,color.DRAKBLUE, new int[]{35,70,175,500,1100,1300,1500,200,200,193}, new int[]{39}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[37],p,i); } });
        board[39] = new land(type.LAND, "Rue de la Paix",           400,color.DRAKBLUE, new int[]{50,100,200,600,1400,1700,2000,200,200,220}, new int[]{37}, new action() { public void perform(board b, player[] p, int i) { land.onProperty((land)board[39],p,i); } });
        board[5] = new stations(type.TRAINSTATIONS, "Gare Montparnasse", 200, color.WHITE, new int[]{25,50,100,200}, new action() { public void perform(board b, player[] p, int i) { stations.onProperty((stations)board[5],p,i); } });
        board[15] = new stations(type.TRAINSTATIONS, "Gare de Lyon", 200, color.WHITE, new int[]{25,50,100,200}, new action() { public void perform(board b, player[] p, int i) { stations.onProperty((stations)board[15],p,i); } });
        board[25] = new stations(type.TRAINSTATIONS, "Gare du Nord", 200, color.WHITE, new int[]{25,50,100,200}, new action() { public void perform(board b, player[] p, int i) { stations.onProperty((stations)board[25],p,i); } });
        board[35] = new stations(type.TRAINSTATIONS, "Gare Saint-Lazare", 200, color.WHITE, new int[]{25,50,100,200}, new action() { public void perform(board b, player[] p, int i) { stations.onProperty((stations)board[35],p,i); } });
        board[12] = new stations(type.COMPANIES, "Compagnie de distribution d'électricité", 150, color.WHITE, new int[]{4,10}, new action() { public void perform(board b, player[] p, int i) { stations.onProperty((stations)board[12],p,i); } });
        board[28] = new stations(type.COMPANIES, "Compagnie de distribution des eaux", 150, color.WHITE, new int[]{4,10}, new action() { public void perform(board b, player[] p, int i) { stations.onProperty((stations)board[28],p,i); } });
        board[2]  = new box(type.COMMISSION, "Caisse de Communauté", new action() { public void perform(board b, player[] p, int i) {luck x = new luck(); x.onLuck(b,p,i); } });
        board[17] = new box(type.COMMISSION, "Caisse de Communauté", new action() { public void perform(board b, player[] p, int i) {luck x = new luck(); x.onLuck(b,p,i); } });
        board[33] = new box(type.COMMISSION, "Caisse de Communauté", new action() { public void perform(board b, player[] p, int i) {luck x = new luck(); x.onLuck(b,p,i); } });
        board[7]  = new box(type.LUCK, "Chance", new action() { public void perform(board b, player[] p, int i) {luck x = new luck(); x.onLuck(b,p,i); } });
        board[22] = new box(type.LUCK, "Chance", new action() { public void perform(board b, player[] p, int i) {luck x = new luck(); x.onLuck(b,p,i); } });
        board[36] = new box(type.LUCK, "Chance", new action() { public void perform(board b, player[] p, int i) {luck x = new luck(); x.onLuck(b,p,i); } });
        board[4] = new box(type.TAXES, "Impôts sur le revenu", new action() { public void perform(board b, player[] p, int i) { p[i].retrieveMoney(200); } });
        board[38] = new box(type.TAXES, "Impôts sur le revenu", new action() { public void perform(board b, player[] p, int i) { p[i].retrieveMoney(200); } });
        board[10] = new box(type.PARC, "Parking Gratuit", new action() {public void perform(board b, player[] p, int i){}});
        board[20] = new box(type.PARC, "Parking Gratuit", new action() {public void perform(board b, player[] p, int i){}});
    
        board[30] = new box(type.JAIL, "Allez en prison", new action() { public void perform(board b, player[] p, int i) { p[i].GoToJail(); } });
    }

    public String getName(int i) { return board[i].name; }
    public box getbox(int i) { return board[i]; }

    private static char isPlayerHere(int i, player[] p) {
        char[] c = new char[] {'❶','❷','❸','❹','❺','❻','❼','❽','❾','❿'};
        for(int j=0; j < p.length; j++) {
            if (p[j].getPosition() == i){return c[j];}
        }
        if (board[i] instanceof land && ((land)board[i]).owner != null) {return '▪';}
        if (board[i] instanceof stations && ((stations)board[i]).owner != null) {return '▪';}
        return '▢';
    }
    public static void printBoard(player[] p) {
        System.out.print("\033[H\033[2J");
        
        int max = 0; for (player x : p) {max = Math.max(max, x.getNbProperties());}
        int max2 =0; for (player x : p) {for (int j=0;j<x.getNbProperties();j++) {max2 = Math.max(max2, x.getProperties(j).name.length());}}max2++;
        for (player x : p) {
        System.out.print("\u001B[4m"+x.getName()+"\u001B[0m"+" (M"+x.getMoney()+")");
            for(int i=(x.getName()+x.getMoney()).length();i<max2;i++) {System.out.print(" ");}
            System.out.print("     ");
        }
        // System.out.println();
        for (int i=0; i < max; i++) {
            System.out.println();
            for (player x : p) {
                if (x.getNbProperties() > i) {
                    String aff = '-'+x.getProperties(i).name+(x.getProperties(i).isMortgaged()? " (mortgaged)" : "");System.out.print(aff);
                    for(int j=aff.length()-3;j<=max2;j++) {System.out.print(" ");}
                    System.out.print("     ");
                } else {
                    for(int j=-3;j<=max2;j++) {System.out.print(" ");}
                    System.out.print("     ");
                }
            }
        }
        System.out.println("\n");

        for (int i=0; i < 11; i++) {
            color.getColor(board[20+i]);
            System.out.print(isPlayerHere(20+i,p)+" ");
        }
        System.out.println();
        for (int i=0; i < 9; i++) {
            color.getColor(board[19-i]);
            System.out.print(isPlayerHere(19-i,p)+"                   ");
            color.getColor(board[31+i]);
            System.out.print(isPlayerHere(31+i,p)+"\n");
        }
        for (int i=0; i < 11; i++) {
            color.getColor(board[10-i]);
            System.out.print(isPlayerHere(10-i,p)+" ");
        }
        color.getColor(board[0]);
        System.out.println("\n");
    }
}