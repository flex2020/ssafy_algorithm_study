import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int N; // 가수의 수
    static int M; // 보조 PD의 수
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 진입차수
        int[] arr = new int[N+1];

        // 누가 있는지
        List<List<Integer>> arrayList = new ArrayList<>();

        for (int i = 0; i < N+1; i++) {
            arrayList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            for (int j = 1; j < num; j++) {
                int n = Integer.parseInt(st.nextToken());
                arrayList.get(n).add(start);
                arr[n]++;
                start = n;
            }
        }

//        for (int i = 1; i < N+1; i++) {
//            System.out.println(arrayList.get(i));
//        }
//        System.out.println(Arrays.toString(arr));

        boolean[] check = new boolean[N+1];
        while(true){
            boolean flag = false;
            for (int i = 1; i < N+1; i++) {
                // 이미 본 놈인지 확인하기
                if(arr[i]==0 && !check[i]){
                    check[i]=true;
                    flag=true;
                    // 진입한거 지우기
                    for (int j = 1; j < N+1; j++) {
                        while(arrayList.get(j).contains(i)){
                            arrayList.get(j).remove(Integer.valueOf(i));
                            arr[j]--;
                        }
                    }
                    sb.append(i).append("\n");
                }
            }

            if(!flag) break;
        }

        // 순서를 정할 수 없는게 있었는지 확인하기
        for (int i = 1; i < N+1; i++) {
            if(!check[i]){ // 있으면 넣기
                System.out.println(0);
                return;
            }
        }

        System.out.println(sb.toString());
    }
}
