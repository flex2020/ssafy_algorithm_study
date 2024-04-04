import java.util.*;
import java.io.*;

public class SolutionBaekJoon1202 {

	static class Jewel implements Comparable<Jewel> {
		int weight;
		int value;

		Jewel(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
		@Override
		public int compareTo(Jewel o) {
			int result = Integer.compare(weight, o.weight);
			
			return result == 0 ? Integer.compare(o.value, value) : result;
		}

	}

	static int K;
	static PriorityQueue<Jewel> jewels;
	static PriorityQueue<Integer> bags;
	static PriorityQueue<Integer> jewelValues;
	static int maxBagWeight;
	static long result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 보석 수
		K = Integer.parseInt(st.nextToken()); // 가방 수

		// 보석 정보 입력받기
		jewels = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			jewels.add(new Jewel(weight, value));
		}

		// 가방 최대 무게 입력받기
		bags = new PriorityQueue<>();
		for (int i = 0; i < K; i++) {
			int weight = Integer.parseInt(br.readLine());
			bags.add(weight);
		}

		result = 0;
		calculateResult();

		System.out.println(result);
	}

	public static void calculateResult() {
		jewelValues = new PriorityQueue<>((j1, j2) -> Integer.compare(j2, j1));
		int bag = bags.poll();
		Jewel jewel = jewels.poll();
		aa: while (K > 0) {
			if (jewel.weight <= bag) {
				jewelValues.add(jewel.value);
				
				if (jewels.isEmpty()) {
					break aa;
				}

				jewel = jewels.poll();
			} else {
				// 사용한 가방 꺼내기
				while (jewel.weight > bag) {
					if (bags.isEmpty()) {
						break aa;
					}
					
					bag = bags.poll();
					
					// 사용한 가방만큼 보석 가치 계산하기
					if (!jewelValues.isEmpty()) {
						result += jewelValues.poll();
					}
					K--;
				}
			}
		}
		
		// 남은 훔친 보석 가치 계산하기
		while (!jewelValues.isEmpty() && K > 0) {
			result += jewelValues.poll();
			K--;
		}
	}

}
