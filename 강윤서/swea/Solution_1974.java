package 강윤서.swea;

import java.io.*;
import java.util.*;
class Solution_1974 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
            int answer = 1;
    		int[][] board = new int[9][9];
            for (int i=0; i<9; i++) {
                String[] input = br.readLine().split(" ");
                for (int j=0; j<9; j++) {
                    board[i][j] = Integer.parseInt(input[j]);
                }
            }
            int[] count = new int[10];
            for (int i=0; i<9; i++) {
                Arrays.fill(count, 0);
                for (int j=0; j<9; j++) {
                    count[board[i][j]] += 1;
                    count[board[j][i]] += 1;
                }
                for (int k=1; k<=9; k++){
                    if (count[k] != 2) {
                        answer = 0;
                        break;
                    }
                }
            }
            for (int i=0; i<9; i+=3) {
                for (int j=0; j<9; j+=3) {
                    Arrays.fill(count, 0);
                    for (int y=i; y<i+3; y++) {
                        for (int x=j; x<j+3; x++) {
                            count[board[y][x]] += 1;
                        }
                    }
                    for (int k=1; k<=9; k++){
                        if (count[k] != 1) {
                            answer = 0;
                            break;
                        }
               		}
                }
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}