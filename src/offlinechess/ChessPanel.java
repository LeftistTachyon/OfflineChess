package offlinechess;

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
    ChessBoard cb;
    
    /**
     * Default constructor
     */
    public ChessPanel() {
        cb = new ChessBoard();
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
}