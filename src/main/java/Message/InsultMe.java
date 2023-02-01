package Message;

import SuperInterface.SuperInterface;

/**
 * The InsultMe class extends the Message class and is used to request an insult from a sender.
 *
 */
public class InsultMe extends Message {

    /**
     * Constructor for the InsultMe class.
     * @param from The sender of the message.
     *
     */
    public InsultMe(SuperInterface  from) {
        super(from, "InsultMe");

    }

}
