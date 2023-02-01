package Proxy;

import Actor.Actor;
import Message.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * The ActorProxy class extends the Proxy class and serves as a proxy for an Actor.
 * It allows for sending and receiving messages to and from the Actor.
 * The class also contains a BlockingQueue for holding messages that are replies.
 *
 */
public class ActorProxy extends Proxy {
    Actor actor;
    BlockingQueue<Message> cola = new ArrayBlockingQueue<>(10);

    /**
     * Creates a new ActorProxy with the given Actor.
     *
     * @param name the Actor that the proxy will represent
     */
    public ActorProxy(Actor name) {
        actor = name;
    }

    /**
     * Sends a message to the represented Actor. If the message is a ReplyMessage, it will be added to the BlockingQueue.
     *
     * @param message the message to be sent
     * @throws InterruptedException if the thread is interrupted while waiting to add the message to the BlockingQueue
     */
    public void send(Message message) throws InterruptedException {
        if(message instanceof ReplyMessage){
            cola.put(message);
        }else{
            actor.send(message);
        }
    }

    /**
     * Retrieves and removes the head of the BlockingQueue, waiting if necessary until an element becomes available.
     *
     * @return the message at the head of the BlockingQueue.
     * @throws InterruptedException if the thread is interrupted while waiting to retrieve the message.
     */
    public Message receive() throws InterruptedException {
        return cola.take();
    }

    /**
     * Returns the name of the represented Actor.
     * @return the name of the represented Actor.
     */
    @Override
    public String getName() {
        return actor.getName();
    }

    /**
     * Returns the represented Actor.
     * @return the represented Actor
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Sets the represented Actor.
     * @param actor the Actor to be represented by the proxy
     */
    public void setActor(Actor actor) {
        this.actor = actor;
    }

    /**
     * Add message to the BlockingQueue.
     *
     * @param message the message to be added
     * @throws InterruptedException if the thread is interrupted while waiting to add the message to the BlockingQueue
     */
    public void AddQueue(Message message) throws InterruptedException {
        cola.put(message);
    }



}
