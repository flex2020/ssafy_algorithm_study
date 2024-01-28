package 강윤서.swea;

import java.io.*;

public class Solution_5356 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
        char[][] board = new char[5][];
		for (int tc=1; tc<=T ;tc++) {
			int maxLength = 0;
			for (int i=0; i<5; i++) {
				char[] input = br.readLine().toCharArray();
				board[i] = new char[input.length];
				maxLength = Math.max(maxLength, input.length);
				for (int j=0; j<input.length; j++) {
					board[i][j] = input[j];
				}
			}
			System.out.print("#" + tc + " ");
			for (int i=0; i<maxLength; i++) {
				for (int j=0; j<5; j++) {
					if (i < board[j].length)
						System.out.print(board[j][i]);
				}
			}

            System.out.println();

		}
	}
}