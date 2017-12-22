package offlinechess;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 * A class that represents the king
 * @author Jed Wang
 */
public class King extends AbstractPiece {
    
    /**
     * Whether this king has moved before - used to check castling
     */
    private boolean moved = false;

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
        boolean canCastle = false;
        if(!moved) {
            canCastle = true;
            output.add(currentPosition);
            // white on 7, black on 0
            int column = (isWhite)?7:0;
            // 1, 2, 3, Queenside
            if(cb.isEmptySquare(column, 1) && cb.isEmptySquare(column, 2) && cb.isEmptySquare(column, 3)) {
                output.add(ChessBoard.shiftSquare(currentPosition, -2, 0));
            }
            // 5, 6, Kingside
            if(cb.isEmptySquare(column, 5) && cb.isEmptySquare(column, 6)) {
                output.add(ChessBoard.shiftSquare(currentPosition, 2, 0));
            }
        }
        LinkedList<String> otherArmy = new LinkedList<>();
        for(int i = 0;i<8;i++) {
            for(int j = 0;j<8;j++) {
                AbstractPiece ap = cb.getPiece(i, j);//lit dude lit
                if(ap != null && (ap.isWhite ^ isWhite)) {
                    if(ap instanceof King && includeOtherKing) {
                        addAllWODuplicates(ap.legalCaptures(cb, ChessBoard.toSquare(i, j)), otherArmy);
                    } else if(!(ap instanceof King)) {
                        addAllWODuplicates(ap.legalCaptures(cb, ChessBoard.toSquare(i, j)), otherArmy);
                    }
                }
            }
        }
        LinkedList<String> difference = difference(otherArmy, output); //lol;
        if(canCastle) {
            if(!difference.contains(currentPosition)) {
                difference.remove(ChessBoard.shiftSquare(currentPosition, -2, 0));
                difference.remove(ChessBoard.shiftSquare(currentPosition, 2, 0));
            }
            if(!difference.contains(ChessBoard.shiftSquare(currentPosition, 1, 0))) {
                difference.remove(ChessBoard.shiftSquare(currentPosition, 2, 0));
            }
            if(!difference.contains(ChessBoard.shiftSquare(currentPosition, -1, 0))) {
                difference.remove(ChessBoard.shiftSquare(currentPosition, -2, 0));
            }
        }
        difference.remove(currentPosition);
        return difference;
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
        LinkedList<V> aCopy = new LinkedList<>(a);
        for(V v:aCopy) {
            if(bCopy.contains(v)) bCopy.remove(v);
        }
        return bCopy;
    }
    
    /**
     * The images for the black and white pieces
     */
    private static BufferedImage black, white;
    
    /**
     * Loads the images for this piece
     * @param b the black image
     * @param w the white image
     * @throws IOException if something goes wrong
     */
    public static void loadImages(URL b, URL w) throws IOException {
        white = ImageIO.read(w);
        black = ImageIO.read(b);
    }
    
    /**
     * Draws this piece
     * @param g the Graphics to draw on
     * @param x the X coordinate of the image
     * @param y the Y coordinate of the image
     * @param width the width of the picture
     * @param height the height of the picture
     */
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        if(isWhite) {
            g.drawImage(white, x, y, width, height, null);
        } else {
            g.drawImage(black, x, y, width, height, null);
        }
    }
    
    public static BufferedImage getImage(boolean isWhite) {
        return (isWhite)?white:black;
    }

    @Override
    public String getCharRepresentation() {
        return "K";
    }
    
    /**
     * Notifies the King that it has moved
     */
    public void notifyOfMove() {
        moved = true;
    }

    /**
     * Returns whether this King has moved
     * @return whether this King has moved
     */
    public boolean isMoved() {
        return moved;
    }
}