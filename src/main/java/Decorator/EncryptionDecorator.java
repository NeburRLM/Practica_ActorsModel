package Decorator;

import Actor.Actor;
import Message.Message;

/**
 * EncryptionDecorator class is a decorator class that encrypts and decrypts messages sent and received by the actor it decorates.
 *
 */
public class EncryptionDecorator extends ActorDecorator {

    /**
     * Constructor for EncryptionDecorator class
     * @param client the actor to be decorated
     */
	public EncryptionDecorator(Actor client) {
		 super( client);
        client.setName( client.getName()+" encryption decorator");
    }

    /**
     * Decrypts a message
     * @param message the message to be decrypted
     */
    private void decryptMessage(Message message) {
        int key=3;
        String input = message.getMessage();
        StringBuilder decrypted = new StringBuilder();
        for (int j = 0; j < input.length(); j++) {
            decrypted.append((char) (input.charAt(j) ^ key));
        }
        message.setMessage(decrypted.toString());
    }

    /**
     * Encrypts a message
     * @param message the message to be encrypted
     */
    private void encryptMessage(Message message) {
        int key=3;
        String input = message.getMessage();
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            encrypted.append((char) (input.charAt(i) ^ key));
        }
        message.setMessage(encrypted.toString());
    }

    /**
     * Encrypts the message before sending it to the decorated actor
     * @param message the message to be sent
     * @throws InterruptedException if interrupted while sending the message
     */
    public void send(Message message) throws InterruptedException {
    	encryptMessage(message);
    	getClientActor().send(message);
    }

    /**
     * Decrypts the message before passing it to the decorated actor
     * @param message the message to be processed
     * @return true if the message was processed successfully, false otherwise
     * @throws InterruptedException if interrupted while processing the message
     */
    public boolean processMessage(Message message) throws InterruptedException {
                decryptMessage(message);
        return getClientActor().processMessage(message);

    }
}
