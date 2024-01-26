import java.util.Scanner;
import java.io.FileInputStream;
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
            String answer = "Possible";
            int idx=0;
            int minus=0;
            int bbang=0;
            
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] people = new int[N];
            st = new StringTokenizer(br.readLine());
            
            for(int i=0;i<N;i++){ // 여기까지 입력
             	people[i]=Integer.parseInt(st.nextToken());   
            }
            
            Arrays.sort(people); // 오름차순 정렬
            
            for(int i=0;i<N;i++){
            	bbang=(people[i]/M*K)-minus;
                if(bbang>0){
                    minus++;
                }else{
                 	answer="Impossible";
                    break;
                }
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
}
