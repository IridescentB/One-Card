package P;

public class EachCard {
 
	int nSuite; //0: Spade 1: Diamond 2: Heart 3: Clover
	int nNum; // 0 ~ 12 : A ~ K
	int nIndex; // Card index = 0 ~ 51 (0~12: Spade, 13~25: Diamond, 26~38: Heart, 39~51: Clover)
	String sSuite, sNum;
	String[] suites = {"Spade", "Diamond", "Heart", "Clover"};
	String[] nums = {"A","2","3","4","5","6","7","8","9","T","J","Q","K"};
	
	EachCard(int x){
		nSuite = x/13;
		nNum = x%13;
		sSuite = suites[nSuite];
		sNum = nums[nNum];
		nIndex = x;	
	}
}
