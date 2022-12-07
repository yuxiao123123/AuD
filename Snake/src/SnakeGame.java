import java.util.Random;

public class SnakeGame extends AudGameWindow {
    private int score = 0;
    final static int SQUARE_SIZE = 16;
    final static int GROW_AMOUNT = 2;
    int width;
    int height;
    public long STEP_TIME = 100;
    long lastSnakeUpdate;
    Snake.Direction nextDirection = Snake.Direction.UP;
    Brick[] wall_left = new Brick[getGameAreaHeight()];
    Brick[] wall_right = new Brick[getGameAreaHeight()];
    Brick[] wall_up = new Brick[getGameAreaWidth()];
    Brick[] wall_Down = new Brick[getGameAreaWidth()];
    int length = 2 * (getGameAreaWidth() / SQUARE_SIZE - 2) + 2 * getGameAreaHeight() / SQUARE_SIZE;
    Brick[] wall = new Brick[length];
    Snake snake;
    Apple apple;
    public SnakeGame () {
        setTitle("AuD-Snake - Score: " + score);
        width = getGameAreaWidth() / SQUARE_SIZE;
        height = getGameAreaHeight() / SQUARE_SIZE;
        snake = new Snake(width/2, height/2);
        lastSnakeUpdate = System.currentTimeMillis();
        int index = 0;
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                if ((i == 0) | (i == height - 1) | (j == 0) | (j == width - 1)) {
                    wall[index] = new Brick(j, i);
                    index++;
                }
            }
        }



//        for(int i = 0; i < wall_left.length; i++) {
//            wall_left[i] = new Brick(0,i);
//            wall_right[i] = new Brick(getGameAreaWidth() - SQUARE_SIZE
//                    , i);
//        }
//        for(int i = 0; i < getGameAreaWidth(); i++) {
//            wall_up[i] = new Brick(i, 0);
//            wall_Down[i] = new Brick(i, getGameAreaHeight() - SQUARE_SIZE);
//        }
//
//        for (int i = 0; i < getGameAreaHeight(); i++) {
//            wall[i] = wall_left[i];
//            wall[i + getGameAreaHeight()] = wall_right[i];
//        }
//
//        for (int i = 0; i < getGameAreaWidth() ; i++) {
//            wall[i + 2 * getGameAreaHeight()] = wall_up[i];
//            wall[i + length - getGameAreaWidth()] = wall_Down[i];
//        }
        createNewApple();

    }


    @Override
    public void updateGame(long time) {
        STEP_TIME = time - lastSnakeUpdate;
        snake.step();
        try{
            Thread.sleep(200);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        lastSnakeUpdate = time;
        checkCollisions();

    }


    @Override
    public void paintGame(AudGraphics g) {
        g.setColor(AudColor.white);
        g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());
        snake.paint(g);
        setTitle("AuD-Snake - Score: " + score);
//        for(int j = 0; j < wall_left.length; j++) {
//            wall_left[j].paint(g);
//            wall_right[j].paint(g);
//        }
//        for (int j = 0; j < getGameAreaWidth(); j++) {
//            wall_up[j].paint(g);
//            wall_Down[j].paint(g);
//        }
        for (int j = 0; j < length; j++) {
            wall[j].paint(g);
        }
        apple.paint(g);
    }


    @Override
    public void handleInput(int keyCode) {
        if(keyCode - Snake.lastDirection.ordinal() != 37){
            System.out.println(Snake.lastDirection.ordinal() + " " + keyCode);
            switch (keyCode) {
                case KeyEvent.VK_RIGHT:
                    nextDirection = Snake.Direction.RIGHT;
                    snake.setNextDirection(nextDirection);
                    break;
                case KeyEvent.VK_DOWN:
                    nextDirection = Snake.Direction.DOWN;
                    snake.setNextDirection(nextDirection);
                    break;
                case KeyEvent.VK_LEFT:
                    nextDirection = Snake.Direction.LEFT;
                    snake.setNextDirection(nextDirection);
                    break;
                case KeyEvent.VK_UP:
                    nextDirection = Snake.Direction.UP;
                    snake.setNextDirection(nextDirection);
                    break;
            }
        }
    }


    public void createNewApple() {
        int x;
        int y;
        do{
            x = new Random().nextInt(width - 2) + 1;
            y = new Random().nextInt(height - 2) + 1;
            apple = new Apple(x, y);
        }while (snake.collidesWith(apple));
    }


    public void checkCollisions() {
        for (int i = 0; i < length; i++) {
            if(snake.collidesWith((GameItem) wall[i]) == true) {
                stop();
                showDialog("You died! Score:" + score);
            }
        }
        if (snake.collidesWithSelf() == true) {
            stop();
            showDialog("You died! Score:" + score);
        }
        if(snake.collidesWith(apple) == true) {
            snake.grow(GROW_AMOUNT);
            score += this.apple.getVALUE();
            createNewApple();
        }
    }

    public static void main(String[] args) {
        SnakeGame t1 = new SnakeGame();
        t1.start();
    }
}
