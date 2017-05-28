package P;
import java.util.Random;

public class Gamee {
	int nMainCard = 0;
	String[] info = new String[2];
	int infoIndex;
	Deckk closedDeck = new Deckk();
	Deckk openDeck = new Deckk();
	int nGet;
	Handd p1Hand = new Handd();
	Handd p2Hand = new Handd();	
	
	int randCard() {
		Random random = new Random();
		return random.nextInt(52);
	}
	
	void cardInfo(EachCard card) {
		info[0] = card.sSuite;
		info[1] = card.sNum;
		infoIndex = card.nIndex;
	}
	
	int getCardFromClosed() {
		//need exception for 'No More Card in Closed Deck'
		while(true) {
			nGet = this.randCard(); 
			for(int i = 0; i < closedDeck.nDeckNum; i++) {
				if(nGet == (closedDeck.deckk)[i].nIndex) {
					break;
				}
			}
		}
		return nGet;
	}
	
	void prepare() {
		for(int i = 0; i < 7; i++){ // 2 Players, 7 Cards each
			nGet = getCardFromClosed();
			closedDeck.remove(nGet);
			p1Hand.append(nGet);
			nGet = getCardFromClosed();
			closedDeck.remove(nGet);
			p2Hand.append(nGet);
		}
	}
}
