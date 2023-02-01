package Actor;

import Message.Message;
import Proxy.ActorProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  The ReflectiveActor class is an extension of the Actor class, that allows for
 *  the execution of methods on a given object, using reflection.
 */
public class ReflectiveActor extends Actor{

    /**
     * Instance to invoke methods
     */
    private final Object instance;

    /**
     * Constructor to creates a ReflectiveActor with the given instance.
     * @param instance the instance that will be used to invoke methods on.
     */
    public ReflectiveActor(Object instance) {
        super("Reflective Actor");
        this.instance = instance;
    }


    /**
     *  Overrides the processMessage method of the Actor class, which allows to invoke methods on the instance passed in the constructor.
     *
     * @param message the message that will be used to invoke the method.
     * @return true if the method is invoked successfully, false otherwise.
     * @throws InterruptedException when the thread is interrupted while waiting.
     */
    @Override
    public boolean processMessage(Message message) throws InterruptedException {
        try {
            Method method = instance.getClass().getMethod(message.getType(), ActorProxy.class);
            method.invoke(instance,(ActorProxy)message.getSender());
            return true;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }
}
