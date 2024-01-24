import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st;
            int answer=0;
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][2];
            int speed=0;
            
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                arr[i][0]=Integer.parseInt(st.nextToken());
                if(arr[i][0]!=0){
                 	arr[i][1]=Integer.parseInt(st.nextToken());   
                }
                // 여기까지 입력
                
                switch(arr[i][0]){
                    case 1:
                        speed+=arr[i][1];
                        break;
                    case 2:
                        speed-=arr[i][1];
                        if(speed<0){
                            speed=0;
                            continue;
                        }
                        break;
                }
                answer+=speed;
            }
			System.out.println("#"+test_case+" "+answer);
		}
	}
}
