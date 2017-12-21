package offlinechess;

import java.util.ArrayList;

/**
 * A class that records the moves of a game
 * @author Jed Wang
 */
public class MoveRecorder {
    
    /**
     * Represents a pawn
     */
    public static final int PAWN = 0;
    
    /**
     * Represents a knight
     */
    public static final int KNIGHT = 1;
    
    /**
     * Represents a bishop
     */
    public static final int BISHOP = 2;
    
    /**
     * Represents a rook
     */
    public static final int ROOK = 3;
    
    /**
     * Represents a queen
     */
    public static final int QUEEN = 4;
    
    /**
     * Represents a king
     */
    public static final int KING = 5;
    /**
     * The collection of moves made in the game
     */
    private ArrayList<String> moves;
    
    /**
     * Default constructor
     */
    public MoveRecorder() {
        moves = new ArrayList<>();
    }
    
    /**
     * Creates a duplicate of the given MoveRecorder
     * @param mr the MoveRecorder to duplicate
     */
    public MoveRecorder(MoveRecorder mr) {
        moves = new ArrayList<>(mr.moves);
    }

    /**
     * Returns the moves made
     * @return the moves made
     */
    public ArrayList<String> getMoves() {
        return moves;
    }
    
    /**
     * Determines a String that denotes a move <br/>
     * Can be used for en passant captures as well
     * @param fromWhere from where the piece is moved
     * @param toWhere to where the piece moved
     * @param whichPiece which piece moved
     * @param capture whether this piece is capturing something
     * @return the String that denotes the move
     */
    public String toMoveString(String fromWhere, String toWhere, int whichPiece, boolean capture) {
        String captureSymbol = (capture)?"x":"";
        switch(whichPiece) {
            case BISHOP:
                return "B" + captureSymbol + toWhere;
            case KING:
                return "K" + captureSymbol + toWhere;
            case KNIGHT:
                return "N" + captureSymbol + toWhere;
            case PAWN:
                return (capture)?fromWhere.substring(0, 1) + "x" + toWhere:toWhere;
            case QUEEN:
                return "Q" + captureSymbol + toWhere;
            case ROOK:
                return "R" + captureSymbol + toWhere;
            default:
                return "";
        }
    }
    
    /**
     * Determines a String that denotes a move <br/>
     * Used when a move is ambiguous and the file or rank is needed for clarification
     * @param toWhere to where the piece moved
     * @param column which file this piece is moved from
     * @param whichPiece which piece moved
     * @param capture whether this piece is capturing something
     * @return the String that denotes the move
     */
    public String toMoveStringF(String toWhere, int column, int whichPiece, boolean capture) {
        String captureSymbol = (capture)?"x":"";
        String file = "" + (char)(column + 'a');
        switch(whichPiece) {
            case BISHOP:
                return "B" + file + captureSymbol + toWhere;
            case KING:
                return "K" + file + captureSymbol + toWhere;
            case KNIGHT:
                return "N" + file + captureSymbol + toWhere;
            case PAWN:
                return (capture)?file + "x" + toWhere:toWhere;
            case QUEEN:
                return "Q" + file + captureSymbol + toWhere;
            case ROOK:
                return "R" + file + captureSymbol + toWhere;
            default:
                return "";
        }
    }
    
    /**
     * Determines a String that denotes a move <br/>
     * Used when a move is ambiguous and the rank is needed for clarification
     * @param toWhere to where the piece moved
     * @param row which rank this piece is moved from
     * @param whichPiece which piece moved
     * @param capture whether this piece is capturing something
     * @return the String that denotes the move
     */
    public String toMoveStringR(String toWhere, int row, int whichPiece, boolean capture) {
        String captureSymbol = (capture)?"x":"";
        row = 8 - row;
        switch(whichPiece) {
            case BISHOP:
                return "B" + row + captureSymbol + toWhere;
            case KING:
                return "K" + row + captureSymbol + toWhere;
            case KNIGHT:
                return "N" + row + captureSymbol + toWhere;
            case QUEEN:
                return "Q" + row + captureSymbol + toWhere;
            case ROOK:
                return "R" + row + captureSymbol + toWhere;
            default:
                return "";
        }
    }
    
    /**
     * Determines a String that denotes a move <br/>
     * Used when a move is ambiguous and the rank and file is needed for clarification
     * @param fromWhere from where the piece is moved
     * @param toWhere to where the piece moved
     * @param whichPiece which piece moved
     * @param capture whether this piece is capturing something
     * @return the String that denotes the move
     */
    public String toMoveStringFR(String fromWhere, String toWhere, int whichPiece, boolean capture) {
        String captureSymbol = (capture)?"x":"";
        switch(whichPiece) {
            case BISHOP:
                return "B" + fromWhere + captureSymbol + toWhere;
            case KING:
                return "K" + fromWhere + captureSymbol + toWhere;
            case KNIGHT:
                return "N" + fromWhere + captureSymbol + toWhere;
            case QUEEN:
                return "Q" + fromWhere + captureSymbol + toWhere;
            case ROOK:
                return "R" + fromWhere + captureSymbol + toWhere;
            default:
                return "";
        }
    }
    
    /**
     * Determines a String that denotes a castling move
     * @param isKingSide whether the castling move is to the king side
     * @return the String that denotes the move
     */
    public String castlingMoveString(boolean isKingSide) {
        return (isKingSide)?"O-O":"O-O-O";
    }
    
