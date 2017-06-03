package P;

public class GameeTest {
	public static void main(String[] args) {
		Gamee game = new Gamee();
		EachCard[] iCard = game.iCard;
		int nMainCard = game.nMainCard;
		int nPrevMain = 0;
		boolean hasChanged = false;
		int nPlayers = game.nPlayers;
		int j = 0; // 1st Player = 0
		int jControl = 1; // 1 : CW , -1 : CCW
		
		for(int i = 0; i < 52; i++) {
			iCard[i] = new EachCard(i);
			game.closedDeck.append(i);
		}
		game.prepare();
		nMainCard = game.randCard();
		game.closedDeck.remove(nMainCard);
		game.cardInfo(iCard[nMainCard]);
		System.out.printf("Main Card : %s %s (%d)\n", game.info[0], game.info[1], game.infoIndex);
		
		while(true) {
				for(int i = 0; i < nPlayers ; i++) { // printing hands
					System.out.printf("Player %d Hand: ", i+1);
					game.handPrint(game.pHands[i]);
				}
				System.out.println("=============================");
				nPrevMain = nMainCard;
				nMainCard = game.play(game.pHands[j], nMainCard);
				
				hasChanged = (nMainCard != nPrevMain);
				
				//winning situation
				if(nMainCard == 100) {
					for(int i = 0; i < nPlayers ; i++) {	
						System.out.printf("Player %d Hand: ", i+1);
						game.handPrint(game.pHands[i]);
					}
					System.out.println("=============================");
					System.out.printf(" Winner : Player %d !\n", j+1);
					return;
				}
			game.cardInfo(iCard[nMainCard]);
			System.out.printf("Player %d turn\n", j+1);
			System.out.println();
			System.out.printf("Main Card : %s %s (%d)\n", game.info[0], game.info[1], game.infoIndex);
			if(hasChanged) System.out.printf("%d\n", j+1);
			
			if(nMainCard%13 == 10 && hasChanged) j = (j + jControl*2 + nPlayers)%nPlayers; 
				// special case : J cards act only once
				//(act + nPlayers)%nPlayers : to prevent negative modulo result in case of jControl == -1
			else if(nMainCard%13 == 11 && hasChanged) jControl = -jControl; // special case : Q cards act only once
			else if(nMainCard%13 == 12 && hasChanged) continue; // special case : K cards act only once
			else j = (j + jControl + nPlayers)%nPlayers;
			
			if(game.closedDeck.nDeckNum == 1) {
				return;
			}
		}
	}
}

/* jControl = -1 or 1 ( 1 : CW, -1 : CCW)
 * if J : j = j + jControl *2
 * if Q : jControl = -jControl
 * if K : j = j (not change)
 * 
 * else : j = j + jControl
 * 
 * JYet, QYet, KYet : false (default)
 * when nMainCard == J or Q or K :
 */