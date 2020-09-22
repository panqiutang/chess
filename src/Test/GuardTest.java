package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.Bishop;
import chessGame.Game;
import chessGame.Guard;
import chessGame.Pawn;

public class GuardTest {
	Game game;
	Guard guard;
	Pawn pawn;
	Bishop bishop;
	
	@Before
	public void setUpBoard() {
		game = new Game();
		guard = new Guard(3, 3, game, 0);
		game.board[3][3] = guard;
	}

	@Test
	public void testMoveUp() {
		guard.move(3, 4);
		assertTrue(game.checkPieceExist(3, 4));
		assertTrue(!game.checkPieceExist(3, 3));
	}
	
	@Test
	public void testMoveRightDown() {
		guard.move(4, 2);
		assertTrue(game.checkPieceExist(4, 2));
		assertTrue(!game.checkPieceExist(3, 3));
	}
	
	@Test
	public void testCaptureLeft() {
		pawn = new Pawn(0, 3, game, 1);
		game.board[0][3] = pawn;
		
		guard.move(0, 3);
		assertTrue(game.checkPieceExist(0, 3));
		assertTrue(!game.checkPieceExist(3, 3));
	}
	
	@Test
	public void testCaptureFrontIllegal() {
		pawn = new Pawn(3, 4, game, 1);
		game.board[3][4] = pawn;
		
		guard.move(3, 4);
		assertTrue(game.checkPieceExist(3, 4));
		assertTrue(game.checkPieceExist(3, 3));
	}
	
	@Test
	public void testCaptureFromFrontIllegal() {
		pawn = new Pawn(4, 4, game, 1);
		game.board[4][4] = pawn;
		
		game.turn = 1;
		pawn.move(3, 3);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(4, 4));
	}
	
	@Test
	public void testCaptureFromBack() {
		bishop = new Bishop(2, 2, game, 1);
		game.board[2][2] = bishop;
		
		game.turn = 1;
		bishop.move(3, 3);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(!game.checkPieceExist(2, 2));
	}

}
