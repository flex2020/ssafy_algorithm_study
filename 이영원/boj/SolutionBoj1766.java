import java.io.*;
import java.util.*;
import java.awt.Point;

// 어우 못풀었당
public class Main {

    static int N, M;
    static int[] arr;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        list = new ArrayList[N+1];
        boolean[] visited = new boolean[N+1];

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            arr[b]++;
        }

        for (int i = 1; i <= N; i++) {
            if(arr[i]==0) {
                dq.offerLast(i);
                break;
            }
        }

        while(!dq.isEmpty()){
            int num = dq.pollFirst();
            visited[num]=true;
            for(int a : list[num]){
                if(!visited[a]) arr[a]--;
            }
            for (int i = 1; i <= N; i++) {
                if(!visited[i] && arr[i]==0) {
                    dq.offerLast(i);
                    break;
                }
            }
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString());
    }

}
