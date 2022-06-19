import java.util.TreeMap;

public class FillTheFieldGame {
	
	int [][]backgroundArray = {{3,3,4,5,5,5,5,4,3,3},{3,3,4,5,5,5,5,4,3,3},{4,4,6,7,7,7,7,6,4,4},
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
		game = resetSquareField(game);
		fillSquare(game, x, y);	
		//x ve y ekseni
//		for(int y=0; y<=9; y++) {
//			for(int x=0; x<=9; x++) {
//				game = resetSquareField(game);
//				fillSquare(game, x, y);				
//			}
//		}
		
//		for(int i=0; i<10; i++) {
//			for(int j=0; j<10; j++) {
//				if(backgroundArray[i][j] < 10) {
//					System.out.print(backgroundArray[i][j] + "   ");
//				}
//				else if(backgroundArray[i][j] == 100){
//					System.out.print(backgroundArray[i][j] + " ");
//				}
//				else {
//				System.out.print(backgroundArray[i][j] + "  ");
//				}
//			}
//			System.out.println();
//		}
	}
	//100'ü bulmaya çalışma
	public void fillSquare (int [][]game, int x, int y) {
		int number=1;
		//Sıralama için TreeMap kullanıldı.
		TreeMap<Integer,String> sortingWays = new TreeMap<Integer,String>();
		
		while(number <= 100) {
			//System.out.println(number);
			game[y][x]=number;
			backgroundArray[y][x]=-1;
			decreasePossibilities(x,y);
			
			
			sortingWays.clear();
			
			sortingWays.put(right(x,y), "right");
			sortingWays.put(left(x,y), "left");
			sortingWays.put(top(x,y), "top");
			sortingWays.put(bottom(x,y), "bottom");
			sortingWays.put(topRight(x,y), "topRight");
			sortingWays.put(topLeft(x,y), "topLeft");
			sortingWays.put(bottomRight(x,y), "bottomRight");
			sortingWays.put(bottomLeft(x,y), "bottomLeft");
			
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
	public int right(int x, int y) {
		if(x+3 <= 9 && backgroundArray[y][x+3] != -1) {return backgroundArray[y][x+3];}
		else {return 999;}
	}
	public int left(int x, int y) {
		if(x-3 >= 0 && backgroundArray[y][x-3] != -1) {return backgroundArray[y][x-3];}
		else {return 999;}
	}
	public int top(int x, int y) {
		if(y-3 >= 0 && backgroundArray[y-3][x] != -1) {return backgroundArray[y-3][x];}
		else {return 999;}
	}
	public int bottom(int x, int y) {
		if(y+3 <= 9 && backgroundArray[y+3][x] != -1) {return backgroundArray[y+3][x];}
		else {return 999;}
	}
	public int topRight(int x, int y) {
		if((x+2 <= 9 && y-2 >= 0) && backgroundArray[y-2][x+2] != -1) {return backgroundArray[y-2][x+2];}
		else {return 999;}
	}
	public int topLeft(int x, int y) {
		if((x-2 >= 0 && y-2 >= 0) && backgroundArray[y-2][x-2] != -1) {return backgroundArray[y-2][x-2];}
		else {return 999;}
	}
	public int bottomRight(int x, int y) {
		if((x+2 <= 9 && y+2 <= 9) && backgroundArray[y+2][x+2] != -1) {return backgroundArray[y+2][x+2];}
		else {return 999;}
	}
	public int bottomLeft(int x, int y) {
		if((x-2 >= 0 && y+2 <= 9) && backgroundArray[y+2][x-2] != -1) {return backgroundArray[y+2][x-2];}
		else {return 999;}
	}
	
	
	public void decreasePossibilities(int x,int y) {
		if(x+3 <= 9 && backgroundArray[y][x+3] != -1) {backgroundArray[y][x+3] -=1;}
		if(x-3 >= 0 && backgroundArray[y][x-3] != -1) {backgroundArray[y][x-3] -=1;}
		if(y+3 <= 9 && backgroundArray[y+3][x] != -1) {backgroundArray[y+3][x] -=1;}
		if(y-3 >= 0 && backgroundArray[y-3][x] != -1) {backgroundArray[y-3][x] -=1;}
		if((x+2 <= 9 && y+2 <= 9) && backgroundArray[y+2][x+2] != -1) {backgroundArray[y+2][x+2] -=1;}
		if((x+2 <= 9 && y-2 >= 0) && backgroundArray[y-2][x+2] != -1) {backgroundArray[y-2][x+2] -=1;}
		if((x-2 >= 0 && y+2 <= 9) && backgroundArray[y+2][x-2] != -1) {backgroundArray[y+2][x-2] -=1;}
		if((x-2 >= 0 && y-2 >= 0) && backgroundArray[y-2][x-2] != -1) {backgroundArray[y-2][x-2] -=1;}
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