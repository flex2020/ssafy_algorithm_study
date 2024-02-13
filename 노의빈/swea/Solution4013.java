package swea;

import java.io.*;
import java.util.*;

public class Solution4013 {
	private static int K, answer;
	private static int[][] magnetics;
	private static Pointer[] ptrs;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			answer = 0;
			K = Integer.parseInt(br.readLine());
			magnetics = new int[4][8];
			ptrs = new Pointer[4];
			ptrs[0] = new Pointer(0, 2);
			ptrs[1] = new Pointer(0, 6, 2);
			ptrs[2] = new Pointer(0, 6, 2);
			ptrs[3] = new Pointer(0, 6, 2);
			
			for (int i=0; i<4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<8; j++) {
					magnetics[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 자석 회전
			for (int i=0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int index = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				switch (dir) {
					case -1:
						rotateLeft(index);
						break;
					case 1:
						rotateRight(index);
						break;
				}
				
			}
			setScore();
			System.out.println("#" + tc + " " + answer);
		}
	}
	// index번째 자석을 시계방향으로 회전
	private static void rotateRight(int index) {
		boolean[] link = new boolean[3];
		if (magnetics[0][ptrs[0].right] != magnetics[1][ptrs[1].left]) {
			link[0] = true;
		}
		if (magnetics[1][ptrs[1].right] != magnetics[2][ptrs[2].left]) {
			link[1] = true;
		}
		if (magnetics[2][ptrs[2].right] != magnetics[3][ptrs[3].left]) {
			link[2] = true;
		}
		boolean left = false; // false 반시계, true 시계
		boolean right = false;
		if (index == 0) {
			// 오른쪽 확인
			for (int i=0; i<3; i++) {
				if (!link[i]) break;
				if (!right) {
					ptrs[i+1].rotateLeft();
				} else {
					ptrs[i+1].rotateRight();			
				}
				right = !right;
			}
		}
		else if (index == 3) {
			// 왼쪽 확인
			for (int i=2; i>=0; i--) {
				if (!link[i]) break;
				if (!left) {
					ptrs[i].rotateLeft();
				}
				else {
					ptrs[i].rotateRight();
				}
				left = !left;
			}
		}
		else {
			// 왼쪽 확인
			for (int i=index-1; i>=0; i--) {
				if (!link[i]) break;
				if (!left) {
					ptrs[i].rotateLeft();
				}
				else {
					ptrs[i].rotateRight();
				}
				left = !left;
			}
			
			// 오른쪽 확인
			for (int i=index; i<3; i++) {
				if (!link[i]) break;
				if (!right) {
					ptrs[i+1].rotateLeft();
				} else {
					ptrs[i+1].rotateRight();			
				}
				right = !right;
			}
		}

		// 기준점 자석 회전
		ptrs[index].rotateRight();
	}
	// index번째 자석을 반시계방향으로 회전
	private static void rotateLeft(int index) {
		
		boolean[] link = new boolean[3];
		if (magnetics[0][ptrs[0].right] != magnetics[1][ptrs[1].left]) {
			link[0] = true;
		}
		if (magnetics[1][ptrs[1].right] != magnetics[2][ptrs[2].left]) {
			link[1] = true;
		}
		if (magnetics[2][ptrs[2].right] != magnetics[3][ptrs[3].left]) {
			link[2] = true;
		}
		boolean left = false; // false 시계, true 반시계
		boolean right = false;
		
	
		if (index == 0) {
			// 오른쪽 확인
			for (int i=0; i<3; i++) {
				if (!link[i]) break;
				if (!right) {
					ptrs[i+1].rotateRight();
				} else {
					ptrs[i+1].rotateLeft();			
				}
				right = !right;
			}
		}
		else if (index == 3) {
			// 왼쪽 확인
			for (int i=2; i>=0; i--) {
				if (!link[i]) break;
				if (!left) {
					ptrs[i].rotateRight();
				}
				else {
					ptrs[i].rotateLeft();
				}
				left = !left;
			}
		}
		else {
			// 왼쪽 확인
			for (int i=index-1; i>=0; i--) {
				if (!link[i]) break;
				if (!left) {
					ptrs[i].rotateRight();
				}
				else {
					ptrs[i].rotateLeft();
				}
				left = !left;
			}
			
			// 오른쪽 확인
			for (int i=index; i<3; i++) {
				if (!link[i]) break;
				if (!right) {
					ptrs[i+1].rotateRight();
				} else {
					ptrs[i+1].rotateLeft();			
				}
				right = !right;
			}
		}
		// 기준점 자석 회전
		ptrs[index].rotateLeft();
	}
	private static void setScore() {
		for (int i=0; i<4; i++) {
			int index = ptrs[i].red;
			if (magnetics[i][index] == 1) {
				//System.out.println(i + "번째 자석");
				answer += Math.pow(2, i);
			}
		}
	}
}
class Pointer {
	int red, left, right;

	public Pointer(int red, int right) {
		super();
		this.red = red;
		this.right = right;
	}

	public Pointer(int red, int left, int right) {
		super();
		this.red = red;
		this.left = left;
		this.right = right;
	}
	
	public void rotateRight() {
		red = (red + 7) % 8;
		left = (left + 7) % 8;
		right = (right + 7) % 8;
	}
	
	public void rotateLeft() {
		red = (red + 1) % 8;
		left = (left + 1) % 8;
		right = (right + 1) % 8;
	}

	@Override
	public String toString() {
		return "Pointer [red=" + red + ", left=" + left + ", right=" + right + "]";
	}
	
}
