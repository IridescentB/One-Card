import java.util.Scanner;
import java.util.Random;

public class Card {
	private int cNum;
	private char cKQJ = 0;
	private char cPat; //pattern : SDHC : Spade Diamond Heart Clover
	
	Card(){
		Random random = new Random();
		cNum = random.nextInt(14);
		while(cNum != 0) {
			cNum = random.nextInt(14);
		}
		switch(cNum) {
			case(11): cKQJ = 'J';
			case(12): cKQJ = 'Q';
			case(13): cKQJ = 'K';
		}
	}
	
	public int getNum() {
		return cNum;
	}
	public char getPat() {
		return cPat;
	}
};
