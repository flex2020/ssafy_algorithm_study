import java.io.*;
import java.util.*;

// 벨만포드 알고리즘이란?
// 그래프 최단 경로 구하는 알고리즘
// 하나의 정점에서 출발하는 최단 거리를 구함(출발지만 정함)
// 음수 사이클 없어야 함(음수 가중치 허용)
// O(nm) 시간 복잡도 가짐 (n은 정점, m은 간선)
public class Main {

    static int N;
    static int M;

    static class Edge{
        int start; // 나가는 정점
        int end; // 들어오는 정점
        int cost; // 비용

        public Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static long[] dist;
    static final long INF = Long.MAX_VALUE;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 도시 개수
        M = Integer.parseInt(st.nextToken()); // 버스 노선의 개수

        Edge[] edgeList = new Edge[M];

        for (int i = 0; i < M; i++) {
            st= new StringTokenizer(br.readLine());
            edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        // 정점(도시) 개수만큼 반복
        for (int i = 1; i < N+1; i++) {
            // 버스 노선(간선) 개수만큼 반복
            for (int j = 0; j < M; j++) {
                // 현재 간선의 들어오는 정점에 대해 비교
                if(dist[edgeList[j].start]!=INF && dist[edgeList[j].end] > dist[edgeList[j].start] + edgeList[j].cost){
                    dist[edgeList[j].end] = dist[edgeList[j].start] + edgeList[j].cost;
                    if(i==N){ // 마지막 싸이클에도 값이 변경되는 경우 음의 싸이클이 존재한다는 뜻이다.
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        for (int i = 2; i < N+1; i++) {
            if(dist[i]!=INF) sb.append(dist[i]).append("\n");
            else sb.append(-1).append("\n");
        }

        System.out.print(sb.toString());
        return;
    }

}
