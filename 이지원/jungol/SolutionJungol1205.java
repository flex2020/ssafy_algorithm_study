import java.util.*;
import java.io.*;

public class SolutionJungol1205 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[N];
		Arrays.fill(cards, Integer.MAX_VALUE);
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int cardSize = 0;
		for (int i = 0; i < cards.length; i++) {
			int card = Integer.parseInt(st.nextToken());
			if (card != 0) {
				cards[cardSize++] = card;
			}
		}
		Arrays.sort(cards);
		
		int totalZeroCount = cards.length - cardSize;
		int zeroCount = 0;
		int sameCount = 0;
		int maxLength = cardSize > 0 ? totalZeroCount + 1 : totalZeroCount;
		for (int i = 0; i < cardSize - 1; i++) {
			zeroCount = 0;
			sameCount = 0;
			for (int j = i + 1; j < cardSize; j++) {
				if (cards[j - 1] == cards[j]) {
					sameCount++;
				} else {
					zeroCount += cards[j] - (cards[j - 1] + 1);
				}
				
				if (j == cardSize - 1 && zeroCount <= totalZeroCount) {
					maxLength = Math.max(maxLength, (j - (i + sameCount) + 1) + totalZeroCount);
				}
				
				if (zeroCount > totalZeroCount) {
					maxLength = Math.max(maxLength, (j - (i + sameCount)) + totalZeroCount);
					break;
				}
			}
		}
		
		System.out.println(maxLength);
	}

}
