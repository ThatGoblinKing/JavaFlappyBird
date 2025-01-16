import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Bird {
    private static final int FLAP_STRENGTH = -15;
    private static final int BIRD_SIZE = 30;
    private Rectangle rect;
    private int velocity = 0;

    public Bird(int startX, int startY) {
        this.rect = new Rectangle(startX, startY, BIRD_SIZE, BIRD_SIZE);

    }

    public Bird() {
        this.rect = new Rectangle(0, 0, BIRD_SIZE, BIRD_SIZE);
    }

    public void update() {
        this.rect.y += this.velocity;
        this.velocity += GameManager.GRAVITY;
    }

    public void flap() {
        velocity = FLAP_STRENGTH;
    }

    public Rectangle getRect() {
        return this.rect;
    }
}
