import java.awt.Graphics;
import java.awt.Point;

public class GameControl {
	private int gap = 0;
	private int player1 = 0;
	private int player2 = 0;
	
	public int checkEnd(GameImage[][] gi) {
		gap = 0;
		player1 = 0;
		player2 = 0;

		for (int i = 0; i < Main.getN(); i++) {
			for (int j = 0; j < Main.getN(); j++) {
				if (gi[i][j].getImage() == 0)
					gap++;
				if (gi[i][j].getImage() == 1)
					player1++;
				if (gi[i][j].getImage() == 2)
					player2++;
			}
		}
		if (gap == 0) { //둘곳이 없을때
			if (player1 > player2) // P1이 많으면 P1의 승리
				return 1;
			else if (player1 < player2) //p2가 많으면 P2의 승리
				return 2;
			else
				return 3; // 같으면 비김
		}
		if (gap != 0 && player1 == 0) //둘곳은 있으나 플레이어 1의 돌이 하나도 없으면 플레이어1의 패배
			return 2;
		else if (gap != 0 && player2 == 0) // 둘곳은 있으나 플레이어 2의 돌이 하나도 없으면 플레이어2의 패배
			return 1;

		return 0; //모든 경우에 해당이 안될경우 게임 진행
	} 
	
	public boolean placeCheck(int player, int x, int y, GameImage[][] gi){
		for(int i = -1; i <=1; i++){
			for(int j = -1; j <=1; j++){
				if(((x+i >= 0 && x+i <= 7) && (y+j >= 0 && y+j <= 7))){
					if(gi[i+x][j+y].getImage() !=0 && gi[x][y].getImage()== 0){
						Point a = new Point(x+i,y+j);
						if(takeStoneCheck(player, a, false, i,j, gi)){
							return true;
						}
							
					}
					
				}
			}
		}
		return false;
	}
	
	//비어있는 자리중에 플레이어가 둘 수 있는 곳이 있는지 체크
	public boolean allPlaceCheck(int player, GameImage[][] gi){
		boolean check = false;
		for(int i = 0; i < Main.getN(); i++){
			for(int j = 0; j< Main.getN(); j++){
				if(gi[i][j].getImage() == 0 && placeCheck(player, i,j, gi)){
					check = true;
				}
			}
		}
		return check;
	}
	
	public boolean takeStoneCheck(int player, Point a, boolean check, int x, int y, GameImage[][] gi) {
		////////검사할 위치가 맵의 밖이면 검사하지 않음
		if ((a.x) < 0 || (a.x) > 7 || (a.y) < 0 || (a.y) > 7)
			return false;
		
		//검사 위치가 빈칸이거나 현재 플레이어의 색과 같으면 검사하지 않음	
		if (gi[a.x][a.y].getImage()  == 0)
			return false;
		else if((gi[a.x][a.y].getImage()  != 0) && (gi[a.x][a.y].getImage() != player)){
			a.x+=x;
			a.y+=y;
			if(!takeStoneCheck(player, a, true, x, y, gi))
				return false;
		}
		else if(gi[a.x][a.y].getImage()  == player){
			if(check==false)
				return false;			
		}
		
		return true;
	}
	
	
}
