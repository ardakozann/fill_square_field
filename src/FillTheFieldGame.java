import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FillTheFieldGame {
	
	final int [][]backgroundArray = {{3,3,4,5,5,5,5,4,3,3},{3,3,4,5,5,5,5,4,3,3},{4,4,6,7,7,7,7,6,4,4},
			{5,5,7,8,8,8,8,7,5,5},{5,5,7,8,8,8,8,7,5,5},{5,5,7,8,8,8,8,7,5,5},{5,5,7,8,8,8,8,7,5,5},
			{4,4,6,7,7,7,7,6,4,4},{3,3,4,5,5,5,5,4,3,3},{3,3,4,5,5,5,5,4,3,3}};
	
	//oyun tablosunu sıfırlama
	public int [][] resetSquareField(int [][]dizi) {
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				dizi[i][j]=0;
			}
		}
		return dizi;
	}
	
	//Oyunda başlangıç seçme
	public void chooseField(int x, int y) {
		
		int [][] game = new int[10][10];
		int [][] template = {{3,3,4,5,5,5,5,4,3,3},{3,3,4,5,5,5,5,4,3,3},{4,4,6,7,7,7,7,6,4,4},
				{5,5,7,8,8,8,8,7,5,5},{5,5,7,8,8,8,8,7,5,5},{5,5,7,8,8,8,8,7,5,5},{5,5,7,8,8,8,8,7,5,5},
				{4,4,6,7,7,7,7,6,4,4},{3,3,4,5,5,5,5,4,3,3},{3,3,4,5,5,5,5,4,3,3}};
		game = resetSquareField(game);
		fillSquare(game, template, x, y);	
	}
	
	//100'ü bulmaya çalışma
	public void fillSquare (int [][]game, int[][]template, int x, int y) {
		int number=1;
		//Sıralama için TreeMap kullanıldı.
		TreeMap<Integer,String> sortingWays = new TreeMap<Integer,String>();
		
		while(number <= 100) {
			//System.out.println(number);
			game[y][x]=number;
			template[y][x]=-1;
			decreasePossibilities(template,x,y);
			
			sortingWays.clear();
			
			sortingWays.put(right(template,x,y), "right");
			sortingWays.put(left(template,x,y), "left");
			sortingWays.put(top(template,x,y), "top");
			sortingWays.put(bottom(template,x,y), "bottom");
			sortingWays.put(topRight(template,x,y), "topRight");
			sortingWays.put(topLeft(template,x,y), "topLeft");
			sortingWays.put(bottomRight(template,x,y), "bottomRight");
			sortingWays.put(bottomLeft(template,x,y), "bottomLeft");
			
			if(sortingWays.firstEntry().getValue().equals("right") && sortingWays.firstKey() != 999) {
				x = x+3;
			}
			else if(sortingWays.firstEntry().getValue().equals("left") && sortingWays.firstKey() != 999) {
				x = x-3;
			}
			else if(sortingWays.firstEntry().getValue().equals("top") && sortingWays.firstKey() != 999) {
				y = y-3;
			}
			else if(sortingWays.firstEntry().getValue().equals("bottom") && sortingWays.firstKey() != 999) {
				y = y+3;
			}
			else if(sortingWays.firstEntry().getValue().equals("topRight") && sortingWays.firstKey() != 999) {
				x = x+2;
				y = y-2;
			}
			else if(sortingWays.firstEntry().getValue().equals("topLeft") && sortingWays.firstKey() != 999) {
				x = x-2;
				y = y-2;
			}
			else if(sortingWays.firstEntry().getValue().equals("bottomRight") && sortingWays.firstKey() != 999) {
				x = x+2;
				y = y+2;
			}
			else if(sortingWays.firstEntry().getValue().equals("bottomLeft") && sortingWays.firstKey() != 999) {
				x = x-2;
				y = y+2;
			}
			else {
				showSquare(game,number);
				break;
			}
			number++;
		}
	}
	
	//Arka plandaki arrrayden gidebileceği yerlerin ihtimali
	//Dizide önceden herhangi bir değer atanmışsa, background dizisinde orası -1 olur.
	public int right(int[][] template, int x, int y) {
		if(x+3 <= 9 && template[y][x+3] != -1) {return template[y][x+3];}
		else {return 999;}
	}
	public int left(int[][] template, int x, int y) {
		if(x-3 >= 0 && template[y][x-3] != -1) {return template[y][x-3];}
		else {return 999;}
	}
	public int top(int[][] template, int x, int y) {
		if(y-3 >= 0 && template[y-3][x] != -1) {return template[y-3][x];}
		else {return 999;}
	}
	public int bottom(int[][] template, int x, int y) {
		if(y+3 <= 9 && template[y+3][x] != -1) {return template[y+3][x];}
		else {return 999;}
	}
	public int topRight(int[][] template, int x, int y) {
		if((x+2 <= 9 && y-2 >= 0) && template[y-2][x+2] != -1) {return template[y-2][x+2];}
		else {return 999;}
	}
	public int topLeft(int[][] template, int x, int y) {
		if((x-2 >= 0 && y-2 >= 0) && template[y-2][x-2] != -1) {return template[y-2][x-2];}
		else {return 999;}
	}
	public int bottomRight(int[][] template, int x, int y) {
		if((x+2 <= 9 && y+2 <= 9) && template[y+2][x+2] != -1) {return template[y+2][x+2];}
		else {return 999;}
	}
	public int bottomLeft(int[][] template, int x, int y) {
		if((x-2 >= 0 && y+2 <= 9) && template[y+2][x-2] != -1) {return template[y+2][x-2];}
		else {return 999;}
	}
	
	
	public void decreasePossibilities(int[][] template,int x,int y) {
		if(x+3 <= 9 && template[y][x+3] != -1) {template[y][x+3] -=1;}
		if(x-3 >= 0 && template[y][x-3] != -1) {template[y][x-3] -=1;}
		if(y+3 <= 9 && template[y+3][x] != -1) {template[y+3][x] -=1;}
		if(y-3 >= 0 && template[y-3][x] != -1) {template[y-3][x] -=1;}
		if((x+2 <= 9 && y+2 <= 9) && template[y+2][x+2] != -1) {template[y+2][x+2] -=1;}
		if((x+2 <= 9 && y-2 >= 0) && template[y-2][x+2] != -1) {template[y-2][x+2] -=1;}
		if((x-2 >= 0 && y+2 <= 9) && template[y+2][x-2] != -1) {template[y+2][x-2] -=1;}
		if((x-2 >= 0 && y-2 >= 0) && template[y-2][x-2] != -1) {template[y-2][x-2] -=1;}
	}
	
	//Oyunu gösterme
	public void showSquare(int [][] game, int number) {
		System.out.println(number);
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(game[i][j] < 10) {
					System.out.print(game[i][j] + "   ");
				}
				else if(game[i][j] == 100){
					System.out.print(game[i][j] + " ");
				}
				else {
				System.out.print(game[i][j] + "  ");
				}
			}
			System.out.println();
		}
		System.out.println("\n\n");
		
	}
}