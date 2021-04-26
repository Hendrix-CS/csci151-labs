import java.util.Queue;

/**
 * @author Brent Yorgey
 * @version November 7, 2016
 *
 * The DiscreteEventSimulation class represents a generic discrete event
 * simulation.  It is an abstract class; an actual simulation can be
 * developed by deriving a subclass from it and implementing the required
 * methods (as well as any additional methods particular to the desired
 * simulation).
 *
 */
public abstract class DiscreteEventSimulation {

	protected int maxTime;
    protected Queue<Event> eventQueue;  // queue holding future events

    /** Construct a new discrete event simulation.
     *
     * @param events   The initial queue of events.
     * @param maxTime  How long to run the simulation.
     */
    public DiscreteEventSimulation ( Queue<Event> events, int maxTime ) {
        this.maxTime = maxTime;
        eventQueue = events;
    }

    /** Run a simulation.
     *
     * @param output  Whether to produce logging output.  If output is true,
     * print the logging messages produced by events.  If output is false,
     * do not print anything.  Setting output to false can be used when e.g.
     * running many simulations for many time steps, to avoid slowing things
     * down (System.out.println is relatively slow).
     *
     */
    public void run(boolean output) {
    	// TODO implement me!  Keep simulating the next event until
    	// there are no more events.
    }

    /** The printStatistics method should be implemented by each derived
     *  simulation class to print any statistics relevant to that
     *  particular simulation.
     */
    public abstract void printStatistics();

    /** The scheduleEvent method is used to schedule a future event.
     *  If the event would happen after the max simulation time, it
     *  is ignored.
     */
    public void scheduleEvent( Event e ) {
    	// TODO implement me!
    }
}
