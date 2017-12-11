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
    public LinkedList<String> allLegalMoves(ChessBoard cb, String currentPosition) {
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
        String temp;
        if(ChessBoard.isValidShift(currentPosition, 1, 0)) {
            temp = ChessBoard.shiftSquare(currentPosition, 1, 0);
            if(ChessBoard.isValidSquare(temp)) {
                if(cb.isEmptySquare(temp)) {
                    output.add(temp);
                } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, 1, 1)) {
            temp = ChessBoard.shiftSquare(currentPosition, 1, 1);
            if(ChessBoard.isValidSquare(temp)) {
                if(cb.isEmptySquare(temp)) {
                    output.add(temp);
                } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, 0, 1)) {
            temp = ChessBoard.shiftSquare(currentPosition, 0, 1);
            if(ChessBoard.isValidSquare(temp)) {
                if(cb.isEmptySquare(temp)) {
                    output.add(temp);
                } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, -1, 1)) {
            temp = ChessBoard.shiftSquare(currentPosition, -1, 1);
            if(ChessBoard.isValidSquare(temp)) {
                if(cb.isEmptySquare(temp)) {
                    output.add(temp);
                } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, -1, 0)) {
            temp = ChessBoard.shiftSquare(currentPosition, -1, 0);
            if(ChessBoard.isValidSquare(temp)) {
                if(cb.isEmptySquare(temp)) {
                    output.add(temp);
                } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, -1, -1)) {
            temp = ChessBoard.shiftSquare(currentPosition, -1, -1);
            if(ChessBoard.isValidSquare(temp)) {
                if(cb.isEmptySquare(temp)) {
                    output.add(temp);
                } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, 0, -1)) {
            temp = ChessBoard.shiftSquare(currentPosition, 0, -1);
            if(ChessBoard.isValidSquare(temp)) {
                if(cb.isEmptySquare(temp)) {
                    output.add(temp);
                } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        if(ChessBoard.isValidShift(currentPosition, 1, -1)) {
            temp = ChessBoard.shiftSquare(currentPosition, 1, -1);
            if(ChessBoard.isValidSquare(temp)) {
                if(cb.isEmptySquare(temp)) {
                    output.add(temp);
                } else if(cb.getPiece(temp).isWhite ^ isWhite) {
                    output.add(temp);
                }
            }
        }
        LinkedList<String> otherArmy = new LinkedList<>();
        for(int i = 0;i<8;i++) {
            for(int j = 0;j<8;j++) {
                AbstractPiece ap = cb.getPiece(i, j);//lit dude lit
                if(ap != null && (ap.isWhite ^ isWhite)) {
                    if(ap instanceof King && includeOtherKing) {
                        addAllWODuplicates(ap.legalCaptures(cb, ChessBoard.toSquare(i, j)), output);
                    } else if(!(ap instanceof King)) {
                        addAllWODuplicates(ap.legalCaptures(cb, ChessBoard.toSquare(i, j)), output);
                    }
                }
            }
        }
        return difference(otherArmy, output);//lol;
    }
    
    /**
     * Copies from one LinkedList to another, without duplicates.
     * @param <V> the class of the objects contained in the LinkedLists
     * @param from the LinkedList the elements are copied from
     * @param to the LinkedList the elements are copied to
     * @return a LinkedList with the elements copied
     */
    private <V> LinkedList<V> addAllWODuplicates(LinkedList<V> from, LinkedList<V> to) {
        for(V v:from) {
            if(!to.contains(v)) to.add(v);
        }
        return to;
    }
    
    /**
     * Removes elements in one LinkedList that are present in the other.<br>
     *  = b - a.
     * @param <V> the class of the objects contained in the LinkedLists
     * @param a the LinkedList to subtract
     * @param b the LinkedList to subtract from
     * @return the difference of the LinkedLists
     */
    private <V> LinkedList<V> difference(LinkedList<V> a, LinkedList<V> b) {
        LinkedList<V> bCopy = new LinkedList<>(b);
        for(V v:bCopy) {
            if(a.contains(v)) bCopy.remove(v);
        }
        return bCopy;
    }
}