package Main;

import Actor.RingActor;
import Message.*;
import java.util.LinkedList;

/**
 * The class TestRingActor runs a test for a ring of RingActor objects.
 * It sets the next RingActor for each element in the list and then sends a message to the first element in the list.
 *
 */
public class TestRingActor {

    /**
     * This method runs a test for a ring of RingActor objects.
     * It sets the next RingActor for each element in the list and then sends a message to the first element in the list.
     *
     * @param list The list of RingActor objects that form the ring
     *
     */
    public void ring(LinkedList<RingActor> list) throws InterruptedException {
        for(int i=0;i< list.size()-1;i++){
            list.get(i).setNext(list.get(i+1));
        }
        list.get(list.size()-1).setNext(list.get(0));

        list.get(0).send(new Message(list.get(list.size()-1).getProxy(),"Hello"));

    }

}
