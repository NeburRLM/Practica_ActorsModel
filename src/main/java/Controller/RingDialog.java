package Controller;

import javax.swing.*;

import java.util.LinkedList;

/**
 * RingDialog is a JDialog class that allows the user to select a list of names from a provided list and then
 * starts the ring using the selected names.
 */
public class RingDialog extends JDialog {
    /**
     * NameComboBox is a JComboBox used to display the list of names for selection.
     */
    private final JComboBox<String> nameComboBox;

    /**
     * send is a JButton used to start the ring with the selected names.
     */
    private JButton send;

    /**
     * nameComboBox1, nameComboBox2, nameComboBox3, and nameComboBox4 are additional JComboBoxes used to display
     * the remaining list of names after a selection has been made.
     */
    private JComboBox<String> nameComboBox1;
    private JComboBox<String> nameComboBox2;
    private JComboBox<String> nameComboBox3;
    private JComboBox<String> nameComboBox4;

    /**
     * name, name1, name2, name3, and name4 are strings used to store the selected names.
     */
    private String name, name1, name2, name3,name4;

    /**
     * Constructor for the RingDialog class.
     *
     * @param controller is the Controller object used to retrieve the list of names and start the ring.
     */
    public RingDialog(Controller controller) {
        setTitle("RingDialog");
        setSize(900, 950);

        LinkedList<String> listName = controller.getRingActors();

        nameComboBox = new JComboBox<>(listName.toArray(new String[0]));
        add(nameComboBox);
        nameComboBox.setBounds(300, 250, 200, 25);
        setLayout(null);


        nameComboBox.addActionListener(e -> {
            name = (String) nameComboBox.getSelectedItem();
            listName.remove(name);
            nameComboBox1 = new JComboBox<>(listName.toArray(new String[0]));
            nameComboBox1.setBounds(500, 150, 200, 25);
            add(nameComboBox1);
            nameComboBox.setEnabled(false);
            revalidate();
            repaint();


            nameComboBox1.addActionListener(e1 -> {
                name1= (String) nameComboBox1.getSelectedItem();
                listName.remove(name1);
                nameComboBox2 = new JComboBox<>(listName.toArray(new String[0]));
                add(nameComboBox2);
                nameComboBox2.setBounds(100, 150, 200, 25);
                nameComboBox1.setEnabled(false);
                revalidate();
                repaint();


                nameComboBox2.addActionListener(e2 -> {
                    name2 = (String) nameComboBox2.getSelectedItem();
                    listName.remove(name2);
                    nameComboBox3 = new JComboBox<>(listName.toArray(new String[0]));
                    add(nameComboBox3);
                    nameComboBox3.setBounds(500, 50, 200, 25);
                    nameComboBox2.setEnabled(false);
                    revalidate();
                    repaint();


                    nameComboBox3.addActionListener(e3 -> {
                        name3= (String) nameComboBox3.getSelectedItem();
                        listName.remove(name3);
                        nameComboBox4 = new JComboBox<>(listName.toArray(new String[0]));
                        add(nameComboBox4);
                        nameComboBox4.setBounds(100, 50, 200, 25);
                        nameComboBox3.setEnabled(false);
                        revalidate();
                        repaint();


                        nameComboBox4.addActionListener(e4 -> {
                            name4 = (String) nameComboBox4.getSelectedItem();
                            send = new JButton("Start the ring");
                            send.setBounds(300, 450, 200, 25);
                            add(send);
                            nameComboBox4.setEnabled(false);
                            revalidate();
                            repaint();


                            send.addActionListener(e5 -> {
                                if(name!=null && name1!=null &&name2!=null && name3!=null&& name4!=null ) {
                                    LinkedList<String> list=new LinkedList<>();
                                    list.add(name);
                                    list.add(name1);
                                    list.add(name2);
                                    list.add(name3);
                                    list.add(name4);
                                    try {
                                        controller.TestRing(list);
                                    } catch (InterruptedException ex) {
                                        throw new RuntimeException(ex);
                                    }

                                    send.setEnabled(false);
                                    dispose();
                                }

                            });
                        });
                    });
                });
            });
        });
    }
}
