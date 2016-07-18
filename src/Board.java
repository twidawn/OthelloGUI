import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class Board extends JPanel implements ActionListener{
	Board board;
	GameImage[][] gi;
	
	public Board(Board b){
		super();
		setLayout(new GridLayout(0, Main.getN()));
		board = b;
		gi = new GameImage[Main.getN()][Main.getN()];
		for(int i = 0; i< Main.getN(); i++){
			for(int j = 0; j < Main.getN(); j++){
				gi[i][j] = new GameImage(i,j);
				gi[i][j].addActionListener(this);
				add(gi[i][j]);
			}
		}
		
		gi[3][4].setImage(1);
		gi[4][3].setImage(1);
		gi[3][3].setImage(2);
		gi[4][4].setImage(2);
				
		printDosMap();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GameImage temp = (GameImage) e.getSource();
		Main.x = temp.getX();
		Main.y = temp.getY();
		/*
		if(temp.getImage() == 1)
			temp.setImage(2);
		else if(temp.getImage() == 2 || temp.getImage() == 0)
			temp.setImage(1);
		*/
		//printDosMap();
		
	}
	
	public void printDosMap(){
		for(int i = 0; i< Main.getN(); i++){
			for(int j = 0; j < Main.getN(); j++){
				System.out.print(gi[i][j].getImage() + " ");
			}
			System.out.println();
		}
	}
	
	
}
