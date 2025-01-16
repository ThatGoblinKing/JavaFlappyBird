import java.awt.*;

public class Pipe {
    public static int WIDTH = 30;
    private Rectangle rect;

    public Pipe(int startX, int height, boolean onFloor) {
        if (onFloor) {
            rect = new Rectangle(startX, GameManager.FRAME_HEIGHT - height, WIDTH, height);
        } else {
            rect = new Rectangle(startX, 0, WIDTH, height);
        }
    }

    public boolean move(int moveBy) {
        rect.x += moveBy;
        return rect.x + rect.width > 0;

    }

    public Rectangle getRect() {
        return rect;
    }


}
