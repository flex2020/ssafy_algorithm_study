class Solution {
    int N, M;
    int[] answer;
    int[] out, in;

    public int[] solution(int[][] edges) {
        answer = new int[4];
        M = edges.length;
        // 1. root 노드 찾기
        setDegree(edges);
        for (int i=1; i<=N; i++) {
            if (out[i] >= 2 && in[i] == 0) answer[0] = i;
            else if (out[i] >= 2) answer[3] += 1;
            else if (out[i] == 0 && in[i] >= 1) answer[2] += 1;
        }
        answer[1] = out[answer[0]] - answer[2] - answer[3];
        return answer;
    }
    private void setDegree(int[][] edges) {
        N = 0;
        out = new int[1_000_001];
        in = new int[1_000_001];
        // 진입차수 0, 진출차수 2인 노드가 루트노드임
        for (int i=0; i<edges.length; i++) {
            out[edges[i][0]] += 1;
            in[edges[i][1]] += 1;
            N = Math.max(edges[i][0], N);
            N = Math.max(edges[i][1], N);
        }
    }
}
