package Actor;

import Message.Message;

import java.util.LinkedList;

/**
 *  The RingActor class is an implementation of the Actor class that represents a ring of actors,
 *  where messages are passed along in a circular fashion.
 */
public class RingActor extends Actor{
    /**
     * Next actor for the ring.
     */
    private RingActor nextActor;

    /**
     * A linked list containing the insults.
     */
    private final LinkedList<String> insultList = new LinkedList<>();

    /**
     * Constructs a new RingActor instance with the given name.
     * @param name the name of the actor
     */
    public RingActor (String name){
        super(name);
        loadListInsults();
    }

    /**
     * Sets the next actor in the ring.
     * @param next the next actor in the ring
     */
    public void setNext(RingActor next) {
        nextActor = next;
    }

    /**
     * Method to process a message.
     * @param poll the message to be processed
     * @throws InterruptedException if the thread is interrupted
     * @return false, as the message is passed along to the next actor in the ring
     */
    @Override
    public boolean processMessage(Message poll) throws InterruptedException {
        poll.getSender().send(new Message(nextActor.getProxy(), getMessageFromListInsult()));
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
     * Method to get a random insult from the list
     * @return a random insult
     */
    public String getMessageFromListInsult() {
        int index = (int) (Math.random() * insultList.size());
        return insultList.get(index);
    }

}
