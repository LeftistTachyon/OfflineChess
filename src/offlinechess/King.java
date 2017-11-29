package offlinechess;

import java.util.LinkedList;

/**
 * A class that represents the king
 * @author Jed Wang
 */
public class King extends AbstractPiece {

    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public LinkedList<String> legalMoves(ChessBoard cb, String currentPosition) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition) instanceof King)) throw new IllegalArgumentException("This isn\'t a king!");
        LinkedList<String> output = new LinkedList<>();
        String temp = ChessBoard.shiftSquare(currentPosition, 1, 0);
        return output;
    }

    @Override
    public LinkedList<String> legalCaptures(ChessBoard cb, String currentPosition) {
        return legalMoves(cb, currentPosition);
    }
    
}