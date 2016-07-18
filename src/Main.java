import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class Main  extends JFrame implements ActionListener{
	GameControl gc;
	Board bd;
	public static int x = -1;
	public static int y = -1;
	GameImage[][] gi;
	public int end;
	
	public Main(){
		
		/*setLayout(new GridLayout(N, N));
		
		this.setPreferredSize(new Dimension(N * SIZE, N * SIZE));
		setSize(N * SIZE, N * SIZE);*/
		
		gc = new GameControl();
		bd = new Board(bd);
		gi = bd.gi;
		add(bd);
		
		
	/*	setLayout(new GridLayout(8, 8));
		JButton[][] btn = new JButton[8][8];
		for(int i = 0; i< Main.getN(); i++){
			for(int j = 0; j < Main.getN(); j++){
				btn[i][j] = new JButton();
				if((i+j)%2==0)
					btn[i][j].setBackground(Color.black);
				else
					btn[i][j].setBackground(Color.white);
				add(btn[i][j]);
				
			}
		}*/
		setTitle("오델로");
		setBounds(100,100,SIZE * N,SIZE * N);
		setVisible(true);
		setResizable(false); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public void gameStatus(){
		while(gameSet != 2){
			end = gc.checkEnd(gi);
			if(end!=0)
				break;			
			//bd.printDosMap();
			if(!gc.allPlaceCheck(trun, bd.gi)){
				if(trun== 1)
					trun = 2;
				else
					trun = 1;
				gameSet++;
				continue;
			}
			if(x==-1 && y==-1)
				continue;
			if(!gc.placeCheck(trun, x, y, gi))
				continue;			
			
			gi[x][y].setImage(trun);
			
			reverseStone();
			System.out.println("플레이어"+ trun+"의 턴의 완료");
			bd.printDosMap();
			if(trun == 1)
				trun =2;
			else
				trun =1;
			gameSet =0;
		}
		System.out.println("게임 끝 플레이어 " + end + "승리");
		
	}
	
	public void reverseStone(){
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ((x + i) >= 0 && (x + i) <= 7 && (y + j) >= 0 && (y + j) <= 7) {
					if (gi[x+i][y+j].getImage() != 0 && gi[x+i][y+j].getImage() != gi[x][y].getImage()) {
						Point a = new Point(x+i, y + j);
						if(gc.takeStoneCheck(gi[x][y].getImage(),a , false, i, j, gi)){
						for (int k = i, z = j; gi[x+k][y+z].getImage() != gi[x][y].getImage(); k += i, z += j)
							gi[x+k][y+z].setImage(trun);
						}
					}
				}
			}
		}
		
		
	}
	
	
	public static void main(String[] args) {
		Main game = new Main();
		game.gameStatus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	private int trun = 1; // 현재 플레이어
	private int gameSet = 0; //게임 상태
	
	
	private static final int N = 8;
    private static final int SIZE = 64;    

	static public int getN(){return N;}
	static public int getSIZE(){return SIZE;}
}
