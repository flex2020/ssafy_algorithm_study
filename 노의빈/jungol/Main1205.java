import java.util.*;
import java.io.*;

public class Main {
	private static Integer[] cards;
	private static int joker, answer;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cards = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
			if (cards[i] == 0) {
				joker += 1; // 조커 개수 세기
			}
		}
		Arrays.sort(cards, (a, b) -> a - b);
		for (int i=joker; i<N; i++) {
			int count = 1;
			int prev = cards[i];
			int tempJoker = joker;
			// i~카드 연속이 불가능할때까지의 수를 셈
			for (int j=i+1; j<N; j++) {
				if (cards[j] == prev) continue;
				// 이전 카드와 연속되는 경우
				if (cards[j] - prev == 1) {
					count += 1;
					prev = cards[j];
				}
				// 연속은 아니지만 조커가 있는 경우
				else if (tempJoker > 0) {
					// 조커의 개수로 커버가 가능한 경우
					int cardAmount = cards[j] - prev - 1; // 이전 카드와 현재 카드 사이에 몇개의 카드가 있는지
					if (cardAmount <= tempJoker) {
						count += cardAmount + 1; // 사이 카드 수 + 현재 카드
						prev = cards[j];
						tempJoker -= cardAmount;
					}
					// 조커의 개수로 커버가 불가능한 경우
					else {
						break;
					}
				}
			}
			count += tempJoker;
			answer = Math.max(answer, count);
		}
		answer = Math.max(answer, joker);
		System.out.println(answer);
	}
}
