package chessGame;

public class Rook extends Piece{
	
	/*
	 * Constructs a bishop from the superclass constructor.
	 */
	public Rook(int x, int y, Game parentgame, int player) {
		super(x, y, parentgame, player);
	}

	/*
     * Determines if the rook can move to the intended location. A rook can move
     * along rank or file and cannot leap over pieces.
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
    	
    	int minx = Math.min(x, posx);
    	int maxx = Math.max(x, posx);
    	int miny = Math.min(y, posy);
    	int maxy = Math.max(y, posy);
    	if (x == posx) { // move along file
    		for (int i = 1; i < maxy - miny; i++) {
        		if (game.checkPieceExist(x, miny+i)) {
        			System.out.println("Cannot leap over pieces");
    	    		return false;
        		}
        	}
    	}
    	else { // move along rank
    		for (int i = 1; i < maxx - minx; i++) {
        		if (game.checkPieceExist(minx+i, y)) {
        			System.out.println("Cannot leap over pieces");
    	    		return false;
        		}
        	}
    	}
    	return true;
    }
}
