package memo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class Card extends JButton {
	public int yID, xID;
	public boolean isDummy = false;
	public boolean isScored = false;
	
	public Card(String text, int yID, int xID) {
		super(text);
		
		Font font = Util.loadFont("fonts/pixel.ttf", Util.screenHeight()/42);
		super.setFont(font);
		super.setBackground(Color.decode("#b8d8e3"));
		
		Border compoundBorder = BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.decode("#535878"), 2),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		);
		
		 super.setBorder(compoundBorder);
		
		this.yID = yID;
		this.xID = xID;
	}

	public Card() {
		this.isDummy = true;
	}
	
	public void updateTextValue(String newTextValue) {
		setText(newTextValue);
	}

	public void hideCard() {
		Font font = Util.loadFont("fonts/pixel.ttf", Util.screenHeight()/42);
		super.setFont(font);
		updateTextValue("Memory Game");
	}

	public void unhide() {
		Font font = Util.loadFont("fonts/pixel.ttf", Util.screenHeight()/20);
		super.setFont(font);
	}

	public void scoredCard() {
		this.isScored = true;
		super.setBackground(Color.decode("#252525"));
		super.setForeground(Color.decode("#eeeeee"));
	}
}
