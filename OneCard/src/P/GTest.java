package P;

public class GTest {
	public static void main(String[] args) {
		Gamee game = new Gamee();
		
		for(int i = 0; i<52; i++) {
			game.cardInfo(game.iCard[i]);
			System.out.printf("%s %s %d", game.info[0], game.info[1], game.infoIndex);
			System.out.println();
		}
	}
}
