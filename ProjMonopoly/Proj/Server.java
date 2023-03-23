package Proj;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    static List<Player> players = new ArrayList<Player>();
    static List<Integer> positions = new ArrayList<Integer>();
    static int PLAYER_MAX = 1;
    static int currentPlayer = 0;
    static Board board = new Board();

    public void Connection() throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        int playerCount = 0;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(-1);
        }
        int temp = 0;
        while (listening) {
            if (playerCount < PLAYER_MAX) {
                ThreadPlayer Player = new ThreadPlayer(serverSocket.accept(), playerCount, PLAYER_MAX);
                new Thread(Player).start();
                playerCount++;
            }
            else if (temp == 0) {
                System.out.println("Maximum number of players reached.");
                temp++;
            }
        }

        serverSocket.close();
    }

    public class ThreadPlayer implements Runnable {
        private Socket socket = null;
        private int playerId = -1;
        private int playerCount = -1;
        private final Object lock = new Object();
        int x = 0;
        int y = 0;

        public ThreadPlayer(Socket socket, int playerId, int playerCount) {
            this.playerCount = playerCount;
            this.socket = socket;
            this.playerId = playerId;
            players.add(new Player(new Affichage(playerId, PLAYER_MAX)));
            positions.add(0);
            if (playerId+1 == playerCount) {
                for (int i = 0; i < players.size(); i++) {
                    System.out.println("Player " + i + " is at position " + positions.get(i));
                }
            }
        }
    
        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
                String inputLine, outputLine;
                while ((inputLine = in.readLine()) != null) {
                    synchronized (lock) {
                        if (playerId == currentPlayer) {
                            System.out.println("Player " + playerId + " sent: " + inputLine);
                            outputLine = "It's your turn.";
                            currentPlayer = (currentPlayer + 1) % playerCount;
                        }
                        else {
                            System.out.println("Player " + playerId + " sent: " + inputLine+ " but it's not his turn.");
                            outputLine = "It's not your turn.";
                        }
                    }
                    out.println(outputLine);
                }
    
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
           }
        }   
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.Connection();
    }
}
