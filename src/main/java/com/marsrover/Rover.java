import java.util.logging.Logger;

public class Rover {
    private static final Logger LOGGER = Logger.getLogger(Rover.class.getName());

    private Point position;
    private Direction direction;
    private final Terrain terrain;

    public Rover(Point position, Direction direction, Terrain terrain) {
        if (position == null || direction == null || terrain == null) {
            throw new IllegalArgumentException("Invalid initialization parameters.");
        }
        if (!terrain.isValidPosition(position)) {
            throw new IllegalArgumentException("Starting position out of bounds or on obstacle.");
        }
        this.position = position;
        this.direction = direction;
        this.terrain = terrain;
    }

    public void executeCommand(Command command) throws RoverException {
        command.execute();
    }

    public void move() throws RoverException {
        Point newPosition = direction.move(position);
        if (!terrain.isValidPosition(newPosition)) {
            throw new RoverException("Cannot move: Out of bounds or obstacle detected.");
        }
        position = newPosition;
        LOGGER.info("Moved to " + position);
    }

    public void turnLeft() {
        direction = direction.turnLeft();
        LOGGER.info("Turned left, now facing " + direction.getClass().getSimpleName());
    }

    public void turnRight() {
        direction = direction.turnRight();
        LOGGER.info("Turned right, now facing " + direction.getClass().getSimpleName());
    }

    public String getStatusReport() {
        return "Rover is at (" + position.getX() + ", " + position.getY() + ") facing " +
                direction.getClass().getSimpleName() + ". No Obstacles detected.";
    }
}