package offlinechess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
     * Which column the pawn is to be promoting.<br>
     * -1 stands for no promotion<br>
     * Controls the promotion dialog
     */
    private int promotion = -1;
    
    /**
     * The squares open for en passant.<br>
     * null stands for no open squares<br>
     * Controls en passant
     */
    private String enPassant = null;
    
    /**
     * A Map of all of the legal moves possible
     */
    private HashMap<String, LinkedList<String>> allLegalMoves;
    
    /**
     * A Map of the king's position
     */
    private HashMap<Boolean, String> kingPos;
    
    /**
     * The last move by a piece.<br>
     * Controls drawing the last move
     */
    private String lastMoveFrom = null, lastMoveTo = null;
    
    /**
     * The piece's square to be dragging from.<br>
     * Controls dragging pieces
     */
    private String draggingFrom = null;
    
    /**
     * The piece's square to be dragging from.<br>
     * Controls playing around with pieces
     */
    private String fakeDraggingFrom = null;
    
    /**
     * The last known non-null point the mouse was at.<br>
     * Controls dragging pieces
     */
    private Point lastPoint;
    
    /**
     * The square a pawn is promoting from<br>
     * Controls promotion
     */
    private String promotingFrom = null;
    
    /**
     * Counts how many times a position repeats<br>
     * Controls threefold repetition
     */
    private HashMap<String, Integer> positions;
    
    /**
     * From which perspective the board is to be drawn.
     */
    private boolean fromPerspective = true;
    
    /**
     * Which side this player can move pieces for.<br>
     * 0 == WHITE<br>
     * 1 == BLACK<br>
     * 2 == BOTH
     */
    private int manipulable = 1;
    
    /**
     * The size of the individual chess squares
     */
    public static final int SQUARE_SIZE = 64; // change to 64 soon
    
    /**
     * The offset to the center needed for a 13-diameter circle
     */
    public static final int CENTER_OFFSET = (SQUARE_SIZE-13)/2;
    
    /**
     * The sizes of the triangles that surround a piece that can be captured
     */
    public static final int TRIANGLE_SIZE = (int) ((11.0/51)*SQUARE_SIZE);
    
    /**
     * The size of the text
     */
    public static final int TEXT_SIZE = 12;
    
    /**
     * A number which represents White is manipulable.
     */
    public static final int WHITE_MANIPULABLE = 0;
    
    /**
     * A number which represents Black is manipulable.
     */
    public static final int BLACK_MANIPULABLE = 1;
    
    /**
     * A number which represents both sides is manipulable.
     */
    public static final int BOTH_MANIPULABLE = 2;
    
    /**
     * Default constructor.
     */
    public ChessBoard() {
        board = new AbstractPiece[8][8];
        initImages();
        kingPos = new HashMap<>();
        addPieces();
        mr = new MoveRecorder();
        allLegalMoves = new HashMap<>();
        positions = new HashMap<>();
        x = 0;
        y = 0;
        lastPoint = ChessPanel.getMouseCoordinates();
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
        
        kingPos.put(true, "e1");
        kingPos.put(false, "e8");
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
    
    /**
     * Constructor from a previous ChessBoard
     * @param cb the ChessBoard to duplicate
     */
    public ChessBoard(ChessBoard cb) {
        this();
        for(int i = 0; i < cb.board.length; i++) {
            System.arraycopy(cb.board[i], 0, board[i], 0, cb.board[i].length);
        }
        this.enPassant = cb.enPassant;
    }
    
    /**
     * Draws the current state of the chess board
     * @param g Graphics to draw on
     */
    public void draw(Graphics g) {
        Point temp = ChessPanel.getMouseCoordinates();
        if(temp != null) 
            lastPoint = temp;
        
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawCheckers(g2D);
        drawCheck(g2D);
        drawSelection(g2D);
        drawPieces(g2D);
        drawDraggedPiece(g2D);
        drawPromotions(g2D);
    }
    
    /**
     * Draws the checkered pattern
     * @param g2D Graphics2D to draw on
     */
    private void drawCheckers(Graphics2D g2D) {
        g2D.setColor(new Color(181, 136, 99));
        g2D.fillRect(x, y, 8*SQUARE_SIZE, 8*SQUARE_SIZE);
        g2D.setColor(new Color(240, 217, 181));
        for(int i = x;i<8*SQUARE_SIZE+x;i+=SQUARE_SIZE*2) {
            for(int j = y;j<8*SQUARE_SIZE+y;j+=SQUARE_SIZE*2) {
                g2D.fillRect(i, j, SQUARE_SIZE, SQUARE_SIZE);
                g2D.fillRect(i+SQUARE_SIZE, j+SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
        g2D.setColor(new Color(155, 199, 0, 105));
        if(lastMoveFrom != null) {
            if(fromPerspective) {
                g2D.fillRect(getColumn(lastMoveFrom)*SQUARE_SIZE+x, 
                        getRow(lastMoveFrom)*SQUARE_SIZE+y, 
                        SQUARE_SIZE, SQUARE_SIZE);
            } else {
                g2D.fillRect((7-getColumn(lastMoveFrom))*SQUARE_SIZE+x, 
                        (7-getRow(lastMoveFrom))*SQUARE_SIZE+y, 
                        SQUARE_SIZE, SQUARE_SIZE);
            }
        }
        if(lastMoveTo != null) {
            if(fromPerspective) {
                g2D.fillRect(getColumn(lastMoveTo)*SQUARE_SIZE+x, 
                        getRow(lastMoveTo)*SQUARE_SIZE+y, 
                        SQUARE_SIZE, SQUARE_SIZE);
            } else {
                g2D.fillRect((7-getColumn(lastMoveTo))*SQUARE_SIZE+x, 
                        (7-getRow(lastMoveTo))*SQUARE_SIZE+y, 
                        SQUARE_SIZE, SQUARE_SIZE);
            }
        }
        
        g2D.setPaint(Color.BLACK);
        g2D.setFont(new Font("Century Gothic", 0, 12)); // NOI18N
        if(fromPerspective) {
            for (int i = 0; i < 8; i++) {
                g2D.drawString((char) ('a' + i) + "",
                        SQUARE_SIZE * i + (SQUARE_SIZE / 2) - 3 + x, 
                        SQUARE_SIZE * 8 + 12 + y);
                g2D.drawString((8 - i) + "", SQUARE_SIZE * 8 + 3 + x,
                        SQUARE_SIZE * i + (SQUARE_SIZE / 2) + 6 + y);
            }
        } else {
            for (int i = 0; i < 8; i++) {
                g2D.drawString((char) ('a' + 7 - i) + "",
                        SQUARE_SIZE * i + (SQUARE_SIZE / 2) - 3 + x, 
                        SQUARE_SIZE * 8 + 12 + y);
                g2D.drawString((i + 1) + "", SQUARE_SIZE * 8 + 3 + x,
                        SQUARE_SIZE * i + (SQUARE_SIZE / 2) + 6 + y);
            }
        }
    }
    
    /**
     * Draws the pieces on the board.
     * @param g2D Graphics2D to draw on
     */
    private void drawPieces(Graphics2D g2D) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] != null) {
                    if (toSquare(i, j).equals(draggingFrom) || toSquare(i, j).equals(fakeDraggingFrom)) {
                        if(fromPerspective) {
                            board[i][j].drawGhost(g2D, (i * SQUARE_SIZE) + 7 + x, 
                                    (j * SQUARE_SIZE) + 7 + y, 50, 50);
                        } else {
                            board[i][j].drawGhost(g2D, ((7-i) * SQUARE_SIZE) + 7 + x, 
                                    ((7-j) * SQUARE_SIZE) + 7 + y, 50, 50);
                        }
                    } else {
                        if(fromPerspective) {
                            board[i][j].draw(g2D, (i * SQUARE_SIZE) + 7 + x, 
                                    (j * SQUARE_SIZE) + 7 + y, 50, 50);
                        } else {
                            board[i][j].draw(g2D, ((7-i) * SQUARE_SIZE) + 7 + x, 
                                    ((7-j) * SQUARE_SIZE) + 7 + y, 50, 50);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Draws the selected pieces
     * @param g2D Graphics2D to draw on
     */
    private void drawSelection(Graphics2D g2D) {
        String selection;
        if(draggingFrom == null) {
            if(selected == null) {
                return;
            } else selection = selected;
        } else selection = draggingFrom;
        LinkedList<String> moves = allLegalMoves.get(selection);
        if(moves == null) return;
        Color moveDest = new Color(20, 85, 30, 77);
        g2D.setColor(moveDest);
        final Point p = ChessPanel.getMouseCoordinates();
        //System.out.println((p == null)?"null":"(" + p.x + ", " + p.y + ")");
        for(String s:moves) {
            int x1 = ChessBoard.getColumn(s), 
                    y1 = ChessBoard.getRow(s);
            int x2 = x1, y2 = y1;
            if(!fromPerspective) {
                x2 = 7 - x1;
                y2 = 7 - y1;
            }
            
            if(p != null) {
                if(isEmptySquare(x1, y1) && 
                        (p.x >= x+x2*SQUARE_SIZE && p.x <= x+x2*SQUARE_SIZE+SQUARE_SIZE) && 
                        (p.y >= y+y2*SQUARE_SIZE && p.y <= y+y2*SQUARE_SIZE+SQUARE_SIZE)) {
                    g2D.fillRect(x+x2*SQUARE_SIZE, y+y2*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    continue;
                }
            }
            if(isEmptySquare(x1, y1)) {
                g2D.fillOval(x+x2*SQUARE_SIZE+CENTER_OFFSET, y+y2*SQUARE_SIZE+CENTER_OFFSET, 14, 14);
            } else {
                /*
                1___2
                 | |
                4---3
                */
                Point one = new Point(x+x2*SQUARE_SIZE, y+y2*SQUARE_SIZE), 
                        two = new Point(x+x2*SQUARE_SIZE + SQUARE_SIZE, y+y2*SQUARE_SIZE), 
                        three = new Point(x+x2*SQUARE_SIZE + SQUARE_SIZE, y+y2*SQUARE_SIZE + SQUARE_SIZE), 
                        four = new Point(x+x2*SQUARE_SIZE, y+y2*SQUARE_SIZE + SQUARE_SIZE);
                
                g2D.fillPolygon(new int[]{one.x, one.x, one.x+TRIANGLE_SIZE}, 
                        new int[]{one.y, one.y+TRIANGLE_SIZE, one.y}, 3); // 1
                g2D.fillPolygon(new int[]{two.x, two.x, two.x-TRIANGLE_SIZE}, 
                        new int[]{two.y, two.y+TRIANGLE_SIZE, two.y}, 3); // 2
                g2D.fillPolygon(new int[]{three.x, three.x, three.x-TRIANGLE_SIZE}, 
                        new int[]{three.y, three.y-TRIANGLE_SIZE, three.y}, 3); // 3
                g2D.fillPolygon(new int[]{four.x, four.x, four.x+TRIANGLE_SIZE}, 
                        new int[]{four.y, four.y-TRIANGLE_SIZE, four.y}, 3); // 4
            }
        }
        Color selectionColor = new Color(20, 85, 30, 128);
        g2D.setColor(selectionColor);
        if(fromPerspective) {
            g2D.fillRect(x+ChessBoard.getColumn(selection)*SQUARE_SIZE, 
                    y+ChessBoard.getRow(selection)*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        } else {
            g2D.fillRect(x+(7-ChessBoard.getColumn(selection))*SQUARE_SIZE, 
                    y+(7-ChessBoard.getRow(selection))*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }
    
    /**
     * Draws the promotion choice box
     * @param g2D the Graphics to draw on
     */
    private void drawPromotions(Graphics2D g2D) {
        if(promotion != -1) {
            g2D.setColor(new Color(250, 250, 250, (int) (255*0.7)));
            g2D.fillRect(x, y, (8*SQUARE_SIZE)+x, (8*SQUARE_SIZE)+y);
            Color inside = new Color(176, 176, 176);
            Color outsideNH = new Color(128, 128, 128);
            Color outsideH = new Color(216, 80, 0);
            BufferedImage[] promotions = new BufferedImage[]{
                Queen.getImage(playerIsWhite), Rook.getImage(playerIsWhite), 
                Bishop.getImage(playerIsWhite), Knight.getImage(playerIsWhite)
            };
            int col = (fromPerspective)?promotion:7-promotion;
            if(ChessPanel.getMouseCoordinates() == null) {
                if(playerIsWhite ^ fromPerspective) {
                    for(int i = 7; i >= 4; i--) {
                        g2D.setPaint(new RadialGradientPaint((SQUARE_SIZE/2) + (col*SQUARE_SIZE)+x, (SQUARE_SIZE/2) + (SQUARE_SIZE * i)+y, 57, new float[]{0F, 1.0F}, new Color[]{inside, outsideNH}));
                        g2D.fill(new Ellipse2D.Double(col*SQUARE_SIZE+x, SQUARE_SIZE * i+y, SQUARE_SIZE, SQUARE_SIZE));
                        g2D.drawImage(promotions[7-i], (SQUARE_SIZE*col)+(SQUARE_SIZE/10)+x, (SQUARE_SIZE*i)+(SQUARE_SIZE/10)+y, (SQUARE_SIZE*4)/5, (SQUARE_SIZE*4)/5, null);
                    }
                } else {
                    for(int i = 0; i <= 3; i++) {
                        g2D.setPaint(new RadialGradientPaint((SQUARE_SIZE/2) + (col*SQUARE_SIZE)+x, (SQUARE_SIZE/2) + (SQUARE_SIZE * i)+y, 57, new float[]{0F, 1.0F}, new Color[]{inside, outsideNH}));
                        g2D.fill(new Ellipse2D.Double(col*SQUARE_SIZE+x, SQUARE_SIZE * i+y, SQUARE_SIZE, SQUARE_SIZE));
                        g2D.drawImage(promotions[i], (SQUARE_SIZE*col)+(SQUARE_SIZE/10)+x, (SQUARE_SIZE*i)+(SQUARE_SIZE/10)+y, (SQUARE_SIZE*4)/5, (SQUARE_SIZE*4)/5, null);
                    }
                }
            } else {
                Point mouse = new Point((ChessPanel.getMouseCoordinates().x-x)/60, (ChessPanel.getMouseCoordinates().y-y)/60);
                if(playerIsWhite ^ fromPerspective) {
                    for(int i = 7; i >= 4; i--) {
                        if(mouse.x == col && mouse.y == i) {
                            g2D.setPaint(
                                    new RadialGradientPaint(
                                            (SQUARE_SIZE/2) + (col*SQUARE_SIZE)+x,
                                            (SQUARE_SIZE/2) + (SQUARE_SIZE * i)+y, 52,
                                            new float[]{0F, 1.0F}, 
                                            new Color[]{inside, outsideH}
                                    )
                            );
                            g2D.fill(
                                    new Rectangle2D.Double(
                                            col*SQUARE_SIZE+x,
                                            SQUARE_SIZE * i+y, 
                                            SQUARE_SIZE, SQUARE_SIZE
                                    )
                            );
                            g2D.drawImage(
                                    promotions[7-i], SQUARE_SIZE*col+5+x, 
                                    SQUARE_SIZE*i+5+y, 50, 50, null
                            );
                        } else {
                            g2D.setPaint(
                                    new RadialGradientPaint(
                                            (SQUARE_SIZE/2) + (col*SQUARE_SIZE)+x,
                                            (SQUARE_SIZE/2) + (SQUARE_SIZE * i)+y, 57,
                                            new float[]{0F, 1.0F}, 
                                            new Color[]{inside, outsideNH}
                                    )
                            );
                            g2D.fill(
                                    new Ellipse2D.Double(
                                            col*SQUARE_SIZE+x,
                                            SQUARE_SIZE * i+y, SQUARE_SIZE,
                                            SQUARE_SIZE
                                    )
                            );
                            g2D.drawImage(
                                    promotions[7-i],
                                    (SQUARE_SIZE*col)+(SQUARE_SIZE/10)+x,
                                    (SQUARE_SIZE*i)+(SQUARE_SIZE/10)+y, 
                                    (SQUARE_SIZE*4)/5, (SQUARE_SIZE*4)/5, null
                            );
                        }
                    }
                } else {
                    for(int i = 0; i <= 3; i++) {
                        if(mouse.x == col && mouse.y == i) {
                            g2D.setPaint(
                                    new RadialGradientPaint(
                                            (SQUARE_SIZE/2) + (col*SQUARE_SIZE)+x,
                                            (SQUARE_SIZE/2) + (SQUARE_SIZE * i)+y,
                                            52, new float[]{0F, 1.0F}, 
                                            new Color[]{inside, outsideH}
                                    )
                            );
                            g2D.fill(
                                    new Rectangle2D.Double(
                                            col*SQUARE_SIZE+x,
                                            SQUARE_SIZE * i+y, 
                                            SQUARE_SIZE, SQUARE_SIZE
                                    )
                            );
                            g2D.drawImage(
                                    promotions[i], SQUARE_SIZE*col+5+x, 
                                    SQUARE_SIZE*i+5+y, 50, 50, null
                            );
                        } else {
                            g2D.setPaint(
                                    new RadialGradientPaint(
                                            (SQUARE_SIZE/2) + (col*SQUARE_SIZE)+x,
                                            (SQUARE_SIZE/2) + (SQUARE_SIZE * i)+y, 57,
                                            new float[]{0F, 1.0F}, 
                                            new Color[]{inside, outsideNH}
                                    )
                            );
                            g2D.fill(
                                    new Ellipse2D.Double(
                                            col*SQUARE_SIZE+x,
                                            SQUARE_SIZE * i+y,
                                            SQUARE_SIZE, SQUARE_SIZE
                                    )
                            );
                            g2D.drawImage(
                                    promotions[i],
                                    (SQUARE_SIZE*col)+(SQUARE_SIZE/10)+x,
                                    (SQUARE_SIZE*i)+(SQUARE_SIZE/10)+y, 
                                    (SQUARE_SIZE*4)/5, (SQUARE_SIZE*4)/5, null
                            );
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Draws whether either king is in check
     * @param g2D the Graphics2D to draw on
     */
    private void drawCheck(Graphics2D g2D) {
        float[] fractions = new float[]{ 0.0f, 0.25f, 0.89f, 1.0f };
        Color[] colors = new Color[]{
            new Color(255, 0, 0, 255), new Color(231, 0, 0, 255), 
            new Color(169, 0, 0, 0), new Color(158, 0, 0, 0)
        };
        if(inCheck(playerIsWhite)) {
            String kingAt = kingPos.get(playerIsWhite);
            int col = getColumn(kingAt), row = getRow(kingAt);
            if(!fromPerspective) {
                col = 7 - col;
                row = 7 - row;
            }
            g2D.setPaint(
                    new RadialGradientPaint(
                            (SQUARE_SIZE/2) + (col*SQUARE_SIZE) + x, 
                            (SQUARE_SIZE/2) + (row*SQUARE_SIZE) + y, 
                            SQUARE_SIZE*7/12, fractions, colors
                    )
            );
            g2D.fill(
                    new Ellipse2D.Double(
                            col*SQUARE_SIZE + x, 
                            row*SQUARE_SIZE + y, 
                            SQUARE_SIZE, SQUARE_SIZE
                    )
            );
        }
    }
    
    /**
     * Draws the dragged piece
     * @param g2D the Graphics2D to draw on
     */
    private void drawDraggedPiece(Graphics2D g2D) {
        if(fakeDraggingFrom != null) {
            int midX = lastPoint.x - (SQUARE_SIZE/2), 
                midY = lastPoint.y - (SQUARE_SIZE/2);
            getPiece(fakeDraggingFrom).draw(g2D, midX, midY, 50, 50);
        }
        if(draggingFrom != null) {
            int midX = lastPoint.x - (SQUARE_SIZE/2), 
                midY = lastPoint.y - (SQUARE_SIZE/2);
            getPiece(draggingFrom).draw(g2D, midX, midY, 50, 50);
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
        if(s == null) return false;
        if(s.length() == 2) {
            int col = s.charAt(0)-'a', 
                    row = 8 - Integer.parseInt(s.charAt(1) + "");
            return Character.isLowerCase(s.charAt(0)) && 
                    Character.isDigit(s.charAt(1)) && isValidSquare(col, row);
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
     * @param s the square
     * @return the column / file
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
     * Recalculates all of the moves on a square
     */
    public void recalculateMoves() {
        allLegalMoves = new HashMap<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == null) continue;
                if(board[i][j].isWhite == playerIsWhite) {
                    String current = ChessBoard.toSquare(i, j);
                    LinkedList<String> moves = board[i][j].legalMoves(this, current);
                    allLegalMoves.put(current, moves);
                }
            }
        }
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
        ChessBoard thisCopy = new ChessBoard(this);
        maybeMove(fromWhereX, fromWhereY, toWhereX, toWhereY);
        if(board[toWhereX][toWhereY].getCharRepresentation().equals("K")) {
            ((King)(board[toWhereX][toWhereY])).notifyOfMove();
        }
        enPassant = null;
        if(board[toWhereX][toWhereY].getCharRepresentation().equals("P")) {
            if(Math.abs(fromWhereY-toWhereY) == 2) {
                String file = (char) ('a' + fromWhereX) + "", rank = String.valueOf(8-((fromWhereY+toWhereY)/2));
                enPassant = file + rank;
            }
        }
        String kingAt = kingPos.get(playerIsWhite);
        ((King)(board[getColumn(kingAt)][getRow(kingAt)])).notifyNoCheck();
        System.out.println("Moved: " + playerIsWhite);
        playerIsWhite = !playerIsWhite;
        recalculateMoves();
        updatePos(miniFEN());
        mr.moved(thisCopy, this, ChessBoard.toSquare(fromWhereX, fromWhereY), ChessBoard.toSquare(toWhereX, toWhereY));
        lastMoveFrom = toSquare(fromWhereX, fromWhereY);
        lastMoveTo = toSquare(toWhereX, toWhereY);
        if(checkMated(playerIsWhite)) System.out.println("Checkmate!\n");
        else if(inCheck(playerIsWhite)) {
            ((King)(getPiece(kingPos.get(playerIsWhite)))).notifyCheck();
            System.out.println("Check!\n");
        } else if(isDraw(playerIsWhite)) System.out.println("Draw.\n");
        ChessMain.Timer.end();
    }
    
    /**
     * Used to check whether this move is legal
     * @param fromWhere from where to move a piece
     * @param toWhere to where to move a piece
     */
    public void maybeMove(String fromWhere, String toWhere) {
        maybeMove(
                getColumn(fromWhere), getRow(fromWhere), 
                getColumn(toWhere), getRow(toWhere)
        );
    }
    
    /**
     * Used to check whether this move is legal
     * @param fromWhereX from which column to move a piece
     * @param fromWhereY from which row to move a piece
     * @param toWhereX to which column to move a piece
     * @param toWhereY to which row to move a piece
     */
    public void maybeMove(int fromWhereX, int fromWhereY, int toWhereX, int toWhereY) {
        if(board[fromWhereX][fromWhereY].getCharRepresentation().equals("K")) {
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
        } else if(toSquare(toWhereX, toWhereY).equals(enPassant)) {
            board[getColumn(enPassant)][getRow(enPassant)+(fromWhereY-toWhereY)] = null;
        }
        
        board[toWhereX][toWhereY] = board[fromWhereX][fromWhereY];
        board[fromWhereX][fromWhereY] = null;
        if(board[toWhereX][toWhereY].getCharRepresentation().equals("K")) kingPos.put(playerIsWhite, toSquare(toWhereX, toWhereY));
    }
    
    /**
     * Promotes a pawn
     * @param fromWhere from where to promote
     * @param toWhere to where to promote
     * @param toWhatPiece to what piece to promote to
     */
    public void promotePiece(String fromWhere, String toWhere, int toWhatPiece) {
        if(!getPiece(fromWhere).getCharRepresentation().equals("P")) 
            assert false : "Cannot promote a non-pawn";
        boolean isWhite = getPiece(fromWhere).isWhite;
        ChessBoard thisCopy = new ChessBoard(this);
        int fromWhereX = getColumn(fromWhere), fromWhereY = getRow(fromWhere);
        int toWhereX = getColumn(toWhere), toWhereY = getRow(toWhere);
        board[fromWhereX][fromWhereY] = null;
        // board[toWhereX][toWhereY];
        switch(toWhatPiece) {
            case MoveRecorder.BISHOP:
                board[toWhereX][toWhereY] = new Bishop(isWhite);
                break;
            case MoveRecorder.KNIGHT:
                board[toWhereX][toWhereY] = new Knight(isWhite);
                break;
            case MoveRecorder.QUEEN:
                board[toWhereX][toWhereY] = new Queen(isWhite);
                break;
            case MoveRecorder.ROOK:
                board[toWhereX][toWhereY] = new Rook(isWhite);
                break;
            default:
                throw new IllegalArgumentException("Unknown piece" + toWhatPiece);
        }
        playerIsWhite = !playerIsWhite;
        mr.moved(thisCopy, this, fromWhere, toWhere);
        System.out.println("Promoted from " + fromWhere + " to " + toWhere + " to a " + toWhatPiece);
        recalculateMoves();
        lastMoveFrom = toSquare(fromWhereX, fromWhereY);
        lastMoveTo = toSquare(toWhereX, toWhereY);
        if(checkMated(playerIsWhite)) System.out.println("Checkmate!\n");
        else if(inCheck(playerIsWhite)) System.out.println("Check!\n");
        else if(stalemated(playerIsWhite)) System.out.println("Stalemate.\n");
    }
    
    /**
     * DO NOT USE OFTEN <br>
     * Places a piece somewhere
     * @param ap a piece to place
     * @param where where to place the piece
     * @deprecated since it is not needed
     */
    @Deprecated
    public void placePiece(AbstractPiece ap, String where) {
        placePiece(ap, getColumn(where), getRow(where));
    }
    
    /**
     * DO NOT USE OFTEN <br>
     * Places a piece somewhere
     * @param ap a piece to place
     * @param col the column to place the piece in
     * @param row the row to place the piece in
     * @deprecated since it is not needed
     */
    @Deprecated
    public void placePiece(AbstractPiece ap, int col, int row) {
        board[col][row] = ap;
    }
    
    /**
     * Determines whether one side's king is in check
     * @param isWhite whether the side to check is white (PUN INTENDED)
     * @return whether the side is in check
     */
    public boolean inCheck(boolean isWhite) {
        for(int i = 0;i<8;i++) {
            for(int j = 0;j<8;j++) {
                AbstractPiece ap = getPiece(i, j);//lit dude lit
                if(ap != null) {
                    if(ap.isWhite ^ isWhite) {
                        //if(ap.legalCaptures(this, ChessBoard.toSquare(i, j)).contains(kingPos))
                        // if the current opposite-colored piece can eat the king on the next move
                        if(ap.isAllLegalMove(this, ChessBoard.toSquare(i, j), kingPos.get(isWhite))) {
                            return true;
                        }
                    }
                }
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
        if(inCheck(isWhite)) {
            for(String s : allLegalMoves.keySet()) {
                if(!allLegalMoves.get(s).isEmpty()) return false;
            }
            return true;
        } else return false;
    }
    
    /**
     * Determines whether one side is stalemated
     * @param isWhite whether the side to check is white
     * @return whether one side is stalemated
     */
    public boolean stalemated(boolean isWhite) {
        for(LinkedList<String> allLegalMove : allLegalMoves.values()) {
            if(!allLegalMove.isEmpty()) return false;
        }
        return !inCheck(isWhite);
    }
    
    /**
     * Determines whether either side has insufficient material to checkmate
     * @return whether either side has insufficient material to checkmate
     */
    public boolean insufficientMaterial() {
        HashMap<String, Integer> pieces = new HashMap<>();
        pieces.put("BW", 0);
        pieces.put("BB", 0);
        pieces.put("N", 0);
        pieces.put("bw", 0);
        pieces.put("bb", 0);
        pieces.put("n", 0);
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == null) continue;
                if(board[i][j].getCharRepresentation().equals("K")) continue;
                if("QRP".contains(board[i][j].getCharRepresentation())) return false;
                String rep = board[i][j].getCharRepresentation();
                if(board[i][j].getCharRepresentation().equals("B")) {
                    if(isSquareWhite(i, j))
                        rep += "W";
                    else
                        rep += "B";
                }
                if(board[i][j].getCharRepresentation().equals("N")) {
                    String nRep = (board[i][j].isWhite)?"N":"n";
                    if(pieces.get(nRep) == 1)
                        return false;
                }
                if(!board[i][j].isWhite)
                    rep = rep.toLowerCase();
                try {
                    pieces.put(rep, pieces.get(rep)+1);
                } catch(NullPointerException npe) {
                    throw new NullPointerException(npe.getMessage() + ": " + rep);
                }
            }
        }
        final boolean noBW = pieces.get("BW") == 0, 
                noBB = pieces.get("BB") == 0, noN = pieces.get("N") == 0;
        final boolean nobw = pieces.get("bw") == 0, 
                nobb = pieces.get("bb") == 0, non = pieces.get("n") == 0;
        final boolean whiteBare = noBW && noBB && noN;
        final boolean blackBare = nobw && nobb && non;
        return (whiteBare && blackBare) || 
                (noN && non && ((noBB && nobb) || (noBW && nobw))) ||
                (blackBare && noN && (noBW || noBB)) || 
                (blackBare && noBB && noBW && pieces.get("N") == 1) || 
                (whiteBare && non && (nobw || nobb)) || 
                (whiteBare && nobb && nobw && pieces.get("n") == 1);
    }
    
    /**
     * Determines whether the current state of the game is a draw
     * @param isWhite the side to check for stalemates
     * @return whether the game is a draw
     */
    public boolean isDraw(boolean isWhite) {
        return insufficientMaterial() || stalemated(isWhite) || mr.is50MoveDraw();
    }
    
    /**
     * Determines whether there is threefold repetition
     * @return whether there is threefold repetition
     */
    public boolean threeFoldRep() {
        for(int value : positions.values()) {
            if(value >= 3)
                return true;
        }
        return false;
    }
    
    /**
     * Updates positions
     * @param pos the position to update with
     */
    private void updatePos(String pos) {
        if(positions.containsKey(pos)) {
            positions.put(pos, positions.get(pos)+1);
        } else {
            positions.put(pos, 1);
        }
    }
    
    /**
     * Determines whether a square is white
     * @param square the square to check
     * @return whether the square is white
     */
    public static boolean isSquareWhite(String square) {
        return isSquareWhite(getColumn(square), getRow(square));
    }
    
    /**
     * Determines whether a square is white
     * @param col the column of the square to check
     * @param row the row of the square to check
     * @return whether the square is white
     */
    public static boolean isSquareWhite(int col, int row) {
        return (col+row)%2==0;
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
     * Refinds both kings.
     */
    public void resetKingPos() {
        String bKing = null, wKing = null;
        OUTER: for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == null) continue;
                if(board[i][j].getCharRepresentation().equals("K")) {
                    if(board[i][j].isWhite) {
                        if(wKing == null) {
                            wKing = toSquare(i, j);
                        } else {
                            assert false : "There are two white kings?!";
                        }
                    } else {
                        if(bKing == null) {
                            bKing = toSquare(i, j);
                        } else {
                            assert false : "There are two black kings?!";
                        }
                    }
                    if(wKing != null && bKing != null) break OUTER;
                }
            }
        }
        if(wKing == null) assert false : "Cannot find white king";
        if(bKing == null) assert false : "Cannot find black king";
        kingPos.put(true, wKing);
        kingPos.put(false, bKing);
    }
    
    /**
     * Refinds only one king.
     * @param isWhite whether the king to find again is white
     */
    public void resetKingPos(boolean isWhite) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == null) continue;
                if(board[i][j].getCharRepresentation().equals("K") && (board[i][j].isWhite == isWhite)) {
                    kingPos.put(isWhite, toSquare(i, j));
                    return;
                }
            }
        }
    }
    
    /**
     * Notifies this that the board has been clicked on a square
     * @param square where the board has been clicked
     */
    public void clicked(String square) {
        if(selected == null && promotion == -1) {
            if(!isEmptySquare(square) && (getPiece(square).isWhite == playerIsWhite) && 
                    ((playerIsWhite && manipulable == 0) || (!playerIsWhite && manipulable == 1) || manipulable == 2)) {
                selected = square;
            }
        } else if(promotion != -1) {
            if(ChessBoard.getColumn(square) == promotion) {
                String promoteTo = toSquare((playerIsWhite)?7:0, (fromPerspective)?promotion:7-promotion);
                if(playerIsWhite) {
                    /**
                     * QUEEN
                     * ROOK
                     * BISHOP
                     * KNIGHT
                     */
                    switch(ChessBoard.getRow(square)) {
                        case 0:
                            promotePiece(promotingFrom, promoteTo, MoveRecorder.QUEEN);
                            break;
                        case 1:
                            promotePiece(promotingFrom, promoteTo, MoveRecorder.ROOK);
                            break;
                        case 2:
                            promotePiece(promotingFrom, promoteTo, MoveRecorder.BISHOP);
                            break;
                        case 3:
                            promotePiece(promotingFrom, promoteTo, MoveRecorder.KNIGHT);
                            break;
                    }
                } else {
                    switch(ChessBoard.getRow(square)) {
                        case 7:
                            promotePiece(promotingFrom, promoteTo, MoveRecorder.QUEEN);
                            break;
                        case 6:
                            promotePiece(promotingFrom, promoteTo, MoveRecorder.ROOK);
                            break;
                        case 5:
                            promotePiece(promotingFrom, promoteTo, MoveRecorder.BISHOP);
                            break;
                        case 4:
                            promotePiece(promotingFrom, promoteTo, MoveRecorder.KNIGHT);
                            break;
                    }
                }
                promotingFrom = null;
                promotion = -1;
                recalculateMoves();
            }
        } else if(selected.equals(square)) {
            selected = null;
        } else {
            if(!isEmptySquare(square)) {
                if(getPiece(selected).isLegalMove(this, selected, square)) {
                    if(getPiece(selected).getCharRepresentation().equals("P") && (ChessBoard.getRow(square) == 0 || ChessBoard.getRow(square) == 7)) {
                        promotion = ChessBoard.getColumn(square);
                        promotingFrom = selected;
                    } else {
                        movePiece(selected, square);
                        selected = null;
                    }
                } else {
                    if(getPiece(square).isWhite == playerIsWhite) {
                        selected = square;
                    } else {
                        selected = null;
                    }
                }
            } else {
                if(getPiece(selected).isLegalMove(this, selected, square)) {
                    if(getPiece(selected).getCharRepresentation().equals("P") && (ChessBoard.getRow(square) == 0 || ChessBoard.getRow(square) == 7)) {
                        promotion = ChessBoard.getColumn(square);
                        promotingFrom = selected;
                    } else {
                        movePiece(selected, square);
                        selected = null;
                    }
                } else selected = null;
            }
        }
        System.out.println("selected: " + selected);
    }
    
    /**
     * Enables dragging.
     * @param fromWhere from where the piece is being dragged 
     */
    public void enableDragging(String fromWhere) {
        if(!isEmptySquare(fromWhere)) 
            if(getPiece(fromWhere).isWhite == playerIsWhite && 
                    ((playerIsWhite && manipulable == 0) || 
                    (!playerIsWhite && manipulable == 1) || manipulable == 2)) 
                draggingFrom = fromWhere;
            else 
                fakeDraggingFrom = fromWhere;
        System.out.println("selected: " + selected);
    }
    
    /**
     * Disables dragging.
     * @param toWhere to where the piece is being dragged 
     */
    public void disableDragging(String toWhere) {
        if(fakeDraggingFrom != null) {
            fakeDraggingFrom = null;
            return;
        }
        if(draggingFrom == null) return;
        System.out.println("(" + lastPoint.x + ", " + lastPoint.y + ")");
        System.out.println(draggingFrom + " -> " + toWhere);
        /*if(getPiece(draggingFrom).isLegalMove(this, draggingFrom, dropSquare)) {
            movePiece(draggingFrom, dropSquare);
        }*/
        if(getPiece(draggingFrom).isLegalMove(this, draggingFrom, toWhere)) {
            if(getPiece(draggingFrom).getCharRepresentation().equals("P") && (ChessBoard.getRow(toWhere) == 0 || ChessBoard.getRow(toWhere) == 7)) {
                promotion = ChessBoard.getColumn(toWhere);
                promotingFrom = draggingFrom;
            } else {
                movePiece(draggingFrom, toWhere);
            }
        }
        if(!draggingFrom.equals(selected)) selected = null;
        draggingFrom = null;
    }

    /**
     * Returns how much the board was transformed in the x-axis
     * @return how much the board was transformed in the x-axis
     */
    public int getX() {
        return x;
    }

    /**
     * Returns how much the board was transformed in the y-axis
     * @return how much the board was transformed in the y-axis
     */
    public int getY() {
        return y;
    }

    /**
     * Determines which square is open for en passant
     * @return which square is open for en passant
     */
    public String getEnPassant() {
        return enPassant;
    }

    /**
     * Returns the board of AbstractPieces
     * @return the board of AbstractPieces
     */
    public AbstractPiece[][] getBoard() {
        return board;
    }

    /**
     * Determines from which perspective this ChessBoard is drawn
     * @return from which perspective this ChessBoard is drawn
     */
    public boolean getPerspective() {
        return fromPerspective;
    }
    
    /**
     * Returns the current player
     * @return the current player
     */
    public boolean getCurrentPlayer() {
        return playerIsWhite;
    }
    
    /**
     * Determines the square being referenced from a position
     * @param xPos the x-position of the mouse
     * @param yPos the y-position of the mouse
     * @return a square
     */
    public String toSquareFromPos(int xPos, int yPos) {
        int x1 = (xPos - x)/SQUARE_SIZE, y1 = (yPos - y)/SQUARE_SIZE;
        return toPerspectiveSquare(x1, y1);
    }
    
    /**
     * Determines the square being referenced in perspective
     * @param x the x position of the square
     * @param y the y position of the square
     * @return the square being referenced in perspective
     */
    public String toPerspectiveSquare(int x, int y) {
        String output = (fromPerspective) ? toSquare(x, y) : rotateSquare180(x, y);
        return (isValidSquare(output))? output : null;
    }
    
    /**
     * Rotates a square 180 degrees.
     * @param s the square to rotate
     * @return the resulting square
     */
    public String rotateSquare180(String s) {
        return rotateSquare180(getColumn(s), getRow(s));
    }
    
    /**
     * Rotates a square 180 degrees.
     * @param x the x position of the square to rotate
     * @param y the y position of the square to rotate
     * @return the resulting square
     */
    public String rotateSquare180(int x, int y) {
        String output = toSquare(7-x, 7-y);
        return (isValidSquare(output))? output : null;
    }
    
    /**
     * Flips this ChessBoard.
     */
    public void flipBoard() {
        fromPerspective = !fromPerspective;
    }
    
    /**
     * Sets the perspective
     * @param perspective the perspective to set to
     */
    public void setPerspective(boolean perspective) {
        fromPerspective = perspective;
    }
    
    /**
     * Sets the perspective in a standard sort of way
     * @param isWhite what to change the perspective to
     */
    public void setStandardPerspective(boolean isWhite) {
        fromPerspective = isWhite;
        manipulable = (isWhite)?WHITE_MANIPULABLE:BLACK_MANIPULABLE;
    }

    /**
     * Determines which side's pieces are manipulable.<br>
     * Useful for figuring out which side is the player's side
     * @return which side's pieces are manipulable
     */
    public int getManipulable() {
        return manipulable;
    }

    /**
     * DO NOT USE OFTEN <br>
     * Sets this board to a new state
     * @param board the board to set to
     */
    public void setBoard(AbstractPiece[][] board) {
        this.board = new AbstractPiece[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                this.board[i][j] = board[i][j];
            }
        }
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
                } else System.out.print(ap.getCharRepresentation());
            }
            System.out.println();
        }
    }
    
    /**
     * Prints all of the current moves.
     */
    public void printMoves() {
        System.out.println(mr.toString());
    }
    
    /**
     * Returns a miniature of this chess board
     * @return a miniature of this chess board
     */
    public String miniFEN() {
        String output = "";
        for(AbstractPiece[] col : board) {
            int blanks = 0;
            for(AbstractPiece piece : col) {
                if(piece == null) {
                    blanks++;
                } else {
                    if(blanks != 0) {
                        output += blanks;
                    }
                    blanks = 0;
                    String rep = piece.getCharRepresentation();
                    if(piece.isWhite) 
                        output += rep.toUpperCase(); 
                    else 
                        output += rep.toLowerCase();
                }
            }
            output += "/";
        }
        return output;
    }
}