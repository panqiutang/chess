package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import chessGame.Bishop;
import chessGame.Game;
import chessGame.King;
import chessGame.Knight;
import chessGame.Pawn;
import chessGame.Queen;
import chessGame.Rook;

public class GameTest {
	private static Game game;
	private static Pawn[] pawn1 = new Pawn[8], pawn2 = new Pawn[8];
	private static Rook rook1, rook2, rook3, rook4;
	private static Knight knight1, knight2, knight3, knight4;
	private static Bishop bishop1, bishop2, bishop3, bishop4;
	private static King king1, king2;
	private static Queen queen1, queen2;

	@Test
	public void foolsMate() {
		game = new Game();
		
		game.startNewBoard();
		king1 = (King)game.board[4][0];
		king2 = (King)game.board[4][7];
		assertTrue(!game.gameEnd(king1, king2));
		
		game.board[5][1].move(5, 2);
		assertTrue(!game.gameEnd(king1, king2));
		
		game.board[4][6].move(4, 4);
		assertTrue(!game.gameEnd(king1, king2));
		
		game.board[6][1].move(6, 3);
		assertTrue(!game.gameEnd(king1, king2));
		
		game.board[3][7].move(7, 3);
		assertTrue(game.gameEnd(king1, king2));
	}
	
	@Test
	public void scholarsMate() {
		game = new Game();
		
		pawn1 = new Pawn[8];
		for (int i = 0; i <= 7; i++) {
			pawn1[i] = new Pawn(i, 1, game, 0);
			game.board[i][1] = pawn1[i];
		}
		rook1 = new Rook(0, 0, game, 0);
		game.board[0][0] = rook1;
		rook2 = new Rook(7, 0, game, 0);
		game.board[7][0] = rook2;
		knight1 = new Knight(1, 0, game, 0);
		game.board[1][0] = knight1;
		knight2 = new Knight(6, 0, game, 0);
		game.board[6][0] = knight2;
		bishop1 = new Bishop(2, 0, game, 0);
		game.board[2][0] = bishop1;
		bishop2 = new Bishop(5, 0, game, 0);
		game.board[5][0] = bishop2;
		king1 = new King(4, 0, game, 0);
		game.board[4][0] = king1;
		queen1 = new Queen(3, 0, game, 0);
		game.board[3][0] = queen1;
		
		pawn2 = new Pawn[8];
		for (int i = 0; i <= 7; i++) {
			pawn2[i] = new Pawn(i, 6, game, 1);
			game.board[i][6] = pawn2[i];
		}
		rook3 = new Rook(0, 7, game, 1);
		game.board[0][7] = rook3;
		rook4 = new Rook(7, 7, game, 1);
		game.board[7][7] = rook4;
		knight3 = new Knight(1, 7, game, 1);
		game.board[1][7] = knight3;
		knight4 = new Knight(6, 7, game, 1);
		game.board[6][7] = knight4;
		bishop3 = new Bishop(2, 7, game, 1);
		game.board[2][7] = bishop3;
		bishop4 = new Bishop(5, 7, game, 1);
		game.board[5][7] = bishop4;
		king2 = new King(4, 7, game, 1);
		game.board[4][7] = king2;
		queen2 = new Queen(3, 7, game, 1);
		game.board[3][7] = queen2;

		assertTrue(!game.gameEnd(king1, king2));
		
		pawn1[4].move(4, 3);
		assertTrue(!game.gameEnd(king1, king2));
		
		pawn2[4].move(4, 4);
		assertTrue(!game.gameEnd(king1, king2));
		
		queen1.move(7, 4);
		assertTrue(!game.gameEnd(king1, king2));
		
		knight3.move(2, 5);
		assertTrue(!game.gameEnd(king1, king2));
		
		bishop2.move(2, 3);
		assertTrue(!game.gameEnd(king1, king2));
		
		knight4.move(5, 5);
		assertTrue(!game.gameEnd(king1, king2));
		
		queen1.move(5, 6);
		assertTrue(game.gameEnd(king1, king2));
	}

	@Test
	public void checkMate1() {
		game = new Game();
		King king1 = new King(5, 4, game, 0);
		game.board[5][4] = king1;
		Rook rook1 = new Rook(7, 0, game, 0);
		game.board[7][0] = rook1;
		King king2 = new King(7, 4, game, 1);
		game.board[7][4] = king2;
		
		game.turn = 1;
		
		assertTrue(game.checkMate(king2));
		assertTrue(game.gameEnd(king1, king2));
	}

	@Test
	public void notCheckMate1() {
		game = new Game();
		King king1 = new King(5, 3, game, 0);
		game.board[5][4] = king1;
		Rook rook1 = new Rook(7, 0, game, 0);
		game.board[7][0] = rook1;
		King king2 = new King(7, 4, game, 1);
		game.board[7][4] = king2;
		
		game.turn = 1;
		
		assertTrue(!game.checkMate(king2));
	}

	@Test
	public void staleMate1() {
		game = new Game();
		King king1 = new King(5, 5, game, 0);
		game.board[5][5] = king1;
		Pawn pawn1 = new Pawn(5, 6, game, 0);
		game.board[5][6] = pawn1;
		King king2 = new King(5, 7, game, 1);
		game.board[5][7] = king2;
		
		//assertTrue(game.staleMate(king1, king2));
	}
	
	@Test
	public void staleMate2() {
		game = new Game();
		King king1 = new King(1, 5, game, 0);
		game.board[1][5] = king1;
		Rook rook1 = new Rook(7, 7, game, 0);
		game.board[7][7] = rook1;
		King king2 = new King(0, 7, game, 1);
		game.board[0][7] = king2;
		Bishop bishop2 = new Bishop(1, 7, game, 1);
		game.board[1][7] = bishop2;
		
		//assertTrue(game.staleMate(king1, king2));
	}
	
	@Test
	public void staleMate3() {
		game = new Game();
		King king1 = new King(6, 4, game, 0);
		game.board[6][4] = king1;
		Pawn pawn1 = new Pawn(1, 2, game, 0);
		game.board[1][2] = rook1;
		King king2 = new King(0, 0, game, 1);
		game.board[0][0] = king2;
		Pawn pawn2 = new Pawn(0, 1, game, 1);
		game.board[0][1] = pawn2;
		
		//assertTrue(game.staleMate(king1, king2));
	}
	

	@Test
	public void whiteMovesFirst() {
		game = new Game();
		King king1 = new King(5, 4, game, 0);
		game.board[5][4] = king1;
		King king2 = new King(7, 4, game, 1);
		game.board[7][4] = king2;
		
		king2.move(6, 4);
		assertTrue(game.checkPieceExist(7, 4));
		assertTrue(!game.checkPieceExist(6, 4));
		
		king1.move(4, 4);
		assertTrue(game.checkPieceExist(4, 4));
		assertTrue(!game.checkPieceExist(5, 4));
	}
	
	@Test
	public void testUndoOnce() {
		game = new Game();
		game.startNewBoard();
		
		game.board[1][1].move(1, 2);
		game.undo();
		
		assertTrue(game.checkPieceExist(1, 1));
		assertTrue(!game.checkPieceExist(1, 2));
	}

}