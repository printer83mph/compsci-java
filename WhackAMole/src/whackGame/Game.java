package whackGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import javafx.scene.input.MouseEvent;

public class Game extends JFrame {

	private JPanel panel;
	private JLabel scoreLbl;
	private JLabel timeLeftLbl;
	private JLabel highScoreLbl;
	private JButton startBtn;
	private JLabel[] holes = new JLabel[16];
	
	private Timer timer;
	
	private int[] board = new int[16];
	private int score = 0; // not restartable w/o reinstancing
	private int timeLeft = 30;
	private int highScore = 0;
	
	private void genRandMole() {
		Random r = new Random(System.currentTimeMillis());
		int moleID = r.nextInt(16);
		board[moleID] = 1;
		holes[moleID].setIcon(loadImage("/moleOut.png"));
		
	}
	
	private ImageIcon loadImage(String path) {
		Image img = new ImageIcon(this.getClass().getResource(path)).getImage();
		Image scaledImage = img.getScaledInstance(132, 132, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}
	
	private void initGUI() {
		
		setTitle("Whack-a-mole");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 720);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel titleLbl = new JLabel("Whack-a-Mole");
		titleLbl.setFont(new Font("Century Gothic", Font.BOLD, 20));
		titleLbl.setForeground(new Color(153, 204, 0));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(0, 0, 602, 47);
		contentPane.add(titleLbl);
		
		JLabel scoreLbl = new JLabel("Score: 0");
		scoreLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		scoreLbl.setFont(new Font("Cambria", Font.BOLD, 14));
		scoreLbl.setForeground(new Color(135, 206, 250));
		scoreLbl.setBounds(423, 54, 144, 33);
		contentPane.add(scoreLbl);
		
		JLabel timeLeftLbl = new JLabel("30");
		timeLeftLbl.setHorizontalAlignment(SwingConstants.CENTER);
		timeLeftLbl.setFont(new Font("Cambria Math", Font.BOLD, 24));
		timeLeftLbl.setForeground(new Color(240, 128, 128));
		timeLeftLbl.setBounds(232, 54, 144, 33);
		contentPane.add(timeLeftLbl);
		
		JLabel highScoreLbl = new JLabel("Highscore: 0");
		highScoreLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		highScoreLbl.setFont(new Font("Cambria", Font.BOLD, 14));
		highScoreLbl.setForeground(new Color(255, 255, 0));
		highScoreLbl.setBounds(433, 18, 134, 33);
		contentPane.add(highScoreLbl);
		
		startBtn = new JButton("Start");
		startBtn.setBackground(Color.WHITE);
		startBtn.setBounds(32, 60, 110, 33);
		contentPane.add(startBtn);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 102, 0));
		panel.setBounds(32, 105, 535, 546);
		panel.setLayout(null);
		
		contentPane.add(panel);
		
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				JLabel newLabel = new JLabel(Integer.toString(x + 4*y));
				newLabel.setName(Integer.toString(x + 4*y));
				newLabel.setBounds(132 * x, 132 * y, 132, 132);
				holes[x + 4*y] = newLabel;
				panel.add(holes[x + y*4]);
			}
		}
		
	}
	
	private void initEvents() {
		
		for (int i = 0; i < holes.length; i++) {
			holes[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					JLabel lbl = (JLabel)e.getSource();
					int id = Integer.parseInt(lbl.getName());
					pressedButton(id);
				}
			});
		}
		
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startBtn.setEnabled(false);
				resetBoard();
				genRandMole();
				timer.start();
			}
		});
		
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timeLeft == 0) {
					timeLeftLbl.setText("" + timeLeft);
					timer.stop();
					gameOver();
				}
				timeLeftLbl.setText("" + timeLeft);
				timeLeft--;
			}
		});
		
	}
	
	private void resetBoard() {
		
		for (int i = 0; i < holes.length; i++) {
			holes[i].setIcon(loadImage("/moleIn.png"));
			board[i] = 0;
		}
		
	}
	
	private void pressedButton(int id) {
		
		score += board[id] == 1 ? 1 : -1;
		
		scoreLbl.setText("Score: " + score);
		
		resetBoard();
		
		genRandMole();
		
	}
	
	private void gameOver() {
		
		startBtn.setEnabled(true);
		if (score > highScore) {
			highScore = score;
			highScoreLbl.setText("Highscore: " + highScore);
			JOptionPane.showMessageDialog(this, "Your final score is: " + score, "You beat the high score!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Your final score is: " + score, "Game over!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		score = 0;
		timeLeft = 30;
		scoreLbl.setText("Score: 0");
		timeLeftLbl.setText("30");
		
		resetBoard();
		
	}
	
	public Game() {
		initGUI();
		initEvents();
		resetBoard();
		genRandMole();
	}

	public static void main(String[] args) {
		Game frame = new Game();
		frame.setVisible(true);
	}
	
}
