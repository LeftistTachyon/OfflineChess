package offlinechess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A MouseListener for the chess application
 * @author Jed Wang
 */
public class ChessMouseListener implements MouseListener {
    
    /**
     * Represents mouse pressed
     */
    public static final int MOUSE_PRESSED = 0;
    
    /**
     * Represents mouse released
     */
    public static final int MOUSE_RELEASED = 1;
    
    /**
     * Represents mouse clicked
     */
    public static final int MOUSE_CLICKED = 2;
    
    /**
     * The ChessPanel to notify
     */
    private ChessPanel cp;
    
    /**
     * Constructor method.
     */
    public ChessMouseListener() {
        super();
    }
    
    /**
     * Constructor method.
     * @param cp the ChessPanel to notify
     */
    public ChessMouseListener(ChessPanel cp) {
        super();
        this.cp = cp;
    }

    /**
     * Outputs an event
     * @param eventDescription the description of the event
     * @param e the MouseEvent
     */
    private void eventOutput(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on "
                + e.getComponent().getClass().getName()
                + " @ (" + e.getX() + ", " + e.getY() + ").");
    }
     
    @Override
    public void mousePressed(MouseEvent e) {
        eventOutput("Mouse pressed (# of clicks: "
                + e.getClickCount() + ")", e);
        cp.notify(e, MOUSE_PRESSED);
    }
     
    @Override
    public void mouseReleased(MouseEvent e) {
        eventOutput("Mouse released (# of clicks: "
                + e.getClickCount() + ")", e);
        cp.notify(e, MOUSE_RELEASED);
    }
     
    @Override
    public void mouseClicked(MouseEvent e) {
        eventOutput("Mouse clicked (# of clicks: "
                + e.getClickCount() + ")", e);
        cp.notify(e, MOUSE_CLICKED);
    }
     
    @Override
    public void mouseEntered(MouseEvent e) {
        eventOutput("Mouse entered", e);
    }
     
    @Override
    public void mouseExited(MouseEvent e) {
        eventOutput("Mouse exited", e);
    }
}