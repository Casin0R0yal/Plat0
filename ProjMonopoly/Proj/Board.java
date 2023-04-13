package Proj;

import java.util.List;
import java.util.ArrayList;

public class Board {
    public static List<Case> cases = new ArrayList<Case>();
    public static Case casesell;
    public static int publicparking = 0;
    public Board() {
        cases.add(new Case(0, "Départ", 0, null, null));
        int[] friends = new int[]{3};
        int[] rent = {2, 4, 10, 30, 90, 160, 250};
        cases.add(new Case(60, "Boulevard de Belleville", 1, friends, rent));
        cases.add(new Case(0, "Caisse de Communauté", 2, null, null));
        friends = new int[]{1};
        rent = new int[]{4, 8, 20, 60, 180, 320, 450};
        cases.add(new Case(60, "Rue Lecourbe", 3, friends, rent));
        friends = new int[]{15, 25, 35};
        rent = new int[]{25, 50, 100, 200};
        cases.add(new Case(200, "Gare Montparnasse", 5, friends, rent));
        friends = new int[]{8, 9};
        rent = new int[]{6, 12, 30, 90, 270, 400, 550};
        cases.add(new Case(100, "Rue de Vaugirard", 6, friends, rent));
        cases.add(new Case(0, "Chance", 7, null, null));
        friends = new int[]{6, 9};
        cases.add(new Case(100, "Rue de Courcelles", 8, friends, rent));
        friends = new int[]{6, 8};
        rent = new int[]{8, 16, 40, 100, 300, 450, 600};
        cases.add(new Case(120, "Avenue de la République", 9, friends, rent));
        cases.add(new Case(0, "Visite de Prison", 10, null, null));
        friends = new int[]{13, 14};
        rent = new int[]{10, 20, 50, 150, 450, 625, 750};
        cases.add(new Case(140, "Boulevard de la Villette", 11, friends, rent));
        friends = new int[]{28};
        rent = new int[]{4, 10};
        cases.add(new Case(150, "Compagnie de distribution d'électricité", 12, friends, rent));
        friends = new int[]{11, 14};
        rent = new int[]{10, 20, 50, 150, 450, 625, 750};
        cases.add(new Case(140, "Avenue de Neuilly", 13, friends, rent));
        friends = new int[]{11, 13};
        rent = new int[]{12, 24, 60, 180, 500, 700, 900};
        cases.add(new Case(160, "Rue de Paradis", 14, friends, rent));
        friends = new int[]{5, 25, 35};
        rent = new int[]{25, 50, 100, 200};
        cases.add(new Case(200, "Gare de Lyon", 15, friends, rent));
        friends = new int[]{18, 19};
        rent = new int[]{14, 28, 70, 200, 550, 750, 950};
        cases.add(new Case(180, "Avenue Mozart", 16, friends, rent));
        cases.add(new Case(0, "Caisse de Communauté", 17, null, null));
        friends = new int[]{16, 19};
        rent = new int[]{14, 28, 70, 200, 550, 750, 950};
        cases.add(new Case(180, "Boulevard Saint-Michel", 18, friends, rent));
        friends = new int[]{16, 18};
        rent = new int[]{16, 32, 80, 220, 600, 800, 1000};
        cases.add(new Case(200, "Place Pigalle", 19, friends, rent));
        cases.add(new Case(0, "Parc Gratuit", 20, null, null));
        friends = new int[]{23, 24};
        rent = new int[]{18, 36, 90, 250, 700, 875, 1050};
        cases.add(new Case(220, "Avenue Matignon", 21, friends, rent));
        cases.add(new Case(0, "Chance", 22, null, null));
        friends = new int[]{21, 24};
        rent = new int[]{18, 36, 90, 250, 700, 875, 1050};
        cases.add(new Case(220, "Boulevard Malesherbes", 23, friends, rent));
        friends = new int[]{21, 23};
        rent = new int[]{20, 40, 100, 300, 750, 925, 1100};
        cases.add(new Case(240, "Avenue Henri-Martin", 24, friends, rent));
        friends = new int[]{5, 15, 35};
        rent = new int[]{25, 50, 100, 200};
        cases.add(new Case(200, "Gare du Nord", 25, friends, rent));
        friends = new int[]{27, 29};
        rent = new int[]{22, 44, 110, 330, 800, 975, 1150};
        cases.add(new Case(260, "Faubourg Saint-Honoré", 26, friends, rent));
        friends = new int[]{26, 29};
        rent = new int[]{22, 44, 110, 330, 800, 975, 1150};
        cases.add(new Case(260, "Place de la Bourse", 27, friends, rent));
        friends = new int[]{12};
        rent = new int[]{4, 10};
        cases.add(new Case(150, "Compagnie de distribution des eaux", 28, friends, rent));
        friends = new int[]{26, 27};
        rent = new int[]{24, 48, 120, 360, 850, 1025, 1200};
        cases.add(new Case(280, "Rue de la Fayette", 29, friends, rent));
        cases.add(new Case(0, "Allez en Prison", 30, null, null));
        friends = new int[]{32, 34};
        rent = new int[]{26, 52, 130, 390, 900, 1100, 1275};
        cases.add(new Case(300, "Avenue de Breteuil", 31, friends, rent));
        friends = new int[]{31, 34};
        rent = new int[]{26, 52, 130, 390, 900, 1100, 1275};
        cases.add(new Case(300, "Avenue Foch", 32, friends, rent));
        cases.add(new Case(0, "Caisse de Communauté", 33, null, null));
        friends = new int[]{31, 32};
        rent = new int[]{28, 56, 150, 450, 1000, 1200, 1400};
        cases.add(new Case(320, "Boulevard des Capucines", 34, friends, rent));
        friends = new int[]{5, 15, 25};
        rent = new int[]{25, 50, 100, 200};
        cases.add(new Case(200, "Gare Saint-Lazare", 35, friends, rent));
        cases.add(new Case(0, "Chance", 36, null, null));
        friends = new int[]{39};
        rent = new int[]{35, 70, 175, 500, 1100, 1300, 1500};
        cases.add(new Case(350, "Avenue des Champs-Élysées", 37, friends, rent));
        cases.add(new Case(0, "Taxe de Luxe", 38, null, null));
        friends = new int[]{37};
        rent = new int[]{50, 100, 200, 600, 1400, 1700, 2000};
        cases.add(new Case(400, "Rue de la Paix", 39, friends, rent));
    }

