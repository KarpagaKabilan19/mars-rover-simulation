public interface Direction {
    Point move(Point current);

    Direction turnLeft();

    Direction turnRight();
}