package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.Game;
import chessGame.Pawn;

public class PawnTest {
	private static Game game;
	Pawn pawn1;
	Pawn pawn2;

	@Before
	public void setUpBoard() {
		game = new Game();
		pawn1 = new Pawn(0, 1, game, 0);
		game.board[0][1] = pawn1;
	}
	
	@Test
	public void testMoveTwoFirst() {
		pawn1.move(0, 3);
		assertTrue(game.checkPieceExist(0, 3));
		assertTrue(!game.checkPieceExist(0, 1));
		assertEquals(pawn1.posx, 0);
		assertEquals(pawn1.posy, 3);
	}
	
	@Test
	public void testMoveOneFirst() {
		pawn1.move(0, 2);
		assertTrue(game.checkPieceExist(0, 2));
		assertTrue(!game.checkPieceExist(0, 1));
		assertEquals(pawn1.posx, 0);
		assertEquals(pawn1.posy, 2);
	}
	
	@Test
	public void testMoveTwoFirstThenMoveOne() {
		pawn1.move(0, 3);
		assertTrue(game.checkPieceExist(0, 3));
		assertTrue(!game.checkPieceExist(0, 1));
		assertEquals(pawn1.posx, 0);
		assertEquals(pawn1.posy, 3);
		
		game.turn = 0;
		pawn1.move(0, 4);
		assertTrue(game.checkPieceExist(0, 4));
		assertTrue(!game.checkPieceExist(0, 3));
	}
	
	@Test
	public void testMoveTwoFirstThenMoveTwoIllegal() {
		pawn1.move(0, 3);
		assertTrue(game.checkPieceExist(0, 3));
		assertTrue(!game.checkPieceExist(0, 1));
		assertEquals(pawn1.posx, 0);
		assertEquals(pawn1.posy, 3);
		
		pawn1.move(0, 5);
		assertTrue(!game.checkPieceExist(0, 5));
		assertTrue(game.checkPieceExist(0, 3));
	}
	
	@Test
	public void testCaptureInFrontIllegal() {
		pawn2 = new Pawn(0, 2, game, 1);
		game.board[0][2] = pawn2;
		
		pawn1.move(0, 2);
		assertTrue(game.checkPieceExist(0, 2));
		assertTrue(game.checkPieceExist(0, 1));
	}
	
	@Test
	public void testCapture() {
		pawn2 = new Pawn(1, 2, game, 1);
		game.board[1][2] = pawn2;
		
		pawn1.move(1, 2);
		assertTrue("new location has pawn", game.checkPieceExist(1, 2));
		assertTrue("old location doesn't have pawn", !game.checkPieceExist(0, 1));
		assertTrue(game.board[1][2].player==0);
		assertEquals(pawn1.posx, 1);
		assertEquals(pawn1.posy, 2);
	}

}
