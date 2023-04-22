package src;

import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
    JButton Jail;
    JButton Echanger;
    JButton EchangerButton;
    JButton House;
    JButton HouseButton;
    JButton Cancel;

    int injail = 0;
    int BidAmount;
    int paytobank = 0;

    JLabel playerLabel1;
    JLabel playerLabel2;
    JLabel playerLabel3;
    JLabel playerLabel4;

    JLabel message;
    JLabel message2;

    String allproperties;
    String all;
    JLabel list1;
    JLabel list2;
    JLabel list3;
    JLabel list4;

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
        image = Toolkit.getDefaultToolkit().getImage("images/monopoly.png");
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);

        playerwidth = 30;
        playerheight = 30;
        message = new JLabel();
        message.setBounds(WINDOW_WIDTH/2-50,WINDOW_HEIGHT/2-150, 300, 20);
        message.setVisible(false);
        panel.add(message);
        message2 = new JLabel();
        message2.setBounds(WINDOW_WIDTH/2-50,WINDOW_HEIGHT/2-150, 300, 20);
        message2.setVisible(false);
        panel.add(message2);
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

        Jail = new JButton("Jail");
        Jail.setBounds(WINDOW_WIDTH/2-50, WINDOW_HEIGHT/2, 100, 50);
        Jail.setVisible(false);
        panel.add(Jail);

        Echanger = new JButton("Ask what you want");
        Echanger.setBounds(WINDOW_WIDTH/2-50, WINDOW_HEIGHT/2, 100, 50);
        Echanger.setVisible(false);
        panel.add(Echanger);

        EchangerButton = new JButton("Echanger");
        EchangerButton.setBounds(WINDOW_WIDTH/2, WINDOW_HEIGHT/2, 100, 50);
        EchangerButton.setVisible(false);
        panel.add(EchangerButton);

        House = new JButton("What house and how many?");
        House.setBounds(WINDOW_WIDTH/2-50, WINDOW_HEIGHT/2, 100, 50);
        House.setVisible(false);
        panel.add(House);

        HouseButton = new JButton("House");
        HouseButton.setBounds(WINDOW_WIDTH/2-100, WINDOW_HEIGHT/2, 100, 50);
        HouseButton.setVisible(false);
        panel.add(HouseButton);

        Cancel = new JButton("Cancel");
        Cancel.setBounds(WINDOW_WIDTH/2-50, WINDOW_HEIGHT/2+100, 100, 50);
        Cancel.setVisible(false);
        panel.add(Cancel);

        if (Server.PLAYER_MAX > 0) {
            player1 = Toolkit.getDefaultToolkit().getImage("images/player.png");
            player1 = player1.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel1 = new JLabel(new ImageIcon(player1));
            playerLabel1.setBounds(x, y, playerwidth, playerheight);
            panel.add(playerLabel1);
            list1 = new JLabel("player1 with 1500$ case/nb of house: ");
            list1.setBounds(0, 670, 1000, 20);
            panel.add(list1);
        }
        if (Server.PLAYER_MAX > 1) {
            player2 = Toolkit.getDefaultToolkit().getImage("images/boat.png");
            player2 = player2.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel2 = new JLabel(new ImageIcon(player2));
            playerLabel2.setBounds(x+5, y, playerwidth, playerheight);
            panel.add(playerLabel2);
            list2 = new JLabel("player2 with 1500$ case/nb of house: ");
            list2.setBounds(0, 690, 1000, 20);
            panel.add(list2);
        }
        if (Server.PLAYER_MAX > 2) {
            player3 = Toolkit.getDefaultToolkit().getImage("images/player.png");
            player3 = player3.getScaledInstance(playerwidth, playerheight, Image.SCALE_SMOOTH);
            playerLabel3 = new JLabel(new ImageIcon(player3));
            playerLabel3.setBounds(x, y-5, playerwidth, playerheight);
            panel.add(playerLabel3);
            list3 = new JLabel("player3 with 1500$ case/nb of house: ");
            list3.setBounds(0, 20, 1000, 20);
            panel.add(list3);
        }
        if (Server.PLAYER_MAX > 3) {
            player4 = Toolkit.getDefaultToolkit().getImage("images/player.png");
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
                    // if it is the player's turn
                    if (playerId == Server.currentPlayer) {
                        int temp = 0;
                        for (Player player : Server.players) {
                            System.out.println(player.sold);
                            if (!player.bankrupt) {
                                temp++;
                            }
                        }
                        // if there is only one player left
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
                            // if the player is in jail
                            if (Server.players.get(playerId).inJail && injail != 3) {
                                System.out.println("You are in jail");
                                message.setText("You are in jail");
                                message.setVisible(true);
                                Jail.setVisible(true);
                                playButton.setVisible(false);
                                House.setVisible(false);
                                Echanger.setVisible(false);
                                repaint();
                            }
                            else {
                                if (injail == 3) {
                                    message.setText("You are from too long in jail");
                                    message.setVisible(true);
                                    Server.players.get(playerId).inJail = false;
                                    injail = 0;
                                }
                                System.out.println("It is your turn");
                                Random rand = new Random();
                                int pos1 = rand.nextInt(5) + 1;
                                int pos2 = rand.nextInt(5) + 1;
                                if (pos1 == pos2 && Server.players.get(playerId).nbdoubles == 2) {
                                    Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
                                    message2.setText("You are in jail because 3 doubles in a row");
                                    message2.setVisible(true);
                                    Server.players.get(playerId).inJail = true;
                                    Server.positions.set(playerId, 10);
                                    Server.players.get(playerId).affichage.ChangePosition();
                                    for (Player player : Server.players) {
                                        player.affichage.ActPlayers(player, playerId);
                                    }
                                }
                                else {
                                    if (pos1 == pos2) {
                                        Server.players.get(playerId).nbdoubles++;
                                    }
                                    else {
                                        Server.players.get(playerId).nbdoubles = 0;
                                    }
                                    if (pos1+pos2+Server.positions.get(playerId) >= 40) {
                                        Server.players.get(playerId).sold += 200;
                                    }
                                    Server.positions.set(playerId, (Server.positions.get(playerId) + pos1 + pos2) % 40);
                                    System.out.println(Server.positions.get(playerId));
                                    ChangePosition();
                                    for (Player player : Server.players) {
                                        player.affichage.message.setVisible(false);
                                        player.affichage.message2.setVisible(false);
                                        ActPlayers(player, playerId);
                                    }
                                    for (Case c : Board.cases) {
                                        if (c.position == Server.positions.get(playerId)) {
                                            if (c.position == 30) {
                                                Server.positions.set(playerId, 10);
                                                ChangePosition();
                                                for (Player player : Server.players) {
                                                    ActPlayers(player, playerId);
                                                }
                                                Server.players.get(playerId).inJail = true;
                                                Server.currentPlayer = nextplayer();
                                                message.setText("You are in jail");
                                                message.setVisible(true);
                                                repaint();
                                            }
                                            else if (c.position == 7 || c.position == 22 || c.position == 36) {
                                                message.setText("You are on a chance card");
                                                message.setVisible(true);
                                                Server.luck.Action(playerId);
                                                for (Player player : Server.players) {
                                                    ActPlayers(player, playerId);
                                                }
                                            }
                                            else if (c.position == 2 || c.position == 17 || c.position == 33) {
                                                message.setText("You are on a community chest card");
                                                message.setVisible(true);
                                                Server.community.Action(playerId);
                                                for (Player player : Server.players) {
                                                    ActPlayers(player, playerId);
                                                }
                                            }
                                            else if (c.position == 38 || c.position == 4) {
                                                Board.publicparking += 100;
                                                paytobank = 100;
                                                if (c.position == 4) {
                                                    Board.publicparking += 100;
                                                    paytobank = 200;
                                                }
                                                message.setText("You payed " + paytobank + " to the bank");
                                                message.setVisible(true);
                                                DisplayPayBank(paytobank);
                                            }
                                            else if (c.position == 10 || c.position == 20 || c.position == 0) {
                                                if (c.position == 20) {
                                                    Server.players.get(playerId).sold += Board.publicparking;
                                                    Board.publicparking = 0;
                                                }
                                                Server.currentPlayer = nextplayer();
                                            }
                                            else {
                                                if (c.owner == null) {
                                                    Buy.setVisible(true);
                                                    Bid.setVisible(true);
                                                    playButton.setVisible(false);
                                                    House.setVisible(false);
                                                    Echanger.setVisible(false);
                                                    repaint();
                                                }
                                                else {
                                                    if (c.owner != Server.players.get(playerId)) {
                                                        message.setText("You payed " + c.Price() + " to " + c.owner);
                                                        message.setVisible(true);
                                                        DisplayPaySomebody(c);
                                                        repaint();
                                                    }
                                                    else {
                                                        message.setText("You are on your property");
                                                        message.setVisible(true);
                                                        repaint();
                                                        Server.currentPlayer = nextplayer();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("It is not your turn");
                    }
                } catch (Exception e1) {
                    System.err.println("Error  sending message to server: " + e1.getMessage());
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
                            DisplayPlay(playerId);
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
                DisplayHideAll();
                for (Player player : Server.players) {
                    player.affichage.BidNumber.setVisible(true);
                    player.affichage.BidAmount = -1;
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
                                if (player.affichage.playerId == playerId) {
                                    player.affichage.DisplayPlay(playerId);
                                }
                                else {
                                    player.affichage.playButton.setVisible(true);
                                }
                                if (player.affichage.BidAmount > temp2) {
                                    temp2 = player.affichage.BidAmount;
                                    temp3 = player.affichage.playerId;
                                }
                            }
                            int cur = Server.currentPlayer;
                            if (cur == 0) {
                                cur = Server.players.size() - 1;
                            }
                            else {
                                cur = cur - 1;
                            }
                            for (Case c : Board.cases) {
                                if (c.position == Server.positions.get(cur)) {
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
                            if (paytobank == 0) {
                                DisplayPaySomebody(Board.casesell);
                            }
                            else {
                                DisplayPayBank(paytobank);
                            }
                            repaint();
                        }

                    } catch (NumberFormatException ex) {
                        // Si l'utilisateur a entré une valeur qui n'est pas un chiffre, afficher une boîte de dialogue d'erreur
                        JOptionPane.showMessageDialog(null, "Veuillez entrer un chiffre valide.");
                    }
                }
            }
        });

        // the player is in jail, he can, pay the fine, use a card or roll the dice
        Jail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("1/pay 2/card 3/roll");
                try{
                    int number = Integer.parseInt(input);
                    if (number == 1) {
                        injail = 0;
                        Server.players.get(playerId).sold -= 50;
                        Server.players.get(playerId).inJail = false;
                    }
                    else if (number == 2) {
                        injail = 0;
                        Server.players.get(playerId).inJail = false;
                    }
                    else if (number == 3) {
                        Random rand = new Random();
                        int dice1 = rand.nextInt(5) + 1;
                        int dice2 = rand.nextInt(5) + 1;
                        if (dice1 == dice2) {
                            message.setText("You rolled " + dice1 + " and " + dice2 + " you are free to replay");
                            message.setVisible(true);
                            Server.players.get(playerId).inJail = false;
                            injail = 0;
                        }
                        else {
                            injail++;
                            message.setText("You rolled " + dice1 + " and " + dice2 + " you are still in jail");
                            message.setVisible(true);
                            Server.players.get(playerId).inJail = true;
                            Server.currentPlayer = nextplayer();
                        }
                    }
                    Jail.setVisible(false);
                    DisplayPlay(playerId);
                } catch (NumberFormatException ex) {
                    // Si l'utilisateur a entré une valeur qui n'est pas un chiffre, afficher une boîte de dialogue d'erreur
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un chiffre valide.");
                }
            }
        });

        // the player can buy houses
        House.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("nb of the property/nb of houses to buy");
                try{
                    List<String> list = Arrays.asList(input.split("/"));
                    int number1 = Integer.parseInt(list.get(0));
                    int number2 = Integer.parseInt(list.get(1));
                    if (Board.cases.get(number1).owner == Server.players.get(playerId) && Board.PlayerCanBuyHouse(number1, number2)) {
                        DisplayPlay(playerId);
                        Board.BuyHouse(number1, number2);
                        repaint();
                    }
                } catch (NumberFormatException ex) {
                    // Si l'utilisateur a entré une valeur qui n'est pas un chiffre, afficher une boîte de dialogue d'erreur
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un chiffre valide.");
                }
            }
        });

        HouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPlayToHouse(playerId);
            }
        });

        // the player can exchange properties with another player
        Echanger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("nb of the property you want to sell/nb of the property you want to buy");
                try{
                    int number = Integer.parseInt(input);
                    int number2 = Integer.parseInt(input);
                    if (Board.cases.get(number).owner == Server.players.get(playerId) && Board.cases.get(number2).owner != Server.players.get(playerId)) {
                        DisplayHideAll();
                        DisplayPlay(playerId);
                        repaint();
                    }
                } catch (NumberFormatException ex) {
                    // Si l'utilisateur a entré une valeur qui n'est pas un chiffre, afficher une boîte de dialogue d'erreur
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un chiffre valide.");
                }
            }
        });

        EchangerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPlayToEchanger(playerId);
            }
        });

        // the player can cancel his action
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayHideAll();
                DisplayPlay(playerId);
            }
        });        

        System.out.println("Affichage");
        // Add the JPanel to the JFrame and display it
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Display weither the play button and the echanger button or with the house button
    public void DisplayPlay(int playerId) {
        DisplayHideAll();
        for (Player p : Server.players) {
            if (p.affichage.playerId == Server.currentPlayer) {
                p.affichage.playButton.setVisible(true);
                p.affichage.EchangerButton.setVisible(true);
                if (Board.HouseCouldBeBought()) {
                    p.affichage.HouseButton.setVisible(true);
                }
                else {
                    p.affichage.HouseButton.setVisible(false);
                }
            }
            else {
                p.affichage.HouseButton.setVisible(false);
                p.affichage.EchangerButton.setVisible(false);
                p.affichage.playButton.setVisible(true);
            }
        }
    }

    // Show the housebutton and hide the buttons for the player
    public void DisplayPlayToHouse(int playerId) {
        Server.players.get(playerId).affichage.Cancel.setVisible(true);
        Server.players.get(playerId).affichage.House.setVisible(true);
        Server.players.get(playerId).affichage.playButton.setVisible(false);
        Server.players.get(playerId).affichage.Bid.setVisible(false);
        Server.players.get(playerId).affichage.EchangerButton.setVisible(false);
        Server.players.get(playerId).affichage.HouseButton.setVisible(false);
    }

    // Show the echanger button, show a message for the players that the player who play want to exchange properties and hide the buttons for the player
    public void DisplayPlayToEchanger(int playerId) {
        Server.players.get(playerId).affichage.Cancel.setVisible(true);
        Server.players.get(playerId).affichage.Echanger.setVisible(true);
        Server.players.get(playerId).affichage.playButton.setVisible(false);
        Server.players.get(playerId).affichage.Bid.setVisible(false);
        Server.players.get(playerId).affichage.EchangerButton.setVisible(false);
        Server.players.get(playerId).affichage.HouseButton.setVisible(false);
        for (int i = 0; i < Server.players.size(); i++) {
            if (i != playerId) {
                Server.players.get(i).affichage.message.setText("Player " + playerId + " want to exchange properties");
                Server.players.get(i).affichage.message.setVisible(true);
            }
        }
    }

    // Hide all for all for all players
    public void DisplayHideAll() {
        for (int i = 0; i < Server.players.size(); i++) {
            Server.players.get(i).affichage.Cancel.setVisible(false);
            Server.players.get(i).affichage.playButton.setVisible(false);
            Server.players.get(i).affichage.EchangerButton.setVisible(false);
            Server.players.get(i).affichage.Echanger.setVisible(false);
            Server.players.get(i).affichage.HouseButton.setVisible(false);
            Server.players.get(i).affichage.House.setVisible(false);
        }
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

    public int __nextplayer() {
        if (Server.players.get(playerId).nbdoubles != 0) {
            return Server.currentPlayer;
        }
        else {
            int res = (playerId + 1) % playerCount;
            while (Server.players.get(res).bankrupt) {
                res = (res + 1) % playerCount;
            }
            return res;
        }
    }

    public int nextplayer() {
        int res = __nextplayer();
        Server.currentPlayer = res;
        DisplayPlay(res);
        return res;
    }

    //Display and change the sold of the owner and the visitor of the case price
    public void DisplayPaySomebody(Case c){
        if (Server.players.get(playerId).sold < c.Price()) {
            Board.casesell = c;
            Sell.setVisible(true);
            playButton.setVisible(false);
            HouseButton.setVisible(false);
            EchangerButton.setVisible(false);
        }
        else {
            Board.casesell = null;
            Sell.setVisible(false);
            DisplayPlay(playerId);
            Server.players.get(c.owner.affichage.playerId).sold += c.Price();
            Server.players.get(c.owner.affichage.playerId).affichage.all = "player"+(c.owner.affichage.playerId+1)+" with "+Server.players.get(c.owner.affichage.playerId).sold+c.owner.affichage.allproperties;
            Server.players.get(playerId).sold -= c.Price();
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

    public void DisplayPayBank(int amount) {
        if (Server.players.get(playerId).sold < amount) {
            paytobank = amount;
            Sell.setVisible(true);
            playButton.setVisible(false);
            House.setVisible(false);
            Echanger.setVisible(false);
        }
        else {
            paytobank = 0;
            Sell.setVisible(false);
            DisplayPlay(playerId);
            Server.players.get(playerId).sold -= amount;
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

    public void ChangeNumberHouse(Case c) {
        Server.players.get(c.owner.affichage.playerId).affichage.allproperties = Server.players.get(c.owner.affichage.playerId).affichage.allproperties.replace(" n"+c.position+"/"+c.house, " n"+c.position+"/"+c.house+1);
        Server.players.get(c.owner.affichage.playerId).affichage.all = "player"+(c.owner.affichage.playerId+1)+" with "+Server.players.get(c.owner.affichage.playerId).sold+Server.players.get(c.owner.affichage.playerId).affichage.allproperties;
        for (Player player : Server.players) {
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
