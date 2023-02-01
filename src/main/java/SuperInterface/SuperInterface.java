package SuperInterface;

import Message.Message;

/**
 * SuperInterface is an interface that defines send and getName methods.
 *
 */
public interface SuperInterface {

     /**
      * This method is used to send a message.
      *
      * @param message the message to be sent.
      * @throws InterruptedException if the thread is interrupted while waiting for the message to be sent.
      */
     void send(Message message) throws InterruptedException;

     /**
      * This method is used to get the name of the object.
      *
      * @return the name of the object.
      */
     String getName();
}
