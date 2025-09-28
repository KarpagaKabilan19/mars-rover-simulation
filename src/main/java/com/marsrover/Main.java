
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter grid width: ");
            int width = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter grid height: ");
            int height = Integer.parseInt(scanner.nextLine().trim());
            Terrain terrain = new Terrain(width, height);

            System.out.print("Enter number of obstacles: ");
            int numObstacles = Integer.parseInt(scanner.nextLine().trim());
            Set<Point> obstacles = new HashSet<>();
            for (int i = 0; i < numObstacles; i++) {
                System.out.print("Enter obstacle x,y (e.g., 2,2): ");
                String[] coords = scanner.nextLine().trim().split(",");
                int x = Integer.parseInt(coords[0].trim());
                int y = Integer.parseInt(coords[1].trim());
                obstacles.add(new Point(x, y));
            }
            terrain.addObstacles(obstacles);

            System.out.print("Enter starting x: ");
            int startX = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter starting y: ");
            int startY = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter starting direction (N/S/E/W): ");
            String dirStr = scanner.nextLine().trim().toUpperCase();
            Direction direction = getDirectionFromString(dirStr);
            Rover rover = new Rover(new Point(startX, startY), direction, terrain);

            LOGGER.info("Rover initialized at " + startX + "," + startY + " facing " + dirStr);

            System.out.println("Enter commands (M=move, L=left, R=right, S=status, Q=quit):");
            String input;
            do {
                input = scanner.nextLine().trim().toUpperCase();
                try {
                    if (input.equals("Q")) {
                        LOGGER.info("Simulation ended by user.");
                        break;
                    }
                    if (input.equals("S")) {
                        System.out.println(rover.getStatusReport());
                        continue;
                    }
                    Command command = getCommandFromString(input, rover);
                    rover.executeCommand(command);
                    LOGGER.info("Executed command: " + input);
                } catch (RoverException e) {
                    LOGGER.log(Level.WARNING, "Command error: " + e.getMessage());
                    System.out.println("Error: " + e.getMessage() + ". Retry.");
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e);
                    System.out.println("Invalid input. Retry.");
                }
            } while (true);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid initialization input: " + e.getMessage(), e);
            System.out.println("Invalid input during setup. Exiting.");
        } finally {
            scanner.close();
        }
    }

    private static Direction getDirectionFromString(String dir) {
        if ("N".equals(dir))
            return new North();
        if ("S".equals(dir))
            return new South();
        if ("E".equals(dir))
            return new East();
        if ("W".equals(dir))
            return new West();
        throw new IllegalArgumentException("Invalid direction: " + dir);
    }

    private static Command getCommandFromString(String cmd, Rover rover) {
        if ("M".equals(cmd))
            return new MoveCommand(rover);
        if ("L".equals(cmd))
            return new TurnLeftCommand(rover);
        if ("R".equals(cmd))
            return new TurnRightCommand(rover);
        throw new IllegalArgumentException("Invalid command: " + cmd);
    }
}