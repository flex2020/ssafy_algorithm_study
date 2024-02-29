import java.util.*;
import java.io.*;
 
public class SolutionSWEA1949 {
     
    static class Position {
        int x;
        int y;
         
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
     
    static int K;
    static int[][] map;
    static boolean[][] isChoosed;
    static List<Position> maxHeightPositions;
    static int maxHeight;
    static int maxLength;
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
            K = Integer.parseInt(st.nextToken());
             
            // 지도 정보 입력받기
            map = new int[N + 2][N + 2];
            Arrays.fill(map[0], -1);
            Arrays.fill(map[map.length - 1], -1);
            maxHeight = Integer.MIN_VALUE;
            for (int i = 1; i < map.length - 1; i++) {
                map[i][0] = -1;
                map[i][map[0].length - 1] = -1;
                 
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < map[0].length - 1; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                     
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }
             
            // 가장 높은 봉우리 찾기
            maxHeightPositions = new ArrayList<>();
            for (int i = 1; i < map.length - 1; i++) {
                for (int j = 1; j < map[0].length - 1; j++) {
                    if (map[i][j] == maxHeight) {
                        maxHeightPositions.add(new Position(i, j));
                    }
                }
            }
             
            // 가장 긴 등산로 찾기
            maxLength = Integer.MIN_VALUE;
            for (Position maxHeightPosition : maxHeightPositions) {
                isChoosed = new boolean[map.length][map[0].length];
                isChoosed[maxHeightPosition.x][maxHeightPosition.y] = true;
                calculateMaxLength(maxHeightPosition, 1, false);
                isChoosed[maxHeightPosition.x][maxHeightPosition.y] = false;
            }
             
            System.out.println("#" + testCase + " " + maxLength);
        }
    }
     
    public static void calculateMaxLength(Position current, int totalLength, boolean isMinus) {
        boolean isNext = false;
        for (int direction = 0; direction < dx.length; direction++) {
            int nextX = current.x + dx[direction];
            int nextY = current.y + dy[direction];
             
            if (map[nextX][nextY] == -1 || isChoosed[nextX][nextY]) {
                continue;
            }
             
            if (map[nextX][nextY] < map[current.x][current.y]) {
                // 깎지 않고 다음 넘어가기
                isChoosed[nextX][nextY] = true;
                calculateMaxLength(new Position(nextX, nextY), totalLength + 1, isMinus);
                isChoosed[nextX][nextY] = false;
                 
                isNext = true;
            } else if (map[nextX][nextY] >= map[current.x][current.y] && !isMinus) {
                int difference = map[nextX][nextY] - map[current.x][current.y] + 1;
                // 깎고 다음 넘어가기
                if (map[nextX][nextY] - difference >= 0 && difference <= K) {
                    map[nextX][nextY] -= difference;
                    isChoosed[nextX][nextY] = true;
                    calculateMaxLength(new Position(nextX, nextY), totalLength + 1, true);
                    isChoosed[nextX][nextY] = false;
                    map[nextX][nextY] += difference;
                     
                    isNext = true;
                }
            }
        }
         
        if (!isNext) {
            maxLength = Math.max(maxLength, totalLength);
        }
    }
 
}