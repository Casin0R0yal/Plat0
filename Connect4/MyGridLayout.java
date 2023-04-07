import java.awt.*;
import javax.swing.*;

public class MyGridLayout extends JFrame{
    private static final long serialVersionUID = 1L;

    public MyGridLayout(){
        initUI();
    }

    private void initUI(){

        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        panel.setLayout(new GridLayout(1,1,1,1));

        String[] buttons = {
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G"
        };

        for(int i = 0; i < buttons.length; i++)
            panel.add(new JButton(buttons[i]));


        
        add(panel);

        setTitle("Connect-4");
        setSize(350,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args){

        EventQueue.invokeLater(() -> {
            MyGridLayout l = new MyGridLayout();
            l.setVisible(true);
        });

    }
}



