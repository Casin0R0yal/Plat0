package Monopoly;

public enum color {
    YELLOW("\u001B[93m"),
    BROWN("\u001B[90m"),
    LIGHTBLUE("\u001B[34m"),
    PINK("\u001B[35m"),
    ORANGE("\u001B[33m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    DRAKBLUE("\u001B[94m"),
    WHITE("\u001B[0m");

    private String value;
    private color(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public static void getColor(box b) {
        try { System.out.print(((land)b).color.getValue()); }
        catch (Exception e) { System.out.print(color.WHITE.getValue()); }
    }
}