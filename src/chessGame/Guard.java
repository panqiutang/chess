package chessGame;

public class Guard extends Piece{
	
	public Guard(int x, int y, Game parentgame, int player) {
		super(x, y, parentgame, player);
	}

	/*
     * Determines if the guard can move to the intended location. A guard can move one
     * square in any direction. If it's capturing another piece on the same rank, then
     * it can move multiple pieces. It cannot be captured from its front or side,
     * only from its back.
     * @param x the x position of the intended move
     * @param y the y position of the intended move
     * @return true if the move is legal.
     */
    public boolean canMove(int x, int y) {
    	if (!checkMoveLocation(x, y)) {
    		return false;
    	}
    	
    	boolean capture = game.checkPieceExist(x, y);
    	int minx = Math.min(x, posx);
    	int maxx = Math.max(x, posx);
    	int miny = Math.min(y, posy);
    	int maxy = Math.max(y, posy);
    	int diffx = maxx - minx;
    	int diffy = maxy - miny;
    	if (!capture && ((diffx == 1 && diffy == 0) || (diffx == 0 && diffy == 1) || (diffx == 1 && diffy == 1))) {
    		return true;
    	}
    	
    	if (capture && y == posy) {
    		return true;
    	}
    	
    	return false;
    }
}
