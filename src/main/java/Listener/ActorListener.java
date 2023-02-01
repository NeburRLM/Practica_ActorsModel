package Listener;

import java.util.EventListener;

/**
 * The ActorListener interface is used to define the behavior of an object that listens for events from an Actor.
 * When an event occurs, the update() method will be called with the event as a parameter.
 *
 */
public interface ActorListener extends EventListener {

    /**
     * This method is called when an event occurs from an Actor.
     *
     * @param r The event that occurred.
     */
    void update(Event r) ;

}
