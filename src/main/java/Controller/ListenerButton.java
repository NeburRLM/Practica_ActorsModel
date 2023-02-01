package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ListenerButton class is an implementation of the ActionListener interface, which is used to handle button
 * clicks in the user interface.
 */
public class ListenerButton implements ActionListener {
    private final Vista window;
    private final Controller controller;

    /**
     * Constructor for ListenerButton class.
     *
     * @param window reference to the main window (Vista).
     * @param controller reference to the Controller of the application.
     */
    public ListenerButton(Vista window, Controller controller) {
        this.window =window;
        this.controller=controller;
    }

    /**
     * This method is called whenever a button is clicked. It checks which button was clicked and calls
     * the appropriate method of the controller.
     *
     * @param e ActionEvent object that contains information about the button click event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button.getText().equals("Show actors")) {
            window.emptyInfo();
            controller.getNames().forEach(window::writeInfo);
        }

        if(button.getText().equals("Talk")) {
            TalkDialog n=new TalkDialog(controller);
            n.setVisible(true);
        }

        if (button.getText().equals("Add actor")){
            AddActorDialog add=new AddActorDialog(controller);
            add.setVisible(true);
        }

        if(button.getText().equals("Show Stats")) {
            window.emptyInfo();
            controller.getNames().forEach((m) -> window.writeInfo(m+" : has received "+ controller.getNumberOfMessages(m)+" messages"));
        }

        if(button.getText().equals("Ring")) {
            RingDialog r= new RingDialog(controller);
            r.setVisible(true);
        }

        if(button.getText().equals("Received messages")) {
        MessDialog mess=new MessDialog(controller);
        mess.setVisible(true);
        }

        if(button.getText().equals("Queue status")){
            QueueDialog vm=new QueueDialog(controller);
            vm.setVisible(true);
        }

        if(button.getText().equals("Ping Pong")){
            PingPongDialog m=new PingPongDialog(controller);
            m.setVisible(true);
        }
    }
}