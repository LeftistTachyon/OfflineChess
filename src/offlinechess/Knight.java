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
    public LinkedList<String> legalMoves(ChessBoard cb, String currentPosition) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition) instanceof Rook)) throw new IllegalArgumentException("This isn\'t a knight!");
        LinkedList<String> output = new LinkedList<>();
        String temp = ChessBoard.shiftSquare(currentPosition, -2, -1);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, -2, 1);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 2, -1);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 2, 1);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 1, -2);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, -1, -2);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 1, 2);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, -1, 2);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        return output;
    }

    @Override
    public LinkedList<String> legalCaptures(ChessBoard cb, String currentPosition) {
        return legalMoves(cb, currentPosition);
    }
    
}