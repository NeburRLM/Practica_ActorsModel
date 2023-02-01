package Ring;

import Actor.RingActor;

import java.util.ArrayList;

/**
 * This class represents a ring, which links a list of RingActors in a circular fashion.
 *
 */
public class Ring {

    /**
     * Connects the RingActors in a ring.
     *
     * @param list The list of RingActors to be connected in a ring.
     * @param n The number of RingActors in the list.
     */
    public void ring(ArrayList<RingActor> list, int n) {
        for(int i=0;i<n-1;i++){
            list.get(i).setNext(list.get(i+1));
        }
        list.get(n-1).setNext(list.get(0));

    }
}
