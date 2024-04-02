package memo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Gui {
	private GameBoard gameBoard;
	
	private int scoreValue = 0;
	private JFrame frame;
	private JPanel panel;
	private JLabel score;
	private int gameSize;

	private Card[] uncoveredCards = new Card[2];
	
	public Gui(int gameSize, GameBoard gameBoard) {
		this.gameBoard = gameBoard;
		this.gameSize = gameSize;

		this.uncoveredCards[0] = new Card();
		this.uncoveredCards[1] = new Card();
		
		frame = new JFrame("Memory Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(gameSize, gameSize));
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		
		HUD();
		addCards(gameSize);
	}
	
	public void HUD() {
		JPanel textPanel = new JPanel(new BorderLayout());
		
		JLabel scorePlaceholder = new JLabel("   Score: ");
		this.score = new JLabel("0");
		JLabel exitInfo = new JLabel("Press [ ESC ] to exit   ");
		
		int verticalPadding = 25;
		int horizontalPadding = 0;
		
		String bckgColor = "#9db0ce";
		
		scorePlaceholder.setBackground(Color.decode(bckgColor));
		scorePlaceholder.setOpaque(true);
		
		this.score.setBackground(Color.decode(bckgColor));
		this.score.setOpaque(true);
		
		exitInfo.setBackground(Color.decode(bckgColor));
		exitInfo.setOpaque(true);
		
		Border compoundBorder = BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode("#535878")),
			BorderFactory.createEmptyBorder(verticalPadding, horizontalPadding, verticalPadding, horizontalPadding)
		);
		
		scorePlaceholder.setBorder(compoundBorder);
		this.score.setBorder(compoundBorder);
		exitInfo.setBorder(compoundBorder);
		
		Font font = Util.loadFont("fonts/pixel_bold.ttf", (int)Util.screenHeight()/32);
		
		scorePlaceholder.setFont(font);
		exitInfo.setFont(font);
		this.score.setFont(font);
		
		textPanel.add(scorePlaceholder, BorderLayout.WEST);
		textPanel.add(this.score, BorderLayout.CENTER);
		textPanel.add(exitInfo, BorderLayout.EAST);
		
		frame.getContentPane().add(textPanel, BorderLayout.NORTH);
		frame.revalidate();

	}
	
	public void updateScore() {
		this.score.setText(Integer.toString(this.scoreValue));
	}
	
	public void selectedCard(Card selectedCard) {
		frame.requestFocusInWindow();
		if (selectedCard.isScored) {
			return;
		}

		if (!this.uncoveredCards[0].isDummy && 
		(this.uncoveredCards[0].xID == selectedCard.xID) && 
		(this.uncoveredCards[0].yID == selectedCard.yID)) {

			return;
		}

		selectedCard.updateTextValue(Integer.toString(gameBoard.getValue(selectedCard.xID, selectedCard.yID)));
		selectedCard.unhide();

		if (this.uncoveredCards[0].isDummy) {
			this.uncoveredCards[0] = selectedCard;
			return;
		}
		

		if (this.uncoveredCards[1].isDummy) {
			this.uncoveredCards[1] = selectedCard;
			if (gameBoard.checkPair(
				uncoveredCards[0].xID, uncoveredCards[0].yID, 
				uncoveredCards[1].xID, uncoveredCards[1].yID
			)) {
				scoreValue += 1;
				updateScore();
				uncoveredCards[0].scoredCard();
				uncoveredCards[1].scoredCard();
				// 
				//  YOU WIN !!!
				// 
				if (scoreValue == gameSize * gameSize / 2) {
					Font font = Util.loadFont("fonts/pixel_bold.ttf", (int)Util.screenHeight()/8);
					frame.getContentPane().remove(panel);
					panel = new JPanel(new GridBagLayout());
					JLabel winPrompt = new JLabel("You win !!!");
					panel.setBackground(Color.decode("#9db0ce"));
					winPrompt.setFont(font);
					panel.add(winPrompt);
					frame.getContentPane().add(panel);
				}
			}
			return;
		}

		
		for (int i=0; i<2; i++) {
			if (!uncoveredCards[i].isScored) {
				uncoveredCards[i].hideCard();
			}
			uncoveredCards[i] = new Card();
		}
		this.uncoveredCards[0] = selectedCard;

		return;

	}

	public void addCards(int gameSize) {
		panel.setFocusTraversalPolicy(null);
		for (int i = 0; i < gameSize; i++) {
			for (int j = 0; j < gameSize; j++) {
				Card card = new Card("Memory Game", i, j);
				card.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						selectedCard(card);
					}
				});
				panel.add(card);
			}
		}
		frame.revalidate();
    }
}
