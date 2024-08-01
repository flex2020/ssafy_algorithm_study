import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int N;
    static int[][] map;
    static int[][] memo;
    static int answer;

    static int[] dx = {-1, 1, 0, 0}; // 우 하 좌 상
    static int[] dy = {0, 0, -1, 1}; // 우 하 좌 상

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        memo = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, dfs(i, j));
//                print();
            }
        }

//        print();

        System.out.println(answer+1);

    }

    private static void print(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(memo[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

		// 돌아돌아돌아랏
    private static int dfs(int x, int y){
        if(memo[x][y]!=0){ // 업데이트 된거라면 패스
            return memo[x][y];
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny] <= map[x][y]) continue;
            // 해줘잉
            memo[x][y] = Math.max(memo[x][y], dfs(nx, ny) + 1);
        }
        return memo[x][y];
    }
}
