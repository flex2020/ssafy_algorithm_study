import java.util.*;
import java.io.*;

public class SolutionBaekJoon16926 {
	
	// 아래, 오른쪽, 위, 왼쪽
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] nums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		nums = new int[N][M ];
		for (int i = 0; i < nums.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < nums[0].length; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = Math.min(nums.length, nums[0].length) / 2;
		for (int i = 0; i < R; i++) {
			calculateResult(count);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < nums.length; x++) {
			for (int y = 0; y < nums[0].length; y++) {
				sb.append(nums[x][y] + " ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void calculateResult(int count) {
		int index = 0;
		int startI = 0;
		int startJ = 0;
		int endI = nums.length - 1;
		int endJ = nums[0].length - 1;
		int i = startI;
		int j = startJ;
		int prevNum = nums[startI][startJ];
		int tmp = 0;
		
		while (startI != count) {
			// 첫 번째 숫자 저장한 후 다음에 방문할 인덱스 갱신하기
			prevNum = nums[i][j];
			i += dx[index];

			while (i != startI || j != startJ) {
				// 숫자 바꿔주기
				tmp = nums[i][j];
				nums[i][j] = prevNum;
				prevNum = tmp;
				
				// 더 이상 앞으로 갈 수 없으면 방향 전환하기
				if ((i == startI && j == startJ) || (i == endI && j == startJ) || (i == endI && j == endJ) || (i == startI && j == endJ)) {
					index = (index + 1) % 4;
				}
				// 다음에 방문할 인덱스 갱신하기
				i += dx[index];
				j += dy[index];
			}
			nums[i][j] = prevNum;  // 첫 번째 숫자를 이전 숫자로 변경하기
			
			// 다음에 방문할 인덱스 & 방향 갱신하기
			startI++;
			startJ++;
			endI--;
			endJ--;
			i = startI;
			j = startJ;
			index = 0;
		}
	}

}