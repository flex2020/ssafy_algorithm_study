import java.util.*;
import java.io.*;

public class SolutionBaekJoon12100 {
	
	static int N;
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 숫자 입력받기
		int[][] nums = new int[N + 2][N + 2];
		Arrays.fill(nums[0], -1);
		Arrays.fill(nums[nums.length - 1], -1);
		StringTokenizer st;
		for (int i = 1; i < nums.length - 1; i++) {
			nums[i][0] = -1;
			nums[i][nums[0].length - 1] = -1;
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < nums[0].length - 1; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 최대 5번 이동시켜 얻을 수 있는 가장 큰 블록 찾기
		max = Integer.MIN_VALUE;
		calculateResult(copyNums(nums), 0);
		
		System.out.println(max);
	}
	
	public static void calculateResult(int[][] nums, int count) {
		if (count == 5) {
			return;
		}
		
		// 상
		calculateResult(moveBlock(nums, 0, nums.length - 2, 1), count + 1);
		// 하
		calculateResult(moveBlock(nums, 1, 1, 1), count + 1);
		// 좌
		calculateResult(moveBlock(nums, 2, 1, nums[0].length - 2), count + 1);
		// 우
		calculateResult(moveBlock(nums, 3, 1, 1), count + 1);
	}
	
	public static int[][] moveBlock(int[][] nums, int direction, int startX, int startY) {
		int[][] copiedNums = copyNums(nums);
		
		int x = startX;
		int y = startY;
		int curX = startX;
		int curY = startY;
		int count = N;
		while (count > 0) {
			Deque<Integer> deque = new ArrayDeque<>();
			while (copiedNums[curX][curY] != -1) {
				if (copiedNums[curX][curY] > 0) {
					int curNum = copiedNums[curX][curY];
					copiedNums[curX][curY] = 0;

					if (!deque.isEmpty() && deque.peekLast() == curNum) {  // 같은 숫자인 경우
						deque.offer(deque.pollLast() * 2);
						
						// 숫자 저장하기
						while (!deque.isEmpty()) {
							copiedNums[x][y] = deque.poll();
							max = Math.max(max, copiedNums[x][y]);
							x += dx[direction];
							y += dy[direction];
						}
					} else {  // 이전에 숫자가 없거나 이전 숫자와 현재 숫자가 다른 경우
						// 숫자 저장하기
						while (!deque.isEmpty()) {
							copiedNums[x][y] = deque.poll();
							max = Math.max(max, copiedNums[x][y]);
							x += dx[direction];
							y += dy[direction];
						}

						deque.offer(curNum);
					}
				}
				
				// 다음으로 넘어가기
				curX += dx[direction];
				curY += dy[direction];
			}
			
			// 남은 숫자 저장하기
			while (!deque.isEmpty()) {
				copiedNums[x][y] = deque.poll();
				max = Math.max(max, copiedNums[x][y]);
				x += dx[direction];
				y += dy[direction];
			}
			
			// 행 또는 열 바꾸기
			if (direction < 2) {  // 열 바꾸기
				x = startX;
				y++;
				
				curX = startX;
				curY++;
			} else {
				x++;
				y = startY;
				
				curX++;
				curY = startY;
			}
			
			count--;
		}
		
		return copiedNums;
	}
	
	public static int[][] copyNums(int[][] nums) {
		int[][] copiedNums = new int[nums.length][nums[0].length];
		
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				copiedNums[i][j] = nums[i][j];
			}
		}
		
		return copiedNums;
	}

}
