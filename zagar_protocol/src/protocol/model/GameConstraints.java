package protocol.model;

public class GameConstraints {

    public static final int MAX_PLAYERS_IN_SESSION = 10;
    public static final int FIELD_WIDTH = 1000;
    public static final int FIELD_HEIGHT = 1000;
    public static final int FOOD_MASS = 8;
    public static final int DEFAULT_PLAYER_CELL_MASS = 10;
    public static final int VIRUS_MASS = 100;
    public static final int FOOD_PER_SECOND_GENERATION = 1;
    public static final int MAX_FOOD_ON_FIELD = 100;
    public static final int NUMBER_OF_VIRUSES = 10;
    public static final int INITIAL_VIRUS_MASS = 50;
    public static final int BLOB_MASS_CREATE = 18;
    public static final int BLOB_MASS_EATEN = 13;
    public static final int BLOB_SPEED = 2;

    private GameConstraints() {
        throw new IllegalAccessError(getClass() + " - utility class");
    }

}
