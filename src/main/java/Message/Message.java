package Message;
import SuperInterface.SuperInterface;

/**
 * Class representing a message that can be sent between instances of SuperInterface (actors and proxy).
 *
 */
public class Message {

    SuperInterface sender;
    String message;

    /**
     * Constructs a new message with the specified sender and message.
     *
     * @param sender The sender of the message.
     * @param message The message content.
     */
     public Message(SuperInterface sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    /**
     * Returns the type of the message, which is the class name without the "Message" suffix and with the first
     * letter lower cased (it's use for the ReflectiveActor).
     *
     * @return The type of the message.
     */
     public String getType() {
         String className = this.getClass().getSimpleName();
         //System.out.println(s);
         return className.substring(0, className.length() - 7).replaceFirst(className.substring(0,1), className.substring(0,1).toLowerCase());
     }

    /**
     * Returns the content of the message.
     *
     * @return The content of the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the content of the message.
     *
     * @param message The new content of the message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the sender of the message.
     *
     * @return The sender of the message.
     */
    public SuperInterface getSender() {
         return sender;
     }
}