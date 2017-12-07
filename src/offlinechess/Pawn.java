package offlinechess;

import java.util.LinkedList;

/**
 * A class that represents a pawn
 * @author Jed Wang
 */
public class Pawn extends AbstractPiece {
    
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public LinkedList<String> legalMoves(ChessBoard cb, String currentPosition) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition) instanceof Pawn)) throw new IllegalArgumentException("This isn\'t a pawn!");
        LinkedList<String> output = new LinkedList<>();
        int row = ChessBoard.getRow(currentPosition), column = ChessBoard.getColumn(currentPosition);
        if(isWhite) {
            if(row == 0) assert false : "Pawns should have promoted already!";
            if(row == 7) assert false : "Dafuq white pawns shouldn\'t be on the first rank";
            if(cb.isEmptySquare(column, row - 1)) {
                output.add(ChessBoard.shiftSquare(currentPosition, 0, -1));
            }
            if(row == 6 && cb.isEmptySquare(column, row - 1) && cb.isEmptySquare(column, row - 2)) {
                output.add(ChessBoard.shiftSquare(currentPosition, 0, -2));
            }
            if(!cb.isEmptySquare(column - 1, row - 1) && !cb.getPiece(column - 1, row - 1).isWhite) {
                output.add(ChessBoard.shiftSquare(currentPosition, -1, -1));
            }
            if(!cb.isEmptySquare(column + 1, row - 1) && !cb.getPiece(column + 1, row - 1).isWhite) {
                output.add(ChessBoard.shiftSquare(currentPosition, 1, -1));
            }
        } else {
            if(row == 7) assert false : "Pawns should have promoted already!";
            if(row == 0) assert false : "Dafuq black pawns shouldn\'t be on the eighth rank";
            if(cb.isEmptySquare(column, row + 1)) {
                output.add(ChessBoard.shiftSquare(currentPosition, 0, 1));
            }
            if(row == 1 && cb.isEmptySquare(column, row + 1) && cb.isEmptySquare(column, row + 2)) {
                output.add(ChessBoard.shiftSquare(currentPosition, 0, 2));
            }
            if(!cb.isEmptySquare(column - 1, row + 1) && cb.getPiece(column - 1, row + 1).isWhite) {
                output.add(ChessBoard.shiftSquare(currentPosition, -1, 1));
            }
            if(!cb.isEmptySquare(column + 1, row + 1) && cb.getPiece(column + 1, row + 1).isWhite) {
                output.add(ChessBoard.shiftSquare(currentPosition, 1, 1));
            }
        }
        return output;
    }

    @Override
    public LinkedList<String> legalCaptures(ChessBoard cb, String currentPosition) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition) instanceof Pawn)) throw new IllegalArgumentException("This isn\'t a pawn!");
        LinkedList<String> output = new LinkedList<>();
        int row = ChessBoard.getRow(currentPosition), column = ChessBoard.getColumn(currentPosition);
        if(isWhite) {
            if(row == 0) assert false : "Pawns should have promoted already!";
            if(row == 7) assert false : "Dafuq white pawns shouldn\'t be on the first rank";
            if(!cb.getPiece(column - 1, row - 1).isWhite) {
                output.add(ChessBoard.shiftSquare(currentPosition, -1, -1));
            }
            if(!cb.getPiece(column + 1, row - 1).isWhite) {
                output.add(ChessBoard.shiftSquare(currentPosition, 1, -1));
            }
        } else {
            if(row == 7) assert false : "Pawns should have promoted already!";
            if(row == 0) assert false : "Dafuq black pawns shouldn\'t be on the eighth rank";
            if(cb.getPiece(column - 1, row + 1).isWhite) {
                output.add(ChessBoard.shiftSquare(currentPosition, -1, 1));
            }
            if(cb.getPiece(column + 1, row + 1).isWhite) {
                output.add(ChessBoard.shiftSquare(currentPosition, 1, 1));
            }
        }
        return output;
    }
}