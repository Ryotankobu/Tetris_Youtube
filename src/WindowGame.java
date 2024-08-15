import javax.swing.JFrame;
public class WindowGame {
    public static final int WIDTH = 445, HEIGHT = 629;
    private JFrame window;
    private Board board;

    public WindowGame(){
        window = new JFrame("Tetris");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        board = new Board();
        window.add(board);
        window.addKeyListener(board);

        window.setVisible(true);



    }
}
