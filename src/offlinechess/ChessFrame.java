package offlinechess;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * A Frame for the application
 * @author Jed Wang
 */
public class ChessFrame extends JFrame {
    
    /**
     * Default constructor
     */
    public ChessFrame() {
        super("Offline Chess");
        setSize(new Dimension(485, 510));
        super.getContentPane().add(new ChessPanel());
        super.setResizable(false);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}