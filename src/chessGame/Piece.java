package chessGame;

/**
 * A Piece is an abstract class as the parent of pieces in a chess game including
 * king, queen, knight, etc.
 */
public abstract class Piece {
    public int posx;
    public int posy;
    public Game game;
    public int player;
    
    /*
     * Constructs a Piece.
     * @param x the x/column/letter position of the piece
     * @param y the y/row/number position of the piece
     * @param parentgame the game that this piece belongs to
     * @param player the player that this piece belongs to
     */
    public Piece(int x, int y, Game parentgame, int player) {
        this.posx = x;
        this.posy = y;
        this.game = parentgame;
        this.player = player;
    }
    
    /*
     * A general check of whether the location of the move is valid.
     * Checks if the location is within the board,
     * and if the action is a capture, the piece to capture belongs to the opponent.
     * @param x the x position of the intended move
     * @param y the y position of the intended move
     * @return true if the move is within the board and captures the opponent (if applicable).
     */
    public boolean checkMoveLocation(int x, int y) {
    	if (x < 0 || x >= game.boardwidth || y < 0 || y >= game.boardheight) {
    		System.out.println("Out of board");
    		return false;
    	}
    	if (game.checkPieceExist(x, y) && game.board[x][y].player == player) {
    		System.out.println("Cannot capture your own piece");
    		return false;
    	} else if (game.checkPieceExist(x, y) && game.board[x][y] instanceof Guard) {
    		if (player == 0 && posy > y) {
    			return true;
    		}
    		else if (player == 1 && posy < y) {
    			return true;
    		}
    		return false;
    	}
    	return true;
    }
    
    /*
     * An abstract method for determining whether a piece can move to the intended location
     * given its specific rules.
     * @param x the x position of the intended move
     * @param y the y position of the intended move
     * @return true if the move is legal.
     */
    public abstract boolean canMove(int x, int y);
    
    /*
     * Moves a piece to the intended location. Checks whether it is the player's turn
     * and if the move is legal first.
     * @param x the x position of the intended move
     * @param y the y position of the intended move
     * @return boolean if the move was successful
     */
    public boolean move(int x, int y) {
    	if (player == game.turn && canMove(x, y)) {
    		Piece orig = game.board[x][y];
    		int origx = posx;
    		int origy = posy;
    		Piece origpiece = game.board[x][y];
    		
    		game.board[posx][posy] = null;
        	game.board[x][y] = this;
        	posx = x;
        	posy = y;
        	
        	/* find the king */
        	int kingx = -1;
        	int kingy = -1;
        	for (int i = 0; i < game.boardwidth; i++) {
        		for (int j = 0; j < game.boardheight; j++) {
        			Piece piece = game.board[i][j];
        			if ((piece instanceof King) && piece.player == player) {
        				kingx = piece.posx;
        				kingy = piece.posy;
        				break;
        			}
        		}
        		if (kingx != -1) {
        			break;
        		}
        	}
        	
        	/* if king is in check then illegal move; revert */
        	if (game.inCheck(kingx, kingy, player)) {
        		System.out.println("Illegal: putting the king in check");
        		posx = origx;
        		posy = origy;
        		game.board[x][y] = orig;
        		game.board[origx][origy] = this;
        	}
        	else {
        		game.turn = 1 ^ game.turn;
            	System.out.println("moved to " + x + " " + y);
            	Step step = new Step(this, origx, origy, x, y, origpiece);
				game.moveStack.push(step);
            	return true;
        	}
    	}
    	
    	return false;
    }
}
