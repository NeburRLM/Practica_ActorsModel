package Message;

import SuperInterface.SuperInterface;

/**
 * The ReplyMessage class is a subclass of the Message class and represents
 * a message sent in response to another message.
 *
 */
public class ReplyMessage extends Message{

    /**
     * Constructor for the ReplyMessage class.
     *
     * @param sender The sender of the message
     * @param message The message content
     */
    public ReplyMessage(SuperInterface sender, String message) {
        super(sender, message);
    }
}
