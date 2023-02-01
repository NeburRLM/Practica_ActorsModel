package Actor;

import Message.*;

import java.util.LinkedList;

/**
 *  The class InsultActor is a class that extends the Actor class and represents
 *  an Actor that can generate insults.
 */
public class InsultActor extends Actor {

    /**
     * A linked list containing the insults.
     */
    private final LinkedList<String> insultList = new LinkedList<>();

    /**
     * Constructor for the InsultActor class.
     *
     * @param name the name of the actor
     */
    public InsultActor(String name) {
        super(name);
        loadListInsults();
    }

    /**
     * Method to process a message.
     *
     * @param message the message to process
     * @throws InterruptedException if the thread is interrupted
     * @return boolean indicating if the actor should stop processing messages
     */
    public boolean processMessage(Message message) throws InterruptedException {
        if (message != null) {
            switch (message) {
                case QuitMessage m1 -> {
                    getProxy().AddQueue(m1);
                    return true;
                }
                case InsultMe m2 -> m2.getSender().send(new ReplyMessage(this, getMessageFromListInsult()));
                case AddInsultMessage m3 -> addInsultMessage(m3.getMessage());
                case GetAllInsultsMessage m4 -> m4.getSender().send(new ReplyMessage(this, insultList.toString()));
                default -> message.getSender().send(new ReplyMessage(this, getMessageFromListInsult()));
            }
        }
        return false;
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
     * Method to get a random insult from the list.
     *
     * @return a random insult.
     */
    public String getMessageFromListInsult() {
        int index = (int) (Math.random() * insultList.size());
        return insultList.get(index);
    }

    /**
     * Method to add an insult to the list.
     *
     * @param message the insult to add.
     */
    public void addInsultMessage(String message) {
        insultList.add(message);
    }

}
