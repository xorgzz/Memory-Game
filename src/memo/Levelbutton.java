package memo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class Levelbutton extends JButton {

	public Levelbutton(String text) {
		super(text);
		
		Font font = Util.loadFont("fonts/pixel.ttf", Util.screenHeight()/20);
		super.setFont(font);
		super.setBackground(Color.decode("#b8d8e3"));
		
		Border compoundBorder = BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.decode("#535878"), 2),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		);
		
		 super.setBorder(compoundBorder);
		
	}
}
