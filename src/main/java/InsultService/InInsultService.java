package InsultService;

/**
 * The InInsultService interface defines the methods that must be implemented by classes
 * that provide functionality for managing insults.
 *
 */
public interface InInsultService {

    /**
     * Adds an insult to the service.
     *
     * @param insult the insult to add
     * @throws InterruptedException if the thread is interrupted while waiting to add the insult
     */
     void addInsult(String insult) throws InterruptedException;

    /**
     * Returns all insults currently stored in the service.
     *
     * @return a string containing all insults
     * @throws InterruptedException if the thread is interrupted while waiting to retrieve the insults
     */
    String getAllInsults() throws InterruptedException;

    /**
     * Returns a single insult from the service.
     *
     * @return a string containing the insult
     * @throws InterruptedException if the thread is interrupted while waiting to retrieve the insult
     */
     String getInsult() throws InterruptedException;


}