    /**
     * Determines a String that denotes a promotion
     * @param normalMove the move without the promotion
     * @param promotionPiece the piece to promote to
     * @return the String that denotes the move
     */
    public String promotionMoveString(String normalMove, int promotionPiece) {
        switch(promotionPiece) {
            case BISHOP:
                return normalMove + "=B";
            case KNIGHT:
                return normalMove + "=N";
            case QUEEN:
                return normalMove + "=Q";
            case ROOK:
                return normalMove + "=R";
            default:
                return "";
        }
    }
    
    /**
     * Notifies this object of a move and notes it
     * @param before the state of the game before the move
     * @param after the state of the game after the move
     * @param fromWhere from where the piece was moved
     * @param toWhere to where the piece was moved
     */
    public void moved(ChessBoard before, ChessBoard after, String fromWhere, String toWhere) {
        /*moved(before, 
                ChessBoard.getColumn(fromWhere), ChessBoard.getRow(fromWhere), 
                ChessBoard.getColumn(toWhere), ChessBoard.getRow(toWhere));*/
        AbstractPiece toMove = before.getPiece(fromWhere);
        if(toMove == null) throw new IllegalArgumentException("Null piece");
        if(!toMove.isLegalMove(before, fromWhere, toWhere)) throw new IllegalArgumentException("Not a legal move!");
        
        switch(toMove.getCharRepresentation()) {
            case "P":
                if(ChessBoard.getRow(toWhere) == 0 || ChessBoard.getRow(toWhere) == 8) {
                    switch(after.getPiece(toWhere).getCharRepresentation()) {
                        case "N":
                            moves.add(promotionMoveString(toMoveString(fromWhere, toWhere, PAWN, !before.isEmptySquare(toWhere)), KNIGHT));
                            break;
                        case "B":
                            moves.add(promotionMoveString(toMoveString(fromWhere, toWhere, PAWN, !before.isEmptySquare(toWhere)), BISHOP));
                            break;
                        case "R":
                            moves.add(promotionMoveString(toMoveString(fromWhere, toWhere, PAWN, !before.isEmptySquare(toWhere)), ROOK));
                            break;
                        case "Q":
                            moves.add(promotionMoveString(toMoveString(fromWhere, toWhere, PAWN, !before.isEmptySquare(toWhere)), QUEEN));
                            break;
                    }
                } else {
                    moves.add(toMoveString(fromWhere, toWhere, PAWN, !before.isEmptySquare(toWhere)));
                }
                break;
            case "K":
                if(Math.abs(ChessBoard.getColumn(fromWhere)-ChessBoard.getColumn(toWhere)) == 2) {
                    moves.add(castlingMoveString(ChessBoard.getColumn(fromWhere) < ChessBoard.getColumn(toWhere)));
                } else {
                    moves.add(toMoveString(fromWhere, toWhere, KING, !before.isEmptySquare(toWhere)));
                }
                break;
            case "N":
                moves.add(moveString(before, fromWhere, toWhere, toMove, KNIGHT, !before.isEmptySquare(toWhere)));
                break;
            case "B":
                moves.add(moveString(before, fromWhere, toWhere, toMove, BISHOP, !before.isEmptySquare(toWhere)));
                break;
            case "R":
                moves.add(moveString(before, fromWhere, toWhere, toMove, ROOK, !before.isEmptySquare(toWhere)));
                break;
            case "Q":
                moves.add(moveString(before, fromWhere, toWhere, toMove, QUEEN, !before.isEmptySquare(toWhere)));
                break;
            default:
                throw new IllegalArgumentException("Unknown piece");
        }
    }
    
    /**
     * Notifies this object of a move and notes it
     * @param before the state of the game before the move
     * @param after the state of the game after the move
     * @param fromWhereX from which X position the piece was moved
     * @param fromWhereY from which Y position the piece was moved
     * @param toWhereX to which X position the piece was moved
     * @param toWhereY to which Y position the piece was moved
     */
    public void moved(ChessBoard before, ChessBoard after, int fromWhereX, int fromWhereY, int toWhereX, int toWhereY) {
        moved(before, after,  
                ChessBoard.toSquare(fromWhereX, fromWhereY), 
                ChessBoard.toSquare(toWhereX, toWhereY));
    }
    
    /**
     * Determines the String representing a move
     * @param before the state of the game before the move
     * @param fromWhere from where the piece was moved
     * @param toWhere to where the piece was moved
     * @param piece which piece is to be moved
     * @return A String that represents the move
     */
    private String moveString(ChessBoard before, String fromWhere, String toWhere, AbstractPiece piece, int whichPiece, boolean capture) {
       if(!piece.isLegalMove(before, fromWhere, toWhere)) throw new IllegalArgumentException("This isn\'t a legal move");
        ArrayList<String> allPiece = before.findAll(whichPiece, piece.isWhite);
        boolean needRank = false, needFile = false;
        for(String square:allPiece) {
            if(!fromWhere.equals(square)) {
                if(before.getPiece(square).isLegalMove(before, square, toWhere)) {
                    if(ChessBoard.getColumn(square) == ChessBoard.getColumn(fromWhere)) {
                        needRank = true;
                    } else needFile = true;
                }
            }
        }
        if(needRank) {
            if(needFile) {
                return toMoveStringFR(fromWhere, toWhere, whichPiece, capture);
            } else {
                return toMoveStringR(toWhere, ChessBoard.getRow(fromWhere), whichPiece, capture);
            }
        } else{ 
            if(needFile) {
                return toMoveStringF(toWhere, ChessBoard.getColumn(fromWhere), whichPiece, capture);
            } else {
                return toMoveString(fromWhere, toWhere, whichPiece, capture);
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < moves.size(); i++) {
            if(i%2 == 0) {
                output += ((i/2) + 1) + ". ";
            }
            output += moves.get(i) + " ";
        }
        return output;
    }
}