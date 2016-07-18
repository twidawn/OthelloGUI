import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/*
 * 오셀로의 모든 칸은 하나의 버튼으로 구성 되며
 * 그 버튼이 맵과 돌이 놓여지는 액션을 취하게 된다.
 * 기본적으로는 모두 빈 사진을 표시하게 되며 사용자가 맵을 누를 시에 그 버튼을 알아내서 알맞은 돌을 표시해주는 역할을 하게 된다. 
 * 
 */
public class GameImage extends JButton {
	private int x, y;
	// static boolean imagesInitialized = false;
	static ImageIcon[] images = new ImageIcon[3];
	public static final int NONE = 0, BLACK = 1, WHITE = 2;
	public int image = NONE;

	public GameImage(int i, int j) {
		
		if ((i + j) % 2 == 0) {
			setBackground(Color.gray);
		} else {
			setBackground(Color.white);
		}
		if (i == 0 && j == 0) {
			images[NONE] = new ImageIcon(
					Toolkit.getDefaultToolkit().getImage("C:\\JavaProjects\\JavaLab2\\OthelloGUI\\image\\empty.png"));
			images[BLACK] = new ImageIcon(
					Toolkit.getDefaultToolkit().getImage("C:\\JavaProjects\\JavaLab2\\OthelloGUI\\image\\black.png"));
			images[WHITE] = new ImageIcon(
					Toolkit.getDefaultToolkit().getImage("C:\\JavaProjects\\JavaLab2\\OthelloGUI\\image\\white.png"));
		}
		setIcon(images[NONE]);
		
		this.x = i;
		this.y = j;

	}

	public void setImage(int i) {
		image = i;
		setIcon(images[image]);
	}

	public void setCoordi(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getImage() {
		return image;
	}

}
