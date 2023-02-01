package Message;

import SuperInterface.SuperInterface;

/**
 * Class representing a QuitMessage which is a type of Message.
 * It is used to signal that the sender is quitting the system.
 *
 */
public class QuitMessage extends Message {

    /**
     * Creates a new QuitMessage with a given sender and message body.
     *
     * @param from the sender of the message
     * @param body the message body
     */
	public QuitMessage(SuperInterface from, String body) {
        super(from, body);
    }
}