    public static boolean HouseCouldBeBought() {
        int nbproperty = 0;
        for (Case c : cases) {
            if (c.owner != null) {
                nbproperty++;
            }
        }
        if (nbproperty > 6) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean PlayerCanBuyHouse(int property, int nbhouse) {
        if (property == 5 || property == 15 || property == 25 || property == 35) {
            return false;
        }
        if (property == 12 || property == 28) {
            return false;
        }
        int temp = Board.cases.get(property).house;
        for (int i = 0; i < Board.cases.get(property).friends.length; i++) {
            temp += Board.cases.get(Board.cases.get(property).friends[i]).house;
        }
        if ((temp+nbhouse) % (Board.cases.get(property).friends.length+1) > 5){
            return false;
        }
        else if (Server.players.get(Server.currentPlayer).sold < nbhouse*Board.cases.get(property).housePrice){
            return false;
        }
        else {
            return true;
        }
    }

    public static void BuyHouse(int property, int nbhouse) {
        Server.players.get(Server.currentPlayer).sold -= nbhouse*Board.cases.get(property).housePrice;
        while (nbhouse > 0) {
            int acthouse = Board.cases.get(property).house;
            boolean temp = false;
            for (int i = 0; i < Board.cases.get(property).friends.length; i++) {
                if (Board.cases.get(Board.cases.get(property).friends[i]).house < acthouse && nbhouse > 0) {
                    Server.players.get(Server.currentPlayer).affichage.ChangeNumberHouse(Board.cases.get(Board.cases.get(property).friends[i]));
                    Board.cases.get(Board.cases.get(property).friends[i]).house++;
                    nbhouse--;
                    temp = true;
                }
            }
            if (temp == false) {
                Server.players.get(Server.currentPlayer).affichage.ChangeNumberHouse(Board.cases.get(property));
                Board.cases.get(property).house++;
                nbhouse--;
            }
        }
    }
}
