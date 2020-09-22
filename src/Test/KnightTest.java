package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.Game;
import chessGame.Knight;
import chessGame.Pawn;

public class KnightTest {
	private static Game game;
	Knight knight1;
	Pawn pawn1;

	@Before
	public void setUpBoard() {
		game = new Game();
		knight1 = new Knight(3, 3, game, 0);
		pawn1 = new Pawn(4, 1, game, 1);
		game.board[3][3] = knight1;
		game.board[4][1] = pawn1;
	}
	
	@Test
	public void testMoveRightUp() {
		knight1.move(5, 4);
		assertTrue(game.checkPieceExist(5, 4));
		assertTrue(!game.checkPieceExist(3, 3));
	}
		
	@Test
	public void testMoveIllegal() {
		knight1.move(4, 3);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(!game.checkPieceExist(4, 3));
	}
	
	@Test
	public void testLeftDown() {
		knight1.move(2, 1);
		assertTrue(game.checkPieceExist(2, 1));
		assertTrue(!game.checkPieceExist(3, 3));
	}
		
	@Test
	public void testCapture() {
		knight1.move(4, 1);
		assertTrue(game.checkPieceExist(4, 1));
		assertTrue(!game.checkPieceExist(3, 3));
	}


}