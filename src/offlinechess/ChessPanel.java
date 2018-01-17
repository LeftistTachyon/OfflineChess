package offlinechess;

import java.awt.*;
import java.awt.event.MouseEvent;
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
     * The mouse listener
     */
    private ChessMouseListener cml;
    
    /**
     * A reference to the most recent ChessPanel created
     */
    private static ChessPanel _this;
    
    /**
     * When to stop the game
     */
    private volatile boolean stop = false;
    
    /**
     * Default constructor
     */
    public ChessPanel() {
        cml = new ChessMouseListener(this);
        _this = this;
        cb = new ChessBoard();
        cb.recalculateMoves();
        addMouseListener(cml);
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
        cb.draw(g);
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
     * Notifies this of a MouseEvent
     * @param me the MouseEvent
     * @param i what fired this event (See: <code>ChessMouseListener.MOUSE_?</code>)
     */
    public void notify(MouseEvent me, int i) {
        ChessMain.Timer.start();
        String selected;
        switch(i) {
            case ChessMouseListener.MOUSE_CLICKED:
                selected = ChessBoard.toSquare(
                        (me.getX()-cb.getX())/ChessBoard.SQUARE_SIZE, 
                        (me.getY()-cb.getY())/ChessBoard.SQUARE_SIZE
                );
                if(ChessBoard.isValidSquare(selected)) cb.clicked(selected);
                break;
            case ChessMouseListener.MOUSE_PRESSED:
                selected = ChessBoard.toSquare(
                        (me.getX()-cb.getX())/ChessBoard.SQUARE_SIZE, 
                        (me.getY()-cb.getY())/ChessBoard.SQUARE_SIZE
                );
                if(ChessBoard.isValidSquare(selected)) cb.enableDragging(selected);
                break;
            case ChessMouseListener.MOUSE_RELEASED:
                cb.disableDragging();
                break;
        }
        repaint();
    }
    
    /**
     * Determines where the mouse currently is
     * @return A point representing the mouse's position
     */
    public static Point getMouseCoordinates() {
        return _this.getMousePosition();
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