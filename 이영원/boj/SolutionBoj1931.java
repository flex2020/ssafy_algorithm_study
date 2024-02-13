import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 끝시간이 같을경우 시작시간 기준 오름차순, 다를경우 끝시간 기준 오름차순
		Arrays.sort(arr, (a, b) -> {
			if(a[1]==b[1]) return a[0]-b[0];
			return a[1]-b[1];
		});
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(arr[i][0] + " " + arr[i][1]);
//		}
		
		// 초기값들
		int idx=1;
		int answer=1; 
		int prevEnd = arr[0][1]; 
		
		while(idx!=N) {
			if(prevEnd <= arr[idx][0]) { // 시작시간보다 크다면 answer 올리기
				answer++;
				prevEnd = arr[idx][1];
			}
			idx++;
		}
		
		System.out.println(answer);
	}
}
