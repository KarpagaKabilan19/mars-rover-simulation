# Mars Rover Simulation

A Java-based simulation of a Mars Rover navigating a grid-based terrain, implementing OOP and design patterns as per Exercise 2 requirements.

## Setup
- Compile the code: `javac -d . src/main/java/com/marsrover/*.java`
- Run the program: `java Main`

## Usage
1. Enter grid width and height when prompted.
2. Specify the number of obstacles and their coordinates (x,y).
3. Provide the starting position (x, y) and direction (N, S, E, W).
4. Enter commands one at a time:
   - `M`: Move forward
   - `L`: Turn left
   - `R`: Turn right
   - `S`: Get status report
   - `Q`: Quit the simulation
5. The program handles obstacles and boundaries, providing error messages if invalid moves are attempted.

## Notes
- The simulation avoids if-else constructs using polymorphism and implements Command and Composite design patterns.
- Logs and exceptions are managed for robust execution.
