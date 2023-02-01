package Controller;

import Actor.Actor;
import Message.Message;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The MessDialog class is a subclass of JDialog and displays a dialog box that shows the received messages of a selected actor.
 */
public class MessDialog extends JDialog {

    /**
     * JComboBox that displays a list of actors.
     */
    private final JComboBox<String> nameComboBox;

    /**
     * JTextArea that displays the received messages of the selected actor.
     */
    private final JTextArea info = new JTextArea();

    /**
     * Map that contains the received messages of all the actors,
     */
    private Map<Actor, List<Message>> map;

    /**
     * Constructor of the class.
     *
     * @param controller an instance of the Controller class that is used to retrieve the received messages of the actors.
     */
    public MessDialog(Controller controller) {

        setTitle("MessDialog ");
        setSize(900, 950);
        JScrollPane scrollPane = new JScrollPane(info);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        LinkedList<String> l = controller.getNames();
        String[] names  = l.toArray(new String[0]);
        info.setEditable(false);
        add(scrollPane);
        scrollPane.setBounds(0,0,300,950);
        setLayout(null);
        info.setVisible(true);

        nameComboBox = new JComboBox<>(names);
        add(nameComboBox);
        nameComboBox.setBounds(500, 150, 200, 25);

        nameComboBox.addActionListener(e -> {
            info.setText("");
            String name = (String) nameComboBox.getSelectedItem();
            map  = controller.getReceivedMessages();
            List<Message> message = map.get(controller.lookup(name));


            info.setFont(new Font("Gill Sans Nova Ultra Bold", Font.BOLD, 12));
            if(message!=null) {
                for (Message value : message) {
                    info.append(value.getMessage());
                    info.append("\n");
                }
            }

        });
    }
}
