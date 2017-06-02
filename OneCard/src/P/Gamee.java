package P;
import java.util.Random;

public class Gamee {
	int nMainCard = 0;
	EachCard[] iCard = new EachCard[52]; // to find card info with index number
	String[] info = new String[2]; // to return card info (suite, number)
	int infoIndex; // to return card info (index)
	Deckk closedDeck = new Deckk();
	Deckk openDeck = new Deckk();
	int nGet; // temp, used in play()
	int nThrow; // temp, used in play()
	/*
	Handd p1Hand = new Handd();
	Handd p2Hand = new Handd();	
	*/
	Handd[] pHands = new Handd[2];
	
	Gamee(){
		for(int i = 0; i<52; i++) {
			iCard[i] = new EachCard(i);
		}
		for(int j = 0; j < 2; j++) {
			pHands[j] = new Handd();
		}
	}
	
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
		if(closedDeck.nDeckNum == 0) {
			closedDeck = openDeck;
			openDeck = new Deckk();
		}
		while(true) {
			nGet = this.randCard(); 
			for(int i = 0; i < 52; i++) {
				if((closedDeck.deckk)[i] != null){
					if(nGet == (closedDeck.deckk)[i].nIndex) { // if random number == index of card in closed deck, then return
						return nGet;
					}
				}
			}
		}
	}
	
	void prepare() {
		for(int j = 0; j < 2; j++){ // 2 Players, 7 Cards each
			for(int i = 0; i < 7; i++) {	
				nGet = getCardFromClosed();
				closedDeck.remove(nGet);
				pHands[j].append(nGet);
			}
		}
	}
	
	boolean match(int nMe, int nAnother) {
		boolean a,b;
		if(iCard[nMe].sSuite.equals(iCard[nAnother].sSuite)) {
			a = true;
		} else {
			a = false;
		}
		if(iCard[nMe].sNum.equals(iCard[nAnother].sNum)) {
			b = true;
		} else {
			b = false;
		}
		return a || b;
	}
	
	
	void handPrint(Handd h) { // h : p1Hand or p2Hand
		for(int i = 0; i < 52; i++) {
			if(h.handd[i] != null) {
				cardInfo(h.handd[i]);
				System.out.printf(" %s %s (%d) / ", info[0], info[1], infoIndex); //suite number index
			}
		}
	}
	
	int play(Handd h, int nMain) { // h : p1Hand or p2Hand, nMain : nMaincard
		//exception for winning situation (all cards in hand == null)
		//for all i, if(h.handd[i] == null)
		if(h.nHandNum == 0) {
			System.out.println("Winner!");
			return 100;
		}
		int[] LMatch = {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99} ;
		int n = 0;
		for(EachCard ec : h.handd) {
			if(ec != null) {
				if(match(ec.nIndex, nMain)) {
					LMatch[n] = ec.nIndex;
					n++;
				}
			}
		}
		if(n == 0) { //no match with main card -> draw new card from closed deck
			nGet = getCardFromClosed();
			closedDeck.remove(nGet);
			h.append(nGet);
			return nMain;
		} else { // choose 1 from matching cards
			
			Random random = new Random();
			nThrow = LMatch[random.nextInt(n)];
			h.remove(nThrow);
			openDeck.append(nThrow);
			//
			if(h.nHandNum == 0) {
				return 100;
			}
			//
			return nThrow;
		}
		
	}
}
