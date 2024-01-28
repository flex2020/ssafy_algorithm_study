package 강윤서.swea;

import java.io.*;
import java.util.StringTokenizer;
public class Solution_7236 {
    static int N;
    static char[][] map;
    static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            int answer = 0;
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            for (int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    map[i][j] = st.nextToken().charAt(0);
                }
            }
            
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (map[i][j] == 'W')
                        answer = Math.max(answer, check(i, j));
                }
            }
            System.out.printf("#%d %d\n", tc, answer);

        }
    }
    public static int check(int y, int x) {
        int result = 0;
        for (int i=0; i<8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (0<=ny && ny<N && 0<=nx && nx<N) {
                if (map[ny][nx] == 'W') {
                    result += 1;
                }
            }
        }
        if (result == 0) 
            return 1;
        else 
            return result;
    }
}
