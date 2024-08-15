import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener {

    private static int FPS = 60;
    private static int delay = FPS / 1000;

    private Timer looper;
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    public static final int BLOCK_SIZE = 30;
    private Color[][] board = new Color[BOARD_HEIGHT][BOARD_WIDTH];

    private Random random;


    private Shape[] shapes = new Shape[7];
    private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"), Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};
    private Shape currentShape;


    public Board() {
        random = new Random();

        shapes[0] = new Shape(new int[][] {
            {1, 1, 1, 1}
        }, this, colors[0]);
        shapes[1] = new Shape(new int[][] {
            {1, 1, 1},
            {0, 1, 0}
        }, this, colors[1]);
        shapes[2] = new Shape(new int[][] {
            {1, 1, 1},
            {1, 0, 0}
        }, this, colors[2]);
        shapes[3] = new Shape(new int[][] {
            {1, 1, 1},
            {0, 0, 1}
        }, this, colors[3]);
        shapes[4] = new Shape(new int[][] {
            {0, 1, 1},
            {1, 1, 0}
        }, this, colors[4]);
        shapes[5] = new Shape(new int[][] {
            {1, 1, 0},
            {0, 1, 1}
        }, this, colors[5]);
        shapes[6] = new Shape(new int[][] {
            {1, 1},
            {1, 1}
        }, this, colors[6]);

        currentShape = shapes[0];


        looper = new Timer(delay, new ActionListener() {
            int n = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                update();
                repaint();
            }
        });
        looper.start();
    }

    private void update() {
        currentShape.update();

    }


    public void setCurrentShape() {
        // Example: randomly select the next shape
//        int nextShapeIndex = (int) (Math.random() * shapes.length);
//        currentShape = shapes[nextShapeIndex];
//        currentShape.setX(4);
//        currentShape.setY(0);

        currentShape = shapes[random.nextInt(shapes.length)];
        currentShape.reset();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        currentShape.render(g);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != null) {
                    g.setColor(board[row][col]);
                    g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }

            }
        }


//draw the board
        g.setColor(Color.white);
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            g.drawLine(0, BLOCK_SIZE * row, BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * row);
        }

        for (int col = 0; col < BOARD_WIDTH + 1; col++) {
            g.drawLine(col * BLOCK_SIZE, 0, col * BLOCK_SIZE, BLOCK_SIZE * BOARD_HEIGHT);
        }
    }

    public Color[][] getboard() {
        return board;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedUp();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentShape.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentShape.moveLeft();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedDown();
        }

    }

    public Color[][] getBoard() {
        return board;
    }
}
