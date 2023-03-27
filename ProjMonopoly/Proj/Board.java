package Proj;

import java.util.List;
import java.util.ArrayList;

class Case {
    public int price;
    public String name;
    public Player owner = null;
    public int position;
    public String path;
    public int house;

    public Case(int price, String name, int position, String path) {
        this.price = price;
        this.name = name;
        this.position = position;
        this.path = path;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}

public class Board {
    public static List<Case> cases = new ArrayList<Case>();
    public static Case casesell;
    public Board() {
        cases.add(new Case(0, "Départ", 0, null));
        cases.add(new Case(60, "Boulevard de Belleville", 1, "image1.png"));
        cases.add(new Case(0, "Caisse de Communauté", 2, null));
        cases.add(new Case(60, "Rue Lecourbe", 3, "image3.png"));
        cases.add(new Case(200, "Impôts sur le revenu", 4, null));
        cases.add(new Case(200, "Gare Montparnasse", 5, "image5.png"));
        cases.add(new Case(100, "Rue de Vaugirard", 6, "image6.png"));
        cases.add(new Case(0, "Chance", 7, null));
        cases.add(new Case(100, "Rue de Courcelles", 8, "image8.png"));
        cases.add(new Case(120, "Avenue de la République", 9, "image9.png"));
        cases.add(new Case(0, "Prison", 10, null));
        cases.add(new Case(140, "Boulevard de la Villette", 11, "image11.png"));
        cases.add(new Case(150, "Compagnie de distribution d'électricité", 12, "image12.png"));
        cases.add(new Case(140, "Avenue de Neuilly", 13, "image13.png"));
        cases.add(new Case(160, "Rue de Paradis", 14, "image14.png"));
        cases.add(new Case(200, "Gare de Lyon", 15, "image15.png"));
        cases.add(new Case(180, "Avenue Mozart", 16, "image16.png"));
        cases.add(new Case(0, "Caisse de Communauté", 17, null));
        cases.add(new Case(180, "Boulevard Saint-Michel", 18, "image18.png"));
        cases.add(new Case(200, "Place Pigalle", 19, "image19.png"));
        cases.add(new Case(0, "Aller en prison", 20, null));
        cases.add(new Case(220, "Avenue Matignon", 21, "image21.png"));
        cases.add(new Case(0, "Chance", 22, null));
        cases.add(new Case(220, "Boulevard Malesherbes", 23, "image23.png"));
        cases.add(new Case(240, "Avenue Henri-Martin", 24, "image24.png"));
        cases.add(new Case(200, "Gare du Nord", 25, "image25.png"));
        cases.add(new Case(260, "Faubourg Saint-Honoré", 26, "image26.png"));
        cases.add(new Case(260, "Place de la Bourse", 27, "image27.png"));
        cases.add(new Case(150, "Compagnie de distribution des eaux", 28, "image28.png"));
        cases.add(new Case(280, "Rue de la Fayette", 29, "image29.png"));
        cases.add(new Case(0, "Parking gratuit", 30, null));
        cases.add(new Case(300, "Avenue de Breteuil", 31, "image31.png"));
        cases.add(new Case(300, "Avenue Foch", 32, "image32.png"));
        cases.add(new Case(0, "Caisse de Communauté", 33, null));
        cases.add(new Case(320, "Boulevard des Capucines", 34, "image34.png"));
        cases.add(new Case(200, "Gare Saint-Lazare", 35, "image35.png"));
        cases.add(new Case(0, "Chance", 36, null));
        cases.add(new Case(350, "Avenue des Champs-Élysées", 37, "image37.png"));
        cases.add(new Case(100, "Taxe de luxe", 38, null));
        cases.add(new Case(400, "Rue de la Paix", 39, "image39.png"));
    }
}
