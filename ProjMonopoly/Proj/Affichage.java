package Proj;

import java.util.Random;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Affichage extends JFrame {
    public static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;
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
    JLabel playerLabel1;
    JLabel playerLabel2;
    JLabel playerLabel3;
    JLabel playerLabel4;

    private String all;
    private JLabel list1;
    private JLabel list2;
    private JLabel list3;
    private JLabel list4;

    // Create a JPanel to display the image
    JPanel panel = new JPanel() {
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

    public Affichage(int playerId, int playerCount) {
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

        all = "player"+playerId+" case/nb of house/price: ";
        // Create a button and add it to the JPanel
        playButton = new JButton("Play");
        panel.add(playButton);

        Buy = new JButton("Buy");
        panel.add(Buy);

        if (Server.PLAYER_MAX > 0) {
            player1 = Toolkit.getDefaultToolkit().getImage("player.png");
            player1 = player1.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel1 = new JLabel(new ImageIcon(player1));
            panel.add(playerLabel1);
            list1 = new JLabel("player1 case/nb of house/price: ");
            panel.add(list1);
        }
        if (Server.PLAYER_MAX > 1) {
            player2 = Toolkit.getDefaultToolkit().getImage("player.png");
            player2 = player2.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel2 = new JLabel(new ImageIcon(player2));
            panel.add(playerLabel2);
            list2 = new JLabel("player2 case/nb of house/price: ");
            panel.add(list2);
        }
        if (Server.PLAYER_MAX > 2) {
            player3 = Toolkit.getDefaultToolkit().getImage("player.png");
            player3 = player3.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel3 = new JLabel(new ImageIcon(player3));
            panel.add(playerLabel3);
            list3 = new JLabel("player3 case/nb of house/price: ");
            panel.add(list3);
        }
        if (Server.PLAYER_MAX > 3) {
            player4 = Toolkit.getDefaultToolkit().getImage("player.png");
            player4 = player4.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel4 = new JLabel(new ImageIcon(player4));
            panel.add(playerLabel4);
            list4 = new JLabel("player4 case/nb of house/price: ");
            panel.add(list4);
        }
        
        // Set the position of the button to the center of the JPanel
        playButton.setBounds(panel.getWidth()/2 - 50, panel.getHeight()/2 - 25, 100, 50);
        Buy.setBounds(panel.getWidth()/2 - 50, panel.getHeight()/2 - 25, 0, 0);
        
        // Add a ComponentListener to the JFrame to listen for componentResized events
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Resize the image to match the new size of the panel
                image = image.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
                imageWidth = image.getWidth(null);
                imageHeight = image.getHeight(null);
                
                // Reposition the button to the center of the panel
                playButton.setBounds(panel.getWidth()/2 - 50, panel.getHeight()/2 - 25, 100, 50);
                Buy.setBounds(panel.getWidth()/2 - 50, panel.getHeight()/2 - 25, 0, 0);
                if (Server.PLAYER_MAX > 0) {
                    list1.setBounds(170, 670, 500, 20);
                    playerLabel1.setBounds(x, y, playerwidth, playerheight);
                }
                if (Server.PLAYER_MAX > 1) {
                    list2.setBounds(170, 690, 500, 20);
                    playerLabel2.setBounds(x+5, y, playerwidth, playerheight);
                }
                if (Server.PLAYER_MAX > 2) {
                    list3.setBounds(170, 20, 500, 20);
                    playerLabel3.setBounds(x, y-5, playerwidth, playerheight);
                }
                if (Server.PLAYER_MAX > 3) {
                    list4.setBounds(170, 40, 500, 20);
                    playerLabel4.setBounds(x+5, y-5, playerwidth, playerheight);
                }
                repaint();
            }
        });

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
                            ActPlayers(player, playerId);
                        }
                        Buy.setBounds(panel.getWidth()/2 - 50, panel.getHeight()/2 - 25, 100, 50);
                        playButton.setBounds(panel.getWidth()/2 - 50, panel.getHeight()/2 - 25, 0, 0);
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
                playButton.setBounds(panel.getWidth()/2 - 50, panel.getHeight()/2 - 25, 100, 50);
                Buy.setBounds(panel.getWidth()/2 - 50, panel.getHeight()/2 - 25, 0, 0);
                Server.currentPlayer = (Server.currentPlayer + 1) % playerCount;
                for (Case c : Board.cases) {
                    if (c.position == Server.positions.get(playerId)) {
                        if (c.owner == null) {
                            c.owner = Server.players.get(playerId);
                            System.out.println("You bought " + c.name);
                            AddCase(c);
                            Server.players.get(playerId).number += 1;
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
        });

        // Add the JPanel to the JFrame and display it
        add(panel);
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
    public void AddCase(Case c) {
        all += " n"+c.position+"/0/"+c.price;
        for (Player player : Server.players) {
            if (playerId == 0) {
                player.affichage.list1.setText(all);
            }
            else if (playerId == 1) {
                player.affichage.list2.setText(all);
            }
            else if (playerId == 2) {
                player.affichage.list3.setText(all);
            }
            else if (playerId == 3) {
                player.affichage.list4.setText(all);
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
