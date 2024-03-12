package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_21610 {
    static int N, M;
    static int[][] A;
    static boolean[][] checked;
    static Queue<int[]> cloud, increased;
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1}, dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        checked = new boolean[N][N];
        cloud = new ArrayDeque<>();
        increased = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 초기 구름 세팅
        cloud.offer(new int[] {N-2, 0});
        cloud.offer(new int[] {N-2, 1});
        cloud.offer(new int[] {N-1, 0});
        cloud.offer(new int[] {N-1, 1});
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            simulate(d, s);
        }
        int answer = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                answer += A[i][j];
            }
        }
        System.out.println(answer);
    }
    public static void simulate(int d, int s) {
        // 0. checked 배열 초기화
        for (int i=0; i<N; i++) {
            Arrays.fill(checked[i], false);
        }
        // 1. 구름 움직이기 (d방향으로 s만큼)
        int size = cloud.size();
        for (int i=0; i<size; i++) {
            int[] cur = cloud.poll();
            int nr = cur[0] + dr[d] * s;
            int nc = cur[1] + dc[d] * s;
            while (nr < 0) nr += N;
            while (nc < 0) nc += N;
            while (nr >= N) nr -= N;
            while (nc >= N) nc -= N;
            cloud.offer(new int[] {nr, nc});
        }
        // 2. 구름이 있는 곳 비 내리고 구름 사라짐
        while (!cloud.isEmpty()) {
            int[] cur = cloud.poll();
            A[cur[0]][cur[1]]++;
            increased.offer(cur);
        }
        // 3. 물복사 
        while (!increased.isEmpty()) {
            int[] cur = increased.poll();
            int cnt = 0; 
            for (int i=1; i<8; i+=2) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (0<=nr && nr<N && 0<=nc && nc<N && A[nr][nc] > 0) {
                    cnt++;
                }
            }
            A[cur[0]][cur[1]] += cnt;
            checked[cur[0]][cur[1]] = true;
        }
        // 4. 구름 있던 곳 제외하고 다시 구름 재 생성
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!checked[i][j] && A[i][j] >= 2) {
                    A[i][j] -= 2;
                    cloud.offer(new int[] {i, j});
                }
            }
        }
    }
}
