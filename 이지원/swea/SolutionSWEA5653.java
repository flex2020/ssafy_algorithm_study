import java.util.*;
import java.io.*;
 
class Cell {
    int value;
    int spreadTime;
    int time;  // 활성화 시간
     
    Cell(int value) {
        this.value = value;
    }
     
    public boolean isDead() {
        return value == time;
    }
}
 
class Position {
    int x;
    int y;
     
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
public class SolutionSWEA5653 {
     
    static int K;
    static Cell[][] map;
    static Queue<Position> cellPositions;
    static int time;
    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
             
            // 지도 정보 입력받기
            map = new Cell[N + 2 * K][M + 2 * K];
            cellPositions = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i + K][j + K] = new Cell(Integer.parseInt(st.nextToken()));
                     
                    if (map[i + K][j + K].value != 0) {
                        cellPositions.offer(new Position(i + K, j + K));
                    }
                }
            }
             
            spreadCells();
             
            System.out.println("#" + testCase + " " + cellPositions.size());
        }
    }
     
    // 세포 번식하기
    public static void spreadCells() {
        time = 1;
        while (time <= K) {
            int size = cellPositions.size();
            for (int i = 0; i < size; i++) {
                Position cellPosition = cellPositions.poll();
                 
                // 활성화 1시간인 경우
                if (map[cellPosition.x][cellPosition.y].time == 0
                    && (time % (map[cellPosition.x][cellPosition.y].value + 1) == 0)) {
                    spreadCell(cellPosition);
                     
                    map[cellPosition.x][cellPosition.y].time++;
                    if (!map[cellPosition.x][cellPosition.y].isDead()) {  // 죽지 않은 경우
                        cellPositions.offer(cellPosition);
                    }
                    continue;
                }
                 
                // 활성화 1시간 이상인 경우
                if (map[cellPosition.x][cellPosition.y].time > 0) {
                    map[cellPosition.x][cellPosition.y].time++;
                    if (!map[cellPosition.x][cellPosition.y].isDead()) {  // 죽지 않은 경우
                        cellPositions.offer(cellPosition);
                    }
                    continue;
                }
                 
                cellPositions.offer(cellPosition);
            }
             
            time++;
        }
    }
     
    public static void spreadCell(Position cellPosition) {
        int value = map[cellPosition.x][cellPosition.y].value;
         
        for (int index = 0; index < dx.length; index++) {
            int nextX = cellPosition.x + dx[index];
            int nextY = cellPosition.y + dy[index];
             
            if (map[nextX][nextY] == null || map[nextX][nextY].value == 0) {
                map[nextX][nextY] = new Cell(value);
                map[nextX][nextY].spreadTime = time;
                cellPositions.offer(new Position(nextX, nextY));
            } else if (map[nextX][nextY].spreadTime == time) {
                map[nextX][nextY].value = Math.max(map[nextX][nextY].value, value);
            }
        }
    }
 
}