package offlinechess;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 * A class that represents a pawn
 * @author Jed Wang
 */
public class Pawn extends AbstractPiece {
    
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public LinkedList<String> allLegalMoves(ChessBoard cb, String currentPosition) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition).getCharRepresentation().equals("P"))) throw new IllegalArgumentException("This isn\'t a pawn!");
        LinkedList<String> output = new LinkedList<>();
        int row = ChessBoard.getRow(currentPosition), column = ChessBoard.getColumn(currentPosition);
        if(isWhite) {
            if(row == 0) assert false : "Pawns should have promoted already!";
            if(row == 7) assert false : "Dafuq white pawns shouldn\'t be on the first rank";
            if(ChessBoard.isValidSquare(column, row-1)) {
                if(cb.isEmptySquare(column, row - 1)) {
                    output.add(ChessBoard.shiftSquare(currentPosition, 0, -1));
                }
            }
            if(ChessBoard.isValidSquare(column, row - 1) && ChessBoard.isValidSquare(column, row - 2)) {
                if(row == 6 && cb.isEmptySquare(column, row - 1) && cb.isEmptySquare(column, row - 2)) {
                    output.add(ChessBoard.shiftSquare(currentPosition, 0, -2));
                }
            }
            if(ChessBoard.isValidSquare(column - 1, row - 1)) {
                if((!cb.isEmptySquare(column - 1, row - 1) && !cb.getPiece(column - 1, row - 1).isWhite) || 
                        (cb.getEnPassant() == null?ChessBoard.shiftSquare(currentPosition, -1, -1) == null
                        :cb.getEnPassant().equals(ChessBoard.shiftSquare(currentPosition, -1, -1)))) {
                    output.add(ChessBoard.shiftSquare(currentPosition, -1, -1));
                }
            }
            if (ChessBoard.isValidSquare(column + 1, row - 1)) {
                if(!cb.isEmptySquare(column + 1, row - 1) && !cb.getPiece(column + 1, row - 1).isWhite || 
                        (cb.getEnPassant() == null?ChessBoard.shiftSquare(currentPosition, 1, -1) == null
                        :cb.getEnPassant().equals(ChessBoard.shiftSquare(currentPosition, 1, -1)))) {
                    output.add(ChessBoard.shiftSquare(currentPosition, 1, -1));
                }
            }
        } else {
            if(row == 7) assert false : "Pawns should have promoted already!";
            if(row == 0) assert false : "Dafuq black pawns shouldn\'t be on the eighth rank";
            if(ChessBoard.isValidSquare(column, row+1)) {
                if(cb.isEmptySquare(column, row + 1)) {
                    output.add(ChessBoard.shiftSquare(currentPosition, 0, 1));
                }
            }
            if(ChessBoard.isValidSquare(column, row + 1) && ChessBoard.isValidSquare(column, row + 2)) {
                if(row == 1 && cb.isEmptySquare(column, row + 1) && cb.isEmptySquare(column, row + 2)) {
                    output.add(ChessBoard.shiftSquare(currentPosition, 0, 2));
                }
            }
            if(ChessBoard.isValidSquare(column - 1, row + 1)) {
                if(!cb.isEmptySquare(column - 1, row + 1) && cb.getPiece(column - 1, row + 1).isWhite 
                        || (cb.getEnPassant() == null?ChessBoard.shiftSquare(currentPosition, -1, 1) == null
                        :cb.getEnPassant().equals(ChessBoard.shiftSquare(currentPosition, -1, 1)))) {
                    output.add(ChessBoard.shiftSquare(currentPosition, -1, 1));
                }
            }
            if(ChessBoard.isValidSquare(column + 1, row + 1)) {
                if(!cb.isEmptySquare(column + 1, row + 1) && cb.getPiece(column + 1, row + 1).isWhite 
                        || (cb.getEnPassant() == null?ChessBoard.shiftSquare(currentPosition, 1, 1) == null
                        :cb.getEnPassant().equals(ChessBoard.shiftSquare(currentPosition, 1, 1)))) {
                    output.add(ChessBoard.shiftSquare(currentPosition, 1, 1));
                }
            }
        }
        return output;
    }

    @Override
    public LinkedList<String> legalCaptures(ChessBoard cb, String currentPosition) {
        if(!ChessBoard.isValidSquare(currentPosition)) throw new IllegalArgumentException("Invalid square");
        if(!(cb.getPiece(currentPosition).getCharRepresentation().equals("P"))) throw new IllegalArgumentException("This isn\'t a pawn!");
        LinkedList<String> output = new LinkedList<>();
        int row = ChessBoard.getRow(currentPosition), column = ChessBoard.getColumn(currentPosition);
        if(isWhite) {
            if(row == 0) assert false : "Pawns should have promoted already!";
            if(row == 7) assert false : "Dafuq white pawns shouldn\'t be on the first rank";
            if(ChessBoard.isValidSquare(column - 1, row - 1)) {
                if(cb.isEmptySquare(column - 1, row - 1) || !cb.getPiece(column - 1, row - 1).isWhite) {
                    output.add(ChessBoard.shiftSquare(currentPosition, -1, -1));
                }
            }
            if (ChessBoard.isValidSquare(column + 1, row - 1)) {
                if(cb.isEmptySquare(column + 1, row - 1) || !cb.getPiece(column + 1, row - 1).isWhite) {
                    output.add(ChessBoard.shiftSquare(currentPosition, 1, -1));
                }
            }
        } else {
            if(row == 7) assert false : "Pawns should have promoted already!";
            if(row == 0) assert false : "Dafuq black pawns shouldn\'t be on the eighth rank";
            if(ChessBoard.isValidSquare(column - 1, row + 1)) {
                if(cb.isEmptySquare(column - 1, row + 1) || cb.getPiece(column - 1, row + 1).isWhite) {
                    output.add(ChessBoard.shiftSquare(currentPosition, -1, 1));
                }
            }
            if(ChessBoard.isValidSquare(column + 1, row + 1)) {
                if(cb.isEmptySquare(column + 1, row + 1) || cb.getPiece(column + 1, row + 1).isWhite) {
                    output.add(ChessBoard.shiftSquare(currentPosition, 1, 1));
                }
            }
        }
        return output;
    }
    
    /**
     * The shape of black and white pawns
     */
    private static GeneralPath pawnGP;
    
    static {
        pawnGP = new GeneralPath();
        // M 22.5 9
        pawnGP.moveTo(22.5, 9);
        // c -2.21 0 -4 1.79 -4 4
        pawnGP.curveTo(20.29, 9, 
                18.5, 10.79, 
                18.5, 13);
        // c 0 .89 .29 1.71 .78 2.38
        pawnGP.curveTo(18.5, 13.89, 
                18.79, 14.71, 
                19.28, 15.38);
        // C 17.33 16.5 16 18.59 16 21
        pawnGP.curveTo(17.33, 16.5, 
                16, 18.59, 
                16, 21);
        // c 0 2.03 .94 3.84 2.41 5.03
        pawnGP.curveTo(16, 23.03, 
                16.94, 24.84, 
                18.41, 26.03);
        // c -3 1.06 -7.41 5.55 -7.41 13.47
        pawnGP.curveTo(15.41, 27.09, 
                11, 31.58, 
                11, 39.5);
        // h 23
        pawnGP.lineTo(34, 39.5);
        // c 0 -7.92 -4.41 -12.41 -7.41 -13.47
        pawnGP.curveTo(34, 31.58, 
                29.59, 27.09, 
                26.59, 26.03);
        // c 1.47 -1.19 2.41 -3 2.41 -5.03
        pawnGP.curveTo(28.06, 24.84, 
                29, 23.03, 
                29, 21);
        // c 0 -2.41 -1.33 -4.5 -3.28 -5.62
        pawnGP.curveTo(29, 18.59, 
                27.67, 16.5, 
                25.72, 15.38);
        // c .49 -.67 .78 -1.49 .78 -2.38
        pawnGP.curveTo(26.21, 14.71, 
                26.5, 13.89, 
                26.5, 13);
        // c 0 -2.21 -1.79 -4 -4 -4
        pawnGP.curveTo(26.5, 10.79, 
                24.71, 9, 
                22.5, 9);
        // z
        pawnGP.closePath();
    }
    
    /**
     * Draws this piece
     * @param g2D the Graphics2D to draw on
     * @param x the X coordinate of the image
     * @param y the Y coordinate of the image
     * @param width the width of the picture
     * @param height the height of the picture
     */
    @Override
    public void draw(Graphics2D g2D, int x, int y, int width, int height) {
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double xStretch = width/45.0, yStretch = height/45.0;
        g2D.setStroke(new BasicStroke((float) (1.5*((xStretch + yStretch)/2)), 
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        GeneralPath gp = new GeneralPath(pawnGP);
        gp.transform(new AffineTransform(xStretch, 0, 0, yStretch, x, y));
        if(isWhite) {
            //g2D.drawImage(white, x, y, width, height, null);
            g2D.setPaint(Color.WHITE);
            g2D.fill(gp);
            g2D.setPaint(Color.BLACK);
            g2D.draw(gp);
        } else {
            g2D.setPaint(Color.BLACK);
            g2D.fill(gp);
            g2D.draw(gp);
        }
    }
    
    /**
     * Draws a ghost of this image
     * @param g2D the Graphics2D to draw on
     * @param x the X coordinate of the image
     * @param y the Y coordinate of the image
     * @param width the width of the picture
     * @param height the height of the picture
     */
    @Override
    public void drawGhost(Graphics2D g2D, int x, int y, int width, int height) {
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double xStretch = width/45.0, yStretch = height/45.0;
        g2D.setStroke(new BasicStroke((float) (1.5*((xStretch + yStretch)/2)), 
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        GeneralPath gp = new GeneralPath(pawnGP);
        gp.transform(new AffineTransform(xStretch, 0, 0, yStretch, x, y));
        
        if(isWhite) {
            g2D.setPaint(new Color(1.0f, 1.0f, 1.0f, 0.3f));
            g2D.fill(gp);
            g2D.setPaint(new Color(0.0f, 0.0f, 0.0f, 0.3f));
            g2D.draw(gp);
        } else {
            g2D.setPaint(new Color(0.0f, 0.0f, 0.0f, 0.3f));
            g2D.fill(gp);
            g2D.draw(gp);
        }
    }

    /**
     * Gets this piece's GeneralPath that is white or black
     * @param isWhite whether the GeneralPath should be white or black
     * @return the GeneralPath that represents this piece
     */
    public static GeneralPath getGeneralPath(boolean isWhite) {
        return pawnGP;
    }
    
    @Override
    public String getCharRepresentation() {
        return "P";
    }
}