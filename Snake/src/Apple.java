public class Apple  extends  GameItem{
    private int VALUE;
    public static int nextValue = 0;
    public Apple(int x, int y) {
        super(x, y);
//        int newValue = VALUE + nextValue;
        nextValue++;
        VALUE = nextValue;
    }

//    public static void setVALUE(int VALUE) {
//        Apple.VALUE = VALUE;
//    }
//    获取数值；

    public int getVALUE() {
        return VALUE;
    }

    public void paint(AudGraphics g) {
        g.setColor(AudColor.RED);
        g.fillOval(getPosition().getX() * SnakeGame.SQUARE_SIZE,
                getPosition().getY() * SnakeGame.SQUARE_SIZE,
                SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
    }
}
