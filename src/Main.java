import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner enterValue = new Scanner(System.in);
		int x = enterValue.nextInt();
		int y = enterValue.nextInt();
		
		FillTheFieldGame fillTheFieldGame = new FillTheFieldGame();
		fillTheFieldGame.chooseField(x,y);
		
	}

}
