package chessai;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A MCTS tree.
 * @author Simon Lucas
 * Tweaked by Jed Wang
 * @created 2010
 */
public class TreeNode {
    /**
     * A RNG for tie breaks.
     */
    static Random r = new Random();
    
    /**
     * The avaliable number of moves.
     * Change later on to not be final
     */
    private int nActions;
    
    /**
     * Very small number for tie breaks.
     */
    public static double EPSILON = 1e-6;

    
    /**
     * This {@code TreeNode}'s children.
     */
    private TreeNode[] children;
    
    /**
     * The number of total visits.
     */
    private double nVisits;
    
    /**
     * The value of this {@code TreeNode}.
     */
    private double totValue;
    
    /**
     * This node's state of the game
     */
    private ChessBoard cb = new ChessBoard();

    public TreeNode(ChessBoard cb) {
        this.cb = cb;
        nActions = cb.numOfLegalMoves();
    }

    /**
     * Searches through the Monte Carlo tree.
     */
    public void selectAction() {
        System.out.println("SELECTING");
        List<TreeNode> visited = new LinkedList<>();
        TreeNode cur = this;
        visited.add(this);
        while (!cur.isLeaf()) {
            cur = cur.select();
            // System.out.println("Adding: " + cur);
            visited.add(cur);
        }
        System.out.println("EXPANDING");
        cur.expand();
        System.out.println("SELECTING");
        TreeNode newNode = cur.select();
        visited.add(newNode);
        System.out.println("SIMULATING");
        double value = simulate(newNode);
        System.out.println("UPDATING");
        for(TreeNode node : visited) {
            // would need extra logic for n-player game
            // System.out.println(node.toString());
            node.updateStats(value);
        }
    }

    /**
     * Creates new {@code TreeNode}s to match all playable branches of the game.
     */
    public void expand() {
        children = new TreeNode[nActions];
        cb.printBoard();
        for (int i=0; i<nActions; i++) {
            ChessBoard temp = new ChessBoard(cb);
            temp.movePiece(i);
            temp.recalculateMoves();
            temp.printBoard();
            children[i] = new TreeNode(temp);
        }
    }

    /**
     * Selects a {@code TreeNode} to start searching from
     * @return a {@code TreeNode}
     */
    private TreeNode select() {
        TreeNode selected = null;
        double bestValue = Double.MIN_VALUE;
        for (TreeNode c : children) {
            double uctValue =
                    c.totValue / (c.nVisits + EPSILON) +
                            Math.sqrt(Math.log(nVisits+1) / (c.nVisits + EPSILON)) +
                            r.nextDouble() * EPSILON;
            // small random number to break ties randomly in unexpanded nodes
            // System.out.println("UCT value = " + uctValue);
            if (uctValue > bestValue) {
                selected = c;
                bestValue = uctValue;
            }
        }
        // System.out.println("Returning: " + selected);
        return selected;
    }

    /**
     * Determines whether this {@code TreeNode} is a leaf.
     * @return whether this {@code TreeNode} is a leaf
     */
    public boolean isLeaf() {
        return children == null;
    }

    /**
     * Simulates the game from the given {@code TreeNode}
     * @param tn the {@code TreeNode} to simulate from
     * @return the result
     */
    public double simulate(TreeNode tn) {
        // ultimately a roll out will end in some value
        // assume for now that it ends in a win or a loss
        // and just return this at random
        // Use a NN to minimax through later
        ChessBoard copy = new ChessBoard(tn.cb);
        copy.printBoard();
        while(!(copy.checkMated(copy.currentPlayer()) || 
                copy.isDraw(copy.currentPlayer()))) {
            int random = r.nextInt(copy.numOfLegalMoves());
            copy.movePiece(random);
            copy.printBoard();
            System.out.println();
            System.out.println("Checkmate: " + copy.checkMated(copy.currentPlayer()) + 
                    " \tDraw: " + copy.isDraw(copy.currentPlayer()));
        }
        return (copy.checkMated(true))? -1 : (copy.checkMated(false) ? 1 : 0);
    }

    /**
     * Updates the statistics of this {@code TreeNode}.
     * @param value the value to change this {@code TreeNode}.
     */
    public void updateStats(double value) {
        nVisits++;
        totValue += value;
    }

    /**
     * Determines how many children this {@code TreeNode} has.
     * If it is a leaf, then it returns 0.
     * @return how many children this {@code TreeNode} has
     */
    public int arity() {
        return children == null ? 0 : children.length;
    }
}