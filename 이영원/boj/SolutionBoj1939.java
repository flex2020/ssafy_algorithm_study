import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    static class Bridge{
        int n;
        int cost;

        public Bridge(int n, int cost){
            this.n = n;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 섬 수
        M = Integer.parseInt(st.nextToken()); // 다리 수

        int[] check = new int[N+1];

        Arrays.fill(check, Integer.MIN_VALUE);

        PriorityQueue<Bridge> pq = new PriorityQueue<>((a, b) -> {
            return b.cost - a.cost;
        });

        ArrayList<ArrayList<Bridge>> bridgeList = new ArrayList<>();

        for (int i = 0; i < N+1; i++) {
            bridgeList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // A
            int b = Integer.parseInt(st.nextToken()); // B
            int c = Integer.parseInt(st.nextToken()); // C (중량제한)

            bridgeList.get(a).add(new Bridge(b, c));
            bridgeList.get(b).add(new Bridge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        check[start]= 0;

        for (int i = 0; i < bridgeList.get(start).size(); i++) {
            Bridge b = bridgeList.get(start).get(i);
            pq.offer(b);
            check[b.n] = b.cost;
        }

        while(true){
            Bridge b = pq.poll();
            if(b.n==end){
                System.out.println(b.cost);
                break;
            }
            for (Bridge bridge : bridgeList.get(b.n)) {
                if(check[bridge.n] == Integer.MIN_VALUE) { // 방문한 적이 없는경우
                    if(bridge.cost <= b.cost){
                        pq.offer(bridge);
                        check[bridge.n] = bridge.cost;
                    } else{
                        pq.offer(new Bridge(bridge.n, b.cost));
                        check[bridge.n] = b.cost;
                    }

                }else if(check[bridge.n] < b.cost){ // 방문한 적은 있지만 더 크게 들어올 수 있는 경우
                    if(bridge.cost <= b.cost){
                        pq.offer(bridge);
                        check[bridge.n] = bridge.cost;
                    } else{
                        pq.offer(new Bridge(bridge.n, b.cost));
                        check[bridge.n] = b.cost;
                    }
                }
            }
        }

    }

}
