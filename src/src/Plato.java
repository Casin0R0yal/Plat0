package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;

public class Plato {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Plat0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel(null) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE);
                int circleDiameter = Math.min(getWidth(), getHeight()) / 2;
                g.fillOval(getWidth() / 2 - circleDiameter / 2, getHeight() / 2 - circleDiameter / 2, circleDiameter, circleDiameter);
            }
        };
        panel.setPreferredSize(new Dimension(800, 600));

        ImageIcon icon1 = new ImageIcon("images/monopoly.jpg");
        JButton button1 = new JButton(icon1);
        button1.setBounds(275, 175, 100, 100);
        button1.setContentAreaFilled(false);
        button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    frame.setVisible(false);
                    Process process = Runtime.getRuntime().exec("make -C Monopoly/ProjMonopoly");
                    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.println("Monopoly Server Started");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            public void mouseEntered(MouseEvent e) {
                button1.setBounds(225, 125, 200, 200);
            }

            public void mouseExited(MouseEvent e) {
                button1.setBounds(275, 175, 100, 100);
            }
        });

        ImageIcon icon2 = new ImageIcon("images/uno.jpg");
        JButton button2 = new JButton(icon2);
        button2.setBounds(425, 175, 100, 100);
        button2.setContentAreaFilled(false);
        button2.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e) {
                try {
                    frame.setVisible(false);
                    Process process = Runtime.getRuntime().exec("make -C uno/src");
                    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.println("Connect-4 started");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                button2.setBounds(375, 125, 200, 200);
            }

            public void mouseExited(MouseEvent e) {
                button2.setBounds(425, 175, 100, 100);
            }
        });

        ImageIcon icon3 = new ImageIcon("images/solitaire.jpg");
        JButton button3 = new JButton(icon3);
        button3.setBounds(275, 325, 100, 100);
        button3.setContentAreaFilled(false);
        button3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    frame.setVisible(false);
                    Process process = Runtime.getRuntime().exec("make -C Solitaire/src");
                    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }


                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                button3.setBounds(225, 275, 200, 200);
            }

            public void mouseExited(MouseEvent e) {
                button3.setBounds(275, 325, 100, 100);
            }
        });

        ImageIcon icon4 = new ImageIcon("images/power4.png");
        JButton button4 = new JButton(icon4);
        button4.setBounds(425, 325, 100, 100);
        button4.setContentAreaFilled(false);
        button4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    frame.setVisible(false);
                    Process process = Runtime.getRuntime().exec("make -C Connect4 ui");
                    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.println("Connect-4 started");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                button4.setBounds(375, 275, 200, 200);
            }

            public void mouseExited(MouseEvent e) {
                button4.setBounds(425, 325, 100, 100);
            }
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
