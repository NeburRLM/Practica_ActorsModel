package InsultService;

import Message.AddInsultMessage;
import Message.GetAllInsultsMessage;
import Message.InsultMe;
import Proxy.ActorProxy;


/**
 * The class InsultService implements the InInsultService interface
 * and provides methods to add, get, and get all insults.
 *
 */
public class InsultService implements InInsultService {
    private final ActorProxy ap;

    /**
     * Constructor that sets the ActorProxy.
     *
     * @param ap the ActorProxy to be set.
     */
    public InsultService(ActorProxy ap) {
        this.ap=ap;
    }

    /**
     * Method to add an insult to the service.
     *
     * @param insult the insult to be added.
     * @throws InterruptedException if thread is interrupted.
     */
    public void addInsult(String insult) throws InterruptedException {
        ap.send(new AddInsultMessage(insult));
    }

    /**
     * Method to get all insults from the service.
     *
     * @return null
     * @throws InterruptedException if thread is interrupted.
     */
    public String getAllInsults() throws InterruptedException {
        ap.send(new GetAllInsultsMessage(ap));
        return null;
    }

    /**
     * Method to get a single insult from the service.
     *
     * @return null
     * @throws InterruptedException if thread is interrupted.
     */
    public String getInsult() throws InterruptedException {
        ap.send(new InsultMe(ap));
        return null;
    }

    /**
     * Method to get a single insult from the service for a given ActorProxy.
     * Method to ReflectiveProxy
     *
     * @param p1 the ActorProxy to get the insult for.
     * @return null
     * @throws InterruptedException if thread is interrupted.
     */
    public String getInsult(ActorProxy p1) throws InterruptedException {
        ap.send(new InsultMe(p1));
        return null;
    }
}
