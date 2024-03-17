package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_14500 {
    static int N, M, answer = -1;
    static int[][] board;
    static int[][][][] type = {
        {{{1, 0}, {2, 0}, {3, 0}}, {{0, 1}, {0, 2}, {0, 3}}},
        {{{0, 1}, {1, 0}, {1, 1}}, {{0, -1}, {1, -1}, {1, 0}}},
        {{{1, 0}, {2, 0}, {2, 1}}, {{0, 1}, {0, 2}, {-1, 2}}},
        {{{1, 0}, {1, 1}, {2, 1}}, {{0, 1}, {-1, 1}, {-1, 2}}},
        {{{0, 1}, {0, 2}, {1, 1}}, {{1, 0}, {2, 0}, {1, -1}}}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 모든 점에서 모든 종류로 해보기
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                check(i, j);
            }
        }
        System.out.println(answer);
    }
    public static void check(int r, int c) {
        for (int i=0; i<5; i++) { // 1. 테트로미노 종류 5개
            int[] standard = new int[] {r, c}; // 기준 점 세팅
            for (int j=0; j<8; j++) { // 2. 하나의 테트로미노로부터 나올 수 있는 경우의 수 8가지
                boolean flag = true; // 해당 위치에서 해당 방향으로 테트로미노를 놓을 수 있는가?
                int sum = board[r][c];
                for (int cnt=0; cnt<3; cnt++) { // 3. 한 가지 경우에 대해 확인해야 할 좌표 3개
                    int nr = standard[0];
                    int nc = standard[1];
                    if (j < 2) {
                        nr = standard[0] + type[i][j%2][cnt][0];
                        nc = standard[1] + type[i][j%2][cnt][1];
                    } else if (j < 4) {
                        nr = standard[0] + (-1) * type[i][j%2][cnt][0];
                        nc = standard[1] + (-1) * type[i][j%2][cnt][1];
                    } else if (j == 4 || j == 5) { 
                        nr = standard[0] + (-1) * type[i][j%2][cnt][0];
                        nc = standard[1] + type[i][j%2][cnt][1];
                    } else if (j == 6 || j == 7) { 
                        nr = standard[0] + type[i][j%2][cnt][0];
                        nc = standard[1] + (-1) * type[i][j%2][cnt][1];
                    }
                    if (nr<0 || nr>=N || nc<0 || nc>=M) {
                        flag = false;
                        break;
                    }
                    sum += board[nr][nc];
                }
                if (flag) {
                    answer = Math.max(answer, sum);
                }
            }
        }
    }
}
