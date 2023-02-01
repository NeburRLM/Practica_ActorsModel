package Proxy;

import Message.Message;
import SuperInterface.SuperInterface;

/**
 * The abstract class Proxy is the foundation of the proxy design pattern. This class implements the SuperInterface and
 * provides a basic implementation for the send, receive and getName methods.
 * The send method is used to send messages to the actor, the receive method is used to receive messages from the actor
 * and the getName method is used to get the name of the actor.
 *
 */
public abstract class Proxy implements SuperInterface {

    /**
     * The send method is used to send messages to the actor.
     *
     * @param message The message to be sent to the actor.
     */
    public abstract void send(Message message)throws InterruptedException;

    /**
     * The receive method is used to receive messages from the actor.
     *
     * @return The message received from the actor.
     */
    public abstract Message receive() throws InterruptedException;

    /**
     * The getName method is used to get the name of the actor.
     *
     * @return The name of the actor
     */
    public abstract String getName() ;

    /**
     * The toString method is used to print the name of actor.
     *
     * @return The name of the actor.
     */
    @Override
    public String toString() {
        return "Proxy: "+getName();
    }
}
