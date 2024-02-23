import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = Integer.parseInt(br.readLine());
            int[] clothes = new int[N];
            int answer=0;
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                clothes[i] = Integer.parseInt(st.nextToken());
                answer+= clothes[i];
            }
            
            Arrays.sort(clothes);
            
            for(int i=clothes.length-3 ; i>=0;i-=3){
				answer-=clothes[i];
            }
            System.out.println("#" + test_case + " " + answer);
		}
	}
}
