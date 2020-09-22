package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import chessGame.Bishop;
import chessGame.Cannon;
import chessGame.Game;
import chessGame.Guard;
import chessGame.King;
import chessGame.Knight;
import chessGame.Pawn;
import chessGame.Piece;
import chessGame.Queen;
import chessGame.Rook;
 

/*
 * A GUI for the chess game.
 */
public class GUI{
	public Game game;
	Piece pieceToMove = null;
	JButton pieceToMoveButton = null;
	int score_white;
	int score_black;
	JLabel scorelabel;
	public JButton[][] boardButton = new JButton[8][8];
	JPanel boardPanel;
	MyController controller;
	JLabel playerlabelblack;
	JLabel playerlabelwhite;
 
    public GUI(Game game){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        JFrame window = new JFrame("Chess Game");
        window.setSize(600, 700);
        JPanel myPanel = initializePanel();
        window.setContentPane(myPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        score_white = 0;
        score_black = 0;
        JButton btn_restart = initializeButton(myPanel, "Restart");
        JButton btn_forfeitblack = initializeButton(myPanel, "Black: Forfeit");
        btn_forfeitblack.putClientProperty("player", "black");
        JButton btn_forfeitwhite = initializeButton(myPanel, "White: Forfeit");
        btn_forfeitwhite.putClientProperty("player", "white");
        JButton btn_undo = initializeButton(myPanel, "Undo");
        scorelabel = new JLabel("Score: white " + score_white + " - black " + score_black);
        myPanel.add(scorelabel);
        playerlabelblack = new JLabel("Black player: cat");
        myPanel.add(playerlabelblack);
        JButton btn_playerblackname = initializeButton(myPanel, "Change name");
        btn_playerblackname.putClientProperty("player", "black");
        playerlabelwhite = new JLabel("White player: fish");
        myPanel.add(playerlabelwhite);
        JButton btn_playerwhitename = initializeButton(myPanel, "Change name");
        btn_playerwhitename.putClientProperty("player", "white");
        
        /* create the chess board */
        this.game = game;
        game.startNewBoard();
        boardPanel = new JPanel(new GridBagLayout());
        boardPanel.setMaximumSize(new Dimension(500, 500));
        myPanel.add(boardPanel);
        
        /* add listeners */
        controller = new MyController(game, this);
        newGame();
        btn_restart.addActionListener(controller.restartlistener);
        btn_forfeitblack.addActionListener(controller.forfeitlistener);
        btn_forfeitwhite.addActionListener(controller.forfeitlistener);
        btn_undo.addActionListener(controller.undolistener);
        btn_playerwhitename.addActionListener(controller.namelistener);
        btn_playerblackname.addActionListener(controller.namelistener);
        
        window.setVisible(true);
        
    }
    
    /*
     * Setup the board UI layout for a new game.
     */
    public void newGame() {
    	GridBagConstraints c = new GridBagConstraints();
    	for (int y = 0; y < 9; y++) {
        	for (int x = 0; x < 9; x++) {
        		if (x == 8 && y == 8) {
        			continue;
        		} else if (x == 8) { // create rank labels
        			JLabel label = new JLabel("<html><h1>"+(8-y)+"<h1></html>");
        			c.insets = new Insets(0,20,0,0);
        			addLabelToGridBag(boardPanel, c, y, x, label);
        		} else if (y == 8) { // create file labels
        			JLabel label = new JLabel("<html><h1>"+(char)(65+x)+"<h1></html>");
        			c.insets = new Insets(0,0,20,0);
        			addLabelToGridBag(boardPanel, c, y, x, label);
        		} else { // create Piece button
        			Piece piece = game.board[x][7-y];
        			JButton button = new JButton();
        			boardButton[x][7-y] = button;
        			
        	    	if (piece != null) {
        	    		readPieceButtonImageIcon(piece, button);
        	    	}
        	    	
        	    	/* configure the button */
        	    	if ((x+y)%2 == 1) {
        	    		button.setBackground(Color.gray);
        	    	} else {
        	    		button.setBackground(Color.white);
        	    	}
        	    	button.setBorderPainted( false );
        	    	button.putClientProperty("x", x);
        	    	button.putClientProperty("y", 7-y);
        	    	c.gridx = x;
        	    	c.gridy = y;
        	    	c.insets = new Insets(0,0,0,0);
        	    	button.setMinimumSize(new Dimension(60,60));
        	    	button.setPreferredSize(new Dimension(60,60));
        	    	
        	    	button.addActionListener(controller.piecelistener);
        	    	
        	    	boardPanel.add(button, c);
        		}
        	}
        	
        }
    }
    
    /*
     * Remove all components of the board, start a new game, set up the board, and refresh.
     */
    public void clearAndStartNewBoard() {
    	boardPanel.removeAll();
		game.startNewBoard();
		newGame();
		boardPanel.revalidate();
		boardPanel.repaint();
    }
    
    /*
     * A player wins: update the score, and start a new game.
     */
    public void win(String player) {
    	if (player == "white") {
    		score_white += 1;
    	} else {
    		score_black += 1;
    	}
		scorelabel.setText("Score: white " + score_white + " - black " + score_black);
		clearAndStartNewBoard();
    }
    
    /*
     * Change a player's name.
     * @param String player the white or black player to change name
     * @param String name the new name for the player
     */
    public void changePlayerName(String player, String name) {
    	if (player == "white") {
    		playerlabelwhite.setText("White player: " + name);
    	} else {
    		playerlabelblack.setText("Black player: " + name);
    	}
    }

    /*
     * Set the image icon of a button to a corresponding piece.
     * @param Piece piece the piece to set the image icon to
     * @param JButton button the button to set the image icon for
     */
	public void readPieceButtonImageIcon(Piece piece, JButton button) {
		/* read piece icon image path */
		String imagePath = "D:/eclipse-workspace/mp1/images/";
		
		if (piece.player == 0) {
			imagePath += "white_";
		} else {
			imagePath += "black_";
		}
		
		if (piece instanceof Pawn) {
			imagePath += "pawn.png";
		} else if (piece instanceof Rook) {
			imagePath += "rook.png";
		} else if (piece instanceof Bishop) {
			imagePath += "bishop.png";
		} else if (piece instanceof Knight) {
			imagePath += "knight.png";
		} else if (piece instanceof Queen) {
			imagePath += "queen.png";
		} else if (piece instanceof King) {
			imagePath += "king.png";
		} else if (piece instanceof Cannon) {
			imagePath += "cannon.png";
		} else if (piece instanceof Guard) {
			imagePath += "guard.png";
		}
		
		/* set piece icon image */
		try {
			BufferedImage buttonIcon = ImageIO.read(new File(imagePath));
			button.setIcon(new ImageIcon(buttonIcon));
			
		} catch (Exception e) {
			String workingDir = System.getProperty("user.dir");
		    System.out.println("Current working directory : " + workingDir);
			e.printStackTrace();
		}
	}
   
    /*
     * Add labels to the panel
     * @param JPanel boardPanel the panel to place the labels on
     * @param GridBagConstraints c the constraints for the labels
     * @param int y gridy position in GridBagLayout
     * @param int x gridx position in GridBagLayout
     * @param JLabel label the label to place
     */
	private void addLabelToGridBag(JPanel boardPanel, GridBagConstraints c, int y, int x, JLabel label) {
		c.gridx = x;
		c.gridy = y;
		boardPanel.add(label, c);
	}
    
	/*
	 * initialize a button.
	 * @param JPanel myPanel the panel to place the button on
	 * @param String label the label of the button
	 * @return button
	 */
    private JButton initializeButton(JPanel myPanel, String label) {
        JButton button = new JButton(label);
        myPanel.add(button);
        return button;
    }
 
    /* 
     * initialize the main panel
     * @return panel
     */
    private JPanel initializePanel() {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(500,500));
        myPanel.setLayout(new FlowLayout());
        return myPanel;
    }

    public static void main(String[] args) {
    	Game game = new Game();
        new GUI(game);
        
    }
}