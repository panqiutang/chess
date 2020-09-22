package chessGame;

public class King extends Piece{
	
	/*
	 * Constructs a bishop from the superclass constructor.
	 */
	public King(int x, int y, Game parentgame, int player) {
		super(x, y, parentgame, player);
	}

	/*
     * Determines if the king can move to the intended location. A king can move one
     * square in any direction.
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
    	int diffx = maxx - minx;
    	int diffy = maxy - miny;
    	if ((diffx == 1 && diffy == 0) || (diffx == 0 && diffy == 1) || (diffx == 1 && diffy == 1)) {
    		return true;
    	}
    	
    	return false;
    }
    
}
