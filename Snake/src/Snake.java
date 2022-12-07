

public class Snake {
    Point[] points;
    AudColor color = AudColor.blue;
    private Direction nextDirection = Direction.UP;
    public static Direction lastDirection;
    private int  GROW_AMOUNT;
    Point[] newPoint;


    enum Direction {
        RIGHT, DOWN, LEFT, UP;
    }


    public Snake(int length, int x, int y) {

        if(length <= 0) {
            throw new IllegalArgumentException();
        }else {
            points = new Point[length];
            points[0] = new Point(x, y);
        }
    }


    public Snake(int x, int y) {
        this(5, x, y);
    }


    public void paint(AudGraphics g) {
        g.setColor(color);
        int x;
        int y;
        for (int i = 0; i < points.length; i++) {
            if(points[i] != null) {
                x = points[i].getX() * SnakeGame.SQUARE_SIZE;
                y = points[i].getY() * SnakeGame.SQUARE_SIZE;
                g.fillRect(x, y, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
            }
        }
    }


    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }


    public Direction getNextDirection() {
        return this.nextDirection;
    }


    public void step() {
        Point[] points2 = new Point[points.length];
        System.arraycopy(points, 0, points2, 1, points.length - 1);
        switch (nextDirection) {
            case UP:
                points2[0] = new Point(points2[1].getX(),
                        points2[1].getY() - 1);
                break;
            case DOWN:
                points2[0] = new Point(points2[1].getX(),
                        points2[1].getY() + 1);
                break;
            case LEFT:
                points2[0] = new Point(points2[1].getX() - 1,
                        points2[1].getY());
                break;
            case RIGHT:
                points2[0] = new Point(points2[1].getX() + 1,
                        points2[1].getY());
                break;
        }
        lastDirection = nextDirection;
        points = points2;
//        if (lastDirection == Direction.DOWN | lastDirection == Direction.UP) {
//            points[0] = new Point(points[0].getX() + 1, points[0].getY());
//        }
    }


    public boolean collidesWith(GameItem item) {
      return collidesWith(item.getPosition().getX(), item.getPosition().getY());
    }


    public boolean collidesWith(int x, int y) {
        boolean result = false;
        if(points[0].getX() == x && points[0].getY() == y) {
            result =  true;
        }
        return result;
    }


    public boolean collidesWithSelf() {
        boolean result = false;
        for (int i = 1; i < points.length; i++) {
            if(points[i] != null && points[i].getY() == points[0].getY() && points[i].getX() == points[0].getX()) {
                result = true;
            }
        }
        return result;
    }


    public void grow(int GROW_AMOUNT) {

        if(GROW_AMOUNT <= 0) {
            throw new IllegalArgumentException();
        }else {
            newPoint = new Point[points.length + GROW_AMOUNT];
            System.arraycopy(points, 0, newPoint,0, points.length);

            points = newPoint;
        }
    }
}
