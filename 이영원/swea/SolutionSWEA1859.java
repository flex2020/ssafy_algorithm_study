import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
        long answer=0;
        StringTokenizer st;
        int maxIdx = 0;
        int max = 0;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int arr[] = new int[N];
            answer=0;
            max=0;
            
            for(int i=0;i<N;i++){ // 입력하면서 max값 인덱스 찾기
				arr[i]=Integer.parseInt(st.nextToken());
            }
            
            for(int i=N-1;i>=0;i--){ // 뒤에서부터 크면 그걸로 바꾸고 작으면 더한다.
                if(max<arr[i]){
                 	max=arr[i];   
                }else{
                 	answer+=max-arr[i];   
                }
             	   
            }
            
			System.out.println("#" + test_case + " " + answer);
		}
	}
}
