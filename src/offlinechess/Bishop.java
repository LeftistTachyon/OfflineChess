package offlinechess;

import java.util.LinkedList;

/**
 * A class that represents the bishop
 * @author Jed Wang
 */
public class Bishop extends AbstractPiece {

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public LinkedList<String> allLegalMoves(ChessBoard cb, String currentPosition) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition) instanceof Bishop)) throw new IllegalArgumentException("This isn\'t a bishop!");
        LinkedList<String> output = new LinkedList<>();
        String temp;
        if(ChessBoard.isValidShift(currentPosition, 1, 1)) {
            temp = ChessBoard.shiftSquare(currentPosition, 1, 1);
            while(cb.isEmptySquare(temp)) {
                output.add(temp);
                try {
                    temp = ChessBoard.shiftSquare(temp, 1, 1);
                } catch(IllegalArgumentException iae) {
                    break;
                }
            }
            if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
                if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, -1, -1)) {
            temp = ChessBoard.shiftSquare(currentPosition, -1, -1);
            while(cb.isEmptySquare(temp)) {
                output.add(temp);
                try {
                    temp = ChessBoard.shiftSquare(temp, -1, -1);
                } catch(IllegalArgumentException iae) {
                    break;
                }
            }
            if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
                if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, -1, 1)) {
            temp = ChessBoard.shiftSquare(currentPosition, -1, 1);
            while(cb.isEmptySquare(temp)) {
                output.add(temp);
                try {
                    temp = ChessBoard.shiftSquare(temp, -1, 1);
                } catch(IllegalArgumentException iae) {
                    break;
                }
            }
            if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
                if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, 1, -1)) {
            temp = ChessBoard.shiftSquare(currentPosition, 1, -1);
            while(cb.isEmptySquare(temp)) {
                output.add(temp);
                try {
                    temp = ChessBoard.shiftSquare(temp, 1, -1);
                } catch(IllegalArgumentException iae) {
                    break;
                }
            }
            if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
                if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        return output;
    }

    @Override
    public LinkedList<String> legalCaptures(ChessBoard cb, String currentPosition) {
        return allLegalMoves(cb, currentPosition);
    }
}