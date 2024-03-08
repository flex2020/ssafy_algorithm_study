import java.util.*;
import java.io.*;

public class SolutionBaekJoon1525 {

    static class Puzzle {
        int x;
        int y;
        StringBuilder nums;

        Puzzle(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Puzzle(int x, int y, StringBuilder nums) {
            this.x = x;
            this.y = y;
            this.nums = nums;
        }
    }

    static final int N = 3;
    static Puzzle origin;
    static Set<String> visited;
    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 숫자 입력받기
        origin = null;
        String nums = "";
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                String num = st.nextToken();
                nums += num;

                if (num.equals("0")) {
                    origin = new Puzzle(i, j);
                }
            }
        }
        origin.nums = new StringBuilder(nums);

        // 최소의 이동 횟수 구하기
        visited = new HashSet<>();
        int minResult = calculateMinResult();

        System.out.println(minResult);
    }

    public static int calculateMinResult() {
        Queue<Puzzle> queue = new ArrayDeque<>();
        // 첫 번째
        visited.add(origin.nums.toString());
        queue.offer(origin);

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int c = 0; c < size; c++) {
                Puzzle p = queue.poll();
                int current = p.x * 3 + p.y;

                // 도착지인 경우
                if (current == 8 && isAnswer(p.nums)) {
                    return result;
                }

                // 상하좌우 살펴보기
                for (int direction = 0; direction < dx.length; direction++) {
                    int nextX = p.x + dx[direction];
                    int nextY = p.y + dy[direction];

                    // 맵 범위를 벗어나는 경우
                    if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                        continue;
                    }

                    int next = nextX * 3 + nextY;
                    // 숫자 이동하기
                    char nextNum = p.nums.charAt(next);
                    StringBuilder nextNums = new StringBuilder(p.nums.toString());
                    nextNums.setCharAt(current, nextNum);
                    nextNums.setCharAt(next, '0');

                    // 이미 살펴본 경우
                    if (validateDuplicated(nextNums)) {
                        queue.offer(new Puzzle(nextX, nextY, nextNums));
                    }
                }
            }

            result++;
        }

        return -1;
    }

    public static boolean validateDuplicated(StringBuilder nums) {
        int size = visited.size();
        visited.add(nums.toString());

        if (visited.size() == size) {
            return false;
        }
        return true;
    }

    public static boolean isAnswer(StringBuilder nums) {
        if (nums.toString().equals("123456780")) {
            return true;
        }
        return false;
    }

}
