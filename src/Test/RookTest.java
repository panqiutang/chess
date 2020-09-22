package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.Game;
import chessGame.Pawn;
import chessGame.Rook;

public class RookTest {
	private static Game game;
	Rook rook1;
	Pawn pawn1;

	@Before
	public void setUpBoard() {
		game = new Game();
		rook1 = new Rook(3, 3, game, 0);
		game.board[3][3] = rook1;
	}
	
	@Test
	public void testMoveUp() {
		rook1.move(3, 4);
		assertTrue(game.checkPieceExist(3, 4));
		assertTrue(!game.checkPieceExist(3, 3));
	}	
	
	@Test
	public void testMoveRight() {
		rook1.move(4, 3);
		assertTrue(game.checkPieceExist(4, 3));
		assertTrue(!game.checkPieceExist(3, 3));
	}
	
	@Test
	public void testMoveIllegal() {
		rook1.move(1, 1);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(!game.checkPieceExist(1, 1));
	}
		
	@Test
	public void testLeapRightIllegal() {
		pawn1 = new Pawn(4, 3, game, 1);
		game.board[4][3] = pawn1;
		
		rook1.move(5, 3);
		assertTrue(game.checkPieceExist(4, 3));
		assertTrue(game.checkPieceExist(3, 3));
	}
	
	@Test
	public void testLeapDownIllegal() {
		pawn1 = new Pawn(3, 2, game, 1);
		game.board[3][2] = pawn1;
		
		rook1.move(3, 1);
		assertTrue(game.checkPieceExist(3, 2));
		assertTrue(game.checkPieceExist(3, 3));
	}
		
	@Test
	public void testCapture() {
		pawn1 = new Pawn(4, 3, game, 1);
		game.board[4][3] = pawn1;
		
		rook1.move(4, 3);
		assertTrue(game.checkPieceExist(4, 3));
		assertTrue(!game.checkPieceExist(3, 3));
	}


}