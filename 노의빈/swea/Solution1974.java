package swea;

import java.util.*;
import java.io.*;

public class Solution1974 {
	private static int[][] board = new int[9][9];
	private static int[][] nboard = new int[9][9];
	private static int N;
	private static final int check1 = 45, check2 = 2*3*4*5*6*7*8*9;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			int answer = 1;
			for (int i=0; i<9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<9; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if (!checkBox()) {
				answer = 0;
			} else {
				rotate();
				for (int i=0; i<9; i++) {
					if (!checkRow(i)) {
						answer = 0;
						//System.out.println(i);
						break;
					}
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
	private static boolean checkBox() {
		boolean[] check;
		for (int i=0; i<9; i+=3) {
			for (int j=0; j<9; j+=3) {
				// 박스 시작지점 i, j
				check = new boolean[9];
				for (int k=0; k<3; k++) {
					for (int l=0; l<3; l++) {
						int num = board[i+k][j+l] - 1;
						check[num] = true;
					}
				}
				// 박스마다 1~9가 들어있었는지 확인
				for (int k=0; k<9; k++) {
					if (!check[k]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	private static boolean checkRow(int row) {
		boolean[] checkArr1, checkArr2;
		for (int i=0; i<9; i++) {
			checkArr1 = new boolean[9];
			checkArr2 = new boolean[9];
			for (int j=0; j<9; j++) {
				int num1 = board[i][j] - 1;
				int num2 = nboard[i][j] - 1;
				checkArr1[num1] = true;
				checkArr2[num2] = true;
			}
			for (int j=0; j<9; j++) {
				if (!checkArr1[j] || !checkArr2[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void rotate() {
		for (int j=0; j<9; j++) {
			for (int i=0; i<9; i++) {
				nboard[j][i] = board[i][j];
			}
		}
	}

}
