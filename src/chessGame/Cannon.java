package chessGame;

public class Cannon extends Piece{
	
	/*
	 * Constructs a bishop from the superclass constructor.
	 */
	public Cannon(int x, int y, Game parentgame, int player) {
		super(x, y, parentgame, player);
	}

	/*
     * Determines if the cannon can move to the intended location. A cannon can move in
     * any direction any number of squares without leaping over pieces. It can only
     * capture pieces if there is another piece on its path.
     * @param x the x position of the intended move
     * @param y the y position of the intended move
     * @return true if the move is legal.
     */
    public boolean canMove(int x, int y) {
    	if (!checkMoveLocation(x, y)) {
    		return false;
    	}
    	
    	if (x != posx && y != posy) {
    		System.out.println("Invalid location");
    		return false;
    	}
    	
    	boolean capture = false;
    	if (game.checkPieceExist(x, y)) {
    		capture = true;
    	}
    	int minx = Math.min(x, posx);
    	int maxx = Math.max(x, posx);
    	int miny = Math.min(y, posy);
    	int maxy = Math.max(y, posy);
    	boolean pieceBetween = false;
    	
    	if (x == posx) {
    		for (int i = 1; i < maxy - miny; i++) {
        		if (game.checkPieceExist(x, miny+i)) {
        			if (!capture) { // moving
        				System.out.println("Cannot leap over pieces");
        	    		return false;
        			}
        			else if (!pieceBetween) { // capture
        				pieceBetween = true;
        			}
        			else { // more than one piece in between
        				System.out.println("Cannot capture pieces with more than one piece in between");
        	    		return false;
        			}
        		}
        	}
    	}
    	else {
    		for (int i = 1; i < maxx - minx; i++) {
        		if (game.checkPieceExist(minx+i, y)) {
        			if (!capture) { // moving
        				System.out.println("Cannot leap over pieces");
        	    		return false;
        			}
        			else if (!pieceBetween) { // capture
        				pieceBetween = true;
        			}
        			else { // more than one piece in between
        				System.out.println("Cannot capture pieces with more than one piece in between");
        	    		return false;
        			}
        		}
        	}
    	}
    	if (capture && !pieceBetween) {
    		return false;
    	}
    	
    	return true;
    }

}
