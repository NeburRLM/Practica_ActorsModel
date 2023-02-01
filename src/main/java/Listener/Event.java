package Listener;

import Actor.Actor;
import Message.Message;

import java.util.EventObject;

/**
 * The Event class represents an event that occurs in the Actor system.
 *
 */
public class Event extends EventObject {

    private final Actor actor;

    private final Message message;

    private final EventType event;

    /**
     * Constructs a new Event object with the given information.
     *
     * @param obj The object on which the event occurred.
     * @param event The type of event that occurred.
     * @param message The message being processed.
     * @param actor The actor that generated the event.
     *
     */
    public Event (Object obj, EventType event, Message message, Actor actor) {
        super(obj);
        this.actor=actor;
        this.event = event;
        this.message = message;
    }

    /**
     * Returns the actor that generated the event.
     * @return The actor that generated the event.
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Returns the type of event that occurred.
     * @return The type of event that occurred.
     */
    public EventType getEvent() {
        return event;
    }

    /**
     * Returns the message being processed.
     * @return The message being processed.
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Returns a string representation of the event.
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        if(message!=null){
            return "Event{" +
                    "message=" + message +
                    ", event=" + event +
                    '}';
        }else{
            return "Event{ "+event +" }";
        }

    }

}

