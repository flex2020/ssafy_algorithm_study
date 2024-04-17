package 강윤서.codetree;

import java.io.*;
import java.util.*;
import java.awt.Point;
public class Main_나무박멸 {
    static int N, M, K, C, answer;
    static int[][] board, timeBoard;
    static boolean[][] visited;
    static List<Point> trees, spreadTree;
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1}, dc = {0, 0, -1, 1, -1, 1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        timeBoard = new int[N][N]; // 제초제 남은 시간 2차원 배열
        visited = new boolean[N][N];
        trees = new ArrayList<>();
        spreadTree = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0; i<M; i++) {
            // 0. 나무 리스트에 담기
            makeTree();
            // 1. 나무 성장
            grow();
            // 2. 나무 번식
            initVisited();
            spread();
            // 3. 제초제
            decreaseTime();
            spray();
        }
        System.out.println(answer);
    }
    public static void makeTree() {
        trees.clear();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] > 0) {
                    trees.add(new Point(i, j));
                }
            }
        }
    }
    public static void initVisited() {
        for (int i=0; i<N; i++) {
            Arrays.fill(visited[i], false);
        }
    }
    public static void grow() {
        for (Point t : trees) {
            int cnt = 0;
            for (int d=0; d<4; d++) {
                int nr = t.x + dr[d];
                int nc = t.y + dc[d];
                if (0<=nr && nr<N && 0<=nc && nc<N && board[nr][nc] > 0) {
                    cnt++;
                }
            }
            board[t.x][t.y] += cnt;
        }
    }
    public static void spread() {
        int size = trees.size();
        for (int i=0; i<size; i++) {
            Point t = trees.get(i);
            spreadTree.clear();
            for (int d=0; d<4; d++) {
                int nr = t.x + dr[d];
                int nc = t.y + dc[d];
                if (0<=nr && nr<N && 0<=nc && nc<N) {
                    if (board[nr][nc] == -1) continue;
                    if (board[nr][nc] > 0 && !visited[nr][nc]) continue; // 이미 심어진 나무가 있음
                    if (timeBoard[nr][nc] > 0) continue; // 제초제가 뿌려진 나무
                    spreadTree.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            for (Point next : spreadTree) {
                board[next.x][next.y] += board[t.x][t.y] / spreadTree.size();
                trees.add(next);
            }
        }
    }
    public static void spray() {
        PriorityQueue<int[]> PQ = new PriorityQueue<>((p1, p2) -> p1[0]==p2[0] ? p1[1]==p2[1] ? p1[2]-p2[2] : p1[1]-p2[1] : p2[0]-p1[0]);
        for (int i=0; i<trees.size(); i++) {
            Point t = trees.get(i);
            if (board[t.x][t.y] <= 0 || timeBoard[t.x][t.y] > 0) continue;
            int cnt = 0;
            for (int d=4; d<8; d++) {
                for (int length=1; length<=K; length++) {
                    int nr = t.x + dr[d] * length;
                    int nc = t.y + dc[d] * length;
                    if (nr<0 || nr>=N || nc<0 || nc>=N) break; // 범위 벗어남
                    if (board[nr][nc] <= 0) break; // 벽 또는 나무가 없음
                    cnt += board[nr][nc];       
                }
            }
            cnt += board[t.x][t.y];
            PQ.offer(new int[] {cnt, t.x, t.y});
        }
        // PQ에서 빼서 제초제 뿌리기
        if (!PQ.isEmpty()) {
            int[] medicine = PQ.poll();
            for (int d=4; d<8; d++) {
                for (int length=1; length<=K; length++) {
                    int nr = medicine[1] + dr[d] * length;
                    int nc = medicine[2] + dc[d] * length;
                    if (nr<0 || nr>=N || nc<0 || nc>=N) break; // 범위 벗어남
                    timeBoard[nr][nc] = C;
                    if (board[nr][nc] <= 0) break; // 벽 또는 나무가 없음
                    answer += board[nr][nc];
                    board[nr][nc] = 0; // 나무 죽이기
                }
            }
            answer += board[medicine[1]][medicine[2]];
            board[medicine[1]][medicine[2]] = 0;
            timeBoard[medicine[1]][medicine[2]] = C;
        }
    }
    public static void decreaseTime() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (timeBoard[i][j] > 0)
                    timeBoard[i][j]--;
            }
        }
    }
    public static void print() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("보드출력");
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(timeBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("제초제 테이블 출력");
    }
}