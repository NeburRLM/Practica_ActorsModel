package Actor;


import Message.Message;
import Message.QuitMessage;

import java.util.Objects;

/**
 *  HelloWorldActor class extends Actor
 *  This class represents an actor that prints a message to the console.
 */
public class HelloWorldActor extends Actor {

    /**
     * Constructor of HelloWorldActor
     * @param name the name of the actor
     */
    public HelloWorldActor ( String name){
        super(name);
    }

    /**
     * Process the message.
     * @param message the message to process
     * @throws InterruptedException if the thread is interrupted while waiting to process the message
     * @return false if the message was processed successfully, true otherwise
     */
    public boolean processMessage(Message message) throws InterruptedException {
        if (Objects.requireNonNull(message) instanceof QuitMessage m1) {
            System.out.println(m1);
            getProxy().AddQueue(message);
        } else {
            System.out.println(message.getMessage());
            getProxy().AddQueue(message);
        }
        return false;
    }
}
