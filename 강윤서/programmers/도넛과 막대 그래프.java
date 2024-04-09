package 강윤서.programmers;
import java.util.*;
class Solution {
    static int maxNode; 
    static int[] indegrees, outdegrees, parents;
    static int[][] cnt;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }
        indegrees = new int[maxNode+1];
        outdegrees = new int[maxNode+1];
        // parents = new int[maxNode+1];
        // cnt = new int[maxNode+1][2];
        for (int[] edge : edges) {
            outdegrees[edge[0]]++;
            indegrees[edge[1]]++;
        }
        for (int i=1; i<=maxNode; i++) {
            if (outdegrees[i] >= 2 && indegrees[i] == 0) {
                answer[0] = i; // 삽입한 노드
            } else if (outdegrees[i] >= 2 && indegrees[i] >= 2) {
                answer[3]++;
            } else if (indegrees[i] != 0 && outdegrees[i] == 0) {
                answer[2]++;
            }
        }
        answer[1] = outdegrees[answer[0]] - answer[3] - answer[2];
        // make();
        // for (int[] edge : edges) {
        //     if (edge[0] == answer[0]) {
        //         indegrees[edge[1]]--;
        //         continue;
        //     }
        //     union(edge[0], edge[1]);
        // }
        // for (int i=1; i<=maxNode; i++) {
        //     if (i == answer[0]) continue;
        //     cnt[parents[i]][0]++;
        //     cnt[parents[i]][1] += indegrees[i];
        // }
        // System.out.println(Arrays.toString(indegrees));
        // System.out.println(Arrays.toString(outdegrees));
        // // System.out.println(Arrays.toString(parents));
        // // for (int[] c : cnt) {
        // //     System.out.println(Arrays.toString(c));
        // // }
        // for (int i=1; i<=maxNode; i++) {
        //     if (cnt[i][0] == 0) continue;
        //     if (cnt[i][0] == cnt[i][1]) { // 도넛
        //         answer[1]++;
        //     } else if (cnt[i][0] > cnt[i][1]) { // 막대
        //         answer[2]++;
        //     } else {
        //         answer[3]++;
        //     }
        // }
        return answer;
    }
    // public static void make() {
    //     for (int i=1; i<=maxNode; i++) {
    //         parents[i] = i;
    //     }
    // }
    // public static void union(int a, int b) {
    //     int rootA = find(a);
    //     int rootB = find(b);
    //     parents[rootA] = rootB;
    // }
    // public static int find(int node) {
    //     if (parents[node] == node) return parents[node];
    //     return parents[node] = find(parents[node]);
    // }
}
