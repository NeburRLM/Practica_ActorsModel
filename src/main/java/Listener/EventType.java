package Listener;

/**
 * Enumeration of different event types that can occur in an Actor system.
 * The available event types are:
 * - CREATION - indicates the creation of an actor
 * - FINALIZATION - indicates the finalization of an actor
 * - INCORRECT_FINALIZATION - indicates an incorrect finalization of an actor
 * - RECEIVED_MESSAGE - indicates a message has been received by an actor
 *
 */
public enum EventType {
    CREATION,
    FINALIZATION,
    INCORRECT_FINALIZATION,
    RECEIVED_MESSAGE
}
