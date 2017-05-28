package P;

public class Handd {
	EachCard[] handd = new EachCard[26];
	int nHandNum;
	
	Handd() {
		for(int i = 0; i< 26; i++) {
			handd[i] = null;
		}
		nHandNum = 0;
	}
	
	void remove(int x) {
		for(int i = 0; i < 52; i++) {
			if(handd[i].nIndex == x) {
				handd[i] = null;
			}
		}
	}
	void append(int x) {
		while(true) {
			for(int i = 0 ; i < 52; i++) {
				if(handd[i] == null) {
					handd[i] = new EachCard(x);
				}
			}
		}
	}
}
