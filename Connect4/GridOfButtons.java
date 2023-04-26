import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class GridOfButtons extends JPanel {

    private static final int ROWS = 6, COLS = 7, SIZE = 42, BORDER = 2;
    private static final Color BOARD_COLOR = Color.BLACK;

    public GridOfButtons() {

        setLayout(new GridLayout(ROWS + 1, COLS, BORDER, BORDER));
        setBackground(BOARD_COLOR);

        StonesFactory factory = new StonesFactory(SIZE);
        boolean isBlack = false;



        String[] buttons = {
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G"
        };

        for(int i = 0; i < 7; i++)
        {
            JButton index = new JButton();
            //index.setPreferredSize(new Dimension(0, 0));
            index.setBackground(Color.WHITE);

            add(index);
        }



        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                add(factory.makeButton(isBlack));
                isBlack = !isBlack;
            }
        }

        this.initBoard();
    }

    public void initBoard() {
        JFrame f = new JFrame("Connect-4");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridBagLayout());
        f.add(this);
        f.pack();
        f.setVisible(true);
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new GridOfButtons());
    }
}

class StonesFactory{

    private static final Color STONE = Color.WHITE, YELLOW_STONE = Color.YELLOW, RED_STONE = Color.RED;
    private final int size;
    private final ImageIcon whiteIcon, blackIcon;

    public StonesFactory(int size) {
        this.size = size;
        whiteIcon = new ImageIcon(createImage(false));
        blackIcon = new ImageIcon(createImage(true));
    }

    JButton makeButton(boolean isBlack){
        JButton stone = new JButton();
        stone.setPreferredSize(new Dimension(size, size));
        stone.setBackground(STONE);
        stone.setIcon(isBlack ? blackIcon : whiteIcon);
        return stone;
    }

    //construct image for button's icon
    private BufferedImage createImage(boolean isBlack) {
        BufferedImage img = new BufferedImage(size , size,  BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(isBlack ? YELLOW_STONE : RED_STONE);
        g2.fillOval(0,0,size,size);
        g2.dispose();
        return img;
    }
}
