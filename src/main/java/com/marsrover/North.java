
public class North implements Direction {
    @Override
    public Point move(Point current) {
        return new Point(current.getX(), current.getY() + 1);
    }

    @Override
    public Direction turnLeft() {
        return new West();
    }

    @Override
    public Direction turnRight() {
        return new East();
    }
}