import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main
{

    static boolean[][] adjMtx;
    static int[] people;
    static int[] group1;
    static int[] group2;
    static boolean[] groupCheck;
    static int N;
    static int answer = Integer.MAX_VALUE;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());

        people = new int[N+1]; // 1부터 하려고 하나 크게

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            people[i]=Integer.parseInt(st.nextToken());
        }

        adjMtx = new boolean[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int n = Integer.parseInt(st.nextToken());
                adjMtx[i][n]=true;
                adjMtx[n][i]=true;
            }
        }

        // group 크기에 맞게 배열을 만들어주고 comb로 만들기
        for (int i = 1; i <= N/2; i++) {
            group1 = new int[i];
            group2 = new int[N-i];
            groupCheck = new boolean[N+1];
            comb(1, 0);
        }

        if(answer==Integer.MAX_VALUE) answer=-1;

        System.out.println(answer);


    }

    // dfs를 통해서 같은 그룹에 전부 속해있는지 체크
    private static void dfs(int idx, int[] group, boolean[] visited){
        // basis part
        if(idx==visited.length){
            return;
        }
        // inductive part
        for (int i = 1; i < N+1; i++) {
            if(adjMtx[idx][i] && !visited[i]) {
                boolean flag = false;
                for (int j = 0; j < group.length; j++) {
                    if(i==group[j]) {
                        flag=true;
                        break;
                    }
                }
                if(flag){
                    visited[i]=true;
                    dfs(i, group, visited);
                }
            }
        }
    }

    // 계산하기
    private static void calculate(){
        boolean[] visited = new boolean[N+1];
        visited[group1[0]]=true;
        visited[group2[0]]=true;
        if(group1.length!=1){
            dfs(group1[0], group1, visited);
        }
        if(group2.length!=1){
            dfs(group2[0], group2, visited);
        }

        // 같은 그룹에 속해있지 않다면 이건 아니므로 리턴
        for (int i = 1; i < N+1; i++) {
            if(!visited[i]) return;
        }

//        System.out.println(Arrays.toString(group1));
//        System.out.println(Arrays.toString(group2));

        // 같은 그룹이 맞다면 차이를 구함
        int sumA=0;
        int sumB=0;
        for (int i = 0; i < group1.length; i++) {
            sumA+=people[group1[i]];
        }
        for (int i = 0; i < group2.length; i++) {
            sumB+=people[group2[i]];
        }

        // 차이의 최솟값을 answer에 저장
        answer = Math.min(answer, Math.abs(sumA-sumB));
    }

    // group2 만들기
    private static void getGroup2(){
        int cnt=0;
        for (int i = 1; i < N+1; i++) {
            if(!groupCheck[i]){
                group2[cnt++] = i;
            }
        }

        calculate();
    }

    // 조합짜는 코드
    private static void comb(int idx, int cnt){
        // basis part
        if(cnt==group1.length){
            // group1이 정해졌다면 남는걸로 group2 구성
            getGroup2();
            return;
        }
        // inductive part
        for (int i = idx; i < people.length; i++) {
            group1[cnt]=i;
            groupCheck[i]=true;
            comb(i+1, cnt+1);
            groupCheck[i]=false;
        }
    }
}
