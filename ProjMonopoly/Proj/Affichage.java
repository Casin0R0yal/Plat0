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

    int BidAmount;

    JLabel playerLabel1;
    JLabel playerLabel2;
    JLabel playerLabel3;
    JLabel playerLabel4;

    JLabel message;

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
        all = "player"+(playerId+1)+" case/nb of house: ";
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

        if (Server.PLAYER_MAX > 0) {
            player1 = Toolkit.getDefaultToolkit().getImage("player.png");
            player1 = player1.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel1 = new JLabel(new ImageIcon(player1));
            playerLabel1.setBounds(x, y, playerwidth, playerheight);
            panel.add(playerLabel1);
            list1 = new JLabel("player1 case/nb of house: ");
            list1.setBounds(0, 670, 1000, 20);
            panel.add(list1);
        }
        if (Server.PLAYER_MAX > 1) {
            player2 = Toolkit.getDefaultToolkit().getImage("player.png");
            player2 = player2.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel2 = new JLabel(new ImageIcon(player2));
            playerLabel2.setBounds(x+5, y, playerwidth, playerheight);
            panel.add(playerLabel2);
            list2 = new JLabel("player2 case/nb of house: ");
            list2.setBounds(0, 690, 1000, 20);
            panel.add(list2);
        }
        if (Server.PLAYER_MAX > 2) {
            player3 = Toolkit.getDefaultToolkit().getImage("player.png");
            player3 = player3.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel3 = new JLabel(new ImageIcon(player3));
            playerLabel3.setBounds(x, y-5, playerwidth, playerheight);
            panel.add(playerLabel3);
            list3 = new JLabel("player3 case/nb of house: ");
            list3.setBounds(0, 20, 1000, 20);
            panel.add(list3);
        }
        if (Server.PLAYER_MAX > 3) {
            player4 = Toolkit.getDefaultToolkit().getImage("player.png");
            player4 = player4.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel4 = new JLabel(new ImageIcon(player4));
            playerLabel4.setBounds(x+5, y-5, playerwidth, playerheight);
            panel.add(playerLabel4);
            list4 = new JLabel("player4 case/nb of house: ");
            list4.setBounds(0, 40, 1000, 20);
            panel.add(list4);
        }

        // Add an ActionListener to the button to handle click events
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (playerId == Server.currentPlayer) {
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
                                    Server.currentPlayer = (Server.currentPlayer + 1) % playerCount;
                                    message.setText("You payed " + c.price + " to " + c.owner);
                                    message.setVisible(true);
                                    Server.players.get(playerId).sold -= c.price;
                                    c.owner.sold += c.price;
                                    repaint();
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
                            Server.currentPlayer = (Server.currentPlayer + 1) % playerCount;
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

        Bid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                                    AddCase(c, temp3);
                                }
                            }
                            Server.currentPlayer = (Server.currentPlayer + 1) % playerCount;
                        }
                    }
                    // Faites quelque chose avec le chiffre ici
                } catch (NumberFormatException ex) {
                    // Si l'utilisateur a entré une valeur qui n'est pas un chiffre, afficher une boîte de dialogue d'erreur
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un chiffre valide.");
                }
            }
        });


        

        // Add the JPanel to the JFrame and display it
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

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

    //Draw the case of the player
    public void AddCase(Case c, int playerbuy) {
        Server.players.get(playerbuy).affichage.all += " n"+c.position+"/0";
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

    // Paint all the Players on the board
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
