import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;

public class ConnectFourGUI extends JFrame implements ActionListener {

    private Board board;
    private JButton[][] grid;
    private JLabel playerTurnLabel;
    private Game game;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public ConnectFourGUI() {
        board = new Board();
        game = new Game(board);

        player1 = new Player("red", 1);
        player2 = new Player("yellow", 2);
        currentPlayer = player1;

        setTitle("Connect 4");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the board grid
        JPanel boardPanel = new JPanel(new GridLayout(6, 7));
        grid = new JButton[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = new JButton();
                grid[i][j].setPreferredSize(new Dimension(80, 80));
                grid[i][j].addActionListener(this);
                boardPanel.add(grid[i][j]);
            }
        }

        // Create the player turn label
        playerTurnLabel = new JLabel(currentPlayer.getName() + "'s turn");

        // Add the board and player turn label to the frame
        add(boardPanel, BorderLayout.CENTER);
        add(playerTurnLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Get the button that was clicked
        JButton button = (JButton) e.getSource();

        // Find the row and column of the button in the grid
        int row = -1;
        int col = -1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (grid[i][j] == button) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Check if the move is valid
        int[] putResult = board.PutCell(currentPlayer, col);
        if (putResult == null) {
            return;
        }

        // Update the grid and check for a winner
        grid[putResult[0]][putResult[1]].setContentAreaFilled(false);
        grid[putResult[0]][putResult[1]].setOpaque(true);
        grid[putResult[0]][putResult[1]].setBackground(currentPlayer.getColor());
        grid[putResult[0]][putResult[1]].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        grid[putResult[0]][putResult[1]].setPreferredSize(new Dimension(80, 80));
        grid[putResult[0]][putResult[1]].setBorderPainted(true);
        grid[putResult[0]][putResult[1]].addActionListener(this);
        grid[putResult[0]][putResult[1]].setFocusPainted(false);
        grid[putResult[0]][putResult[1]].setHorizontalAlignment(JButton.CENTER);
        grid[putResult[0]][putResult[1]].setVerticalAlignment(JButton.CENTER);
        grid[putResult[0]][putResult[1]].setPreferredSize(new Dimension(80, 80));

        grid[putResult[0]][putResult[1]].setForeground(currentPlayer.getColor().darker().darker());
        grid[putResult[0]][putResult[1]].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
        grid[putResult[0]][putResult[1]].setText("\u25CF");

        if (game.EndGame()) {
            JOptionPane.showMessageDialog(this, currentPlayer.getName() + " has won!");
            return;
        }
        if (game.TieGame()) {
            JOptionPane.showMessageDialog(this, "The game is a tie.");
            return;
        }

        // Switch to the other player's turn
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
        playerTurnLabel.setText(currentPlayer.getName() + "'s turn");
    }

    public static void main(String[] args) {
        new ConnectFourGUI();
    }

}

