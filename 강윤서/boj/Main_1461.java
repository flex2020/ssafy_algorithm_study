package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_1461 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer> posi = new ArrayList<>();
		List<Integer> nega = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int value = Integer.parseInt(st.nextToken());
			if (value < 0) {
				nega.add((-1)*value);
			} else {
				posi.add(value);
			}
		}
		int answer = 0;
		posi.sort((p1, p2) -> p2 - p1);
		nega.sort((n1, n2) -> n2 - n1);
		for (int i=0; i<posi.size(); i+=M) {
			answer += posi.get(i)*2;
		}
		for (int i=0; i<nega.size(); i+=M) {
			answer += nega.get(i)*2;
		}
		int maxValue = -1;
		if (!posi.isEmpty()) maxValue = Math.max(maxValue, posi.get(0));
		if (!nega.isEmpty()) maxValue = Math.max(maxValue, nega.get(0));
		System.out.println(answer - maxValue);

	}

}
