package chessGame;

import java.util.Stack;

/*
 * A Game structure of chess.
 */
public class Game {
	public int boardwidth = 8; // board width
	public int boardheight = 8;	// board height
    public Piece[][] board = new Piece[boardwidth][boardheight]; // the board
    public int turn = 0; // the turn of the two players
    public Piece king_white = null;
    public Piece king_black = null;
    public Stack<Step> moveStack = new Stack<>();
    
    /*
     * Set up a new game.
     */
    public void startNewBoard() {
    	board = new Piece[boardwidth][boardheight];
    	for (int i = 1; i < boardheight; i++) {
    		board[i][1] = new Pawn(i, 1, this, 0);
    		board[i][6] = new Pawn(i, 6, this, 1);
    	}
    	
		board[0][0] = new Rook(0, 0, this, 0);
		board[7][0] = new Rook(7, 0, this, 0);
		board[1][0] = new Knight(1, 0, this, 0);
		board[6][0] = new Knight(6, 0, this, 0);
		board[2][0] = new Bishop(2, 0, this, 0);
		board[5][0] = new Bishop(5, 0, this, 0);
		board[4][0] = new King(4, 0, this, 0);
		board[3][0] = new Queen(3, 0, this, 0);
		board[0][1] = new Guard(0, 1, this, 0);
		board[0][2] = new Cannon(0, 2, this, 0);
		
		board[0][7] = new Rook(0, 7, this, 1);
		board[7][7] = new Rook(7, 7, this, 1);
		board[1][7] = new Knight(1, 7, this, 1);
		board[6][7] = new Knight(6, 7, this, 1);
		board[2][7] = new Bishop(2, 7, this, 1);
		board[5][7] = new Bishop(5, 7, this, 1);
		board[4][7] = new King(4, 7, this, 1);
		board[3][7] = new Queen(3, 7, this, 1);
		board[0][6] = new Guard(0, 6, this, 1);
		board[0][5] = new Cannon(0, 5, this, 1);
		
		
		king_white = board[4][0];
		king_black = board[4][7];
		turn = 0;
		moveStack = new Stack<>();
    }
    
    /*
     * Undo the last move of the game, regardless of player. If it's the black player's turn, 
     * the white player's last move will be undone. If it's the white player's turn, the black player's
     * last move will be undone.
     */
    public Step undo() {
    	if (moveStack.empty()) {
    		return null;
    	}
    	Step step = moveStack.pop();
		board[step.piece.posx][step.piece.posy] = step.captured;
		step.piece.posx = step.oldx;
		step.piece.posy = step.oldy;
		board[step.oldx][step.oldy] = step.piece;
		if (step.piece instanceof Pawn && step.first) {
			((Pawn) step.piece).first = true;
		}
		turn = 1 ^ turn;
		return step;
    }
    
    /*
     * Checks if the location has a piece.
     * @param x the x position of the location
     * @param y the y position of the location
     * @return true if the location has a piece
     */
    public boolean checkPieceExist(int x, int y) {
    	if (board[x][y] != null) {
    		return true;
    	}
    	return false;
    }
    
    /*
     * Checks if the game has ended by checking checkmate on the two kings.
     * @param king_white the white king
     * @param king_black
     * @return true if game has ended
     */
    public boolean gameEnd(King king_white, King king_black) {
    	if (checkMate(king_white) || checkMate(king_black)) {
    		return true;
    	}
    	return false;
    }
    
    /*
     * Check if the king is in check.
     * @param x the x position of the king
     * @param y the y position of the king
     * @player the player the king belongs to
     */
    public boolean inCheck(int x, int y, int player) {
    	for (int i = 0; i < boardwidth; i++) {
    		for (int j = 0; j < boardheight; j++) {
    			Piece piece = board[i][j];
    			
    			if (piece != null && piece.player != player && piece.canMove(x, y)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    /*
     * Checks whether there is a checkmate on the king.
     * @param king the king to check conditions
     * @return true if the king is in checkmate
     */
    public boolean checkMate(Piece king) {
    	int me = king.player;
    	int x = king.posx;
    	int y = king.posy;

    	/* if the king is not in check then it's not checkmate */
    	if (!inCheck(x, y, me)) {
    		return false;
    	}
    	
    	/* Check if the king can move to escape */
    	boolean canMove = false;
    	
    	for (int newx = x-1; newx <= x+1; newx++) { // loop over possible moves of king
    		for (int newy = y-1; newy <= y+1; newy++) {
    			/* if the king cannot legally move here then skip */
    			if ((newx == x && newy == y) || !king.canMove(newx, newy)) {
    				continue;
    			}
    			
    			/* temporarily move the king */
    			Piece orig = board[newx][newy];
    			board[x][y] = null;
    			board[newx][newy] = king;
    			
    			boolean check = inCheck(newx, newy, me);
    			
    			/* returning the king's position */
    			board[x][y] = king;
    			board[newx][newy] = orig;
    			
    			/* if the king is not in check in this move, then it can escape checkmate */
    			if (!check) {
    				canMove = true;
    				break;
    			}
    		}
    	}
    	
    	/* if the king cannot escape then it's checkmate */
    	if (!canMove) {
    		return true;
    	}
    	
    	return false;
    }
    
    /*
     * Container for stalemate detection.
     * @param king_white the white king
     * @param king_black the black king
     * @return true if game is in stalemate
     */
//    public boolean staleMate(King king_white, King king_black) {
//    	return false;
//    }
}
