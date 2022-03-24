package rocket.model;

public class Rocket {

    // Static final constant for your use later
    public static final int FUEL_SPOTS = 3;

    // Fields
    private Canister[] fuel;
    private boolean onGround;

    /***
     * A Rocket will have spots for fuel canisters based
     * on the FUEL_SPOTS constant, and
     * starts on the ground.
     */
    public Rocket() {
        fuel = new Canister[FUEL_SPOTS];
        onGround = true;
    }

    public boolean isOnGround() {
        return onGround;
    }

    /***
     * If the requested spot is a valid index, and
     * if the Canister in that spot is empty, then
     * replace it with a new Canister and return true,
     * otherwise return false
     * @param spot, the fuel location
     * @return success of adding a new Canister
     */
    public boolean addFuelCanister(int spot) {
        if (spot >= 0 && spot < FUEL_SPOTS &&
                (fuel[spot] == null || fuel[spot].isEmpty())) {
            fuel[spot] = new Canister();
            return true;
        } else {
            return false;
        }
    }

    public Canister getCanister(int spot) {
        if (spot >= 0 && spot < FUEL_SPOTS) {
            return fuel[spot];
        } else {
            return null;
        }
    }

    /***
     * A rocket can blastOff if it is on the ground and
     * at least one of its Canisters still has fuel. When these
     * conditions are met, one pellet of fuel will be used
     * and the rocket will no longer be on the ground.
     */
    public void blastOff() {
        if (onGround) {
            if (useFuel()) {
                onGround = false;
            }
        }
    }

    /***
     * A rocket can land if it is not on the ground and
     * at least one of its Canisters still has fuel. When these
     * conditions are met, one pellet of fuel will be used
     * and the rocket will now be on the ground.
     */
    public void land() {
        if (!onGround) {
            if (useFuel()) {
                onGround = true;
            }
        }
    }

    private boolean useFuel() {
        for (int i = 0; i < FUEL_SPOTS; i++) {
            if (fuel[i] != null && !fuel[i].isEmpty()) {
                fuel[i].useFuel();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "Fuel: ";
        for (int i = 0; i < FUEL_SPOTS; i++) {
            s += "[";
            s += fuel[i] == null ? "x" : fuel[i].getPellets();
            s += "]";
        }
        s += "\nGrounded? " + onGround;
        return s;
    }
}
