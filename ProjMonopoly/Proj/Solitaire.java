package Proj;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class Card {
    public String name;
    public String color;
    public int value;

    public Card(String name, String color, int value) {
        this.name = name;
        this.color = color;
        this.value = value;
    }
}

public class Solitaire extends JFrame {
    public List<List<Card>> plateau = new ArrayList<List<Card>>();
    
    public Solitaire() {
        super("Solitaire");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du plateau de jeu
        JPanel board = new JPanel(null);
        board.setPreferredSize(new Dimension(800, 600));
        board.setBackground(Color.GREEN);

        // Ajout des cartes au plateau de jeu
        for (int i = 0; i < 7; i++) {
            plateau.add(new ArrayList<Card>());
            for (int j = 0; j < i; j++) {
                Card card = new Card("Carte", "Rouge", 1);
                plateau.get(i).add(card);
            }
        }

        // Ajout des cartes au plateau de jeu
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i; j++) {
                Card card = plateau.get(i).get(j);
                JLabel label = new JLabel(card.name);
                label.setBounds(100 + i * 100, 100 + j * 100, 100, 100);
                board.add(label);
            }
        }

        // Ajout du plateau de jeu à la fenêtre
        add(board);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Solitaire();
    }
}
