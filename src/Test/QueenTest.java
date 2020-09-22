package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chessGame.Game;
import chessGame.Pawn;
import chessGame.Queen;

public class QueenTest {
	private static Game game;
	Queen queen1;
	Pawn pawn1;

	@Before
	public void setUpBoard() {
		game = new Game();
		queen1 = new Queen(3, 3, game, 0);
		game.board[3][3] = queen1;
	}
	
	@Test
	public void testMoveIllegal() {
		queen1.move(2, 1);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(!game.checkPieceExist(2, 1));
	}
		
	@Test
	public void testMoveRight() {
		queen1.move(3, 0);
		assertTrue(game.checkPieceExist(3, 0));
		assertTrue(!game.checkPieceExist(3, 3));
	}
		
	@Test
	public void testMoveLeftDown() {
		queen1.move(2, 2);
		assertTrue(game.checkPieceExist(2, 2));
		assertTrue(!game.checkPieceExist(3, 3));
	}
		
	@Test
	public void testLeapIllegal() {
		pawn1 = new Pawn(3, 4, game, 1);
		game.board[3][4] = pawn1;
		
		queen1.move(3, 5);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(3, 4));
	}
	
	@Test
	public void testCapture() {
		pawn1 = new Pawn(3, 4, game, 1);
		game.board[3][4] = pawn1;
		
		queen1.move(3, 4);
		assertTrue(!game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(3, 4));
	}

	@Test
	public void leapMultipleDirectionsIllegal() {
		game = new Game();
		Queen queen1 = new Queen(3, 3, game, 0);
		game.board[3][3] = queen1;
		Pawn pawn1 = new Pawn(3, 1, game, 0);
		game.board[3][1] = pawn1;
		Pawn pawn2 = new Pawn(4, 4, game, 0);
		game.board[4][4] = pawn2;
		Pawn pawn3 = new Pawn(2, 4, game, 0);
		game.board[2][4] = pawn3;
		
		/* leap down */
		queen1.move(3, 0);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(3, 1));
		assertTrue(!game.checkPieceExist(3, 0));
		
		/* leap right up */
		queen1.move(5, 5);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(4, 4));
		assertTrue(!game.checkPieceExist(5, 5));
		
		/* leap left up */
		queen1.move(1, 5);
		assertTrue(game.checkPieceExist(3, 3));
		assertTrue(game.checkPieceExist(2, 4));
		assertTrue(!game.checkPieceExist(1, 5));
	}

}
