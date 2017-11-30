package offlinechess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * A class that represents a chess board
 * @author Jed Wang
 */
public class ChessBoard {
    private AbstractPiece[][] board;
    private Image blackBishop, blackKing, blackKinght, blackPawn, blackQueen, blackRook, 
            whiteBishop, whiteKing, whiteKnight, whitePawn, whiteQueen, whiteRook;
    
    /**
     * Default constructor.
     */
    public ChessBoard() {
        board = new AbstractPiece[8][8];
        String filename = "null.wtf";
        try {
            filename = "truePawn.png";
            URL url = getClass().getResource("/images/" + filename);
            whitePawn = ImageIO.read(url);
            filename = "trueQueen.png";
            url = getClass().getResource("/images/" + filename);
            whiteQueen = ImageIO.read(url);
            filename = "truePawn.png";
            url = getClass().getResource("/images/" + filename);
            whitePawn = ImageIO.read(url);
            filename = "trueQueen.png";
            url = getClass().getResource("/images/" + filename);
            whiteQueen = ImageIO.read(url);
            filename = "truePawn.png";
            url = getClass().getResource("/images/" + filename);
            whitePawn = ImageIO.read(url);
            filename = "trueQueen.png";
            url = getClass().getResource("/images/" + filename);
            whiteQueen = ImageIO.read(url);
        }
        catch(IOException e) {
            System.out.println("Could not find file images/" + filename);
            System.exit(1); 
        }
        addPieces();
    }
    
    /**
     * Adds the starting pieces to a chessboard.
     */
    private void addPieces() {
        for(int i = 0;i<8;i++) {
            board[i][1] = new Pawn(false);
            board[i][6] = new Pawn(true);
        }
        
        board[0][0] = new Rook(false);
        board[1][0] = new Knight(false);
        board[2][0] = new Bishop(false);
        board[3][0] = new Queen(false);
        board[4][0] = new King(false);
        board[5][0] = new Bishop(false);
        board[6][0] = new Knight(false);
        board[7][0] = new Rook(false);
        
        board[0][7] = new Rook(true);
        board[1][7] = new Knight(true);
        board[2][7] = new Bishop(true);
        board[3][7] = new Queen(true);
        board[4][7] = new King(true);
        board[5][7] = new Bishop(true);
        board[6][7] = new Knight(true);
        board[7][7] = new Rook(true);
    }
    
    /**
     * Draws the current state of the chess board
     * @param g Graphics to draw on
     */
    public void draw(Graphics g) {
        drawCheckers(g);
    }
    
    /**
     * Draws the checkered pattern
     * @param g Graphics to draw on
     */
    private void drawCheckers(Graphics g) {
        g.setColor(Color.BLACK);
        for(int i = 0;i<400;i+=100) {
            for(int j = 0;j<400;j+=100) {
                g.fillRect(i, j, 50, 50);
                g.fillRect(i+50, j+50, 50, 50);
            }
        }
    }
    
    /**
     * Determines which piece occupies a square
     * @param square a square
     * @return the piece on that square, and if none, null
     */
    public AbstractPiece getPiece(String square) {
        if(isValidSquare(square)) {
            return board[getColumn(square)][getRow(square)];
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines which piece occupies a space represented by ABSOLUTE coordinates
     * i.e. (0, 0) represents the top left corner
     * @param col the ABSOLUTE column
     * @param row the ABSOLUTE row
     * @return the piece on that square, and if none, null
     */
    public AbstractPiece getPiece(int col, int row) {
        if(isValidSquare(col, row)) {
            return board[col][row];
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines whether a square is empty
     * @param square a square
     * @return whether that square is empty
     */
    public boolean isEmptySquare(String square) {
        return getPiece(square) == null;
    }
    
    /**
     * Determines whether a space represented by ABSOLUTE coordinates is empty
     * @param col the ABSOLUTE column
     * @param row the ABSOLUTE row
     * @return whether that square is empty
     */
    public boolean isEmptySquare(int col, int row) {
        return getPiece(col, row) == null;
    }
    
    /**
     * Determines the validity of the square
     * @param s a square
     * @return whether the square is valid
     */
    public static boolean isValidSquare(String s) {
        if(s.length() == 2) {
            return Character.isLowerCase(s.charAt(0)) && Character.isDigit(s.charAt(1));
        } else return false;
    }
    
    /**
     * Determines the validity of the square
     * @param col the ABSOLUTE column
     * @param row the ABSOLUTE row
     * @return whether the square is valid
     */
    public static boolean isValidSquare(int col, int row) {
        return col >= 0 && col <= 7 && row >= 0 && row <= 7;
    }
    
    /**
     * Determines which column a square is referring to
     * 
     * The columns are ordered as such:
     * |_|_|_|_|_|_|_|_|
     *  0 1 2 3 4 5 6 7
     *  a b c d e f g h
     * @param s a square
     * @return which column the String is referring to
     */
    public static int getColumn(String s) {
        if(isValidSquare(s)) {
            return s.charAt(0)-'a';
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines which row a square is referring to
     * 
     * The rows are ordered as such:
     *    _
     * 0 |_
     * 1 |_ 
     * 2 |_
     * 3 |_
     * 4 |_
     * 5 |_
     * 6 |_
     * 7 |_
     *    W
     * @param s
     * @return 
     */
    public static int getRow(String s) {
        if(isValidSquare(s)) {
            return 8 - Integer.parseInt(s.charAt(1) + "");
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines where a square is after a shift (a.k.a. moving it left and right, up and down)
     * @param col current column
     * @param row current row
     * @param colShift how much to shift the columns
     * @param rowShift how much to shift the rows
     * @return the shifted square
     */
    public static String shiftSquare(int col, int row, int colShift, int rowShift) {
        if(isValidSquare(col, row)) {
            int shiftedCol = col + colShift, shiftedRow = row + rowShift;
            if(isValidSquare(shiftedCol, shiftedRow)) {
                return toSquare(shiftedCol, shiftedRow);
            } else throw new IllegalArgumentException("Invalid shift");
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines where a square is after a shift (a.k.a. moving it left and right, up and down)
     * @param s the current square
     * @param colShift how much to shift the columns
     * @param rowShift how much to shift the rows
     * @return the shifted square
     */
    public static String shiftSquare(String s, int colShift, int rowShift) {
        if(isValidSquare(s)) {
            int col = getColumn(s), row = getRow(s);
            int shiftedCol = col + colShift, shiftedRow = row + rowShift;
            if(isValidSquare(shiftedCol, shiftedRow)) {
                return toSquare(shiftedCol, shiftedRow);
            } else throw new IllegalArgumentException("Invalid shift");
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines the square represented by the row and column
     * @param column the ABSOLUTE column
     * @param row the ABSOLUTE row
     * @return the square that is represented by the row and column
     */
    public static String toSquare(int column, int row) {
        return "" + (char)('a' + column) + (8 - row);
    }
}