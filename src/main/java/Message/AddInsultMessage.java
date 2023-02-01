package Message;


/**
 * The AddInsultMessage class represents a message that adds an insult to a collection of insults.
 *
 */
public class AddInsultMessage extends Message {

    /**
     * Creates a new AddInsultMessage with the given insult text.
     *
     * @param body The insult text to be added.
     */
    public AddInsultMessage( String body) {
        super(null, body);

    }

}
