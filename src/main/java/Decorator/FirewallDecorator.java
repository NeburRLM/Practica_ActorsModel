package Decorator;

import Actor.Actor;
import Message.Message;
import ActorContext.ActorContext;
import Proxy.*;

/**
 * The FirewallDecorator class is an implementation of the ActorDecorator class. It provides a firewall security
 * feature by only allowing messages to be sent by actors that are registered in the ActorContext.
 *
 */
public class FirewallDecorator extends ActorDecorator {

    /**
     * Creates a new FirewallDecorator object that decorates the given actor with a firewall feature.
     *
     * @param actor the actor to be decorated with a firewall feature.
     */
    public FirewallDecorator(Actor actor) {
        super( actor);
        actor.setName(actor.getName() + " firewall decorator");
    }

    /**
     * send method from the ActorDecorator class to provide a firewall feature.
     * Only allows messages to be sent by actors that are registered in the ActorContext.
     *
     * @param message the message to be sent.
     * @throws InterruptedException if the thread is interrupted while waiting to send the message.
     */
    @Override
    public void send(Message message) throws InterruptedException {
    	ActorContext ac = ActorContext.getInstance();

        if(!(message.getSender() instanceof ActorProxy)){
            if (ac.lookup(message.getSender().getName()) != null) {
                getClientActor().send(message);
            }

        }

    }

    /**
     * Overrides the processMessage method from the ActorDecorator class to process messages received by the decorated actor.
     *
     * @param poll the message to be processed.
     * @return true if the message is processed successfully, false otherwise.
     * @throws InterruptedException if the thread is interrupted while waiting to process the message.
     */
    @Override
   public boolean processMessage(Message poll) throws InterruptedException {
        return getClientActor().processMessage(poll);

    }


}
