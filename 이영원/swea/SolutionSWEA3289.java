import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            int[] set = new int[N+1];
            // set 초기화
            for(int i=1 ; i<N+1;i++){
                set[i]=i;
            }
            
            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                if(cmd==1){
                    check(from, to, set, sb);
                }else{
                    union(from, to, set);
                }
            }
            
            System.out.println("#" + test_case + " " + sb);

		}
	}
    
	// 체크
    private static void check(int num1, int num2, int[] set, StringBuilder sb){
        int a = find(num1, set);
        int b = find(num2, set);
        if(a==b) {
            sb.append(1);
            return;
        }
        sb.append(0);
    }
    
    // 찾는거
    private static int find(int num, int[] set){
        if(set[num]==num){
            return set[num];
        }else{
            set[num] = find(set[num], set);
        }
        return set[num];
    }
    
    // 합치는거
    private static void union(int org, int change, int[] set){
        int a = find(org, set);
        int b = find(change, set);
        if(a != b){
            set[a] = b;
        }
    }
}
