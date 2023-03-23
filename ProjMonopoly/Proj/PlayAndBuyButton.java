package Proj;

import javax.swing.*;

public class PlayAndBuyButton extends JFrame {
    
    private JButton playButton;
    private JButton buyButton;
    
    public PlayAndBuyButton() {
        // Initialise la fenêtre JFrame
        super("Play and Buy Button");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        
        // Initialise les boutons
        playButton = new JButton("Play");
        buyButton = new JButton("Buy");
        buyButton.setBounds(200, 150, 80, 25); // Positionne le bouton buy
        
        // Ajoute le listener pour le bouton play
        playButton.addActionListener(event -> {
            // Cache le bouton play
            playButton.setVisible(false);
            // Affiche le bouton buy à la position 200,150
            add(buyButton);
            repaint();
        });
        
        // Ajoute les boutons à la fenêtre
        add(playButton);
        
        // Affiche la fenêtre
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new PlayAndBuyButton();
    }
}
