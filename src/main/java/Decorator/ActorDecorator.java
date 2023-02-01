package Decorator;

import Actor.Actor;
import Message.Message;
import Proxy.ActorProxy;

import java.util.concurrent.BlockingQueue;

/**
 * Abstract class that represents a decorator for the Actor class.
 *
 */
public abstract class ActorDecorator extends Actor{
    /**
     * The actor that is being decorated.
     */
    private final Actor clientActor;

    /**
     * Constructor for the ActorDecorator class.
     *
     * @param a the actor that is being decorated
     */
    public ActorDecorator(Actor a) {
        super("");
        clientActor=a;
    }

    /**
     * Returns the queue of the client actor.
     *
     * @return the queue of the client actor
     */
    public BlockingQueue<Message> getQueue() {
        return clientActor.getQueue();
    }

    /**
     * Sets the proxy of the client actor.
     *
     * @param proxy the proxy of the client actor
     */
    public void setProxy(ActorProxy proxy) {
        clientActor.setProxy(proxy) ;
    }

    /**
     * Abstract method that processes a message.
     *
     * @param poll the message to be processed
     * @throws InterruptedException if the thread is interrupted
     * @return true if the message was processed successfully, false otherwise
     */
    @Override
    public abstract  boolean processMessage(Message poll) throws InterruptedException;

    /**
     * Returns the name of the client actor.
     *
     * @return the name of the client actor
     */
    public String getName() {
        return clientActor.getName();
    }

    /**
     * Returns the proxy of the client actor.
     *
     * @return the proxy of the client actor
     */
    public ActorProxy getProxy() {
        return clientActor.getProxy();
    }


    /**
     * Abstract method that sends a message
     *
     * @param message the message to be sent
     * @throws InterruptedException if the thread is interrupted
     */
    public abstract void send(Message message) throws InterruptedException;

    /**
     * Returns the actor that is being decorated
     *
     * @return Actor
     */
    public Actor getClientActor() {
        return clientActor;
    }
}

