package MonitorService;


import Actor.Actor;
import ActorContext.ActorContext;
import Listener.ActorListener;
import Listener.Event;
import Listener.EventType;
import Message.Message;
import Runner.Runner;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * The MonitorService class is used to monitor the activity of actors in a system.
 * It implements the ActorListener interface, which allows it to receive updates on events that occur within the system.
 *
 */
public class MonitorService implements ActorListener {

    private  final BlockingQueue<Event> events = new ArrayBlockingQueue<>(1000);

    /**
     * This method is called when an event occurs in the system.
     *
     * @param event The event that occurred in the system.
     */
    @Override
    public void update(Event event) {
        events.add(event);
    }

    /**
     * This method allows to monitor a specific actor by its name.
     *
     * @param name name of the actor.
     */
    public void monitorActor(String name)  {
        Runner runner = ActorContext.getInstance().lookup2(name);
        runner.getPublisher().subscribe(this, runner.getActor());

    }

    /**
     * This method allows to monitor all actors.
     *
     */
    public void monitorAllActors()  {
        ActorContext acc = ActorContext.getInstance();
        LinkedList<String> ListName = acc.getNames();
        for (String name : ListName) {
            monitorActor(name);
        }
    }

    /**
     * This method returns the number of messages received by each actor, grouped by the level of traffic
     * (LOW, MEDIUM, or HIGH).
     *
     * @return a map where the keys are the levels of traffic and the values are lists of actors that fall into that category.
     *
     */
    public Map<String, List<String>> getTraffic() {
        return events.stream().filter(m -> m.getEvent().equals(EventType.RECEIVED_MESSAGE))
                .collect(Collectors.groupingBy(m -> m.getActor().getName(), Collectors.counting())).entrySet().stream().collect(Collectors.groupingBy(entry -> {
                    long messages = entry.getValue();
                    if (messages < 5) return "LOW";
                    else if (messages < 15) return "MEDIUM";
                    else return "HIGH";
                }, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

    }

    /**
     * This method returns the number of messages received by the specified actor.
     *
     * @param name the name of the actor.
     * @return the number of messages received by the actor.
     */
    public int getNumberOfMessages(String name) {
        return (int) events.stream().filter(m -> m.getEvent().equals(EventType.RECEIVED_MESSAGE)).filter(m -> m.getActor().getName().equals(name)).count();
    }

    /**
     * This method returns a map of all sent messages, grouped by the sender.
     *
     * @return a map where the keys are the names of the actors that sent the messages and the values are lists of the messages sent by that actor.
     */
    public Map<String, List<Message>> getSentMessages() {
        return events.stream().filter(m -> m.getEvent().equals(EventType.RECEIVED_MESSAGE)).collect(Collectors.groupingBy(
                e -> e.getMessage().getSender().getName(),
                Collectors.mapping(Event::getMessage, Collectors.toList())
        ));
    }

    /**
     * Returns a map of actors and the messages they have received.
     *
     * @return a map of actors and the messages they have received.
     */
    public Map<Actor, List<Message>> getReceivedMessages() {
        return events.stream().filter(m -> m.getEvent().equals(EventType.RECEIVED_MESSAGE)).collect(Collectors.groupingBy(
                Event::getActor,
                Collectors.mapping(Event::getMessage, Collectors.toList())
        ));
    }

    /**
     * Returns a map of event types and the events that have occurred.
     *
     * @return a map of event types and the events that have occurred.
     */
    public Map<EventType, List<Event>> getEvents(){
        return events.stream().filter(m -> !m.getEvent().equals(EventType.RECEIVED_MESSAGE))
                .collect(Collectors.groupingBy(Event::getEvent));
    }


}