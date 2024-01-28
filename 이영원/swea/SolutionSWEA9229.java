import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            int answer = -1;
            int result = 0;
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] bags = new int[N];
            int start = 0;
            int end =N-1;
            st = new StringTokenizer(br.readLine());
            
            for(int i=0;i<N;i++){ // bags 입력
                bags[i] = Integer.parseInt(st.nextToken());
            }
            
            Arrays.sort(bags); // 오름차순 정렬
            
            for(;start<N-1;start++){
                for(end=N-1;end!=start;end--){
                    result = bags[start]+bags[end];
                    if(result<=M && answer<result){
                        answer = result;
                    } else if(answer>=result) break;
                }
                if(result>M) break; // (여기)
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
}
