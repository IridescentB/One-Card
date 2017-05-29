package P;

public class Deckk {
	EachCard[] deckk = new EachCard[52];
	int nDeckNum;
	
	Deckk() {
		for(int i = 0; i< 52; i++) {
			deckk[i] = null;
		}
		nDeckNum = 0;
	}
	
	void remove(int x) {
		for(int i = 0; i < 52; i++) {
			if(deckk[i].nIndex == x) {
				deckk[i] = null;
				nDeckNum -= 1;
			}
		}
	}
	void append(int x) {
		while(true) {
			for(int i = 0 ; i < 52; i++) {
				if(deckk[i] == null) {
					deckk[i] = new EachCard(x);
					nDeckNum += 1;
				}
			}
		}
	}
}
