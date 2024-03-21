import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Union Find
public class Main {

    static int[] set;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 점 개수
        M = Integer.parseInt(st.nextToken()); // 시행횟수
        int answer=0;
        set = new int[N];

        make();

        boolean flag=false;


        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            if(flag) continue;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(!merge(a, b)){
                answer=i;
                flag=true;
            }
        }

        System.out.println(answer);


    }

    private static void make(){
        for (int i = 0; i < N; i++) {
            set[i]=i;
        }
    }

    private static int find(int a){
        if(set[a]==a){
            return a;
        }else{
            return set[a]=find(set[a]);
        }
    }

    private static boolean merge(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA==rootB) return false;
        set[rootA]=rootB;
        return true;
    }
}
