package 강윤서.boj;

import java.util.*;
import java.io.*;
public class Main_19237 {
    static int N, M, K, value, curTime, sharkCnt;
    static int[][][] priority;
    static int[][][] map;
    static Shark[] sharkArray;
    static PriorityQueue<Shark> PQ;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static class Shark {
        int number, r, c, dir, state, active;
        Shark (int number, int r, int c, int state, int active) {
            this.number = number;
            this.r = r;
            this.c = c;
            this.state = state;
            this.active = active;
        }
        @Override
        public String toString() {
            return number + " " + r + " " + c + " " + dir + " " + active + " " + state;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N][2]; // 번호, 남은 시간
        priority = new int[M+1][4][4]; // 방향 우선순위 저장
        sharkArray = new Shark[M+1];
        PQ = new PriorityQueue<>((s1, s2) -> s1.number == s2.number ? s2.state - s1.state : s1.number - s2.number);


        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                value = Integer.parseInt(st.nextToken());
                if (value > 0) { // 상어 존재
                    Shark shark = new Shark(value, i, j, 1, K);
                    sharkArray[value] = shark;
                }
            }
        }
        st = new StringTokenizer(br.readLine()); // 상어 방향 설정
        for (int i=1; i<=M; i++) {
            sharkArray[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }
        for (int i=1; i<=M; i++) {
            for (int j=0; j<4; j++) { // 상 하 좌 우 우선순위
                st = new StringTokenizer(br.readLine());
                for (int k=0; k<4; k++) {
                    priority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        simulation();
        System.out.println(curTime);
    }
    public static void simulation() {
        while (true) {
            if (curTime > 1000) {
                curTime = -1;
                break;
            }
            sharkCnt = 0;
            initialize(); // 1. sharkArray 초기화 및 PQ에 상어넣기
            // 2. 냄새 1 감소
            for (int i=0; i<N; i++) {
            	for (int j=0; j<N; j++) {
            		if (map[i][j][1] > 0) {
            			map[i][j][1]--;
            		} else {
            			map[i][j] = new int[] {0, 0};
            		}
            	}
            }
            if (sharkCnt == 1) {
                break; // 1번 혼자 남음 -> 종료
            }
            move(); // 3. 상어 이동
            curTime++;
        }
    }
    public static void initialize() {
        for (int i=1; i<=M; i++) {
            if (sharkArray[i] == null) continue;
            int number = sharkArray[i].number;
            int r = sharkArray[i].r;
            int c = sharkArray[i].c;
            if (map[r][c][0] == 0 || map[r][c][0] == number) {
                sharkCnt++;
                PQ.offer(sharkArray[i]);
                map[r][c][0] = number;
                map[r][c][1] = K;
            }
        }
        Arrays.fill(sharkArray, null);
    }
    public static void move() {
        while (!PQ.isEmpty()) {
            Shark cur = PQ.poll();
            boolean canMove = false;
            for (int i=0; i<4; i++) { // 우선순위대로 다음 이동 위치 확인
                int nr = cur.r + dr[priority[cur.number][cur.dir][i]];
                int nc = cur.c + dc[priority[cur.number][cur.dir][i]];
                if (0<=nr && nr<N && 0<=nc && nc<N) {
                    if (map[nr][nc][0] == 0) { // 냄새가 묻지 않은 땅
                        canMove = true;
                        sharkArray[cur.number] = new Shark(cur.number, nr, nc, 1, K);
                        sharkArray[cur.number].dir = priority[cur.number][cur.dir][i];
                        break;
                    }
                }
            }
            if (!canMove) {
                // 자기 냄새가 있는 곳으로 옮기기
                for (int i=0; i<4; i++) {
                    int nr = cur.r + dr[priority[cur.number][cur.dir][i]];
                    int nc = cur.c + dc[priority[cur.number][cur.dir][i]];
                    if (0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc][0] == cur.number) {
                        sharkArray[cur.number] = new Shark(cur.number, nr, nc, 1, K);
                        sharkArray[cur.number].dir = priority[cur.number][cur.dir][i];
                        break;
                    }
                }
            }
        }
    }
}