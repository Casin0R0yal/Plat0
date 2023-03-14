package Proj;

import java.util.Random;

public class Monopoly {
    public enum GameStatus {
        PLAYING,
        GAME_OVER
    }

    private static GameStatus status;
    private static int[] playerPositions;
    private static int currentPlayer;

    public Monopoly(int numPlayers) {
        status = GameStatus.PLAYING;
        playerPositions = new int[numPlayers];
        currentPlayer = 0;
    }

    public static void takeTurn(int playerId) {
        // Roll the dice
        Random rand = new Random();
        int roll = rand.nextInt(6) + 1;
        System.out.println("Player " + playerId + " rolled a " + roll + "!");

        // Move the player
        movePlayer(playerId, roll);
    }

    public static GameStatus getStatus() {
        return status;
    }

    public static int getPlayerPosition(int playerId) {
        return playerPositions[playerId];
    }

    public static void movePlayer(int playerId, int roll) {
        int newPosition = playerPositions[playerId] + roll;
        if (newPosition >= 40) {
            newPosition -= 40;
        }
        playerPositions[playerId] = newPosition;

        // Check for passing Go
        if (newPosition < playerPositions[playerId] && playerId == currentPlayer) {
            System.out.println("Player " + playerId + " passed Go and collected $200!");
        }

        // Check for landing on a chance space
        if (newPosition == 7 || newPosition == 22 || newPosition == 36) {
            System.out.println("Player " + playerId + " landed on a Chance space!");
            drawChanceCard(playerId);
        }

        // Check for landing on a property
        if (newPosition == 1 || newPosition == 3 || newPosition == 6) {
            System.out.println("Player " + playerId + " landed on a property!");
        }

        // Move on to the next player
        currentPlayer = (currentPlayer + 1) % playerPositions.length;
    }

    private static void drawChanceCard(int playerId) {
        // Draw a random chance card
        Random rand = new Random();
        int cardIndex = rand.nextInt(6);

        switch (cardIndex) {
            case 0:
                System.out.println("Advance to Go (Collect $200)!");
                playerPositions[playerId] = 0;
                break;
            case 1:
                System.out.println("Bank error in your favor. Collect $200.");
                break;
            case 2:
                System.out.println("Get out of Jail Free. This card may be kept until needed or sold.");
                break;
            case 3:
                System.out.println("Go to Jail. Go directly to jail, do not pass Go, do not collect $200.");
                playerPositions[playerId] = 10;
                break;
            case 4:
                System.out.println("Pay poor tax of $15.");
                break;
            case 5:
                System.out.println("Advance to St. Charles Place. If you pass Go, collect $200.");
                playerPositions[playerId] = 11;
                break;
        }
    }
}
