package Controller;
import java.awt.*;

import javax.swing.*;

/**
 * The Vista class is a JFrame extension that provides the user interface for the Actors model.
 */
public class Vista extends JFrame{
    private final JTextArea info = new JTextArea();
    private final JPanel ButtonPanel = new JPanel();
    private final JButton b1 = new JButton("Show actors");
    private final JButton b2 = new JButton("Talk");
    private final JButton b3 = new JButton("Add actor");
    private final JButton b4 = new JButton("Show Stats");
    private final JButton b5= new JButton("Ring");
    private final JButton b6 =new JButton("Received messages");
    private final JButton b7 =new JButton("Queue status");
    private final JButton b8 =new JButton("Ping Pong");

    /**
     * Returns the Controller object associated with this Vista.
     *
     * @return the Controller object associated with this Vista.
     */
    public Controller getController() {
        return controller;
    }

    private final Controller controller;  // reference to the associated Controller object

    /**
     * Constructor that creates a new Vista object and initializes the user interface.
     *
     * @param controller the Controller object to be associated with this Vista.
     */
    public Vista(Controller controller) {
        this.controller=controller;
        windowSetting();
        windowComponent();
        this.setVisible(true);

    }

    /**
     * Method to set up the window's properties.
     *
     */
    public void windowSetting() {
        this.setSize(1500,1500);
        this.setTitle("Actors model");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

    }

    /**
     * Method to add components to the window.
     *
     */
    private void windowComponent() {

        // We force the arrangement of the objects contained in the panel
        ButtonPanel.setLayout(new FlowLayout());
        ButtonPanel.add(b1); // Let's add the buttons to the panel
        ButtonPanel.add(b2);
        ButtonPanel.add(b3);
        ButtonPanel.add(b4);
        ButtonPanel.add(b5);
        ButtonPanel.add(b6);
        ButtonPanel.add(b7);
        ButtonPanel.add(b8);
        ListenerButton l = new ListenerButton(this,controller); //we pass the window
        b1.addActionListener(l);
        b2.addActionListener(l);
        b3.addActionListener(l);
        b4.addActionListener(l);
        b5.addActionListener(l);
        b6.addActionListener(l);
        b7.addActionListener(l);
        b8.addActionListener(l);
        add(ButtonPanel, BorderLayout.NORTH);

        info.setEditable(false);
        add(info, BorderLayout.CENTER);
    }

    /**
     * Writes the given message to the JTextArea of the class.
     *
     * @param m the message to be written.
     */
    public void writeInfo(String m) {
        info.setFont(new Font("Gill Sans Nova Ultra Bold", Font.BOLD, 12));
        info.append(m);
        info.append("\n");
    }

    /**
     * Clears the JTextArea of the class.
     *
     */
    public void emptyInfo() {
        info.setText("");
    }

}
