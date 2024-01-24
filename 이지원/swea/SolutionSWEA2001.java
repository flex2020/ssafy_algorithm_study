import java.util.Scanner;

public class SolutionSWEA2001 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] map = new int[N][N];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int result = Integer.MIN_VALUE;
            for (int i = 0; i < map.length - M + 1; i++) {
                for (int j = 0; j < map.length - M + 1; j++) {
                    result = Math.max(result, calculateFlyCount(map, M, i, j));
                }
            }

            System.out.println("#" + testCase + " " + result);
        }
    }

    public static int calculateFlyCount(int[][] map, int M, int startI, int startJ) {
        int flyCount = 0;
        for (int i = startI; i < startI + M; i++) {
            for (int j = startJ; j < startJ + M; j++) {
                flyCount += map[i][j];
            }
        }

        return flyCount;
    }
}