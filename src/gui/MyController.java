package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import chessGame.Game;
import chessGame.Step;

public class MyController {
	ActionListener undolistener;
	ActionListener forfeitlistener;
	ActionListener restartlistener;
	ActionListener piecelistener;
	ActionListener namelistener;
	
	public MyController(Game game, GUI gui) {
		/*
		 * A listener for the Undo button. Set the image icons of the original and new location
		 * buttons of the piece.
		 */
		undolistener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		Step step = game.undo();
        		if (step == null) {
        			return;
        		}
        		
        		JButton oldbutton = gui.boardButton[step.oldx][step.oldy];
        		JButton newbutton = gui.boardButton[step.newx][step.newy];
        		oldbutton.setIcon(newbutton.getIcon());
        		
        		if (step.captured != null) {
        			gui.readPieceButtonImageIcon(step.captured, newbutton);
        		} else {
        			newbutton.setIcon(null);
        		}
        	}
		};
        
        /*
         * A listener for the forfeit button for a player. Asks if the player wants to
         * forfeit the game in a pop up window first. If yes, the opponent wins.
         */
        forfeitlistener = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JButton button = (JButton) e.getSource();
        		String player = (String) button.getClientProperty("player");
        		String opponent;
        		if (player == "white") {
        			opponent = "black";
        		} else {
        			opponent = "white";
        		}
        		int returnvalue = JOptionPane.showConfirmDialog(null, player+": Do you want to forfeit the game?", "Forfeit", JOptionPane.YES_NO_OPTION);
        		if (returnvalue == 0) {
        			JOptionPane.showMessageDialog(null, opponent+" has won!", "Game end", JOptionPane.INFORMATION_MESSAGE);
        			gui.win(opponent);
            	}
        	}
        };
        
        /*
         * A listener for the Restart button. Asks if both user agrees to restart in a pop up window.
         * If both yes, then restart the game and doesn't change the score.
         */
        restartlistener = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int return1 = JOptionPane.showConfirmDialog(null, "White: Do you agree to restart the game?", "Start Over", JOptionPane.YES_NO_OPTION);
        		int return2 = JOptionPane.showConfirmDialog(null, "Black: Do you agree to restart the game?", "Start Over", JOptionPane.YES_NO_OPTION);
            	if (return1 == 0 && return2 == 0) {
            		gui.clearAndStartNewBoard();
            	}
        	}
        };
        
        /*
         * A listener for the piece buttons. First check if the button is the piece to move or the
         * destination. Then check if the move is legal. If successful, check if the game has ended.
         */
        piecelistener = new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			JButton button = (JButton)e.getSource();
    			if (gui.pieceToMove == null) { // piece to move
    				int x = (int)button.getClientProperty("x");
	    			int y = (int)button.getClientProperty("y");
	    			gui.pieceToMove = game.board[x][y];
	    			if (gui.pieceToMove == null) { // button is not a piece
	    				return;
	    			}
	    			gui.pieceToMoveButton = button;
	    			button.setBackground(Color.blue);
    			} else { // destination to move
    				int x = (int)button.getClientProperty("x");
	    			int y = (int)button.getClientProperty("y");
	    			int oldx = (int)gui.pieceToMoveButton.getClientProperty("x");
	    			int oldy = (int)gui.pieceToMoveButton.getClientProperty("y");
	    			
	    			if ((oldx+oldy)%2 == 0) {
        	    		gui.pieceToMoveButton.setBackground(Color.gray);
        	    	} else {
        	    		gui.pieceToMoveButton.setBackground(Color.white);
        	    	}
	    			
    				if (gui.pieceToMove.move(x, y)) { // move successful
    					Icon oldicon = gui.pieceToMoveButton.getIcon();
    					button.setIcon(oldicon);
    					gui.pieceToMoveButton.setIcon(null);
    					
    					/* check game end */
    					if (game.turn == 1) {
    						if (game.checkMate(game.king_black)) { // white win
    							JOptionPane.showMessageDialog(null, "Checkmate, white has won!", "Game end", JOptionPane.INFORMATION_MESSAGE);
    							gui.win("white");
    						} else if (game.inCheck(game.king_black.posx, game.king_black.posy, 1)) {
    							JOptionPane.showMessageDialog(null, "Black is in check", "Check", JOptionPane.INFORMATION_MESSAGE);
    						}
    					} else {
    						if (game.checkMate(game.king_white)){ // black win
	    						JOptionPane.showMessageDialog(null, "Checkmate, black has won!", "Game end", JOptionPane.INFORMATION_MESSAGE);
	    						gui.win("black");
    						} else if (game.inCheck(game.king_white.posx, game.king_white.posy, 0)) {
    							JOptionPane.showMessageDialog(null, "White is in check", "Check", JOptionPane.INFORMATION_MESSAGE);
    						}
    					}
    				} else { // move unsuccessful
    					JOptionPane.showMessageDialog(null, "Illegal move", "!", JOptionPane.INFORMATION_MESSAGE);
    					
    				}
    				
    				gui.pieceToMove = null;
    			}
    		}
    	};
    	
    	/*
    	 * A listener for the Change Name button. Prompts the user to enter their new name is a 
    	 * pop up window, and set the corresponding player's name.
    	 */
    	namelistener = new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String name = JOptionPane.showInputDialog("Please enter your new name");
    			JButton button = (JButton) e.getSource();
    			String player = (String) button.getClientProperty("player");
    			gui.changePlayerName(player, name);
    		}
    	};
	}

}
