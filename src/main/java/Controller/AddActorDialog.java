package Controller;


import Proxy.Proxy;
import javax.swing.*;


/**
 * The AddActorDialog class is a JDialog that allows the user to add new actors to the system.
 */
public class AddActorDialog extends JDialog {
    private final JComboBox<String> nameComboBox;
    private String typeActor;

    /**
     * Constructor for AddActorDialog
     * @param controller  The controller that will handle the logic of the dialog
     */
    public AddActorDialog(Controller controller) {
        setTitle("AddActorDialog");
        setSize(400, 150);
        setLayout(null);

        // Get the different types of actors from the controller
        String[] names =controller.TypeOfActors();

        // Create the JComboBox with the types of actors
        nameComboBox = new JComboBox<>(names);
        add(nameComboBox);
        nameComboBox.setBounds(50, 35, 300, 25);
        nameComboBox.addActionListener(e -> {
            typeActor = (String) nameComboBox.getSelectedItem();
            nameComboBox.setEnabled(false);
            String nameActor = JOptionPane.showInputDialog("Enter the actor's name: ");

            // Validate that the user entered a non-empty string
            while (nameActor == null || nameActor.equals("")) {

                JOptionPane.showMessageDialog(null, "An actor is needed!", "ERROR", JOptionPane.ERROR_MESSAGE);
                nameActor = JOptionPane.showInputDialog("Enter the actor's name: ");

            }

            // Create the new actor and dispose the dialog
            Proxy proxy=controller.SpawnActors(nameActor,typeActor);
            dispose();
        });

    }
}
