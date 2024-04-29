package 강윤서.boj;
import java.io.*;
import java.util.*;
public class Main_2234 {
    static int N, M, roomNumber=1, maxCnt=0, removeWallMaxCnt=0;
    static int[][] board;
    static int[][] num;
    static int[] room;
    static List<Set<Integer>> list; // 인접리스트
    static int[] dr = {0, -1, 0, 1}; // 서(1) 북(2) 동(4) 남(8)
    static int[] dc = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        num = new int[N][M];
        room = new int[N*M+1];
        list = new ArrayList<>();
        list.add(new HashSet<>()); // 0번 방 넣기
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 1. 방의 개수 구하기
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (num[i][j] == 0) {
                    list.add(new HashSet<>());
                    bfs(i, j);
                    roomNumber++;
                }
            }
        }
        // 방 별로 크기 구하기
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                room[num[i][j]]++;
            }
        }
        // 가장 넓은 크기 구하기
        for (int v : room) {
            maxCnt = Math.max(maxCnt, v);
        }
        sb.append(roomNumber-1 + "\n"); // 1. 방의 개수
        sb.append(maxCnt + "\n"); // 2. 가장 넓은 방의 넓이
        // 3. 벽 하나 부숴보기
        for (int i=1; i<roomNumber; i++) {
            for (int next : list.get(i)) {
                removeWallMaxCnt = Math.max(removeWallMaxCnt, room[i] + room[next]);
            }
        }
        sb.append(removeWallMaxCnt);
        System.out.println(sb);
    }
    public static void bfs(int r, int c) {
        Queue<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[] {r, c});
        num[r][c] = roomNumber;
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();

            for (int i=0 ;i<4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (0<=nr && nr<N && 0<=nc && nc<M) { // 범위 체크
                    if (((1 << i) & board[cur[0]][cur[1]]) == 0) { // 벽이 없음 = 같은 방
                        if (num[nr][nc] == 0) {
                            Q.offer(new int[] {nr, nc});
                            num[nr][nc] = roomNumber;
                        }
                    } else if (num[nr][nc] != 0 && num[nr][nc] != roomNumber){
                        list.get(roomNumber).add(num[nr][nc]);
                    }
                }
            }
        }
    }
    public static void print() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.print(num[i][j] + " ");
            }
            System.out.println();
        }
    }
}