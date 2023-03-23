package Proj;

import javax.swing.*;

class ExampleFrame extends JFrame {
    private JButton button;
    private JButton button2;
    public ExampleFrame() {
        super("Example Frame");
        JPanel panel = new JPanel(null);
        button = new JButton("Click me");
        button2 = new JButton("Hehehe");
        button.setBounds(50, 50, 100, 30);
        button2.setBounds(50, 50, 100, 30);
        button2.setVisible(false);
        button.addActionListener(event -> {
            button.setVisible(false);
            button2.setVisible(true);
            panel.add(button2);
            repaint();
        });
        panel.add(button);
        add(panel);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ExampleFrame();
    }
}

public class PlayAndBuyButton extends JFrame {
    private JButton button;
    private JButton button2;
    public PlayAndBuyButton() {
        super("Play and Buy Button");
        JPanel panel = new JPanel(null);
        button = new JButton("Play");
        button2 = new JButton("Buy");
        button.setBounds(100, 100, 100, 50);        
        button.addActionListener(event -> {
            button.setVisible(false);
            button2.setBounds(100, 100, 100, 50);
            panel.add(button2);
            repaint();
        });
        panel.add(button);
        add(panel);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
        public static void main(String[] args) {
        new PlayAndBuyButton();
    }
}