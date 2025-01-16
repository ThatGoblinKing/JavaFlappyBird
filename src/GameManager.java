import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameManager extends JPanel implements MouseListener {
    public static final int GRAVITY = 1, FRAME_WIDTH = 800, FRAME_HEIGHT = 600, PIPE_SPEED = -5;
    private Bird bird = new Bird(50, 250);
    private ArrayList<Pipe> pipes = new ArrayList<>();
    private Random random = new Random();
    private int pipeSpawnTimer = 0, score = 0;


    public GameManager() {

        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.add(this); //Add this panel to frame
        frame.setVisible(true);

        addMouseListener(this);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bird.update();


                movePipes();
                spawnPipes();
                checkCollisions();

                repaint();
            }
        });
        timer.start();
    }

    private void spawnPipes() {
        pipeSpawnTimer++;
        if (pipeSpawnTimer >= 100) {
            int pipeHeight = random.nextInt(650);
            int gap = 150;
            pipes.add(new Pipe(800, pipeHeight, false));
            pipes.add(new Pipe(800, FRAME_HEIGHT - pipeHeight - gap, true));
            pipeSpawnTimer = 0;
        }
    }

    private void movePipes() {
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            if (!pipe.move(PIPE_SPEED)) {
                pipes.remove(pipe);
                i--;
                score++;
            }
        }
    }

    private void checkCollisions() {
        Rectangle birdRect = bird.getRect();
        if (birdRect.y < 0 || birdRect.y + birdRect.height > 600) {
            gameOver();
            return;
        }

        for (Pipe pipe : pipes) {
            if (birdRect.intersects(pipe.getRect())) {
                gameOver();
                return;
            }
        }
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over! Your Score: " + score);
        System.exit(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        Rectangle birdRect = bird.getRect();
        g.setColor(Color.RED);
        g.fillRect(birdRect.x, birdRect.y, birdRect.width, birdRect.height);

        g.setColor(Color.GREEN);
        for (Pipe pipe : pipes) {
            Rectangle pipeRect = pipe.getRect();
            g.fillRect(pipeRect.x, pipeRect.y, pipeRect.width, pipeRect.height);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        bird.flap();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
