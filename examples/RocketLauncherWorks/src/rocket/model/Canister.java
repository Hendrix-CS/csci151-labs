package rocket.model;

public class Canister {

    public static final int CANISTER_SIZE = 3;

    private int pellets;

    /***
     * Constructs a full Canister of fuel based on the
     * CANISTER_SIZE constant.
     */
    public Canister() {
        pellets = CANISTER_SIZE;
    }

    /***
     * Consumes a pellet of fuel if there are any available.
     */
    public void useFuel() {
        if (!isEmpty()) {
            pellets--;
        }
    }

    /***
     * Determines whether there is fuel left in the Canister
     * @return true if there are pellets, false otherwise
     */
    public boolean isEmpty() {
        return pellets == 0;
    }

    public int getPellets() {
        return pellets;
    }
}
