package ecosystem;

public class WorldConstants {
    //WORLD
    public final static double[] WINDOW = {-10, 10, -10, 10};

    //TERRAIN
    public final static int NROWS = 20;
    public final static int NCOLS = 30;
    public enum PatchType {
        EMPTY, OBSTACLE, FERTILE, FOOD
    }

    public final static double[] PATCH_TYPE_PROB = {0.2f,0.2f,0.2f,0.4f};
    public final static int NSTATES = PatchType.values().length;
    public static int[][] TERRAIN_COLORS = {
            {200+50,200,60},
            {0,230,255},
            {200,200,60},
            {40,200,20}
    };
    public final static float[] REGENERATION_TIME = {10.f,20.f}; //seconds - valor min e max

    public final static float PREY_SIZE = .2f;
    public final static float PREY_MASS = 1f;
    public final static int INI_PREY_POPULATION = 15;
    public final static float INI_PREY_ENERGY = 30f;
    public final static float ENERGY_FROM_PLANT = 15f;
    public final static float PREY_ENERGY_TO_REPRODUCE = 45f;
    public static int[] PREY_COLOR = {80,100,220};
    //novo
    public final static float PREDATOR_SIZE = .2f;
    public final static float PREDATOR_MASS = 1f;
    public final static int INI_PREDATOR_POPULATION = 15;
    public final static float INI_PREDATOR_ENERGY = 30f;
    public final static float PREDATOR_ENERGY_TO_REPRODUCE = 70f;
    public static int[] PREDATOR_COLOR = {255,0,0};
    //public final static float ENERGY_FROM_PREY = 30f;

}
