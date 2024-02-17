import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 위상정렬이라는데 뭘 어떻게 풀었는지 모르겠지만 풀렸습니다.
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 해당 숫자가 들어갔는지 아닌지 체크하는 배열
        boolean[] check = new boolean[N+1];
        int[][] compare = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            compare[i][0] = Integer.parseInt(st.nextToken());
            compare[i][1] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            // 둘다 안들어가있는 경우 뒤쪽에 넣어주고 true로 바꿔주기
            if(!check[compare[i][0]] && !check[compare[i][1]]){
                dq.offerLast(compare[i][0]);
                dq.offerLast(compare[i][1]);
                check[compare[i][0]]=true;
                check[compare[i][1]]=true;
            }
            // 앞에께 들어가있는 경우 뒤에것만 넣어주기
            else if(!check[compare[i][1]]){
                dq.offerLast(compare[i][1]);
                check[compare[i][1]]=true;
            }
            // 뒤에께 들어가있는 경우 앞에것만 넣어주기
            else if(!check[compare[i][0]]){
                dq.offerFirst(compare[i][0]);
                check[compare[i][0]]=true;
            }
        }

        // 비교하지 않은건 그냥 넣어주기
        for (int i = 1; i < check.length; i++) {
            if(!check[i]) sb.append(i).append(" ");
        }

        // 비교한건 그대로 넣어주기
        while(!dq.isEmpty()){
            sb.append(dq.pollFirst()).append(" ");
        }

        System.out.println(sb);

    }
}
