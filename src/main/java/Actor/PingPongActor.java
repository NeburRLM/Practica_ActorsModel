package Actor;

import Message.*;


import java.util.LinkedList;

/**
 *  The PingPongActor class is a subclass of the Actor class. It receives a
 *  message and sends a random insult from a predefined list of insults to the
 *  sender of the message.
 */
public class PingPongActor extends Actor{
    /**
     * A linked list containing the insults.
     */
    private final LinkedList<String> insultList = new LinkedList<>();

    /**
     * Constructor that creates a new PingPongActor with the given name and
     * loads a list of predefined insults
     * @param name The name of the PingPongActor
     */
    public PingPongActor (String name){
        super(name);
        loadListInsults();
    }

    /**
     * Method to load the insults in the list
     */
    private void loadListInsults() {
        insultList.add("Silly!");
        insultList.add("Asshole");
        insultList.add("How about dumb");
        insultList.add("Even my grandmother programs better than you");
        insultList.add("Fuck off");
    }

    /**
     * The processMessage method receives a message and sends a random
     * insult from the insultList to the sender of the message
     * @param message The message received
     * @return false
     */
    public boolean processMessage(Message message) throws InterruptedException {
        message.getSender().send(new Message(this, getMessageFromListInsult()));
        return false;
    }

    /**
     * Method to get a random insult from the list
     * @return a random insult
     */
    public String getMessageFromListInsult() {
        int index = (int) (Math.random() * insultList.size());
        return insultList.get(index);
    }

}
