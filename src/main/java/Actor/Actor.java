package Actor;

import Message.Message;
import Proxy.*;
import SuperInterface.SuperInterface;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *  Actor abstract class implements SuperInterface
 *  This class represents an actor in a concurrent system.
 *  It has a name, a BlockingQueue for messages, an ActorProxy for sending messages, and an abstract method for processing messages.
 */
public abstract class Actor implements SuperInterface {


	/**
	 * The name of the actor
	 */
	private String name;

	/**
	 * The BlockingQueue for messages
	 */
	private final BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);

	/**
	 * The ActorProxy for sending messages
	 */
	private ActorProxy proxy;

	/**
	 * Constructs an Actor with a name
	 * @param name the name of the actor
	 */
	public Actor(String name) {
		this.name = name;
	}

	/**
	 * Sets the ActorProxy for the actor
	 * @param proxy the ActorProxy to set
	 */
	public void setProxy(ActorProxy proxy) {
		this.proxy = proxy;
	}

	/**
	 * Sends a message to the actor's BlockingQueue
	 * @param message the message to send
	 * @throws InterruptedException if the thread is interrupted while waiting to put the message in the queue
	 */
	public void send(Message message) throws InterruptedException {
		queue.put(message);
	}

	/**
	 * Gets the name of the actor
	 * @return the name of the actor
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the actor
	 * @param name the name of the actor
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the ActorProxy of the actor
	 * @return the ActorProxy of the actor
	 */
	public ActorProxy getProxy() {
		return proxy;
	}

	/**
	 * Gets the BlockingQueue of messages for the actor
	 * @return the BlockingQueue of messages for the actor
	 */
	public BlockingQueue<Message> getQueue() {
		return queue;
	}

	/**
	 * Abstract method for processing a message
	 * @param poll the message to process
	 * @throws InterruptedException if the thread is interrupted while waiting to process the message
	 * @return true if the message was processed successfully, false otherwise
	 */
	public abstract boolean processMessage(Message poll) throws InterruptedException;


}


