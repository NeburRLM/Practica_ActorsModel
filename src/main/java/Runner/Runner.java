package Runner;

import Actor.Actor;

import Publisher.*;
import Listener.Event;
import Listener.EventType;
import Message.Message;

import java.util.LinkedList;

/**
 * The Runner class is a thread that runs the Actor and notifies the Publisher of events that occur while processing messages.

 */
public class Runner extends Thread {
    private final Publisher publisher;
    private final Actor actor;

    /**
     * Returns the Actor.
     * @return the Actor.
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Returns the Publisher.
     * @return the Publisher.
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Notify all subscribed observers of a given event.
     *
     * @param e The event to notify the observers of.
     */
    public void notifyObservers(Event e)  {
        publisher.notifyObservers(e);
    }

    /**
     * Constructor of the Runner class
     *
     * @param actor Actual actor
     * @param publisher Actual publisher
     */
    public Runner(Actor actor, Publisher publisher) {
        this.publisher = publisher;
        this.actor = actor;
        ;
    }

    /**
     * Run method processing messages for a specific actor with the specific event.
     *
     */
    @Override
    public void run() {
        boolean finished = false;
        while (!finished) {
            try {
                Thread.sleep(2000);
                Message mens = actor.getQueue().take();
                notifyObservers(new Event(this, EventType.RECEIVED_MESSAGE, mens, actor));

                if (actor.processMessage(mens)) {
                    finished = true;
                    notifyObservers(new Event(this, EventType.FINALIZATION, null, actor));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                notifyObservers(new Event(this, EventType.INCORRECT_FINALIZATION, null, actor));
            }
        }
    }


}

	

	    
		
	
	 
		
		
	

