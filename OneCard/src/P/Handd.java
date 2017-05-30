package P;

public class Handd {
	EachCard[] handd = new EachCard[52];
	int nHandNum;
	
	Handd() {
		for(int i = 0; i< 26; i++) {
			handd[i] = null;
		}
		nHandNum = 0;
	}
	
	void remove(int x) {
		for(int i = 0; i < 52; i++) {
			if(handd[i] != null && handd[i].nIndex == x) {
				handd[i] = null;
				nHandNum -= 1;
				break;
			}
		}
	}
	void append(int x) {
		for(int i = 0 ; i < 52; i++) {
			if(handd[i] == null) {
				handd[i] = new EachCard(x);
				nHandNum += 1;
				break;
			}
		}
	}
}
