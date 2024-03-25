import java.util.*;
import java.io.*;

public class SolutionBaekJoon20040 {

    static int N;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int result = 0;
        makeSet();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (unionSet(x, y)) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }

    public static void makeSet() {
        parents = new int[N];

        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    public static int findParent(int n) {
        if (n == parents[n]) {
            return parents[n];
        } else {
            return parents[n] = findParent(parents[n]);
        }
    }

    public static boolean unionSet(int x, int y) {
        int xParent = findParent(x);
        int yParent = findParent(y);

        if (xParent == yParent) {
            return true;
        } else {
            parents[yParent] = xParent;
            return false;
        }
    }

}
