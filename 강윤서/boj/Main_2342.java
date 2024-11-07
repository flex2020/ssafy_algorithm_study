import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer> list;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        while (true) {
            int value = Integer.parseInt(st.nextToken());
            if (value == 0) break;
            list.add(value);
        }
        N = list.size();
        dp = new int[N][5][5];
        dfs(0, 0, 0);
        System.out.println(dp[0][0][0]);
    }

    public static int dfs(int left, int right, int idx) {
//        System.out.println(left + " " + right + " " + idx);
        if (idx == N) {
            return 0;
        }
        if (dp[idx][left][right] != 0) {
            return dp[idx][left][right];
        }

        // (left, right) 에서 (next, right) 또는 (left, next) 로 이동하기
        int next = list.get(idx);

        int lp = dfs(next, right, idx + 1) + getPoint(left, next);
        int rp = dfs(left, next, idx + 1) + getPoint(right, next);

        // 왼발, 오른발 이동했을 때 중에 최소의 값 dp에 저장
        dp[idx][left][right] = Math.min(lp, rp);
        return dp[idx][left][right];
    }

    public static int getPoint(int foot, int next) {
        if (foot == next) return 1;
        if (foot == 0) return 2;
        if (foot + 2 == next || (foot + 2) % 4 == next) return 4;
        return 3;
    }
}
