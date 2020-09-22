package chessGame;

public class Knight extends Piece{
	
	/*
	 * Constructs a bishop from the superclass constructor.
	 */
	public Knight(int x, int y, Game parentgame, int player) {
		super(x, y, parentgame, player);
	}

	/*
     * Determines if the knight can move to the intended location. A knight can move
     * either two squares horizontal and one square vertical, or two squares vertical
     * and one square horizontal. Knight is the only piece that can leap over pieces.
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
    	if ((maxx-minx == 1 && maxy-miny == 2) || (maxx-minx == 2 && maxy-miny == 1)) {
        	return true;
    	}
    	
		return false;
    }
}
