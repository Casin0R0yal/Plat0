package src;

import java.net.*;

class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Solitaire");
        frame.setSize(500, 500);
        JButton button1 = new JButton("Move");
        frame.add(button1);
        button1.setBounds(100, 100, 5, 5);
        button1.setBackground(Color.red);
        // JButton button2 = new JButton("Move");
        // button2.setBounds(200, 100, 50, 50);
        // frame.add(button2);
        // JButton button3 = new JButton("Move");
        // button3.setBounds(300, 100, 50, 50);
        // frame.add(button3);
        // JButton button4 = new JButton("Move");
        // button4.setBounds(400, 100, 50, 50);
        // frame.add(button4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
    }
}