package 강윤서.swea;

import java.io.*;
class Solution_2001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);
            int[][] board = new int[N][N];
            for (int i=0; i<N; i++) {
                input = br.readLine().split(" ");
                for (int j=0; j<N; j++) 
                    board[i][j] = Integer.parseInt(input[j]);
            }
            int answer = 0;
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    int temp = 0;
                    boolean flag = true;
                    for (int y=i; y<i+M; y++) {
                        for (int x=j; x<j+M; x++) {
                            if (0<=y && y<N && 0<=x && x<N) {
                                if (flag) temp += board[y][x];
                            } else {
                                flag = false;
                            }
                        }
                    }
                    answer = Math.max(answer, temp);
                }
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}
            