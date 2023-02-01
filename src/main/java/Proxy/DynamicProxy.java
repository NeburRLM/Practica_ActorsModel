package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import Message.*;

/**
 * This class implements the InvocationHandler interface and serves as a dynamic proxy for the target object.
 * It intercepts method calls and can perform additional actions, such as sending and receiving messages through the ActorProxy.
 *
 */
public class DynamicProxy implements InvocationHandler {
    private final Object target;
    private final ActorProxy a;

    /**
     * This method creates a new dynamic proxy instance for a given target object and ActorProxy.
     *
     * @param target The object for which the dynamic proxy will be created.
     * @param actor The ActorProxy associated with the target object.
     * @return A new dynamic proxy object that can be used to invoke methods on the target object.
     */
    public static Object intercept(Object target, ActorProxy actor){

        Class targetClass = target.getClass();
        Class[] interfaces = targetClass.getInterfaces();
        return Proxy.newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target,actor));
    }

    /**
     * The constructor for the DynamicProxy class.
     *
     * @param target The object for which the dynamic proxy will be created.
     * @param actor The ActorProxy associated with the target object.
     */
    private DynamicProxy(Object target, ActorProxy actor ) {
        this.target = target;
        this.a=actor;
    }

    /**
     * This method is called when a method is invoked on the dynamic proxy. It performs the method call on the target object,
     * and can perform additional actions, such as sending and receiving messages through the ActorProxy.
     *
     * @param proxy The dynamic proxy object.
     * @param method The method that was invoked.
     * @param args The arguments passed to the method.
     * @return The result of the method invocation, if any.
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("equals")) {
            return method.invoke(this.target, args);
        }
        if (method.getName().equals("hashCode")) {
            return method.invoke(this.target, args);
        }
        if (method.getName().equals("addInsult")) {
            return method.invoke(this.target, args);
        }
        method.invoke(this.target, args);

        Message m=a.receive();

        return m.getMessage();
    }


}
