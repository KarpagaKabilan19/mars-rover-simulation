
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the grid terrain using Composite Pattern (grid as composite,
 * obstacles as leaves).
 */
public class Terrain {
    private final int width;
    private final int height;
    private final Set<Point> obstacles = new HashSet<>();

    public Terrain(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Grid dimensions must be positive.");
        }
        this.width = width;
        this.height = height;
    }

    public void addObstacles(Set<Point> newObstacles) {
        for (Point p : newObstacles) {
            if (!isWithinBounds(p)) {
                throw new IllegalArgumentException("Obstacle " + p + " out of bounds.");
            }
        }
        obstacles.addAll(newObstacles);
    }

    public boolean isValidPosition(Point position) {
        return isWithinBounds(position) && !obstacles.contains(position);
    }

    private boolean isWithinBounds(Point position) {
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}