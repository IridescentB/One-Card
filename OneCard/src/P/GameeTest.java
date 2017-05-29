package P;

public class GameeTest {
	public static void main(String[] args) {
		Gamee game = new Gamee();
		EachCard[] iCard = game.iCard;
		int nMainCard = game.nMainCard;
		
		for(int i = 0; i < 52; i++) {
			iCard[i] = new EachCard(i);
			game.closedDeck.append(i);
		}
		game.prepare();
		nMainCard = game.randCard();
		game.cardInfo(iCard[nMainCard]);
		System.out.printf("Main Card : %s %s %i\n", game.info[0], game.info[1], game.infoIndex);
		
		while(true) {
			game.handPrint(game.p1Hand);
			game.handPrint(game.p2Hand);
			System.out.println("=============================");
			nMainCard = game.play(game.p1Hand, nMainCard);
			
			//need to handle p1 winning situation
			
			game.cardInfo(iCard[nMainCard]);
			System.out.printf("Main Card : %s %s %i\n", game.info[0], game.info[1], game.infoIndex);
			
			game.handPrint(game.p1Hand);
			game.handPrint(game.p2Hand);
			System.out.println("=============================");
			nMainCard = game.play(game.p2Hand, nMainCard);
			
			//need to handle p2 winning situation
			
			game.cardInfo(iCard[nMainCard]);
			System.out.printf("Main Card : %s %s %i\n", game.info[0], game.info[1], game.infoIndex);
				
		}
	}
}
