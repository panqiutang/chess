package chessGame;

public class Pawn extends Piece {
	public boolean first; // whether a pawn is on its first move

	/*
	 * Constructs a bishop from the superclass constructor.
	 */
    public Pawn(int x, int y, Game parentgame, int player) {
		super(x, y, parentgame, player);
		first = true;
	}
    
    /*
     * Determines if the pawn can move to the intended location. A pawn can move two squares
     * to the front if it's its first move, and one square to the front afterwards. 
     * It can only capture pieces that are on its direct diagonal line one square in front.
     * @param x the x position of the intended move
     * @param y the y position of the intended move
     * @return true if the move is legal.
     */
    public boolean canMove(int x, int y) {
    	if (!checkMoveLocation(x, y)) {
    		return false;
    	}
		
		int yplus1 = posy+1;
		int yplus2 = posy+2;
		if (player == 1) {
			yplus1 = posy-1;
			yplus2 = posy-2;
		}
		if (first && x == posx && (y == yplus1 || y == yplus2) && !game.checkPieceExist(x, y)) {
			return true;
		}
		else if (x == posx && y == yplus1 && !game.checkPieceExist(x, y)) {
    		return true;
    	}
		else if (game.checkPieceExist(x, y) && y == yplus1 && (x == posx+1 || x == posx-1)) {
			return true;
		}
    	
		System.out.println("Invalid location");
		return false;
    }
    
    /*
     * Overrides the default move function of a piece. Adds the functionality to check
     * if a pawn is on its first move.
     * @param x the intended x position of the move
     * @param y the intended y position of the move
     * @return boolean if the move was successful
     */
    public boolean move(int x, int y) {
    	if (player == game.turn && canMove(x, y)) {
    		boolean wasfirst = false;
    		if (first) {
    			first = false;
    			wasfirst = true;
    		}
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
				if (wasfirst) {
					step.first = true;
				}
				game.moveStack.push(step);
            	return true;
        	}
    	}
    	
    	return false;
    }
    
}
