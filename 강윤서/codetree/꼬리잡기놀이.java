
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static int N, M, K, teamNumber = 0;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] board, teamGroup;
    static int[] teamPoint, teamCnt, teamDir;
    static List<List<Point>> teamList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        teamGroup = new int[N][N];
        teamList = new ArrayList<>();
        teamList.add(new ArrayList<>()); // 0번째 더미 list 추가

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) teamNumber++;
            }
        }

        teamPoint = new int[teamNumber + 1];
        teamCnt = new int[teamNumber + 1];
        teamDir = new int[teamNumber + 1];
        Arrays.fill(teamDir, 1);
        int idx = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] == 1) {
                    makeLine(i, j, ++idx);
                }
            }
        }
//        print();
        for (int i=0; i<K; i++) {
//            System.out.println((i) + "번째 라운드~!!!");
            move();
            ball(i);
        }
//        print();
        int answer = 0;
        for (int i=1; i<=teamNumber; i++) {
            answer += teamPoint[i];
        }
        System.out.println(answer);
    }
    public static void ball(int i) {
        int round = i % (4 * N);
        Queue<int[]> Q = new ArrayDeque<>();
        if (0 <= round && round < N) {
            Q.offer(new int[] {round, 0, 0});
        } else if (N <= round && round < 2 * N) {
            Q.offer(new int[] {N-1, round % N, 1});
        } else if (2 * N <= round && round < 3 * N) {
            Q.offer(new int[] {N - round % N - 1, N-1, 2});
        } else {
            Q.offer(new int[] {0, N - round % N - 1, 3});
        }
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            if (teamGroup[cur[0]][cur[1]] > 0) {
//                System.out.println("wow!!!!!!!!!" + teamGroup[cur[0]][cur[1]] + " " + cur[0] + " " + cur[1]);
                int number = teamGroup[cur[0]][cur[1]];
                int index = teamList.get(number).indexOf(new Point(cur[0], cur[1]));
                if (index < teamCnt[number]) {
                    if (teamDir[number] == 1)
                        teamPoint[number] += (index+1) * (index+1);
                    else
                        teamPoint[number] += (teamCnt[number]-index) * (teamCnt[number]-index);
                    // 방향 바꾸기
                    teamDir[number] *= -1;
                    return ;
                }
            }
            int nr = cur[0] + dr[cur[2]];
            int nc = cur[1] + dc[cur[2]];
            if (0<=nr && nr<N && 0<=nc && nc<N) {
                Q.offer(new int[] {nr, nc, cur[2]});
            }
        }
    }
    public static void makeLine(int r, int c, int teamNumber) {
        Queue<Point> Q = new ArrayDeque<>();
        teamList.add(new LinkedList<>());

        Q.offer(new Point(r, c));

        while (!Q.isEmpty()) {
            Point cur = Q.poll();
            if (board[cur.x][cur.y] != 4)
                teamCnt[teamNumber] += 1;
            teamGroup[cur.x][cur.y] = teamNumber;
            teamList.get(teamNumber).add(new Point(cur.x, cur.y));

            for (int i=0; i<4; i++) {
                int nr = cur.x + dr[i];
                int nc = cur.y + dc[i];
                if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
                if (teamGroup[nr][nc] != 0) continue;
                if (board[nr][nc] == board[cur.x][cur.y] || board[nr][nc] == board[cur.x][cur.y] + 1) {
                    Q.offer(new Point(nr, nc));
                }
            }
        }
    }
    public static void move() {
        for (int i=1; i<teamList.size(); i++) {
            int size = teamList.get(i).size();
            if (teamDir[i] == 1) {
                Point p = teamList.get(i).remove(size-1);
                teamList.get(i).add(0, p);
            } else {
                Point p = teamList.get(i).remove(0);
                teamList.get(i).add(p);
            }
        }
    }
    public static void print() {
        for (int i=1; i<teamList.size(); i++) {
            System.out.println(teamList.get(i));
        }
        System.out.println("--------------------------");
        System.out.println(Arrays.toString(teamCnt));
        System.out.println(Arrays.toString(teamPoint));
        System.out.println(Arrays.toString(teamDir));
        System.out.println("--------------------------");
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(teamGroup[i][j] + " ");
            }
            System.out.println();
        }
    }
}
