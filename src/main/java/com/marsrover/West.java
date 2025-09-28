
public class West implements Direction {
    @Override
    public Point move(Point current) {
        return new Point(current.getX() - 1, current.getY());
    }

    @Override
    public Direction turnLeft() {
        return new South();
    }

    @Override
    public Direction turnRight() {
        return new North();
    }
}