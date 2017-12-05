package offlinechess;

import java.util.LinkedList;

/**
 * A class that represents the queen
 * @author Jed Wang
 */
public class Queen extends AbstractPiece {

    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public LinkedList<String> legalMoves(ChessBoard cb, String currentPosition) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition) instanceof Queen)) throw new IllegalArgumentException("This isn\'t a queen!");
        LinkedList<String> output = new LinkedList<>();
        String temp = ChessBoard.shiftSquare(currentPosition, 1, 0);
        while(ChessBoard.isValidSquare(temp) && cb.isEmptySquare(temp)) {
            output.add(temp);
            temp = ChessBoard.shiftSquare(temp, 1, 0);
        }
        if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
            if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, -1, 0);
        while(ChessBoard.isValidSquare(temp) && cb.isEmptySquare(temp)) {
            output.add(temp);
            temp = ChessBoard.shiftSquare(temp, -1, 0);
        }
        if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
            if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 0, 1);
        while(ChessBoard.isValidSquare(temp) && cb.isEmptySquare(temp)) {
            output.add(temp);
            temp = ChessBoard.shiftSquare(temp, 0, 1);
        }
        if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
            if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 0, -1);
        while(ChessBoard.isValidSquare(temp) && cb.isEmptySquare(temp)) {
            output.add(temp);
            temp = ChessBoard.shiftSquare(temp,0, -1);
        }
        if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
            if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 1, 1);
        while(ChessBoard.isValidSquare(temp) && cb.isEmptySquare(temp)) {
            output.add(temp);
            temp = ChessBoard.shiftSquare(temp, 1, 1);
        }
        if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
            if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 1, -1);
        while(ChessBoard.isValidSquare(temp) && cb.isEmptySquare(temp)) {
            output.add(temp);
            temp = ChessBoard.shiftSquare(temp, 1, -1);
        }
        if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
            if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, -1, -1);
        while(ChessBoard.isValidSquare(temp) && cb.isEmptySquare(temp)) {
            output.add(temp);
            temp = ChessBoard.shiftSquare(temp, -1, -1);
        }
        if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
            if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, -1, 1);
        while(ChessBoard.isValidSquare(temp) && cb.isEmptySquare(temp)) {
            output.add(temp);
            temp = ChessBoard.shiftSquare(temp, -1, 1);
        }
        if(ChessBoard.isValidSquare(temp) && !cb.isEmptySquare(temp)) {
            if(cb.getPiece(temp).isWhite ^ isWhite) {
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