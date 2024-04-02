package memo;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;

public class Util {
	
	public static int screenWidth() {
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}
	
	public static int screenHeight() {
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
	
	public static Font loadFont(String path, int size) {
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			Font font = Font.createFont(Font.TRUETYPE_FONT, fis);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			font = font.deriveFont((float) size);
			fis.close();
			return font;
		} catch (Exception ex) {
			ex.printStackTrace();
			return new Font("Arial", Font.PLAIN, 12);
		}
	}
}
