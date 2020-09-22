package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.Bishop;
import chessGame.Game;
import chessGame.Pawn;

public class BishopTest {
	private static Game game;
	Bishop bishop1;
	Pawn pawn1;
	Pawn pawn2;

	@Before
	public void setUpBoard() {
		game = new Game();
		bishop1 = new Bishop(2, 0, game, 0);
		pawn1 = new Pawn(3, 1, game, 1);
		pawn2 = new Pawn(4, 2, game, 1);
		game.board[2][0] = bishop1;
		game.board[3][1] = pawn1;
		game.board[4][2] = pawn2;
	}

	@Test
	public void moveRankFileIllegal() {
		bishop1.move(2, 1);
		assertTrue(game.checkPieceExist(2, 0));
		assertTrue(!game.checkPieceExist(2, 1));
		
		bishop1.move(0, 2);
		assertTrue(game.checkPieceExist(0, 2));
		assertTrue(!game.checkPieceExist(2, 0));
	}
	
	@Test
	public void moveRightUp() {
		bishop1.move(3, 1);
		assertTrue(game.checkPieceExist(3, 1));
		assertTrue(!game.checkPieceExist(2, 0));
	}
		
	@Test
	public void moveLeftUp() {
		bishop1.move(1, 1);
		assertTrue(game.checkPieceExist(1, 1));
		assertTrue(!game.checkPieceExist(2, 0));
	}
	
	@Test
	public void testLeapIllegal() {
		bishop1.move(4, 2);
		assertTrue(game.checkPieceExist(2, 0));
		assertTrue(game.checkPieceExist(3, 1));
		assertTrue(game.checkPieceExist(4, 2));
	}
	
	@Test
	public void testCapture() {
		bishop1.move(3, 1);
		assertTrue(game.checkPieceExist(3, 1));
		assertTrue(!game.checkPieceExist(2, 0));
	}


}
