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

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int n = Integer.parseInt(br.readLine());
            StringBuilder answer = new StringBuilder();
            int plus=1;
            int[][] arr= new int[2][8]; // 두개의 공간에 번갈아 옮겨가면서 저장하기 위해 2차원배열 생성
            int ans = 1; // 저장공간 인덱스
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<8;i++){
             	arr[0][i]=Integer.parseInt(st.nextToken());
            }
            
            do {
                arr[ans][7] = arr[(ans+1)%2][0]-plus; // 가장 처음 값 맨 끝으로 옮겨주기
                for(int i=1;i<8;i++){ // 밀어주기
                    arr[ans][i-1]=arr[(ans+1)%2][i];
                }

                if(arr[ans][7]<=0) { // 맨 끝 값이 0보다 작거나 같아지면 암호이므로 0으로 해주고 break
                    arr[ans][7]=0;
                    break;
                }
                plus=plus%5+1; // 더할값 업데이트
                ans=(ans+1)%2; // 저장할 공간 지정
            }while(arr[ans][7]!=0); 
            
            for(int i=0;i<8;i++){
                answer.append(arr[ans][i]).append(" ");
            }
            
            System.out.println("#" + test_case + " " + answer);

		}
	}
}
