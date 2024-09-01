import java.io.*;
import java.util.*;

public class Main {

    static int K, V, E;

    static int[] check;
    static ArrayList<ArrayList<Integer>> arrList;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine()); // 테케 수

        for (int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 정점 개수
            E = Integer.parseInt(st.nextToken()); // 간선 개수

            check = new int[V+1];

            arrList = new ArrayList<>();

            for (int i = 0; i <= V; i++) {
                arrList.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                arrList.get(a).add(b);
                arrList.get(b).add(a);
            }

            calculate();
        }
    }

    private static void calculate(){
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 1; i <= V; i++) {
            if(check[i]==0) { // 시작할 때 or 아무것도 연결 되어있지 않은 경우, 1로 설정
                dq.offerLast(i);
                check[i]=1;
            }

            while(!dq.isEmpty()){
                int n = dq.pollFirst();

                // n이랑 연결된 next들을 모두 탐색하기
                for(int next : arrList.get(n)){
                    if(check[next]==0) { // 한번도 방문하지 않은 경우, dq에 넣고 그룹은 반대로 지정
                        dq.offerLast(next);
                        check[next] = check[n]%2+1;
                    }

                    if(check[next]==check[n]){ // n이랑 next랑 그룹이 같으면 NO
                        System.out.println("NO");
                        return;
                    }
                }
            }
        }

        // 다 통과하면 YES
        System.out.println("YES");
    }

}
