package exam1;

public class Garden {
    private Dirt[] plots;
    private String type;

    // Initialize the plots so there are numPlots available spaces, and set them
// all to EMPTY. Also save the type of plants grown.
    public Garden(int numPlots, String type) {
        for (int i = 0; i < numPlots; i++) {
            plots[i] = Dirt.EMPTY;
        }

    }

    // If the specified garden plot is EMPTY, convert it to a SEED.
    public void plant(int plotNum) {
        if (plots[plotNum] == Dirt.EMPTY) {
            plots[plotNum] = Dirt.SEED;
        }

    }

    // Check each plot, and if it is a SEED, set it equal to a PLANT.
    public void water() {
        for (int i = 0; i < plots.length; i++) {
            if (plots[i] == Dirt.SEED) {
                plots[i] = Dirt.PLANT;
            }
        }
    }

    // Try to harvest a plant at the specified garden plot. If you find a PLANT there,
// set it to EMPTY and return the type of plant grown in this garden. If there is a SEED,
// return the string ""Needs Water!"". Otherwise return ""Nothing but Dirt...""
    public String harvest(int plotNum) {
        if (plots[plotNum] == Dirt.PLANT) {
            plots[plotNum] = Dirt.EMPTY;
            return type;
        }
        if (plots[plotNum] == Dirt.SEED) {
            return (" Needs Water !");
        } else {
            return (" Nothing but Dirt...");
        }


    }
}