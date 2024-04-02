package memo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.io.File;
import javax.sound.sampled.*;

public class Menu {
	private JFrame frame;
	private JPanel panel;

	public Menu() {
		frame = new JFrame("Memory Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.decode("#9db0ce"));

		int verticalPadding = 96;
		int horizontalPadding = 0;

		Border compoundBorder = BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode("#535878")),
			BorderFactory.createEmptyBorder(verticalPadding, horizontalPadding, verticalPadding, horizontalPadding)
		);
		
		JLabel label = new JLabel("Select Level:", SwingConstants.CENTER);
		Font font = Util.loadFont("fonts/pixel_bold.ttf", (int)Util.screenHeight()/10);
		label.setFont(font);
		label.setBorder(compoundBorder);
		panel.add(label, BorderLayout.NORTH);

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		playMusic();

		JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		buttonPanel.setBackground(Color.decode("#9db0ce"));

		Levelbutton easyButton = new Levelbutton("Easy");
		easyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.requestFocusInWindow();
				Game game = new Game(4);
			}
		});
		buttonPanel.add(easyButton);

		Levelbutton mediumButton = new Levelbutton("Medium");
		mediumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.requestFocusInWindow();
				Game game = new Game(6);
			}
		});
		buttonPanel.add(mediumButton);

		Levelbutton hardButton = new Levelbutton("Hard");
		hardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.requestFocusInWindow();
				Game game = new Game(8);
			}
		});
		buttonPanel.add(hardButton);

		panel.add(buttonPanel, BorderLayout.CENTER);

		frame.getContentPane().add(panel);
		frame.setVisible(true);

		frame.setFocusable(true);
		frame.requestFocusInWindow();
	}

	public static void playMusic() {
		try {
			File soundFile = new File("music/good_tunes.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			
			// Set loop count to LOOP_CONTINUOUSLY
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
