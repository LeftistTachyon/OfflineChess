package offlinechess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that represents a chess board
 * @author Jed Wang
 */
public class ChessBoard {
    /**
     * The chess board
     */
    private AbstractPiece[][] board;
    
    /**
     * All of the images for the pieces
     */
    private Image blackBishop, blackKing, blackKnight, blackPawn, blackQueen, blackRook, 
            whiteBishop, whiteKing, whiteKnight, whitePawn, whiteQueen, whiteRook;
    
    /**
     * Coordinates of the top left corner
     */
    private int x, y;
    
    /**
     * The selected square
     */
    private String selected = null;
    
    /**
     * Whether the player this board is facing is white
     */
    private boolean playerIsWhite = true; // set it during the server application
    
    /**
     * The MoveRecorder
     */
    private MoveRecorder mr;
    
    /**
     * The size of the individual chess squares
     */
    public final int SQUARE_SIZE = 60; // change to 50 soon
    
    /**
     * The offset to the center needed for a 13-diameter square
     */
    public final int CENTER_OFFSET = (SQUARE_SIZE-13)/2;
    
    /**
     * The sizes of the triangles that surround a piece that can be captured
     */
    public final int TRIANGLE_SIZE = (int) ((11.0/51)*SQUARE_SIZE);
    
    /**
     * Default constructor.
     */
    public ChessBoard() {
        board = new AbstractPiece[8][8];
        initImages();
        addPieces();
        mr = new MoveRecorder();
        x = 0;
        y = 0;
    }
    
