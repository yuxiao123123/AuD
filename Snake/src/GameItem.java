public abstract class GameItem {
    private Point Position;

    public GameItem(int x, int y) {
        Position = new Point(x, y);
    }

    public Point getPosition() {
        return Position;
    }

    public abstract void paint(AudGraphics g);
}
