package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.Cannon;
import chessGame.Game;
import chessGame.Pawn;

public class CannonTest {
	private static Game game;
	Cannon cannon1;
	Pawn pawn1;
	Pawn pawn2;
	Pawn pawn3;
	Pawn pawn4;
	Pawn pawn5;

	@Before
	public void setUpBoard() {
		game = new Game();
		
		cannon1 = new Cannon(3, 3, game, 0);
		game.board[3][3] = cannon1;
		pawn1 = new Pawn(5, 3, game, 1);
		game.board[5][3] = pawn1;
		pawn2 = new Pawn(6, 3, game, 1);
		game.board[6][3] = pawn2;
		pawn3 = new Pawn(7, 3, game, 1);
		game.board[7][3] = pawn3;
		pawn4 = new Pawn(3, 2, game, 1);
		game.board[3][2] = pawn4;
		pawn5 = new Pawn(3, 1, game, 1);
		game.board[3][1] = pawn5;
		game.board[1][3] = new Pawn(1, 3, game, 1);
		game.board[3][7] = new Pawn(3, 7, game, 1);
		game.board[3][5] = new Pawn(3, 5, game, 1);
		game.board[3][6] = new Pawn(3, 6, game, 1);
		
	}
	
	@Test
	public void testMoveUp() {
		cannon1.move(3, 4);
		assertTrue(game.checkPieceExist(3, 4));
		assertTrue(!game.checkPieceExist(3, 3));
	}
	
	@Test
	public void testMoveLeft() {
		cannon1.move(2, 3);
		assertTrue(game.checkPieceExist(2, 3));
		assertTrue(!game.checkPieceExist(3, 3));
	}
	
	@Test
	public void testCaptureIllegalNoPieceBetween() {
		cannon1.move(5, 3);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(5, 3));
	}
	
	@Test
	public void testCaptureIllegalTwoPieceBetween() {
		cannon1.move(7, 3);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(7, 3));
	}
	
	@Test
	public void testCaptureOnePieceBetween() {
		cannon1.move(6, 3);
		assertTrue(!game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(6, 3));
	}
	
	@Test
	public void testCaptureDown() {
		cannon1.move(3, 1);
		assertTrue(!game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(3, 1));
	}
	
	@Test
	public void testLeapLeftIllegal() {
		cannon1.move(0, 3);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(!game.checkPieceExist(0, 3));
	}
	
	@Test
	public void testCaptureTwoUpIllegal() {
		cannon1.move(3, 7);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(3, 7));
	}

}
