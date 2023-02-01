package Message;

import SuperInterface.SuperInterface;

/**
 * The GetAllInsultsMessage class is a child class of Message class.
 * It is used to send a message requesting all insults from another actor.
 *
 */
public class GetAllInsultsMessage extends Message {

    /**
     * Constructor for the GetAllInsultsMessage class.
     *
     * @param from - The sender of the message.
     *
     */
    public GetAllInsultsMessage(SuperInterface from) {
        super(from, "Send me all your insults");

    }
}
