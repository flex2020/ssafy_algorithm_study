import java.util.*;
import java.io.*;
 
class Position {
    int x;
    int y;
     
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
public class SolutionSWEA1953 {
     
    static int L;
    static int[][] map;
    static boolean[][] isVisited;
    static Queue<Position> queue;
    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int destinationCount;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
 
            Position start = new Position(Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()) + 1);
             
            L = Integer.parseInt(st.nextToken());
             
            // 지도 정보 입력받기
            map = new int[N + 2][M + 2];
            Arrays.fill(map[0], -1);
            Arrays.fill(map[map.length - 1], -1);
            for (int i = 1; i < map.length - 1; i++) {
                map[i][0] = -1;
                map[i][map[0].length - 1] = -1;
                 
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < map[0].length - 1; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            calculateResult(start);
             
            System.out.println("#" + testCase + " " + destinationCount);
        }
    }
     
    public static void calculateResult(Position start) {
        isVisited = new boolean[map.length][map[0].length];
        queue = new ArrayDeque<>();
        // 첫 번째 지점
        destinationCount = 1;
        isVisited[start.x][start.y] = true;
        queue.offer(start);
        L--;
         
        Position current;
        int size;
        while (L > 0 && !queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                current = queue.poll();
                 
                switch (map[current.x][current.y]) {
                    case 1:  // 상, 하, 좌, 우
                        move(current, 0);
                        move(current, 1);
                        move(current, 2);
                        move(current, 3);
                        break;
                    case 2:  // 상, 하
                        move(current, 0);
                        move(current, 1);
                        break;
                    case 3:  // 좌, 우
                        move(current, 2);
                        move(current, 3);
                        break;
                    case 4:  // 상, 우
                        move(current, 0);
                        move(current, 3);
                        break;
                    case 5:  // 하, 우
                        move(current, 1);
                        move(current, 3);
                        break;
                    case 6:  // 하, 좌
                        move(current, 1);
                        move(current, 2);
                        break;
                    case 7:  // 상, 좌
                        move(current, 0);
                        move(current, 2);
                        break;
                }
            }
            L--;
        }
    }
     
    public static void move(Position current, int direction) {
        Position next = new Position(current.x + dx[direction], current.y + dy[direction]);
         
        if (!isVisited[next.x][next.y]) {
            switch (map[next.x][next.y]) {
                case 1:  // 상, 하, 좌, 우
                    destinationCount++;
                    isVisited[next.x][next.y] = true;
                    queue.offer(next);
                    break;
                case 2:  // 상, 하
                    if (direction < 2) {
                        destinationCount++;
                        isVisited[next.x][next.y] = true;
                        queue.offer(next);
                    }
                    break;
                case 3:  // 좌, 우
                    if (direction >= 2) {
                        destinationCount++;
                        isVisited[next.x][next.y] = true;
                        queue.offer(next);
                    }
                    break;
                case 4:  // 하, 좌
                    if (direction == 1 || direction == 2) {
                        destinationCount++;
                        isVisited[next.x][next.y] = true;
                        queue.offer(next);
                    }
                    break;
                case 5:  // 상, 좌
                    if (direction == 0 || direction == 2) {
                        destinationCount++;
                        isVisited[next.x][next.y] = true;
                        queue.offer(next);
                    }
                    break;
                case 6:  // 상, 우
                    if (direction == 0 || direction == 3) {
                        destinationCount++;
                        isVisited[next.x][next.y] = true;
                        queue.offer(next);
                    }
                    break;
                case 7:  // 하, 우
                    if (direction == 1 || direction == 3) {
                        destinationCount++;
                        isVisited[next.x][next.y] = true;
                        queue.offer(next);
                    }
                    break;
            }
        }
    }
 
}