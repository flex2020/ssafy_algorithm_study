import java.io.*;
import java.util.Arrays;

public class Main16435 {
	private static int N, L;
	private static int[] heights;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		L = Integer.parseInt(input[1]);
		heights = new int[N];
		input = br.readLine().split(" ");
		for (int i=0; i<N; i++) {
			heights[i] = Integer.parseInt(input[i]);
		}
		
		// heights 정렬
		Arrays.sort(heights);
		for (int i=0; i<N; i++) {
			// 먹을 수 있는 조건 => 높이가 스네이크버드의 길이보다 작거나 같은 경우
			if (heights[i] <= L) {
				L += 1; // 스네이크 버드의 길이 증가
			} else {
				// 이 부분에 들어온다면 먹을 수 없는 경우
				break; // 루프 종료
			}
		}
		System.out.println(L);
	}
}