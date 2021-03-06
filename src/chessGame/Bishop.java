package chessGame;


/**
 *  A Bishop piece in Chess.
 */
public class Bishop extends Piece{

	/*
	 * Constructs a bishop from the superclass constructor.
	 */
	public Bishop(int x, int y, Game parentgame, int player) {
		super(x, y, parentgame, player);
	}

	/*
     * Determines if the bishop can move to the intended location. A bishop can only
     * move diagonally, and cannot leap over pieces.
     * @param x the x position of the intended move
     * @param y the y position of the intended move
     * @return true if the move is legal.
     */
    public boolean canMove(int x, int y) {
    	if (!checkMoveLocation(x, y)) {
    		return false;
    	}
    	
    	int minx = Math.min(x, posx);
    	int maxx = Math.max(x, posx);
    	int miny = Math.min(y, posy);
    	int maxy = Math.max(y, posy);
    	if (maxx - minx != maxy - miny) {
    		System.out.println("Invalid location");
    		return false;
    	}

		if ((posx < x && posy < y) || (posx > x && posy > y)) {
			for (int i = 1; i < maxy - miny; i++) {
    			if (game.checkPieceExist(minx+i, miny+i)) {
    				System.out.println("Cannot leap over pieces");
    	    		return false;
    			}
    		}
		}
		else {
			for (int i = 1; i < maxy - miny; i++) {
    			if (game.checkPieceExist(minx+i, maxy-i)) {
    				System.out.println("Cannot leap over pieces");
    	    		return false;
    			}
    		}
		}
		
    	return true;
    }
}
