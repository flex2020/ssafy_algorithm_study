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
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] cities = new int[N+2];
            int[] gates = new int[N+2];
            int cnt = 1;
            int answer = 0;
            
            cities[0]=1;
            cities[N+1]=1;
            gates[0]=0;
            for(int i=1;i<N+1;i++){
                cities[i] = Integer.parseInt(st.nextToken());
                if(cities[i]==1) gates[cnt++]=i; // 차원관문 인덱스를 따로 저장
            }
            gates[cnt++]=N+1;

            for(int i=1;i<cnt;i++){
                if((gates[i]-gates[i-1])%D==0){
                    answer+=(gates[i]-gates[i-1])/D - 1;	
                }else{
                	answer+=(gates[i]-gates[i-1])/D;
                }
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
}
