import java.util.*;
import java.io.*;

public class SolutionBaekJoon14500 {

    static int N, M;
    static int[][] nums;
    static int[] dx = { 0, 0, 1, 1, -1, 2, -1, 2 };
    static int[] dy = { -1, 2, -1, 2, 0, 0, 1, 1 };
    static int maxResult;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 종이 정보 입력받기
        nums = new int[N + 2][M + 2];
        Arrays.fill(nums[0], -1);
        Arrays.fill(nums[nums.length - 1], -1);
        for (int i = 1; i <= N; i++) {
            nums[i][0] = -1;
            nums[i][nums[0].length - 1] = -1;

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 수들의 합의 최댓값 구하기
        maxResult = Integer.MIN_VALUE;
        calculateMaxResult();

        System.out.println(maxResult);
    }

    public static void calculateMaxResult() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 정사각형 합 구하기 & 정사각형 안에 제일 작은 값 구하기
                boolean canSquare = true;
                int sum = 0;
                for (int x = 0; x <= 1; x++) {
                    for (int y = 0; y <= 1; y++) {
                        if (nums[i + x][j + y] == -1) {
                            canSquare = false;
                            break;
                        }
                        sum += nums[i + x][j + y];
                    }
                }

                if (canSquare) {
                    maxResult = Math.max(maxResult, sum);

                    // 정사각형, 직선 모양 제외 모양 둘러보기
                    calculateOtherShape(i, j, sum);
                }

                // 1x4, 4x1에서 제일 큰 값 구하기
                int horizontal = 0;
                for (int k = 0; k < 4; k++) {
                    if (nums[i][j + k] == -1) {
                        break;
                    }

                    horizontal += nums[i][j + k];

                    if (k == 3) {
                        maxResult = Math.max(maxResult, horizontal);
                    }
                }

                int vertical = 0;
                for (int k = 0; k < 4; k++) {
                    if (nums[i + k][j] == -1) {
                        break;
                    }

                    vertical += nums[i + k][j];

                    if (k == 3) {
                        maxResult = Math.max(maxResult, vertical);
                    }
                }
            }
        }
    }

    public static void calculateOtherShape(int i, int j, int sum) {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                // 테두리에서 가장 큰 값 구하기
                int max = Integer.MIN_VALUE;
                aa: for (int k = 0; k < dx.length; k++) {
                    switch (2 * x + y) {
                        case 0:
                            if (k == 0 || k == 4) {
                                continue aa;
                            }
                            break;
                        case 1:
                            if (k == 1 || k == 6) {
                                continue aa;
                            }
                            break;
                        case 2:
                            if (k == 2 || k == 5) {
                                continue aa;
                            }
                            break;
                        case 3:
                            if (k == 3 || k == 7) {
                                continue aa;
                            }
                            break;
                    }

                    max = Math.max(max, nums[i + dx[k]][j + dy[k]]);
                }

                maxResult = Math.max(maxResult, sum - nums[i + x][j + y] + max);
            }
        }
    }

}
