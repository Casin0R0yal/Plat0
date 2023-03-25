package Proj;

import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Affichage extends JFrame {
    public static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;

    // Create a JPanel to display the image
    JPanel panel;

    private Image image;
    private Image player1;
    private Image player2;
    private Image player3;
    private Image player4;    

    private int imageWidth;
    private int imageHeight;
    private int playerheight;
    private int playerwidth;
    int x = 780;
    int y = 610;
    int playerId = 0;
    int playerCount = 0;

    JButton playButton;
    JButton Buy;
    JButton Bid;
    JButton BidNumber;
    JButton Sell;

    int BidAmount;

    JLabel playerLabel1;
    JLabel playerLabel2;
    JLabel playerLabel3;
    JLabel playerLabel4;

    JLabel message;

    private String allproperties;
    private String all;
    private JLabel list1;
    private JLabel list2;
    private JLabel list3;
    private JLabel list4;

    public Affichage(int playerId, int playerCount) {
        panel = new JPanel(null) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
    
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(imageWidth, imageHeight);
            }
        };
        this.playerCount = playerCount;
        this.playerId = playerId;
        setTitle("Monopoly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);

        // Load the image from a file
        image = Toolkit.getDefaultToolkit().getImage("monopoly.png");
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);

        playerwidth = 30;
        playerheight = 30;
        message = new JLabel();
        message.setBounds(WINDOW_WIDTH/2-50,WINDOW_HEIGHT/2, 300, 20);
        message.setVisible(false);
        panel.add(message);
        allproperties = "$ case/nb of house: ";
        all = "player"+(playerId+1)+" with 1500"+allproperties;
        // Create a button and add it to the JPanel
        playButton = new JButton("Play");
        playButton.setBounds(WINDOW_WIDTH/2-50, WINDOW_HEIGHT/2-50, 100, 50);
        panel.add(playButton);

        Buy = new JButton("Buy");
        Buy.setBounds(WINDOW_WIDTH/2-100, WINDOW_HEIGHT/2-50, 100, 50);
        Buy.setVisible(false);
        panel.add(Buy);

        Bid = new JButton("Bid");
        Bid.setBounds(WINDOW_WIDTH/2, WINDOW_HEIGHT/2-50, 100, 50);
        Bid.setVisible(false);
        panel.add(Bid);

        BidNumber = new JButton("Ask a price");
        BidNumber.setBounds(WINDOW_WIDTH/2-50, WINDOW_HEIGHT/2, 100, 50);
        BidNumber.setVisible(false);
        panel.add(BidNumber);

        Sell = new JButton("Sell");
        Sell.setBounds(WINDOW_WIDTH/2-50, WINDOW_HEIGHT/2+50, 100, 50);
        Sell.setVisible(false);
        panel.add(Sell);

        if (Server.PLAYER_MAX > 0) {
            player1 = Toolkit.getDefaultToolkit().getImage("player.png");
            player1 = player1.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel1 = new JLabel(new ImageIcon(player1));
            playerLabel1.setBounds(x, y, playerwidth, playerheight);
            panel.add(playerLabel1);
            list1 = new JLabel("player1 with 1500$ case/nb of house: ");
            list1.setBounds(0, 670, 1000, 20);
            panel.add(list1);
        }
        if (Server.PLAYER_MAX > 1) {
            player2 = Toolkit.getDefaultToolkit().getImage("player.png");
            player2 = player2.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel2 = new JLabel(new ImageIcon(player2));
            playerLabel2.setBounds(x+5, y, playerwidth, playerheight);
            panel.add(playerLabel2);
            list2 = new JLabel("player2 with 1500$ case/nb of house: ");
            list2.setBounds(0, 690, 1000, 20);
            panel.add(list2);
        }
        if (Server.PLAYER_MAX > 2) {
            player3 = Toolkit.getDefaultToolkit().getImage("player.png");
            player3 = player3.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel3 = new JLabel(new ImageIcon(player3));
            playerLabel3.setBounds(x, y-5, playerwidth, playerheight);
            panel.add(playerLabel3);
            list3 = new JLabel("player3 with 1500$ case/nb of house: ");
            list3.setBounds(0, 20, 1000, 20);
            panel.add(list3);
        }
        if (Server.PLAYER_MAX > 3) {
            player4 = Toolkit.getDefaultToolkit().getImage("player.png");
            player4 = player4.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel4 = new JLabel(new ImageIcon(player4));
            playerLabel4.setBounds(x+5, y-5, playerwidth, playerheight);
            panel.add(playerLabel4);
            list4 = new JLabel("player4 with 1500$ case/nb of house: ");
            list4.setBounds(0, 40, 1000, 20);
            panel.add(list4);
        }

        // the player plays his turn
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (playerId == Server.currentPlayer) {
                        int temp = 0;
                        for (Player player : Server.players) {
                            if (!player.bankrupt) {
                                temp++;
                            }
                        }
                        if (temp == 1) {
                            for (Player player : Server.players) {
                                if (!player.bankrupt) {
                                    message.setText("Player " + (playerId + 1) + " won the game");
                                    message.setVisible(true);
                                    repaint();
                                }
                            }
                        }
                        else {
                            System.out.println("It is your turn");
                            Random rand = new Random();
                            int pos = rand.nextInt(5) + 1;
                            Server.positions.set(playerId, (Server.positions.get(playerId) + pos) % 40);
                            System.out.println(Server.positions.get(playerId));
                            ChangePosition();
                            for (Player player : Server.players) {
                                player.affichage.message.setVisible(false);
                                ActPlayers(player, playerId);
                            }
                            for (Case c : Board.cases) {
                                if (c.position == Server.positions.get(playerId)) {
                                    if (c.owner == null) {
                                        Buy.setVisible(true);
                                        Bid.setVisible(true);
                                        playButton.setVisible(false);
                                        repaint();
                                    }
                                    else {
                                        message.setText("You payed " + c.price + " to " + c.owner);
                                        message.setVisible(true);
                                        DisplayPaySomebody(c);
                                        repaint();
                                    }
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("It is not your turn");
                    }
                } catch (Exception e1) {
                    System.err.println("Error while sending message to server: " + e1.getMessage());
                }
            }
        });

        // the player can buy the property directly
        Buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Case c : Board.cases) {
                    if (c.position == Server.positions.get(playerId)) {
                        if (Server.players.get(playerId).sold >= c.price) {
                            Server.players.get(playerId).sold -= c.price;
                            Buy.setVisible(false);
                            Bid.setVisible(false);
                            playButton.setVisible(true);
                            repaint();
                            Server.currentPlayer = nextplayer();
                            if (c.owner == null) {
                                c.owner = Server.players.get(playerId);
                                System.out.println("You bought " + c.name);
                                AddCase(c, playerId);
                            }
                            else if (c.owner == Server.players.get(playerId)) {
                                System.out.println("You already own this property");
                            }
                            else {
                                System.out.println("This property is already owned by another player");
                            }
                        }
                    }
                }
            }
        });

        // when a case is not owned, the player can bid for it and the highest bidder wins
        Bid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Server.currentPlayer = nextplayer();
                Buy.setVisible(false);
                Bid.setVisible(false);
                for (Player player : Server.players) {
                    player.affichage.BidNumber.setVisible(true);
                    player.affichage.BidAmount = -1;
                    player.affichage.Buy.setVisible(false);
                    player.affichage.Bid.setVisible(false);
                    player.affichage.playButton.setVisible(false);
                }
                repaint();
            }
        });

        // the bid is open until all players have bid
        BidNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = -1;
                String input = JOptionPane.showInputDialog("Veuillez entrer un chiffre:");
                try {
                    number = Integer.parseInt(input);
                    if (number>-1 && number<Server.players.get(playerId).sold){
                        BidAmount = number;
                        int temp = 0;
                        for (Player player : Server.players) {
                            if (player.affichage.BidAmount == -1) {
                                temp = 1;
                            }
                        }
                        if (temp == 0) {
                            int temp2 = -1;
                            int temp3 = -1;
                            for (Player player : Server.players) {
                                player.affichage.BidNumber.setVisible(false);
                                player.affichage.playButton.setVisible(true);
                                if (player.affichage.BidAmount > temp2) {
                                    temp2 = player.affichage.BidAmount;
                                    temp3 = player.affichage.playerId;
                                }
                            }
                            for (Case c : Board.cases) {
                                if (c.position == Server.positions.get(Server.currentPlayer)) {
                                    c.owner = Server.players.get(temp3);
                                    System.out.println("player"+temp3+" bought " + c.name);
                                    Server.players.get(temp3).sold -= temp2;
                                    AddCase(c, temp3);
                                }
                            }
                        }
                    }
                    // Faites quelque chose avec le chiffre ici
                } catch (NumberFormatException ex) {
                    // Si l'utilisateur a entré une valeur qui n'est pas un chiffre, afficher une boîte de dialogue d'erreur
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un chiffre valide.");
                }
            }
        });

        // the player have to sell some properties to pay the rent
        Sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int temp = 0;
                for (Case c : Board.cases) {
                    if (c.owner == Server.players.get(playerId)) {
                        temp = 1;
                    }
                }
                // the player has lost if he has no properties
                if (temp == 0) {
                    System.out.println("You lose");
                    Server.players.get(playerId).bankrupt = true;
                    Server.currentPlayer = nextplayer();
                    Sell.setVisible(false);
                }
                else {
                    String input = JOptionPane.showInputDialog("Veuillez entrer un chiffre:");
                    try{
                        int number = Integer.parseInt(input);
                        if (Board.cases.get(number).owner == Server.players.get(playerId)) {
                            Server.players.get(playerId).sold += Board.cases.get(number).price;
                            Board.cases.get(number).owner = null;
                            RemoveCase(Board.cases.get(number), playerId);
                            DisplayPaySomebody(Board.casesell);
                            repaint();
                        }

                    } catch (NumberFormatException ex) {
                        // Si l'utilisateur a entré une valeur qui n'est pas un chiffre, afficher une boîte de dialogue d'erreur
                        JOptionPane.showMessageDialog(null, "Veuillez entrer un chiffre valide.");
                    }
                }
            }
        });


        

        // Add the JPanel to the JFrame and display it
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // change the position of the player
    public void ChangePosition() {
        int pos = Server.positions.get(playerId);
        int val = 0;
        if (pos != 0 && pos != 10 && pos != 20 && pos != 30) {
            val = 10;
            if (pos > 0 % 20 && pos < 10 % 20) {
                val += 10;
            }
        }
        if (pos < 10) {
            x = 780 - pos * 55 - val;
            y = 610;
        }
        else if (pos < 20) {
            x = 200;
            y = 610 - (pos - 10) * 48 - val;
        }
        else if (pos < 30) {
            x = 200 + (pos - 20) * 55 + val;
            y = 110;
        }
        else if (pos < 40) {
            x = 780;
            y = 110 + (pos - 30) * 48 + val;
        }
    }

    public int nextplayer() {
        int res = (playerId + 1) % playerCount;
        while (Server.players.get(res).bankrupt) {
            res = (res + 1) % playerCount;
        }
        return res;
    }

    //Display and change the sold of the owner and the visiter of the case price
    public void DisplayPaySomebody(Case c){
        if (Server.players.get(playerId).sold < c.price) {
            Board.casesell = c;
            Sell.setVisible(true);
            playButton.setVisible(false);
        }
        else {
            Sell.setVisible(false);
            playButton.setVisible(true);
            Server.players.get(c.owner.affichage.playerId).sold += c.price;
            Server.players.get(playerId).sold -= c.price;
            Server.players.get(c.owner.affichage.playerId).affichage.all = "player"+(c.owner.affichage.playerId+1)+" with "+Server.players.get(c.owner.affichage.playerId).sold+c.owner.affichage.allproperties;
            Server.players.get(playerId).affichage.all = "player"+(playerId+1)+" with "+Server.players.get(playerId).sold+allproperties;
            for (Player player : Server.players) {
                if (playerId == 0){
                    player.affichage.list1.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 1){
                    player.affichage.list2.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 2){
                    player.affichage.list3.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 3){
                    player.affichage.list4.setText(Server.players.get(playerId).affichage.all);
                }
                if (c.owner.affichage.playerId == 0){
                    player.affichage.list1.setText(Server.players.get(c.owner.affichage.playerId).affichage.all);
                }
                else if (c.owner.affichage.playerId == 1){
                    player.affichage.list2.setText(Server.players.get(c.owner.affichage.playerId).affichage.all);
                }
                else if (c.owner.affichage.playerId == 2){
                    player.affichage.list3.setText(Server.players.get(c.owner.affichage.playerId).affichage.all);
                }
                else if (c.owner.affichage.playerId == 3){
                    player.affichage.list4.setText(Server.players.get(c.owner.affichage.playerId).affichage.all);
                }
            }
            Server.currentPlayer = nextplayer();
        }
    }

    //Add and display the case of the owner for each player
    public void AddCase(Case c, int playerbuy) {
        Server.players.get(playerbuy).affichage.allproperties += " n"+c.position+"/0";
        Server.players.get(playerbuy).affichage.all = "player"+(playerbuy+1)+" with "+Server.players.get(playerbuy).sold+Server.players.get(playerbuy).affichage.allproperties;
        for (Player player : Server.players) {
            if (playerbuy == 0) {
                player.affichage.list1.setText(Server.players.get(playerbuy).affichage.all);
            }
            else if (playerbuy == 1) {
                player.affichage.list2.setText(Server.players.get(playerbuy).affichage.all);
            }
            else if (playerbuy == 2) {
                player.affichage.list3.setText(Server.players.get(playerbuy).affichage.all);
            }
            else if (playerbuy == 3) {
                player.affichage.list4.setText(Server.players.get(playerbuy).affichage.all);
            }
        }
    }

    //Remove and display the case of the owner for each player
    public void RemoveCase(Case c, int playersell) {
        Server.players.get(playersell).affichage.allproperties = Server.players.get(playersell).affichage.allproperties.replace(" n"+c.position+"/0", "");
        Server.players.get(playersell).affichage.all = "player"+(playersell+1)+" with "+Server.players.get(playersell).sold+Server.players.get(playersell).affichage.allproperties;
        for (Player player : Server.players) {
            if (playersell == 0) {
                player.affichage.list1.setText(Server.players.get(playersell).affichage.all);
            }
            else if (playersell == 1) {
                player.affichage.list2.setText(Server.players.get(playersell).affichage.all);
            }
            else if (playersell == 2) {
                player.affichage.list3.setText(Server.players.get(playersell).affichage.all);
            }
            else if (playersell == 3) {
                player.affichage.list4.setText(Server.players.get(playersell).affichage.all);
            }
        }
    }

    // when a player move, display it to all players
    public void ActPlayers(Player player, int playerId) {
        if (playerId == 0) {
            player.affichage.playerLabel1.setBounds(x, y, playerwidth, playerheight);
        }
        else if (playerId == 1) {
            player.affichage.playerLabel2.setBounds(x+5, y, playerwidth, playerheight);
        }
        else if (playerId == 2) {
            player.affichage.playerLabel3.setBounds(x, y-5, playerwidth, playerheight);
        }
        else if (playerId == 3) {
            player.affichage.playerLabel4.setBounds(x+5, y-5, playerwidth, playerheight);
        }
    }
}
