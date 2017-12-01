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
        return moves(cb, currentPosition, true);
    }

    @Override
    public LinkedList<String> legalCaptures(ChessBoard cb, String currentPosition) {
        return moves(cb, currentPosition, false);
    }
    
    /**
     * Determines what moves are necessary
     * @param cb the current state of the game
     * @param currentPosition where the piece currently is
     * @param includeOtherKing whether to discount the other's king
     * @return all King moves discounting the other's king or not
     */
    private LinkedList<String> moves(ChessBoard cb, String currentPosition, boolean includeOtherKing) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition) instanceof King)) throw new IllegalArgumentException("This isn\'t a king!");
        LinkedList<String> output = new LinkedList<>();
        String temp = ChessBoard.shiftSquare(currentPosition, 1, 0);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 1, 1);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 0, 1);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, -1, 1);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, -1, 0);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, -1, -1);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {//doooooodddddd wasussupppp
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 0, -1);
        if(ChessBoard.isValidSquare(temp)) {
            if(cb.isEmptySquare(temp)) {//yoooo mannnnn
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        temp = ChessBoard.shiftSquare(currentPosition, 1, -1);
        if(ChessBoard.isValidSquare(temp)) {//sup sup
            if(cb.isEmptySquare(temp)) {
                output.add(temp);
            } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                output.add(temp);
            }
        }
        LinkedList<String> otherArmy = new LinkedList<>();
        for(int i = 0;i<8;i++) {
            for(int j = 0;j<8;j++) {
                AbstractPiece ap = cb.getPiece(i, j);//lit dude lit
                if(ap != null && ap.isWhite == isWhite) {
                    if(ap instanceof King && includeOtherKing) {
                        output.add(ChessBoard.toSquare(i, j));
                    } else if(!(ap instanceof King)) {
                        output.add(ChessBoard.toSquare(i, j));
                    }
                }
            }
        }
        return output;//lol;
    }
    
}