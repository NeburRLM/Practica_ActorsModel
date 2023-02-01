package Decorator;

import Actor.Actor;
import Message.AddClosureMessage;
import Message.Message;

import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * This class is a decorator that allows to apply a firewall based on Lambda expressions.
 *
 */
public class FirewallLambdaDecorator extends ActorDecorator{
    private final LinkedList <Predicate <Object>> predicates= new LinkedList<>();

    private boolean isCorrect =true;

    /**
     * Constructor for the FirewallLambdaDecorator class.
     *
     * @param a the actor to be decorated
     */
    public FirewallLambdaDecorator(Actor a) {
        super(a);
        a.setName(a.getName() + " firewall lambda decorator");
    }

    /**
     * This method is used to process messages.
     *
     * @param poll the message to be processed
     * @throws InterruptedException if the thread is interrupted
     * @return returns the result of the processMessage method of the actor being decorated.
     */
    @Override
    public boolean processMessage(Message poll) throws InterruptedException {
        //System.out.println(getClientActor().processMessage(poll));
        if(poll instanceof AddClosureMessage acm){
            addClosureMessage(acm.getP());
        }
        return getClientActor().processMessage(poll);


    }

    /**
     * This method is used to send messages.
     * Before sending the message, it checks if the message meets the conditions set in the predicates.
     *
     * @param message the message to be sent
     * @throws InterruptedException if the thread is interrupted
     */
    @Override
    public void send(Message message) throws InterruptedException {
        isCorrect =true;
        for(Predicate <Object> elem: predicates) {
            if (!(elem.test(message.getMessage()))) {
                isCorrect = false;
                System.out.println("It's not correct");
            }
        }
        if(isCorrect){
            getClientActor().send(message);
            System.out.println("It's correct");
        }

    }
    /**
     * This method returns the value of isCorrect variable.
     *
     * @return returns the value of isCorrect variable.
     */
    public boolean getCorrect(){
        return isCorrect;
    }

    /**
     * This method is used to add a Predicate to the list of predicates.
     *
     * @param closure the Predicate to be added to the list.
     */
    public void addClosureMessage(Predicate<Object> closure) {
        this.predicates.add(closure);
    }
}