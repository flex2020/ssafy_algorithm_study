import java.util.*;
class Solution {
    static int N;
    static int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        N = n;
        parent = new int[n];
        Arrays.sort(costs, (c1, c2) -> c1[2] - c2[2]); // 간선 비용 오름차순 정렬
        make();
        for (int[] cost : costs) {
            if (union(cost[0], cost[1])) {
                answer += cost[2];
            }
        }
        return answer;
    }
    public static void make() {
        for (int i=0; i<N; i++) {
            parent[i] = i;
        }
    }
    public static int find(int a) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }
    public static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) { // 사이클 발생
            return false;
        } else {
            parent[rootB] = rootA;
            return true;
        }
    }
}
