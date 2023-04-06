public class Card {

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_WHITE = "\u001B[37m";


    private final String color;
    private final int value;
    public boolean iskip;
    public final boolean is_joker;

    public final boolean is_4_joker;
    public final boolean isplus2;
    public Card(String color, int value, boolean is_joker, boolean is_4_joker, boolean isplus2, boolean iskip)
    {
        this.iskip = iskip;
        this.color = color;
        this.value = value;
        this.is_joker = is_joker;
        this.is_4_joker = is_4_joker;
        this.isplus2 = isplus2;
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }
    public void displaycard(){


        switch (this.getColor()) {

            case "red" -> System.out.print(ANSI_RED_BACKGROUND+ TEXT_BLACK);

            case "green" -> System.out.print(ANSI_GREEN_BACKGROUND + TEXT_BLACK);
            case "yellow" -> System.out.print(ANSI_YELLOW_BACKGROUND + TEXT_BLACK);
            case "blue" -> System.out.print(ANSI_BLUE_BACKGROUND + TEXT_BLACK);
        }
        if (this.is_joker)
            System.out.print(ANSI_BLACK_BACKGROUND+TEXT_GREEN+"Joker");

        else if (this.is_4_joker)
            System.out.print("4 Joker");
        else if (this.isplus2)
            System.out.print("Plus 2");
        else if (this.iskip)
            System.out.print("Skip");
        else
            System.out.print(this.getValue());

        System.out.print(TEXT_RESET);
        System.out.print(" ");

    }

}
