package chessai;

import java.awt.*;
import javax.swing.JPanel;

/**
 * The Panel which you can draw on
 * @author Jed Wang
 */
public class ChessPanel extends JPanel {
    
    /**
     * The chessboard
     */
    private ChessBoard cb;
    
    /**
     * When to stop the game
     */
    private volatile boolean stop = false;
    
    /**
     * Default constructor
     */
    public ChessPanel() {
        cb = new ChessBoard();
        cb.recalculateMoves();
        super.setVisible(true);
    }

    /**
     * Updates the current rendering
     * @param g Graphics to draw on
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    /**
     * Renders the image
     * @param g Graphics to draw on
     */
    @Override
    public void paint(Graphics g) {
        drawBackground(g, Color.WHITE);
    }
    
    /**
     * Paints the background a solid color
     * @param g the Graphics to draw on
     * @param c The color to draw the background
     */
    private void drawBackground(Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    /**
     * A method that starts the redrawing of the chess board constantly
     */
    public void start() {
        new Thread() {
            @Override
            public void run() {
                while(!stop) {
                    repaint();
                    /*try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }*/
                }
                System.out.println("Thread stopped!");
            }
        }.start();
    }
    
    /**
     * Stops this thread and redrawing the chess board.
     */
    public void stop() {
        stop = true;
        cb.printMoves();
    }
}