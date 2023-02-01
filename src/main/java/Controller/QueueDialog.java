package Controller;

import javax.swing.*;
import java.util.LinkedList;


/**
 * This class creates a dialog for viewing the current state of a queue.
 */
public class QueueDialog extends JDialog {

    private final JComboBox<String> nameComboBox;
    private String name;
    private final JProgressBar progressBar;

    /**
     * Constructor for QueueDialog.
     *
     * @param controller the controller of the application
     */
    public QueueDialog(Controller controller){
        setTitle("QueueDialog");
        setSize(900, 550);

        LinkedList<String> l = controller.getNames();
        String[] names  = l.toArray(new String[0]);

        JButton showComboButton = new JButton("Refresh");
        showComboButton.setBounds(350, 300, 200, 25);
        add(showComboButton);
        nameComboBox = new JComboBox<>(names);
        add(nameComboBox);
        nameComboBox.setBounds(350, 150, 200, 25);
        progressBar = new JProgressBar(0, 5);
        progressBar.setBounds(350,200,200,20);

        add(progressBar);
        setLayout(null);
        nameComboBox.addActionListener(e -> {
            name = (String) nameComboBox.getSelectedItem();
            progressBar.setValue(controller.getQueue(name).size());
            revalidate();
            repaint();
        });
        showComboButton.addActionListener(e -> {
            progressBar.setValue(controller.getQueue(name).size());
            revalidate();
            repaint();
        });

    }
}