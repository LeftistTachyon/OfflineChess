package offlinechess;

import java.util.LinkedList;

/**
 * A class that represents a knight
 * @author Jed Wang
 */
public class Knight extends AbstractPiece {

    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isLegalMove(ChessBoard cb, String fromWhere, String toWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<String> legalMoves(ChessBoard cb, String currentPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}