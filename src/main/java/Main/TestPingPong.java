package Main;

import Actor.PingPongActor;
import Message.*;

/**
 * TestPingPong class is used to test the functionality of the PingPongActor class.
 *
 */
public class TestPingPong {

    /**
     * Constructor for the TestPingPong class.
     *
     * @param a1 instance of PingPongActor class
     * @param a2 instance of PingPongActor class
     * @throws InterruptedException when the thread is interrupted
     */
    public TestPingPong(PingPongActor a1, PingPongActor a2) throws InterruptedException {
        a2.send(new Message(a1.getProxy(),"Tonto"));
    }
}
