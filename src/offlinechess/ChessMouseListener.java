package offlinechess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A MouseListener for the chess application
 * @author Jed Wang
 */
public class ChessMouseListener implements MouseListener {
    
    /**
     * Constructor method.
     */
    public ChessMouseListener() {
        super();
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
    }
     
    @Override
    public void mouseReleased(MouseEvent e) {
        eventOutput("Mouse released (# of clicks: "
                + e.getClickCount() + ")", e);
    }
     
    @Override
    public void mouseClicked(MouseEvent e) {
        eventOutput("Mouse clicked (# of clicks: "
                + e.getClickCount() + ")", e);
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