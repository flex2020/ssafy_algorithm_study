import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int N, M, K;
    static int[] candies;
    static int[] arr;
    static HashMap<Integer, int[]> map;
    static int[] idxArr;
    static int answer;

    static int[][] dp;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 아이 수
        M = Integer.parseInt(st.nextToken()); // 관계 수
        K = Integer.parseInt(st.nextToken()); // 최소 우는아이 제한 수

        candies = new int[N+1];
        arr = new int[N+1];

        make();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b); // 그룹으로 만들기
        }

        map = new HashMap<>();

        // 그룹을 map으로 해서 만들기 [개수, 사탕합]
        for (int i = 1; i < N+1; i++) {
            int parentI = find(i);
            if(!map.containsKey(parentI)){
                map.put(parentI, new int[]{1, candies[i]});
            }else{
                int[] mapArr = map.get(parentI);
                mapArr[0]++;
                mapArr[1]+=candies[i];
            }
        }

        // map의 인덱스를 담은 배열
        idxArr = new int[map.size()+1];
        int num = 1;
        dp = new int[idxArr.length][K]; // 0-1 knapsack 진행

        for(int key : map.keySet()){
            idxArr[num++] = key; // 키를 넣기
        }

//        System.out.println(Arrays.toString(candies));
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(idxArr));
//
//        for(int[] arr2 : map.values()){
//            System.out.println(Arrays.toString(arr2));
//        }

        for (int i = 1; i < idxArr.length; i++) {
            int[] mapArr = map.get(idxArr[i]);
            for (int j = 1; j < K; j++) {
                if(mapArr[0] > j) dp[i][j]=dp[i-1][j]; // 없으면 넣기
                else { // 전에꺼랑 비교해서 넣기
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-mapArr[0]] + mapArr[1]);
                }
            }
        }

//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }


        System.out.println(dp[idxArr.length-1][K-1]);
    }

    private static void make(){
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i;
        }
    }

    private static int find(int num){
        if(arr[num]==num) return num;
        else return arr[num]=find(arr[num]);
    }

    private static void union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parentA!=parentB) arr[parentB] = parentA;
    }

}
