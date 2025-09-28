
public class East implements Direction {
    @Override
    public Point move(Point current) {
        return new Point(current.getX() + 1, current.getY());
    }

    @Override
    public Direction turnLeft() {
        return new North();
    }

    @Override
    public Direction turnRight() {
        return new South();
    }
}