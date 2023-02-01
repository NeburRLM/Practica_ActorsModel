package Controller;

import Actor.*;

import Decorator.EncryptionDecorator;
import Decorator.FirewallDecorator;
import Main.TestPingPong;
import Main.TestRingActor;
import Message.InsultMe;
import Message.Message;
import MonitorService.MonitorService;

import ActorContext.ActorContext;
import Proxy.Proxy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * The Controller class provides the functionality for controlling the behavior of Actors. It creates and manages.
 */
public class Controller {

    /**
     * Constructor for the Controller class. It creates a new instance of the Vista class and passes a reference to
     * itself.
     */
    public Controller() {
         new Vista(this);
    }

    /**
     * An instance of the MonitorService class, which is responsible for monitoring the activity of Actors.
     */
    private MonitorService mn;

    /**
     * Initializes the Controller by spawning three InsultActor instances.
     */
    public  void initialize() {
        ActorContext.spawnActor(new InsultActor("David"));
        ActorContext.spawnActor(new InsultActor("Ruben"));
        ActorContext.spawnActor(new InsultActor("Pedro"));
        mn=new MonitorService();
        mn.monitorActor("David");
        mn.monitorActor("Ruben");
        mn.monitorActor("Pedro");
    }

    /**
     * Returns a list of names of all Actors currently in the system.
     *
     * @return a list of names of all Actors in the system.
     */
    public LinkedList<String> getNames(){
        ActorContext acc= ActorContext.getInstance();
       return acc.getNames();
    }

    /**
     * Returns the number of messages received by a specific Actor.
     *
     * @param name the name of the Actor
     * @return the number of messages received by the Actor
     */
    public int getNumberOfMessages(String name){
        return mn.getNumberOfMessages(name);
    }

    /**
     * Sends a message from one Actor to another.
     *
     * @param name the name of the Actor sending the message.
     * @param name1 the name of the Actor receiving the message.
     * @throws InterruptedException if the thread is interrupted while waiting to send the message.
     */
    public void SendMessage(String name, String name1) throws InterruptedException {
        ActorContext acc= ActorContext.getInstance();
        acc.lookup(name).send(new InsultMe(acc.lookup(name1)));
    }

    /**
     * Returns a list of types of Actors that can be spawned.
     *
     * @return an Array of types of Actors that can be spawned
     */
    public String[] TypeOfActors(){
        return new String[]{"InsultActor", "HelloWorldActor", "InsultActor + FirewallDecorator", "HelloWorldActor + FirewallDecorator", "InsultActor + EncryptionDecorator", "HelloWorldActor + EncryptionDecorator", "RingActor", "PingPongActor"};
    }

    /**
     * Spawns a new Actor of the specified type and name.
     *
     * @param nameActor the name of the Actor to spawn.
     * @param type the type of Actor to spawn.
     * @return a proxy to the newly spawned Actor.
     */
    public Proxy SpawnActors(String nameActor, String type){
        Proxy proxy;

        switch (type) {
            case "InsultActor" -> proxy = ActorContext.spawnActor(new InsultActor(nameActor));
            case "HelloWorldActor" -> proxy = ActorContext.spawnActor(new HelloWorldActor(nameActor));
            case "InsultActor + FirewallDecorator" ->
                    proxy = ActorContext.spawnActor(new FirewallDecorator(new InsultActor(nameActor)));
            case "InsultActor + EncryptionDecorator" ->
                    proxy = ActorContext.spawnActor(new EncryptionDecorator(new InsultActor(nameActor)));
            case "HelloWorldActor + FirewallDecorator" ->
                    proxy = ActorContext.spawnActor(new FirewallDecorator(new HelloWorldActor(nameActor)));
            case "HelloWorldActor + EncryptionDecorator" ->
                    proxy = ActorContext.spawnActor(new EncryptionDecorator(new HelloWorldActor(nameActor)));
            case "RingActor" ->
                    proxy = ActorContext.spawnActor(new RingActor(nameActor));
            case "PingPongActor" -> proxy = ActorContext.spawnActor(new PingPongActor(nameActor));

            default -> proxy=null;
        }
        if(proxy!=null){
            mn.monitorActor(nameActor);
        }
        return proxy;
    }

    /**
     * Gets the name of the ringActors there are.
     *
     * @return a list of the ringActors.
     */
    public LinkedList<String> getRingActors(){
        return ActorContext.getInstance().getRingNames();
    }

    /**
     * Method to use the RingActors. It takes in a linked list of strings.
     *
     * @param list a linked list of strings representing actors names.
     * @throws InterruptedException if any thread has interrupted the current thread.
     *
     */
    public void TestRing(LinkedList<String> list) throws InterruptedException {
        ActorContext acc= ActorContext.getInstance();
        LinkedList<RingActor> ringActorList=new LinkedList<>();
        for(String name:list){
            ringActorList.add((RingActor) acc.lookup(name));
        }
        TestRingActor test=new TestRingActor();
        test.ring(ringActorList);
    }

    /**
     * GetReceivedMessages method returns a map of actors and the messages they received.
     *
     * @return a map of actors and a list of messages they received.
     */
    public Map<Actor, List<Message>> getReceivedMessages() {
        return mn.getReceivedMessages();
    }

    /**
     * Lookup method returns an actor object with the given name.
     *
     * @param name the name of the actor to be looked up.
     * @return an actor object.
     */
    public Actor lookup(String name){
        return ActorContext.getInstance().lookup(name);
    }

    /**
     * GetQueue method returns the message queue of an actor with the given name.
     *
     * @param name the name of the actor whose queue is to be returned.
     * @return a blocking queue of messages.
     */
    public BlockingQueue<Message> getQueue(String name){
        return ActorContext.getInstance().lookup(name).getQueue();
    }

    /**
     * GetPingPongNames method returns the list of ping pong actors names.
     *
     * @return a linked list of strings representing actors names.
     */
    public LinkedList<String> getPingPongNames() {
        return ActorContext.getInstance().getPingPongNames();
    }

    /**
     * TestPingPong method is used to test the PingPongActor class. It takes in two strings.
     *
     * @param name the name of the first PingPongActor.
     * @param name1 the name of the second PingPongActor.
     * @throws InterruptedException if any thread has interrupted the current thread.            .
     */
    public void TestPingPong(String name, String name1) throws InterruptedException {
        ActorContext acc= ActorContext.getInstance();
        new TestPingPong((PingPongActor) acc.lookup(name),(PingPongActor) acc.lookup(name1));
    }
}