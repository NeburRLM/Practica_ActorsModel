package Controller;

import javax.swing.*;
import java.util.LinkedList;

/**
 *
 * The class PingPongDialog extends JDialog and allows the user to select two actors from the list of available actors,
 * and sends a ping-pong message between them by using the controller's TestPingPong method.
 */
public class PingPongDialog extends JDialog {
    private JButton send;
    private final JComboBox<String> nameComboBox;
    private JComboBox<String> nameComboBox1;
    private String name, name1;

    /**
     *
     * Constructs a new PingPongDialog object and initializes the dialog with the list of actors and the functionality
     * to send the ping-pong message.
     *
     * @param controller The controller that handles the logic of the ping-pong message sending.
     */
    public PingPongDialog(Controller controller) {
        setTitle("PingPong");
        setSize(900, 950);

        setLayout(null);

        LinkedList<String> listName = controller.getPingPongNames();

        nameComboBox = new JComboBox<>(listName.toArray(new String[0]));
        add(nameComboBox);
        nameComboBox.setBounds(100, 250, 200, 25);


        nameComboBox.addActionListener(e -> {
            name = (String) nameComboBox.getSelectedItem();
            listName.remove(name);
            nameComboBox1 = new JComboBox<>(listName.toArray(new String[0]));
            nameComboBox1.setBounds(500, 250, 200, 25);
            add(nameComboBox1);
            revalidate();
            repaint();

            nameComboBox1.addActionListener(e1 -> {
                name1= (String) nameComboBox1.getSelectedItem();
                nameComboBox1.setEnabled(false);
                send = new JButton("send");
                send.setBounds(300, 450, 200, 25);
                add(send);
                revalidate();
                repaint();
                send.addActionListener(e11 -> {
                    if(name!=null && name1!=null) {
                        try {
                            controller.TestPingPong(name,name1);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        send.setEnabled(false);
                        dispose();
                    }

                });
            });
            nameComboBox.setEnabled(false);
        });

    }
}
