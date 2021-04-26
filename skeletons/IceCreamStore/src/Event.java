/**
 * @author Brent Yorgey
 * @version November 7, 2016
 *
 * The Event class represents a single event in a discrete event simulation.
 * It keeps track of the time at which the event occurs, and implements
 * the Comparable interface in such a way that events which occur sooner
 * will be "less" than later events. This is so a priority queue can
 * be used to store all the pending events in a simulation; dequeueing the
 * minimum element from the queue will yield the event which will
 * happen next.
 *
 * The Event class is an abstract class, meaning that objects of type Event
 * cannot actually be instantiated.  Instead, a separate class extending the
 * Event class should be created for each different type of event.  The
 * processEvent method is called when an event takes place; each Event
 * subclass should implement it appropriately.
 *
 * Note that a reference to the governing simulation object is passed as a
 * parameter to the processEvent method.  This is simply because events can
 * have effects on the environment, be affected by the environment, or
 * generate other events.  The code in the processEvent method can therefore
 * use the reference to the simulation object to obtain information about the
 * environment, make changes to the environment, or schedule new events
 * (using the scheduleEvent method).
 *
 */
public abstract class Event implements Comparable<Event> {
    private int time;   // the time at which this event is to occur

    public Event ( int time ) {
        this.time = time;
    }

    public int time() {
        return time;
    }

    /** The processEvent method is called on an event when it is the event's
     *  turn to be simulated. Any subclasses of Event should implement it
     *  appropriately.
     *
     *  @param sim  A reference to the running simulation.
     *  @return A string suitable for showing in a log of the simulation.
     */
    public abstract String processEvent( DiscreteEventSimulation sim );

    /* Implement comparison so Events can be stored in a priority queue.
     * Events are sorted by time.
     */
    public int compareTo(Event other) {

    	// TODO implement me!

    	return 0;
    }
}
