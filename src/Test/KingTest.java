package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.Game;
import chessGame.King;
import chessGame.Pawn;

public class KingTest {
	private static Game game;
	Pawn pawn1;
	King king1;

	@Before
	public void setUpBoard() {
		game = new Game();
		king1 = new King(4, 0, game, 0);
		game.board[4][0] = king1;
	}
	
	@Test
	public void testMoveUp() {
		king1.move(4, 1);
		assertTrue(game.checkPieceExist(4, 1));
		assertTrue(!game.checkPieceExist(4, 0));
		assertEquals(king1.posx, 4);
		assertEquals(king1.posy, 1);
	}
	
	@Test
	public void testPutInCheckIllegalMove() {
		pawn1 = new Pawn(3, 2, game, 1);
		game.board[3][2] = pawn1;
		
		king1.move(4, 1);
		assertTrue(!game.checkPieceExist(4, 1));
		assertTrue(game.checkPieceExist(4, 0));
	}
		
	@Test
	public void testMoveTwoIllegal() {
		king1.move(6, 0);
		assertTrue(game.checkPieceExist(4, 0));
		assertTrue(!game.checkPieceExist(6, 0));
	}
	
	@Test
	public void testCapture() {
		pawn1 = new Pawn(3, 1, game, 1);
		game.board[3][1] = pawn1;
		
		king1.move(3, 1);
		assertTrue(game.checkPieceExist(3, 1));
		assertTrue(!game.checkPieceExist(4, 0));
		assertTrue(game.board[3][1].player==0);
		assertEquals(king1.posx, 3);
		assertEquals(king1.posy, 1);
	}
		
	@Test
	public void testCaptureOwnIllegal() {
		pawn1 = new Pawn(3, 1, game, 0);
		game.board[3][1] = pawn1;
		
		king1.move(3, 1);
		assertTrue(game.checkPieceExist(3, 1));
		assertTrue(game.checkPieceExist(4, 0));
	}
	
	@Test
	public void moveOutsideIllegal() {
		king1.move(4, -1);
		assertTrue(game.checkPieceExist(4, 0));
		assertEquals(king1.posx, 4);
		assertEquals(king1.posy, 0);
	}

}