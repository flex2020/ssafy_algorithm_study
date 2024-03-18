import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    static int[][] tetris = {
            {0, 0, 0}, // 가로4개
            {1, 1, 1}, // 세로4개
            {0, 1, 2}, // 2*2 정사각형
            {1, 1, 0}, // ㄱ 1
            {0, 3, 3}, // ㄱ 2
            {3, 0, 0}, // ㄱ 3
            {0, 0, 1}, // ㄱ 4
            {1, 0, 0}, // ㄱ 5
            {0, 0, 3}, // ㄱ 6
            {3, 3, 0}, // ㄱ 7
            {0, 1, 1}, // ㄱ 8
            {1, 0, 1}, // ㄹ 1
            {3, 0, 3}, // ㄹ 2
            {0, 1, 0}, // ㄹ 3
            {0, 3, 0}, // ㄹ 4
    };

    static int[][] oh = {
            {0, 1, 2}, // ㅗ 1
            {1, 2, 3}, // ㅗ 2
            {2, 3, 0}, // ㅗ 3
            {3, 0, 1} // ㅗ4
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로 행
        int M = Integer.parseInt(st.nextToken()); // 가로 열

        int[][] map = new int[N+2][M+2];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer=0;

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < M+1; j++) {
                L: for (int k = 0; k < tetris.length; k++) { // ㅗ를 제외한 나머지 경우
                    int result = 0;
                    int nx = i;
                    int ny = j;
                    result += map[nx][ny];
                    for (int l = 0; l < tetris[k].length; l++) {
                        nx += dx[tetris[k][l]];
                        ny += dy[tetris[k][l]];
                        if(map[nx][ny]==0) continue L;
                        result += map[nx][ny];
                    }
                    answer = Math.max(answer, result);
                }
                L: for (int k = 0; k < oh.length; k++) { // ㅗ인 경우
                    int result = 0;
                    int nx = i;
                    int ny = j;
                    result += map[nx][ny];
                    for (int l = 0; l < oh[k].length; l++) {
                        nx = i + dx[oh[k][l]];
                        ny = j + dy[oh[k][l]];
                        if(map[nx][ny]==0) continue L;
                        result += map[nx][ny];
                    }
                    answer = Math.max(answer, result);
                }
            }
        }

        System.out.println(answer);

    }
}
