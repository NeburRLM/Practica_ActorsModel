package MonitorService;

import ActorContext.ActorContext;
import Listener.ActorListener;
import Listener.Event;
import Runner.Runner;
import java.util.LinkedList;

/**
 * This class MonitorTest implements the interface ActorListener and is used to monitor actors.
 *
 */
public class MonitorTest implements ActorListener {

    /**
     * This method is called when an event occurs in the system.
     *
     * @param event The event that occurred in the system.
     */
    @Override
    public void update(Event event) {
        System.out.println(event.toString());
    }

    /**
     * This method allows to monitor a specific actor by its name.
     *
     * @param name name of the actor.
     */
    public void monitorActor(String name)  {
        Runner runner = ActorContext.getInstance().lookup2(name);
        runner.getPublisher().subscribe(this,runner.getActor());
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
}
