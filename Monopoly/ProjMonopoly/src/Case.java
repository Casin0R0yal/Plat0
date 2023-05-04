package src;

public class Case {
    public int price;
    public String name;
    public Player owner = null;
    public int position;
    public String path;
    public int house;
    public int[] friends;
    public int[] rent;
    public int housePrice;

    public Case(int price, String name, int position, int[] friends, int[] rent) {
        if (position >= 0 && position <= 10) {
            housePrice = 50;
        }
        else if (position >= 11 && position <= 20) {
            housePrice = 100;
        }
        else if (position >= 21 && position <= 30) {
            housePrice = 150;
        }
        else if (position >= 31 && position <= 40) {
            housePrice = 200;
        }
        this.price = price;
        this.name = name;
        this.position = position;
        this.friends = friends;
        this.rent = rent;
    }

    public int Price() {
        if (position == 5 || position == 15 || position == 25 || position == 35) {
            int pos = 0;
            for (int i = 0; i < friends.length; i++) {
                System.out.println(Board.cases.get(friends[i]-1).position);
                System.out.println(Board.cases.get(friends[i]-1).owner);
                if (Board.cases.get(friends[i]-1).owner == owner) {
                    pos++;
                }
            }
            return rent[pos];
        }
        else if (position == 12 || position == 28) {
            int pos = 0;
            for (int i = 0; i < friends.length; i++) {
                if (Board.cases.get(friends[i]-1).owner == owner) {
                    pos++;
                }
            }
            return rent[pos];
        }
        else {
            if (house == 0) {
                int pos = 1;
                for (int i = 0; i < friends.length; i++) {
                    if (Board.cases.get(friends[i]-1).owner != owner) {
                        pos = 0;
                    }
                }
                return rent[pos];
            }
            else {
                return rent[house+1];
            }
        }
    }
}