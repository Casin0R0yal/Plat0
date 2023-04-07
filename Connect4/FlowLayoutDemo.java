
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
 
public class FlowLayoutDemo extends JFrame{
    FlowLayout experimentLayout = new FlowLayout();
    JButton applyButton = new JButton("Apply component orientation");
     
    public FlowLayoutDemo(String name) {
        super(name);
    }
     
    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.TRAILING);
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
         
        //Add buttons to the experiment layout
        compsToExperiment.add(new JButton("A"));
        compsToExperiment.add(new JButton("B"));
        compsToExperiment.add(new JButton("C"));
        compsToExperiment.add(new JButton("D"));
        compsToExperiment.add(new JButton("E"));
        compsToExperiment.add(new JButton("F"));
        compsToExperiment.add(new JButton("G"));

        //Left to right component orientation is selected by default
        //compsToExperiment.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        pane.add(compsToExperiment, BorderLayout.CENTER);
        //pane.add(controls, BorderLayout.SOUTH); ;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        FlowLayoutDemo frame = new FlowLayoutDemo("FlowLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event dispatchi thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
