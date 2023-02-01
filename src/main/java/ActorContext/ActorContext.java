package ActorContext;

import Actor.Actor;
import Actor.InsultActor;
import Actor.PingPongActor;
import Actor.RingActor;
import Proxy.ActorProxy;
import Publisher.Publisher;
import Runner.Runner;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * The ActorContext class is responsible for managing the lifecycle of actors, including spawning and looking up actors.
 * This class is a singleton, meaning that only one instance of it can exist at a time. This is done by having a private constructor and a static getInstance method.
 */
public class ActorContext {

	/**
	 * Actors registered.
	 */
	private static final HashMap<String, Runner> actorsHash = new HashMap<>();

	/**
	 * Instance of ActorContext.
	 */
	private static final ActorContext instance = new ActorContext();

	/**
	 * Returns the singleton instance of the ActorContext class.
	 *
	 * @return the singleton instance of the ActorContext class.
	 */
	public static ActorContext getInstance() {
		return instance;
	}

	/**
	 * Spawns a new actor and adds it to the HashMap of actors.
	 *
	 * @param actor the actor object to be spawned.
	 * @return an ActorProxy object that can be used to send messages to the actor
	 */
	public static ActorProxy spawnActor( Actor actor) {
		ActorProxy nuevoActorProxy = new ActorProxy(actor);
		actor.setProxy(nuevoActorProxy);
		Runner r = new Runner(actor,new Publisher());
		actorsHash.put(actor.getName(), r);

		r.start();

		return nuevoActorProxy;
	}

	/**
	 * Looks up an actor by name in the actorsHash map.
	 *
	 * @param name The name of the actor to be looked up.
	 * @return The actor with the specified name, or null if no such
	 */
	public Actor lookup(String name) {
		return actorsHash.get(name).getActor();
	}

	/**
	 * Looks up the Runner by name in the actorsHash map.
	 *
	 * @param name The name of the actor to be looked up.
	 * @return The Runner with the specified name, or null if no such Runner exists.
	 */
	public Runner lookup2(String name) {
		return actorsHash.get(name);
	}

	/**
	 * Returns a list of all actor names in the actorsHash map.
	 *
	 * @return A list of all actor names in the actorsHash map.
	 */
	public LinkedList<String> getNames() {
        LinkedList<String> actorsNames = new LinkedList<>();
        actorsHash.forEach((k, v) -> actorsNames.add(k));
        return actorsNames;
    }

	/**
	 * Returns a list of all actor names of type InsultActor in the actorsHash map.
	 *
	 * @return A list of all actor names of type InsultActor in the actorsHash map.
	 */
	public LinkedList<String> getInsultNames() {
		LinkedList<String> actorsNames = new LinkedList<>();
		actorsHash.forEach((k, v) -> {
			if (v.getActor() instanceof InsultActor) {
				actorsNames.add(k);
			}
		});
		return actorsNames;
	}

	/**
	 * Returns a list of the names of all PingPongActor actors.
	 *
	 * @return a list of the names of all PingPongActor actors.
	 */
	public LinkedList<String> getPingPongNames() {
		LinkedList<String> actorsNames = new LinkedList<>();
		actorsHash.forEach((k, v) -> {
			if (v.getActor() instanceof PingPongActor) {
				actorsNames.add(k);
			}
		});
		return actorsNames;
	}

	/**
	 * Returns a list of the names of all RingActor actors.
	 *
	 * @return a list of the names of all Ring actors.
	 */
	public LinkedList<String> getRingNames() {
		LinkedList<String> actorsNames = new LinkedList<>();
		actorsHash.forEach((k, v) -> {
			if (v.getActor() instanceof RingActor) {
				actorsNames.add(k);
			}
		});
		return actorsNames;
	}


}
