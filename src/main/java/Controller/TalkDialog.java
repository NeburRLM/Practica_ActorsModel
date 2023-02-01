package Controller;

import javax.swing.*;

import java.util.LinkedList;


/**
 * The TalkDialog class extends JDialog and is used to display a dialog box with options to select names and send messages.
 */
public class TalkDialog extends JDialog {
    private final JButton send;
    private final JComboBox<String> nameComboBox;
    private final JComboBox<String> nameComboBox1;
    private String name, name1;

    /**
     * Constructor for TalkDialog class.
     *
     * @param controller The controller object that controls the flow of the program.
     */
    public TalkDialog(Controller controller) {
        setTitle("TalkDialog");
        setSize(900, 950);

        setLayout(null);


        JButton showComboButton = new JButton("Press");
        showComboButton.setBounds(300, 50, 200, 25);
        add(showComboButton);
        send = new JButton("send");
        send.setBounds(300, 450, 200, 25);
        add(send);



        LinkedList<String> listName = controller.getNames();
        String[] names  = listName.toArray(new String[0]);


        nameComboBox = new JComboBox<>(names);
        nameComboBox1 = new JComboBox<>(names);


        showComboButton.addActionListener(e -> {

            add(nameComboBox);
            add(nameComboBox1);
            nameComboBox.setBounds(100, 250, 200, 25);
            nameComboBox1.setBounds(500, 250, 200, 25);

            revalidate();
            repaint();
        });
        send.addActionListener(e -> {
            if(name!=null && name1!=null) {
                try {
                    controller.SendMessage(name,name1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                send.setEnabled(false);
            }

        });


        nameComboBox.addActionListener(e -> {
            name = (String) nameComboBox.getSelectedItem();

            nameComboBox.setEnabled(false);
        });
        nameComboBox1.addActionListener(e -> {
            name1= (String) nameComboBox1.getSelectedItem();

            nameComboBox1.setEnabled(false);
        });


    }
}