package chessai;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * A Frame for the application
 * @author Jed Wang
 */
public class ChessFrame extends JFrame {
    
    /**
     * The content panel/chess panel
     */
    private ChessPanel cp;
    
    /**
     * Default constructor
     */
    public ChessFrame() {
        super("Offline Chess");
        cp = new ChessPanel();
        setSize(new Dimension(535, 560));
        super.getContentPane().add(cp);
        super.setResizable(false);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cp.start();
    }
    
    /**
     * Stops redrawing the chess board
     */
    public void stop() {
        System.out.println("STOP!");
        cp.stop();
    }
}