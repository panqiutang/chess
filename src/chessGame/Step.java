package chessGame;

public class Step {
	public Piece piece;
	public int oldx;
	public int oldy;
	public int newx;
	public int newy;
	public Piece captured;
	public boolean first;
	
	public Step(Piece piece, int oldx, int oldy, int newx, int newy, Piece captured) {
		this.piece = piece;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
		this.captured = captured;
		first = false;
	}
}

