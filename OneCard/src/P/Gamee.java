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
	int nPlayers = 4;
	Handd[] pHands = new Handd[nPlayers];
	int nForcedSuite;
	boolean hasDrawn;
	
	Gamee(){
		for(int i = 0; i<52; i++) {
			iCard[i] = new EachCard(i);
		}
		for(int j = 0; j < nPlayers ; j++) {
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
		// 'No More Card in Closed Deck' > make Open Deck to Closed Deck 
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
		for(int j = 0; j < nPlayers ; j++){ // 2 Players, 7 Cards each
			for(int i = 0; i < 7; i++) {	
				nGet = getCardFromClosed();
				closedDeck.remove(nGet);
				pHands[j].append(nGet);
			}
		}
	}
	
	boolean match(int nMe, int nN, int nS) {
		boolean a,b;
		if(nN == 1 && hasDrawn == false) { // special case : card 2
			if(nS == nMe/13 && nMe%13 == 0) { // same suite A
				return true;
			} else if (nN == 1 && hasDrawn == false){
				a = (nMe%13 == nN); // only 2
				b = false; // not the same suite cards
			} else {
				a = (nMe%13 == nN); // same num
				b = (nMe/13 == nS); // same suite
			}
		} else if(nN == 0) {
			if(hasDrawn == false) { // special case : card A
				a = (nMe%13 == nN); // only A
				b = false;
			} else {
				a = (nMe%13 == nN); // same num
				b = (nMe/13 == nS); // same suite
			}
		} else {
			a = (nMe%13 == nN); // same num
			b = (nMe/13 == nS); // same suite
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
		System.out.println();
	}
	
	int play(Handd h, int nMain) { // h : each player's hand , nMain : nMaincard
		System.out.printf("nForcedSuite: %d\n", nForcedSuite);
		if(h.nHandNum == 0) {
			System.out.println("Winner!");
			return 100;
		}
		int[] LMatch = {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99} ;
		int n = 0;
		
		int nN = nMain%13;
		int nS = nMain/13;
		if(nMain%13 == 6) { // special case : card 7
			nS = nForcedSuite; // previous player(card 7,forced suite), current player has to follow this, not main card's suite
		}
		/*
		if(nMain%13 == 1) { // special case : card 2
			nN = nForcedNum; // previous player (card 2), current player has to throw 2 or A (same suite)
		}
		*/
		for(EachCard ec : h.handd) {
			if(ec != null) {	
				if(match(ec.nIndex, nN, nS)) {
					LMatch[n] = ec.nIndex;
					n++;
				}
			}
		}
		if(n == 0) { //no match with main card -> draw new card from closed deck
			nGet = getCardFromClosed();
			closedDeck.remove(nGet);
			h.append(nGet);
			if(hasDrawn == false) { // special case : card 2 or A : draw one more card 
				nGet = getCardFromClosed();
				closedDeck.remove(nGet);
				h.append(nGet);
			}
			hasDrawn = true;
			return nMain;
		} else { // choose 1 from matching cards
			Random random = new Random();
			nThrow = LMatch[random.nextInt(n)];
			h.remove(nThrow);
			openDeck.append(nThrow);
			if(h.nHandNum == 0) {
				return 100;
			}
			if(nThrow%13 == 6) { // special case : card 7
				while(true) {
					int x = random.nextInt(52);
					if(h.handd[x] != null) {
						nForcedSuite = (h.handd[x].nIndex)/13; // randomly pick suite from my hand
						System.out.printf("Player forced suite to %d\n", nForcedSuite);
						break;
					}
				}
			}
			if(nThrow%13 == 1 || nThrow%13 == 0) { // special case : card 2 or A
				hasDrawn = false;
			} else {
				hasDrawn = true;
			}
			return nThrow;
		}
	}
}
