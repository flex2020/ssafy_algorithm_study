import java.util.*;

class Solution {

    public int[] answer;
    public int last;
    public int[] parents;
    public int[][] edgeCnts; // 0: in, 1: out

    public int[] solution(int[][] edges) {
        // 마지막 노드 번호 알아내기
        last = Integer.MIN_VALUE;
        for (int i = 0; i < edges.length; i++) {
            last = Math.max(last, Math.max(edges[i][0], edges[i][1]));
        }

        // 진입/진출차수 알아내기
        parents = new int[last + 1];
        edgeCnts = new int[last + 1][2];
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            // make parent
            parents[from] = from;
            parents[to] = to;

            edgeCnts[from][1]++;
            edgeCnts[to][0]++;
        }

        answer = new int[4];
        // 새로 추가된 노드 알아내기
        for (int i = 1; i < edgeCnts.length; i++) {
            if (edgeCnts[i][0] == 0 && edgeCnts[i][1] >= 2) {
                answer[0] = i;
                break;
            }
        }

        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            // 새로 추가된 노드인 경우
            if (from == answer[0]) {
                continue;
            }

            // 인접한 노드 한 그룹으로 묶기
            if (findParent(from) != findParent(to)) {
                union(from, to);
            }
        }

        calculateResult();
        return answer;
    }

    public int findParent(int a) {
        if (parents[a] == a) {
            return parents[a];
        } else {
            return parents[a] = findParent(parents[a]);
        }
    }

    public void union(int from, int to) {
        int fromParent = findParent(from);
        int toParent = findParent(to);

        parents[fromParent] = parents[toParent];
    }

    public void calculateResult() {
        // 노드 개수 세기
        int[] nodeCnts = new int[last + 1];
        int[] pEdgeCnts = new int[last + 1];
        for (int num = 1; num < parents.length; num++) {
            int parent = findParent(parents[num]);

            // 새로 추가된 노드인 경우
            if (parent == answer[0]) {
                continue;
            }

            // 존재하지 않는 노드인 경우
            if (parent == 0) {
                continue;
            }

            nodeCnts[parent]++;
            pEdgeCnts[parent] += edgeCnts[num][1];
        }

        // 그래프 모양 판별하기
        for (int parent = 1; parent < parents.length; parent++) {
            if (nodeCnts[parent] == 0) {
                continue;
            }

            if (nodeCnts[parent] == pEdgeCnts[parent]) {
                answer[1]++;
            } else if (nodeCnts[parent] > pEdgeCnts[parent]) {
                answer[2]++;
            } else if (nodeCnts[parent] < pEdgeCnts[parent]) {
                answer[3]++;
            }
        }
    }
}