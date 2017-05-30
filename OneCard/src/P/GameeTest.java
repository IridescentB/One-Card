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
		game.closedDeck.remove(nMainCard);
		game.cardInfo(iCard[nMainCard]);
		System.out.printf("Main Card : %s %s %d\n", game.info[0], game.info[1], game.infoIndex);
		
		while(true) {
			System.out.printf("Player 1 Hand: ");
			game.handPrint(game.p1Hand);
			System.out.println();
			System.out.printf("Player 2 Hand: ");
			game.handPrint(game.p2Hand);
			System.out.println();
			System.out.println("=============================");
			nMainCard = game.play(game.p1Hand, nMainCard);
			
			//need to handle p1 winning situation
			if(nMainCard == 100) {
				System.out.println(" Player 1 ");
				return;
			}
			
			game.cardInfo(iCard[nMainCard]);
			System.out.printf("Main Card : %s %s %d\n", game.info[0], game.info[1], game.infoIndex);
			
			System.out.printf("Player 1 Hand: ");
			game.handPrint(game.p1Hand);
			System.out.println();
			System.out.printf("Player 2 Hand: ");
			game.handPrint(game.p2Hand);
			System.out.println();
			System.out.println("=============================");
			nMainCard = game.play(game.p2Hand, nMainCard);
			
			//need to handle p2 winning situation
			if(nMainCard == 100) {
				System.out.println(" Player 2 ");
				return;
			}
			game.cardInfo(iCard[nMainCard]);
			System.out.printf("Main Card : %s %s %d\n", game.info[0], game.info[1], game.infoIndex);
				
		}
	}
}
