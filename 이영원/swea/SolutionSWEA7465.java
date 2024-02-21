import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 서로소 집합 써보기
class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
       	int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int answer=0;
            
            boolean[] check = new boolean[N+1];
            int[] set = new int[N+1];
            
            for(int i=1;i<N+1;i++){ // set 설정
                set[i]=i;
            }
            
            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
				union(from, to, set);
//                System.out.println(Arrays.toString(set));
            }
            
            // 대표만 check해서 수 뽑기
            for(int i=1;i<N+1;i++){
            	int a = find(set[i], set);
                if(!check[a]) {
                    answer++;
                    check[a]=true;
                }
            }
            //System.out.println(Arrays.toString(check));
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
	// 합치는 메소드
    private static void union(int org, int change, int[] set){
        int a = find(org, set);
        int b = find(change, set);
        if(a!=b){
            set[a]=b;
        }
    }
    
    // 찾는 메소드
    private static int find(int org, int[] set){
        // 얘가 대빵이라는 뜻
        if(set[org]==org){
            return set[org];
        }else{
            // path compression
            set[org] = find(set[org], set);
        }
        return set[org];
    }
}