    /**
     * Initializes the images
     */
    private void initImages() {
        try {
            Bishop.loadImages(getClass().getResource("/images/falseBishop.png"), getClass().getResource("/images/trueBishop.png"));
        } catch(IOException e) {
            System.err.println("Could not find Bishop file images");
            System.exit(1); 
        }
        
        try {
            King.loadImages(getClass().getResource("/images/falseKing.png"), getClass().getResource("/images/trueKing.png"));
        } catch(IOException e) {
            System.err.println("Could not find King file images");
            System.exit(1); 
        }
        
        try {
            Knight.loadImages(getClass().getResource("/images/falseKnight.png"), getClass().getResource("/images/trueKnight.png"));
        } catch(IOException e) {
            System.err.println("Could not find Knight file images");
            System.exit(1); 
        }
        
        try {
            Pawn.loadImages(getClass().getResource("/images/falsePawn.png"), getClass().getResource("/images/truePawn.png"));
        } catch(IOException e) {
            System.err.println("Could not find Pawn file images");
            System.exit(1); 
        }
        
        try {
            Queen.loadImages(getClass().getResource("/images/falseQueen.png"), getClass().getResource("/images/trueQueen.png"));
        } catch(IOException e) {
            System.err.println("Could not find Queen file images");
            System.exit(1); 
        }
        
        try {
            Rook.loadImages(getClass().getResource("/images/falseRook.png"), getClass().getResource("/images/trueRook.png"));
        } catch(IOException e) {
            System.err.println("Could not find Rook file images");
            System.exit(1); 
        }
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
     * Constructor with coordinates
     * @param x x of the top left corner
     * @param y y of the top left corner
     */
    public ChessBoard(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    
    public ChessBoard(ChessBoard cb) {
        this();
        for (int i = 0; i < cb.board.length; i++) {
            System.arraycopy(cb.board[i], 0, board[i], 0, cb.board[i].length);
        }
    }
    
    /**
     * Draws the current state of the chess board
     * @param g Graphics to draw on
     */
    public void draw(Graphics g) {
        drawCheckers(g);
        drawSelection(g);
        drawPieces(g);
    }
    
    /**
     * Draws the checkered pattern
     * @param g Graphics to draw on
     */
    private void drawCheckers(Graphics g) {
        g.setColor(new Color(240, 217, 181));
        g.fillRect(x, y, x+480, y+480);
        g.setColor(new Color(181, 136, 99));
        for(int i = x;i<480+x;i+=120) {
            for(int j = y;j<480+y;j+=120) {
                g.fillRect(i, j, SQUARE_SIZE, SQUARE_SIZE);
                g.fillRect(i+SQUARE_SIZE, j+SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
    
    /**
     * Draws the pieces on the board.
     * @param g Graphics to draw on
     */
    private void drawPieces(Graphics g) {
        for(int i = 0;i<board.length*SQUARE_SIZE;i+=SQUARE_SIZE) {
            for(int j = 0;j<board[i/SQUARE_SIZE].length*SQUARE_SIZE;j+=SQUARE_SIZE) {
                if(board[i/SQUARE_SIZE][j/SQUARE_SIZE] != null) board[i/SQUARE_SIZE][j/SQUARE_SIZE].draw(g, i+5+x, j+5+y, 50, 50);
            }
        }
    }
    
    private void drawSelection(Graphics g) {
        if(selected == null) return;
        LinkedList<String> moves = getPiece(selected).legalMoves(this, selected);
        Color moveDest = new Color(20, 85, 30, 77);
        g.setColor(moveDest);
        final Point p = ChessPanel.getMouseCoordinates();
        System.out.println((p == null)?"null":"(" + p.x + ", " + p.y + ")");
        for(String s:moves) {
            int x1 = ChessBoard.getColumn(s), 
                    y1 = ChessBoard.getRow(s);
            
            if(p != null) {
                if(isEmptySquare(x1, y1) && (p.x >= x1*SQUARE_SIZE && p.x <= x1*SQUARE_SIZE+SQUARE_SIZE) && (p.y >= y1*SQUARE_SIZE && p.y <= y1*SQUARE_SIZE+SQUARE_SIZE)) {
                    g.fillRect(x1*SQUARE_SIZE, y1*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    continue;
                }
            }
            if(isEmptySquare(x1, y1)) {
                g.fillOval(x1*SQUARE_SIZE+CENTER_OFFSET, y1*SQUARE_SIZE+CENTER_OFFSET, 14, 14);
            } else {
                /*
                1___2
                 | |
                4---3
                */
                Point one = new Point(x1*SQUARE_SIZE, y1*SQUARE_SIZE), 
                        two = new Point(x1*SQUARE_SIZE + SQUARE_SIZE, y1*SQUARE_SIZE), 
                        three = new Point(x1*SQUARE_SIZE + SQUARE_SIZE, y1*SQUARE_SIZE + SQUARE_SIZE), 
                        four = new Point(x1*SQUARE_SIZE, y1*SQUARE_SIZE + SQUARE_SIZE);
                
                g.fillPolygon(new int[]{one.x, one.x, one.x+TRIANGLE_SIZE}, new int[]{one.y, one.y+TRIANGLE_SIZE, one.y}, 3); // 1
                g.fillPolygon(new int[]{two.x, two.x, two.x-TRIANGLE_SIZE}, new int[]{two.y, two.y+TRIANGLE_SIZE, two.y}, 3); // 2
                g.fillPolygon(new int[]{three.x, three.x, three.x-TRIANGLE_SIZE}, new int[]{three.y, three.y-TRIANGLE_SIZE, three.y}, 3); // 3
                g.fillPolygon(new int[]{four.x, four.x, four.x+TRIANGLE_SIZE}, new int[]{four.y, four.y-TRIANGLE_SIZE, four.y}, 3); // 4
            }
        }
        Color selection = new Color(20, 85, 30, 128);
        g.setColor(selection);
        g.fillRect(ChessBoard.getColumn(selected)*SQUARE_SIZE, ChessBoard.getRow(selected)*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
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
     * Determines which piece occupies a space represented by ABSOLUTE coordinates<br>
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
     * Determines which column a square is referring to<br>
     * <br>
     * The columns are ordered as such:<br>
     * |_|_|_|_|_|_|_|_|<br>
     * |0 1 2 3 4 5 6 7<br>
     * |a b c d e f g h
     * @param s a square
     * @return which column the String is referring to
     */
    public static int getColumn(String s) {
        if(isValidSquare(s)) {
            return s.charAt(0)-'a';
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines which row a square is referring to<br>
     * <br>
     * The rows are ordered as such:<br>
     * ____<br>
     * 0 |_<br>
     * 1 |_ <br>
     * 2 |_<br>
     * 3 |_<br>
     * 4 |_<br>
     * 5 |_<br>
     * 6 |_<br>
     * 7 |_<br>
     * ___W
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
     * Checks if a shift is valid
     * @param col current column
     * @param row current row
     * @param colShift how much to shift the columns
     * @param rowShift how much to shift the rows
     * @return whether the shift is valid
     */
    public static boolean isValidShift(int col, int row, int colShift, int rowShift) {
        if(isValidSquare(col, row)) {
            int shiftedCol = col + colShift, shiftedRow = row + rowShift;
            return isValidSquare(shiftedCol, shiftedRow);
        } else return false;
    }
    
    /**
     * Checks if this shift is valid
     * @param s current square
     * @param colShift how much to shift the columns
     * @param rowShift how much to shift the rows
     * @return whether the shift is valid
     */
    public static boolean isValidShift(String s, int colShift, int rowShift) {
        return isValidShift(
                ChessBoard.getColumn(s), ChessBoard.getRow(s), 
                colShift, rowShift
        );
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
    
    /**
     * Moves a piece from fromWhere to toWhere
     * @param fromWhere from where a piece is moved
     * @param toWhere where to move a piece
     */
    public void movePiece(String fromWhere, String toWhere) {
        movePiece(
                ChessBoard.getColumn(fromWhere), 
                ChessBoard.getRow(fromWhere), 
                ChessBoard.getColumn(toWhere), 
                ChessBoard.getRow(toWhere)
        );
    }
    
    /**
     * Moves a piece from fromWhere(X, Y) to toWhere(X, Y)
     * @param fromWhereX from where a piece is moved
     * @param fromWhereY from where a piece is moved
     * @param toWhereX where to move a piece
     * @param toWhereY where to move a piece
     */
    public void movePiece(int fromWhereX, int fromWhereY, int toWhereX, int toWhereY) {
        if(board[fromWhereX][fromWhereY] instanceof King) {
            if(Math.abs(fromWhereX-toWhereX) == 2 && fromWhereY == toWhereY) {
                // Castling
                if(fromWhereX < toWhereX) {
                    // Castling Kingside
                    board[toWhereX-1][toWhereY] = board[7][fromWhereY];
                    board[7][fromWhereY] = null;
                } else {
                    // Castling Queenside
                    board[toWhereX+1][toWhereY] = board[0][fromWhereY];
                    board[0][fromWhereY] = null;
                }
            }
            ((King)(board[fromWhereX][fromWhereY])).notifyOfMove();
        }
        
        ChessBoard thisCopy = new ChessBoard(this);
        board[toWhereX][toWhereY] = board[fromWhereX][fromWhereY];
        board[fromWhereX][fromWhereY] = null;
        System.out.println("Moved: " + playerIsWhite);
        playerIsWhite = !playerIsWhite;
        mr.moved(thisCopy, this, ChessBoard.toSquare(fromWhereX, fromWhereY), ChessBoard.toSquare(toWhereX, toWhereY));
        if(checkMated(playerIsWhite)) System.out.println("Checkmate!\n");
        else if(inCheck(playerIsWhite)) System.out.println("Check!\n");
    }
    
    /**
     * Determines whether one side's king is in check
     * @param isWhite whether the side to check is white (PUN INTENDED)
     * @return whether the side is in check
     */
    public boolean inCheck(boolean isWhite) {
        String kingPos = findKing(isWhite);
        for(int i = 0;i<8;i++) {
            for(int j = 0;j<8;j++) {
                AbstractPiece ap = getPiece(i, j);//lit dude lit
                if(ap != null)
                    if(ap.isWhite != isWhite) 
                        //if(ap.legalCaptures(this, ChessBoard.toSquare(i, j)).contains(kingPos))
                        if(ap.isAllLegalMove(this, ChessBoard.toSquare(i, j), kingPos))
                            return true;
            }
        }
        return false;
    }
    
    /**
     * Determines whether the king is checkmated
     * @param isWhite whether the side to check is white (PUN INTENDED)
     * @return whether the side is checkmated
     */
    public boolean checkMated(boolean isWhite) {
        String kingPos = findKing(isWhite);
        return getPiece(kingPos).legalMoves(this, kingPos).isEmpty() && inCheck(isWhite);
    }
    
    /**
     * Determines where the king is
     * @param isWhite which king, white or black
     * @return the king's position
     */
    private String findKing(boolean isWhite) {
        String kingPos = null;
        for(int i = 0;i<8;i++) {
            for(int j = 0;j<8;j++) {
                AbstractPiece ap = getPiece(i, j);//lit dude lit
                if(ap instanceof King && ap.isWhite == isWhite) kingPos = ChessBoard.toSquare(i, j);
            }
        }
        System.out.println(isWhite + "KingPos: " + kingPos);
        return kingPos;
    }
    
    /**
     * Determines where all of the pieces which fit the criteria
     * @param whichPiece which piece, determined by the number
     * @param isWhite whether the piece is white
     * @return where all of the pieces are
     */
    public ArrayList<String> findAll(int whichPiece, boolean isWhite) {
        String representation;
        switch(whichPiece) {
            case MoveRecorder.BISHOP:
                representation = "B";
                break;
            case MoveRecorder.KING:
                representation = "K";
                break;
            case MoveRecorder.KNIGHT:
                representation = "N";
                break;
            case MoveRecorder.PAWN:
                representation = "P";
                break;
            case MoveRecorder.QUEEN:
                representation = "Q";
                break;
            case MoveRecorder.ROOK:
                representation = "R";
                break;
            default:
                throw new IllegalArgumentException("Unknown piece type: " + whichPiece);
        }
        ArrayList<String> output = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == null) continue;
                if(isWhite == board[i][j].isWhite && board[i][j].getCharRepresentation().equals(representation)) {
                    output.add(toSquare(i, j));
                }
            }
        }
        return output;
    }
    
    /**
     * Notifies this that the board has been clicked on a square
     * @param square where the board has been clicked
     */
    public void clicked(String square) {
        if(selected == null) {
            if(!isEmptySquare(square) && (getPiece(square).isWhite == playerIsWhite)) {
                selected = square;
            }
        } else if(selected.equals(square)) {
            selected = null;
        } else {
            if(!isEmptySquare(square)) {
                if(getPiece(selected).isLegalMove(this, selected, square)) {
                    movePiece(selected, square);
                    selected = null;
                } else {
                    if(getPiece(square).isWhite == playerIsWhite) {
                        selected = square;
                    } else {
                        selected = null;
                    }
                }
            } else {
                if(getPiece(selected).isLegalMove(this, selected, square)) {
                    movePiece(selected, square);
                    selected = null;
                } else selected = null;
            }
        }
        System.out.println("selected: " + selected);
    }
    
    /**
     * Prints the current state of the chess board.
     */
    public void printBoard() {
        for(int i = 0;i<board[0].length;i++) {
            for(int j = 0;j<board.length;j++) {
                AbstractPiece ap = board[j][i];
                if(ap == null) {
                    System.out.print(" ");
                } else System.out.println(ap.getCharRepresentation());
            }
            System.out.println();
        }
    }
    
    public void printMoves() {
        System.out.println(mr.toString());
    }
}