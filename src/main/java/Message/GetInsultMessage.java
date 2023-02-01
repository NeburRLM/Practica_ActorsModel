package Message;

import SuperInterface.SuperInterface;

/**
 * GetInsultMessage class is a message class that extends the Message class.
 *
 */
public class GetInsultMessage extends Message{

    /**
     * Constructs a new GetInsultMessage object with the specified sender.
     *
     * @param sender The sender of the message.
     */
    public GetInsultMessage(SuperInterface sender) {
        super(sender, "I want an insult");
    }
}
