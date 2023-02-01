package Publisher;

import Listener.ActorListener;
import Listener.Event;
import Listener.EventType;
import Actor.*;

import java.util.LinkedList;

/**
 * This class represents a publisher, which can hold a list of observers (ActorListeners) and notify them of events.
 *
 */
public class Publisher{

    /**
     * A list of observers that are subscribed to this publisher.
     */
    private final LinkedList<ActorListener> observers=new LinkedList<>();

    /**
     * Subscribe an observer to this publisher.
     *
     * @param observer The observer to subscribe.
     * @param ac The Actor of the event
     */
    public void subscribe(ActorListener observer, Actor ac) {
        observers.add(observer);
        observer.update(new Event(this, EventType.CREATION,null, ac));
    }

    /**
     * Notify all subscribed observers of a given event.
     *
     * @param event The event to notify the observers of.
     */
    public void notifyObservers(Event event) {
        for (ActorListener observer : observers) {
            observer.update(event);
        }
    }

}

