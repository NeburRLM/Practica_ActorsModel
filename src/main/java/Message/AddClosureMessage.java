package Message;

import SuperInterface.SuperInterface;

import java.util.function.Predicate;

/**
 * The AddClosureMessage class is a type of Message that is used to add a closure to the actor system.
 *
 */
public class AddClosureMessage extends Message {

    private final Predicate<Object> p;

    /**
     * Constructor for the AddClosureMessage class.
     * @param sender The sender of the message.
     * @param message The message content.
     * @param p The closure represented as a Predicate object.
     *
     */
    public AddClosureMessage(SuperInterface sender, String message, Predicate<Object> p){
        super(sender,message);
        this.p=p;
    }

    /**
     * Returns the closure represented as a Predicate object.
     * @return Predicate<Object> the closure.
     */
    public Predicate<Object> getP() {
        return p;
    }

}
