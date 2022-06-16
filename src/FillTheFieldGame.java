
public class FillTheFieldGame {
	
	
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
	public void chooseField() {
		int [][] game = new int[10][10];
		//x ve y ekseni
		for(int y=0; y<=9; y++) {
			for(int x=0; x<=9; x++) {
				game = resetSquareField(game);
				fillSquare(game, x, y);				
			}
		}
	}
	//100'ü bulmaya çalışma
	public void fillSquare (int [][]game, int x, int y) {
		int number = 1;
		game[y][x] = number;
		number++;
		while(number <=100) {
			//sağa gidiş
			if(x+3 <= 9 && game[y][x+3] == 0 ) {
				x = x+3;
			}
			//sağ alt çapraz gidiş
			else if((x+2 <= 9 && y+2 <=9) && game[y+2][x+2] == 0) {
				x = x+2;
				y = y+2;
			}
			//aşağı gidiş
			else if(y+3 <= 9 && game[y+3][x] == 0) {
				y = y+3;
			}
			// sol aşağı gidiş
			else if((x-2 >= 0 && y+2 <= 9) && game[y+2][x-2] == 0) {
				x = x-2;
				y = y+2;
			}
			//sola gidiş
			else if(x-3 >= 0 && game[y][x-3] == 0) {
				x = x-3;
			}
			//sol üste gidiş
			else if((x-2 >= 0 && y-2 >= 0) && game[y-2][x-2] == 0) {
				x = x-2;
				y = y-2;
			}
			//üste gidiş
			else if(y-3 >= 0 && game[y-3][x] == 0) {
				y = y-3;
			}
			//üst sağa gidiş
			else if((x+2 <= 9 && y-2 >= 0) && game[y-2][x+2] == 0) {
				x = x+2;
				y = y-2;
				
			}
			//bitince
			else {
//				if(number == 100) {
//					showSquare(game);
//				}
				showSquare(game);
				return;
			}
			game[y][x]=number;
			number++;
		}		
	}
	
	//Oyunu gösterme
	public void showSquare(int [][] game) {
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