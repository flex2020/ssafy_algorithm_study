package 강윤서.jungol;
import java.util.*;
public class Main_1828 {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] temperature = new int[N][2];
		int answer = 1;
		for (int i=0; i<N; i++) {
			temperature[i][0] = sc.nextInt();
			temperature[i][1] = sc.nextInt();
		}
		Arrays.sort(temperature, new Comparator<int[]>() {
			@Override
			public int compare(int[] t1, int[] t2) {
				// 최고 온도 기준으로 정렬
				return Integer.compare(t1[1], t2[1]);
			}
		});
		int end = temperature[0][1];
		for (int i=1; i<N; i++) {
			if (temperature[i][0] <= end) {
				end = Math.min(end, temperature[i][1]);
			} else {
				answer += 1;
				end = temperature[i][1];
			}
		}
		System.out.println(answer);
	}
}